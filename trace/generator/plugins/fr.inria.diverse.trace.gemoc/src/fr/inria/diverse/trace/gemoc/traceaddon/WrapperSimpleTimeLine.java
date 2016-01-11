package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.xdsmlframework.api.core.IDisposable;

import fr.inria.diverse.trace.api.IStep.StepEvent;
import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.api.IValueTrace;
import fr.inria.diverse.trace.gemoc.api.ISimpleTimeLineNotifier;

public class WrapperSimpleTimeLine extends AbstractSequentialTimelineProvider implements IDisposable, ISimpleTimeLineNotifier {

	private ITraceManager traceManager;
	private List<IValueTrace> cache;

	protected List<IValueTrace> getAllValueTraces() {
		if (cache == null)
			this.cache = traceManager.getAllValueTraces();
		return cache;
	}

	@Override
	public void setTraceManager(ITraceManager m) {
		this.traceManager = m;
	}

	/**
	 * Constructor.
	 * 
	 * @param simpleProvider
	 *            The provider of the xDSML.
	 * @param engine
	 *            The engine used for the execution.
	 */
	public WrapperSimpleTimeLine(ITraceManager manager) {
		this.traceManager = manager;
	}

	public WrapperSimpleTimeLine() {
	}

	/**
	 * WARNING: notifyEndChanged is WRONG, since it asks an index and requires a length!
	 */
	@Override
	public void notifyTimeLine() {
		if (traceManager != null) {
			notifyEndChanged(0, traceManager.getTraceSize());
			notifyIsSelectedChanged(0, 0, 0, true);
		}
	}

	@Override
	public int getNumberOfBranches() {
		if (traceManager == null) {
			return 1;
		}
		return 1 + traceManager.getNumberOfValueTraces();
	}

	@Override
	public int getStart(int branch) {
		return 0;
	}

	@Override
	public int getEnd(int branch) {
		if (traceManager == null) {
			return 0;
		}
		if (branch == 0)
			return traceManager.getTraceSize();
		else
			return getAllValueTraces().get(branch - 1).getSize();
	}

	@Override
	public String getTextAt(int branch) {
		
		if (branch == 0) {
			return "All execution states";

		} else {
			IValueTrace trace = getAllValueTraces().get(branch - 1);
			EObject value = trace.getValue(0);
			if (value == null) {
				return "";
			}
			return value.eClass().getName();
		}
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
	 * (non-Javadoc) Asks whether the bubble at "index" is yellow or blue. Well not really, but in our case yes. -1
	 * means yellow, 0 means blue.
	 * 
	 * @see fr.obeo.timeline.view.ITimelineProvider#getSelectedPossibleStep(int, int)
	 */
	public int getSelectedPossibleStep(int branch, int index) {

		if (branch == 0) {
			if (traceManager.getTraceSize() - 1 == index)
				return -1;
			else
				return 0;

		} else {
			IValueTrace trace = getAllValueTraces().get(branch - 1);
			if (trace.getSize() - 1 == index)
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
		if (branch == 0) {
			if (traceManager.getTraceSize() > index) {
				return traceManager.getExecutionState(index);
			}
		} else {
			if (getAllValueTraces().size() > index) {
				return getAllValueTraces().get(branch - 1).getValue(index);
			}
		}
		return null;
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

	@Override
	public List<StateWrapper> getStatesOrValues(int branch) {
		List<StateWrapper> result = new ArrayList<>();
		
		if (branch == 0) {
			for (int i=0;i<traceManager.getTraceSize();i++) {
				result.add(new StateWrapper(traceManager.getExecutionState(i), i, i));
			}
		} else if (branch - 1 < getAllValueTraces().size()) {
			// Setting the trace we want to gather values from
			IValueTrace valueTrace = getAllValueTraces().get(branch - 1);
			// Initializing the index of the current value
			int startIndex = -1;
			int previousIndex = -1;
			for (int i=0;i<traceManager.getTraceSize();i++) {
				// We get the index of the current value in the value trace. 
				int valueIndex = valueTrace.getCurrentIndex(i);
				if (valueIndex != previousIndex) {
					// If it points to a new value
					if (previousIndex != -1) {
						// If we have already encountered at least one value
						// We add the value to our result
						result.add(new StateWrapper(valueTrace.getValue(previousIndex), startIndex, i - 1));
					}
					previousIndex = valueIndex;
					startIndex = i;
				}
			}
			if (previousIndex != -1) {
				result.add(new StateWrapper(valueTrace.getValue(previousIndex), startIndex, traceManager.getTraceSize() - 1));
			}
		}
		
		return result;
	}

	@Override
	public List<StepEvent> getStepEventsForState(int stateIndex) {
		if (stateIndex > -1 && stateIndex < traceManager.getTraceSize()) {
			return traceManager.getEventsForState(stateIndex);
		} else {
			return new ArrayList<>();
		}
	}

}
