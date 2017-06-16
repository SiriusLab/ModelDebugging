/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.commons

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

	public def static void assertEqualsEMF(String message, EObject rootCurrent, EObject rootExpected) {
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
