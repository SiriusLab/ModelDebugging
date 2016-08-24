package fr.inria.diverse.trace.plugin.generator

import ecorext.Rule
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStrings
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class TraceExtractorGeneratorJava {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage abstractSyntax
	private val TraceMMGenerationTraceability traceability
	private val Set<GenPackage> refGenPackages
	
	// Shortcuts
	private val EClass stateClass
	private val EClass valueClass
	private val EClass specificStepClass
	
	private val String stateFQN
	private val String valueFQN
	private val String specificStepFQN
	private val String specificTraceFQN
	private val String statesPackageFQN

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, TraceMMGenerationTraceability traceability,
		Set<GenPackage> refGenPackages, boolean gemoc, EPackage abstractSyntax, boolean partialTraceManagement) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "Extractor"
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
		specificTraceFQN = getJavaFQN(traceability.traceMMExplorer.specificTraceClass)
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

	private def Set<EStructuralFeature> getAllMutablePropertiesOf(EClass exeClass) {
		val Set<EStructuralFeature> res = new HashSet<EStructuralFeature>
		res.addAll(traceability.getMutablePropertiesOf(exeClass))
		res.addAll(exeClass.EAllSuperTypes.map[s|traceability.getMutablePropertiesOf(s)].flatten.toSet);
		return res
	}

	private def String generateImports() {
		return
				'''
					import java.util.ArrayList;
					import java.util.Collection;
					import java.util.Collections;
					import java.util.HashMap;
					import java.util.List;
					import java.util.Map;
					import java.util.Map.Entry;
					import java.util.Optional;
					import java.util.function.Function;
					import java.util.function.Predicate;
					import java.util.regex.Pattern;
					import java.util.stream.Collectors;
					
					import org.eclipse.emf.common.util.Monitor;
					import org.eclipse.emf.compare.Comparison;
					import org.eclipse.emf.compare.Diff;
					import org.eclipse.emf.compare.EMFCompare;
					import org.eclipse.emf.compare.Match;
					import org.eclipse.emf.compare.diff.DefaultDiffEngine;
					import org.eclipse.emf.compare.diff.DiffBuilder;
					import org.eclipse.emf.compare.diff.FeatureFilter;
					import org.eclipse.emf.compare.diff.IDiffEngine;
					import org.eclipse.emf.compare.diff.IDiffProcessor;
					import org.eclipse.emf.compare.internal.spec.MatchSpec;
					import org.eclipse.emf.compare.postprocessor.BasicPostProcessorDescriptorImpl;
					import org.eclipse.emf.compare.postprocessor.IPostProcessor;
					import org.eclipse.emf.compare.postprocessor.IPostProcessor.Descriptor.Registry;
					import org.eclipse.emf.compare.postprocessor.PostProcessorDescriptorRegistryImpl;
					import org.eclipse.emf.compare.scope.DefaultComparisonScope;
					import org.eclipse.emf.compare.scope.IComparisonScope;
					import org.eclipse.emf.ecore.EObject;
					import org.eclipse.emf.ecore.EReference;
					import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
					import org.eclipse.xtext.naming.QualifiedName;
					
					import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
					import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
					import fr.inria.diverse.trace.commons.model.trace.Step;
					import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;
				'''
	}
	
	private def String generateFields() {
		return
				'''
					private «getJavaFQN(traceability.traceMMExplorer.specificTraceClass)» traceRoot;
					
					final private List<List<? extends «valueFQN»>> valueTraces = Collections.synchronizedList(new ArrayList<>());
					
					private List<«stateFQN»> statesTrace;
					
					final private Map<«specificStepFQN», Integer> stepToStartingIndex = new HashMap<>();
					final private Map<«specificStepFQN», Integer> stepToEndingIndex = new HashMap<>();
					
					final private DefaultDeclarativeQualifiedNameProvider nameProvider = new DefaultDeclarativeQualifiedNameProvider();
					
					final private Map<Integer, Boolean> ignoredValueTraces = new HashMap<>();
				'''
	}
	
	private def String generateConstructors() {
		return
				'''
					public «className»() {
						configureDiffEngine();
					}
				'''
	}
	
	private def String generateStateUtilities() {
		return
				'''
					private final IPostProcessor customPostProcessor = new IPostProcessor() {
					
						private final Function<EObject, String> getIdFunction = e -> e.eClass().getName();
					
						@Override
						public void postMatch(Comparison comparison, Monitor monitor) {
							final List<Match> matches = new ArrayList<>(comparison.getMatches());
							final List<Match> treatedMatches = new ArrayList<>();
							matches.forEach(m1 -> {
								matches.forEach(m2 -> {
									if (m1 != m2 && !treatedMatches.contains(m2)) {
										final EObject left;
										final EObject right;
										if (m1.getLeft() != null && m1.getRight() == null && m2.getLeft() == null
												&& m2.getRight() != null) {
											left = m1.getLeft();
											right = m2.getRight();
										} else if (m2.getLeft() != null && m2.getRight() == null && m1.getLeft() == null
												&& m1.getRight() != null) {
											left = m2.getLeft();
											right = m1.getRight();
										} else {
											return;
										}
										final String leftId = getIdFunction.apply(left);
										final String rightId = getIdFunction.apply(right);
										if (leftId.equals(rightId)) {
											comparison.getMatches().remove(m1);
											comparison.getMatches().remove(m2);
											final Match match = new MatchSpec();
											match.setLeft(left);
											match.setRight(right);
											comparison.getMatches().add(match);
										}
									}
								});
								treatedMatches.add(m1);
							});
						}
					
						@Override
						public void postDiff(Comparison comparison, Monitor monitor) {
						}
					
						@Override
						public void postRequirements(Comparison comparison, Monitor monitor) {
						}
					
						@Override
						public void postEquivalences(Comparison comparison, Monitor monitor) {
						}
					
						@Override
						public void postConflicts(Comparison comparison, Monitor monitor) {
						}
					
						@Override
						public void postComparison(Comparison comparison, Monitor monitor) {
						}
					};
					
					private List<Diff> compareEObjects(EObject e1, EObject e2) {
						
						if (e1 == e2) {
							return Collections.emptyList();
						}
						
						IPostProcessor.Descriptor descriptor = new BasicPostProcessorDescriptorImpl(customPostProcessor,
								Pattern.compile(".*"), null);
					
						Registry registry = new PostProcessorDescriptorRegistryImpl();
						registry.put(customPostProcessor.getClass().getName(), descriptor);
					
						final EMFCompare compare;
					
						compare = EMFCompare.builder().setPostProcessorRegistry(registry).setDiffEngine(diffEngine).build();
					
						final IComparisonScope scope = new DefaultComparisonScope(e1, e2, null);
						final Comparison comparison = compare.compare(scope);
						return comparison.getDifferences();
					}
					
					private void computeStateComparisonValue(final «stateFQN» state,
							final List<«valueFQN»> values,
							final Map<«stateFQN», Integer> stateToComparisonValue,
							final List<«valueFQN»> observedValues, final int statesNb) {
						Integer stateComparisonValue = stateToComparisonValue.get(state);
						for (int i = 0; i < values.size(); i++) {
							final «valueFQN» value = values.get(i);
							int idx = -1;
							for (int j = 0; j < observedValues.size(); j++) {
								final «valueFQN» v1 = observedValues.get(j);
								final «valueFQN» v2 = value;
								if (v1.eClass() == v2.eClass() && compareEObjects(v1, v2).isEmpty()) {
									idx = j;
									break;
								}
							}
							final int pow = (int) Math.pow(statesNb, i);
							if (idx != -1) {
								if (stateComparisonValue == null) {
									stateComparisonValue = (idx + 1) * pow;
								} else {
									stateComparisonValue = stateComparisonValue + (idx + 1) * pow;
								}
							} else {
								observedValues.add(value);
								idx = observedValues.size();
								if (stateComparisonValue == null) {
									stateComparisonValue = idx * pow;
								} else {
									stateComparisonValue = stateComparisonValue + idx * pow;
								}
							}
							stateToComparisonValue.put(state, stateComparisonValue);
						}
					}
					
					@Override
					public Collection<List<EObject>> computeStateEquivalenceClasses() {
						return computeStateEquivalenceClasses(statesTrace);
					}
					
					@Override
					public Collection<List<EObject>> computeStateEquivalenceClasses(List<? extends EObject> states) {
						final Map<Integer, List<«stateFQN»>> statesMap = new HashMap<>();
						final Map<«stateFQN», List<«valueFQN»>> stateToValues = new HashMap<>();
						// First we build the map of states, grouped by their number of dimensions
						// and we associate to each state the list of its values
						states.stream().distinct().map(e -> («stateFQN») e).forEach(s -> {
							final List<«valueFQN»> values = getAllStateValues(s);
							stateToValues.put(s, values);
							final int size = values.size();
							List<«stateFQN»> tmp = statesMap.get(size);
							if (tmp == null) {
								tmp = new ArrayList<>();
								statesMap.put(size, tmp);
							}
							tmp.add(s);
						});
						final int statesNb = stateToValues.keySet().size();
						
						final List<«valueFQN»> observedValues = new ArrayList<>();
						final Map<«stateFQN», Integer> stateToComparisonValue = new HashMap<>();
						
						for (Entry<Integer, List<«stateFQN»>> entry : statesMap.entrySet()) {
							for («stateFQN» state : entry.getValue()) {
								final List<«valueFQN»> values = stateToValues.get(state);
								// Filling stateTocomparisonValue by side-effect
								computeStateComparisonValue(state, values, stateToComparisonValue, observedValues, statesNb);
							}
						}
						
						final Map<Integer, List<EObject>> accumulator = new HashMap<>();
						
						stateToComparisonValue.entrySet().stream().forEach(e -> {
							final «stateFQN» state = e.getKey();
							final Integer n = e.getValue();
							if (n != null) {
								List<EObject> equivalentStates = accumulator.get(n);
								if (equivalentStates == null) {
									equivalentStates = new ArrayList<>();
									accumulator.put(n, equivalentStates);
								}
								equivalentStates.add(state);
							}
						});
						return accumulator.values();
					}
					
					@Override
						public boolean compareStates(EObject eObject1, EObject eObject2, boolean respectIgnored) {
						final «stateFQN» state1;
						final «stateFQN» state2;
						
						if (eObject1 instanceof «stateFQN») {
							state1 = («stateFQN») eObject1;
						} else {
							return false;
						}
						
						if (eObject2 instanceof «stateFQN») {
							state2 = («stateFQN») eObject2;
						} else {
							return false;
						}
						
						final List<«valueFQN»> values1 = getAllStateValues(state1);
						final List<«valueFQN»> values2 = getAllStateValues(state2);
						
						if (values1.size() != values2.size()) {
							return false;
						}
						
						final List<List<Diff>> result = new ArrayList<>();
						for (int i = 0; i < values1.size(); i++) {
							if (!respectIgnored || !isValueTraceIgnored(i)) {
								final «valueFQN» value1 = values1.get(i);
								final «valueFQN» value2 = values2.get(i);
								if (value1 != value2) {
									final List<Diff> diffs = compareEObjects(value1, value2);
									if (!diffs.isEmpty()) {
										result.add(diffs);
									}
								}
							}
						}
						
						return result.isEmpty();
					}
					
					public boolean compareSteps(EObject eObject1, EObject eObject2) {
						final «specificStepFQN» step1;
						final «specificStepFQN» step2;
						
						if (eObject1 instanceof «specificStepFQN») {
							step1 = («specificStepFQN») eObject1;
						} else {
							return false;
						}
						
						if (eObject2 instanceof «specificStepFQN») {
							step2 = («specificStepFQN») eObject2;
						} else {
							return false;
						}
						
						if (step1.eClass() == step2.eClass()) {
							return true;
						}
						
						return false;
					}
					
					public boolean compareStatesWithSteps(EObject eObject1, EObject eObject2, boolean respectIgnored) {
						final «stateFQN» state1;
						final «stateFQN» state2;
					
						if (eObject1 instanceof «stateFQN») {
							state1 = («stateFQN») eObject1;
						} else {
							return false;
						}
					
						if (eObject2 instanceof «stateFQN») {
							state2 = («stateFQN») eObject2;
						} else {
							return false;
						}
						
						if (compareStates(state1, state2, respectIgnored)) {
							final List<«specificStepFQN»> endedSteps1 = state1.getEndedSteps();
							final List<«specificStepFQN»> startedSteps1 = state1.getStartedSteps();
							final List<«specificStepFQN»> endedSteps2 = state2.getEndedSteps();
							final List<«specificStepFQN»> startedSteps2 = state2.getStartedSteps();
							if (endedSteps1.size() == endedSteps2.size() &&
									startedSteps1.size() == startedSteps2.size()) {
								boolean result = true;
								for (int i = 0; i < endedSteps1.size(); i++) {
									final «specificStepFQN» endedStep1 = endedSteps1.get(i);
									final «specificStepFQN» endedStep2 = endedSteps2.get(i);
									if (!compareSteps(endedStep1, endedStep2)) {
										result = false;
										break;
									}
								}
								if (!result) {
									return false;
								}
								for (int i = 0; i < startedSteps1.size(); i++) {
									final «specificStepFQN» startedStep1 = startedSteps1.get(i);
									final «specificStepFQN» startedStep2 = startedSteps2.get(i);
									if (!compareSteps(startedStep1, startedStep2)) {
										result = false;
										break;
									}
								}
								return result;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
					
					public boolean compareTraces(EObject eObject1, EObject eObject2, boolean respectIgnored) {
						final «specificTraceFQN» trace1;
						final «specificTraceFQN» trace2;
						
						if (eObject1 instanceof «specificTraceFQN») {
							trace1 = («specificTraceFQN») eObject1;
						} else {
							return false;
						}
						
						if (eObject2 instanceof «specificTraceFQN») {
							trace2 = («specificTraceFQN») eObject2;
						} else {
							return false;
						}
						
						final List<«stateFQN»> states1 = trace1.getStatesTrace();
						final List<«stateFQN»> states2 = trace2.getStatesTrace();
						
						if (states1.size() != states2.size()) {
							return false;
						}
						
						boolean result = true;
						
						for (int i = 0; i < states1.size(); i++) {
							final «stateFQN» state1 = states1.get(i);
							final «stateFQN» state2 = states2.get(i);
							if (!compareStatesWithSteps(state1, state2, respectIgnored)) {
								result = false;
								break;
							}
						}
						
						return result;
					}
					
					
					private List<«valueFQN»> getAllStateValues(«stateFQN» state) {
						final List<List<? extends «valueFQN»>> traces = new ArrayList<>();
						final List<«valueFQN»> result = new ArrayList<>();
						«FOR mutClass : traceability.allMutableClasses.filter[c|!c.isAbstract].sortBy[name]»
						«val traced = traceability.getTracedClass(mutClass)»
						«val mutProps = getAllMutablePropertiesOf(mutClass).sortBy[FQN]»
						«IF !mutProps.empty»
						for («getJavaFQN(traced)» tracedObject : ((«getJavaFQN(traceability.traceMMExplorer.specificTraceClass)») state.eContainer()).«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createTraceClassToTracedClass(traced))») {
						«FOR p : mutProps»
						«val EReference ptrace = traceability.getTraceOf(p)»
							traces.add(tracedObject.«EcoreCraftingUtil.stringGetter(ptrace)»);
						«ENDFOR»
						}
						«ENDIF»
						«ENDFOR»
						for (int i = 0; i < traces.size(); i++) {
							if (!isValueTraceIgnored(i)) {
								final List<? extends «valueFQN»> trace = traces.get(i);
								for («valueFQN» value : trace) {
									if (value.getStatesNoOpposite().contains(state)) {
										result.add(value);
										break;
									}
								}
							}
						}
						return result;
					}
				'''
	}
	
	private def String generateValueUtilities() {
		return
				'''
					private IDiffEngine diffEngine = null;
						
					private void configureDiffEngine() {
						IDiffProcessor diffProcessor = new DiffBuilder();
						diffEngine = new DefaultDiffEngine(diffProcessor) {
							@Override
							protected FeatureFilter createFeatureFilter() {
								return new FeatureFilter() {
									@Override
									protected boolean isIgnoredReference(Match match, EReference reference) {
										final String name = reference.getName();
										return name.equals("parent") || name.equals("states") || name.equals("statesNoOpposite");
									}
								};
							}
						};
					}
					
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

					private ValueWrapper getValueWrapper(«valueFQN» value, int valueIndex) {
						List<«stateFQN»> states = value.getStatesNoOpposite();
						«stateFQN» firstState = states.get(0);
						final int firstStateIndex = statesTrace.indexOf(firstState);
						final int lastStateIndex = (int) (firstStateIndex + states.stream().distinct().count() - 1);
						return new ValueWrapper(value, firstStateIndex, lastStateIndex, valueIndex);
					}
					
					private «valueFQN» getValueAt(int traceIndex, int stateIndex) {
						«valueFQN» result = null;
						if (traceIndex >= 0 && traceIndex < valueTraces.size()) {
							final List<? extends «valueFQN»> valueTrace = valueTraces.get(traceIndex);
							final «stateFQN» state = statesTrace.get(stateIndex);
							for («valueFQN» value : valueTrace) {
								if (value.getStatesNoOpposite().contains(state)) {
									result = value;
									break;
								}
							}
						}
						return result;
					}
					
					@Override
					public ValueWrapper getValueWrapper(int traceIndex, int stateIndex) {
						«valueFQN» value = getValueAt(traceIndex, stateIndex);
						if (value == null) {
							return null;
						}
						List<«stateFQN»> states = value.getStatesNoOpposite();
						«stateFQN» firstState = states.get(0);
						final int firstStateIndex = statesTrace.indexOf(firstState);
						final int lastStateIndex = (int) (firstStateIndex + states.stream().distinct().count() - 1);
						return new ValueWrapper(value, firstStateIndex, lastStateIndex, traceIndex);
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
				'''
	}

	private def String generateLoadTraceUtilities() {
		return
				'''
					public void loadTrace(«getJavaFQN(traceability.traceMMExplorer.specificTraceClass)» root) {
						traceRoot = root;
						statesTrace = traceRoot.getStatesTrace();
						valueTraces.addAll(getAllValueTraces());
					}
				'''
	}
	
	private def String generateAPI() {
		return
				'''
					@Override
					public List<StepWrapper> getStepWrappers(int startingState, int endingState) {
						Predicate<«specificStepFQN»> predicate = s -> {
							final int stepStartingState = getStartingIndex(s);
							final int stepEndingState = getEndingIndex(s);
							return (stepEndingState == -1 || stepEndingState >= startingState)
									&& stepStartingState <= endingState;
						};
						return traceRoot.getRootStep().getSubSteps().stream().filter(predicate)
								.map(s -> getStepWrapper(s))
								.collect(Collectors.toList());
					}
					
					private boolean isStateBreakable(«stateFQN» state) {
						«IF !traceability.bigStepClasses.empty»
						final boolean b = state.getStartedSteps().size() == 1;
						if (b) {
							«specificStepFQN» s = state.getStartedSteps().get(0);
							return
								!(«FOR bigStepClass : traceability.bigStepClasses.sortBy[name] SEPARATOR "||"»
								s instanceof «getJavaFQN(bigStepClass)»_ImplicitStep
								«ENDFOR»);
						}
						«ENDIF»
						return true;
					}
					
					@Override
					public StateWrapper getStateWrapper(int stateIndex) {
						if (stateIndex > -1 && stateIndex < statesTrace.size()) {
							final «stateFQN» state = statesTrace.get(stateIndex);
							return new StateWrapper(state, stateIndex, isStateBreakable(state));
						}
						return null;
					}
					
					@Override
					public List<StateWrapper> getStateWrappers(int start, int end) {
						final List<StateWrapper> result = new ArrayList<>();
						final int startStateIndex = Math.max(0, start);
						final int endStateIndex = Math.min(statesTrace.size() - 1, end);
						
						for (int i = startStateIndex; i < endStateIndex + 1; i++) {
							final «stateFQN» state = statesTrace.get(i);
							result.add(new StateWrapper(state, i, isStateBreakable(state)));
						}
						
						return result;
					}
					
					@Override
					public List<ValueWrapper> getValueWrappers(int valueTraceIndex, int start, int end) {
						final List<ValueWrapper> result = new ArrayList<>();
						
						if (valueTraceIndex < valueTraces.size()) {
							final List<? extends «valueFQN»> valueTrace = valueTraces.get(valueTraceIndex);
							for («valueFQN» value : valueTrace) {
								final int currentValueIndex = valueTrace.indexOf(value);
								ValueWrapper wrapper = getValueWrapper(value, currentValueIndex);
								if (wrapper.firstStateIndex < end && wrapper.lastStateIndex > start) {
									result.add(wrapper);
								}
							}
						}
						
						return result;
					}
					
					@SuppressWarnings("unchecked")
					@Override
					public StepWrapper getStepWrapper(Step step) {
						if (step instanceof «specificStepFQN») {
							final «specificStepFQN» step_cast = («specificStepFQN») step;
							final int startingIndex = getStartingIndex(step_cast);
							final int endingIndex = getEndingIndex(step_cast);
							final List<Step> subSteps = new ArrayList<>();
							if (step_cast instanceof SequentialStep<?>) {
								subSteps.addAll(((SequentialStep<«specificStepFQN»>) step_cast).getSubSteps());
							}
							return new StepWrapper(step, startingIndex, endingIndex, subSteps);
						}
						return null;
					}
					
					@Override
					public int getNumberOfTraces() {
						return valueTraces.size();
					}
					
					@Override
					public int getStatesTraceLength() {
						return statesTrace.size();
					}
					
					@Override
					public int getValuesTraceLength(int traceIndex) {
						if (traceIndex > -1 && traceIndex < valueTraces.size()) {
							List<? extends «valueFQN»> trace = valueTraces.get(traceIndex);
							return trace.size();
						}
						return -1;
					}
					
					@Override
					public String getValueLabel(int traceIndex) {
						String attributeName = "";
						if (traceIndex > -1 && traceIndex < valueTraces.size()) {
							final List<? extends «valueFQN»> valueTrace = valueTraces.get(traceIndex);
							if (valueTrace.isEmpty()) {
								return "";
							}
							final «valueFQN» value = valueTrace.get(0);
							final EObject container = value.eContainer();
							final List<String> attributes = container.eClass().getEAllReferences().stream()
									.filter(r -> r.getName().endsWith("Sequence"))
									.map(r->r.getName().substring(0, r.getName().length() - 8))
									.collect(Collectors.toList());
							if (!attributes.isEmpty()) {
								attributes.removeIf(s->!value.getClass().getName().contains("_" + s + "_"));
								attributeName = attributes.get(0);
							}
							final Optional<EReference> originalObject = container.eClass().getEAllReferences()
									.stream().filter(r -> r.getName().equals("originalObject"))
									.findFirst();
							if (originalObject.isPresent()) {
								final Object o = container.eGet(originalObject.get());
								if (o instanceof EObject) {
									final EObject eObject = (EObject) o;
									final QualifiedName qname = nameProvider.getFullyQualifiedName(eObject);
									if (qname == null) {
										return attributeName + " (" + eObject.toString() + ")";
									} else {
										return attributeName + " (" + qname.toString() + " :" + eObject.eClass().getName() + ")";
									}
								}
							}
						}
						return attributeName;
					}
					
					@Override
					public String getStateDescription(int stateIndex) {
						String result = "";
						for (int i = 0; i < valueTraces.size(); i++) {
							if (!isValueTraceIgnored(i)) {
								result += (i == 0 ? "" : "\n") + getValueDescription(i, stateIndex);
							}
						}
						return result;
					}
					
					@Override
					public String getValueDescription(int traceIndex, int stateIndex) {
						final EObject value = getValueAt(traceIndex, stateIndex);
						if (value == null) {
							return null;
						}
						final String description = getValueLabel(traceIndex) + " : " + value;
						return description;
					}
					
					@Override
					public LaunchConfiguration getLaunchConfiguration() {
						return traceRoot.getLaunchconfiguration();
					}
					
					@Override
					public void ignoreValueTrace(int trace, boolean ignore) {
						ignoredValueTraces.put(trace, ignore);
					}
					
					@Override
					public boolean isValueTraceIgnored(int trace) {
						Boolean result = ignoredValueTraces.get(trace);
						return result != null && result;
					}
					
					
					@Override
					public void update() {
						valueTraces.clear();
						valueTraces.addAll(getAllValueTraces());
					}
				'''
	}
	
	private def String generateTraceManagerClass() {
		return 
				'''
					package «packageQN»;
					
					«generateImports»
					
					public class «className» implements ITraceExtractor {
					
						«generateFields»
						«generateConstructors»
						«generateValueUtilities»
						«generateStateUtilities»
						«generateStepUtilities»
						«generateLoadTraceUtilities»
						«generateAPI»
					}
				'''
	}
}
