package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.execution.engine.core.EngineStoppedException;
import org.gemoc.execution.engine.debug.AbstractGemocDebugger;
import org.gemoc.execution.engine.debug.ui.breakpoint.GemocBreakpoint;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.FieldChange;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.IModelChangeListenerAddon;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.SimpleModelChangeListenerAddon;


import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent;
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public class GenericSequentialModelDebugger extends AbstractGemocDebugger {

	/**
	 * {@link MutableField} delta values.
	 */
	private Map<MutableField, Object> lastSuspendMutableFields;

	/**
	 * {@link MutableField} delta values.
	 */
	private Map<MutableField, Object> nextSuspendMutableFields;

	/**
	 * {@link MutableField} mutable values.
	 */
	private List<MutableField> mutableFields;

	/**
	 * A fake instruction to prevent the stepping return to stop on each event.
	 */
	private static final EObject FAKE_INSTRUCTION = EcorePackage.eINSTANCE;

	/**
	 * The {@link NonDeterministicExecutionEngine} to debug.
	 */
	private final ISequentialExecutionEngine engine;

	private EObject executedModelRoot = null;

	private String bundleSymbolicName;

	private IModelChangeListenerAddon modelChangeListenerAddon;

	public GenericSequentialModelDebugger(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine) {
		super(target);
		this.engine = engine;
		bundleSymbolicName = getLanguageDefinition(
				engine.getExecutionContext().getLanguageDefinitionExtension().getXDSMLFilePath()).getDsaProject()
				.getProjectName();
		Set<IModelChangeListenerAddon> listenerAddons = this.engine.getAddonsTypedBy(IModelChangeListenerAddon.class);
		if (listenerAddons.isEmpty()) {
			modelChangeListenerAddon = new SimpleModelChangeListenerAddon(this.engine);
		} else {
			modelChangeListenerAddon = listenerAddons.stream().findFirst().get();
		}
		modelChangeListenerAddon.registerAddon(this);
	}

	private SequentialLanguageDefinition getLanguageDefinition(String xDSMLFilePath) {
		// Loading languagedef model
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createPlatformPluginURI(xDSMLFilePath, true);
		Resource res = rs.createResource(uri);
		try {
			res.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EcoreUtil.resolveAll(rs);// IMPORTANT

		if (res != null) {
			EObject first = res.getContents().get(0);

			// Follow-up in other operation...
			if (first instanceof SequentialLanguageDefinition) {
				return (SequentialLanguageDefinition) first;
			}
		}
		return null;
	}

	@Override
	public boolean control(String threadName, EObject instruction) {
		if (!isTerminated() && instruction instanceof LogicalStep) {
			return true;
		} else {
			return super.control(threadName, instruction);
		}
	}

	@Override
	/*
	 * This method is eventually called within a new engine thread.
	 * (non-Javadoc)
	 * 
	 * @see fr.obeo.dsl.debug.ide.IDSLDebugger#start()
	 */
	public void start() {
		engine.start();
	}

	@Override
	public void disconnect() {
		return;
	}

	@Override
	/*
	 * For this debugger, instructions should only be MSEOcurrences
	 * (non-Javadoc)
	 * 
	 * @see fr.obeo.dsl.debug.ide.IDSLDebugger#canStepInto(java.lang.String,
	 * org.eclipse.emf.ecore.EObject)
	 */
	public boolean canStepInto(String threadName, EObject instruction) {
		// TODO generate code to test small/big step
		return currentInstructions.get(threadName) == instruction;
	}

	@Override
	public void steppingOver(String threadName) {
		// To send notifications, but probably useless
		super.steppingOver(threadName);

		// We add a future break as soon as the step is over
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {

			// The operation we want to step over
			private MSEOccurrence steppedOver = engine.getCurrentMSEOccurrence();

			@Override
			public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
				// We finished stepping over once the mseoccurrence is not there
				// anymore
				return !engine.getCurrentStack().contains(steppedOver);
			}
		});
	}

	@Override
	public void steppingInto(String threadName) {
		// To send notifications, but probably useless
		super.steppingInto(threadName);
		// We add a future break asap
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
			@Override
			public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
				// We finished stepping as soon as we encounter a new step
				return true;
			}
		});
	}

	/**
	 * Note that for now we do not consider that mutable fields may appear
	 * during the execution (ie, creation of new objects)
	 */
	private void initializeMutableDatas() {
		mutableFields = new ArrayList<MutableField>();
		lastSuspendMutableFields = new HashMap<MutableField, Object>();
		nextSuspendMutableFields = new HashMap<MutableField, Object>();

		// We create a list of all mutable data extractors we want to try
		List<MutableFieldExtractor> extractors = new ArrayList<MutableFieldExtractor>();
		// We put annotation first
		extractors.add(new AnnotationMutableFieldExtractor());
		// Then introspection
		extractors.add(new IntrospectiveMutableFieldExtractor(bundleSymbolicName));

		// We fetch all resources concerned by the execution,
		// since they may contain mutable fields
		Resource executedResource = executedModelRoot.eResource();
		Set<Resource> allResources = org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(executedResource);
		allResources.add(executedResource);

		// We try each extractor
		for (MutableFieldExtractor extractor : extractors) {

			// On all objects of all resources
			for (Resource resource : allResources) {
				TreeIterator<EObject> iterator = resource.getAllContents();
				while (iterator.hasNext()) {
					EObject eObject = iterator.next();
					mutableFields.addAll(extractor.extractMutableField(eObject));

					// If we found private stuff, we make it public
					Arrays.asList(eObject.getClass().getDeclaredFields()).stream().forEach((f) -> {
						try {
							f.setAccessible(true);
						} catch (Exception e) {
						}
					});
				}

				// If we found stuff with an extractor, we stop searching to
				// avoid redundancies
				if (!mutableFields.isEmpty())
					break;
			}
		}

		// we sort the list of mutable data objects by name
		mutableFields.sort(new Comparator<MutableField>() {
			@Override
			public int compare(MutableField o1, MutableField o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

	}

	private boolean updateMutableFieldList(EObject eObject) {
		AnnotationMutableFieldExtractor extractor = new AnnotationMutableFieldExtractor();
		List<MutableField> newMutableFields = extractor.extractMutableField(eObject);
		if (newMutableFields.isEmpty()) {
			IntrospectiveMutableFieldExtractor extractor2 = new IntrospectiveMutableFieldExtractor(bundleSymbolicName);
			newMutableFields = extractor2.extractMutableField(eObject);
		}
		return mutableFields.addAll(newMutableFields);
	}

	protected void updateVariables(List<MutableField> mutableDatas) {
		List<FieldChange> changes = modelChangeListenerAddon.getChanges(this);
		for (FieldChange change : changes) {
			switch (change.getChangeType()) {
			case MODIFY:
			case ADD:
				if (change.getValue() instanceof EObject) {
					EObject eObject = (EObject) change.getValue();
					List<MutableField> currentMutableFields = lookForMutableFields(eObject);
					if (currentMutableFields.isEmpty()) {
						// This is a new object
						updateMutableFieldList(eObject);
					}
				}
				break;
			case REMOVE:
				// TODO search for references to the removed value.
				// If none is found, remove it from mutable fields
				break;
			}
		}
		List<MutableField> changed = new ArrayList<MutableField>();
		mutableDatas.forEach(e -> {
			nextSuspendMutableFields.put(e, e.getValue());
			if (mutableDataChanged(e, e.getValue())) {
				changed.add(e);
			}
		});

		for (MutableField m : changed) {
			variable("Model debugging", executedModelRoot.eClass().getName(), "mutable data", m.getName(),
					m.getValue()/*m.geteObject()*/, true);
		}

		if (!nextSuspendMutableFields.isEmpty()) {
			lastSuspendMutableFields = nextSuspendMutableFields;
			nextSuspendMutableFields = new HashMap<MutableField, Object>();
		}
	}

	protected void updateStack(String threadName, EObject instruction) {
		// Catching the stack up with events that occurred since last suspension
		// We use a virtual stack to replay the last events to avoid pushing
		// stackframes that would be popped right after.
		Deque<MSEOccurrence> virtualStack = new ArrayDeque<>();
		for (ToPushPop m : toPushPop) {
			if (m.push) {
				// We push the mse onto the virtual stack.
				virtualStack.push(m.mseOccurrence);
			} else {
				if (virtualStack.isEmpty()) {
					// The virtual stack is empty, we pop the top stackframe off
					// of the real stack.
					popStackFrame(threadName);
				} else {
					// The virtual stack is not empty, we pop the top stackframe
					// off of it.
					virtualStack.pop();
				}
			}
		}

		// We then push the missing stackframes onto the real stack.
		for (MSEOccurrence mseOccurrence : virtualStack) {
			EObject caller = mseOccurrence.getMse().getCaller();
			String name = caller.eClass().getName() + " (" + mseOccurrence.getMse().getName() + ") ["
					+ caller.toString() + "]";
			pushStackFrame(threadName, name, caller, caller);
		}

		setCurrentInstruction(threadName, instruction);

		toPushPop.clear();
	}

	@Override
	/*
	 * This operation is called lots of time to update the stackframe view. We
	 * have to call "pushStackFrame" and "popStackFrame" to construct the
	 * stackframe.
	 * 
	 * TODO When using "pushStackFrame", we give the big step MSEOcc as the
	 * context, and the small step MSEOcc as the currentInstruction
	 * (non-Javadoc)
	 * 
	 * @see fr.obeo.dsl.debug.ide.IDSLDebugger#updateData(java.lang.String,
	 * org.eclipse.emf.ecore.EObject)
	 */
	public void updateData(String threadName, EObject instruction) {

		if (instruction == null) {
			updateVariables(mutableFields);
			return;
		}

		// We don't want to deal with logical steps since we are in sequential
		// mode
		if (instruction instanceof LogicalStep) {
			instruction = ((LogicalStep) instruction).getMseOccurrences().get(0);
		}

		// Initializing the root stackframe that holds the mutable data of the
		// model
		if (executedModelRoot == null) {
			executedModelRoot = lookForRoot();
			initializeMutableDatas();
			pushStackFrame(threadName, executedModelRoot.eClass().getName(), executedModelRoot, instruction);

			for (MutableField m : mutableFields) {
				variable(threadName, executedModelRoot.eClass().getName(), "mutable data", m.getName(),
						m.getValue()/*m.geteObject()*/,
						true);
			}
		} else {
			// Updating mutable datas
			updateVariables(mutableFields);
		}

		updateStack(threadName, instruction);
	}

	private boolean mutableDataChanged(MutableField mutableData, Object value) {
		final Object lastValue = lastSuspendMutableFields.get(mutableData);
		return (lastValue != null && value == null) || (lastValue == null && value != null)
				|| (lastValue != null && value != null && !lastValue.equals(value));
	}

	private EObject lookForRoot() {
		EObject eObject = engine.getCurrentMSEOccurrence().getMse().getCaller();
		while (eObject.eContainer() != null) {
			eObject = eObject.eContainer();
		}
		return eObject;
	}

	protected EObject getExecutedModelRoot() {
		return executedModelRoot;
	}

	private MutableField lookForMutableData(String variableName) {
		return mutableFields.stream().filter(m -> m.getName().equals(variableName)).findFirst().get();
	}

	private List<MutableField> lookForMutableFields(EObject eObject) {
		return mutableFields.stream().filter(m -> m.geteObject() == eObject).collect(Collectors.toList());
	}

	@Override
	public boolean shouldBreak(EObject instruction) {
		if (instruction instanceof MSEOccurrence) {
			return shouldBreakMSEOccurence((MSEOccurrence) instruction);
		}
		return false;
	}

	private boolean hasRegularBreakpointTrue(EObject o) {
		return super.shouldBreak(o)
				&& (Boolean.valueOf((String) getBreakpointAttributes(o, GemocBreakpoint.BREAK_ON_LOGICAL_STEP)) || Boolean
						.valueOf((String) getBreakpointAttributes(o, GemocBreakpoint.BREAK_ON_MSE_OCCURRENCE)));
	}

	private boolean shouldBreakMSEOccurence(MSEOccurrence mseOccurrence) {
		if (shouldBreakPredicates(engine, mseOccurrence))
			return true;
		// If still no break yet, we look at regular breakpoints on MSE
		ModelSpecificEvent mse = mseOccurrence.getMse();
		if (hasRegularBreakpointTrue(mse)) {
			return true;
		}
		// If still no break yet, we look at regular breakpoints on MSE's caller
		EObject caller = mseOccurrence.getMse().getCaller();
		if (hasRegularBreakpointTrue(caller)) {
			return true;
		}
		return false;
	}

	@Override
	public EObject getNextInstruction(String threadName, EObject currentInstruction, Stepping stepping) {
		// We always return fakeinstruction to make sure to not stop and to
		// handle everything with shouldBreak
		return FAKE_INSTRUCTION;
	}

	@Override
	public void engineStarted(IBasicExecutionEngine executionEngine) {
		spawnRunningThread("Model debugging", engine.getExecutionContext().getResourceModel().getContents().get(0));
	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) {
		if (!isTerminated("Model debugging")) {
			terminated("Model debugging");
		}
	}

	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine executionEngine, LogicalStep logicalStepToApply) {
		// if (!control(Thread.currentThread().getName(), logicalStepToApply)) {
		// throw new EngineStoppedException("Debug thread has stopped.");
		// }
	}

	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		ToPushPop aaa = new ToPushPop(mseOccurrence, true);
		toPushPop.add(aaa);
		if (!control("Model debugging", mseOccurrence)) {
			throw new EngineStoppedException("Debug thread has stopped.");
		}
	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		ToPushPop aaa = new ToPushPop(mseOccurrence, false);
		toPushPop.add(aaa);
	}

	@Override
	public void terminate() {
		super.terminate();
		engine.stop();
	}

	/*
	 * Checks if the given string can be interpreted as a valid value for the
	 * given variable.
	 */
	@Override
	public boolean validateVariableValue(String threadName, String variableName, String value) {
		final MutableField data = lookForMutableData(variableName);
		return getValue(data, value) != null;
	}

	/*
	 * Returns the given string interpreted as a value of the same type as the
	 * current value of the data.
	 */
	private Object getValue(MutableField data, String value) {
		final Object res;

		final Object currentValue = data.getValue();

		if (currentValue instanceof String) {
			res = value;
		} else if (currentValue instanceof Integer) {
			Integer integerValue = null;
			try {
				integerValue = Integer.decode(value);
			} catch (Exception e) {
				// nothing to do here
			}
			res = integerValue;
		} else if (currentValue instanceof Double) {
			Double doubleValue = null;
			try {
				doubleValue = Double.parseDouble(value);
			} catch (Exception e) {
				// nothing to do here
			}
			res = doubleValue;
		} else if (currentValue instanceof Boolean) {
			res = Boolean.valueOf(value);
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public Object getVariableValue(String threadName, String stackName, String variableName, String value) {
		final MutableField data = lookForMutableData(variableName);
		return getValue(data, value);
	}

	@Override
	public void setVariableValue(String threadName, String stackName, String variableName, Object value) {
		final MutableField data = lookForMutableData(variableName);
		data.setValue(value);
	}

	private static class ToPushPop {
		public MSEOccurrence mseOccurrence;
		public boolean push;

		ToPushPop(MSEOccurrence mseOccurrence, boolean push) {
			this.mseOccurrence = mseOccurrence;
			this.push = push;
		}
	}

	List<ToPushPop> toPushPop = new ArrayList<>();

	@Override
	public void engineAboutToStart(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAboutToDispose(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine, LogicalStep selectedLogicalStep) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine, LogicalStep logicalStepExecuted) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engine, RunStatus newStatus) {
		// TODO Auto-generated method stub

	}

}
