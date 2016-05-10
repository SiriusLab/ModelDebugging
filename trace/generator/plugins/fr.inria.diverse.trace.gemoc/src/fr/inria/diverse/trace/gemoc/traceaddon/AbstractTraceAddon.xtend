package fr.inria.diverse.trace.gemoc.traceaddon

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer
import fr.inria.diverse.trace.gemoc.api.ITraceListener
import fr.inria.diverse.trace.gemoc.api.ITraceNotifier
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.executionframework.engine.core.CommandExecution
import org.gemoc.executionframework.engine.mse.Step
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine
import org.gemoc.xdsmlframework.api.core.IExecutionContext
import org.gemoc.xdsmlframework.api.engine_addon.DefaultEngineAddon
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.BatchModelChangeListenerAddon
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint

abstract class AbstractTraceAddon extends DefaultEngineAddon implements IMultiDimensionalTraceAddon, ITraceNotifier {

	private IExecutionContext _executionContext
	private ITraceExplorer traceExplorer
	private ITraceConstructor traceConstructor
	private boolean shouldSave = true

	private BatchModelChangeListenerAddon listenerAddon
	
	abstract def ITraceConstructor constructTraceConstructor(Resource exeModel, Resource traceResource, Map<EObject, EObject> exeToTraced)

	abstract def boolean isAddonForTrace(EObject traceRoot)

	abstract def ITraceExplorer loadTrace(Resource traceResource)

	abstract def ITraceExplorer loadTrace(Resource modelResource, Resource traceResource, Map<EObject, EObject> tracedToExe)

	override getTraceExplorer() {
		return traceExplorer
	}

	override getTraceConstructor() {
		return traceConstructor
	}

	private val List<ITraceListener> listeners = new ArrayList

	override void notifyListeners() { listeners.forEach[l|l.update] }

	override void addListener(ITraceListener listener) { listeners.add(listener) }

	override void removeListener(ITraceListener listener) { listeners.remove(listener) }

	public def void disableTraceSaving() {
		shouldSave = false
	}

	/**
	 * Sort-of constructor for the trace manager.
	 */
	private def void setUp(IBasicExecutionEngine engine) {
		if (_executionContext == null) {
			_executionContext = engine.executionContext
			
			val modelResource = _executionContext.resourceModel

			// Creating the resource of the trace
			val ResourceSet rs = modelResource.getResourceSet()
			val URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false)
			val Resource traceResource = rs.createResource(traceModelURI)

			// We construct a new listener addon if required
			this.listenerAddon = if (engine.hasAddon(BatchModelChangeListenerAddon))
				engine.getAddon(BatchModelChangeListenerAddon)
			else
				new BatchModelChangeListenerAddon(engine)
			listenerAddon.registerObserver(this)

			val BiMap<EObject, EObject> exeToTraced = HashBiMap.create

			// We construct the trace constructor, using the concrete generated method
			traceConstructor = constructTraceConstructor(modelResource, traceResource, exeToTraced)
			
			// We initialize the trace
			modifyTrace([traceConstructor.initTrace])
			
			// And we enable trace exploration by loading it in a new trace explorer
			traceExplorer = loadTrace(modelResource,traceResource, exeToTraced.inverse)
		}
	}

	public def void load(Resource traceModel) {
		traceExplorer = loadTrace(traceModel)
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
	override aboutToExecuteStep(IBasicExecutionEngine executionEngine, Step step) {
		// If null, it means it was a "fake" event just to stop the engine
		if (step != null) {
			modifyTrace([
				traceConstructor.addState(listenerAddon.getChanges(this))
				traceConstructor.addStep(step)
				traceExplorer.updateCallStack(step)
			])
			
			if (shouldSave) {
				traceConstructor.save()
			}
		}
	}

	/**
	 * Called just after a method is finished.
	 */
	override stepExecuted(IBasicExecutionEngine engine, Step step) {
		if (step != null) {
			modifyTrace([
				traceConstructor.addState(listenerAddon.getChanges(this))
				traceConstructor.endStep(step)
				traceExplorer.updateCallStack(step)
			])
			
			if (shouldSave) {
				traceConstructor.save()
			}
		}
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
			val ed = TransactionUtil.getEditingDomain(_executionContext.resourceModel)
			var RecordingCommand command = new RecordingCommand(ed, message) {
				protected override void doExecute() {
					r.run
				}
			}
			CommandExecution.execute(ed, command)
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

		val ArrayList<String> errors = new ArrayList<String>()

		var boolean found = false
		var addonName = ""
		for (IEngineAddon iEngineAddon : otherAddons) {
			if (iEngineAddon instanceof AbstractTraceAddon && iEngineAddon !== this) {
				found = true
				addonName = EngineAddonSpecificationExtensionPoint.getName(iEngineAddon)
			}
		}

		if (found) {
			val thisName = EngineAddonSpecificationExtensionPoint.getName(this)
			errors.add(thisName + " can't run with " + addonName)
		}

		return errors
	}

}
