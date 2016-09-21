package org.gemoc.sequential_addons.diffviewer.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.compare.Comparison;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.gemoc.sequential_addons.diffviewer.logic.Diff.DiffKind;

import javafx.util.Pair;

@SuppressWarnings("restriction")
public class DiffComputer {

	@SuppressWarnings("rawtypes")
	private Registry registry = null;
	private IDiffEngine diffEngine = null;
	private EMFCompare compare;
	private IPostProcessor.Descriptor descriptor = null;
	private boolean compareInitialized = false;

	private final List<Pair<List<EObject>, List<EObject>>> eqGroup = new ArrayList<>();
	private final List<Pair<List<EObject>, List<EObject>>> substGroup = new ArrayList<>();
	private final List<List<EObject>> inGroup = new ArrayList<>();
	private final List<List<EObject>> delGroup = new ArrayList<>();

	private final List<Diff> diffs = new ArrayList<>();

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean compareEObjects(EObject e1, EObject e2) {
		if (e1 == e2) {
			return true;
		}

		if (e1 == null || e2 == null) {
			return false;
		}

		if (e1.eClass() != e2.eClass()) {
			return false;
		}

		if (!compareInitialized) {
			configureDiffEngine();
			descriptor = new BasicPostProcessorDescriptorImpl(customPostProcessor, Pattern.compile(".*"), null);
			registry = new PostProcessorDescriptorRegistryImpl();
			registry.put(customPostProcessor.getClass().getName(), descriptor);
			compare = EMFCompare.builder().setPostProcessorRegistry(registry).setDiffEngine(diffEngine).build();
			compareInitialized = true;
		}

		final IComparisonScope scope = new DefaultComparisonScope(e1, e2, null);
		final Comparison comparison = compare.compare(scope);
		return comparison.getDifferences().isEmpty();
	}

	private boolean compareTraces(final List<EObject> trace1, final List<EObject> trace2) {
		final int length1 = trace1.size();
		final int length2 = trace2.size();

		if (length1 != length2) {
			return false;
		}
		boolean result = true;
		int i = 0;
		while (i < length1 && result) {
			result = compareEObjects(trace1.get(i), trace2.get(i));
			i++;
		}
		return result;
	}

