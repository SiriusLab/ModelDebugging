package fr.inria.diverse.trace.plugin.generator;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class FakeModelImporterDetailPage extends ModelImporterDetailPage {

	private Composite fakeParent;

	public FakeModelImporterDetailPage(ModelImporter modelImporter,
			String pageName) {
		super(modelImporter, pageName);
	}

	public void fakeSetParent(Composite c) {
		this.fakeParent = c;
	}

	public void fakeSetURI(String uri) {
		this.uriText = new Text(fakeParent, 0);
		uriText.setText(uri);
		uriTextModified("");
	}

	public void fakeLoad() {
		refreshModel();
	}

	@Override
	protected void internalSetGenModelFileName(String name) {
		if (usingInternalSetName && showGenModel() && name != null) {
	      getModelImporter().setGenModelFileName(name);
	    }
	}
}
