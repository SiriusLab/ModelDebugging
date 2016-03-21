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
package fr.inria.diverse.tracemm.xmof2tracematerial

import java.util.Set
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import ecorext.Ecorext
import org.eclipse.xtend.lib.annotations.Accessors

class XmofTraceMaterialExtractor {

	// Input
	protected val Resource xmofModel
	protected Set<EPackage> ecore

	// Transient
	protected boolean done = false
	protected Copier copier

	// Output
	@Accessors(PUBLIC_GETTER,PROTECTED_SETTER)
	Ecorext exeExt

	new(Set<EPackage> ecore, Resource xmofModel) {
		this.xmofModel = xmofModel
		this.ecore = ecore
	}

	new(Resource ecoreModel, Resource xmofModel) {
		this(ecoreModel.contents.filter(EPackage).toSet, xmofModel)
	}

	public def void computeAllMaterial() {
		if (!done) {
			val exeExtGenerator = new XmofExecutionExtensionGenerator(ecore, xmofModel)
			exeExtGenerator.computeMMExtension
			exeExt = exeExtGenerator.mmextensionResult
		} else {
			println("ERROR: already computed.")
		}
	}

}
