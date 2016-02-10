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

	protected ITraceManager traceManager;
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
			return "All execution states (" + traceManager.getTraceSize() + ")";

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
	
//	@Override
//	public Map<Integer,List<StateWrapper>> getAllStatesOrValues(int startStateIndex, int endStateIndex) {
//		final Map<Integer,List<StateWrapper>> result = new HashMap<>();
//		final Map<Integer,Integer> lengthByState = new HashMap<>();
//		
//		startStateIndex = Math.max(0, startStateIndex);
//		endStateIndex = Math.min(traceManager.getTraceSize(), endStateIndex);
//		
//		// Initializing the result map.
//		result.put(0,new ArrayList<StateWrapper>());
//		for (int i=0;i<getAllValueTraces().size();i++) {
//			final List<StateWrapper> list = new ArrayList<StateWrapper>();
//			list.add(new StateWrapper());
//			result.put(i+1,list);
//		}		
//		
//		for (int i=startStateIndex;i<endStateIndex;i++) {
//			final EObject state = traceManager.getExecutionState(i);
//			final List<StepEvent> events = traceManager.getEventsForState(i);
//			events.removeIf(e->!e.start);
//			final int stateLength = events.size()+1;
//			result.get(0).add(new StateWrapper(state,i,i,stateLength));
//			for (int j=0;j<getAllValueTraces().size();j++) {
//				// Getting the trace we want to gather values from
//				final IValueTrace valueTrace = getAllValueTraces().get(j);
//				// Getting the corresponding wrapper list under construction
//				final List<StateWrapper> currentWrapperList = result.get(j+1);
//				// Getting the wrapper currently under construction
//				final StateWrapper currentWrapper = currentWrapperList.get(currentWrapperList.size()-1);
//				currentWrapper.lengthByState = new HashMap<>();
//				// Initializing the index of the value being analyzed
//				final int valueStartIndex = currentWrapper.stateIndex;
//				// Getting the index of the first state of the current value
//				final int startIndex = valueTrace.getActiveValueStartingState(i);
//				final boolean valueFound = startIndex != -1;
//				
//				if (valueStartIndex == -1) {
//					// A value has not been found yet for the current wrapper.
//					if (valueFound) {
//						// We found a value for the current state.
//						currentWrapper.value = valueTrace.getActiveValue(startIndex);
//						currentWrapper.stateIndex = startIndex;
//						currentWrapper.traceIndex = valueTrace.getActiveValueIndex(startIndex);
//						currentWrapper.length = stateLength;
//						currentWrapper.lengthByState.put(i, stateLength);
//						for (int k=startIndex;k<startStateIndex;k++) {
//							// If the first state of this value is anterior to the
//							// first considered state, we compute the added length.
//							final int length = lengthByState.computeIfAbsent(k, (key)->{
//								final List<StepEvent> tmpEvents = traceManager.getEventsForState(key);
//								tmpEvents.removeIf(e->!e.start);
//								return tmpEvents.size()+1;
//							});
//							currentWrapper.lengthByState.put(k, length);
//							currentWrapper.length += length;
//						}
//					}
//				} else {
//					// A value has already been found for the current wrapper
//					// but it has not been fully built yet.
//					if (startIndex != valueStartIndex) {
//						// A new value (or absence of value) has been found,
//						// thus we finalize the wrapper.
//						final StateWrapper newWrapper = new StateWrapper();
//						currentWrapperList.add(newWrapper);
//						if (valueFound) {
//							// We indeed found a new value, thus we initialize the new wrapper.
//							newWrapper.value = valueTrace.getActiveValue(startIndex);
//							newWrapper.stateIndex = startIndex;
//							newWrapper.traceIndex = valueTrace.getActiveValueIndex(startIndex);
//							newWrapper.length = stateLength;
//							newWrapper.lengthByState.put(i, stateLength);
//						}
//					} else {
//						// The value of the current wrapper is still active at
//						// the current state, thus we expand its length.
//						currentWrapper.length += stateLength;
//						currentWrapper.lengthByState.put(i, stateLength);
//					}
//				}
//			}
//		}
//		
//		for (int i=0;i<getAllValueTraces().size();i++) {
//			final List<StateWrapper> list = result.get(i+1);
//			final StateWrapper lastWrapper = list.get(list.size()-1);
//			final IValueTrace valueTrace = getAllValueTraces().get(i);
//			if (lastWrapper.value == null) {
//				// This last wrapper acts as a marker that the last value has
//				// been completely analyzed. We remove it before returning.
//				list.remove(lastWrapper);
//			} else {
//				// This last wrapper is incomplete : we iterate until it is
//				// completed before returning.
//				int j = endStateIndex;
//				while (j < traceManager.getTraceSize()) {
//					final int startIndex = valueTrace.getActiveValueStartingState(j);
//					if (startIndex != lastWrapper.stateIndex) {
//						break;
//					} else {
//						final int length = lengthByState.computeIfAbsent(j, (key)->{
//							final List<StepEvent> tmpEvents = traceManager.getEventsForState(key);
//							tmpEvents.removeIf(e->!e.start);
//							return tmpEvents.size()+1;
//						});
//						lastWrapper.length += length;
//						lastWrapper.lengthByState.put(j,length);
//					}
//					j++;
//				}
//			}
//		}
//		
//		return result;
//	}

	@Override
	public List<StateWrapper> getStatesOrValues(int line, int startStateIndex, int endStateIndex) {
		List<StateWrapper> result = new ArrayList<>();
		
		startStateIndex = Math.max(0, startStateIndex);
		endStateIndex = Math.min(traceManager.getTraceSize(), endStateIndex);
		
		if (line == 0) {
			for (int i=startStateIndex;i<endStateIndex;i++) {
				result.add(new StateWrapper(traceManager.getExecutionState(i), i, i, i));
			}
		} else if (line-1<getAllValueTraces().size()) {
			// Getting the trace we want to gather values from
			IValueTrace valueTrace = getAllValueTraces().get(line - 1);
			// Initializing the index of the current value
			int valueStartIndex = -1;
			for (int i=startStateIndex;i<endStateIndex;i++) {
				// We get the starting index of the current value in the value trace.
				int startIndex = valueTrace.getActiveValueStartingState(i);
				if (startIndex != valueStartIndex) {
					// If it points to a new value
					if (valueStartIndex != -1) {
						// If we have a current value
						result.add(new StateWrapper(valueTrace.getActiveValue(valueStartIndex),
								valueStartIndex, valueTrace.getActiveValueIndex(valueStartIndex), i - 1));
					}
					valueStartIndex = startIndex;
				}
			}
			// If the last value does not end before the endStateIndex parameter,
			// we iterate until we find the actual end of the value.
			if (valueStartIndex != -1) {
				int i = endStateIndex;
				int endIndex = traceManager.getTraceSize() - 1;
				while (i < traceManager.getTraceSize()) {
					int startIndex = valueTrace.getActiveValueStartingState(i);
					if (startIndex != valueStartIndex) {
						endIndex = i - 1;
						break;
					}
					i++;
				}
				result.add(new StateWrapper(valueTrace.getActiveValue(valueStartIndex),
						valueStartIndex, valueTrace.getActiveValueIndex(valueStartIndex), endIndex));
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
