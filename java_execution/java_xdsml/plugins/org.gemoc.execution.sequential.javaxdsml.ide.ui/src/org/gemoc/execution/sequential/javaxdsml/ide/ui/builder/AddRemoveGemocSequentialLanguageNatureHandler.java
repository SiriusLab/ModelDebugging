package org.gemoc.execution.sequential.javaxdsml.ide.ui.builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.commands.*;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.gemoc.commons.eclipse.core.resources.NatureToggling;
import org.gemoc.commons.eclipse.core.resources.Project;
import org.gemoc.commons.eclipse.pde.JavaProject;
import org.gemoc.commons.eclipse.pde.ui.PluginConverter;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlFactoryImpl;
import org.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper;
import org.osgi.framework.BundleException;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;

public class AddRemoveGemocSequentialLanguageNatureHandler extends AbstractHandler {

	//private ISelection selection;

	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		//
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element)
							.getAdapter(IProject.class);
				}
				if (project != null) {
					try {
						toggleNature(project);
					} catch (CoreException e) {
						//TODO log something
						throw new ExecutionException("Failed to toggle nature",
								e);
					}
				}
			}
		}

		return null;
	}

	/**
	 * Toggles sample nature on a project
	 *
	 * @param project
	 *            to have sample nature added or removed
	 */
	private void toggleNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] natures = description.getNatureIds();

		for (int i = 0; i < natures.length; ++i) {
			if (GemocSequentialLanguageNature.NATURE_ID.equals(natures[i])) {
				// Remove the nature
				String[] newNatures = new String[natures.length - 1];
				System.arraycopy(natures, 0, newNatures, 0, i);
				System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
				return;
			}
		}

		// Add the nature
		String[] newNatures = new String[natures.length + 1];
		System.arraycopy(natures, 0, newNatures, 0, natures.length);
		newNatures[natures.length] = GemocSequentialLanguageNature.NATURE_ID;
		description.setNatureIds(newNatures);
		project.setDescription(description, null);
	}
	
	/**
	 * Toggles sample nature on a project
	 * 
	 * @param project
	 *            to have sample nature added or removed
	 */
	public void toggleNature(IProject project, String languageName) 
	{
		try 
		{
			NatureToggling result = Project.toggleNature(project, GemocSequentialLanguageNature.NATURE_ID);
			switch (result) {
				case Added:
					JavaProject.create(project);
					addPluginNature(project);
					addGemocNature(project, languageName);
					break;
				case Removed:
					break;	
				default:
					break;
			}
		} 
		catch (CoreException | IOException e) 
		{
			Activator.error("Problem while adding Gemoc Language nature to project. " + e.getMessage(), e);
		} 
	}
	
	private void addPluginNature(IProject project) throws CoreException {
		if(!project.hasNature("org.eclipse.pde.PluginNature")) 
		{
			try {
				// create first the plugin.xml file
				PluginXMLHelper.createEmptyTemplateFile(project.getFile(PluginXMLHelper.PLUGIN_FILENAME), false);					
				// convert to plugin and add necessary entries in the build.properties
				PluginConverter.convert(project);							
				// complement manifest
				ManifestChanger changer = new ManifestChanger(project);
				changer.addPluginDependency(org.gemoc.xdsmlframework.api.Activator.PLUGIN_ID, "0.1.0", true, true);
				changer.addPluginDependency("org.eclipse.emf.ecore.xmi", "2.8.0", true, true);				
				changer.addPluginDependency("org.gemoc.xdsmlframework.api");				
				changer.addPluginDependency("org.gemoc.execution.sequential.javaxdsml.api");		
				changer.addPluginDependency("org.gemoc.executionframework.engine");
				changer.addSingleton();
				changer.addAttributes("Bundle-RequiredExecutionEnvironment","JavaSE-1.7");
				changer.commit();					
			} 
			catch (InvocationTargetException | InterruptedException | IOException | BundleException e) 
			{
				Activator.error("cannot add org.eclipse.pde.PluginNature nature to project due to " + e.getMessage(), e);
			}								
		}
	}
	
	private void addGemocNature(IProject project, String languageName)
			throws CoreException {
		addAsMainNature(project, GemocSequentialLanguageNature.NATURE_ID, null);
		addMissingResourcesToNature(project, languageName);
		addGemocResourcesToBuildProperties(project);
	}

	// add the nature making sure this will be the first
	public static void addAsMainNature(IProject project, String natureID, IProgressMonitor monitor) throws CoreException{
		if (monitor != null && monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		if (!project.hasNature(natureID)) {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 1, natures.length);
			newNatures[0] = natureID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		} else {
			if (monitor != null) {
				monitor.worked(1);
			}
		}
	}
	
	private void addMissingResourcesToNature(IProject project, String languageName) {
		IFile configFile = project.getFile(new Path(Activator.GEMOC_PROJECT_CONFIGURATION_FILE)); 
		if(!configFile.exists()) {
			Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		    Map<String, Object> m = registry.getExtensionToFactoryMap();
		    m.put(Activator.GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION, new XMIResourceFactoryImpl());

		    // Obtain a new resource set
		    ResourceSet resSet = new ResourceSetImpl();

		    // Create the resource
		    Resource resource = resSet.createResource(URI.createURI(configFile.getLocationURI().toString()));
		    // Creates default root elements,
		    SequentialLanguageDefinition ld = Sequential_xdsmlFactoryImpl.eINSTANCE.createSequentialLanguageDefinition();
		    ld.setName(languageName);
		    resource.getContents().add(ld);	
			
			try {
				resource.save(null);
			} catch (IOException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}			
	}
	
	private void addGemocResourcesToBuildProperties(IProject project){


		try {
			Properties properties = new Properties();
			InputStream inputStream = project.getFile("build.properties").getContents();
			properties.load(inputStream);
			String binIncludes = properties.getProperty("bin.includes");
			if(binIncludes != null ){
//				if(!binIncludes.contains("project.xdsml")){
//					properties.put("bin.includes", binIncludes+", project.xdsml");
//				}
			}
			//create an empty InputStream
			PipedInputStream in = new PipedInputStream();
			//create an OutputStream with the InputStream from above as input
			PipedOutputStream out = new PipedOutputStream(in);

			//now work on the OutputStream e.g.
			properties.store(out, "");
			out.close();
			//now you have the OutputStream as InputStream

			//overwrite file contents
			project.getFile("build.properties").setContents(in, true, true, new NullProgressMonitor());
				
		} catch (CoreException e1) {
			Activator.error(e1.getMessage(), e1);
		} catch (IOException e) {
			Activator.error(e.getMessage(), e);
		}
		
	}

}