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
package fr.obeo.dsl.debug.ide.sirius.ui;

import fr.obeo.dsl.debug.ide.sirius.ui.services.AbstractDSLDebuggerServices;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the Debug IDE UI plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class DebugSiriusIdeUiPlugin extends EMFPlugin {

	/**
	 * The plug-in identifier.
	 */
	public static final String ID = "fr.obeo.dsl.debug.ide.sirius.ui";

	/**
	 * Keep track of the singleton.
	 */
	public static final DebugSiriusIdeUiPlugin INSTANCE = new DebugSiriusIdeUiPlugin();

	/**
	 * Keep track of the singleton.
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 */
	public DebugSiriusIdeUiPlugin() {
		super(new ResourceLocator[] {});
	}

	/**
	 * Returns the singleton instance of the Eclipse plug-in.
	 * 
	 * @return the singleton instance.
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plug-in.
	 * 
	 * @return the singleton instance.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plug-in</b>.
	 */
	public static class Implementation extends EclipsePlugin {
		/**
		 * Creates an instance.
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}

		@Override
		public void start(BundleContext context) throws Exception {
			AbstractDSLDebuggerServices.LISTENER.install();
			super.start(context);
		}

		@Override
		public void stop(BundleContext context) throws Exception {
			AbstractDSLDebuggerServices.LISTENER.uninstall();
			super.stop(context);
		}

	}

}
