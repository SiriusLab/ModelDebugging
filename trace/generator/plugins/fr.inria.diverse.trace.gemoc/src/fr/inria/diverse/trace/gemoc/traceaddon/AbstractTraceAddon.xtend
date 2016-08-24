package fr.inria.diverse.trace.gemoc.traceaddon

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import fr.inria.diverse.trace.commons.model.trace.Step
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor
import fr.inria.diverse.trace.gemoc.api.ITraceListener
import fr.inria.diverse.trace.gemoc.api.ITraceNotifier
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.executionframework.engine.core.CommandExecution
import org.gemoc.xdsmlframework.api.core.IExecutionContext
import org.gemoc.xdsmlframework.api.engine_addon.DefaultEngineAddon
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.BatchModelChangeListenerAddon
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.gemoc.xdsmlframework.api.core.IExecutionEngine

abstract class AbstractTraceAddon extends DefaultEngineAddon implements IMultiDimensionalTraceAddon, ITraceNotifier {

	private IExecutionContext _executionContext
	private ITraceExplorer traceExplorer
	private ITraceExtractor traceExtractor
	private ITraceConstructor traceConstructor
	private boolean shouldSave = true
	private var boolean needTransaction = true

	private BatchModelChangeListenerAddon listenerAddon

	abstract def ITraceConstructor constructTraceConstructor(Resource modelResource, Resource traceResource,
		Map<EObject, EObject> exeToTraced)

	abstract def boolean isAddonForTrace(EObject traceRoot)

	abstract def ITraceExtractor constructTraceExtractor(Resource traceResource)

	abstract def ITraceExplorer constructTraceExplorer(Resource traceResource)

	abstract def ITraceExplorer constructTraceExplorer(Resource modelResource, Resource traceResource, Map<EObject, EObject> tracedToExe)

	override getTraceExplorer() {
		return traceExplorer
	}

	override getTraceConstructor() {
		return traceConstructor
	}

	override getTraceExtractor() {
		return traceExtractor
	}

	private val List<ITraceListener> listeners = new ArrayList

	override void notifyListeners() { listeners.forEach[l|l.update] }

	override void addListener(ITraceListener listener) { listeners.add(listener) }

	override void removeListener(ITraceListener listener) { listeners.remove(listener) }

	public def void disableTraceSaving() {
		shouldSave = false
	}

	public def void load(Resource traceModel) {
		traceExplorer = constructTraceExplorer(traceModel)
		traceExtractor = constructTraceExtractor(traceModel)
	}

	private static def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name.toFirstUpper
		}
	}

	override aboutToExecuteStep(IExecutionEngine executionEngine, Step step) {
		manageStep(step, true)
	}

	override stepExecuted(IExecutionEngine engine, Step step) {
		manageStep(step, false)
	}

	protected def manageStep(Step step, boolean add) {
		if (step != null) {
			modifyTrace([
				traceConstructor.addState(listenerAddon.getChanges(this))
				if (add)
					traceConstructor.addStep(step)
				else
					traceConstructor.endStep(step)
				traceExplorer.updateCallStack(step)
				traceExtractor.update()
			])

			if (shouldSave) {
//				traceConstructor.save()
			}
		}
	}

	/**
	 * To construct the trace manager
	 */
	override engineAboutToStart(IExecutionEngine engine) {
		if (_executionContext == null) {
			_executionContext = engine.executionContext

			val modelResource = _executionContext.resourceModel

			// Creating the resource of the trace
			// val ResourceSet rs = modelResource.getResourceSet()
			val ResourceSet rs = new ResourceSetImpl

			// We check whether or not we need transactions
			val ed = TransactionUtil.getEditingDomain(rs)
			needTransaction = ed != null

			val URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false)
			val Resource traceResource = rs.createResource(traceModelURI)

			// We construct a new listener addon if required
			this.listenerAddon = if (engine.hasAddon(BatchModelChangeListenerAddon)) {
				engine.getAddon(BatchModelChangeListenerAddon)
			} else {
				new BatchModelChangeListenerAddon(engine)
			}
			listenerAddon.registerObserver(this)

			val launchConfiguration = engine.extractLaunchConfiguration

			val BiMap<EObject, EObject> exeToTraced = HashBiMap.create

			// We construct the trace constructor, using the concrete generated method
			traceConstructor = constructTraceConstructor(modelResource, traceResource, exeToTraced)

			// We initialize the trace
			modifyTrace([traceConstructor.initTrace(launchConfiguration)])

			// And we enable trace exploration by loading it in a new trace explorer
			traceExplorer = constructTraceExplorer(modelResource, traceResource, exeToTraced.inverse)
			traceExtractor = constructTraceExtractor(traceResource)
		}
	}

	/**
	 * Wrapper using lambda to always use a RecordingCommand when modifying the trace
	 */
	private def void modifyTrace(Runnable r, String message) {
		if (needTransaction) {
			val ed = TransactionUtil.getEditingDomain(_executionContext.resourceModel)
			val Set<Throwable> catchedException = new HashSet
			var RecordingCommand command = new RecordingCommand(ed, message) {
				protected override void doExecute() {
					try {
						r.run
					} catch (Throwable t) {
						catchedException.add(t)
					}
				}
			}
			CommandExecution.execute(ed, command)
			if (!catchedException.empty)
				throw catchedException.head
		} else {
			r.run
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
