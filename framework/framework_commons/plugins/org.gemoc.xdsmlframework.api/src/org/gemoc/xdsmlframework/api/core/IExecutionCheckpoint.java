package org.gemoc.xdsmlframework.api.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Checkpoint allowing execution.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public interface IExecutionCheckpoint {

	/**
	 * Maps {@link ResourceSet} to {@link IExecutionCheckpoint}.
	 */
	Map<ResourceSet, IExecutionCheckpoint> CHECKPOINTS = new HashMap<ResourceSet, IExecutionCheckpoint>();

	/**
	 * Tells if execution is allowed.
	 * 
	 * @param rs
	 *            the {@link ResourceSet} to allow/forbid
	 * @param allow
	 *            if <code>true</code> execution is allowed, forbidden otherwise
	 */
	void allow(ResourceSet rs, boolean allow);
}
