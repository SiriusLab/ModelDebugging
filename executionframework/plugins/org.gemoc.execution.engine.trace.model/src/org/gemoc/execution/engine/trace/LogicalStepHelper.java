package org.gemoc.execution.engine.trace;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.execution.engine.mse.engine_mse.MSE;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;


public class LogicalStepHelper {

	public static String getLogicalStepName(LogicalStep step) {
		//returns the emf id or the java id if the emf id is null
		return "LogicalStep ["+(EcoreUtil.getID(step) !=  null ? EcoreUtil.getID(step):System.identityHashCode(step)) +"]";
	}

	public static List<MSE> getMSEs(LogicalStep instruction) {
		ArrayList<MSE> l = new ArrayList<MSE>();
		for (MSEOccurrence o : instruction.getMseOccurrences())
		{
			l.add(o.getMse());
		}
		return l;
	}
	
}
