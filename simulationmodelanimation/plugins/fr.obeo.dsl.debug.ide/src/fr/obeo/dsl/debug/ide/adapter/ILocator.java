package fr.obeo.dsl.debug.ide.adapter;

import org.eclipse.emf.ecore.EObject;

/*******************************************************************************
 * Copyright (c) 2015 Obeo. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/

public interface ILocator {

	enum Type {
		XTEXT_LOCATION("Xtext"), SIRIUS_LOCATION("Sirius");

		public final String typeName;

		Type(final String typeName) {
			this.typeName = typeName;
		}
	}

	Location getLocation(EObject eObject);

	class Location {

		final Type type;

		final Object data;

		public Location(final Type type, final Object data) {
			this.type = type;
			this.data = data;
		}
	}
}
