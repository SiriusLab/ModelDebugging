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
package fr.obeo.dsl.debug.ide.adapter;

import fr.obeo.dsl.debug.StackFrame;
import fr.obeo.dsl.debug.ThreadUtils;
import fr.obeo.dsl.debug.Variable;
import fr.obeo.dsl.debug.ide.DSLEclipseDebugIntegration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

/**
 * The {@link StackFrame} DSL debug model.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLStackFrameAdapter extends AbstractDSLDebugElementAdapter implements IStackFrame {

	/**
	 * The {@link IThread} containing this {@link IStackFrame}. Needed after popping the stack.
	 */
	private IThread thread;

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 */
	public DSLStackFrameAdapter(DSLEclipseDebugIntegration factory) {
	super(factory);
	}

	@Override
	public boolean isAdapterForType(Object type) {
	return super.isAdapterForType(type) || type == IStackFrame.class;
	}

	/**
	 * Gets the {@link StackFrame}.
	 * 
	 * @return the {@link StackFrame}
	 */
	public StackFrame getHost() {
	assert target instanceof StackFrame;
	return (StackFrame)target;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepInto()
	 */
	public boolean canStepInto() {
	return getThread().canStepInto();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepOver()
	 */
	public boolean canStepOver() {
	return getThread().canStepOver();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepReturn()
	 */
	public boolean canStepReturn() {
	return getThread().canStepReturn();
	}

	public boolean isStepping() {
	return getThread().isStepping();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#stepInto()
	 */
	public void stepInto() throws DebugException {
	getThread().stepInto();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
	getThread().stepOver();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
	getThread().stepReturn();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
	return getThread().canResume();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
	return getThread().canSuspend();
	}

	public boolean isSuspended() {
	return getThread().isSuspended();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
	getThread().resume();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
	getThread().suspend();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
	 */
	public boolean canTerminate() {
	return getThread().canTerminate();
	}

	public boolean isTerminated() {
	return getThread().isTerminated();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException {
	getThread().terminate();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getThread()
	 */
	public IThread getThread() {
	if (thread == null) {
		thread = (IThread)factory.adapt(ThreadUtils.getThread(getHost()), IThread.class);
		if (thread == null) {
		throw new IllegalStateException("can't addapt Thread to IThread.");
		}
	}
	return thread;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getVariables()
	 */
	public IVariable[] getVariables() throws DebugException {
	final List<IVariable> res = new ArrayList<IVariable>();

	for (Variable variable : getHost().getVariables()) {
		final IVariable var = (IVariable)factory.adapt(variable, IVariable.class);
		if (var != null) {
		res.add(var);
		} else {
		throw new IllegalStateException("can't addapt Variable to IVariable.");
		}
	}

	return res.toArray(new IVariable[res.size()]);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#hasVariables()
	 */
	public boolean hasVariables() throws DebugException {
	return getHost().getVariables().size() > 0;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getLineNumber()
	 */
	public int getLineNumber() throws DebugException {
	EObject context = getContext();
	INode node = NodeModelUtils.getNode(context);
	if (node != null) {
		return node.getStartLine();
	}
	return -1;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getCharStart()
	 */
	public int getCharStart() throws DebugException {
	return -1;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getCharEnd()
	 */
	public int getCharEnd() throws DebugException {
	return -1;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IStackFrame#getName()
	 */
	public String getName() throws DebugException {
	return getHost().getName();
	}

	public IRegisterGroup[] getRegisterGroups() throws DebugException {
	// TODO Auto-generated method stub
	return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.debug.core.model.IStackFrame#hasRegisterGroups()
	 */
	public boolean hasRegisterGroups() throws DebugException {
	return getHost().getRegisterGroups().size() > 0;
	}

	/**
	 * Gets the {@link StackFrame#getCurrentInstruction() current instruction}.
	 * 
	 * @return the {@link StackFrame#getCurrentInstruction() current instruction}
	 */
	public EObject getCurrentInstruction() {
	return getHost().getCurrentInstruction();
	}

	/**
	 * Gets the {@link StackFrame#getContext() context}.
	 * 
	 * @return the {@link StackFrame#getContext() context}
	 */
	public EObject getContext() {
	return getHost().getContext();
	}

}
