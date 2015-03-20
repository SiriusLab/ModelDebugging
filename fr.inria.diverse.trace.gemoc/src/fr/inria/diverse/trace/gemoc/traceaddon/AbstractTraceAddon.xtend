package fr.inria.diverse.trace.gemoc.traceaddon

import fr.inria.diverse.trace.api.ITraceManager
import fr.inria.diverse.trace.gemoc.timeline.WrapperSimpleTimeLine
import java.util.Collection
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.execution.engine.core.CommandExecution
import org.gemoc.execution.engine.io.views.timeline.TimeLineProviderProvider
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep
import org.gemoc.gemoc_language_workbench.api.core.IExecutionContext
import org.gemoc.gemoc_language_workbench.api.core.IExecutionEngine
import org.gemoc.gemoc_language_workbench.api.engine_addon.DefaultEngineAddon

abstract class AbstractTraceAddon extends DefaultEngineAddon {

	private IExecutionContext _executionContext;
	private WrapperSimpleTimeLine provider
	private ITraceManager traceManager


	abstract def ITraceManager constructTraceManager (Resource exeModel, Resource traceResource)

	
	public def ITraceManager getTraceManager() {
		return traceManager
	}

	/**
	 * Sort-of constructor for the trace manager.
	 * Independent from how the trace metamodel is made.
	 */
	private def void setUp(IExecutionEngine engine) {
		if (_executionContext == null) {
			_executionContext = engine.executionContext

			// Creating the resource of the trace
			val ResourceSet rs = _executionContext.getResourceModel().getResourceSet();
			val URI traceModelURI = URI.createPlatformResourceURI(_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false);
			val Resource traceResource = rs.createResource(traceModelURI);
			
			traceManager = constructTraceManager(_executionContext.resourceModel,traceResource)
			modifyTrace([traceManager.initTrace])

			// Telling (indirectly) the TimeLine how to work with us
			if (engine.hasAddon(TimeLineProviderProvider)) {
				val providerprovider = engine.getAddon(TimeLineProviderProvider)
				provider = new WrapperSimpleTimeLine(traceManager, engine)
				providerprovider.timeLineProvider = provider
			}

		}
	}

	/**
	 * Called just before a modification is done.
	 * The first time it is called -> init state
	 * The last time 			   -> just before the last state, so last state captured with "engineStop" 
	 * Independent from how the trace metamodel is made.
	 */
	override aboutToSelectLogicalStep(IExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		modifyTrace([traceManager.addState();])
		modifyTrace([traceManager.addEvent();])
		provider.notifyTimeLine()
		traceManager.save();
	}

	/**
	 * To catch the last state
	 * Independent from how the trace metamodel is made.
	 */
	override engineStopped(IExecutionEngine engine) {
		modifyTrace([traceManager.addState()])
		provider.notifyTimeLine()
	}

	/**
	 * To construct the trace manager
	 * Independent from how the trace metamodel is made.
	 */
	override engineAboutToStart(IExecutionEngine engine) {
		setUp(engine)
	}

	/**
	 * Wrapper using lambda to always use a RecordingCommand when modifying the trace
	 * Independent from how the trace metamodel is made.
	 */
	private def void modifyTrace(Runnable r, String message) {
		val ed = TransactionUtil.getEditingDomain(_executionContext.getResourceModel());
		var RecordingCommand command = new RecordingCommand(ed, message) {
			protected override void doExecute() {
				r.run
			}
		};
		CommandExecution.execute(ed, command);
	}

	/**
	 * Same as above, but without message. 
	 */
	private def void modifyTrace(Runnable r) {
		modifyTrace(r, "")
	}

	

}
