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
import org.gemoc.executionframework.engine.mse.MseFactory;
import org.gemoc.executionframework.engine.mse.GenericMSE;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEModel;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.EngineStatus;
import org.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;

public abstract class AbstractSequentialExecutionEngine extends AbstractExecutionEngine implements ISequentialExecutionEngine {

	private Runnable _runnable;
	private MSEModel _actionModel;
	private EMFCommandTransaction currentTransaction;
	private Deque<LogicalStep> currentLogicalSteps = new ArrayDeque<LogicalStep>();
	protected InternalTransactionalEditingDomain editingDomain;
	private IMultiDimensionalTraceAddon traceAddon;

	@Override
	public void initialize(IExecutionContext executionContext) {
		super.initialize(executionContext);
		this.editingDomain = getEditingDomain(executionContext.getResourceModel().getResourceSet());
		Set<IMultiDimensionalTraceAddon> traceManagers = this.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
		if (!traceManagers.isEmpty())
			this.traceAddon = traceManagers.iterator().next();
		_runnable = new Runnable() {
			@Override
			public void run() {
				try {
					Runnable initializeModel = getInitializeModel();
					if (initializeModel != null) {
						initializeModel.run();
					}
					getEntryPoint().run();
					Activator.getDefault().info("Execution finished");
					notifyAboutToStop();
				} catch (EngineStoppedException stopExeception) {
					// not really an error, simply forward the stop exception
					throw stopExeception;
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					setEngineStatus(EngineStatus.RunStatus.Stopped);
					notifyEngineStopped();

					// We always try to commit the last remaining transaction
					try {
						commitCurrentTransaction();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		};
	}

	@Override
	public Deque<MSEOccurrence> getCurrentStack() {
		Deque<MSEOccurrence> result = new ArrayDeque<MSEOccurrence>();
		for (LogicalStep ls : currentLogicalSteps) {
			result.add(ls.getMseOccurrences().get(0));
		}
		return result;
	}

	@Override
	public MSEOccurrence getCurrentMSEOccurrence() {
		if (currentLogicalSteps.size() > 0)
			return currentLogicalSteps.getFirst().getMseOccurrences().get(0);
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
	protected Runnable getRunnable() {
		return _runnable;
	}

	private void notifyMSEOccurenceExecuted(MSEOccurrence occurrence) {
		for (IEngineAddon addon : getExecutionContext().getExecutionPlatform().getEngineAddons()) {
			try {
				addon.mseOccurrenceExecuted(this, occurrence);
			} catch (EngineStoppedException ese) {
				Activator.getDefault().info("Addon has received stop command (" + addon + "), " + ese.getMessage(), ese);
				stop();
			} catch (Exception e) {
				Activator.getDefault().error("Exception in Addon (" + addon + "), " + e.getMessage(), e);
			}
		}
	}

	private void notifyMSEOccurrenceAboutToStart(MSEOccurrence occurrence) {
		for (IEngineAddon addon : getExecutionContext().getExecutionPlatform().getEngineAddons()) {
			try {
				addon.aboutToExecuteMSEOccurrence(this, occurrence);
			} catch (EngineStoppedException ese) {
				Activator.getDefault().info("Addon has received stop command (" + addon + "), " + ese.getMessage(), ese);
				stop();
			} catch (Exception e) {
				Activator.getDefault().error("Exception in Addon (" + addon + "), " + e.getMessage(), e);
			}
		}
	}

	private EMFCommandTransaction createTransaction(InternalTransactionalEditingDomain editingDomain, RecordingCommand command) {

		return new EMFCommandTransaction(command, editingDomain, null);
	}

	private void commitCurrentTransaction() throws Exception {
		if (currentTransaction != null) {
			try {
				currentTransaction.commit();
			} catch (RollbackException t) {

				// Throwing the real error
				Throwable realT = t.getStatus().getException();
				if (realT instanceof Exception)
					throw (Exception) realT;
			}
			currentTransaction = null;
		}
	}

	private void startNewTransaction(InternalTransactionalEditingDomain editingDomain, RecordingCommand command) throws InterruptedException {
		currentTransaction = createTransaction(editingDomain, command);
		currentTransaction.start();
	}

	private LogicalStep createLogicalStep(EObject caller, String className, String methodName) {
		LogicalStep logicalStep = MseFactory.eINSTANCE.createLogicalStep();
		MSE mse = findOrCreateMSE(caller, className, methodName);
		MSEOccurrence occurrence = null;
		if (traceAddon == null) {
			occurrence = MseFactory.eINSTANCE.createMSEOccurrence();
			occurrence.setLogicalStep(logicalStep);
			occurrence.setMse(mse);
		} else {
			occurrence = traceAddon.getFactory().createMSEOccurrence(mse, new ArrayList<Object>(), new ArrayList<Object>());
			occurrence.setLogicalStep(logicalStep);
		}
		currentLogicalSteps.push(logicalStep);
		return logicalStep;

	}

	private boolean isInLogicalStep() {

		boolean containsNotNull = false;

		for (LogicalStep ls : currentLogicalSteps) {
			if (ls != null && ls.getMseOccurrences().get(0) != null) {
				containsNotNull = true;
				break;
			}
		}

		return !currentLogicalSteps.isEmpty() && containsNotNull;

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
		if (getFQN(object.eClass(), "").equalsIgnoreCase(className))
			containingEClass = object.eClass();
		else
			for (EClass candidate : object.eClass().getEAllSuperTypes()) {
				if (getFQN(candidate, "").equalsIgnoreCase(className))
					containingEClass = candidate;
			}

		// Then we create the missing operation (VERY approximatively)
		EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
		if (containingEClass != null)
			containingEClass.getEOperations().add(operation);
		operation.setName(methodName);
		return operation;
	}

	public MSE findOrCreateMSE(EObject caller, String className, String methodName) {

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
	
	
	
	protected void beforeExecutionStep(Object caller, String className, String operationName) throws Exception {
		
		RecordingCommand rc = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
			}
		};
		
		beforeExecutionStep(caller,className,operationName,rc);
		
	}

	protected void beforeExecutionStep(Object caller, String className, String operationName, RecordingCommand rc) throws Exception {


		// If the engine is stopped, we use this call to executeStep to stop the
		// execution
		if (_isStopped) {
			notifyAboutToStop(); // notification occurs only if not already
									// stopped
			throw new EngineStoppedException("Execution stopped");
		}

		// We only work with calls from non-null EObjects, with non-null
		// commands
		if (caller != null && caller instanceof EObject && editingDomain != null) {

			// The call is expected to be done from an EMF model, so we cast to
			// EObject
			EObject caller_cast = (EObject) caller;

			// We end any running transaction
			commitCurrentTransaction();

			// We raise a MSE, ie we put an MSE in the K3 "Solver"
			LogicalStep logicalStep = createLogicalStep(caller_cast, className, operationName);

			// We notify addons
			notifyAboutToExecuteLogicalStep(logicalStep);
			notifyMSEOccurrenceAboutToStart(logicalStep.getMseOccurrences().get(0));

			// We start a new transaction
			startNewTransaction(editingDomain, rc);

		}

	}

	protected void afterExecutionStep() throws Exception {

		RecordingCommand rc = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
			}
		};
		afterExecutionStep(rc);

	}

	protected void afterExecutionStep(RecordingCommand rc) throws Exception {

		LogicalStep logicalStep = currentLogicalSteps.pop();

		// We commit the transaction (which might be a different one
		// than the one created earlier, or null if two operations
		// end successively)
		commitCurrentTransaction();

		// We notify addons that the (real) MSE ended.
		notifyMSEOccurenceExecuted(logicalStep.getMseOccurrences().get(0));
		notifyLogicalStepExecuted(logicalStep);

		// And we start a new transaction, since we might still be
		// in the middle of
		// a step.
		if (isInLogicalStep())
			startNewTransaction(editingDomain, rc);
	}

	/**
	 * Must be called in a callback from the executed code from the operational
	 * semantics.
	 * 
	 * @param caller
	 * @param operationName
	 * @param operation
	 */
	protected void executeOperation(Object caller, String className, String operationName, final Runnable operation) {

		RecordingCommand rc = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				operation.run();
			}
		};

		try {

			beforeExecutionStep(caller, className, operationName, rc);

			rc.execute();

			afterExecutionStep(rc);

		} catch (EngineStoppedException stopExeception) {
			// We dispose to remove adapters
			rc.dispose();
			throw new EngineStoppedException(stopExeception.getMessage(), stopExeception);
		} catch (Exception e) {
			// We dispose to remove adapters
			rc.dispose();

			// We transform everything into a RuntimeException.
			// This is required because executeStep cannot throw any
			// (non-Runtime) exception, since it is used within K3AL
			// generated Java code.
			throw new RuntimeException(e);
		}
	}
}
