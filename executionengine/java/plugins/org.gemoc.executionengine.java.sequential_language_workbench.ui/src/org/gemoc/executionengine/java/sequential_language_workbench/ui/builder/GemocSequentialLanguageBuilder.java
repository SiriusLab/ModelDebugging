package org.gemoc.executionengine.java.sequential_language_workbench.ui.builder;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gemoc.executionengine.java.api.extensions.languages.SequentialLanguageDefinitionExtensionPoint;
import org.gemoc.executionengine.java.sequential_language_workbench.ui.Activator;
import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionframework.language_workbench.ui.builder.pde.PluginXMLHelper;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject;
import org.gemoc.executionframework.xdsml_base.SiriusEditorProject;
import org.gemoc.executionframework.xdsml_base.XTextEditorProject;
import org.gemoc.gemoc_language_workbench.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.jdom2.Element;
import org.osgi.framework.BundleException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;

public class GemocSequentialLanguageBuilder extends IncrementalProjectBuilder {

	class GemocSequentialLanguageProjectDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.REMOVED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.CHANGED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class GemocSequentialLanguageResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			updateProjectPluginConfiguration(resource);
			checkConsistency(resource);
			//return true to continue visiting children.
			return true;
		}
	}

	class XMLErrorHandler extends DefaultHandler {
		
		private IFile file;

		public XMLErrorHandler(IFile file) {
			this.file = file;
		}

		private void addMarker(SAXParseException e, int severity) {
			GemocSequentialLanguageBuilder.this.addMarker(file, e.getMessage(), e
					.getLineNumber(), severity);
		}

		public void error(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void warning(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_WARNING);
		}
	}

	public static final String BUILDER_ID = "org.gemoc.executionengine.java.sequential_language_workbench.ui.GemocSequentialLanguageBuilder";

	private static final String MARKER_TYPE = "org.gemoc.executionengine.java.sequential_language_workbench.ui.xmlProblem";

	private SAXParserFactory parserFactory;

	private void addMarker(IFile file, String message, int lineNumber,
			int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	public void checkConsistency(IResource resource){
		// TODO DVK, check consistency of plugin.xml according to existence of projects referenced in the xdsml  
	}
	
	/**
	 * Update plugin.xml according to the model
	 * 
	 * @param resource
	 */
	private void updateProjectPluginConfiguration(IResource resource) {
		if (resource instanceof IFile 
			&& resource.getName().equals(Activator.GEMOC_PROJECT_CONFIGURATION_FILE)) {
			IFile file = (IFile) resource;
			IProject project = file.getProject();
			// try {
			if (file.exists()) {
				Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> m = registry.getExtensionToFactoryMap();
				m.put(Activator.GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION, new XMIResourceFactoryImpl());

				// Obtain a new resource set
				ResourceSet resSet = new ResourceSetImpl();

				// Create the resource
				Resource modelresource = resSet.getResource(URI.createURI(file.getLocationURI().toString()), true);
				SequentialLanguageDefinition languageDef =  (SequentialLanguageDefinition) modelresource.getContents().get(0);
			    // then look to all the content to do the work
				TreeIterator<EObject> it = modelresource.getAllContents();
				String languageRootElement = "";
				
				ManifestChanger manifestChanger = new ManifestChanger(project);
				try {
					while (it.hasNext()) {
						EObject eObject = (EObject) it.next();
						languageRootElement = updateManifestAndPlugin(project, languageRootElement, manifestChanger, eObject);
					}
					manifestChanger.commit();
					
				} catch (CoreException e) {
					Activator.error(e.getMessage(), e);				
				} catch (IOException e) {
					Activator.error(e.getMessage(), e);				
				} catch (BundleException e) {
					Activator.error(e.getMessage(), e);				
				} 
				
				// update entry in plugin.xdsml
				setPluginLanguageNameAndFilePath(project, languageDef.getName());
			}
		}
	}

	private String updateManifestAndPlugin(IProject project,
			String languageRootElement,
			ManifestChanger manifestChanger, EObject eObject)
			throws BundleException, IOException, CoreException {
		
		if (eObject instanceof SequentialLanguageDefinition) {
			SequentialLanguageDefinition languageDefinition =  (SequentialLanguageDefinition) eObject;
			if(languageDefinition.isNeedMelangeSynchronization()){
//				MelangeGenerator melangeGenerator = new MelangeGenerator(project, languageDefinition);
//				melangeGenerator.updateGeneratedMelange(manifestChanger);
			}
		}
		
		if (eObject instanceof DomainModelProject) {
			DomainModelProject domainModelProject = (DomainModelProject) eObject;
			manifestChanger.addPluginDependency(domainModelProject.getProjectName());
			updateModelLoaderClass(project, domainModelProject.getModelLoaderClass());
			if(domainModelProject.getModelLoaderClass() == null){
				manifestChanger.addPluginDependency(org.gemoc.gemoc_language_workbench.extensions.sirius.Activator.PLUGIN_ID);
			}
			languageRootElement = domainModelProject.getDefaultRootEObjectQualifiedName();
		}
		if (eObject instanceof DSAProject) {
			DSAProject dsaProject = (DSAProject) eObject;
			manifestChanger.addPluginDependency( dsaProject.getProjectName());		
		}

		if (eObject instanceof XTextEditorProject) {
			XTextEditorProject xtextProject = (XTextEditorProject) eObject;
			manifestChanger.addPluginDependency( xtextProject.getProjectName());
		}

		if( eObject instanceof SiriusEditorProject){
			SiriusEditorProject editorProject = (SiriusEditorProject) eObject;
			manifestChanger.addPluginDependency( editorProject.getProjectName());
		}
		if( eObject instanceof SiriusAnimatorProject){
			SiriusAnimatorProject animatorProject = (SiriusAnimatorProject) eObject;
			manifestChanger.addPluginDependency( animatorProject.getProjectName());
		}
		return languageRootElement;
	}
	
	/**
	 * create or replace existing ModelLoaderClass by an implementation that is
	 * able to load models of the domain
	 * 
	 * @param project
	 * @param ld
	 */
	protected void updateModelLoaderClass(IProject project, String modelLoaderClass) {
		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		Element gemocExtensionPoint = helper.getOrCreateExtensionPoint(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(
				gemocExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT,
				modelLoaderClass != null ? modelLoaderClass : "org.gemoc.gemoc_language_workbench.extensions.sirius.modelloader.DefaultModelLoader");
		helper.saveDocument(pluginfile);


	}
	
	protected void setPluginLanguageNameAndFilePath(IProject project, final String languageName) {
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		Element gemocExtensionPoint = helper.getOrCreateExtensionPoint(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionInExtensionPoint(gemocExtensionPoint, languageName);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(gemocExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT, project.getFullPath()
						.toString() + "/project.xdsml");
		helper.saveDocument(pluginfile);
	}
	
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// delete markers set and files created
		getProject().deleteMarkers(MARKER_TYPE, true, IResource.DEPTH_INFINITE);
	}

		private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new GemocSequentialLanguageResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new GemocSequentialLanguageProjectDeltaVisitor());
	}
}
