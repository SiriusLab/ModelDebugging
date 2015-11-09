package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStrings
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
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
import fr.inria.diverse.trace.commons.tracemetamodel.StepStrings
import org.eclipse.emf.ecore.EOperation
import ecorext.Rule

class TraceManagerGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val TraceMMGenerationTraceability traceability
	private val List<GenPackage> refGenPackages
	private val boolean gemoc

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, TraceMMGenerationTraceability traceability,
		List<GenPackage> refGenPackages, boolean gemoc) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "Manager"
		this.packageQN = packageQN
		this.traceability = traceability
		this.refGenPackages = refGenPackages
		this.gemoc = gemoc
	}

	private def String getBaseFQN(EClassifier c) {
		if (c != null) {
			val EPackage p = c.getEPackage
			if (p != null) {
				return getBaseFQN(p) + "." + c.name
			} else {
				return c.name
			}
		} else {
			return ""
		}
	}
	
		private def String getBaseFQN(Rule r) { 
		val EOperation o = r.operation
		val EClass c = r.containingClass
		return getBaseFQN(c) + "." + o.name
	}

	private def String getFQN(EClassifier c) {

		if (c.instanceClassName != null && c.instanceClassName != "")
			return c.instanceClassName

		var String base = ""
		val gc = getGenClassifier(c)
		if (gc != null) {
			if (gc.genPackage.basePackage != null) {
				base = gc.genPackage.basePackage + "."
			}
		}
		return base + getBaseFQN(c);
	}
	
	private def String getFQN(EPackage p) {

		var String base = ""
		val gp = getGenPackage(p)
		if (gp != null) {
			if (gp.basePackage != null) {
				base = gp.basePackage + "."
			}
		}
		return base + getBaseFQN(p);
	}

	private def String getTracedFQN(EClassifier c) {
		if (c instanceof EClass) {
			val tracedClass = traceability.getTracedClass(c)
			if (tracedClass != null)
				return getFQN(traceability.getTracedClass(c))
			else
				return getFQN(c)
		} else {
			return getFQN(c)
		}
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
						val s1 = getBaseFQN(ecoreClass)
						val s2 = getBaseFQN(c)
						if (s1 != null && s2 != null && s1.equalsIgnoreCase(s2)) {
							return gc
						}
					}
				}
			}

		}
		return null
	}
	
	private def GenPackage getGenPackage(EPackage p) {
		if (p != null) {
			for (gp : refGenPackages) {
				val packageInGenpackage = gp.getEcorePackage
				if (packageInGenpackage != null) {
					val s1 = getBaseFQN(p)
						val s2 = getBaseFQN(packageInGenpackage)
						if (s1 != null && s2 != null && s1.equalsIgnoreCase(s2)) {
							return gp
						}
				}
			}
		}
		return null
	}

	private static def boolean isNotSuperTypeOf(EClass c, Collection<EClass> eclasses) {
		for (eclass : eclasses) {
			if (eclass.EAllSuperTypes.contains(c))
				return false
		}
		return true
	}

	private static def List<EClass> partialOrderSort (List<EClass> eclasses) { 
		val List<EClass> result = new ArrayList<EClass>
		for (ci : eclasses) {
			if (result.isEmpty)
				result.add(ci)
			else {
				var boolean found = false
				for (var int i = 0; i < result.size && !found; i++) {
					val Set<EClass> followings = result.subList(i, result.size).toSet
					if (ci.isNotSuperTypeOf(followings)) {
						result.add(0, ci)
						found = true
					}
				}

				if (!found)
					result.add(ci)
			}
		}
		return result

	}
	
	private  def String getEOperationGetCode (Rule r) {
		val o = r.operation
		val eclass = r.containingClass
		val epackage = eclass.EPackage
		val res = '''«getFQN(epackage)».«epackage.name.toFirstUpper»Package.eINSTANCE.get«eclass.name»__«o.name.toFirstUpper»()'''
		return res
	}

	private def String getBaseFQN(EPackage p) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getBaseFQN(superP) + "." + p.name
		} else {
			return p.name
		}
	}

	private def String stringCreate(EClass c) {
		val EPackage p = c.EPackage
		return getBaseFQN(p) + "." + p.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name + "()"
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
		val String code = generateTraceManagerClass()
		try {
			return CodeGenUtil.formatJavaCode(code)
		} catch (Throwable t) {
			return code
		}

	}

	private Map<String, Integer> counters = new HashMap

	private def String uniqueVar(String s) {
		if (!counters.containsKey(s)) {
			counters.put(s, 0)
		}
		return s + counters.get(s)
	}

	private def void incVar(String s) {
		if (!counters.containsKey(s)) {
			counters.put(s, 0)
		}
		counters.put(s, counters.get(s) + 1)
	}

	private def EClassifier getEventParamRuntimeType(EStructuralFeature f) {
		var EClass res = null
		if (f instanceof EAttribute) {
			// TODO
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

	private def String stringGetterTracedValue(String javaVarName, EStructuralFeature p) {
		if (p instanceof EReference && traceability.hasTracedClass(p.EType as EClass))
			return '''((«getFQN(traceability.getTracedClass(p.EType as EClass))»)exeToTraced.get(«javaVarName».«stringGetter(
				p)»))'''
		else
			return javaVarName + "." + stringGetter(p)
	}

	private def String stringGetterExeValue(String javaVarName, EStructuralFeature p) {
		if (p instanceof EReference && traceability.hasTracedClass(p.EType as EClass))
			return "((" + getFQN(p.EType as EClass) + ")getTracedToExe(" + javaVarName + "." + stringGetter(p) + "))"
		else
			return javaVarName + "." + stringGetter(p)
	}

	private def Set<EClass> getConcreteSubtypesTraceClassOf(EClass tracedClass) {
		val Set<EClass> result = new HashSet()
		result.addAll(this.traceMM.eAllContents.filter(EClass).filter [ c |
			!c.abstract && c.EAllSuperTypes.contains(tracedClass)
		].toSet)
		if (!tracedClass.abstract)
			result.add(tracedClass)
		return result
	}

	private def Set<EStructuralFeature> getAllMutablePropertiesOf(EClass exeClass) {
		val Set<EStructuralFeature> res = new HashSet<EStructuralFeature>
		res.addAll(traceability.getMutablePropertiesOf(exeClass))
		res.addAll(exeClass.EAllSuperTypes.map[s|traceability.getMutablePropertiesOf(s)].flatten.toSet);
		return res
	}

	private def Set<EClass> getAllMutableClasses() {
		return traceability.allMutableClasses.filter[c|!c.allMutablePropertiesOf.empty].toSet
	}


	private def String generateImports() {
		'''
import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.api.IValueTrace;
import fr.inria.diverse.trace.api.impl.GenericValueTrace;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
«««import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.common.util.TreeIterator;
		'''
	}
	
	private def String generateFields() {
		'''
		
	private  «getEClassFQN(traceability.traceMMExplorer.traceClass)» traceRoot;
	private  Resource executedModel;
	
	««« TODO one map per type? So that we can completely stop manipulationg eobjects
	private  Map<EObject, EObject> exeToTraced;
	
	private  «getEClassFQN(traceability.traceMMExplorer.getStateClass)» lastState;
	private List<IValueTrace> traces;

	private Resource traceResource;
	private Deque<«getFQN(traceability.traceMMExplorer.stepClass)»> context = new LinkedList<«getFQN(
			traceability.traceMMExplorer.stepClass)»>();
	private static final List<String> bigSteps = Arrays
			.asList(
				«FOR bigStepClass : traceability.getBigStepClasses SEPARATOR ","»
				"«bigStepClass.name»"
				«ENDFOR»
			);
		'''
	}
	
	private def String generateConstructor() {
		'''
	public «className» (Resource exeModel, Resource traceResource) {
		this.traceResource = traceResource;
		this.executedModel = exeModel;
		this.traces = new ArrayList<IValueTrace>();
	}'''
	}


	private def String generateExeToFromTracedGenericMethods() {
		'''
		private Collection<? extends EObject> getExeToTraced(Collection<? extends EObject> exeObjects) {
		Collection<EObject> result = new ArrayList<EObject>();
		for(EObject exeObject : exeObjects) {
			storeAsTracedObject(exeObject);
			result.add(exeToTraced.get(exeObject));
		}
		return result;
	}	
	
	private Collection<? extends EObject> getTracedToExe(
			Collection<? extends EObject> tracedObjects) {
		Collection<EObject> result = new ArrayList<EObject>();
		for (EObject tracedObject : tracedObjects) {
			result.add(getTracedToExe(tracedObject));
		}
		return result;
	}

	private EObject getTracedToExe(EObject tracedObject) {
		for (EObject key : exeToTraced.keySet()) {
			if (exeToTraced.get(key) == tracedObject)
				return key;
		}
		return null;
	}
		'''
	}
	
	private def String generateStoreAsTracedMethods() {
		'''    «FOR mutClass : traceability.allMutableClasses.filter[c|!c.isAbstract]»

private void storeAsTracedObject(«getFQN(mutClass)» o) {
			«val traced = traceability.getTracedClass(mutClass)»
		
			// First we find the traced object, and we create it if required
			«getEClassFQN(traced)» tracedObject;
			if (!exeToTraced.containsKey(o)) {
			tracedObject = «stringCreate(traced)»; 
			«val Set<EReference> origRefs1 = traceability.getRefs_originalObject(traced)»
			«FOR EReference origRef : origRefs1» 
			tracedObject.«stringSetter(origRef, "o")»;
			«ENDFOR»
			exeToTraced.put(o, tracedObject);
			traceRoot.«stringGetter(TraceMMStrings.ref_createTraceClassToTracedClass(traced))».add(tracedObject);
			
			«FOR p : getAllMutablePropertiesOf(mutClass)»
			«val EReference ptrace = traceability.getTraceOf(p)»
			traces.add(new GenericValueTrace(tracedObject.«stringGetter(ptrace)», this));
			«ENDFOR»
		}
	}
	
    «ENDFOR»

private void storeAsTracedObject(EObject o) {
 «FOR mutClass : partialOrderSort(traceability.allMutableClasses.filter[c|!c.isAbstract].toList) SEPARATOR "\n else "»
 
	if (o instanceof «getFQN(mutClass)»)
		storeAsTracedObject((«getFQN(mutClass)»)o);
		
«ENDFOR»
}'''
	}

private def String generateAddStateMethods() {
	'''
	
		@Override
	public boolean addStateIfChanged() {
		return addState(true);
	}

	@Override
	public void addState() {
		addState(false);
	}
	

	 @SuppressWarnings("unchecked")
	private boolean addState(boolean onlyIfChange) {
		
		«getFQN(traceability.traceMMExplorer.getStateClass)» newState = «stringCreate(
			traceability.traceMMExplorer.getStateClass)»;
		boolean changed = false;
		
		// We look at each object instance of a class with mutable properties 
		// Each of these objects should eventually become a traced object
		
			
		Set<Resource> allResources = new HashSet<>();
		allResources.add(executedModel);
		«IF gemoc»
		allResources.addAll(org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(executedModel));
		«ENDIF»
		for (Resource r : allResources)
		for (TreeIterator<EObject> i = r.getAllContents(); i.hasNext();){
			EObject o = i.next();
		
		
			
			«FOR c : partialOrderSort(getAllMutableClasses.filter[c|!c.isAbstract].toList) SEPARATOR "\n else "»
			«val traced = traceability.getTracedClass(c)»

			/**
			 * Storing the state of a «getEClassFQN(c)» object
			 */
			if (o instanceof «getEClassFQN(c)») {

				«getEClassFQN(c)» o_cast = («getEClassFQN(c)») o;

				storeAsTracedObject(o_cast);
				
				«IF !getAllMutablePropertiesOf(c).empty»
					«getEClassFQN(traced)» tracedObject = («getEClassFQN(traced)») exeToTraced.get(o);
				«ENDIF»
				«FOR p : getAllMutablePropertiesOf(c)»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass stateClass = ptrace.getEType as EClass»
				«incVar("localTrace")»
				«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
				«incVar("previousValue")»
				«incVar("noChange")»


				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				««« TODO to change if we switch from refering the exeMM to refering the AS (as in the ECMFA paper) -> need to compare to refs to origobjs/tracedobj
				««« TODO handle collections of datatypes
				List<«getEClassFQN(stateClass)»> «uniqueVar("localTrace")» = tracedObject.«stringGetter(ptrace)»;
				«getEClassFQN(stateClass)» «uniqueVar("previousValue")» = null;
				if (!«uniqueVar("localTrace")».isEmpty())
					«uniqueVar("previousValue")» = «uniqueVar("localTrace")».get(«uniqueVar("localTrace")».size() - 1);
				
				««« Case many
				«IF p.many»
				
					««« If instances of new class, we have to make sure that there are traced versions 
					«IF traceability.allMutableClasses.contains(p.EType)»
						
						for(«getFQN(p.EType)» aValue : o_cast.«stringGetter(p)») {
							storeAsTracedObject(aValue);
						}
						
					«ENDIF»
				
				boolean «uniqueVar("noChange")»= true;
				if («uniqueVar("previousValue")» != null) {

					if («uniqueVar("previousValue")».«stringGetter(p)».size() == o_cast
							.«stringGetter(p)».size()) {

						««« We this is an ordered collection, we have to compare in the correct order
						«IF p.ordered»
						java.util.Iterator<«getFQN(p.EType)»> it = o_cast.«stringGetter(p)».iterator();
						for («getFQN(traceability.getTracedClass(p.EType as EClass))» aPreviousValue : «uniqueVar("previousValue")»
								.«stringGetter(p)») {
							«getFQN(p.EType)» aCurrentValue = it.next();
							if (aPreviousValue != exeToTraced.get(aCurrentValue)) {
								«uniqueVar("noChange")» = false;
								break;
							}
						}
						
						««« Else we simply check that the content is the same
						«ELSE»	
						«uniqueVar("noChange")» = «uniqueVar("previousValue")».«stringGetter(p)».containsAll(getExeToTraced(o_cast.«stringGetter(
			p)»));
						«ENDIF»
						««« end case ordered

					} else {
						«uniqueVar("noChange")» = false;
					}
				} else {
					«uniqueVar("noChange")» = false;
				}
					
				
				««« Case single
				«ELSE»
				
					««« If instance of new class, we have to make sure that there is a traced version 
					«IF traceability.allMutableClasses.contains(p.EType)»
					storeAsTracedObject(o_cast.«stringGetter(p)»);			
					«ENDIF»
					
					
					««« Getting the content of the field
					«incVar("content")»
					«««
					««« Case reference
					«IF p instanceof EReference»
					«getTracedFQN(p.EType)» «uniqueVar("content")» = null;
					if (o_cast.«stringGetter(p)» != null)
						«uniqueVar("content")» = «stringGetterTracedValue("o_cast", p)»;
					«««
					««« Case datatype
					«ELSEIF p instanceof EAttribute» 
					«getFQN(p.EType)» «uniqueVar("content")» = o_cast.«stringGetter(p)»;
					«ENDIF»
					««« end declaring/getting content
				
						
					boolean «uniqueVar("noChange")» = «uniqueVar("previousValue")» != null 
						&& «uniqueVar("previousValue")».«stringGetter(p)» == «uniqueVar("content")»;
						
					
				«ENDIF»
				««« end collection/single
					
					
					
				if («uniqueVar("noChange")») {
					newState.«stringGetter(refGlobalToState)».add(«uniqueVar("previousValue")»);

				} // Else we create one
				else {
					changed = true;
					«getEClassFQN(stateClass)» newValue = «stringCreate(stateClass)»;
					
					
					
					««« Case collection
					««« TODO: handle collections of datatypes!
					«IF p.many»
						 
						newValue.«stringGetter(p)».addAll((Collection<? extends «getFQN(traceability.getTracedClass(p.EType as EClass))»>) getExeToTraced(o_cast.«stringGetter(
			p)»));
			
					««« Case single
					«ELSE»
					
						newValue.«stringSetter(p, uniqueVar("content"))»;
					
									
					«ENDIF»
					««« end collection/Single
					
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
				traceRoot.«stringGetter(TraceMMStrings.ref_TraceToStates)».add(lastState);
			}
			
			// Undoing the new state created for nothing
			else {
			
			newState.«stringGetter(TraceMMStrings.ref_StateToStep_started)».clear();
			newState.«stringGetter(TraceMMStrings.ref_StateToStep_ended)».clear();	
			
			«FOR p : traceability.allMutableProperties»
			«val EReference tuple = traceability.getStateClassToValueClass(p)»
			newState.«stringGetter(tuple)».clear();
			«ENDFOR»
			}
			
			return createNewState;
			
	}'''
}

private def String generateGoToMethods() {
	'''
	@SuppressWarnings("unchecked")
	@Override
	public void goTo(EObject state) {
		
		if (state instanceof «getEClassFQN(traceability.traceMMExplorer.getStateClass)») {
			«getEClassFQN(traceability.traceMMExplorer.getStateClass)» stateToGo = («getEClassFQN(
			traceability.traceMMExplorer.getStateClass)») state;

		«FOR p : traceability.allMutableProperties»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		for («getEClassFQN(stateClass)» value : stateToGo.«stringGetter(
			TraceMMStrings.ref_createGlobalToState(stateClass))») {
				
				
		««« Case in which we can use the "originalObject" reference and simply set its values
		«IF p.eContainer instanceof ClassExtension»
			
			««« We have to test at runtime because we can't know at design time the type of the object containing the property
			««« The reason is that we keep the same class hierarchy in the trace. Maybe we should remove that. 
			«FOR concreteSubType : getConcreteSubtypesTraceClassOf(ptrace.getEContainingClass)»
			if (value.«stringGetter("parent")» instanceof «getFQN(concreteSubType)») {
				«val Collection<EReference> origRefs = traceability.getRefs_originalObject(concreteSubType)»
				«getFQN(concreteSubType)» parent_cast = («getFQN(concreteSubType)») value.«stringGetter("parent")»;
				«IF !origRefs.isEmpty»
					«val EReference origRef = origRefs.get(0)»
					«IF p.many»
						parent_cast.«stringGetter(origRef)».«stringGetter(p)».clear();
						parent_cast.«stringGetter(origRef)».«stringGetter(p)».addAll((Collection<? extends «getFQN(p.EType)»>) getTracedToExe(value.«stringGetter(
			p)»));
					«ELSE»
						«getFQN(p.EType)» toset = «stringGetterExeValue("value", p)»;
						«getFQN(p.EType)» current = ((«getFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«stringGetter(
			origRef)»).«stringGetter(p)»;
						if (current != toset)
							((«getFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«stringGetter(origRef)»).«stringSetter(
			p, "toset")»;
					«ENDIF»
				«ENDIF»
					
			}
			«ENDFOR»
			
		««« Case in which we have to recreate/restore execution objects in the model
		«ELSEIF p.eContainer instanceof EClass»
			«getFQN(p.EContainingClass)» exeObject = («getFQN(p.EContainingClass)») getTracedToExe(value.getParent());
			«IF p.many»
				exeObject.«stringGetter(p)».clear();
				exeObject.«stringGetter(p)».addAll((Collection<? extends «getFQN(p.EType)»>) getTracedToExe(value.«stringGetter(p)»));
			«ELSE»
				exeObject.«stringSetter(p, stringGetterExeValue("value",p))»;
			«ENDIF»
			
		«ENDIF»  
			
			
			
		}
		
		

		«ENDFOR»
		} else {
			goToValue(state);
		}
	}

	@Override
	public void goTo(int stepNumber) {
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» stateToGo = traceRoot.«stringGetter(
			TraceMMStrings.ref_TraceToStates)».get(stepNumber);
		goTo(stateToGo);
	}
	
	private void goToValue(EObject value) {
		Object states = emfGet(value, "states");
		if (states != null) {
			if (states instanceof List<?>) {
				// We get the first state in which this value existed
				Object state = ((List<?>) states).get(0);
				if (state instanceof «getEClassFQN(traceability.traceMMExplorer.getStateClass)») {
					goTo((«getEClassFQN(traceability.traceMMExplorer.getStateClass)») state);
				}
			}
		}
	}'''
}

private def String generateGenericEMFHelperMethods() {
	'''
	@SuppressWarnings("unchecked")
	private static void emfAdd(EObject o, String property, Object value) {
		for (EReference r : o.eClass().getEAllReferences()) {
			if (r.getName().equalsIgnoreCase(property)) {
				Object coll = o.eGet(r);
				if (coll instanceof Collection) {
					((Collection<Object>) coll).add(value);
					return;
				}
			}
		}
	}
	
	private static Object emfGet(EObject o, String property) {
		for (EReference r : o.eClass().getEAllReferences()) {
			if (r.getName().equalsIgnoreCase(property)) {
				return o.eGet(r);
			}
		}
		return null;
	}'''
}

private def String generateAddStepMethods() {
	'''
	
	
	@Override
	public void retroAddStep(String stepRule, Map<String, Object> params) {
		addStep(stepRule, params, this.getTraceSize()-2);
		
	}

	
	@Override
	public void addStep(String stepRule, Map<String, Object> params) {
		addStep(stepRule, params, this.getTraceSize()-1);
	}
	
	«««TODO how to get the parameters of the operation call? Not possible with current gemoc
	private void addStep(String stepRule, Map<String, Object> params, int stateIndex) {
		
		«getEClassFQN(traceability.traceMMExplorer.stepClass)» toPush = null;
		
		if (stateIndex >= 0) {
		
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» state = this.traceRoot.getStatesTrace().get(stateIndex);
		
		«val stepRules = traceability.mmext.rules»
		«IF !stepRules.empty»
		
			
		«FOR stepRule : stepRules SEPARATOR "else"»
		
			«val EClass stepClass = traceability.getStepClassFromStepRule(stepRule)»
			«val String varName = stepClass.name.toFirstLower.replace(" ", "") + "Instance"»
			
			if (stepRule.equalsIgnoreCase("«getBaseFQN(stepRule)»")) {
				
					
			// First we create the step
			«getEClassFQN(stepClass)» «varName» = «stringCreate(stepClass)»;
			«varName».«stringSetter(TraceMMStrings.ref_StepToState_starting, "state")»;
			
			if (!context.isEmpty() && context.getFirst() != null){
				emfAdd(context.getFirst(), "«StepStrings.ref_BigStepToSub»", «varName»);
			} else {
					traceRoot.getRootSteps().add(«varName»);
			}
			toPush = «varName»;
			«val properties = stepClass.EAllStructuralFeatures.filter[f|
			!traceability.traceMMExplorer.smallStepClass.EStructuralFeatures.contains(f) &&
				!traceability.traceMMExplorer.bigStepClass.EStructuralFeatures.contains(f) &&
				!traceability.traceMMExplorer.stepClass.EStructuralFeatures.contains(f) &&
				!f.name.equals(StepStrings.ref_BigStepToSub)]»
			«IF !properties.empty»
			if (params != null) {
				for (String k : params.keySet()) {
					
					switch(k) {
					«FOR p : properties»
					case "«p.name»":
						Object «uniqueVar("v")» = params.get(k);
						«val type = getEventParamRuntimeType(p)»
						if («uniqueVar("v")» instanceof «getFQN(type)»)
							«IF type == p.EType»
							«varName».«stringSetter(p, "(" + getFQN(p.EType) + ")"+uniqueVar("v"))»;
							«ELSE»
							«varName».«stringSetter(p, "(" + getFQN(p.EType) + ")exeToTraced.get("+uniqueVar("v"+")"))»;
							«ENDIF»
					
						break;
					
					«incVar("v")»
					«ENDFOR»
						}
				}
			}
			«ENDIF»

			// Then we add it to its trace
			this.traceRoot.«stringGetter(traceability.getStepSequence(stepClass))».add(«varName»);
			}
			«ENDFOR»
		}
		
		«ENDIF»
		
		
		context.push(toPush);
		
	}


	@Override
	public void endStep(String stepRule, Object returnValue) {
		«getEClassFQN(traceability.traceMMExplorer.stepClass)» popped = context.pop();
		if (popped != null)
			popped.«stringSetter(TraceMMStrings.ref_StepToState_ending, "lastState")»;
	}'''
}

	private def String generateInitAndSaveTraceMethods() {
		'''
		
	@Override
	public void initTrace() {
		// Create root
		this.traceRoot = «stringCreate(traceability.traceMMExplorer.traceClass)»;
		
		// Put in the resource
		traceResource.getContents().add(traceRoot);

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
		'''
	}
	
	private def String generateGetDescriptionMethods() {
		'''
		
	@Override
	public String getDescriptionOfExecutionState(int index) {
		StringBuilder result = new StringBuilder();
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» gs = traceRoot.«stringGetter(
			TraceMMStrings.ref_TraceToStates)».get(index);
		
		«FOR p : traceability.allMutableProperties» 
		«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		if (!gs.«stringGetter(refGlobalToState)».isEmpty())
			result.append("\n«p.name.toFirstUpper» values:");
		for («getEClassFQN(stateClass)» currenState : gs.«stringGetter(refGlobalToState)») {
			result.append("\n\t" + currenState.«stringGetter(p)»);
		}
		«ENDFOR»
		
	
		if (!gs.«stringGetter(TraceMMStrings.ref_StateToStep_started)».isEmpty()) {
			result.append("\n\nStarting steps: ");
			for («getEClassFQN(traceability.traceMMExplorer.stepClass)» m : gs.«stringGetter(
			TraceMMStrings.ref_StateToStep_started)») {
				result.append("\n\t" + m.eClass().getName());
				if (m.«stringGetter(TraceMMStrings.ref_StepToState_ending)» != null) {
					result.append(" (ends at state "+ traceRoot.getStatesTrace().indexOf(m.«stringGetter(TraceMMStrings.ref_StepToState_ending)») +")");
				}
			}
		}
		
		result.deleteCharAt(0);
		return result.toString();
	}
	
		@Override
	public String getDescriptionOfValue(EObject eObject) {
		«FOR p : traceability.allMutableProperties SEPARATOR " else " AFTER " else "»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		if (eObject instanceof «getEClassFQN(stateClass)») {
			return "«getEClassFQN(stateClass)»: "+ ((«getEClassFQN(stateClass)»)eObject).«stringGetter(p)»;			
		}
		«ENDFOR»
		return "ERROR";
	}
	
		'''
	}
	
	private def String generateStateQueryMethods() {
		'''
	@Override
	public EObject getExecutionState(int index) {
		return traceRoot.«stringGetter(TraceMMStrings.ref_TraceToStates)».get(index);
	}


	@Override
	public int getTraceSize() {
		return traceRoot.«stringGetter(TraceMMStrings.ref_TraceToStates)».size();
	}
	
	
		
	@Override
	public int getNumberOfValueTraces() {
		return getAllValueTraces().size();
	}
	
	@Override
	public Set<EObject> getAllCurrentValues(int stateIndex) {
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» currentState = this.traceRoot.getStatesTrace().get(stateIndex);
		// We find all current values
		Set<EObject> currentValues = new HashSet<EObject>();
		if (currentState != null) {
			«FOR p : traceability.allMutableProperties»
			«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
			currentValues.addAll(currentState.«stringGetter(refGlobalToState)»);
			«ENDFOR»
		}
		return currentValues;
	}
	
	public List<IValueTrace> getAllValueTraces() {
		return traces;
	}


	@Override
	public int getStateIndex(EObject state) {
		return traceRoot.getStatesTrace().indexOf(state);
	}'''
	
	
	
	}
	
	
	
	private def String generateStepQueryMethods() {
		'''
	@Override
	public boolean isBigStep(String string) {
		return bigSteps.contains(string);
	}
	
	@Override
	public String currentBigStep() {
		if(!context.isEmpty() && context.getFirst() != null)
			return context.getFirst().eClass().getName();
		else
			return null;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<fr.inria.diverse.trace.api.IStep> getStackForwardAfterState(int stateIndex) {
		List<fr.inria.diverse.trace.api.IStep> result = new ArrayList<fr.inria.diverse.trace.api.IStep>();
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» currentState = this.traceRoot.getStatesTrace().get(stateIndex);

		// We start at the top of the forward stack, and we go downward
		«getEClassFQN(traceability.traceMMExplorer.stepClass)» itStep = currentState.getStartedSteps().get(0);
		while (itStep != null) {
			«getEClassFQN(traceability.traceMMExplorer.stepClass)» itStep_prev = itStep;
			if (itStep instanceof «getEClassFQN(traceability.traceMMExplorer.stepClass)») {
				result.add(createGenericStep((«getEClassFQN(traceability.traceMMExplorer.stepClass)») itStep));
				List<«getEClassFQN(traceability.traceMMExplorer.stepClass)»> subSteps = (List<«getEClassFQN(traceability.traceMMExplorer.stepClass)»>) emfGet(itStep,
						"subSteps");
				if (subSteps != null) {
					itStep = subSteps.get(0);
				}
			}

			// If we didn't find anything new, we stop
			if (itStep == itStep_prev)
				itStep = null;
		}
		return result;
	}

	@Override
	public List<fr.inria.diverse.trace.api.IStep> getStackForwardBeforeState(int stateIndex) {
		LinkedList<fr.inria.diverse.trace.api.IStep> result = new LinkedList<fr.inria.diverse.trace.api.IStep>();
		«getEClassFQN(traceability.traceMMExplorer.getStateClass)» currentState = this.traceRoot.getStatesTrace().get(stateIndex);

		// We start at the top of the top of the forward stack, and we go upward
		EObject itStep = currentState.getStartedSteps().get(0).eContainer();
		while (itStep != null) {
			if (itStep instanceof «getEClassFQN(traceability.traceMMExplorer.stepClass)») {
				result.addFirst(createGenericStep((«getEClassFQN(traceability.traceMMExplorer.stepClass)») itStep));
				itStep = itStep.eContainer();
			} else {
				itStep = null;
			}

		}
		return result;
	}

	private fr.inria.diverse.trace.api.IStep createGenericStep(«getEClassFQN(traceability.traceMMExplorer.stepClass)» step) {
		fr.inria.diverse.trace.api.IStep result = null;
		
		«FOR Rule r : this.traceability.mmext.rules SEPARATOR "else" »
		«val stepClass = this.traceability.getStepClassFromStepRule(r)»
		if (step instanceof «getFQN(stepClass)») {
			«getFQN(stepClass)» step_cast =  («getFQN(stepClass)») step;
			result = new fr.inria.diverse.trace.api.impl.GenericStep("«getBaseFQN(r.containingClass)»", "«r.operation.name»");
			
			««« Handle caller object ("this"), if any
			«IF r.containingClass != null»
				result.addParameter("this", (step_cast.getThis()));
			«ENDIF»
			
			«FOR a : r.operation.EParameters»
				««« TODO
			«ENDFOR»
			
		}
		«ENDFOR»
		
		return result;
	}'''
	}
	
	private def String generateTraceManagerClass() {
		return '''package «packageQN»;
		
		«generateImports»

public class «className» implements ITraceManager {

	«generateFields»
	«generateConstructor»
	«generateAddStateMethods»
	«generateAddStepMethods»
	«generateGoToMethods»
	«generateInitAndSaveTraceMethods»
	«generateGetDescriptionMethods»
	«generateStoreAsTracedMethods»	
	«generateExeToFromTracedGenericMethods»
	«generateGenericEMFHelperMethods»
	«generateStateQueryMethods»
	«generateStepQueryMethods»
}
		'''
	}

}
