package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import ecorext.Rule
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStrings
import java.util.Collection
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class TraceExplorerGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage abstractSyntax
	private val TraceMMGenerationTraceability traceability
	private val Set<GenPackage> refGenPackages
	
	private boolean getTracedToExeUsed = false
	
	// Shortcuts
	private val EClass stateClass
	private val EClass valueClass
	private val EClass specificStepClass
	
	private val String stateFQN
	private val String valueFQN
	private val String specificStepFQN
	private val String statesPackageFQN

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, TraceMMGenerationTraceability traceability,
		Set<GenPackage> refGenPackages, boolean gemoc, EPackage abstractSyntax, boolean partialTraceManagement) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "Explorer"
		this.packageQN = packageQN
		this.traceability = traceability
		this.refGenPackages = refGenPackages
		this.abstractSyntax = abstractSyntax
		stateClass = traceability.traceMMExplorer.stateClass
		valueClass = traceability.traceMMExplorer.valueClass
		statesPackageFQN = EcoreCraftingUtil.getBaseFQN(traceability.traceMMExplorer.statesPackage) + "." + traceability.traceMMExplorer.statesPackage.name.toFirstUpper + "Package"
		specificStepClass = traceability.traceMMExplorer.specificStepClass
		stateFQN = getJavaFQN(stateClass)
		valueFQN = getJavaFQN(valueClass)
		specificStepFQN = getJavaFQN(specificStepClass)
	}
	
	private def String getFQN(EStructuralFeature eFeature) {
		return EcoreCraftingUtil.getBaseFQN(eFeature.EContainingClass) + "." + eFeature.name
	}
	
	private def String getJavaFQN(EClassifier c) {
		return getJavaFQN(c,false)
	}
	
	private def String getJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		return EcoreCraftingUtil.getJavaFQN(c,refGenPackages,enforcePrimitiveJavaClass)
	}
	
	public def String generateCode() {
		val String code = generateTraceManagerClass()
		try {
			return CodeGenUtil.formatJavaCode(code)
		} catch (Throwable t) {
			return code
		}

	}
	
	public static def String getBaseFQN(Rule r) {
		val EOperation o = r.operation
		val EClass c = r.containingClass
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
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
	
	private def String getTracedToExeMethodName() {
		getTracedToExeUsed = true
		return "getTracedToExe"
	}

	private def String generateImports() {
		return
				'''
					import java.util.ArrayList;
					«IF getTracedToExeUsed»
					import java.util.Collection;
					«ENDIF»
					import java.util.Collections;
					import java.util.HashMap;
					import java.util.List;
					import java.util.Map;
					import java.util.function.Predicate;
					import java.util.stream.Collectors;
					
					import org.eclipse.emf.ecore.EObject;
					import org.eclipse.emf.ecore.resource.Resource;
					import org.eclipse.emf.transaction.RecordingCommand;
					import org.eclipse.emf.transaction.TransactionalEditingDomain;
					import org.eclipse.emf.transaction.util.TransactionUtil;
					import org.gemoc.executionframework.engine.core.CommandExecution;
					
					import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
					import fr.inria.diverse.trace.commons.model.trace.Step;
					import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
					import fr.inria.diverse.trace.gemoc.api.ITraceListener;
				'''
	}
	
	private def String generateFields() {
		return
				'''
					private «getJavaFQN(traceability.traceMMExplorer.specificTraceClass)» traceRoot;
					private Resource modelResource;
					private final Map<EObject, EObject> tracedToExe;
					
					private int lastJumpIndex = -1;
					private «stateFQN» currentState = null;
					final private List<Step> callStack = new ArrayList<>();
					
					final private List<List<? extends «valueFQN»>> valueTraces = new ArrayList<>();
					
					private List<«stateFQN»> statesTrace;
					
					private «specificStepFQN» stepIntoResult;
					private «specificStepFQN» stepOverResult;
					private «specificStepFQN» stepReturnResult;
					
					private «specificStepFQN» stepBackIntoResult;
					private «specificStepFQN» stepBackOverResult;
					private «specificStepFQN» stepBackOutResult;
					
					final private Map<«specificStepFQN», Integer> stepToStartingIndex = new HashMap<>();
					final private Map<«specificStepFQN», Integer> stepToEndingIndex = new HashMap<>();
					
					private final HashMap<Integer, Boolean> canBackValueCache = new HashMap<>();
					
					final private List<ITraceListener> listeners = new ArrayList<>();
				'''
	}
	
	private def String generateConstructors() {
		return
				'''
					public «className»(Map<EObject, EObject> tracedToExe) {
						this.tracedToExe = tracedToExe;
					}
					
					public «className»() {
						this.tracedToExe = null;
					}
				'''
	}
	
	private def String generateValueUtilities() {
		return
				'''
					private List<List<? extends «valueFQN»>> getAllValueTraces() {
						final List<List<? extends «valueFQN»>> result = new ArrayList<>();
						«FOR mutClass : traceability.allMutableClasses.filter[c|!c.isAbstract].sortBy[name]»
						«val traced = traceability.getTracedClass(mutClass)»
						«val mutProps = getAllMutablePropertiesOf(mutClass).sortBy[FQN]»
						«IF !mutProps.empty»
						for («getJavaFQN(traced)» tracedObject : traceRoot.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createTraceClassToTracedClass(traced))») {
						«FOR p : mutProps»
						«val EReference ptrace = traceability.getTraceOf(p)»
							result.add(tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)»);
						«ENDFOR»
						}
						«ENDIF»
						«ENDFOR»
						return result;
					}
					
					private «valueFQN» getActiveValue(List<? extends «valueFQN»> valueTrace, «stateFQN» state) {
						«valueFQN» result = null;
						for («valueFQN» value : valueTrace) {
							if (value.getStatesNoOpposite().contains(state)) {
								result = value;
								break;
							}
						}
						return result;
					}
					
					private int getPreviousValueIndex(final List<? extends «valueFQN»> valueTrace) {
						«valueFQN» value = getActiveValue(valueTrace, currentState);
						if (value != null) {
							return valueTrace.indexOf(value) - 1;
						} else {
							int i = getCurrentStateIndex() - 1;
							while (i > -1 && value == null) {
								value = getActiveValue(valueTrace, statesTrace.get(i));
								i--;
							}
							if (value == null) {
								return -1;
							} else {
								return valueTrace.indexOf(value) - 1;
							}
						}
					}
					
					private int getNextValueIndex(List<? extends «valueFQN»> valueTrace) {
						«valueFQN» value = getActiveValue(valueTrace, currentState);
						if (value != null) {
							final int idx = valueTrace.indexOf(value) + 1;
							if (idx < valueTrace.size()) {
								return idx;
							}
							return -1;
						} else {
							int i = getCurrentStateIndex() + 1;
							final int traceLength = valueTrace.size();
							while (i < traceLength && value == null) {
								value = getActiveValue(valueTrace, statesTrace.get(i));
								i++;
							}
							if (value == null) {
								return -1;
							} else {
								final int idx = valueTrace.indexOf(value) + 1;
								if (idx < valueTrace.size()) {
									return idx;
								}
								return -1;
							}
						}
					}
				'''
	}
	
	private def generateStepUtilities() {
		return
				'''
					private int getStartingIndex(«specificStepFQN» step) {
						return stepToStartingIndex.computeIfAbsent(step, s -> {
							return statesTrace.indexOf(s.getStartingState());
						});
					}
					
					private int getEndingIndex(«specificStepFQN» step) {
						if (step.getEndingState() != null) {
							return stepToEndingIndex.computeIfAbsent(step, s -> {
								return statesTrace.indexOf(s.getEndingState());
							});
						}
						return -1;
					}
					
					@SuppressWarnings("unchecked")
					private «specificStepFQN» findNextStep(final List<«specificStepFQN»> stepPath,
							final «specificStepFQN» previousStep, final int start) {
						final List<«specificStepFQN»> rootSteps = traceRoot.getRootStep()
								.getSubSteps();
						«specificStepFQN» result = null;
						int i = start;
						int depth = stepPath.size();
						«specificStepFQN» previous = previousStep;
						while (result == null && i < depth) {
							final «specificStepFQN» currentStep = stepPath.get(depth - i - 1);
							final List<«specificStepFQN»> currentSubSteps = new ArrayList<>();
							if (currentStep instanceof SequentialStep<?>) {
								currentSubSteps
										.addAll(((SequentialStep<«specificStepFQN»>) currentStep)
												.getSubSteps());
							}
							if (currentSubSteps.isEmpty()) {
								// No substep to step into, we thus have to explore the substeps
								// of the parent step
								previous = currentStep;
							} else {
								if (previous == null) {
									// First time we step into
									result = currentSubSteps.get(0);
								} else {
									final int idx = currentSubSteps.indexOf(previous) + 1;
									if (idx < currentSubSteps.size()) {
										// We step into the next step
										result = currentSubSteps.get(idx);
									} else {
										previous = currentStep;
									}
								}
							}
							i++;
						}
						if (result == null) {
							final int idx = rootSteps.indexOf(previous) + 1;
							if (idx < rootSteps.size()) {
								result = rootSteps.get(idx);
							}
						}
						return result;
					}
				'''
	}
	
	private def generateStepQueryMethods() {
		return
				'''
					@SuppressWarnings("unchecked")
					private «specificStepFQN» computeBackInto(List<«specificStepFQN»> stepPath) {
						final List<«specificStepFQN»> rootSteps = traceRoot.getRootStep().getSubSteps();
						final int depth = stepPath.size();
						«specificStepFQN» result = null;
						if (depth > 1) {
							final «specificStepFQN» currentStep = stepPath.get(depth - 1);
							final «specificStepFQN» parentStep = stepPath.get(depth - 2);
							final SequentialStep<«specificStepFQN»> parentStep_cast = (SequentialStep<«specificStepFQN»>) parentStep;
							final List<? extends «specificStepFQN»> parentSubSteps = parentStep_cast.getSubSteps();
							final int idx = parentSubSteps.indexOf(currentStep);
							if (idx == 0) {
								// If the current step is the first in its parents substeps,
								// return parent step
								result = parentStep;
							} else if (idx > 0) {
								// Otherwise, return the deepest substep in the previous sibling
								// step
								final «specificStepFQN» previousSiblingStep = parentSubSteps.get(idx - 1);
								«specificStepFQN» tmpStep = previousSiblingStep;
								final List<«specificStepFQN»> tmpSubSteps = new ArrayList<>();
								tmpSubSteps.clear();
								if (tmpStep instanceof SequentialStep<?>) {
									SequentialStep<«specificStepFQN»> tmpStep_cast = (SequentialStep<«specificStepFQN»>) tmpStep;
									tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
								}
								while (!tmpSubSteps.isEmpty()) {
									tmpStep = tmpSubSteps.get(tmpSubSteps.size() - 1);
									tmpSubSteps.clear();
									if (tmpStep instanceof SequentialStep<?>) {
										SequentialStep<«specificStepFQN»> tmpStep_cast = (SequentialStep<«specificStepFQN»>) tmpStep;
										tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
									}
								}
								result = tmpStep;
							}
						} else if (depth == 1) {
							final «specificStepFQN» currentStep = stepPath.get(0);
							final int idx = rootSteps.indexOf(currentStep);
							if (idx > 0) {
								«specificStepFQN» tmpStep = rootSteps.get(idx - 1);
								final List<«specificStepFQN»> tmpSubSteps = new ArrayList<>();
								tmpSubSteps.clear();
								if (tmpStep instanceof SequentialStep<?>) {
									SequentialStep<«specificStepFQN»> tmpStep_cast = (SequentialStep<«specificStepFQN»>) tmpStep;
									tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
								}
								while (!tmpSubSteps.isEmpty()) {
									tmpStep = tmpSubSteps.get(tmpSubSteps.size() - 1);
									tmpSubSteps.clear();
									if (tmpStep instanceof SequentialStep<?>) {
										SequentialStep<«specificStepFQN»> tmpStep_cast = (SequentialStep<«specificStepFQN»>) tmpStep;
										tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
									}
								}
								result = tmpStep;
							}
						}
						return result;
					}
					
					@SuppressWarnings("unchecked")
					private «specificStepFQN» computeBackOver(List<«specificStepFQN»> stepPath) {
						final List<«specificStepFQN»> rootSteps = traceRoot.getRootStep().getSubSteps();
						final int depth = stepPath.size();
						«specificStepFQN» result = null;
						if (depth > 1) {
							final «specificStepFQN» currentStep = stepPath.get(depth - 1);
							final «specificStepFQN» parentStep = stepPath.get(depth - 2);
							final SequentialStep<«specificStepFQN»> parentStep_cast = (SequentialStep<«specificStepFQN»>) parentStep;
							final List<«specificStepFQN»> parentSubSteps = parentStep_cast.getSubSteps();
							final int idx = parentSubSteps.indexOf(currentStep);
							if (idx == 0) {
								// If the current step is the first in its parents substeps,
								// return parent step
								result = parentStep;
							} else {
								// Otherwise, return the previous sibling step
								result = parentSubSteps.get(idx - 1);
							}
						} else if (depth == 1) {
							final «specificStepFQN» currentStep = stepPath.get(0);
							final int idx = rootSteps.indexOf(currentStep);
							if (idx > 0) {
								result = rootSteps.get(idx - 1);
							}
						}
						return result;
					}
					
					private «specificStepFQN» computeBackOut(List<«specificStepFQN»> stepPath) {
						if (stepPath.size() > 1) {
							return stepPath.get(stepPath.size() - 2);
						}
						return null;
					}
					
					private «specificStepFQN» computeStepInto(List<«specificStepFQN»> stepPath, List<«specificStepFQN»> rootSteps) {
						return findNextStep(stepPath, null, 0);
					}
					
					private «specificStepFQN» computeStepOver(List<«specificStepFQN»> stepPath, List<«specificStepFQN»> rootSteps) {
						if (!stepPath.isEmpty()) {
							return findNextStep(stepPath, stepPath.get(stepPath.size() - 1), 1);
						}
						return null;
					}
					
					private «specificStepFQN» computeStepReturn(List<«specificStepFQN»> stepPath, List<«specificStepFQN»> rootSteps) {
						if (stepPath.size() > 1) {
							return findNextStep(stepPath, stepPath.get(stepPath.size() - 2), 2);
						}
						return null;
					}
					
					private void computeExplorerState(List<«specificStepFQN»> stepPath) {
						final List<«specificStepFQN»> rootSteps = getStepsForStates(0, statesTrace.size());
						
						final List<«specificStepFQN»> stepPathUnmodifiable = Collections.unmodifiableList(stepPath);
						
						stepIntoResult = computeStepInto(stepPathUnmodifiable, rootSteps);
						stepOverResult = computeStepOver(stepPathUnmodifiable, rootSteps);
						stepReturnResult = computeStepReturn(stepPathUnmodifiable, rootSteps);
						
						stepBackIntoResult = computeBackInto(stepPathUnmodifiable);
						stepBackOverResult = computeBackOver(stepPathUnmodifiable);
						stepBackOutResult = computeBackOut(stepPathUnmodifiable);
						
						callStack.clear();
						callStack.addAll(stepPathUnmodifiable.stream().map(s -> (Step) s)
								.collect(Collectors.toList()));
					}
				'''
	}
	
	private def String generateGoToMethods() {	
		return
				'''
					private void goTo(EObject eObject) {
						if (eObject instanceof «stateFQN») {
							«getJavaFQN(traceability.traceMMExplorer.stateClass)» stateToGo = («getJavaFQN(traceability.traceMMExplorer.stateClass)») eObject;
							«FOR p : traceability.allMutableProperties.sortBy[FQN]»
							«val EReference ptrace = traceability.getTraceOf(p)»
							«val EClass stateClass = ptrace.getEType as EClass»
							for («getJavaFQN(stateClass)» value : stateToGo.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createGlobalToState(stateClass))») {
								««« Case in which we can use the "originalObject" reference and simply set its values
								«IF p.eContainer instanceof ClassExtension»
								««« We have to test at runtime be can't know at design time the type of the object containing the property
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
									«getJavaFQN(p.EType)» current = ((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringGetter(p)»;
									if (current != toset) {
										((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringSetter(p, "toset", refGenPackages)»;
									}
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
«««								exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getTracedJavaFQN(p.EType,true)»>) «getTracedToExeMethodName»(value.«EcoreCraftingUtil.stringGetter(p)»));
								exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) «getTracedToExeMethodName»(value.«EcoreCraftingUtil.stringGetter(p)»));
								«ELSE»
								exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) value.«EcoreCraftingUtil.stringGetter(p)»);
								«ENDIF»
								«ELSE»
								exeObject.«EcoreCraftingUtil.stringSetter(p, stringGetterExeValue("value",p), refGenPackages)»;
								«ENDIF»
								«ENDIF»
							}
							«ENDFOR»
						} else if (eObject instanceof «valueFQN») {
							goTo(((«valueFQN»)eObject).getStatesNoOpposite().get(0));
						}
					}
					
					private void goTo(int stateNumber) {
						if (modelResource != null) {
							try {
								final TransactionalEditingDomain ed = TransactionUtil.getEditingDomain(modelResource);
								if (ed != null) {
									final RecordingCommand command = new RecordingCommand(ed, "") {
										protected void doExecute() {
											goTo(statesTrace.get(stateNumber));
										}
									};
									CommandExecution.execute(ed, command);
								}
							} catch (Exception e) {
								throw e;
							}
						}
					}
				'''
	}

	private def String generateExeToFromTracedGenericMethods() {
		return
				'''
					private Collection<? extends EObject> «getTracedToExeMethodName»(
							Collection<? extends EObject> tracedObjects) {
						Collection<EObject> result = new ArrayList<EObject>();
						for (EObject tracedObject : tracedObjects) {
							result.add(«getTracedToExeMethodName»(tracedObject));
						}
						return result;
					}
					
					private EObject «getTracedToExeMethodName»(EObject tracedObject) {
						return tracedToExe.get(tracedObject);
					}
				'''
	}

	private def String generateNavigationMethods() {
		return
				'''
					private void jumpBeforeStep(«specificStepFQN» step) {
						if (step != null) {
							final int i = getStartingIndex(step);
							if (i == statesTrace.size() - 1) {
								lastJumpIndex = -1;
								currentState = statesTrace.get(statesTrace.size() - 1);
							} else {
								lastJumpIndex = i;
								currentState = statesTrace.get(i);
							}
							if (tracedToExe != null) {
								goTo(i);
							}
							updateCallStack(step);
						}
					}
				'''
	}
	
	private def String generateLoadTraceUtilities() {
		return
				'''
					public void loadTrace(«getJavaFQN(traceability.traceMMExplorer.specificTraceClass)» root) {
						traceRoot = root;
						statesTrace = traceRoot.getStatesTrace();
						if (!statesTrace.isEmpty()) {
							currentState = statesTrace.get(0);
						}
						valueTraces.addAll(getAllValueTraces());
					}
					
					public void loadTrace(Resource modelResource, «getJavaFQN(traceability.traceMMExplorer.specificTraceClass)» root) {
						loadTrace(root);
						this.modelResource = modelResource;
					}
				'''
	}
	
	private def String generateAPI() {
		return
				'''
					@Override
					public boolean canBackValue(int traceIndex) {
						if (traceIndex > -1 && traceIndex < valueTraces.size()) {
							return canBackValueCache
									.computeIfAbsent(
											traceIndex,
											i -> {
												final List<? extends «valueFQN»> valueTrace = valueTraces
														.get(traceIndex);
												final int previousValueIndex = getPreviousValueIndex(valueTrace);
												return previousValueIndex > -1;
											});
						}
						return false;
					}
					
					@Override
					public boolean canStepValue(int traceIndex) {
						return true;
					}
					
					@Override
					public void backValue(int traceIndex) {
						if (traceIndex > -1 && traceIndex < valueTraces.size()) {
							final List<? extends «valueFQN»> valueTrace = valueTraces
									.get(traceIndex);
							final int previousValueIndex = getPreviousValueIndex(valueTrace);
							if (previousValueIndex > -1) {
								jump(valueTrace.get(previousValueIndex));
							}
						}
					}
					
					@Override
					public void stepValue(int traceIndex) {
						if (traceIndex > -1 && traceIndex < valueTraces.size()) {
							final List<? extends «valueFQN»> valueTrace = valueTraces
									.get(traceIndex);
							final int nextValueIndex = getNextValueIndex(valueTrace);
							if (nextValueIndex > -1) {
								jump(valueTrace.get(nextValueIndex));
							}
						}
					}
					
					@SuppressWarnings("unchecked")
					@Override
					public void jump(int i) {
						final List<«stateFQN»> states = statesTrace;
						if (i < states.size()) {
							final List<«specificStepFQN»> rootSteps = getStepsForStates(i, i);
							final List<«specificStepFQN»> searchPath = new ArrayList<>();
							«specificStepFQN» firstStepOfState = null;
							if (!rootSteps.isEmpty()) {
								final Predicate<«specificStepFQN»> p = s -> {
									final int stepStartingState = getStartingIndex(s);
									final int stepEndingState = getEndingIndex(s);
									return (stepEndingState == -1 || stepEndingState >= i) && stepStartingState <= i;
								};
								«specificStepFQN» currentStep = rootSteps.get(0);
								final List<«specificStepFQN»> siblingSteps = new ArrayList<>(rootSteps);
								final List<«specificStepFQN»> currentSubSteps = new ArrayList<>();
								if (currentStep instanceof SequentialStep<?>) {
									final SequentialStep<«specificStepFQN»> currentStep_cast = (SequentialStep<«specificStepFQN»>) currentStep;
									currentSubSteps.addAll(currentStep_cast.getSubSteps().stream().filter(p).collect(Collectors.toList()));
								}
								while (firstStepOfState == null) {
									final int startingIndex = getStartingIndex(currentStep);
									final int endingIndex = getEndingIndex(currentStep);
									if (startingIndex < i && (endingIndex > i || endingIndex == -1)) {
										if (currentSubSteps.isEmpty()) {
											throw new IllegalStateException("Unreachable state");
										} else {
											searchPath.add(0, currentStep);
											siblingSteps.clear();
											siblingSteps.addAll(currentSubSteps);
											currentStep = siblingSteps.get(0);
											currentSubSteps.clear();
											if (currentStep instanceof SequentialStep<?>) {
												final SequentialStep<«specificStepFQN»> currentStep_cast = (SequentialStep<«specificStepFQN»>) currentStep;
												currentSubSteps.addAll(currentStep_cast.getSubSteps().stream().filter(p).collect(Collectors.toList()));
											}
										}
									} else if (endingIndex == i && startingIndex != i) {
										if (currentSubSteps.isEmpty()) {
											// We need to explore the next sibling step
											«specificStepFQN» tmp = currentStep;
											currentStep = null;
											while (currentStep == null) {
												final int idx = siblingSteps.indexOf(tmp) + 1;
												if (idx < siblingSteps.size()) {
													currentStep = siblingSteps.get(idx);
													if (currentStep instanceof SequentialStep<?>) {
														final SequentialStep<«specificStepFQN»> currentStep_cast = (SequentialStep<«specificStepFQN»>) currentStep;
														currentSubSteps.addAll(currentStep_cast.getSubSteps().stream().filter(p).collect(Collectors.toList()));
													}
												} else {
													if (searchPath.isEmpty()) {
														throw new IllegalStateException("Unreachable state");
													} else {
														tmp = searchPath.remove(0);
														siblingSteps.clear();
														if (searchPath.isEmpty()) {
															siblingSteps.addAll(rootSteps);
														} else {
															final «specificStepFQN» s = searchPath.get(0);
															if (s instanceof SequentialStep<?>) {
																final SequentialStep<«specificStepFQN»> s_cast = (SequentialStep<«specificStepFQN»>) s;
																siblingSteps.addAll((s_cast).getSubSteps().stream().filter(p).collect(Collectors.toList()));
															}
														}
													}
												}
											}
										} else {
											// We need to explore the substeps in case one of them starts on i
											searchPath.add(0, currentStep);
											siblingSteps.clear();
											siblingSteps.addAll(currentSubSteps);
											currentStep = siblingSteps.get(0);
											currentSubSteps.clear();
											if (currentStep instanceof SequentialStep<?>) {
												final SequentialStep<«specificStepFQN»> currentStep_cast = (SequentialStep<«specificStepFQN»>) currentStep;
												currentSubSteps.addAll(currentStep_cast.getSubSteps().stream().filter(p).collect(Collectors.toList()));
											}
										}
									} else if (startingIndex == i) {
										firstStepOfState = currentStep;
									}
								}
							}
							jumpBeforeStep(firstStepOfState);
						}
					}
					
					@Override
					public boolean canStepBackInto() {
						return stepBackIntoResult != null;
					}
					
					@Override
					public boolean canStepBackOver() {
						return stepBackOverResult != null;
					}
					
					@Override
					public boolean canStepBackOut() {
						return stepBackOutResult != null;
					}
					
					@Override
					public int getCurrentStateIndex() {
						if (lastJumpIndex != -1) {
							return lastJumpIndex;
						}
						return statesTrace.size() - 1;
					}
					
					@Override
					public List<Step> getCallStack() {
						return callStack;
					}
					
					private List<«specificStepFQN»> getStepsForStates(int startingState, int endingState) {
						Predicate<«specificStepFQN»> predicate = s -> {
							final int stepStartingState = getStartingIndex(s);
							final int stepEndingState = getEndingIndex(s);
							return (stepEndingState == -1 || stepEndingState >= startingState)
									&& stepStartingState <= endingState;
						};
						return traceRoot.getRootStep().getSubSteps().stream().filter(predicate)
								.collect(Collectors.toList());
					}
					
					@Override
					public void notifyListeners() {
						for (ITraceListener listener : listeners) {
							listener.update();
						}
					}
					
					@Override
					public void addListener(ITraceListener listener) {
						if (listener != null) {
							listeners.add(listener);
						}
					}
					
					@Override
					public void removeListener(ITraceListener listener) {
						if (listener != null) {
							listeners.remove(listener);
						}
					}
					
					@Override
					public void update() {
						valueTraces.clear();
						valueTraces.addAll(getAllValueTraces());
						canBackValueCache.clear();
						notifyListeners();
					}
					
					@Override
					public Step getCurrentForwardStep() {
						if (!callStack.isEmpty()) {
							return callStack.get(callStack.size() - 1);
						}
						return null;
					}
					
					@Override
					public Step getCurrentBackwardStep() {
						return stepBackOverResult;
					}
					
					@Override
					public Step getCurrentBigStep() {
						return stepBackOutResult;
					}
					
					@Override
					public void jump(EObject o) {
						int idx = -1;
						if (o instanceof «stateFQN») {
							idx = statesTrace.indexOf(o);
						} else if (o instanceof «valueFQN») {
							final «stateFQN» state = ((«valueFQN») o).getStatesNoOpposite().get(0);
							idx = statesTrace.indexOf(state);
						}
						if (idx != -1) {
							jump(idx);
						}
					}
					
					@SuppressWarnings("unchecked")
					@Override
					public void loadLastState() {
						final int idx = statesTrace.size() - 1;
						final List<«specificStepFQN»> steps = getStepsForStates(idx, idx);
						«specificStepFQN» lastStep = null;
						while (!steps.isEmpty()) {
							lastStep = steps.get(steps.size() - 1);
							steps.clear();
							if (lastStep instanceof SequentialStep<?>) {
								steps.addAll(((SequentialStep<«specificStepFQN»>) lastStep)
										.getSubSteps());
							}
						}
						final int endingIndex = getEndingIndex(lastStep);
						if (endingIndex == -1 || endingIndex == idx) {
							jumpBeforeStep(lastStep);
						}
					}
					
					@Override
					public boolean stepInto() {
						if (stepIntoResult != null) {
							jumpBeforeStep(stepIntoResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean stepOver() {
						if (stepOverResult != null) {
							jumpBeforeStep(stepOverResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean stepReturn() {
						if (stepReturnResult != null) {
							jumpBeforeStep(stepReturnResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean stepBackInto() {
						if (stepBackIntoResult != null) {
							jumpBeforeStep(stepBackIntoResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean stepBackOver() {
						if (stepBackOverResult != null) {
							jumpBeforeStep(stepBackOverResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean stepBackOut() {
						if (stepBackOutResult != null) {
							jumpBeforeStep(stepBackOutResult);
							return true;
						}
						return false;
					}
					
					@Override
					public boolean isInReplayMode() {
						return stepIntoResult != null;
					}
					
					@Override
					public void updateCallStack(Step step) {
						if (step instanceof «specificStepFQN») {
							«specificStepFQN» step_cast = («specificStepFQN») step;
							final List<«specificStepFQN»> newPath = new ArrayList<>();
							newPath.add(step_cast);
							EObject container = step.eContainer();
							while (container != null && container instanceof «specificStepFQN») {
								newPath.add(0, («specificStepFQN») container);
								container = container.eContainer();
							}
							computeExplorerState(newPath);
							update();
						} else {
							throw new IllegalArgumentException("«className» expects specific steps and cannot handle this: "+step);
						}
					}
				'''
	}
	
	private def String generateTraceManagerClass() {
		
		val body =
				'''
					public class «className» implements ITraceExplorer {
							«generateFields»
							«generateConstructors»
							«generateValueUtilities»
							«generateStepUtilities»
							«generateStepQueryMethods»
							«generateGoToMethods»
							«IF getTracedToExeUsed»
							«generateExeToFromTracedGenericMethods»
							«ENDIF»
							«generateNavigationMethods»
							«generateLoadTraceUtilities»
							«generateAPI»
						}
				'''
		
		return 
				'''
					package «packageQN»;
					
					«generateImports»
					
					«body»
				'''
	}
}
