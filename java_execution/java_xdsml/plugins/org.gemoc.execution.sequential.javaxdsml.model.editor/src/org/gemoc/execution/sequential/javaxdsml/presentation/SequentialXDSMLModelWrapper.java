/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.execution.sequential.javaxdsml.presentation;

import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.util.XDSMLSequentialModelHelper;
import org.gemoc.executionframework.xdsml_base.AnimatorProject;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.EditorProject;
import org.gemoc.executionframework.xdsml_base.SiriusEditorProject;
import org.gemoc.executionframework.xdsml_base.XTextEditorProject;

/**
 * Wrapped version of the XDSML model dedicated to the GemoXDSMLForm view
 * 
 *
 */
public class SequentialXDSMLModelWrapper extends ViewModelWrapper {

	public SequentialLanguageDefinition languageDefinition;

	public SequentialXDSMLModelWrapper() {

	}

	public String getLanguageName() {
		if (languageDefinition != null && languageDefinition.getName() != null) {
			return languageDefinition.getName();
		} else
			return "";
	}

	public void setLanguageName(String languageName) {
		firePropertyChange("languageName", getLanguageName(), languageName);
		languageDefinition.setName(languageName);
	}

	public String getDomainModelProjectName() {
		if (languageDefinition != null && languageDefinition.getDomainModelProject() != null
				&& languageDefinition.getDomainModelProject().getProjectName() != null) {
			return languageDefinition.getDomainModelProject().getProjectName();
		} else
			return "";
	}

	public void setDomainModelProjectName(String domainModelProjectName) {
		String oldName = getDomainModelProjectName();
		firePropertyChange("domainModelProjectName", oldName, domainModelProjectName);
		XDSMLSequentialModelHelper.getOrCreateDomainModelProject(languageDefinition).setProjectName(domainModelProjectName);
	}

	public void setGenmodelLocationURI(String genmodel) {
		String oldName = getGenmodelLocationURI();
		firePropertyChange("genmodelLocationURI", oldName, genmodel);
		XDSMLSequentialModelHelper.getOrCreateDomainModelProject(languageDefinition).setGenmodeluri(genmodel);
	}

	public String getGenmodelLocationURI() {
		if (languageDefinition != null) {
			if (languageDefinition.getDomainModelProject() != null
					&& languageDefinition.getDomainModelProject().getGenmodeluri() != null) {
				return languageDefinition.getDomainModelProject().getGenmodeluri();
			}
		}
		return "";
	}

	public void setRootContainerModelElement(String root) {
		String oldName = getRootContainerModelElement();
		firePropertyChange("rootContainerModelElement", oldName, root);
		(XDSMLSequentialModelHelper.getOrCreateDomainModelProject(languageDefinition)).setDefaultRootEObjectQualifiedName(root);
	}

	public String getRootContainerModelElement() {
		if (languageDefinition != null) {
			if (languageDefinition.getDomainModelProject() != null) {
				DomainModelProject ecoreProject = (DomainModelProject) languageDefinition.getDomainModelProject();
				return ecoreProject.getDefaultRootEObjectQualifiedName() != null ? ecoreProject
						.getDefaultRootEObjectQualifiedName() : "";
			}
		}
		return "";
	}

	public void setModelLoaderClass(String modelLoaderClass) {
		String oldName = getGenmodelLocationURI();
		firePropertyChange("modelLoaderClass", oldName, modelLoaderClass);
		XDSMLSequentialModelHelper.getOrCreateDomainModelProject(languageDefinition).setModelLoaderClass(modelLoaderClass);
	}

	public String getModelLoaderClass() {
		if (languageDefinition != null) {
			if (languageDefinition.getDomainModelProject() != null
					&& languageDefinition.getDomainModelProject().getModelLoaderClass() != null) {
				return languageDefinition.getDomainModelProject().getModelLoaderClass();
			}
		}
		return "";
	}

