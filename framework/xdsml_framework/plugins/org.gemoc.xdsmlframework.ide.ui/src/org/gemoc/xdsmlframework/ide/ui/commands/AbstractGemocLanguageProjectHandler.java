package org.gemoc.xdsmlframework.ide.ui.commands;


import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.inria.diverse.melange.metamodel.melange.Language;

public abstract class AbstractGemocLanguageProjectHandler extends AbstractHandler {

	
	protected IProject getUpdatedGemocLanguageProjectFromSelection(ExecutionEvent event) {
		IProject updatedGemocLanguageProject = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
				Iterator<Object> iterator = strucSelection.iterator(); 
				iterator.hasNext();) {
				
				Object element = iterator.next();

				if (element instanceof IResource) {
					updatedGemocLanguageProject = ((IResource) element)
							.getProject();

				}
				if (element instanceof IAdaptable) {
					IResource res = (IResource) ((IAdaptable) element)
							.getAdapter(IResource.class);
					if (res != null) {
						updatedGemocLanguageProject = res.getProject();
					}
				}

				// if still null look after the activeEditor
				if (updatedGemocLanguageProject == null) {

					IResource res = (IResource) HandlerUtil
							.getActiveWorkbenchWindow(event).getActivePage()
							.getActiveEditor().getEditorInput()
							.getAdapter(IResource.class);
					if (res != null) {
						updatedGemocLanguageProject = res.getProject();
					}
				}

				/*
				 * MessageDialog.openInformation(
				 * HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				 * "Gemoc Language Workbench UI",
				 * "Create Domain Model Project command was executed. Selected elment ="
				 * +element.toString());
				 */
			}
		}
		return updatedGemocLanguageProject;
	}

	protected IFile getMelangeFileFromSelection(ExecutionEvent event){
		IFile selectedMelangeIFile = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
				Iterator<Object> iterator = strucSelection.iterator(); 
				iterator.hasNext();) {
				
				Object element = iterator.next();

				if (element instanceof IFile) {
					selectedMelangeIFile = (IFile) element;

				}
				if (element instanceof IAdaptable) {
					IFile res = (IFile) ((IAdaptable) element)
							.getAdapter(IFile.class);
					if (res != null) {
						selectedMelangeIFile = res;
					}
				}
			}
		}
		return selectedMelangeIFile;
	}
	
	protected Language getMelangeLanguageFromSelection(ExecutionEvent event){
		Language selectedMelangeLanguage = null;
		return selectedMelangeLanguage;
	}
	
	protected Language getOrAskMelangeLanguage(ExecutionEvent event){
		Language selectedMelangeLanguage = getMelangeLanguageFromSelection(event);
		if(selectedMelangeLanguage == null){
			// TODO open a popup to ask for it to the available one
		}
		return selectedMelangeLanguage;
	}
	
}
