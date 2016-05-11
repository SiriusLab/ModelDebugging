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
package org.gemoc.executionframework.engine.core;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.gemoc.executionframework.engine.Activator;
import org.gemoc.executionframework.engine.mse.GenericMSE;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEModel;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.MseFactory;
import org.gemoc.executionframework.engine.mse.SequentialStep;
import org.gemoc.executionframework.engine.mse.Step;
import org.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;

public abstract class AbstractSequentialExecutionEngine extends AbstractExecutionEngine implements ISequentialExecutionEngine {

	private Runnable _runnable;
	private MSEModel _actionModel;
	private EMFCommandTransaction currentTransaction;
	private Deque<Step> currentSteps = new ArrayDeque<>();
	protected InternalTransactionalEditingDomain editingDomain;
	private IMultiDimensionalTraceAddon traceAddon;

	abstract protected void executeEntryPoint();

	abstract protected void initializeModel();

	abstract protected void prepareEntryPoint(IExecutionContext executionContext);

	abstract protected void prepareInitializeModel(IExecutionContext executionContext);

	@Override
	public final void initialize(IExecutionContext executionContext) {
		super.initialize(executionContext);
		this.editingDomain = getEditingDomain(executionContext.getResourceModel().getResourceSet());
		Set<IMultiDimensionalTraceAddon> traceManagers = this.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
		if (!traceManagers.isEmpty())
			this.traceAddon = traceManagers.iterator().next();

		prepareEntryPoint(executionContext);
		prepareInitializeModel(executionContext);

		_runnable = new Runnable() {
			@Override
			public void run() {
				try {
					initializeModel();
					executeEntryPoint();
					Activator.getDefault().info("Execution finished");
				} finally {
					// We always try to commit the last remaining transaction
					commitCurrentTransaction();
				}
			}
		};

	}

	private void cleanCurrentTransactionCommand() {
		assert currentTransaction != null;
		if (currentTransaction.getCommand() != null)
			currentTransaction.getCommand().dispose();
	}

	@Override
	public final Deque<MSEOccurrence> getCurrentStack() {
		Deque<MSEOccurrence> result = new ArrayDeque<MSEOccurrence>();
		for (Step ls : currentSteps) {
			result.add(ls.getMseoccurrence());
		}
		return result;
	}

	@Override
	public final MSEOccurrence getCurrentMSEOccurrence() {
		if (currentSteps.size() > 0)
			return currentSteps.getFirst().getMseoccurrence();
		else
			return null;
	}

