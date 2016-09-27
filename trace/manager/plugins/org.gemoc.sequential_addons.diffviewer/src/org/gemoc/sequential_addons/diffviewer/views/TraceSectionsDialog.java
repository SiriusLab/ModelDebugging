package org.gemoc.sequential_addons.diffviewer.views;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;

public class TraceSectionsDialog extends TitleAreaDialog {

	private Text txts1;
	private Text txts2;
	private Text txte1;
	private Text txte2;

	private int s1 = -1;
	private int s2 = -1;
	private int e1 = -1;
	private int e2 = -1;

	private ITraceExtractor extractor1;
	private ITraceExtractor extractor2;

	public TraceSectionsDialog(Shell parentShell, ITraceExtractor extractor1, ITraceExtractor extractor2) {
		super(parentShell);
		this.extractor1 = extractor1;
		this.extractor2 = extractor2;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Trace Sections");
		setMessage("Enter starting and ending states of trace sections", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		txts1 = createLabelText(container, "First Trace Starting Index", extractor1);
		txts2 = createLabelText(container, "Second Trace Starting Index", extractor2);
		txte1 = createLabelText(container, "First Trace Ending Index", extractor1);
		txte2 = createLabelText(container, "Second Trace Ending Index", extractor2);

		return area;
	}

	private Text createLabelText(Composite container, String title, ITraceExtractor extractor) {
		Label label = new Label(container, SWT.NONE);
		label.setText(title);

		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;

		Text text = new Text(container, SWT.BORDER);
		text.setLayoutData(data);
		text.addModifyListener(m -> {
//			String currentText = ((Text) m.widget).getText();
//			try {
//				int i = Integer.parseInt(currentText);
//				if (!(i > -1 && i < extractor.getStatesTraceLength())) {
//					this.buttonBar.setEnabled(true);
//				} else {
//					this.buttonBar.setEnabled(false);
//				}
//			} catch (NumberFormatException e) {
//				this.buttonBar.setEnabled(false);
//			}
		});

		return text;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		s1 = new Integer(txts1.getText());
		s2 = new Integer(txts2.getText());
		e1 = new Integer(txte1.getText());
		e2 = new Integer(txte2.getText());
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public int getS1() {
		return s1;
	}

	public int getS2() {
		return s2;
	}

	public int getE1() {
		return e1;
	}

	public int getE2() {
		return e2;
	}
}