	private int computeDistanceBetweenTraces(final List<EObject> trace1, final List<EObject> trace2) {
		final int[][] m = new int[trace1.size() + 1][trace2.size() + 1];

		for (int i = 0; i < m.length; i++) {
			m[i][0] = i;
		}
		for (int i = 1; i < m[0].length; i++) {
			m[0][i] = i;
		}

		final int[][] cost = new int[trace1.size()][trace2.size()];
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				if (compareEObjects(trace1.get(i), trace2.get(j))) {
					cost[i][j] = 0;
				} else {
					cost[i][j] = 1;
				}
			}
		}

		int result = 0;

		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[1].length; j++) {
				final int deletion = m[i - 1][j] + 1;
				final int insertion = m[i][j - 1] + 1;
				final int substitution = m[i - 1][j - 1] + cost[i - 1][j - 1];
				result = Math.min(Math.min(insertion, deletion), substitution);
				m[i][j] = result;
			}
		}

		return result;
	}

	private Map<int[], Integer> matchTraces(final List<List<EObject>> traces1, List<List<EObject>> traces2) {
		final Map<Integer, List<int[]>> pairs = new HashMap<>();
		for (int i = 0; i < traces1.size(); i++) {
			for (int j = 0; j < traces2.size(); j++) {
				final int k = computeDistanceBetweenTraces(traces1.get(i), traces2.get(j));
				List<int[]> l = pairs.get(k);
				if (l == null) {
					l = new ArrayList<>();
					pairs.put(k, l);
				}
				l.add(new int[] { i, j });
			}
		}

		List<Integer> distances = pairs.keySet().stream().sorted().collect(Collectors.toList());
		Map<int[], Integer> result = new HashMap<>();
		for (Integer d : distances) {
			List<int[]> l = pairs.get(d);
			while (l != null && !l.isEmpty()) {
				int[] p = l.remove(0);
				result.put(p, d);
				pairs.values().forEach(toClean -> toClean.removeIf(t -> t[0] == p[0] || t[1] == p[1]));
			}
		}

		return result;
	}

	private EClass getTraceEClass(final List<EObject> trace) {
		EClass result = null;
		for (EObject e : trace) {
			if (e != null) {
				result = e.eClass();
				break;
			}
		}
		return result;
	}

	public List<Diff> getDiffs() {
		return diffs;
	}

	public List<Pair<List<EObject>, List<EObject>>> getEqGroup() {
		return eqGroup;
	}

	public List<Pair<List<EObject>, List<EObject>>> getSubstGroup() {
		return substGroup;
	}

	public List<List<EObject>> getInGroup() {
		return inGroup;
	}

	public List<List<EObject>> getDelGroup() {
		return delGroup;
	}

	public void loadTraces(final List<List<EObject>> traces1, final List<List<EObject>> traces2) {
		final Map<EClass, List<List<EObject>>> traceGroups1 = new HashMap<>();
		final Map<EClass, List<List<EObject>>> traceGroups2 = new HashMap<>();

		for (List<EObject> trace : traces1) {
			EClass eClass = getTraceEClass(trace);
			if (eClass != null) {
				List<List<EObject>> l = traceGroups1.get(eClass);
				if (l == null) {
					l = new ArrayList<>();
					traceGroups1.put(eClass, l);
				}
				l.add(trace);
			}
		}

		for (List<EObject> trace : traces2) {
			final EClass eClass = getTraceEClass(trace);
			if (eClass != null) {
				List<List<EObject>> l = traceGroups2.get(eClass);
				if (l == null) {
					l = new ArrayList<>();
					traceGroups2.put(eClass, l);
				}
				l.add(trace);
			}
		}

		eqGroup.clear();
		substGroup.clear();
		inGroup.clear();
		delGroup.clear();

		final Set<EClass> classes = new HashSet<>(traceGroups1.keySet());
		classes.addAll(traceGroups2.keySet());
		final List<EClass> classesSorted = classes.stream().sorted((e1, e2) -> e2.getName().compareTo(e1.getName()))
				.collect(Collectors.toList());

		final Map<Pair<List<EObject>, List<EObject>>, Integer> substGroupAccumulator = new HashMap<>();

		for (EClass eClass : classesSorted) {
			if (traceGroups1.containsKey(eClass) && traceGroups2.containsKey(eClass)) {
				List<List<EObject>> traceGroup1 = traceGroups1.get(eClass);
				List<List<EObject>> traceGroup2 = traceGroups2.get(eClass);
				int i = 0;
				int j = 0;
				while (i < traceGroup1.size()) {
					List<EObject> trace1 = traceGroup1.get(i);
					List<EObject> trace2 = traceGroup2.get(j);
					if (compareTraces(trace1, trace2)) {
						traceGroup1.remove(i);
						traceGroup2.remove(j);
						j = 0;
						eqGroup.add(new Pair<>(trace1, trace2));
					} else {
						if (j < traceGroup2.size() - 1) {
							j++;
						} else {
							i++;
							j = 0;
						}
					}
				}
				if (!traceGroup1.isEmpty() && !traceGroup2.isEmpty()) {
					for (Map.Entry<int[], Integer> pair : matchTraces(traceGroup1, traceGroup2).entrySet()) {
						final List<EObject> t1 = traceGroup1.get(pair.getKey()[0]);
						final List<EObject> t2 = traceGroup2.get(pair.getKey()[1]);
						substGroupAccumulator.put(new Pair<>(t1, t2), pair.getValue());
					}
				}
			}
		}

		delGroup.addAll(traces1);
		inGroup.addAll(traces2);

		eqGroup.forEach(p -> {
			inGroup.remove(p.getKey());
			inGroup.remove(p.getValue());
			delGroup.remove(p.getKey());
			delGroup.remove(p.getValue());
		});

		for (Map.Entry<Pair<List<EObject>, List<EObject>>, Integer> e : substGroupAccumulator.entrySet().stream()
				.sorted((e1, e2) -> {
					return e1.getValue() - e2.getValue();
				}).collect(Collectors.toList())) {
			substGroup.add(e.getKey());
		}

		substGroup.forEach(p -> {
			inGroup.remove(p.getKey());
			inGroup.remove(p.getValue());
			delGroup.remove(p.getKey());
			delGroup.remove(p.getValue());
		});

		List<List<EObject>> stateTrace1 = new ArrayList<>();
		List<List<EObject>> stateTrace2 = new ArrayList<>();

		List<List<EObject>> valuesTrace1 = new ArrayList<>();
		List<List<EObject>> valuesTrace2 = new ArrayList<>();

		substGroup.forEach(p -> {
			valuesTrace1.add(p.getKey());
			valuesTrace2.add(p.getValue());
		});

		for (int i = 0; i < valuesTrace1.get(0).size(); i++) {
			final List<EObject> stateValues = new ArrayList<>();
			for (List<EObject> l : valuesTrace1) {
				stateValues.add(l.get(i));
			}
			stateTrace1.add(stateValues);
		}

		for (int i = 0; i < valuesTrace2.get(0).size(); i++) {
			final List<EObject> stateValues = new ArrayList<>();
			for (List<EObject> l : valuesTrace2) {
				stateValues.add(l.get(i));
			}
			stateTrace2.add(stateValues);
		}

		final List<List<EObject>> allStates = new ArrayList<>(stateTrace1);
		allStates.addAll(stateTrace2);

		List<List<List<EObject>>> equivalenceClasses = computeEquivalenceClasses(allStates);
		diffs.addAll(computeDiff(stateTrace1, stateTrace2, equivalenceClasses));
	}

	private List<List<List<EObject>>> computeEquivalenceClasses(final List<List<EObject>> states) {
		final List<Pair<List<EObject>, List<Integer>>> stateToValueIndexes = new ArrayList<>();
		final List<EObject> observedValues = new ArrayList<>();

		for (List<EObject> state : states) {
			final List<Integer> valueIndexes = new ArrayList<>();
			stateToValueIndexes.add(new Pair<>(state, valueIndexes));
			for (int i = 0; i < state.size(); i++) {
				final EObject value = state.get(i);
				int idx = -1;
				for (int j = 0; j < observedValues.size(); j++) {
					final EObject v1 = observedValues.get(j);
					final EObject v2 = value;
					if (compareEObjects(v1, v2)) {
						idx = j;
						break;
					}
				}
				if (idx != -1) {
					valueIndexes.add(idx);
				} else {
					valueIndexes.add(observedValues.size());
					observedValues.add(value);
				}
			}
		}

		final int valueNb = observedValues.size();
		final Map<Integer, List<List<EObject>>> result = new HashMap<>();

		stateToValueIndexes.forEach(p -> {
			final List<EObject> state = p.getKey();
			final List<Integer> indexes = p.getValue();
			int v = 0;
			for (int i = 0; i < indexes.size(); i++) {
				final int index = indexes.get(i);
				v += index * Math.pow(valueNb, i);
			}
			List<List<EObject>> equivalentStates = result.get(v);
			if (equivalentStates == null) {
				equivalentStates = new ArrayList<>();
				result.put(v, equivalentStates);
			}
			if (equivalentStates.isEmpty()) {
				equivalentStates.add(state);
			} else {
				if (states.indexOf(state) < states.indexOf(equivalentStates.get(0))) {
					equivalentStates.add(0, state);
				} else {
					equivalentStates.add(state);
				}
			}
		});

		return result.values().stream().collect(Collectors.toList());
	}

	private int[][] alignTraces(final List<List<EObject>> states1, final List<List<EObject>> states2,
			final Collection<List<List<EObject>>> classes) {
		final Map<List<EObject>, List<List<EObject>>> stateToEquivalentStates = new HashMap<>();
		classes.forEach(l -> {
			l.forEach(s -> {
				final List<List<EObject>> equivalentStates = new ArrayList<>(l);
				equivalentStates.remove(s);
				stateToEquivalentStates.put(s, equivalentStates);
			});
		});

		final int[][] m = new int[states1.size() + 1][states2.size() + 1];

		for (int i = 0; i < m.length; i++) {
			m[i][0] = i;
		}
		for (int i = 1; i < m[0].length; i++) {
			m[0][i] = i;
		}

		final int[][] cost = new int[states1.size()][states2.size()];
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				final List<EObject> s1 = states1.get(i);
				final List<EObject> s2 = states2.get(j);
				final List<List<EObject>> equivalentStates = stateToEquivalentStates.get(s1);
				if (equivalentStates.contains(s2)) {
					cost[i][j] = 0;
				} else {
					cost[i][j] = 1;
				}
			}
		}

		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[1].length; j++) {
				final int deletion = m[i - 1][j] + 1;
				final int insertion = m[i][j - 1] + 1;
				final int substitution = m[i - 1][j - 1] + cost[i - 1][j - 1];
				m[i][j] = Math.min(Math.min(insertion, deletion), substitution);
			}
		}

		return m;
	}

	public List<Diff> computeDiff(final List<List<EObject>> states1, final List<List<EObject>> states2,
			final Collection<List<List<EObject>>> classes) {
		final int[][] comparisonMatrix = alignTraces(states1, states2, classes);
		final int[][] highlightedCells = new int[comparisonMatrix.length][comparisonMatrix[0].length];
		int i = comparisonMatrix.length - 1;
		int j = comparisonMatrix[0].length - 1;
		List<Diff> diffs = new ArrayList<>();
		while (i > 0 && j > 0) {
			final int current = comparisonMatrix[i][j];
			final int deletion = comparisonMatrix[i - 1][j];
			final int insertion = comparisonMatrix[i][j - 1];
			final int substitution = comparisonMatrix[i - 1][j - 1];
			final int min = Math.min(deletion, Math.min(insertion, substitution));
			highlightedCells[i][j] = 1;
			if (min == substitution) {
				diffs.add(new Diff(current == substitution ? DiffKind.EQ : DiffKind.SUBST, i - 1, j - 1));
				i--;
				j--;
			} else if (min == deletion) {
				diffs.add(new Diff(DiffKind.DEL, i - 1, j - 1));
				i--;
			} else {
				diffs.add(new Diff(DiffKind.IN, i - 1, j - 1));
				j--;
			}
		}
		// Since we added the i and j indexes in reverse order,
		// we get them in the right order after the reverse.
		Collections.reverse(diffs);

		return diffs;
	}
}
