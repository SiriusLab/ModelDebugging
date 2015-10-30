package fr.inria.diverse.trace.generic.view;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictraceFactory;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Trace;
import fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Value;
import fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence;

public class GenericTraceSample {

	private static RichgenerictraceFactory factory = RichgenerictraceFactory.eINSTANCE;

	public static Trace createSampleTrace() {

		Value v1 = factory.createValue();
		Value v2 = factory.createValue();
		Value v3 = factory.createValue();

		ValueSequence vs = factory.createValueSequence();
		vs.getValues().add(v1);
		vs.getValues().add(v2);
		vs.getValues().add(v3);

		TracedObject o = factory.createTracedObject();
		o.getValueSequences().add(vs);

		ExecutionState s1 = factory.createExecutionState();
		s1.getValues().add(v1);
		ExecutionState s2 = factory.createExecutionState();
		s2.getValues().add(v2);
		ExecutionState s3 = factory.createExecutionState();
		s2.getValues().add(v3);

		Trace t = factory.createTrace();
		t.getTracedObjects().add(o);
		t.getStates().add(s1);
		t.getStates().add(s2);
		t.getStates().add(s3);

		return t;
	}

}
