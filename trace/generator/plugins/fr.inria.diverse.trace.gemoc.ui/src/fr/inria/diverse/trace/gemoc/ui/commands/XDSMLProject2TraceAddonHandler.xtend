package fr.inria.diverse.trace.gemoc.ui.commands

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.trace.gemoc.generator.TraceAddonGeneratorIntegration
import java.io.PrintWriter
import java.io.StringWriter
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.commands.IHandler
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.jface.dialogs.InputDialog
import org.eclipse.jface.window.Window
import org.eclipse.swt.widgets.Shell
import org.gemoc.commons.eclipse.emf.EMFResource
import org.gemoc.xdsmlframework.ide.ui.commands.AbstractMelangeSelectHandler
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper

/**
 * Handler that allows to get an XDSML project (containing a melange file) 
 * and will ask for selection of the language (if necessary) and launch the generation
 */
class XDSMLProject2TraceAddonHandler extends AbstractMelangeSelectHandler implements IHandler {
	val static String pluginId = "fr.inria.diverse.trace.gemoc.ui"

	override Object executeForSelectedLanguage(ExecutionEvent event, IProject updatedGemocLanguageProject,
		Language language) throws ExecutionException {

		val IFile melangeFile = getMelangeIFile(event, language)
		val baseProjectName = MelangeXDSMLProjectHelper.baseProjectName(melangeFile.project)

		// If the base project name doesn't end with the language name, we suggest it		
		val suggestedBasePluginName = if (baseProjectName.endsWith(language.name.toLowerCase))
				baseProjectName
			else
				baseProjectName + "." + language.name.toLowerCase
		val suggestedPluginName = suggestedBasePluginName + ".trace"

		val InputDialog inputDialog = new InputDialog(null,
			"Create MultiDimensional Trace addon for language " + language.getName(), "Enter project name ",
			suggestedPluginName, null)
		inputDialog.open();
		if (inputDialog.getReturnCode() == Window.OK) {
			val String projectName = inputDialog.getValue();
			val Job j = new Job("Generating trace addon plugin for " + melangeFile.toString) {
				override protected run(IProgressMonitor monitor) {
					try {

						TraceAddonGeneratorIntegration.generateAddon(melangeFile, language.name, projectName, true,
							monitor)

					} catch (Exception e) {
						val StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						val String exceptionAsString = sw.toString();
						return new Status(Status.ERROR, pluginId, exceptionAsString)
					}
					return new Status(Status.OK, pluginId, "Multidimensional Trace addon plugin generated.")
				}
			}
			// And we start the job
			j.schedule
		}

		return null;
	}

	/** Search for the IFile of the melange language either via the event or directly from the eResource */
	protected def IFile getMelangeIFile(ExecutionEvent event, Language language) {
		var IFile melangeFile = getMelangeFileFromSelection(event)
		if (melangeFile == null) {
			// this means that we have to retrieve the IFile from the language instance (either because 
			// it comes from an editor of because we have selected one language among other in the project)
			melangeFile = EMFResource.getIFile(language)
		}
		return melangeFile
	}

	override String getSelectionMessage() {
		return "Select Language that is used to initialize the Multidimensional Trace addon creation wizard";
	}

}
