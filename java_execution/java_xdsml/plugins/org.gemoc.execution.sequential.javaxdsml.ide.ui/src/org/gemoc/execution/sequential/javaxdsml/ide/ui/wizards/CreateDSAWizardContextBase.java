package org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

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
import org.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlFactoryImpl;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;

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
		ManifestChanger manifestChanger = new ManifestChanger(gemocLanguageIProject);
		try {
			manifestChanger.addPluginDependency(projectName);
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addDSAProjectToConf(String projectName, SequentialLanguageDefinition gemocLanguageModel) {
		
		    
		    
			DSAProject DSAProject = Sequential_xdsmlFactoryImpl.eINSTANCE.createDSAProject();
			DSAProject.setProjectName(projectName);
			gemocLanguageModel.setDsaProject(DSAProject);
		
	}

}