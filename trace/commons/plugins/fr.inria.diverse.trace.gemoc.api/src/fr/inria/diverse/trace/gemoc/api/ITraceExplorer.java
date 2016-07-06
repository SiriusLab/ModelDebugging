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
package fr.inria.diverse.trace.gemoc.api;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceExplorer extends ITraceNotifier, ITraceListener {
	
	Step getCurrentForwardStep();

	Step getCurrentBackwardStep();

	Step getCurrentBigStep();

	int getCurrentStateIndex();

	void jump(EObject o);

	void jump(int i);

	void loadLastState();

	boolean stepInto();

	boolean stepOver();

	boolean stepReturn();

	boolean canStepBackInto();

	boolean canStepBackOver();

	boolean canStepBackOut();

	boolean stepBackInto();

	boolean stepBackOver();

	boolean stepBackOut();

	void stepValue(int traceIndex);

	void backValue(int traceIndex);

	boolean canStepValue(int traceIndex);

	boolean canBackValue(int traceIndex);

	boolean isInReplayMode();

	List<Step> getCallStack();

	void updateCallStack(Step step);
}
