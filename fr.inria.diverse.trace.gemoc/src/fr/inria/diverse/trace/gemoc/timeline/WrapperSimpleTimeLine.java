package fr.inria.diverse.trace.gemoc.timeline;

import org.gemoc.gemoc_language_workbench.api.core.IDisposable;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.obeo.timeline.view.AbstractTimelineProvider;

public class WrapperSimpleTimeLine extends AbstractTimelineProvider implements IDisposable {

	private ITraceManager traceManager;

	/**
	 * Constructor.
	 * 
	 * @param simpleProvider
	 *            The provider of the xDSML.
	 * @param engine
	 *            The engine used for the execution.
	 */
	public WrapperSimpleTimeLine(ITraceManager traceManager) {
		this.traceManager = traceManager;
	}

	/**
	 * WARNING: notifyEndChanged is WRONG, since it asks an index and requires a
	 * length!
	 */
	public void notifyTimeLine() {
		int size = traceManager.getTraceSize();
		notifyEndChanged(0, size);
		notifyIsSelectedChanged(0, size, 0, true);
	}

	@Override
	public int getNumberOfBranches() {
		return 1 + traceManager.getNumberOfValueTraces();
	}

	@Override
	public int getStart(int branch) {
		return 0;
	}

	@Override
	public int getEnd(int branch) {
		if (branch == 0)
			return traceManager.getTraceSize();
		else
			return traceManager.getAllValueTraces().get(branch - 1).size();
	}

	@Override
	public String getTextAt(int branch) {
		return "DISABLED";
	}

	@Override
	public int getNumberOfPossibleStepsAt(int branch, int index) {
		return 1;
	}

	@Override
	public String getTextAt(int branch, int index) {
		return "DISABLED";
	}

	@Override
	public int getSelectedPossibleStep(int branch, int index) {
		int lastStepIndex;
		if (branch == 0)
			lastStepIndex = traceManager.getTraceSize() - 1;
		else
			lastStepIndex = traceManager.getAllValueTraces().get(branch - 1).size() - 1;
		if (index == lastStepIndex)
			return -1;
		return 0;
	}

	@Override
	public Object getAt(int branch, int index, int possibleStep) {
		return getAt(branch, index);
	}

	@Override
	public Object getAt(int branch, int index) {
		if (branch == 0)
			return traceManager.getExecutionState(index);
		else
			return traceManager.getAllValueTraces().get(branch - 1).get(index);
	}

	@Override
	public String getTextAt(int branch, int index, int possibleStep) {
		if (branch == 0)
			return traceManager.getDescriptionOfExecutionState(index);
		else
			return traceManager.getDescriptionOfValue(traceManager.getAllValueTraces().get(branch - 1).get(index));
	}

	@Override
	public int[][] getFollowings(int branch, int index, int possibleStep) {

		int[][] result = { { branch, 0 } };
		int lastStepIndex;
		if (branch == 0)
			lastStepIndex = traceManager.getTraceSize() - 1;
		else
			lastStepIndex = traceManager.getAllValueTraces().get(branch - 1).size() - 1;

		if (index >= lastStepIndex - 1)
			result = new int[0][0];
		return result;
	}

	@Override
	public int[][] getPrecedings(int branch, int index, int possibleStep) {
		int[][] result = { { branch, 0 } };
		if (index == 0)
			result = new int[0][0];
		return result;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
