package org.gemoc.executionframework.engine.core;

import org.eclipse.emf.transaction.RollbackException;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;

/**
 * An exception that is caused by anything thrown from the execution of
 * sequential operational semantics.
 * 
 */
public class SequentialExecutionException extends RuntimeException {

	private MSEOccurrence pendingMSEOccurrence;

	new(MSEOccurrence pendingMSE, Throwable cause) {
		this.pendingMSEOccurrence = pendingMSE;
		this.initCause(cause)
	}

	private def String prettyPrintMSEOcc() {
		if (pendingMSEOccurrence !=	null)
			return '''Pending MSEOccurrence: «pendingMSEOccurrence.mse.caller.eClass.name».«pendingMSEOccurrence.mse.action.name» called on «pendingMSEOccurrence.mse.caller».'''
		else
			return "No pending MSE."
	}

	override getMessage() {
		if (this.getCause() != null && this.getCause() instanceof RollbackException) {
			return "An error occured during the execution of the operational semantics (originally catched as a RollbackException during the transaction commit).\n" +
				prettyPrintMSEOcc;
		} else if (this.getCause() != null && this.getCause() instanceof InterruptedException) {
			return "The engine thread was interrupted while it was waiting for being allowed to start an execution step's transaction.\n" +
				prettyPrintMSEOcc;
		} else {
			return super.getMessage();
		}
	}

}
