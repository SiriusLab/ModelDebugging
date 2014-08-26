package org.gemoc.gemoc_language_workbench.sample.deployer;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.gemoc.gemoc_language_workbench.sample.deployer"; //$NON-NLS-1$
	
	private static BundleContext context;
	
	// The shared instance
	private static Activator plugin;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	/**
	 * Log a message with given level into the Eclipse log file.
	 * 
	 * @param message
	 *            the message to log
	 * @param level
	 *            the message priority
	 */
	private void log(String message, int level) {
		log(new Status(level, PLUGIN_ID, message));
	}

	/**
	 * Logs the given {@link Status}.
	 * 
	 * @param status
	 *            the {@link Status}
	 */
	private void log(IStatus status) {

		if (plugin == null) {
			// TODO Do log with java ?
		} else {
			plugin.getLog().log(status);
		}
	}

	/**
	 * Log a warning message.
	 * 
	 * @param message
	 *            the exception to log
	 */
	public void warn(String message) {
		log(message, IStatus.WARNING);
	}

	/**
	 * Log an exception into the Eclipse log file.
	 * 
	 * @param e
	 *            the exception to log
	 */
	public void error(Throwable e) {
		error("Unexpected Error", e);
	}

	/**
	 * Log an exception into the Eclipse log file.
	 * 
	 * @param message
	 *            the message
	 * @param e
	 *            the exception to log
	 */
	public void error(String message, Throwable e) {

		Throwable t = e;
		if (e instanceof InvocationTargetException) {
			t = ((InvocationTargetException)e).getTargetException();
		}

		IStatus status;
		if (t instanceof CoreException) {
			status = ((CoreException)t).getStatus();
		} else {
			status = new Status(IStatus.ERROR, PLUGIN_ID, message, e);
		}

		log(status);
	}
	
}
