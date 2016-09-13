package org.gemoc.sequential_addons.diffviewer.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.sequential_addons.diffviewer.logic.Diff.DiffKind;

public class DiffComputer {

	private static int[][] alignTraces(final List<? extends EObject> states1, final List<? extends EObject> states2,
			final Collection<List<EObject>> classes) {
		final Map<EObject, List<EObject>> stateToEquivalentStates = new HashMap<>();
		classes.forEach(l -> {
			l.forEach(s -> {
				final List<EObject> equivalentStates = new ArrayList<>(l);
				equivalentStates.remove(s);
				stateToEquivalentStates.put(s, equivalentStates);
			});
		});
		
		final int[][] m = new int[states1.size()+1][states2.size()+1];
		
		for (int i = 0; i < m.length; i++) {
			m[i][0] = i;
		}
		for (int i = 1; i < m[0].length; i++) {
			m[0][i] = i;
		}
		
		final int[][] cost = new int[states1.size()][states2.size()];
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				if (stateToEquivalentStates.get(states1.get(i)).contains(states2.get(j))) {
					cost[i][j] = 0;
				} else {
					cost[i][j] = 1;
				}
			}
		}
		
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[1].length; j++) {
				final int deletion = m[i-1][j] + 1;
				final int insertion = m[i][j-1] + 1;
				final int substitution = m[i-1][j-1] + cost[i-1][j-1];
				m[i][j] = Math.min(Math.min(insertion, deletion), substitution);
			}
		}
		
		return m;
	}
	
	public static List<Diff> computeDiff(final List<? extends EObject> states1, final List<? extends EObject> states2,
			final Collection<List<EObject>> classes) {
		int[][] comparisonMatrix = alignTraces(states1, states2, classes);
		for (int i = 0; i < comparisonMatrix.length; i++) {
			String s = "";
			for (int j = 0; j < comparisonMatrix[0].length; j++) {
				int c = comparisonMatrix[i][j];
				s += (c + (c > 9 ? " " : "  "));
			}
			System.out.println(s);
		}
		int i = comparisonMatrix.length - 1;
		int j = comparisonMatrix[0].length - 1;
		List<Diff> diffs = new ArrayList<>();
		while (i > 0 && j > 0) {
			final int current = comparisonMatrix[i][j];
			final int deletion = comparisonMatrix[i-1][j];
			final int insertion = comparisonMatrix[i][j-1];
			final int substitution = comparisonMatrix[i-1][j-1];
			if (substitution <= deletion && substitution <= insertion &&
					substitution == current) {
				i--;
				j--;
				diffs.add(new Diff(DiffKind.EQ, i, j));
			} else if (deletion <= insertion && deletion <= substitution) {
				i--;
				diffs.add(new Diff(DiffKind.DEL, i, j));
			} else if (substitution < deletion && substitution <= insertion) {
				i--;
				j--;
				diffs.add(new Diff(DiffKind.SUBST, i, j));
			} else if (insertion < deletion && insertion < substitution) {
				j--;
				diffs.add(new Diff(DiffKind.IN, i, j));
			}
		}
		// Since we added the i and j indexes in reverse order,
		// we get them in the right order after the reverse.
		Collections.reverse(diffs);
		
		return diffs;
	}
}
