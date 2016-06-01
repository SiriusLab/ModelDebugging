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
package fr.inria.diverse.trace.plugin.generator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;

import fr.inria.diverse.trace.commons.EMFUtil;

public class FakeModelImporterPackagePage extends ModelImporterPackagePage {

	public FakeModelImporterPackagePage(ModelImporter modelImporter,
			String pageName) {
		super(modelImporter, pageName);
	}

	public void checkAll(URI except) {
		// This is done at the creation of the page, theoretically
		filterEPackagesTable(true);
		validate();


		// We have to load the epackages in except (!= epackages referenced by except), to find the corresponding nsURIs
		// The goal is not to check theses epackages as referenced!
		// Because they might already have genmodels, which are hence referenceable
		ResourceSet tmp = new ResourceSetImpl();
		Set<String> nsURIsExcept = new HashSet<String>();
		try {
			Resource r = EMFUtil.loadModelURI(except, tmp);
			for (EObject o : r.getContents()) {
				if (o instanceof EPackage) {
					nsURIsExcept.add(((EPackage) o).getNsURI());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		// We check all the referenced stuff, except the existing genmodels for the epackage we are interested in  
		for (org.eclipse.jface.viewers.TreePath tp : referencedGenModelsCheckboxTreeViewer.getExpandedTreePaths()) {
			Object fSegment = tp.getFirstSegment();
			boolean ok = true;
			if (fSegment instanceof GenModel) {
				GenModel fSegment_cast = (GenModel)fSegment;
				for (GenPackage genp : fSegment_cast.getGenPackages()) {
					String nsURI = genp.getEcorePackage().getNsURI();
					if (nsURIsExcept.contains(nsURI)) {
						ok = false;
						break;
					}
				}
				
			}
			if (ok)
				referencedGenModelsCheckboxTreeViewer.setSubtreeChecked(tp, true);
		}
			

		// We notify
		referencedGenModelsCheckboxTreeViewerCheckStateChanged(null);
		
		// We check all the remaining epackages
		ePackagesCheckboxTableViewer.setAllChecked(true);
	
		// We notify
		ePackageCheckStateChanged();

	}
	
	public List<GenPackage> getReferencedGenPackages() {
		return this.getModelConverter().getReferencedGenPackages();
	} 

}
