package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import java.io.IOException
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.resources.WorkspaceJob
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EPackage

class TraceAddonGeneratorIntegrationHelper {

	/**
	 * Central operation of the class, that calls business operations
	 */
	public static def void generateAddon(String mmName, String pluginName, Set<EPackage> inputMetamodel,
		boolean replace, IProgressMonitor monitor, Ecorext executionExtension) throws CoreException {

		// We look for an existing project with this name
		val IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginName);
		if (existingProject.exists()) {

			// If we replace, we delete most of its content 
			// (we keep the original project in order to be able to replace the project even if it was imported in the workspace)
			if (replace) {
				// existingProject.delete(true, monitor);
				val WorkspaceJob job = new WorkspaceJob("deleting project " + existingProject.name + " content") {
					override public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
						for (IResource iRes : existingProject.members) {
							if (!(iRes.name.equals(".project") || iRes.name.equals(".classpath"))) {
								iRes.delete(true, monitor);
							}
						}
						return Status.OK_STATUS;
					}
				};
				job.setRule(existingProject);
				job.schedule();
			} // Else, error
			else {
				throw new CoreException(
					new Status(Status.ERROR, "fr.inria.diverse.trace.gemoc.ui",
						"Impossible to create a trace addon: a project already exists with this name."));
			}
		}

		try {

			// Then we call all our business operations
			// TODO handle languages defined with multiple ecores
			val EPackage extendedMetamodel = inputMetamodel.iterator().next();

			val GenericEngineTraceAddonGenerator traceaddgen = new GenericEngineTraceAddonGenerator(extendedMetamodel,
				executionExtension, pluginName);
			traceaddgen.generateCompleteAddon(monitor);
		} catch (IOException e) {

			// TODO Do real error handling
			e.printStackTrace();
		}
	}

}