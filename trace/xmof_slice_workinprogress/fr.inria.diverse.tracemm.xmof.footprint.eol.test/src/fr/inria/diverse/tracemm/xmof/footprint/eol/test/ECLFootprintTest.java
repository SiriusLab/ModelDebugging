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
package fr.inria.diverse.tracemm.xmof.footprint.eol.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;

import fr.inria.diverse.tracemm.xmof.footprint.eol.ECLFootprintAnalyzer;
import fr.inria.diverse.tracemm.xmof.footprint.eol.test.internal.EpsilonUtil;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;

public class ECLFootprintTest {

	private static final String FUML_DS_MATCH_RULES_PATH = "eclfiles/actionExecutionOrder.ecl";
	private static final String FUML_DS_TRACEMM_PATH = "tracemms/fumltracemm.ecore";

	@Test
	public void testFootprinting_FUML() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource tracemetamodelResource = resourceSet
				.getResource(URI.createFileURI(new File(FUML_DS_TRACEMM_PATH).getAbsolutePath()), true);
		EPackage metamodelEPackage = (EPackage) tracemetamodelResource.getContents().get(0);
		File eclFile = new File(FUML_DS_MATCH_RULES_PATH);

		ECLFootprintAnalyzer analyzer = new ECLFootprintAnalyzer(metamodelEPackage, eclFile);
		Footprint footprint = analyzer.calculateFootprint();
		EpsilonUtil.print(analyzer.getEclModule().getAst());
		EpsilonUtil.print(footprint);

		save(footprint, resourceSet,
				URI.createFileURI(new File("footprints/actionExecutionOrder.xmi").getAbsolutePath()));
	}

	private void save(Footprint footprint, ResourceSet resourceSet, URI targetURI) throws IOException {
		Resource traceResource = resourceSet.createResource(targetURI);

		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain(resourceSet);
		Command cmd = new AddCommand(editingDomain, traceResource.getContents(), footprint);
		editingDomain.getCommandStack().execute(cmd);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		traceResource.save(options);
	}
}
