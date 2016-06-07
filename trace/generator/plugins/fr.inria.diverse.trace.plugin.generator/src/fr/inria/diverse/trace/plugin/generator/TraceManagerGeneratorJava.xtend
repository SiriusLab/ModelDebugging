package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import ecorext.Rule
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.commons.tracemetamodel.StepStrings
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStrings
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import fr.inria.diverse.trace.commons.model.trace.TracePackage

class TraceManagerGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage abstractSyntax
	private val TraceMMGenerationTraceability traceability
	private val Set<GenPackage> refGenPackages
	private val boolean gemoc
	private val boolean partialTraceManagement
	
	// Transient
	private boolean getExeToTracedUsed = false
	private boolean getTracedToExeUsed = false
	
	
	// Shortcuts
	private val EClass stateClass

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, TraceMMGenerationTraceability traceability,
		Set<GenPackage> refGenPackages, boolean gemoc, EPackage abstractSyntax, boolean partialTraceManagement) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "Manager"
		this.packageQN = packageQN
		this.traceability = traceability
		this.refGenPackages = refGenPackages
		this.gemoc = gemoc
		this.abstractSyntax = abstractSyntax
		stateClass = traceability.traceMMExplorer.stateClass
		this.partialTraceManagement=partialTraceManagement
	}

	private def String getActualFQN(EClass c, Rule r) {
		val EOperation o = r.operation
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
	}
	
	private def String getFQN(EStructuralFeature eFeature) {
		return EcoreCraftingUtil.getBaseFQN(eFeature.EContainingClass) + "." + eFeature.name
	}

	private static def boolean isNotSuperTypeOf(EClass c, Collection<EClass> eclasses) {
		for (eclass : eclasses) {
			if (eclass.EAllSuperTypes.contains(c))
				return false
		}
		return true
	}
	
	private def String getTracedJavaFQN(EClassifier c) {
		return getTracedJavaFQN(c,false)
	}
	
	private def String getTracedJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		if (c instanceof EClass) {
			val tracedClass = traceability.getTracedClass(c)
			if (tracedClass != null)
				return getJavaFQN(traceability.getTracedClass(c),enforcePrimitiveJavaClass)
			else
				return getJavaFQN(c,enforcePrimitiveJavaClass)
		} else {
			return getJavaFQN(c,enforcePrimitiveJavaClass)
		}
	}
	
	private def String getJavaFQN(EClassifier c) {
		return getJavaFQN(c,false)
	}
	
	private def String getJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		return EcoreCraftingUtil.getJavaFQN(c,refGenPackages,enforcePrimitiveJavaClass)
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
	
	/*
	private  def String getEOperationGetCode (Rule r) {
		val o = r.operation
		val eclass = r.containingClass
		val epackage = eclass.EPackage
		val res = '''«getJavaFQN(epackage)».«epackage.name.toFirstUpper»Package.eINSTANCE.get«eclass.name»__«o.name.toFirstUpper»()'''
		return res
	}
*/
 
	

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
	
	public static def String getBaseFQN(Rule r) {
		val EOperation o = r.operation
		val EClass c = r.containingClass
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
	}

	private def EClassifier getEventParamRuntimeType(EStructuralFeature f) {
		var EClass res = null
		if (f instanceof EAttribute) {
			// TODO
		} else if (f instanceof EReference) {
			val potentialRealRuntimeClass = traceability.getRealMutableClass(f.EReferenceType)
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
			return '''((«getJavaFQN(traceability.getTracedClass(p.EType as EClass))»)exeToTraced.get(«javaVarName».«EcoreCraftingUtil.stringGetter(
				p)»))'''
		else
			return javaVarName + "." + EcoreCraftingUtil.stringGetter(p)
	}

	private def String stringGetterExeValue(String javaVarName, EStructuralFeature p) {
		return '''
		«IF (p instanceof EReference && traceability.hasTracedClass(p.EType as EClass))»
		
		««« If many elements are in this fields, we have to cast the element with a collection
		«IF p.many»
		(Collection<? extends «getJavaFQN(p.EType,true)»>) 
		«ELSE»
		(«getJavaFQN(p.EType, true)»)
		«ENDIF»
		«getTracedToExeMethodName»(«javaVarName».«EcoreCraftingUtil.stringGetter(p)»)
		«ELSE»
		«javaVarName».«EcoreCraftingUtil.stringGetter(p)»
		«ENDIF»'''
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
		return '''
import fr.inria.diverse.trace.api.IValueTrace;
import fr.inria.diverse.trace.api.impl.GenericValueTrace;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
«««import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
		'''
	}
	
	private def String generateFields() {
		return '''
		
	private  «getJavaFQN(traceability.traceMMExplorer.getSpecificTraceClass)» traceRoot;
	private fr.inria.diverse.trace.commons.model.trace.MSEModel mseModel;
	private  Resource executedModel;
	
	««« TODO one map per type? So that we can completely stop manipulationg eobjects
	private  Map<EObject, EObject> exeToTraced;
	
	private  «getJavaFQN(traceability.traceMMExplorer.getStateClass)» lastState;
	private List<IValueTrace> traces;

	private Resource traceResource;
	private Deque<«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)»> context = new LinkedList<«getJavaFQN(
			traceability.traceMMExplorer.getSpecificStepClass)»>();
		'''
	}
	
	private def String generateConstructor() {
		return '''
	public «className» (Resource exeModel, Resource traceResource) {
		this.traceResource = traceResource;
		this.executedModel = exeModel;
		this.traces = new ArrayList<IValueTrace>();
	}'''
	}

	
	private def String getExeToTracedMethodName() {
		getExeToTracedUsed = true
		return "getExeToTraced"
	}
	
	private def String getTracedToExeMethodName() {
		getTracedToExeUsed = true
		return "getTracedToExe"
	}
	

	private def String generateExeToFromTracedGenericMethods() {
		return '''
		«IF getExeToTracedUsed»
		private Collection<? extends EObject> «getExeToTracedMethodName»(Collection<? extends EObject> exeObjects) {
		Collection<EObject> result = new ArrayList<EObject>();
		for(EObject exeObject : exeObjects) {
			storeAsTracedObject(exeObject);
			result.add(exeToTraced.get(exeObject));
		}
		return result;
		}	
	«ENDIF»
	
	«IF getTracedToExeUsed»
	private Collection<? extends EObject> «getTracedToExeMethodName»(
			Collection<? extends EObject> tracedObjects) {
		Collection<EObject> result = new ArrayList<EObject>();
		for (EObject tracedObject : tracedObjects) {
			result.add(«getTracedToExeMethodName»(tracedObject));
		}
		return result;
	}

	private EObject «getTracedToExeMethodName»(EObject tracedObject) {
		for (EObject key : exeToTraced.keySet()) {
			if (exeToTraced.get(key) == tracedObject)
				return key;
		}
		return null;
	}
	«ENDIF»
		'''
	}
	
	private def String generateStoreAsTracedMethods() {
		return '''    «FOR mutClass : traceability.allMutableClasses.filter[c|!c.isAbstract].sortBy[name]»

private void storeAsTracedObject(«getJavaFQN(mutClass)» o) {
			«val traced = traceability.getTracedClass(mutClass)»
		
			// First we find the traced object, and we create it if required
			«getJavaFQN(traced)» tracedObject;
			if (!exeToTraced.containsKey(o)) {
			tracedObject = «EcoreCraftingUtil.stringCreate(traced)»; 
			«val Set<EReference> origRefs1 = traceability.getRefs_originalObject(traced)»
			«FOR EReference origRef : origRefs1.sortBy[name]» 
			tracedObject.«EcoreCraftingUtil.stringSetter(origRef, "o")»;
			«ENDFOR»
			exeToTraced.put(o, tracedObject);
			traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createTraceClassToTracedClass(traced))».add(tracedObject);
			
			«FOR p : getAllMutablePropertiesOf(mutClass).sortBy[FQN]»
			«val EReference ptrace = traceability.getTraceOf(p)»
			traces.add(new GenericValueTrace(tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)», this));
			«ENDFOR»
		}
	}
	
    «ENDFOR»
«IF getExeToTracedUsed»
private void storeAsTracedObject(EObject o) {
 «FOR mutClass : partialOrderSort(traceability.allMutableClasses.filter[c|!c.isAbstract].toList.sortBy[name]) SEPARATOR "\n else "»
if (o instanceof «getJavaFQN(mutClass)») {
	storeAsTracedObject((«getJavaFQN(mutClass)»)o);
}
«ENDFOR»
}
«ENDIF»
'''
	}
	
	private def String stringFeatureID(EStructuralFeature p) {
		val containingClass = if (p.eContainer instanceof EClass) p.eContainer as EClassifier else (p.eContainer as ClassExtension).extendedExistingClass
		return EcoreCraftingUtil.stringFeatureID(p,containingClass,refGenPackages)
	}
	
	private def String generateGetAllResourcesMethod() {
		return '''
		
		private Set<Resource> getAllExecutedModelResources() {
		Set<Resource> allResources = new HashSet<>();
		allResources.add(executedModel);
		«IF gemoc»
		allResources.addAll(org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(executedModel));
		«ENDIF»
		return allResources;
		}
		
		'''
	}
	
	
	private def String generateAddInitialStateMethod() {
		return '''
		
		private void addInitialState() {
			if (lastState == null) {
		// Creation of the initial state
		Set<Resource> allResources = getAllExecutedModelResources();
		lastState =  «EcoreCraftingUtil.stringCreate(stateClass)»;
		for (Resource r : allResources) {
			for (TreeIterator<EObject> i = r.getAllContents(); i.hasNext();) {
				EObject o = i.next();
					«FOR c : partialOrderSort(getAllMutableClasses.filter[c|!c.isAbstract].sortBy[name].toList) SEPARATOR "else"»
						
						if (o instanceof «getJavaFQN(c)») {
							«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;
							addNewObjectToState(o_cast, lastState);
						}
					«ENDFOR»
			}
		}
		this.traceRoot.getStatesTrace().add(lastState);
		}}
		'''
	}
	
	private def String generateAddNewObjectToStateMethods() {
		val stateClass = traceability.traceMMExplorer.stateClass
		return '''
		
					«FOR c : partialOrderSort(getAllMutableClasses.filter[c|!c.isAbstract].sortBy[name].toList)»
					«val traced = traceability.getTracedClass(c)»
						
						«IF getAllMutablePropertiesOf(c).exists[p|p instanceof EReference && p.many]»
						@SuppressWarnings("unchecked")
						«ENDIF»
						private void addNewObjectToState(«getJavaFQN(c)» o_cast, «getJavaFQN(stateClass)» newState) {
						storeAsTracedObject(o_cast);
						«getJavaFQN(traced)» traced = («getJavaFQN(traced)») exeToTraced.get(o_cast);
						
						«FOR p : getAllMutablePropertiesOf(c).sortBy[FQN]»
						«val EReference ptrace = traceability.getTraceOf(p)»
						«val EClass valueClass = ptrace.getEType as EClass»
						«val EReference pvalues = traceability.getStateClassToValueClass(p)»
						
						// Creation of the first value of the field «p.name»
						«IF traceability.allMutableClasses.contains(p.EType)»
						«IF p.many»
						for(«getJavaFQN(p.EType)» aValue : o_cast.«EcoreCraftingUtil.stringGetter(p)») {
							storeAsTracedObject(aValue);
						}
						«ELSE»
						storeAsTracedObject(o_cast.«EcoreCraftingUtil.stringGetter(p)»);
						«ENDIF»			
						«ENDIF»
						«getJavaFQN(valueClass)» firstValue_«p.name» = «EcoreCraftingUtil.stringCreate(valueClass)»;
						«IF p.many»
						«IF p instanceof EReference»
						firstValue_«p.name».«EcoreCraftingUtil.stringGetter(p)».addAll
							((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) «getExeToTracedMethodName»(o_cast.«EcoreCraftingUtil.stringGetter(p)»));
						«ELSE»
						firstValue_«p.name».«EcoreCraftingUtil.stringGetter(p)».addAll
							((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) o_cast.«EcoreCraftingUtil.stringGetter(p)»);
						«ENDIF»
						«ELSE»
						firstValue_«p.name».«EcoreCraftingUtil.stringSetter(p,stringGetterTracedValue("o_cast", p))»;
						«ENDIF»
						traced.«EcoreCraftingUtil.stringGetter(ptrace)».add(firstValue_«p.name»);
						newState.«EcoreCraftingUtil.stringGetter(pvalues)».add(firstValue_«p.name»);
						«ENDFOR»
					}
					«ENDFOR»
			
				
		
		'''
	}

private def String generateAddStateUsingListenerMethods() {
	
	val stateClass = traceability.traceMMExplorer.stateClass
	val newClassesNotEmpty = partialOrderSort(traceability.getNewClasses.filter[c|!c.EStructuralFeatures.empty].toList)
	val allConcreteMutableClasses = partialOrderSort(getAllMutableClasses.filter[c|!c.isAbstract].sortBy[name].toList)
	val mutableClassesWithNonCollectionMutableFields = allConcreteMutableClasses.filter[c|getAllMutablePropertiesOf(c).exists[p|!p.many]]
	val mutableClassesWithCollectionMutableFields    = allConcreteMutableClasses.filter[c|getAllMutablePropertiesOf(c).exists[p| p.many]]
	return '''
	
	private «getJavaFQN(stateClass)» copyState(«getJavaFQN(stateClass)»  oldState) {
		«getJavaFQN(stateClass)» newState =  «EcoreCraftingUtil.stringCreate(stateClass)»;
		«FOR c : getAllMutableClasses.filter[c|!c.isAbstract].sortBy[name].toList»
		«FOR p : getAllMutablePropertiesOf(c).sortBy[FQN]»
		newState.«EcoreCraftingUtil.stringGetter(traceability.getStateClassToValueClass(p))».addAll(oldState.«EcoreCraftingUtil.stringGetter(traceability.getStateClassToValueClass(p))»);
		«ENDFOR»
		«ENDFOR»
		return newState;
	}
	
	@Override
	public void addState(Set<org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange> changes) {


		if (lastState == null) {
			addInitialState();
		} ««« end if laststate null


		if (!changes.isEmpty()) {
			
			boolean stateChanged = false;
			
			// We start by a (shallow) copy of the last state
			// But we will have to rollback a little by replacing values that changed
			«getJavaFQN(stateClass)» newState = copyState(lastState);
		
			for (org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange modelChange : changes) {
				EObject o = modelChange.getChangedObject();
			
			
			
				«IF !newClassesNotEmpty.empty»
				// We only look at constructable objects that have mutable fields
				// Here we have nothing to rollback, just a new object to add
				if (modelChange instanceof org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NewObjectModelChange) {
					stateChanged = true;
					
					««« Loop over all classes that may be constructed and that have mutable fields
					«FOR c : newClassesNotEmpty»
					if (o instanceof «getJavaFQN(c)») {
						«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;
						addNewObjectToState(o_cast, newState);
					} ««« end if instanceof
					«ENDFOR»
			
				} ««« end if NewObjectModelChange
				
				
				
				// We only look at constructable objects that have mutable fields
				// Here we must rollback to remove the values of the removed object from the copied state
				else if (modelChange instanceof org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.RemovedObjectModelChange) {
					stateChanged = true;
					
					««« Loop over all classes that may be constructed and that have mutable fields
					«FOR c : newClassesNotEmpty»
					«val traced = traceability.getTracedClass(c)»
					if (o instanceof «getJavaFQN(c)») {
						«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;
						«getJavaFQN(traced)» traced = («getJavaFQN(traced)») exeToTraced.get(o_cast);
						
						««« Loop over the fields of this class, which are all mutable
						«FOR p : c.EStructuralFeatures»
						«val EReference ptrace = traceability.getTraceOf(p)»
						«val EReference pvalues = traceability.getStateClassToValueClass(p)»
						newState.«EcoreCraftingUtil.stringGetter(pvalues)».remove(traced.«EcoreCraftingUtil.stringGetter(ptrace)».get(traced.«EcoreCraftingUtil.stringGetter(ptrace)».size()-1));
						«ENDFOR»
					} ««« end if instanceof
					«ENDFOR»
				} ««« end if RemovedObjectModelChange
				«ENDIF»
				
				

				«IF !mutableClassesWithNonCollectionMutableFields.empty»
				// Here we must look at non-collection mutable fields
				// We must rollback the last values from the copied state, and add new values as well
				// ie. mix of remove and new
				«IF !newClassesNotEmpty.empty» else «ENDIF» if (modelChange instanceof org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NonCollectionFieldModelChange) {
					stateChanged = true;
					
					org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NonCollectionFieldModelChange modelChange_cast = (org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NonCollectionFieldModelChange) modelChange;
					«EStructuralFeature.canonicalName » p = modelChange_cast.getChangedField();
					
					
					«FOR c : mutableClassesWithNonCollectionMutableFields SEPARATOR "\n else "»
					«val nonCollectionMutableFields = getAllMutablePropertiesOf(c).filter[p|!p.many]»
					«val traced = traceability.getTracedClass(c)»
					
					if (o instanceof «getJavaFQN(c)») {
						
						«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;
						
						«FOR p : nonCollectionMutableFields »
						«val EReference ptrace = traceability.getTraceOf(p)»
						«val EClass valueClass = ptrace.getEType as EClass»
						«val EReference pvalues = traceability.getStateClassToValueClass(p)»
						
						if (p.getFeatureID() == «stringFeatureID(p)») {
							
							// Rollback: we remove the last value of this field from the new state
							«getJavaFQN(traced)» traced = («getJavaFQN(traced)») exeToTraced.get(o);
							«getJavaFQN(valueClass)» lastValue = traced.«EcoreCraftingUtil.stringGetter(ptrace)».get(traced.«EcoreCraftingUtil.stringGetter(ptrace)».size()-1);
							newState.«EcoreCraftingUtil.stringGetter(pvalues)».remove(lastValue);
							
							// And we create a proper new value
							«IF traceability.allMutableClasses.contains(p.EType)»
							storeAsTracedObject(o_cast.«EcoreCraftingUtil.stringGetter(p)»);			
							«ENDIF»
							«getJavaFQN(valueClass)» newValue = «EcoreCraftingUtil.stringCreate(valueClass)»;
							newValue.«EcoreCraftingUtil.stringSetter(p,stringGetterTracedValue("o_cast", p))»;
							traced.«EcoreCraftingUtil.stringGetter(ptrace)».add(newValue);
							newState.«EcoreCraftingUtil.stringGetter(pvalues)».add(newValue);
						} ««« end if feature id
						
						«ENDFOR»
					
					} ««« end if instance of
					«ENDFOR»
					

				} ««« end if NonCollectionFieldModelChange
				«ENDIF»
				
				
				«IF !mutableClassesWithCollectionMutableFields.empty»
				// Here we look at collection mutable fields
				// We must first manually find out if the collection changed...
				// If it changed we must rollback the last values from the copied state, and add new values as well
				«IF !newClassesNotEmpty.empty || !mutableClassesWithNonCollectionMutableFields.empty » else «ENDIF» if (modelChange instanceof org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.PotentialCollectionFieldModelChange) {
				org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.PotentialCollectionFieldModelChange modelChange_cast = (org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.PotentialCollectionFieldModelChange) modelChange;
					
					
					«EStructuralFeature.canonicalName » p = modelChange_cast.getChangedField();
	
					«FOR c : mutableClassesWithCollectionMutableFields SEPARATOR "\n else "»
					«val collectionMutableFields = getAllMutablePropertiesOf(c).filter[p|p.many]»
					«val traced = traceability.getTracedClass(c)»
					
				
					if (o instanceof «getJavaFQN(c)») {
						
						«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;
						«getJavaFQN(traced)» tracedObject = («getJavaFQN(traced)») exeToTraced.get(o_cast);
						
						«FOR p : collectionMutableFields »
						«val EReference ptrace = traceability.getTraceOf(p)»
						«val EClass valueClass = ptrace.getEType as EClass»
						«val EReference pvalues = traceability.getStateClassToValueClass(p)»
						
						
						if (p.getFeatureID() == «stringFeatureID(p)») {
							
							// We compare the last collection in the value sequence, and the current one in the potentially changed object
							List<«getJavaFQN(valueClass)»> valueSequence = tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)»;
							«getJavaFQN(valueClass)» previousValue = null;
							if (!valueSequence.isEmpty())
								previousValue = valueSequence.get(valueSequence.size() - 1);
						
							««« If instances of new class, we have to make sure that there are traced versions 
							«IF traceability.allMutableClasses.contains(p.EType)»
								for(«getJavaFQN(p.EType)» aValue : o_cast.«EcoreCraftingUtil.stringGetter(p)») {
									storeAsTracedObject(aValue);
								} ««« end for loop on values
							«ENDIF»
				
							boolean change = false;
							if (previousValue != null) {

								if (previousValue.«EcoreCraftingUtil.stringGetter(p)».size() == o_cast
										.«EcoreCraftingUtil.stringGetter(p)».size()) {
			
									««« We this is an ordered collection, we have to compare in the correct order
									«IF p.ordered»
									java.util.Iterator<«getJavaFQN(p.EType,true)»> it = o_cast.«EcoreCraftingUtil.stringGetter(p)».iterator();
									for («getTracedJavaFQN(p.EType,true)» aPreviousValue : previousValue
											.«EcoreCraftingUtil.stringGetter(p)») {
										«getJavaFQN(p.EType)» aCurrentValue = it.next();
										«IF p instanceof EReference»
										if (aPreviousValue != exeToTraced.get(aCurrentValue))
										«ELSE»
										if (!aPreviousValue.equals(aCurrentValue))
										«ENDIF»
										{
											change = true;
											break;
										}
									}
									
									««« Else we simply check that the content is the same
									«ELSE»	
									change = !previousValue.«EcoreCraftingUtil.stringGetter(p)».containsAll(«getExeToTracedMethodName»(o_cast.«EcoreCraftingUtil.stringGetter(
						p)»));
									«ENDIF»
									««« end case ordered
			
								} ««« end if same size 
								
								else {
									change = true;
								} ««« end else
								
				} ««« end if (previousValue != null) 
				
				else {
					change = true;
				} ««« end else
					
							
							
							
							if (change) {
								stateChanged = true;
								
								// Rollback: we remove the last value of this field from the new state
								«getJavaFQN(valueClass)» lastValue = tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)».get(tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)».size()-1);
								newState.«EcoreCraftingUtil.stringGetter(pvalues)».remove(lastValue);
							
								// And we create a proper new value							
								«getJavaFQN(valueClass)» newValue = «EcoreCraftingUtil.stringCreate(valueClass)»;
								«IF p.many»
								«IF p instanceof EReference»
								newValue.«EcoreCraftingUtil.stringGetter(p)».addAll
									((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) «getExeToTracedMethodName»(o_cast.«EcoreCraftingUtil.stringGetter(p)»));
								«ELSE»
								newValue.«EcoreCraftingUtil.stringGetter(p)».addAll
									((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) o_cast.«EcoreCraftingUtil.stringGetter(p)»);
								«ENDIF»
								«ELSE»
								newValue.«EcoreCraftingUtil.stringSetter(p,stringGetterTracedValue("o_cast", p))»;
								«ENDIF»
								tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)».add(newValue);
								newState.«EcoreCraftingUtil.stringGetter(pvalues)».add(newValue);
							} ««« end if change
							
						} ««« end if featureid
						
						«ENDFOR»
						} ««« end if instanceof
						
					«ENDFOR»
				} ««« end if PotentialCollectionFieldModelChange
			
			«ENDIF»
			
			} ««« end for all changes
			
			if (stateChanged) {
				final «getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» currentStep = context.peekFirst();
				if (currentStep != null && currentStep instanceof «getJavaFQN(TracePackage.eINSTANCE.bigStep)») {
					final «getJavaFQN(traceability.traceMMExplorer.stateClass)» startingState = lastState;
					final «getJavaFQN(traceability.traceMMExplorer.stateClass)» endingState = newState;
					addImplicitStep(currentStep, startingState, endingState);
				}
				
				lastState = newState;
				traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_TraceToStates)».add(lastState);
			} ««« end if (stateChanged)
			
		} ««« end if (!changes.isEmpty())
	} ««« end method
	
	'''
	
	}
	
	private def String generateAddStateMethods() { return	
	'''
	@Override
	public boolean addStateIfChanged() {
		return addState(true);
	}

	@Override
	public void addState() {
		addState(false);
	}
	
	private boolean addState(boolean onlyIfChange) {
		
		«getJavaFQN(traceability.traceMMExplorer.getStateClass)» newState = «EcoreCraftingUtil.stringCreate(
			traceability.traceMMExplorer.getStateClass)»;
		boolean changed = false;
		
		Set<Resource> allResources = getAllExecutedModelResources();

		// We look at each object instance of a class with mutable properties 
		// Each of these objects should eventually become a traced object
		for (Resource r : allResources)
		for (TreeIterator<EObject> i = r.getAllContents(); i.hasNext();){
			EObject o = i.next();
		
		
			
			«FOR c : partialOrderSort(getAllMutableClasses.filter[c|!c.isAbstract].sortBy[name].toList) SEPARATOR "\n else "»
			«val traced = traceability.getTracedClass(c)»

			/**
			 * Storing the state of a «getJavaFQN(c)» object
			 */
			if (o instanceof «getJavaFQN(c)») {

				«getJavaFQN(c)» o_cast = («getJavaFQN(c)») o;

				storeAsTracedObject(o_cast);
				
				«IF !getAllMutablePropertiesOf(c).empty»
					«getJavaFQN(traced)» tracedObject = («getJavaFQN(traced)») exeToTraced.get(o);
				«ENDIF»
				«FOR p : getAllMutablePropertiesOf(c).sortBy[FQN]»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass valueClass = ptrace.getEType as EClass»
				«incVar("valueSequence")»
				«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
				«incVar("previousValue")»
				«incVar("noChange")»


				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				««« TODO to change if we switch from refering the exeMM to refering the AS (as in the ECMFA paper) -> need to compare to refs to origobjs/tracedobj
				««« TODO handle collections of datatypes
				List<«getJavaFQN(valueClass)»> «uniqueVar("valueSequence")» = tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)»;
				«getJavaFQN(valueClass)» «uniqueVar("previousValue")» = null;
				if (!«uniqueVar("valueSequence")».isEmpty())
					«uniqueVar("previousValue")» = «uniqueVar("valueSequence")».get(«uniqueVar("valueSequence")».size() - 1);
				
				««« Case many
				«IF p.many»
				
					««« If instances of new class, we have to make sure that there are traced versions 
					«IF traceability.allMutableClasses.contains(p.EType)»
						
						for(«getJavaFQN(p.EType)» aValue : o_cast.«EcoreCraftingUtil.stringGetter(p)») {
							storeAsTracedObject(aValue);
						}
						
					«ENDIF»
				
				boolean «uniqueVar("noChange")»= true;
				if («uniqueVar("previousValue")» != null) {

					if («uniqueVar("previousValue")».«EcoreCraftingUtil.stringGetter(p)».size() == o_cast
							.«EcoreCraftingUtil.stringGetter(p)».size()) {

						««« We this is an ordered collection, we have to compare in the correct order
						«IF p.ordered»
						java.util.Iterator<«getJavaFQN(p.EType,true)»> it = o_cast.«EcoreCraftingUtil.stringGetter(p)».iterator();
						for («getTracedJavaFQN(p.EType,true)» aPreviousValue : «uniqueVar("previousValue")»
								.«EcoreCraftingUtil.stringGetter(p)») {
							«getJavaFQN(p.EType)» aCurrentValue = it.next();
							«IF p instanceof EReference»
							if (aPreviousValue != exeToTraced.get(aCurrentValue)) {
							«ELSE»
							if (!aPreviousValue.equals(aCurrentValue)) {
							«ENDIF»
								«uniqueVar("noChange")» = false;
								break;
							}
						}
						
						««« Else we simply check that the content is the same
						«ELSE»	
						«uniqueVar("noChange")» = «uniqueVar("previousValue")».«EcoreCraftingUtil.stringGetter(p)».containsAll(«getExeToTracedMethodName»(o_cast.«EcoreCraftingUtil.stringGetter(
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
					storeAsTracedObject(o_cast.«EcoreCraftingUtil.stringGetter(p)»);			
					«ENDIF»
					
					
					««« Getting the content of the field
					«incVar("content")»
					«««
					««« Case reference
					«IF p instanceof EReference»
					«getTracedJavaFQN(p.EType)» «uniqueVar("content")» = null;
					if (o_cast.«EcoreCraftingUtil.stringGetter(p)» != null)
						«uniqueVar("content")» = «stringGetterTracedValue("o_cast", p)»;
					«««
					««« Case datatype
					«ELSEIF p instanceof EAttribute»
						«getJavaFQN(p.EType)» «uniqueVar("content")» = o_cast.«EcoreCraftingUtil.stringGetter(p)»;
					«ENDIF»
					««« end declaring/getting content
					
					«IF getJavaFQN(p.EType) == "java.lang.Byte"
						|| getJavaFQN(p.EType) == "java.lang.Short"
						|| getJavaFQN(p.EType) == "java.lang.Integer"
						|| getJavaFQN(p.EType) == "java.lang.Long"
						|| getJavaFQN(p.EType) == "java.lang.Boolean"
						|| getJavaFQN(p.EType) == "java.lang.Float"
						|| getJavaFQN(p.EType) == "java.lang.Double"
						|| getJavaFQN(p.EType) == "java.lang.String"»
						boolean «uniqueVar("noChange")» = «uniqueVar("previousValue")» != null
							&& «uniqueVar("previousValue")».«EcoreCraftingUtil.stringGetter(p)» != null
							&& «uniqueVar("previousValue")».«EcoreCraftingUtil.stringGetter(p)».equals(«uniqueVar("content")»);
					«ELSE»
						boolean «uniqueVar("noChange")» = «uniqueVar("previousValue")» != null
							&& «uniqueVar("previousValue")».«EcoreCraftingUtil.stringGetter(p)» == «uniqueVar("content")»;
					«ENDIF»
					 
						
						
					
				«ENDIF»
				««« end collection/single
					
					
					
				if («uniqueVar("noChange")») {
					newState.«EcoreCraftingUtil.stringGetter(refGlobalToState)».add(«uniqueVar("previousValue")»);

				} // Else we create one
				else {
					changed = true;
					«getJavaFQN(valueClass)» newValue = «EcoreCraftingUtil.stringCreate(valueClass)»;
					
					
					
					««« Case collection
					««« TODO: handle collections of datatypes!
					«IF p.many»
					
					«IF p instanceof EReference»
						newValue.«EcoreCraftingUtil.stringGetter(p)».addAll
							((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) «getExeToTracedMethodName»(o_cast.«EcoreCraftingUtil.stringGetter(p)»));
						«ELSE»
						newValue.«EcoreCraftingUtil.stringGetter(p)».addAll
							((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) o_cast.«EcoreCraftingUtil.stringGetter(p)»);
						«ENDIF»
								
					««« Case single
					«ELSE»
					
						newValue.«EcoreCraftingUtil.stringSetter(p, uniqueVar("content"))»;
					
									
					«ENDIF»
					««« end collection/Single
					
					tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)».add(newValue);
					newState.«EcoreCraftingUtil.stringGetter(refGlobalToState)».add(newValue);
				}
				
				«ENDFOR»
				}
			«ENDFOR»
			}
			
			boolean createNewState = lastState == null || (!onlyIfChange || changed);
			if (createNewState) {
				
				final «getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» currentStep = context.peekFirst();
				if (currentStep != null && currentStep instanceof «getJavaFQN(TracePackage.eINSTANCE.bigStep)») {
					final «getJavaFQN(traceability.traceMMExplorer.stateClass)» startingState = lastState;
					final «getJavaFQN(traceability.traceMMExplorer.stateClass)» endingState = newState;
					addImplicitStep(currentStep, startingState, endingState);
				}
				
				lastState = newState;
				traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_TraceToStates)».add(lastState);
			}
			
			// Undoing the new state created for nothing
			else {
			
			newState.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_StateToStep_started)».clear();
			newState.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_StateToStep_ended)».clear();	
			
			«FOR p : traceability.allMutableProperties.sortBy[FQN]»
			«val EReference tuple = traceability.getStateClassToValueClass(p)»
			newState.«EcoreCraftingUtil.stringGetter(tuple)».clear();
			«ENDFOR»
			}
			
			return createNewState;
			
	}'''
}

private def String generateGoToMethods() {	
	return '''
	@Override
	public void goTo(EObject state) {
		
		if (state instanceof «getJavaFQN(traceability.traceMMExplorer.stateClass)») {
			«getJavaFQN(traceability.traceMMExplorer.stateClass)» stateToGo = («getJavaFQN(
			traceability.traceMMExplorer.stateClass)») state;

		«FOR p : traceability.allMutableProperties.sortBy[FQN]»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		for («getJavaFQN(stateClass)» value : stateToGo.«EcoreCraftingUtil.stringGetter(
			TraceMMStrings.ref_createGlobalToState(stateClass))») {
				
				
		««« Case in which we can use the "originalObject" reference and simply set its values
		«IF p.eContainer instanceof ClassExtension»
			
			««« We have to test at runtime because we can't know at design time the type of the object containing the property
			««« The reason is that we keep the same class hierarchy in the trace. Maybe we should remove that. 
			«FOR concreteSubType : getConcreteSubtypesTraceClassOf(ptrace.getEContainingClass).sortBy[name]»
			if (value.«EcoreCraftingUtil.stringGetter("parent")» instanceof «getJavaFQN(concreteSubType)») {
				«val Collection<EReference> origRefs = traceability.getRefs_originalObject(concreteSubType).sortBy[name]»
				«getJavaFQN(concreteSubType)» parent_cast = («getJavaFQN(concreteSubType)») value.«EcoreCraftingUtil.stringGetter("parent")»;
				«IF !origRefs.isEmpty»
					«val EReference origRef = origRefs.get(0)»
					«IF p.many»
						«EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(ptrace.getEContainingClass),refGenPackages)» originalObject = («EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(ptrace.getEContainingClass),refGenPackages)») parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»;
						originalObject.«EcoreCraftingUtil.stringGetter(p)».clear();
						originalObject.«EcoreCraftingUtil.stringGetter(p)».addAll(«stringGetterExeValue("value",p)»);
					«ELSE»
						«getJavaFQN(p.EType)» toset = «stringGetterExeValue("value", p)»;
						«getJavaFQN(p.EType)» current = ((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(
			origRef)»).«EcoreCraftingUtil.stringGetter(p)»;
						if (current != toset)
							((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringSetter(
			p, "toset")»;
					«ENDIF»
				«ENDIF»
					
			}
			«ENDFOR»
			
		««« Case in which we have to recreate/restore execution objects in the model
		«ELSEIF p.eContainer instanceof EClass»
			«getJavaFQN(p.EContainingClass)» exeObject = («getJavaFQN(p.EContainingClass)») «getTracedToExeMethodName»(value.getParent());
			«IF p.many»
				exeObject.«EcoreCraftingUtil.stringGetter(p)».clear();
				
				«IF p instanceof EReference»
				exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll
					((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) «getTracedToExeMethodName»(value.«EcoreCraftingUtil.stringGetter(p)»));
				«ELSE»
				exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll
					((Collection<? extends «getJavaFQN(p.EType,true)»>) value.«EcoreCraftingUtil.stringGetter(p)»);
				«ENDIF»
			«ELSE»
				exeObject.«EcoreCraftingUtil.stringSetter(p, stringGetterExeValue("value",p))»;
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
		«getJavaFQN(traceability.traceMMExplorer.getStateClass)» stateToGo = traceRoot.«EcoreCraftingUtil.stringGetter(
			TraceMMStrings.ref_TraceToStates)».get(stepNumber);
		goTo(stateToGo);
	}
	
	private void goToValue(EObject value) {
		Object states = emfGet(value, "states");
		if (states != null) {
			if (states instanceof List<?>) {
				// We get the first state in which this value existed
				Object state = ((List<?>) states).get(0);
				if (state instanceof «getJavaFQN(traceability.traceMMExplorer.getStateClass)») {
					goTo((«getJavaFQN(traceability.traceMMExplorer.getStateClass)») state);
				}
			}
		}
	}'''
}

private def String generateGenericEMFHelperMethods() {
	return '''
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
	return '''
	
	@Override
	public void addStep(String stepRule, Map<String, Object> params) {
		addStep(stepRule, params, this.getTraceSize()-1);
	}
	
	«««TODO how to get the parameters of the operation call? Not possible with current gemoc
	private void addStep(String stepRule, Map<String, Object> params, int stateIndex) {
		
		«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» toPush = null;
		
		if (stateIndex >= 0) {
		
		«getJavaFQN(traceability.traceMMExplorer.stateClass)» state = this.traceRoot.getStatesTrace().get(stateIndex);
		
		
		«val stepRules = traceability.mmext.rules»
		«IF !stepRules.empty»
		«FOR stepRule : stepRules.sortBy[baseFQN] SEPARATOR "else"»
			«val stepCallerClass = stepRule.containingClass»
			«val possibleCallerClasses = abstractSyntax.EClassifiers
				.filter[c|c instanceof EClass]
				.map[c|c as EClass]
				.filter[c|c.equals(stepCallerClass)||c.EAllSuperTypes.contains(stepCallerClass)]
				.toSet»
				
			«val EClass stepClass = traceability.getStepClassFromStepRule(stepRule)»
			«val String varName = stepClass.name.toFirstLower.replace(" ", "") + "Instance"»
			«IF possibleCallerClasses.empty»
			if (stepRule.equalsIgnoreCase("«getBaseFQN(stepRule)»")) {
			«ELSE»
			if (
			«FOR possibleCallerClass: possibleCallerClasses.sortBy[name] SEPARATOR " || "»
				stepRule.equalsIgnoreCase("«getActualFQN(possibleCallerClass, stepRule)»")
			«ENDFOR»
			) {
			«ENDIF»
			// First we create the step
			«getJavaFQN(stepClass)» «varName» = «EcoreCraftingUtil.stringCreate(stepClass)»;
			«varName».«EcoreCraftingUtil.stringSetter(TraceMMStrings.ref_StepToState_starting, "state")»;
			
			if (!context.isEmpty() && context.getFirst() != null){
				emfAdd(context.getFirst(), "«StepStrings.ref_BigStepToSub»", «varName»);
			} else {
				traceRoot.getRootStep().getSubSteps().add(«varName»);
			}
			toPush = «varName»;
			
			««« TODO if we want to use this method in the context of gemoc, need to fill the MSEOccurrence params with those from here
			«IF !gemoc»
			««« TODO rely on information in Rule instead of the structural features?
			«val properties = stepClass.EAllStructuralFeatures.filter[f|
			!TracePackage.eINSTANCE.smallStep.EStructuralFeatures.contains(f) &&
				!TracePackage.eINSTANCE.bigStep.EStructuralFeatures.contains(f) &&
				!traceability.traceMMExplorer.getSpecificStepClass.EStructuralFeatures.contains(f) &&
				!f.name.equals(StepStrings.ref_BigStepToSub)
				&& !f.EContainingClass.name.equals("MSEOccurrence")]»
			«IF !properties.empty»
			if (params != null) {
				for (String k : params.keySet()) {
					
					switch(k) {
					«FOR p : properties.sortBy[name]»
					case "«p.name»":
						Object «uniqueVar("v")» = params.get(k);
						«val type = getEventParamRuntimeType(p)»
						if («uniqueVar("v")» instanceof «getJavaFQN(type)»)
							«IF type == p.EType»
							«varName».«EcoreCraftingUtil.stringSetter(p, "(" + getJavaFQN(p.EType) + ")"+uniqueVar("v"))»;
							«ELSE»
							«varName».«EcoreCraftingUtil.stringSetter(p, "(" + getJavaFQN(p.EType) + ")exeToTraced.get("+uniqueVar("v"+")"))»;
							«ENDIF»
					
						break;
					
					«incVar("v")»
					«ENDFOR»
						}
				}
			}
			«ENDIF»
			«ENDIF»

			// Then we add it to its trace
			this.traceRoot.«EcoreCraftingUtil.stringGetter(traceability.getStepSequence(stepClass))».add(«varName»);
			}
			«ENDFOR»
		
		
		«ENDIF»
		
		}

		context.push(toPush);
		
	}
	
	private void addImplicitStep(«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» currentStep,
		«getJavaFQN(traceability.traceMMExplorer.stateClass)» startingState,
		«getJavaFQN(traceability.traceMMExplorer.stateClass)» endingState) {
		
		«IF !stepRules.empty && !traceability.bigStepClasses.empty»
			«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» implicitStep = null;
			«FOR bigStepClass : traceability.bigStepClasses.sortBy[name] SEPARATOR "else"»
				if (currentStep instanceof «getJavaFQN(bigStepClass)») {
					implicitStep = «EcoreCraftingUtil.stringCreateImplicitStep(bigStepClass)»;
				}
			«ENDFOR»
		if (implicitStep != null) {
			implicitStep.setStartingState(startingState);
			implicitStep.setEndingState(endingState);
			emfAdd(currentStep, "subSteps", implicitStep);
		}
		«ENDIF»
	}
	
	«IF gemoc»
	@Override
	public boolean addStep(fr.inria.diverse.trace.commons.model.trace.MSEOccurrence mseOccurrence) {
		
		«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» step = null;
		
		if (mseOccurrence != null && mseOccurrence instanceof «getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)») {
			
			step = («getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)») mseOccurrence;
	
			if (mseModel == null) {
				mseModel = fr.inria.diverse.trace.commons.model.trace.MseFactory.eINSTANCE.createMSEModel();
				traceResource.getContents().add(mseModel);
			}
			
			mseModel.getOwnedMSEs().add(step.getMseoccurrence().getMse());
	
			// Creating generic (or almost generic) links
			«getJavaFQN(traceability.traceMMExplorer.stateClass)» state = this.traceRoot.getStatesTrace().get(this.getTraceSize()-1);
			step.setStartingState(state);
			if (!context.isEmpty() && context.getFirst() != null) {
				emfAdd(context.getFirst(), "subSteps", step);
			} else {
				traceRoot.getRootStep().getSubSteps().add(step);
			}
			
			// Adding step in its dedicated sequence/dimension
			«IF !stepRules.empty»
			«FOR stepRule : stepRules.sortBy[baseFQN] SEPARATOR "else"»
				«val EClass stepClass = traceability.getStepClassFromStepRule(stepRule)»
				«val String varName = stepClass.name.toFirstLower.replace(" ", "") + "Instance"»
				if (step instanceof «getJavaFQN(stepClass)») {
					«getJavaFQN(stepClass)» «varName» = («getJavaFQN(stepClass)») step;
					this.traceRoot.«EcoreCraftingUtil.stringGetter(traceability.getStepSequence(stepClass))».add(«varName»);
				}
				«ENDFOR»
			«ENDIF»
		}
		context.push(step);
		
		return (step != null);
	}
	
	«ENDIF»


	@Override
	public void endStep(String stepRule, Object returnValue) {
		«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» popped = context.pop();
		if (popped != null)
			popped.«EcoreCraftingUtil.stringSetter(TraceMMStrings.ref_StepToState_ending, "lastState")»;
	}'''
}

	private def String generateInitAndSaveTraceMethods() {
		return '''
		
	@Override
	public void initTrace() {
		// Create root
		this.traceRoot = «EcoreCraftingUtil.stringCreate(traceability.traceMMExplorer.getSpecificTraceClass)»;
		
		// Put in the resource
		traceResource.getContents().add(traceRoot);

		// Initializing the map exeobject -> tracedobject
		this.exeToTraced = new HashMap<EObject, EObject>();
	}
	
	public void loadTrace(«getJavaFQN(traceability.traceMMExplorer.getSpecificTraceClass)» traceRoot) {
		this.traceRoot = traceRoot;
		this.exeToTraced = new HashMap<EObject, EObject>();
		«FOR mutClass : traceability.allMutableClasses.filter[c|!c.isAbstract].sortBy[name]»
			«val traced = traceability.getTracedClass(mutClass)»
			for («getJavaFQN(traced)» tracedObject : traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createTraceClassToTracedClass(traced))») {
			«FOR p : getAllMutablePropertiesOf(mutClass).sortBy[FQN]»
			«val EReference ptrace = traceability.getTraceOf(p)»
				traces.add(new GenericValueTrace(tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)», this));
			«ENDFOR»
			}
		«ENDFOR»
	}

	@Override
	public void save() {
		try {
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(URI uri) {
		try {
			traceResource.setURI(uri);
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
		'''
	}
	
	private def String generateGetDescriptionMethods() {
		return '''
		
	@Override
	public String getDescriptionOfExecutionState(int index) {
		StringBuilder result = new StringBuilder();
		«getJavaFQN(traceability.traceMMExplorer.getStateClass)» gs = traceRoot.«EcoreCraftingUtil.stringGetter(
			TraceMMStrings.ref_TraceToStates)».get(index);
		
		«FOR p : traceability.allMutableProperties.sortBy[FQN]»
		«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		
		if (!gs.«EcoreCraftingUtil.stringGetter(refGlobalToState)».isEmpty())
			result.append("\n«p.name.toFirstUpper» values:");
		for («getJavaFQN(stateClass)» currentState : gs.«EcoreCraftingUtil.stringGetter(refGlobalToState)») {
			«IF p.many»
				String d = "";
				List<«getTracedJavaFQN(p.EType,true)»> l = currentState.«EcoreCraftingUtil.stringGetter(p)»;
				int s = l.size();
				for (int i=0;i<s-1;i++) {
					d += l.get(i).toString() + ",\n\t\t";
				}
				if (s>0) {
					d += l.get(s-1).toString();
				}
				result.append("\n\t" + "["+d+"]");
			«ELSE»
				result.append("\n\t" + currentState.«EcoreCraftingUtil.stringGetter(p)»);
			«ENDIF»
		}
		«ENDFOR»
	
		if (!gs.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_StateToStep_started)».isEmpty()) {
			result.append("\n\nStarting steps: ");
			for («getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» m : gs.«EcoreCraftingUtil.stringGetter(
			TraceMMStrings.ref_StateToStep_started)») {
				result.append("\n\t" + m.eClass().getName());
				if (m.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_StepToState_ending)» != null) {
					result.append(" (ends at state "+ traceRoot.getStatesTrace().indexOf(m.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_StepToState_ending)») +")");
				}
			}
		}
		
		result.deleteCharAt(0);
		return result.toString();
	}
	
	@Override
	public String getDescriptionOfValue(EObject value) {
		«FOR p : traceability.allMutableProperties.sortBy[FQN] SEPARATOR " else " AFTER " else "»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		if (value instanceof «getJavaFQN(stateClass)») {
			return "«getJavaFQN(stateClass)»: "+ ((«getJavaFQN(stateClass)»)value).«EcoreCraftingUtil.stringGetter(p)»;
		}
		«ENDFOR»
		return "ERROR";
	}
	
	@Override
	public Object getContainedValue(EObject value) {
		«FOR p : traceability.allMutableProperties.sortBy[FQN] SEPARATOR " else " AFTER " else "»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»
		if (value instanceof «getJavaFQN(stateClass)») {
			return ((«getJavaFQN(stateClass)»)value).«EcoreCraftingUtil.stringGetter(p)»;
		}
		«ENDFOR»
		return null;
	}
	
		'''
	}
	
	private def String generateStateQueryMethods() {
		return '''
	@Override
	public EObject getExecutionState(int index) {
		return traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_TraceToStates)».get(index);
	}


	@Override
	public int getTraceSize() {
		return traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_TraceToStates)».size();
	}
	
	
		
	@Override
	public int getNumberOfValueTraces() {
		return traces.size();
	}
	
	@Override
	public Set<EObject> getAllCurrentValues(int stateIndex) {
		«getJavaFQN(traceability.traceMMExplorer.getStateClass)» currentState = this.traceRoot.getStatesTrace().get(stateIndex);
		// We find all current values
		Set<EObject> currentValues = new HashSet<EObject>();
		if (currentState != null) {
			«FOR p : traceability.allMutableProperties.sortBy[FQN]»
			«val EReference refGlobalToState = traceability.getStateClassToValueClass(p)»
			currentValues.addAll(currentState.«EcoreCraftingUtil.stringGetter(refGlobalToState)»);
			«ENDFOR»
		}
		return currentValues;
	}
	
	public List<IValueTrace> getAllValueTraces() {
		return traces;
	}

	@Override
	public int getStateOrValueIndex(EObject stateOrValue) {
		int idx = traceRoot.getStatesTrace().indexOf(stateOrValue);
		if (idx == -1) {
			final Object states = emfGet(stateOrValue, "states");
			if (states != null) {
				if (states instanceof List<?>) {
					// We get the first state in which this value existed
					Object valueState = ((List<?>) states).get(0);
					if (valueState instanceof «getJavaFQN(traceability.traceMMExplorer.getStateClass)») {
						idx = traceRoot.getStatesTrace().indexOf(valueState);
					}
				}
			}
		}
		return idx;
	}'''
	}
	
	private def String generateStepQueryMethods() {
		return '''
	@Override
	public String currentBigStep() {
		if(!context.isEmpty() && context.getFirst() != null)
			return context.getFirst().eClass().getName();
		else
			return null;
	}
	
	@Override
	public List<fr.inria.diverse.trace.api.IStep> getStepsForStates(
			int startingState, int endingState) {
		Predicate<fr.inria.diverse.trace.api.IStep> predicate = (s) -> {
			final int stepStartingState = s.getStartingIndex();
			final int stepEndingState = s.getEndingIndex();
			return (stepEndingState == -1 || stepEndingState >= startingState) && stepStartingState <= endingState;
		};
		return traceRoot.getRootStep().getSubSteps().stream()
				.map(s -> createLazyGenericStep(s, null, predicate))
				.filter(predicate)
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	private List<fr.inria.diverse.trace.api.IStep> generateSubSteps(«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» step,
			fr.inria.diverse.trace.api.IStep parent) {
		
		final List<«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)»> subSteps = (List<«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)»>) emfGet(step, "subSteps");
		final List<fr.inria.diverse.trace.api.IStep> result = new ArrayList<>();
		
		if (subSteps != null && !subSteps.isEmpty()) {
			for («getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» subStep : subSteps) {
				result.add(createLazyGenericStep(subStep,parent));
			}
		}
		
		return result;
	}
	
	private fr.inria.diverse.trace.api.IStep createLazyGenericStep(
			«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» step,
			fr.inria.diverse.trace.api.IStep parent) {
		return createLazyGenericStep(step, parent, null);
	}
	
	private fr.inria.diverse.trace.api.IStep createLazyGenericStep(«getJavaFQN(traceability.traceMMExplorer.getSpecificStepClass)» step, fr.inria.diverse.trace.api.IStep parent, Predicate<fr.inria.diverse.trace.api.IStep> predicate) {
		fr.inria.diverse.trace.api.IStep result = null;
		
		«FOR Rule r : this.traceability.mmext.rules.sortBy[baseFQN] SEPARATOR "else" »
		«val stepClass = this.traceability.getStepClassFromStepRule(r)»
		if (step instanceof «getJavaFQN(stepClass)») {
			«getJavaFQN(stepClass)» step_cast =  («getJavaFQN(stepClass)») step;
			int startIndex = this.traceRoot.getStatesTrace().indexOf(step.getStartingState());
			int endIndex = this.traceRoot.getStatesTrace().indexOf(step.getEndingState());
			
			result = new fr.inria.diverse.trace.api.impl.PartiallyLazyGenericStep("«getJavaFQN(r.containingClass)»", "«r.operation.name»", startIndex, endIndex, parent,(s)->generateSubSteps(step_cast,s),predicate);
			«IF r.containingClass != null»
				result.addParameter("caller", (step_cast.getCaller()));
			«ENDIF»
			result.addParameter("this", step);
			
			«FOR a : r.operation.EParameters»
				««« TODO
			«ENDFOR»
		}
		«ENDFOR»
		«FOR implicitStepClass : this.traceability.implicitStepClasses.sortBy[name]»
		else if (step instanceof «getJavaFQN(implicitStepClass)») {
			int startIndex = this.traceRoot.getStatesTrace().indexOf(step.getStartingState());
			int endIndex = this.traceRoot.getStatesTrace().indexOf(step.getEndingState());
			
			result = new fr.inria.diverse.trace.api.impl.GenericStep("«getJavaFQN(traceability.getImplicitStepContainingClass(implicitStepClass))»", "implicitStep",startIndex,endIndex,parent,new ArrayList<>());
			result.addParameter("this", step);
		}
		«ENDFOR»
		
		return result;
	}
	
	'''
	}	
	
	
	private def String generateIsPartialTraceManager() {
		return '''
		@Override
		public boolean isPartialTraceManager() {
			return «partialTraceManagement»;
		}
		'''
	}
	
	private def String generateSetTraceRoot() {
		return
				'''
					@Override
					public void setTraceRoot(EObject object) {
						if (object instanceof «getJavaFQN(traceability.traceMMExplorer.getSpecificTraceClass)») {
							traceRoot = («getJavaFQN(traceability.traceMMExplorer.getSpecificTraceClass)») object;
						}
					}
				'''
	}
	
	private def String generateTraceManagerClass() {
		return '''package «packageQN»;
		
		«generateImports»

public class «className» implements «IF gemoc» fr.inria.diverse.trace.gemoc.api.IGemocTraceManager «ELSE» ITraceManager «ENDIF»{

	«generateFields»
	«generateConstructor»
	«IF gemoc»
	«generateAddInitialStateMethod»
	«generateAddNewObjectToStateMethods»
	«generateAddStateUsingListenerMethods»
	«ELSE»
	«generateAddStateMethods»
	«ENDIF»
	«generateAddStepMethods»
	«generateGoToMethods»
	«generateInitAndSaveTraceMethods»
	«generateGetDescriptionMethods»
	«generateStoreAsTracedMethods»	
	«generateGenericEMFHelperMethods»
	«generateStateQueryMethods»
	«generateStepQueryMethods»
	«generateGetAllResourcesMethod»
	«generateExeToFromTracedGenericMethods»
	«generateIsPartialTraceManager»
	«generateSetTraceRoot»
}
		'''
	
	
	}

}
