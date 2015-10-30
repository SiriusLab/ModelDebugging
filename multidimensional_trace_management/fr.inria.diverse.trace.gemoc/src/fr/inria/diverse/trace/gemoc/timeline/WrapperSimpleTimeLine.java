package fr.inria.diverse.trace.gemoc.timeline;

import java.util.List;

import org.gemoc.gemoc_language_workbench.api.core.IDisposable;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.api.IValueTrace;
import fr.obeo.timeline.view.AbstractTimelineProvider;

public class WrapperSimpleTimeLine extends AbstractTimelineProvider implements IDisposable {

	private ITraceManager traceManager;
	
	private List<IValueTrace> cache;
	private List<IValueTrace> getAllValueTraces() {
		if (cache == null)
			this.cache = traceManager.getAllValueTraces();
		return cache;
	}

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
		notifyIsSelectedChanged(0, 0, 0, true);
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
			return getAllValueTraces().get(branch - 1).getSize();
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
	/*
	 * (non-Javadoc) Asks whether the bubble at "index" is yellow or blue. Well
	 * not really, but in our case yes. -1 means yellow, 0 means blue.
	 * 
	 * @see fr.obeo.timeline.view.ITimelineProvider#getSelectedPossibleStep(int,
	 * int)
	 */
	public int getSelectedPossibleStep(int branch, int index) {

		if (branch == 0) {
			if (traceManager.getCurrentIndex() == index)
				return -1;
			else
				return 0;

		} else {
			IValueTrace trace = getAllValueTraces().get(branch - 1);
			int traceCurrentIndex =  trace.getCurrentIndex();
			if (traceCurrentIndex == index)
				return -1;
			else
				return 0;
		}
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
			return getAllValueTraces().get(branch - 1).getValue(index);
	}

	@Override
	public String getTextAt(int branch, int index, int possibleStep) {
		if (branch == 0)
			return traceManager.getDescriptionOfExecutionState(index);
		else
			return traceManager.getDescriptionOfValue(getAllValueTraces().get(branch - 1).getValue(index));
	}

	@Override
	public int[][] getFollowings(int branch, int index, int possibleStep) {
		return new int[0][0];
	}

	@Override
	public int[][] getPrecedings(int branch, int index, int possibleStep) {
		return new int[0][0];
	}

	@Override
	public void dispose() {
	}

}
