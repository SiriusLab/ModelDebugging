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
package fr.inria.diverse.tracemm.xmof.footprint;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintFactory;

public abstract class FootprintAnalyzer {

	protected EPackage metamodelEPackage;

	public FootprintAnalyzer(EPackage metamodelEPackage) {
		this.metamodelEPackage = metamodelEPackage;
	}

	public abstract Footprint calculateFootprint();

	protected Footprint createFootprint() {
		Footprint footprint = MmfootprintFactory.eINSTANCE.createFootprint();
		footprint.setMetamodelEPackage(metamodelEPackage);
		return footprint;
	}
}
