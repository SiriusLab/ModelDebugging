package fr.inria.diverse.trace.metamodel.generator

import java.util.Set
import ecorext.Rule
import java.util.HashSet
import java.util.Map
import java.util.HashMap
import java.util.LinkedList
import java.util.Deque
import java.util.List
import java.util.ArrayList


/**
 * USELSSS §§§
 */
class RulesTarjan {

	public static class SCC {
		public Set<Rule> vertices = new HashSet
		public Set<SCC> pointsTo = new HashSet
	}

	private static class VerticeInfo {
		public int index = -1
		public int lowlink = -1
		public boolean onStack = false;
	}

	private static val Map<Rule, VerticeInfo> ruleToVerticeInfo = new HashMap

	private def static int index(Rule r) {
		return ruleToVerticeInfo.get(r).index
	}

	private def static void index(Rule r, int newindex) {
		ruleToVerticeInfo.get(r).index = newindex
	}

	private def static int lowlink(Rule r) {
		return ruleToVerticeInfo.get(r).lowlink
	}

	private def static void lowlink(Rule r, int newlowlink) {
		ruleToVerticeInfo.get(r).lowlink = newlowlink
	}

	private def static boolean onStack(Rule r) {
		return ruleToVerticeInfo.get(r).onStack
	}

	private def static void onStack(Rule r, boolean newonStack) {
		ruleToVerticeInfo.get(r).onStack = newonStack
	}

	private static var indexGlobal = 0
	private static val Deque<Rule> SGlobal = new LinkedList

	public static def List<SCC> tarjan(Set<Rule> g) {

		// Reset all
		ruleToVerticeInfo.clear
		indexGlobal = 0
		SGlobal.clear

		// Init of the fake aspect attributes
		for (v : g) {
			ruleToVerticeInfo.put(v, new VerticeInfo)
		}

		// Then it's literally https://en.wikipedia.org/wiki/Tarjan's_strongly_connected_components_algorithm
		val output = new ArrayList<SCC>
		for (v : g) {
			if (v.index == -1) {
				strongconnect(v, output)
			}
		}
		return output
	}

	def static private void strongconnect(Rule v, ArrayList<SCC> output) {
		v.index = indexGlobal
		v.lowlink = indexGlobal
		indexGlobal = indexGlobal + 1
		SGlobal.push(v)
		v.onStack = true

		for (w : v.calledRules) {
			if (w.index == -1) {
				strongconnect(w, output)
				v.lowlink = Math.min(v.lowlink, w.lowlink)
			} else if (w.onStack) {
				v.lowlink = Math.min(v.lowlink, w.index)
			}
		}

		if (v.lowlink == v.index) {
			val newSCC = new SCC
			var Rule w
			do {
				w = SGlobal.pop
				w.onStack = false
				newSCC.vertices.add(w)
			} while (w != v)
		}
	}
}