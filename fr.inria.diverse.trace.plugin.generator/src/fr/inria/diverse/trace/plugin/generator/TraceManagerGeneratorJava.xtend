package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStringsCreator
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

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
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
	private Deque<«getFQN(traceability.macroEventClass)»> context = new ArrayDeque<«getFQN(traceability.macroEventClass)»>();
	private static final List<String> macroEvents = Arrays
			.asList(
				«FOR macroEventClass : traceability.macroEventClasses SEPARATOR ","»
				"«macroEventClass.name»"
				«ENDFOR»
			);
	

	public «className» (Resource exeModel, Resource traceResource) {
		this.traceResource = traceResource;
		this.executedModel = exeModel;
	}

	/**
	 * Note: does NOT check that the model has indeed been modified!! Might be useless
	 * TRACE MM DEPENDENT
	 */
	private boolean addState(boolean onlyIfChange) {
		
		«getFQN(traceability.globalStateClass)» newState = «stringCreate(traceability.globalStateClass)»;
		boolean changed = false;
		
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
					
					newState.«stringGetter(refGlobalToState)».add(«uniqueVar("previousValue")»);

				} // Else we create one
				else {
					changed = true;
					«getEClassFQN(stateClass)» newValue = «stringCreate(stateClass)»;
					newValue.«stringSetter(p, "o_cast." + stringGetter(p))»;
					tracedObject.«stringGetter(ptrace)».add(newValue);
					newState.«stringGetter(refGlobalToState)».add(newValue);
				}
				
				«ENDFOR»
				}
			«ENDFOR»
			}
			
			boolean createNewState = lastState == null || (!onlyIfChange || changed);
			if (createNewState) {
				lastState = newState;
				traceRoot.«stringGetter(TraceMMStringsCreator.ref_SystemToGlobal)».add(lastState);
			}
			
			return createNewState;
			
	}
			
			

	@Override
	public void goTo(EObject state) {
		int index = traceRoot.getStatesTrace().indexOf(state);
		if (index != -1) {
			goTo(index);
		}
	}

	/**
	 * For now very simple since only one model (ie the exe one), and no new classes introduced in the extension
	 * TRACE MM DEPENDENT
	 */
	@Override
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

	@SuppressWarnings("unchecked")
	private static void emfAdd(EObject o, String property, Object value) {
		for (EReference r : o.eClass().getEAllReferences()) {
			if (r.getName().equals(property)) {
				Object coll = o.eGet(r);
				if (coll instanceof Collection) {
					((Collection<Object>) coll).add(value);
					return;
				}
			}
		}
	}
	
	@Override
	public void retroAddEvent(String eventName, Map<String, Object> params) {
		addEvent(eventName, params, this.getTraceSize()-2);
		
	}

	
	@Override
	public void addEvent(String eventName, Map<String, Object> params) {
		addEvent(eventName, params, this.getTraceSize()-1);
	}
	
	/**
	 * TODO how to get the parameters of the operation call? Not possible with current gemoc
	 * TRACE MM DEPENDENT
	 */
	private void addEvent(String eventName, Map<String, Object> params, int stateIndex) {
		
		if (stateIndex >= 0) {
		
		«getEClassFQN(traceability.globalStateClass)» state = this.traceRoot.getStatesTrace().get(stateIndex);
		
		«IF !traceability.eventClasses.empty»
				
		switch(eventName) {
			
			«FOR e : traceability.eventClasses.filter[c|!c.abstract]»

			«val String varName = e.name.toFirstLower.replace(" ", "") + "Instance"»
		
			case "«e.name»":
			
			// First we create the event
			«getEClassFQN(e)» «varName» = «stringCreate(e)»;
			«varName».«stringSetter(TraceMMStringsCreator.ref_EventToGlobal,"state")»;
			
			// TODO only generate this code is the event is indeed potentially part of a macro event
			if (!context.isEmpty()){
				emfAdd(context.getFirst(), "subEvents", «varName»);
			}
			«IF traceability.macroEventClasses.contains(e)»
			context.push(«varName»);
			«ENDIF»
			«val properties = e.EAllStructuralFeatures.filter[f|
			!traceability.eventOccurrenceClass.EStructuralFeatures.contains(f)
			&& !traceability.macroEventClass.EStructuralFeatures.contains(f)
			// TODO store the subEvents string somewhere else?
			&& !f.name.equals("subEvents")]»
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
					
				
					«ENDFOR»
						}
				}
			}
			«ENDIF»

			// Then we add it to its trace
			this.events.«stringGetter(traceability.getEventTrace(e))».add(«varName»);
			break;
			«ENDFOR»
		}
		
		«ENDIF»
		}
	}


	@Override
	public void endEvent(String eventName, Object returnValue) {
		if (isMacro(eventName)) {
			context.pop().setFollowingState(lastState);
		}
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
		StringBuilder result = new StringBuilder();
		«getEClassFQN(traceability.globalStateClass)» gs = traceRoot.«stringGetter(
			TraceMMStringsCreator.ref_SystemToGlobal)».get(index);
		
		«FOR p : traceability.allMutableProperties» 
		«val EReference refGlobalToState = traceability.getGlobalToState(p)»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		result.append("\n«p.name.toFirstUpper» values:");
		for («getEClassFQN(stateClass)» currenState : gs.«stringGetter(refGlobalToState)») {
			result.append("\n\t" + currenState.«stringGetter(p)»);
		}
		«ENDFOR»
		
		//TODO instead of generic display, use instanceof and access to event parameters
		if(gs.getFollowingEvent() != null)
			result.append("\n\nFollowing event: "+gs.getFollowingEvent().eClass().getName());
		if (!gs.getFinishedMacroEvents().isEmpty()) {
			result.append("\n\nFinished macro events: ");
			for («getEClassFQN(traceability.macroEventClass)» m : gs.getFinishedMacroEvents()) {
				result.append("\n\t" + m.eClass().getName());
				result.append(" (began at state "
						+ traceRoot.getStatesTrace().indexOf(
								m.getPrecedingState()) + ")");
			}
		}
		if (!gs.getMacroEvents().isEmpty()) {
			result.append("\n\nStarting macro events: ");
			for («getEClassFQN(traceability.macroEventClass)» m : gs.getMacroEvents()) {
				result.append("\n\t" + m.eClass().getName());
				if (m.getFollowingState() != null) {
					result.append(" (ends at state "+ traceRoot.getStatesTrace().indexOf(m.getFollowingState()) +")");
				}
			}
		}
		
		result.deleteCharAt(0);
		return result.toString();
	}

	@Override
	public int getTraceSize() {
		return traceRoot.«stringGetter(TraceMMStringsCreator.ref_SystemToGlobal)».size();
	}
	
	@Override
	public boolean isMacro(String string) {
		return macroEvents.contains(string);
	}

	@Override
	public boolean addStateIfChanged() {
		return addState(true);
	}

	@Override
	public void addState() {
		addState(false);
	}
	
	
	@Override
	public String currentMacro() {
		if(!context.isEmpty())
			return context.getFirst().eClass().getName();
		else
			return null;
	}

}
		'''
	}

}
