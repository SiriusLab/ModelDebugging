package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.xtext.naming.QualifiedName;

import fr.inria.diverse.trace.commons.model.trace.Dimension;
import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.commons.model.trace.TracedObject;
import fr.inria.diverse.trace.commons.model.trace.Value;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;
import fr.inria.diverse.trace.gemoc.api.ITraceViewListener;

public class GenericTraceExtractor implements ITraceExtractor<Step<?>, State<?,?>, TracedObject<?>, Dimension<?>, Value<?>> {

	private Trace<?,?,?> traceRoot;
	private HashMap<Dimension<?>,Boolean> ignoredDimensions = new HashMap<>();

	@Override
	public void notifyListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerCommand(ITraceViewListener listener, TraceViewCommand command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ITraceViewListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignoreDimension(Dimension<?> dimension, boolean ignore) {
		ignoredDimensions.put(dimension, ignore);
	}

	@Override
	public boolean isDimensionIgnored(Dimension<?> dimension) {
		final Boolean ignored = ignoredDimensions.get(dimension);
		return ignored != null && ignored.booleanValue();
	}

	@Override
	public boolean compareStates(State<?, ?> state1, State<?, ?> state2, boolean respectIgnored) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<List<State<?, ?>>> computeStateEquivalenceClasses(List<? extends State<?, ?>> states) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<State<?, ?>>> computeStateEquivalenceClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LaunchConfiguration getLaunchConfiguration() {
		return traceRoot.getLaunchconfiguration();
	}

	@Override
	public int getNumberOfDimensions() {
		return traceRoot.getTracedObjects().stream()
				.map(o -> o.getDimensions().size())
				.reduce(0, (i1, i2) -> i1 + i2);
	}

	@Override
	public String getStateDescription(int stateIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStateDescription(State<?, ?> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStatesTraceLength() {
		return traceRoot.getStates().size();
	}

	@Override
	public State<?, ?> getState(int stateIndex) {
		return traceRoot.getStates().get(stateIndex);
	}

	@Override
	public int getStateIndex(State<?, ?> state) {
		return traceRoot.getStates().indexOf(state);
	}

	@Override
	public int getValueFirstStateIndex(Value<?> value) {
		return traceRoot.getStates().indexOf(value.getStates().get(0));
	}

	@Override
	public int getValueLastStateIndex(Value<?> value) {
		List<? extends State<?,?>> states = value.getStates();
		return traceRoot.getStates().indexOf(states.get(states.size()));
	}

	@Override
	public String getValueDescription(int traceIndex, int stateIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueDescription(Value<?> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDimensionLabel(int traceIndex) {
		// TODO
		
		return null;
	}

	@Override
	public String getDimensionLabel(Dimension<?> dimension) {
		String attributeName = "";
		final List<? extends Value<?>> valueTrace = dimension.getValues();
		if (valueTrace.isEmpty()) {
			return "";
		}
		if (valueTrace instanceof EcoreEList) {
			final EcoreEList<?> eList = (EcoreEList<?>) valueTrace;
			final EObject owner = eList.getEObject();
			final List<String> attributes = owner.eClass().getEAllReferences().stream()
					.filter(r -> r.getName().endsWith("Sequence"))
					.map(r -> r.getName().substring(0, r.getName().length() - 8)).collect(Collectors.toList());
			final Object originalObject = getOriginalObject(owner);
			if (!attributes.isEmpty()) {
				String n = eList.data().getClass().getComponentType().getName();
				attributeName = attributes.stream().filter(s -> n.contains("_" + s + "_")).findFirst().orElse("");
			}
			if (originalObject != null) {
				if (originalObject instanceof EObject) {
					final EObject eObject = (EObject) originalObject;
					if (eObject.eIsProxy()) {
						final String proxyToString = eObject.toString();
						final int idx = proxyToString.indexOf("eProxyURI: ") + 11;
						final String s = proxyToString.substring(idx, proxyToString.length() - 1);
						return attributeName + " (" + s + ")";
					}
					final QualifiedName qname = nameProvider.getFullyQualifiedName(eObject);
					if (qname != null) {
						return attributeName + " (" + qname.toString() + " :" + eObject.eClass().getName() + ")";
					}
				}
				return attributeName + " (" + originalObject.toString() + ")";
			}
		}
		return attributeName;
	}

	@Override
	public int getValuesTraceLength(int traceIndex) {
		return 0;
	}

	@Override
	public int getValuesTraceLength(Dimension<?> dimension) {
		return dimension.getValues().size();
	}

	@Override
	public void statesAdded(List<State<?, ?>> states) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stepsStarted(List<Step<?>> steps) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stepsEnded(List<Step<?>> steps) {
		// TODO Auto-generated method stub
	}

	@Override
	public void valuesAdded(List<Value<?>> values) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dimensionsAdded(List<Dimension<?>> dimensions) {
		// TODO Auto-generated method stub
	}
}
