package org.gemoc.executionengine.java.sequential_language_workbench.ui.wizards;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gemoc.executionengine.java.sequential_language_workbench.ui.Activator;
import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlFactoryImpl;

public class CreateDSAWizardContextBase {

	protected IProject _gemocLanguageIProject;
	protected SequentialLanguageDefinition _gemocLanguageModel = null;
	
	public CreateDSAWizardContextBase(IProject gemocLanguageIProject) {
		_gemocLanguageIProject = gemocLanguageIProject;
	}
	public CreateDSAWizardContextBase(IProject gemocLanguageIProject, SequentialLanguageDefinition rootModelElement) {
		_gemocLanguageIProject = gemocLanguageIProject;
		_gemocLanguageModel = rootModelElement;
	}

	protected void addDSAProjectToConf(String projectName) {
		if(_gemocLanguageModel != null){
			addDSAProjectToConf(projectName, _gemocLanguageModel);
		} else {
			addDSAProjectToConf(projectName, _gemocLanguageIProject);
		}
	}
	protected void addDSAProjectToConf(String projectName,IProject gemocLanguageIProject) {
		IFile configFile = gemocLanguageIProject.getFile(new Path(Activator.GEMOC_PROJECT_CONFIGURATION_FILE)); 
		if(configFile.exists())
		{			
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		    Map<String, Object> m = reg.getExtensionToFactoryMap();
		    m.put(Activator.GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION, new XMIResourceFactoryImpl());
	
		    // Obtain a new resource set
		    ResourceSet resSet = new ResourceSetImpl();
	
		    // get the resource
		    Resource resource = resSet.getResource(URI.createURI(configFile.getLocationURI().toString()),true);
		    
		    SequentialLanguageDefinition gemocLanguageWorkbenchConfiguration = (SequentialLanguageDefinition) resource.getContents().get(0);
		    
		    addDSAProjectToConf(projectName, gemocLanguageWorkbenchConfiguration);
		    
		    try {
				resource.save(null);
			} catch (IOException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		try {
			configFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
	}
	
	protected void addDSAProjectToConf(String projectName, SequentialLanguageDefinition gemocLanguageModel) {
		
		    
		    
			DSAProject DSAProject = Sequential_xdsmlFactoryImpl.eINSTANCE.createDSAProject();
			DSAProject.setProjectName(projectName);
			gemocLanguageModel.setDsaProject(DSAProject);
		
	}

}