package fr.inria.diverse.tracemm.common

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import fUML.Semantics.Classes.Kernel.Object_
import java.util.Collection

public abstract class SpecificStatesBuilderConfiguration {

	protected ConfigurationObjectMap configurationObjectMap;

	protected EObject traceSystem;

	protected EPackage tracePackage;
	protected EFactory traceFactory;
	protected EClass traceSystemClass;
	protected EClass globalStateClass;

	protected EPackage originalPackage;
	protected EFactory originalFactory;

	protected EPackage confPackage

	protected ConfigurableStatesBuilder statesBuilder;
	protected var boolean initDone = false;

	public def void init(ConfigurableStatesBuilder s) {
		if (!initDone) {
			this.statesBuilder = s;
			initDone = true;
		}
	}

	new(Resource traceMMResource, Resource originalMMResource, Resource confMMResource,
		ConfigurationObjectMap configurationObjectMap) {
		this.tracePackage = traceMMResource.getContents.get(0) as EPackage
		this.originalPackage = originalMMResource.getContents.get(0) as EPackage
		this.confPackage = confMMResource.getContents.get(0) as EPackage
		this.traceSystemClass = tracePackage.getEClassifier("TraceSystem") as EClass
		this.globalStateClass = tracePackage.getEClassifier("GlobalState") as EClass
		this.originalFactory = originalPackage.EFactoryInstance
		this.traceFactory = tracePackage.EFactoryInstance
		this.configurationObjectMap = configurationObjectMap
	}

	/**
	 * Adding to EObjects the possibility to get a field per name
	 */
	protected def Object get(EObject o, String featureName) {
		return o.eGet(o.eClass.getEStructuralFeature(featureName))
	}

	/**
	 * Adding to EObjects the possibility to set a field per name
	 */
	protected def set(EObject o, String featureName, Object value) {
		o.eSet(o.eClass.getEStructuralFeature(featureName), value)
	}

	/**
	 * Creates a new EObject using the trace mm factory
	 */
	protected def EObject newTraceObject(String eClassName) {
		return traceFactory.create(tracePackage.getEClassifier(eClassName) as EClass)
	}

	protected def EObject confToOriginal(EObject confObject) {
		val res = configurationObjectMap.getOriginalObject(confObject)
		if (res == null || findRootPackage(res.eClass.EPackage) != originalPackage)
			return null
		else
			return res
	}

	protected def EClass confClassToOrigClass(EClass confClass) {
		if (findRootPackage(confClass.EPackage) == this.originalPackage)
			return confClass
		else
			return confClass.ESuperTypes.findFirst[t|findRootPackage(t.EPackage) == originalPackage]
	}

	protected def EObject fumlToConf(Object_ fumlObject) {
		return this.statesBuilder.VM.instanceMap.getEObject(fumlObject)
	}

	protected def EObject fumlToOriginal(Object_ fuml) {
		val confObject = fumlToConf(fuml)
		if (confObject != null)
			return confToOriginal(confObject)
		else
			return null
	}

	protected def EPackage findRootPackage(EPackage p) {
		if (p.ESuperPackage == null)
			return p
		else
			return findRootPackage(p.ESuperPackage)
	}

	protected def isNewClass(EClass c) {
		c.EAllSuperTypes.forall[t|!(findRootPackage(t.eClass.EPackage) == originalPackage)]
	}

	protected def EObject convertConfToOriginalAttOnly(EObject confObject) {
		val confEClass = confObject.eClass
		var EClass origEClass = null
		if (findRootPackage(confObject.eClass.EPackage) == this.originalPackage)
			origEClass = confEClass
		else
			origEClass = confClassToOrigClass(confEClass)

		val res = originalFactory.create(origEClass)

		copyAttributes(confObject, res)

		return res

	}

	protected static def copyAttributes(EObject in, EObject out) {
		for (prop : in.eClass.EAllAttributes) {
			val value = in.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)
			try {
				out.eSet(prop, value)
			} catch (Exception e) {
			}

		}
	}

	/*protected def EObject convertConfToTraced(EObject confObject) {
		val confEClass = confObject.eClass
		var EClass origEClass = null
		if (findRootPackage(confObject.eClass.EPackage) == this.originalPackage)
			origEClass = confEClass
		else
			origEClass = confClassToOrigClass(confEClass)
		val res = newTraceObject("Traced" + origEClass.name)

		for (prop : res.eClass.EAllStructuralFeatures) {
			val value = confObject.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)
			try {
				res.eSet(prop, value)
			} catch (Exception e) {
			}

			return res
		}

	}*/
	def createStateSystem() {
		this.traceSystem = traceFactory.create(traceSystemClass)
		createNewState();
	}

	protected def EObject getLastState() {
		val int stateNumber = (traceSystem.get("globalTrace") as Collection<EObject>).size();
		val lastState = (traceSystem.get("globalTrace") as Collection<EObject>).get(stateNumber - 1);
		return lastState;
	}

	def EObject getStateSystem() {
		return traceSystem
	}

	def createNewState() {
		val EObject gs = traceFactory.create(globalStateClass);
		(traceSystem.get("globalTrace") as Collection<EObject>).add(gs)
		createAllStates(gs);
	}

	def boolean isOrigClass(EClass c) {
		originalPackage.eAllContents.filter(EClass).toSet.contains(c)
	}

	public def abstract void updateState();

	public def abstract void addEntryEvent(ActivityEntryEvent event);

	public def abstract void addExitEvent(ActivityExitEvent event);

	protected def abstract void createAllStates(EObject gs);

}
