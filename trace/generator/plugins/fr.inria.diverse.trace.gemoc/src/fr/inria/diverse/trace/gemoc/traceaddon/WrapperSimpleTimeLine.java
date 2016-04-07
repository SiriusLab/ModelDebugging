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
package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.gemoc.xdsmlframework.api.core.IDisposable;

import fr.inria.diverse.trace.api.IStep;
import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.api.IValueTrace;
import fr.inria.diverse.trace.gemoc.api.ISimpleTimeLineNotifier;

public class WrapperSimpleTimeLine extends AbstractSequentialTimelineProvider implements IDisposable, ISimpleTimeLineNotifier {

	protected ITraceManager traceManager;
	private List<IValueTrace> cache;
	private final DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();

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
	public String getTextAt(int branch, int index, int possibleStep) {
		if (branch == 0) {
			return traceManager.getDescriptionOfExecutionState(index);
		} else {
			String result = "";
			try {
				result += traceManager.getContainedValue(getAllValueTraces().get(branch - 1).getValue(index));
			} catch (IllegalStateException e) {
				e.printStackTrace();
				result = traceManager.getDescriptionOfValue(getAllValueTraces().get(branch - 1).getValue(index));
			}
			return result;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.obeo.timeline.view.ITimelineProvider#getTextAt(int)
	 * Used to get the label of the dynamic information timelines.
	 */
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
			EObject container = value.eContainer();
			List<String> attributes = container.eClass().getEAllReferences().stream()
					.filter(r->r.getName().endsWith("Sequence"))
					.map(r->r.getName().substring(0,r.getName().length()-8))
					.collect(Collectors.toList());
			String attributeName = "";
			if (!attributes.isEmpty()) {
				attributeName = attributes.stream().filter(s->value.getClass().getName().contains("_"+s+"_")).collect(Collectors.toList()).get(0);
			}
			Optional<EReference> originalObject = container.eClass().getEAllReferences().stream().filter(r->r.getName().equals("originalObject")).findFirst();
			if (originalObject.isPresent()) {
				Object o = container.eGet(originalObject.get());
				if (o instanceof EObject) {
					EObject eObject = (EObject) o;
					QualifiedName qname = nameprovider.getFullyQualifiedName(eObject);
					if(qname == null) {
						return attributeName + " (" + eObject.toString() + ")";
					} else { 
						return attributeName + " (" + qname.toString() + " :" + eObject.eClass().getName() + ")";
					}
				}
			}
			
			return attributeName;
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
	public List<IStep> getStepsForStates(int startingState, int endingState) {
		return traceManager.getStepsForStates(startingState, endingState);
	}

	@Override
	public int getCurrentBranch() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getCurrentChoice() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getCurrentPossibleStep() {
		// TODO Auto-generated method stub
		return -1;
	}

}