	private static InternalTransactionalEditingDomain getEditingDomain(ResourceSet rs) {
		TransactionalEditingDomain edomain = org.eclipse.emf.transaction.TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rs);
		if (edomain instanceof InternalTransactionalEditingDomain)
			return (InternalTransactionalEditingDomain) edomain;
		else
			return null;
	}

	@Override
	protected final Runnable getRunnable() {
		return _runnable;
	}

	private EMFCommandTransaction createTransaction(InternalTransactionalEditingDomain editingDomain, RecordingCommand command) {
		return new EMFCommandTransaction(command, editingDomain, null);
	}

	private void commitCurrentTransaction() {
		if (currentTransaction != null) {
			try {
				currentTransaction.commit();
			} catch (RollbackException t) {

				cleanCurrentTransactionCommand();

				// Extracting the real error from the RollbackException
				Throwable realT = t.getStatus().getException();

				// And we put it inside our own sort of exception, as a cause
				SequentialExecutionException enclosingException = new SequentialExecutionException(getCurrentMSEOccurrence(), realT);
				enclosingException.initCause(realT);
				throw enclosingException;
			}
			currentTransaction = null;
		}
	}

	private void startNewTransaction(InternalTransactionalEditingDomain editingDomain, RecordingCommand command) {
		currentTransaction = createTransaction(editingDomain, command);
		try {
			currentTransaction.start();
		} catch (InterruptedException e) {
			cleanCurrentTransactionCommand();
			command.dispose();
			SequentialExecutionException enclosingException = new SequentialExecutionException(getCurrentMSEOccurrence(), e);
			enclosingException.initCause(e);
			throw enclosingException;
		}
	}

	private Step createStep(EObject caller, String className, String methodName) {
		MSE mse = findOrCreateMSE(caller, className, methodName);
		Step result;
		if (traceAddon == null) {
			SequentialStep<Step> step = MseFactory.eINSTANCE.createGenericSequentialStep();
			MSEOccurrence occurrence = null;
			occurrence = MseFactory.eINSTANCE.createMSEOccurrence();
			step.setMseoccurrence(occurrence);
			occurrence.setMse(mse);
			result = step;
		} else {
			result = traceAddon.getFactory().createStep(mse, new ArrayList<Object>(), new ArrayList<Object>());
		}
		currentSteps.push(result);
		return result;
	}

	private boolean isInStep() {

		boolean containsNotNull = false;

		for (Step ls : currentSteps) {
			if (ls != null && ls.getMseoccurrence() != null) {
				containsNotNull = true;
				break;
			}
		}

		return !currentSteps.isEmpty() && containsNotNull;

	}

	private static String getFQN(EClassifier c, String separator) {
		EPackage p = c.getEPackage();
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.getName();
		} else {
			return c.getName();
		}
	}

	private static String getEPackageFQN(EPackage p, String separator) {
		EPackage superP = p.getESuperPackage();
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.getName();
		} else {
			return p.getName();
		}
	}

	private EOperation findOperation(EObject object, String className, String methodName) {

		// We try to find the corresponding EOperation in the execution
		// metamodel
		for (EOperation operation : object.eClass().getEAllOperations()) {
			// TODO !!! this is not super correct yet as overloading allows the
			// definition of 2 methods with the same name !!!
			if (operation.getName().equalsIgnoreCase(methodName)) {
				return operation;
			}
		}

		// If we didn't find it, we try to find the class that should contain
		// this operation
		EClass containingEClass = null;
		if (object.eClass().getName().equalsIgnoreCase(className)) {
			containingEClass = object.eClass();
		} else {
			for (EClass candidate : object.eClass().getEAllSuperTypes()) {
				if (candidate.getName().equalsIgnoreCase(className)) {
					containingEClass = candidate;
				}
			}
		}

		// Then we create the missing operation (VERY approximatively)
		EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
		if (containingEClass != null) {
			containingEClass.getEOperations().add(operation);
		}
		operation.setName(methodName);
		return operation;
	}

	public final MSE findOrCreateMSE(EObject caller, String className, String methodName) {

		EOperation operation = findOperation(caller, className, methodName);

		// TODO Should be created somewhere before...
		// at some point didier had written some code to serialize it... I think
		if (_actionModel == null) {
			_actionModel = MseFactory.eINSTANCE.createMSEModel();
		}

		if (_actionModel != null) {
			for (MSE existingMSE : _actionModel.getOwnedMSEs()) {
				if (existingMSE.getCaller().equals(caller) && ((existingMSE.getAction() != null && existingMSE.getAction().equals(operation)) || (existingMSE.getAction() == null && operation == null))) {
					// no need to create one, we already have it
					return existingMSE;
				}
			}
		}
		// let's create a MSE
		final GenericMSE mse = MseFactory.eINSTANCE.createGenericMSE();
		mse.setCallerReference(caller);
		mse.setActionReference(operation);
		if (operation != null)
			mse.setName("MSE_" + caller.getClass().getSimpleName() + "_" + operation.getName());
		else
			mse.setName("MSE_" + caller.getClass().getSimpleName() + "_" + methodName);
		// and add it for possible reuse
		if (_actionModel != null) {

			if (_actionModel.eResource() != null) {
				TransactionUtil.getEditingDomain(_actionModel.eResource());
				RecordingCommand command = new RecordingCommand(TransactionUtil.getEditingDomain(_actionModel.eResource()), "Saving new MSE ") {
					@Override
					protected void doExecute() {
						_actionModel.getOwnedMSEs().add(mse);
						try {
							_actionModel.eResource().save(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				TransactionUtil.getEditingDomain(_actionModel.eResource()).getCommandStack().execute(command);
			}
		} else {
			_actionModel.getOwnedMSEs().add(mse);
		}
		return mse;
	}

	/**
	 * To be called just before each execution step by an implementing engine.
	 */
	protected final void beforeExecutionStep(Object caller, String className, String operationName) {

		// We will trick the transaction with an empty command. This most
		// probably make rollbacks impossible, but at least we can manage
		// transactions the way we want.
		RecordingCommand rc = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
			}
		};

		beforeExecutionStep(caller, className, operationName, rc);
		rc.execute();
	}

	private void stopExecutionIfAsked() {
		// If the engine is stopped, we use this call to stop the execution
		if (_isStopped) {
			// notification occurs only if not already stopped
			notifyAboutToStop();
			throw new EngineStoppedException("Execution stopped.");
		}
	}

	/**
	 * To be called just after each execution step by an implementing engine. If
	 * the step was done through a RecordingCommand, it can be given.
	 */
	protected final void beforeExecutionStep(Object caller, String className, String operationName, RecordingCommand rc) {

		try {
			stopExecutionIfAsked();

			// We end any running transaction
			commitCurrentTransaction();

			if (caller != null && caller instanceof EObject && editingDomain != null) {

				// Call expected to be done from an EMF model, hence EObjects
				EObject caller_cast = (EObject) caller;

				// We create a step
				Step step = createStep(caller_cast, className, operationName);

				// We notify addons
				notifyAboutToExecuteLogicalStep(step);

			}

			// We start a new transaction
			startNewTransaction(editingDomain, rc);

		}

		// In case of error, we dispose recording commands to be sure to remove
		// notifiers
		catch (Throwable t) {
			cleanCurrentTransactionCommand();
			rc.dispose();
			throw t;
		}

	}

	/**
	 * To be called just after each execution step by an implementing engine.
	 */
	protected final void afterExecutionStep() {

		RecordingCommand emptyrc = null;

		try {

			Step step = currentSteps.pop();

			// We commit the transaction (which might be a different one
			// than the one created earlier, or null if two operations
			// end successively)
			commitCurrentTransaction();

			// We notify addons that the step ended.
			notifyLogicalStepExecuted(step);

			// If we are still in the middle of a step, we start a new
			// transaction with an empty command (since we can't have command
			// containing the remainder of the previous step),
			if (isInStep()) {
				emptyrc = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
					}
				};
				startNewTransaction(editingDomain, emptyrc);
				emptyrc.execute();
			}
			engineStatus.incrementNbLogicalStepRun();

			stopExecutionIfAsked();
		}

		// In case of error, we dispose recording commands to be sure to remove
		// notifiers
		catch (Throwable t) {
			cleanCurrentTransactionCommand();
			if (emptyrc != null)
				emptyrc.dispose();
			throw t;
		}

	}

}
