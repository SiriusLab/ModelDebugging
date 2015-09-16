package org.gemoc.executionframework.xdsml_base.util;

import org.gemoc.executionframework.xdsml_base.AnimatorProject;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.EditorProject;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject;
import org.gemoc.executionframework.xdsml_base.SiriusEditorProject;
import org.gemoc.executionframework.xdsml_base.XTextEditorProject;
import org.gemoc.executionframework.xdsml_base.Xdsml_baseFactory;

public class XDSMLBaseModelHelper {
	public static DomainModelProject getOrCreateDomainModelProject(
			LanguageDefinition languageDefinition) {
		if (languageDefinition.getDomainModelProject() == null) {
			DomainModelProject domainModelProject = Xdsml_baseFactory.eINSTANCE
					.createDomainModelProject();
			languageDefinition.setDomainModelProject(domainModelProject);
		}
		return languageDefinition.getDomainModelProject();
	}

	public static EditorProject getOrCreateXTextEditorProject(
			LanguageDefinition languageDefinition) {

		EditorProject xTextEditor = null;
		for (EditorProject editor : languageDefinition.getEditorProjects()) {
			if (editor instanceof XTextEditorProject) {
				xTextEditor = editor;
				break;
			}
		}

		if (xTextEditor == null) {
			xTextEditor = Xdsml_baseFactory.eINSTANCE.createXTextEditorProject();
			languageDefinition.getEditorProjects().add(xTextEditor);
		}
		return xTextEditor;
	}

	public static EditorProject getOrCreateSiriusEditorProject(
			LanguageDefinition languageDefinition) {

		EditorProject siriusEditor = null;
		for (EditorProject editor : languageDefinition.getEditorProjects()) {
			if (editor instanceof SiriusEditorProject) {
				siriusEditor = editor;
				break;
			}
		}

		if (siriusEditor == null) {
			siriusEditor = Xdsml_baseFactory.eINSTANCE.createSiriusEditorProject();
			languageDefinition.getEditorProjects().add(siriusEditor);
		}
		return siriusEditor;
	}

	public static AnimatorProject getOrCreateSiriusAnimatorProject(
			LanguageDefinition languageDefinition) {
		AnimatorProject siriusEditor = null;
		for (AnimatorProject editor : languageDefinition.getAnimatorProjects()) {
			if (editor instanceof SiriusAnimatorProject) {
				siriusEditor = editor;
				break;
			}
		}

		if (siriusEditor == null) {
			siriusEditor = Xdsml_baseFactory.eINSTANCE.createSiriusAnimatorProject();
			languageDefinition.getAnimatorProjects().add(siriusEditor);
		}
		return siriusEditor;
	}

	/*public static FileResource getOrCreateEmfGenmodel(
			LanguageDefinition languageDefinition) {
		DomainModelProject ecoreProject = (DomainModelProject) getOrCreateDomainModelProject(languageDefinition);
		if (ecoreProject.getEmfGenmodel() == null) {
			ecoreProject.setEmfGenmodel(Xdsml_baseFactory.eINSTANCE
					.createEMFGenmodel());
		}
		return ecoreProject.getEmfGenmodel();
	}*/

	
}
