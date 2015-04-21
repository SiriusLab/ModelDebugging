package fr.inria.diverse.trace.gemoc.timeline;

import org.gemoc.gemoc_language_workbench.api.core.IDisposable;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.obeo.timeline.view.AbstractTimelineProvider;

public class WrapperSimpleTimeLine extends AbstractTimelineProvider implements
		IDisposable {

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
		return 1;
	}

	@Override
	public int getStart(int branch) {
		return 0;
	}

	@Override
	public int getEnd(int branch) {
		return traceManager.getTraceSize();
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
		int lastStepIndex = traceManager.getTraceSize() - 1;
		if (index == lastStepIndex)
			return -1;
		return 0;
	}

	@Override
	public Object getAt(int branch, int index, int possibleStep) {
		return traceManager.getExecutionState(index);
	}

	@Override
	public Object getAt(int branch, int index) {
		return traceManager.getExecutionState(index);
	}

	@Override
	public String getTextAt(int branch, int index, int possibleStep) {
		return traceManager.getDescriptionOfExecutionState(index);
	}

	@Override
	public int[][] getFollowings(int branch, int index, int possibleStep) {

		int[][] result = { { 0, 0 } };
		int lastIndex = traceManager.getTraceSize() - 1;
		if (index >= lastIndex - 1)
			result = new int[0][0];
		return result;
	}

	@Override
	public int[][] getPrecedings(int branch, int index, int possibleStep) {
		int[][] result = { { 0, 0 } };
		if (index == 0)
			result = new int[0][0];
		return result;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
