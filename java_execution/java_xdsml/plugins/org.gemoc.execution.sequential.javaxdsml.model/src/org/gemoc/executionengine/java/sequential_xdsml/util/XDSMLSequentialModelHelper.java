package org.gemoc.executionengine.java.sequential_xdsml.util;

import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlFactory;
import org.gemoc.executionframework.xdsml_base.util.XDSMLBaseModelHelper;

public class XDSMLSequentialModelHelper extends XDSMLBaseModelHelper{
	

	public static DSAProject getOrCreateDSAProject(
			SequentialLanguageDefinition languageDefinition) {
		if (languageDefinition.getDsaProject() == null) {
			languageDefinition.setDsaProject(Sequential_xdsmlFactory.eINSTANCE
					.createDSAProject());
		}
		return languageDefinition.getDsaProject();
	}

}
