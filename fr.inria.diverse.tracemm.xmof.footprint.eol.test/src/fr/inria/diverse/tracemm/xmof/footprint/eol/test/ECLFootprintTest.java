package fr.inria.diverse.tracemm.xmof.footprint.eol.test;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import fr.inria.diverse.tracemm.xmof.footprint.eol.ECLFootprintAnalyzer;
import fr.inria.diverse.tracemm.xmof.footprint.eol.test.internal.EpsilonUtil;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;

public class ECLFootprintTest {

	private static final String FUML_DS_MATCH_RULES_PATH = "eclfiles/actionExecutionOrder.ecl";
	private static final String FUML_DS_TRACEMM_PATH = "tracemms/fumltracemm.ecore";

	@Test
	public void testFootprinting_FUML() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource tracemetamodelResource = resourceSet
				.getResource(URI.createFileURI(new File(FUML_DS_TRACEMM_PATH).getAbsolutePath()), true);
		EPackage metamodelEPackage = (EPackage) tracemetamodelResource.getContents().get(0);
		File eclFile = new File(FUML_DS_MATCH_RULES_PATH);

		ECLFootprintAnalyzer analyzer = new ECLFootprintAnalyzer(metamodelEPackage, eclFile);
		EpsilonUtil.print(analyzer.getEclModule().getAst());

		Footprint footprint = analyzer.calculateFootprint();
		EpsilonUtil.print(footprint);
	}

}
