package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStringsCreator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import fr.inria.diverse.trace.commons.CodeGenUtil

class TraceManagerGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage executionMM
	private val TraceMMGenerationTraceability traceability

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, EPackage executionMM,
		TraceMMGenerationTraceability traceability) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "TraceManager"
		this.packageQN = packageQN
		this.executionMM = executionMM
		this.traceability = traceability
	}

	private def String getEClassFQN(EClass c) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p) + "." + c.name
		} else {
			return c.name
		}
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
		return CodeGenUtil.formatJavaCode(code)
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

«««// We import all classes from the trace metamodel (just to be sure)
««««FOR c : traceMM.eAllContents.filter(EClass).toSet»
«««import «getEClassFQN(c)»
««««ENDFOR»


«««// We import all classes from the exe metamodel (just to be sure)
««««FOR c : executionMM.eAllContents.filter(EClass).toSet»
«««import «getEClassFQN(c)»
««««ENDFOR»



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
					«FOR origRef : traceability.getRefs_originalObject(traced)»
					tracedObject.«stringSetter(origRef, "o_cast")»;
					«ENDFOR»
					exeToTraced.put(o, tracedObject);
					tracedObjects.«stringGetter(TraceMMStringsCreator.ref_createTracedObjectsToTrace(traced))».add(tracedObject);
				} else {
					tracedObject = («getEClassFQN(traced)») exeToTraced.get(o);
				}
				
				«FOR p : traceability.mutableProperties»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass stateClass = ptrace.getEType as EClass»
				«val EReference refGlobalToState = traceability.getGlobalToState(p)»

				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				// TODO to change if we switch from refering the exeMM to refering the AS (as in the paper) -> need to compare to refs to origobjs/tracedobj
				List<«getEClassFQN(stateClass)»> localTrace = tracedObject.«stringGetter(ptrace)»;
				«getEClassFQN(stateClass)» previousValue = null;
				if (!localTrace.isEmpty())
					previousValue = localTrace.get(localTrace.size() - 1);
				if (previousValue != null && previousValue.«stringGetter(p)» == o_cast.«stringGetter(p)») {
					
					lastState.«stringGetter(refGlobalToState)».add(previousValue);

				} // Else we create one
				else {
					«getEClassFQN(stateClass)» newValue = «stringCreate(stateClass)»;
					newValue.«stringSetter(p, "o_cast." + stringGetter(p))»;
					tracedObject.«stringGetter(ptrace)».add(newValue);
					lastState.«stringGetter(refGlobalToState)».add(newValue);
				}
				}
				«ENDFOR»
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

		«FOR p : traceability.mutableProperties»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»

		for («getEClassFQN(stateClass)» value : stateToGo.«stringGetter(
			TraceMMStringsCreator.ref_createGlobalToState(stateClass))») {
			«val EReference origRef = traceability.getRefs_originalObject(ptrace.getEContainingClass).get(0)»
			((«getEClassFQN((p.eContainer as ClassExtension).extendedExistingClass)»)value.«stringGetter("parent")».«stringGetter(
			origRef)»).«stringSetter(p, "value." + stringGetter(p))»;
		}
		
		«ENDFOR»

	}

	/**
	 * TODO how to get the parameters of the operation call?
	 * TRACE MM DEPENDENT
	 */
	 @Override
	public void addEvent() {
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
		
		«FOR p : traceability.mutableProperties»
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
