package fr.inria.diverse.trace.gemoc.traceaddon

import fr.inria.diverse.trace.api.ITraceManager
import fr.inria.diverse.trace.gemoc.timeline.WrapperSimpleTimeLine
import java.util.Collection
import java.util.HashMap
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.execution.engine.core.CommandExecution
import org.gemoc.execution.engine.io.views.timeline.TimeLineProviderProvider
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence
import org.gemoc.gemoc_language_workbench.api.core.IExecutionContext
import org.gemoc.gemoc_language_workbench.api.core.IExecutionEngine
import org.gemoc.gemoc_language_workbench.api.engine_addon.DefaultEngineAddon
import fr.inria.diverse.trace.plaink3.tracematerialextractor.Plaink3MaterialStrings
import org.gemoc.execution.engine.io.views.timeline.ITraceAddon
import org.eclipse.emf.ecore.EObject

abstract class AbstractTraceAddon extends DefaultEngineAddon implements ITraceAddon {

	private IExecutionContext _executionContext;
	private WrapperSimpleTimeLine provider
	private ITraceManager traceManager

	abstract def ITraceManager constructTraceManager(Resource exeModel, Resource traceResource)

	public def ITraceManager getTraceManager() {
		return traceManager
	}

	override goTo(EObject state) {
		new Thread(
			new Runnable() {
				override run() {

					val ed = TransactionUtil.getEditingDomain(_executionContext.getResourceModel());
					var RecordingCommand command = new RecordingCommand(ed, "GoTo Operation") {
						protected override void doExecute() {
							traceManager.goTo(state)
						}
					};
					CommandExecution.execute(ed, command);

				}
			}).start();

		provider.notifyTimeLine();
	}

	/**
	 * Sort-of constructor for the trace manager.
	 */
	private def void setUp(IExecutionEngine engine) {
		if (_executionContext == null) {
			_executionContext = engine.executionContext

			// Creating the resource of the trace
			val ResourceSet rs = _executionContext.getResourceModel().getResourceSet();
			val URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false);
			val Resource traceResource = rs.createResource(traceModelURI);

			traceManager = constructTraceManager(_executionContext.resourceModel, traceResource)
			modifyTrace([traceManager.initTrace])

			// Telling (indirectly) the TimeLine how to work with us
			if (engine.hasAddon(TimeLineProviderProvider)) {
				val providerprovider = engine.getAddon(TimeLineProviderProvider)
				provider = new WrapperSimpleTimeLine(traceManager)
				providerprovider.timeLineProvider = provider
			}

		}
	}

	private def String getFQN(EOperation o, String separator) {
		val EClass c = o.EContainingClass
		if (c != null) {
			return getFQN(c, separator) + separator + o.name.toFirstUpper
		} else {
			return c.name
		}
	}

	private def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	private def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name.toFirstUpper
		}
	}

	private def void addStateAndFillEventIfChanged(String eventName) {
		val stateChanged = traceManager.addStateIfChanged();
		if (stateChanged) {
			if (traceManager.currentMacro != null) {
				traceManager.retroAddEvent(traceManager.currentMacro + Plaink3MaterialStrings.fillEventSuffix,
					new HashMap)
			} else {
				traceManager.retroAddEvent(Plaink3MaterialStrings.globalFillEventName, new HashMap)

			}

		}
	}

	/**
	 * Called just before a modification is done.
	 * The first time it is called -> init state
	 * The last time 			   -> just before the last state, so last state captured with "engineStop" 
	 */
	override aboutToSelectLogicalStep(IExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		val occurrence = logicalSteps.get(0).mseOccurrences.get(0)
		val mse = occurrence.mse

		// If null, it means it was a "fake" event just to stop the engine
		if (mse != null) {

			val String eventName = getFQN(mse.action, "_")

			// TODO handle event params + return
			val Map<String, Object> params = new HashMap
			params.put("this", mse.caller)

			// We try to add a new state. If there was a change, then we put a fill event on the previous state.
			modifyTrace(
				[
					addStateAndFillEventIfChanged(eventName)
				])

			// In all cases, we register the event (which will be handled as micro/macro in the TM) 
			modifyTrace([traceManager.addEvent(eventName, params);])

			provider.notifyTimeLine()
			traceManager.save();

		}
	}

	/**
	 * Called just after a method is finished.
	 */
	override mseOccurrenceExecuted(IExecutionEngine engine, MSEOccurrence mseOccurrence) {
		val mse = mseOccurrence.mse

		if (mse != null) {

			val String eventName = getFQN(mse.action, "_")
			val boolean isMacro = traceManager.isMacro(eventName);

			// If micro event, we always create a new state at the end (to be able to store the next one)
			if (!isMacro) {
				modifyTrace([traceManager.addState();])
			}
			// If macro event, we only try to add a new state. If there was a change, then we put a fill event on the previous state.
			else {

				// For some reason, here, the "modifyTrace" operation doesn't always work
				// Therefore the content of the operation is inlined here
				val ed = TransactionUtil.getEditingDomain(_executionContext.getResourceModel());
				var RecordingCommand command = new RecordingCommand(ed, "") {
					protected override void doExecute() {
						addStateAndFillEventIfChanged(eventName)
					}
				};
				CommandExecution.execute(ed, command);
			}

			// In all cases, we tell the trace manager that an event ended
			modifyTrace([traceManager.endEvent(eventName, null);])

		}

		provider.notifyTimeLine()
	}

	/**
	 * To catch the last state
	 */
	override engineStopped(IExecutionEngine engine) {

		// TODO is this good? maybe we don't have conformity at this instant
		modifyTrace([traceManager.addState()])
		provider.notifyTimeLine()
		traceManager.save();
	}

	/**
	 * To construct the trace manager
	 */
	override engineAboutToStart(IExecutionEngine engine) {
		setUp(engine)
	}

	/**
	 * Wrapper using lambda to always use a RecordingCommand when modifying the trace
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
