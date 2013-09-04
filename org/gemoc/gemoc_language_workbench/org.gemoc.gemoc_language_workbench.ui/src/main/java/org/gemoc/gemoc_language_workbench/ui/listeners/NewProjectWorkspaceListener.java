package org.gemoc.gemoc_language_workbench.ui.listeners;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.gemoc.gemoc_language_workbench.ui.Activator;

/**
 * This listener to be registered on the workspace, will look for newly created IProject
 * @author dvojtise
 *
 */
public class NewProjectWorkspaceListener implements IResourceChangeListener {

	public ArrayList<IProject> newlyCreatedProjects = new ArrayList<IProject>();
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		final IResourceDelta delta = event.getDelta();
		if (delta != null) {
			try {
				delta.accept(new NewProjectDetector());
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
	}

	public IProject getLastCreatedProject(){
		if(newlyCreatedProjects.size() == 0) return null;
		else return newlyCreatedProjects.get(newlyCreatedProjects.size()-1);
	}
	// inner class visitor
	class NewProjectDetector implements IResourceDeltaVisitor{

		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			boolean processResourceChildren = true;
			
			if(delta.getResource().getType() == IResource.PROJECT){				
				if(delta.getKind() == IResourceDelta.ADDED){
					newlyCreatedProjects.add((IProject) delta.getResource());
				}
				processResourceChildren = false; // no need to analyze project content
			}
			
			
			return processResourceChildren;
		}
		
	}
}
