package org.gemoc.executionframework.extensions.sirius.services;

import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

/**
 * Animator interface.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 * 
 */
public interface IModelAnimator extends IEngineAddon {

	/**
	 * Activates the given {@link LogicalStep}.
	 * 
	 * @param step
	 *            the {@link LogicalStep}
	 * @param context
	 *            the context {@link Object}
	 */
	void activate(Object context, LogicalStep step);

	/**
	 * Clears {@link IModelAnimator#activate(LogicalStep) activated}
	 * {@link LogicalStep}.
	 * 
	 * @param context
	 *            the context {@link Object}
	 */
	void clear(Object context);

}
