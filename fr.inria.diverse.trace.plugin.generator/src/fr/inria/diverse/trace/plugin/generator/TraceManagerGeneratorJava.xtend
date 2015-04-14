package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStringsCreator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import fr.inria.diverse.trace.commons.CodeGenUtil
import java.util.Set
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClassifier
import java.util.List
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import java.util.Map
import java.util.HashMap

class TraceManagerGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage executionMM
	private val TraceMMGenerationTraceability traceability
	private val List<GenPackage> refGenPackages

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, EPackage executionMM,
		TraceMMGenerationTraceability traceability, List<GenPackage> refGenPackages) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "Manager"
		this.packageQN = packageQN
		this.executionMM = executionMM
		this.traceability = traceability
		this.refGenPackages = refGenPackages
	}

	private def String baseGetFQN(EClassifier c) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p) + "." + c.name
		} else {
			return c.name
		}
	}

	private def String getFQN(EClassifier c) {
		var String base = ""
		val gc = getGenClassifier(c)
		if (gc != null) {
			if (gc.genPackage.basePackage != null) {
				base = gc.genPackage.basePackage + "."
			}
		}
		return base+baseGetFQN(c);
	}

	private def String getEClassFQN(EClass c) {
		return getFQN(c)
	}

	private def GenClassifier getGenClassifier(EClassifier c) {
		if (c != null) {
			for (gp : refGenPackages) {
				for (gc : gp.eAllContents.filter(GenClassifier).toSet) {
					val ecoreClass = gc.ecoreClassifier
					if (ecoreClass != null) {
						val s1 = baseGetFQN(ecoreClass)
						val s2 = baseGetFQN(c)
						if (s1 != null && s2 != null && s1.equals(s2)) {
							return gc
						}
					}
				}
			}

		}
		return null

	}

	private def String getEPackageFQN(EPackage p) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP) + "." + p.name
		} else {
			return p.name
		}
	}

	private def String stringCreate(EClass c) {
		val EPackage p = c.EPackage
		return getEPackageFQN(p) + "." + p.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name + "()"
	}

	private def String stringGetter(EStructuralFeature f) {
		if (f instanceof EAttribute) {
			if (f.EAttributeType.name.equals("EBoolean")) {
				return "is" + f.name.toFirstUpper + "()"
			}
		}
		return "get" + f.name.toFirstUpper + "()"
	}

	private def String stringGetter(String s) {
		return "get" + s.toFirstUpper + "()"
	}

	private def stringSetter(EStructuralFeature f, String value) {
		return "set" + f.name.toFirstUpper + "(" + value + ")"
	}

	private def stringSetter(String f, String value) {
		return "set" + f.toFirstUpper + "(" + value + ")"
	}

	public def String generateCode() {
		val String code = generateUglyCode()
		try {
			return CodeGenUtil.formatJavaCode(code)
		} catch (Throwable t) {
			return code
		}

	}
	
	private Map<String,Integer> counters = new HashMap
	private def String uniqueVar(String s) {
		if (!counters.containsKey(s)) {
			counters.put(s,0)
		}
		return s+counters.get(s)
	}
	private def void incVar(String s) {
		if (!counters.containsKey(s)) {
			counters.put(s,0)
		}
		counters.put(s,counters.get(s)+1)
	}

	private def EClassifier getEventParamRuntimeType(EStructuralFeature f) {
		var EClass res = null
		if (f instanceof EAttribute) {
			//TODO
		} else if (f instanceof EReference) {
			val potentialRealRuntimeClass = traceability.getMutableClass(f.EReferenceType)
			if (potentialRealRuntimeClass != null) {

				// TODO here in the general case we need to find the exe class
				res = potentialRealRuntimeClass
			} else {

				// TODO same here
				res = f.EReferenceType
			}
		}
		return res
	}

	private def String generateUglyCode() {
		return '''package «packageQN»;

import fr.inria.diverse.trace.api.ITraceManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.common.util.TreeIterator;
import java.io.IOException;

public class «className» implements ITraceManager {

	private  «getEClassFQN(traceability.rootClass)» traceRoot;
	private  «getEClassFQN(traceability.tracedObjectsClass)» tracedObjects;
	private  «getEClassFQN(traceability.eventsClass)» events;
	private  Resource executedModel;
	private  Map<EObject, EObject> exeToTraced;
	private  «getEClassFQN(traceability.globalStateClass)» lastState;

	private Resource traceResource;

	public «className» (Resource exeModel, Resource traceResource) {
		this.traceResource = traceResource;
		this.executedModel = exeModel;
	}

	/**
	 * Note: does NOT check that the model has indeed been modified!! Might be useless
	 * TRACE MM DEPENDENT
	 */
	@Override
	public void addState() {

		lastState = «stringCreate(traceability.globalStateClass)»;
		traceRoot.«stringGetter(TraceMMStringsCreator.ref_SystemToGlobal)».add(lastState);

		// We look at each object instance of a class with mutable properties 
		// Each of these objects should eventually become a traced object
		
		
		for (TreeIterator<EObject> i = executedModel.getAllContents(); i.hasNext();){
			EObject o = i.next();
		
			«FOR c : traceability.runtimeClasses»
			«val traced = traceability.getTracedClass(c)»

			/**
			 * Storing the state of a «getEClassFQN(c)» object
			 */
			if (o instanceof «getEClassFQN(c)») {

				«getEClassFQN(c)» o_cast = («getEClassFQN(c)») o;

				// First we find the traced object, and we create it of required
				«getEClassFQN(traced)» tracedObject;
				if (!exeToTraced.containsKey(o)) {
					tracedObject = «stringCreate(traced)»;
					«val Set<EReference> origRefs = traceability.getRefs_originalObject(traced)»
					«FOR EReference origRef : origRefs» 
					tracedObject.«stringSetter(origRef, "o_cast")»;
					«ENDFOR»
					exeToTraced.put(o, tracedObject);
					tracedObjects.«stringGetter(TraceMMStringsCreator.ref_createTracedObjectsToTrace(traced))».add(tracedObject);
				} else {
					tracedObject = («getEClassFQN(traced)») exeToTraced.get(o);
				}
				«FOR p : traceability.getMutablePropertiesOf(c)»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass stateClass = ptrace.getEType as EClass»
				«val EReference refGlobalToState = traceability.getGlobalToState(p)»
				«incVar("localTrace")»
				«incVar("previousValue")»

				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				// TODO to change if we switch from refering the exeMM to refering the AS (as in the paper) -> need to compare to refs to origobjs/tracedobj
				List<«getEClassFQN(stateClass)»> «uniqueVar("localTrace")» = tracedObject.«stringGetter(ptrace)»;
				«getEClassFQN(stateClass)» «uniqueVar("previousValue")» = null;
				if (!«uniqueVar("localTrace")».isEmpty())
					«uniqueVar("previousValue")» = «uniqueVar("localTrace")».get(«uniqueVar("localTrace")».size() - 1);
				if («uniqueVar("previousValue")» != null && «uniqueVar("previousValue")».«stringGetter(p)» == o_cast.«stringGetter(p)») {
					
					lastState.«stringGetter(refGlobalToState)».add(«uniqueVar("previousValue")»);

				} // Else we create one
				else {
					«getEClassFQN(stateClass)» newValue = «stringCreate(stateClass)»;
					newValue.«stringSetter(p, "o_cast." + stringGetter(p))»;
					tracedObject.«stringGetter(ptrace)».add(newValue);
					lastState.«stringGetter(refGlobalToState)».add(newValue);
				}
				
				«ENDFOR»
				}
			«ENDFOR»
			}
			}
			
			

	/**
	 * For now very simple since only one model (ie the exe one), and no new classes introduced in the extension
	 * TRACE MM DEPENDENT
	 */
	public void goTo(int stepNumber) {
		«getEClassFQN(traceability.globalStateClass)» stateToGo = traceRoot.«stringGetter(
			TraceMMStringsCreator.ref_SystemToGlobal)».get(stepNumber);

		«FOR p : traceability.allMutableProperties»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»

		for («getEClassFQN(stateClass)» value : stateToGo.«stringGetter(
			TraceMMStringsCreator.ref_createGlobalToState(stateClass))») {
«««			This is very ugly for now since we don't check if there is indeed an original object
			«val EReference origRef = traceability.getRefs_originalObject(ptrace.getEContainingClass).get(0)»
			((«getEClassFQN((p.eContainer as ClassExtension).extendedExistingClass)»)value.«stringGetter("parent")».«stringGetter(
			origRef)»).«stringSetter(p, "value." + stringGetter(p))»;
		}
		
		«ENDFOR»

	}

	/**
	 * TODO how to get the parameters of the operation call? Not possible with current gemoc
	 * TRACE MM DEPENDENT
	 */
	 @Override
	public void addEvent(String eventName, Map<String, Object> params) {
		
		«IF !traceability.eventClasses.empty»
		
		«getEClassFQN(traceability.eventOccurrenceClass)» createdEvent = null;
		
		switch(eventName) {
			
			«FOR e : traceability.getEventClasses»

			«val String varName = e.name.toFirstLower.replace(" ", "") + "Instance"»
		
			case "«e.name»":
			
			// First we create the event
			«getEClassFQN(e)» «varName» = «stringCreate(e)»;
			createdEvent = «varName»;
			«val properties = e.EAllStructuralFeatures.filter[f|
			!traceability.eventOccurrenceClass.EStructuralFeatures.contains(f)]»
			«IF !properties.empty»
			if (params != null) {
				for (String k : params.keySet()) {
					
					switch(k) {
					«FOR p : properties»
					case "«p.name»":
						Object v = params.get(k);
						«val type = getEventParamRuntimeType(p)»
						if (v instanceof «getFQN(type)»)
							«IF type == p.EType»
							«varName».«stringSetter(p, "(" + getFQN(p.EType) + ")v")»;
							«ELSE»
							«varName».«stringSetter(p, "(" + getFQN(p.EType) + ")exeToTraced.get(v)")»;
							«ENDIF»
					
						break;
					
					}
				}
			}
					«ENDFOR»
			«ENDIF»

			// Then we add it to its trace
			this.events.«stringGetter(traceability.getEventTrace(e))».add(«varName»);
			break;
			«ENDFOR»
		}
		
		if (createdEvent != null) {
			
			createdEvent.«stringSetter(TraceMMStringsCreator.ref_EventToGlobal,"this.lastState")»;
		}
		
		«ENDIF»
		
	}

	@Override
	public void initTrace() {
		// Create root
		this.traceRoot = «stringCreate(traceability.rootClass)»;
		
		// Put in the resource
		traceResource.getContents().add(traceRoot);

		// Create objects storage
		this.tracedObjects = «stringCreate(traceability.getTracedObjectsClass)»;
		this.traceRoot.«stringSetter(TraceMMStringsCreator.ref_SystemToTracedObjects, "tracedObjects")»;

		// Create events storage
		this.events = «stringCreate(traceability.eventsClass)»;
		this.traceRoot.«stringSetter(TraceMMStringsCreator.ref_SystemToEvents, "events")»;

		// Initializing the map exeobject -> tracedobject
		this.exeToTraced = new HashMap<EObject, EObject>();
	}

	@Override
	public void save() {
		try {
			traceResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EObject getTraceRoot() {
		return traceRoot;
	}
	
	@Override
	public EObject getExecutionState(int index) {
		return traceRoot.«stringGetter(TraceMMStringsCreator.ref_SystemToGlobal)».get(index);
	}

	@Override
	public String getDescriptionOfExecutionState(int index) {
		String result = "";
		«getEClassFQN(traceability.globalStateClass)» gs = traceRoot.«stringGetter(
			TraceMMStringsCreator.ref_SystemToGlobal)».get(index);
		
		«FOR p : traceability.allMutableProperties» 
		«val EReference refGlobalToState = traceability.getGlobalToState(p)»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		result += "«p.name.toFirstUpper» values:";
		for («getEClassFQN(stateClass)» currenState : gs.«stringGetter(refGlobalToState)») {
			result += "\n\t" + currenState.«stringGetter(p)»;
		}
		«ENDFOR»
		return result;
	}

	@Override
	public int getTraceSize() {
		return traceRoot.«stringGetter(TraceMMStringsCreator.ref_SystemToGlobal)».size();
	}

}
		'''
	}

}
