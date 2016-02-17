package org.gemoc.execution.sequential.javaxdsml.ide.ui.templates;

import org.eclipse.xtext.util.Strings;

import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.ui.templates.ITemplateSection;
import fr.inria.diverse.melange.ui.templates.melange.SimpleMTNewWizard;

public class SequentialNewWizard extends SimpleMTNewWizard{

	@Override
	public void init(BaseProjectWizardFields data) {
		super.init(data);
		setWindowTitle("Simple sequential GEMOC project");
		
		try {
			String project = getData().projectName;
			String SUFFIX = ".xdsml";
			if(project.endsWith(SUFFIX)){
				
				int startSuffix = project.length() - SUFFIX.length();
				int startName = project.lastIndexOf(".", startSuffix-1) + 1;
				
				String packageName = project.substring(0, startName - 1).toLowerCase();
				String languageName = project.substring(startName, startSuffix);
				languageName = Strings.toFirstUpper(languageName);

				ITemplateSection[] selections = getTemplateSections();
				SequentialTemplate selection = (SequentialTemplate) selections[0];
				selection.updateOptions(packageName, languageName);
			}
		} catch (Exception e) {}
	}
	
	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {new SequentialTemplate()};
	}
}
