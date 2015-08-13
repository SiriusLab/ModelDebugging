/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.gemoc.gemoc_language_workbench.dashboard.utils;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Ease KeyEvent handling.
 */
public class AbstractKeyAdapter extends KeyAdapter {
	/**
	 * Constructor.
	 */
	public AbstractKeyAdapter() {
		super();
	}

	/**
	 * Is given key representation the one that raises the specified key event.
	 * 
	 * @param event_p
	 * @param keyRepresentation_p
	 * @return
	 */
	protected boolean handle(KeyEvent event_p, String keyRepresentation_p) {
		boolean result = false;
		// Do not handle directly the event, as the key bindings may be different from the formal one (e.g
		// Emacs key binding).
		KeyStroke keyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport
				.convertEventToUnmodifiedAccelerator(event_p));
		try {
			result = keyStroke.equals(KeyStroke.getInstance(keyRepresentation_p));
		} catch (ParseException exception_p) {
			// Ignore errors.
		}
		return result;
	}

	/**
	 * Is CTRL pressed for specified key event.
	 * 
	 * @param event_p
	 * @return
	 */
	protected boolean isCtrlPressed(KeyEvent event_p) {
		IKeyLookup keyLookup = KeyLookupFactory.getSWTKeyLookup();
		return keyLookup.getCtrl() == event_p.stateMask || keyLookup.getCtrl() == event_p.keyCode;
	}
}
