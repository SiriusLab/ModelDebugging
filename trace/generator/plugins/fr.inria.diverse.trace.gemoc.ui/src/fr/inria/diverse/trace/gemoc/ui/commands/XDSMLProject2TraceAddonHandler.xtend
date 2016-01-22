package fr.inria.diverse.trace.gemoc.ui.commands

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.trace.gemoc.generator.GenericEngineTraceAddonGeneratorHelper
import java.io.PrintWriter
import java.io.StringWriter
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.wizard.WizardDialog
import org.gemoc.xdsmlframework.ide.ui.commands.AbstractGemocLanguageProjectHandler
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectAnyMelangeLanguageDialog


/**
 * Handler that allows to get an XDSML project (containing a melange file) 
 * and will ask for selection of the language (if necessary) and launch the generation
 */
class XDSMLProject2TraceAddonHandler extends AbstractGemocLanguageProjectHandler {

	val static String pluginId = "fr.inria.diverse.trace.gemoc.ui"

	override execute(ExecutionEvent event) throws ExecutionException {
		
		val IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);
		val IFile melangeFile = getMelangeFileFromSelection(event);
		
		if(melangeFile != null){
			val URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
			val ResourceSet rs = new ResourceSetImpl();
			rs.getResource(uri, true);
			
			val LabelProvider labelProvider = new LabelProvider(){								
				override public String getText(Object element) {
					if(element instanceof Language){
						element.name
					} else	return super.getText(element);
				}
			};
			val SelectAnyMelangeLanguageDialog dialog = new SelectAnyMelangeLanguageDialog(rs, labelProvider);
			val res = dialog.open();
			if (res == WizardDialog.OK) {
				// We prepare a nice job to do that out of the UI thread
				val Job j = new Job("Generating trace addon plugin for " + melangeFile.toString) {
					override protected run(IProgressMonitor monitor) {
						try {
							
							val lang = dialog.getFirstResult() as Language;
							GenericEngineTraceAddonGeneratorHelper.generateAddon(melangeFile, lang.getName(), true, monitor)
							
						} catch (Exception e) {
							val StringWriter sw = new StringWriter();
							e.printStackTrace(new PrintWriter(sw));
							val String exceptionAsString = sw.toString();
							return new Status(Status.ERROR, pluginId, exceptionAsString)
						}
						return new Status(Status.OK, pluginId, "Trace addon plugin generated.")
					}
				}
				// And we start the job
				j.schedule
				
				}
		}

		return null;
	}

}
