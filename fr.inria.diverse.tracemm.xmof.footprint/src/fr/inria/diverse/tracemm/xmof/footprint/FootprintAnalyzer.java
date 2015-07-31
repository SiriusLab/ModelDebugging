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
