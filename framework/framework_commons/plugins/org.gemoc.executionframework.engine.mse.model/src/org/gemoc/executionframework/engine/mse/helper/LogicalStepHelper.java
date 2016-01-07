package org.gemoc.executionframework.engine.mse.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;


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
