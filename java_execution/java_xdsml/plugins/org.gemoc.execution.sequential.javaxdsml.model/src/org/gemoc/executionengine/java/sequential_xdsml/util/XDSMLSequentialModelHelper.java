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
