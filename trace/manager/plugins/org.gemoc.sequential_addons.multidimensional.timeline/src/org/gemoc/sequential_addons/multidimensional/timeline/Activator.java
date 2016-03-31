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
package org.gemoc.sequential_addons.multidimensional.timeline;

import java.util.function.Supplier;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.gemoc.sequential_addons.multidimensional.timeline"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private Supplier<MultidimensionalTimeLineView> multidimensionalTimeLineViewSupplier;


	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		try {
			Class.forName("javafx.embed.swt.FXCanvas");
			super.start(context);
			plugin = this;	
		} catch (ClassNotFoundException e) {
			ErrorDialog.openError(null,"Multidimensional Timeline Unavailable", "JavaFX is needed for the timeline to work",
					new Status(IStatus.ERROR,PLUGIN_ID,"JavaFX is needed for the timeline to work"));
		}	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}


	public static void warn(String msg, Throwable e){
		Activator.getDefault().getLog().log(new Status(Status.WARNING, PLUGIN_ID,
                Status.OK, 
                msg, 
                e));
	}
	public static void error(String msg, Throwable e){
		Activator.getDefault().getLog().log(new Status(Status.ERROR, PLUGIN_ID,
                Status.OK, 
                msg, 
                e));
	}

	public Supplier<MultidimensionalTimeLineView> getMultidimensionalTimeLineViewSupplier() {
		return multidimensionalTimeLineViewSupplier;
	}
	
	public void setMultidimensionalTimeLineViewSupplier(Supplier<MultidimensionalTimeLineView> multidimensionalTimeLineViewSupplier) {
		this.multidimensionalTimeLineViewSupplier = multidimensionalTimeLineViewSupplier;
	}

}
