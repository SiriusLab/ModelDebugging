package fr.inria.diverse.tracemm.xmof2tracematerial.test.util

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.compare.scope.DefaultComparisonScope
import org.eclipse.emf.compare.scope.IComparisonScope
import org.eclipse.emf.compare.EMFCompare
import org.eclipse.emf.compare.Comparison
import java.util.List
import org.eclipse.emf.compare.Diff
import org.eclipse.emf.compare.DifferenceKind
import static org.junit.Assert.*

class EMFCompareUtil {

	def static assertEqualsEMF(String message, EObject rootCurrent, EObject rootExpected) {
		val DefaultComparisonScope _defaultComparisonScope = new DefaultComparisonScope(rootCurrent, rootExpected, null);
		val IComparisonScope scope = _defaultComparisonScope;
		val _builder = EMFCompare.builder();
		val EMFCompare _build = _builder.build();
		val Comparison comparison = _build.compare(scope);
		val List<Diff> differences = comparison.getDifferences();
		for (d : differences) {
			val String _string = d.toString();
			val String _plus = ("Checking:[" + _string);
			val String _plus_1 = (_plus + "]");
			val DifferenceKind _kind = d.getKind();
			println(_plus_1)
			assertEquals(message+" - "+_plus_1, DifferenceKind.MOVE, _kind);
		}

	}

}
