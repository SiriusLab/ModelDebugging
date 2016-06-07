/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.debug.ide.adapter.value;

import fr.obeo.dsl.debug.ide.DSLEclipseDebugIntegration;

/**
 * Array of byte {@link org.eclipse.debug.core.model.IValue IValue}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLByteArrayValue extends DSLArrayValue {

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 * @param referenceTypeName
	 *            the reference type name
	 * @param array
	 *            the array of {@link Object}
	 */
	public DSLByteArrayValue(DSLEclipseDebugIntegration factory, String referenceTypeName, Byte[] array) {
		super(factory, referenceTypeName, array);
	}

	@Override
	protected String getActualTypeName(Object[] array) {
		return byte.class.getCanonicalName();
	}

}
