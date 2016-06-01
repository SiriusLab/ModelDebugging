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
package fr.inria.diverse.tracemm.xmof.footprint.eol;

import java.io.File;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;

import fr.inria.diverse.tracemm.xmof.footprint.FootprintAnalyzer;
import fr.inria.diverse.tracemm.xmof.footprint.eol.internal.ASTAnalyzer;
import fr.inria.diverse.tracemm.xmof.footprint.eol.internal.ASTAnalyzerFactory;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintFactory;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolFactory;

public class ECLFootprintAnalyzer extends FootprintAnalyzer {

	private File eclFile;
	protected EclModule eclModule;
	protected ASTAnalyzerFactory astAnalyzerFactory;

	private HashMap<EModelElement, EModelElementAccess> eModelElementAccessStore = new HashMap<EModelElement, EModelElementAccess>();

	public ECLFootprintAnalyzer(EPackage metamodelEPackage, File eclFile) {
		super(metamodelEPackage);
		this.eclFile = eclFile;
	}

	@Override
	public Footprint calculateFootprint() {
		Footprint footprint = null;

		initialize();
		if (canAnalyze()) {
			footprint = createFootprint();
			AST ast = eclModule.getAst();
			calculateFootprint(footprint, ast);
		}
		return footprint;
	}

	private void initialize() {
		this.eclModule = new EclModule();
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource modelResource = resourceSet.createResource(URI.createPlatformPluginURI("dummymodel.xmi", true));
		InMemoryEmfModel model = new InMemoryEmfModel("dummymodel", modelResource, metamodelEPackage);
		eclModule.getContext().getModelRepository().addModel(model);

		try {
			eclModule.parse(eclFile);
		} catch (Exception e) {
			eclModule = null;
		}

		astAnalyzerFactory = new ASTAnalyzerFactory(eclModule);
	}

	private boolean canAnalyze() {
		return eclModule != null && metamodelEPackage != null;
	}

	@Override
	protected Footprint createFootprint() {
		Footprint footprint = super.createFootprint();
		String eclModuleURI = eclModule.getSourceUri().toString();
		footprint.setAnalyzedObjectURI(eclModuleURI);
		return footprint;
	}

	private void calculateFootprint(Footprint footprint, AST ast) {
		EModelElement accessedEModelElement = getAccessedEModelElement(ast);
		if (accessedEModelElement != null) {
			EModelElementAccess eModelElementAccess = getOrCreateEModelElementAccessObject(footprint,
					accessedEModelElement);
			EOLLocation eolLocation = createElementAccessLocation(ast);
			eModelElementAccess.getAccessLocations().add(eolLocation);
		}
		for (AST childAST : ast.getChildren()) {
			calculateFootprint(footprint, childAST);
		}
	}

	private EModelElement getAccessedEModelElement(AST ast) {
		ASTAnalyzer astAnalyzer = astAnalyzerFactory.getAnalyzer(ast);
		EModelElement accessedEModelElement = astAnalyzer.getAccessedEModelElement(ast);
		return accessedEModelElement;
	}

	private EModelElementAccess getOrCreateEModelElementAccessObject(Footprint footprint,
			EModelElement accessedEModelElement) {
		EModelElementAccess eModelElementAccess = eModelElementAccessStore.get(accessedEModelElement);
		if (eModelElementAccess == null) {
			eModelElementAccess = MmfootprintFactory.eINSTANCE.createEModelElementAccess();
			eModelElementAccess.setEModelElement(accessedEModelElement);
			eModelElementAccessStore.put(accessedEModelElement, eModelElementAccess);
			footprint.getEModelElementAccesses().add(eModelElementAccess);
		}
		return eModelElementAccess;
	}

	private EOLLocation createElementAccessLocation(AST ast) {
		EOLLocation eolLocation = MmfootprinteolFactory.eINSTANCE.createEOLLocation();
		eolLocation.setColumn(ast.getColumn());
		eolLocation.setLine(ast.getLine());
		eolLocation.setTokenStartIndex(ast.getTokenStartIndex());
		eolLocation.setTokenStopIndex(ast.getTokenStopIndex());
		return eolLocation;
	}

	public EclModule getEclModule() {
		return eclModule;
	}

}
