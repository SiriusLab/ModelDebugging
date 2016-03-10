package fr.inria.diverse.trace.gemoc.traceaddon

import fr.inria.diverse.trace.gemoc.api.IGemocTraceManager
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.inria.diverse.trace.gemoc.api.ISimpleTimeLineNotifier
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.executionframework.engine.core.CommandExecution
import org.gemoc.executionframework.engine.mse.MSEOccurrence
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine
import org.gemoc.xdsmlframework.api.core.IExecutionContext
import org.gemoc.xdsmlframework.api.engine_addon.DefaultEngineAddon
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.BatchModelChangeListenerAddon

abstract class AbstractTraceAddon extends DefaultEngineAddon implements IMultiDimensionalTraceAddon {

	private IExecutionContext _executionContext;
	private ISimpleTimeLineNotifier provider
	private IGemocTraceManager traceManager
	private boolean shouldSave = true
	
	/**
	 * TEMPORARY: to test the new add state based on the StepBasedModelChangeListenerAddon!
	 */
    val private static boolean USE_NEW_ADDSTATE = false;

	BatchModelChangeListenerAddon listenerAddon

	abstract def IGemocTraceManager constructTraceManager(Resource exeModel, Resource traceResource)

	override getTraceManager() {
		return traceManager;
	}

	override goToNoTimelineNotification(int i) {
		modifyTrace([traceManager.goTo(i)])
	}

	override goTo(int i) {
		goToNoTimelineNotification(i)
		provider.notifyTimeLine();
	}

	override goTo(EObject state) {
		modifyTrace([traceManager.goTo(state)])
		provider.notifyTimeLine();
	}

	public def void disableTraceSaving() {
		shouldSave = false
	}

	/**
	 * Sort-of constructor for the trace manager.
	 */
	private def void setUp(IBasicExecutionEngine engine) {
		if (_executionContext == null) {
			_executionContext = engine.executionContext

			// Creating the resource of the trace
			val ResourceSet rs = _executionContext.getResourceModel().getResourceSet();
			val URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false);
			val Resource traceResource = rs.createResource(traceModelURI);

			// We construct a new listener addon if required
			if (fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon.USE_NEW_ADDSTATE) {
				this.listenerAddon = if (engine.hasAddon(BatchModelChangeListenerAddon))
					engine.getAddon(BatchModelChangeListenerAddon)
				else
					new BatchModelChangeListenerAddon(engine)
				listenerAddon.registerObserver(this)
			}

			// We construct the trace manager, using the concrete generated method
			traceManager = constructTraceManager(_executionContext.resourceModel, traceResource)
			
			// And we initialize the trace
			modifyTrace([traceManager.initTrace])
			
			// Link to the timeline
			if (provider == null)
				provider = new WrapperSimpleTimeLine(traceManager)
			this.provider.traceManager = traceManager

		}
	}

	override setTimeLineNotifier(ISimpleTimeLineNotifier notif) {
		this.provider = notif
	}

	override getTimeLineProvider() {
		return provider;
	}

	private static def String getFQN(EOperation o, EObject caller, String separator) {
		val EClass c = if(o.EContainingClass != null) o.EContainingClass else caller.eClass
		if (c != null) {
			return getFQN(c, separator) + separator + o.name.toFirstUpper
		} else {
			return o.name
		}
	}

	private static def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	private static def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name.toFirstUpper
		}
	}

	/**
	 * Called just before a modification is done.
	 * The first time it is called -> init state
	 * The last time 			   -> just before the last state, so last state captured with "engineStop" 
	 */
	override aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		val MSEOccurrence occurrence = mseOccurrence
		val mse = occurrence.mse

		// If null, it means it was a "fake" event just to stop the engine
		if (mse != null) {

			val String eventName = if(mse.action != null) getFQN(mse.action, mse.caller, ".") else "NOACTION"

			// TODO handle event params + return
			val Map<String, Object> params = new HashMap
			params.put("this", mse.caller)

			// We try to add a new state (the trace manager might create an implict step here).
			modifyTrace([
				if (fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon.USE_NEW_ADDSTATE)
					traceManager.addState(listenerAddon.getChanges(this))
				else
					traceManager.addStateIfChanged()
			])
	
			// And we add a starting step
			modifyTrace([
				val boolean ok = traceManager.addStep(mseOccurrence)
				if (!ok)
					traceManager.addStep(eventName, params)
			])

			provider.notifyTimeLine()
			if (shouldSave)
				traceManager.save();
		}
	}

	/**
	 * Called just after a method is finished.
	 */
	override mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		val mse = mseOccurrence.mse

		if (mse != null) {

			val String eventName = if (mse.action != null)
					getFQN(mse.caller.eClass, ".") + "." + mse.action.name
				else
					"NOACTION"

			modifyTrace([
				if (fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon.USE_NEW_ADDSTATE)
					traceManager.addState(listenerAddon.getChanges(this))
				else
					traceManager.addStateIfChanged()
			])

			// In all cases, we tell the trace manager that an event ended
			modifyTrace([traceManager.endStep(eventName, null);])

		}

		provider.notifyTimeLine()
	}

	/**
	 * To construct the trace manager
	 */
	override engineAboutToStart(IBasicExecutionEngine engine) {
		setUp(engine)
	}

	/**
	 * Wrapper using lambda to always use a RecordingCommand when modifying the trace
	 */
	private def void modifyTrace(Runnable r, String message) {
		try {
			val ed = TransactionUtil.getEditingDomain(_executionContext.getResourceModel());
			var RecordingCommand command = new RecordingCommand(ed, message) {
				protected override void doExecute() {
					r.run
				}
			};
			CommandExecution.execute(ed, command);
		} catch (Exception e) {
			throw e
		}
	}

	/**
	 * Same as above, but without message. 
	 */
	private def void modifyTrace(Runnable r) {
		modifyTrace(r, "")
	}

	public override List<String> validate(List<IEngineAddon> otherAddons) {

		val ArrayList<String> errors = new ArrayList<String>();

		var boolean found = false;
		var addonName = "";
		for (IEngineAddon iEngineAddon : otherAddons) {
			if (iEngineAddon instanceof AbstractTraceAddon && iEngineAddon !== this) {
				found = true;
				addonName = EngineAddonSpecificationExtensionPoint.getName(iEngineAddon);
			}
		}

		if (found) {
			val thisName = EngineAddonSpecificationExtensionPoint.getName(this);
			errors.add(thisName + " can't run with " + addonName);
		}

		return errors;
	}

}
