package fr.inria.diverse.trace.plugin.generator;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;

public class FakeModelImporterPackagePage extends ModelImporterPackagePage {

	public FakeModelImporterPackagePage(ModelImporter modelImporter,
			String pageName) {
		super(modelImporter, pageName);
	}

	public void checkAll() {
		// This is done at the creation of the page, theoretically
		filterEPackagesTable(true);
		validate();

		// We check all the referenced stuff
		for (org.eclipse.jface.viewers.TreePath tp : referencedGenModelsCheckboxTreeViewer.getExpandedTreePaths())
			referencedGenModelsCheckboxTreeViewer.setSubtreeChecked(tp, true);

		// We notify
		referencedGenModelsCheckboxTreeViewerCheckStateChanged(null);
		
		// We check all the remaining epackages
		ePackagesCheckboxTableViewer.setAllChecked(true);

		// We notify
		ePackageCheckStateChanged();

	}

}