	public String getSupportedFileExtension() {
		StringBuilder sb = new StringBuilder();
		sb.append("Supported file extensions: ");
		int i = 0;
		if (languageDefinition != null) {
			for (String s : languageDefinition.getFileExtensions()) {
	
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(s);
				i++;
			}
		}
		return sb.toString();
	}

	public void setSupportedFileExtension(String supportedFileExtensions) {

	}

	public String getXTextEditorProjectName() {
		if (languageDefinition != null) {
			for (EditorProject editor : languageDefinition.getEditorProjects()) {
				if (editor instanceof XTextEditorProject && editor.getProjectName() != null) {
					return editor.getProjectName();
				}
			}
		}
		return "";
	}

	public void setXTextEditorProjectName(String name) {
		String oldName = getXTextEditorProjectName();
		firePropertyChange("xTextEditorProjectName", oldName, name);
		XDSMLSequentialModelHelper.getOrCreateXTextEditorProject(languageDefinition).setProjectName(name);

	}

	public String getSiriusEditorProjectName() {
		if (languageDefinition != null) {
			for (EditorProject editor : languageDefinition.getEditorProjects()) {
				if (editor instanceof SiriusEditorProject && editor.getProjectName() != null) {
					return editor.getProjectName();
				}
			}
		}
		return "";
	}

	public void setSiriusEditorProjectName(String name) {
		String oldName = getSiriusEditorProjectName();
		firePropertyChange("xSiriusEditorProjectName", oldName, name);
		XDSMLSequentialModelHelper.getOrCreateSiriusEditorProject(languageDefinition).setProjectName(name);
	}

	public String getSiriusAnimatorProjectName() {
		if (languageDefinition != null) {
			for (AnimatorProject editor : languageDefinition.getAnimatorProjects()) {
				return editor.getProjectName();
			}
		}
		return "";
	}

	public void setSiriusAnimatorProjectName(String name) {
		String oldName = getSiriusAnimatorProjectName();
		firePropertyChange("xSiriusAnimatorProjectName", oldName, name);
		XDSMLSequentialModelHelper.getOrCreateSiriusAnimatorProject(languageDefinition).setProjectName(name);
	}

	public String getDSAProjectName() {
		if (languageDefinition != null && languageDefinition.getDsaProject() != null
				&& languageDefinition.getDsaProject().getProjectName() != null) {
			return languageDefinition.getDsaProject().getProjectName();
		} else
			return "";
	}

	public void setDSAProjectName(String projectName) {
		String oldName = getDSAProjectName();
		firePropertyChange("dSAProjectName", oldName, projectName);
		XDSMLSequentialModelHelper.getOrCreateDSAProject(languageDefinition).setProjectName(projectName);
	}

	public String getCodeExecutorClass() {
		if (languageDefinition != null) {
			if (languageDefinition.getDsaProject() != null
					&& languageDefinition.getDsaProject().getCodeExecutorClass() != null) {
				return languageDefinition.getDsaProject().getCodeExecutorClass();
			}
		}
		return "";
	}

	public void setCodeExecutorClass(String codeExecutorClass) {
		String oldName = getCodeExecutorClass();
		firePropertyChange("codeExecutorClass", oldName, codeExecutorClass);
		XDSMLSequentialModelHelper.getOrCreateDSAProject(languageDefinition).setCodeExecutorClass(codeExecutorClass);
	}

	public String getDSAEntryPoint(){
		if (languageDefinition != null && languageDefinition.getDsaProject() != null
				&& languageDefinition.getDsaProject().getEntryPoint() != null) {
			return languageDefinition.getDsaProject().getEntryPoint();
		} else
			return "";
	}
	
	public void setDSAEntryPoint(String entryPoint){
		String oldName = getDSAProjectName();
		firePropertyChange("dSAEntryPoint", oldName, entryPoint);
		XDSMLSequentialModelHelper.getOrCreateDSAProject(languageDefinition).setEntryPoint(entryPoint);
	}
	
	

}
