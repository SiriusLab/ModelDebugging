package org.gemoc.executionframework.engine.core

import org.eclipse.emf.transaction.RecordingCommand

abstract class AbstractCommandBasedSequentialExecutionEngine extends AbstractSequentialExecutionEngine{
	
	
	/**
	 * Must be called in a callback from the executed code from the operational
	 * semantics.
	 * 
	 * @param caller
	 * @param operationName
	 * @param operation
	 */
	protected def void executeOperation(Object caller, String className, String operationName, Runnable operation) {

		val RecordingCommand rc = new RecordingCommand(editingDomain) {
			override doExecute() {
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