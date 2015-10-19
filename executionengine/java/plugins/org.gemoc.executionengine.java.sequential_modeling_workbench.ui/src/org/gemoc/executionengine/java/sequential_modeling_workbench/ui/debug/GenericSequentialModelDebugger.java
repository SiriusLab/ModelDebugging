package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;

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
import org.gemoc.execution.engine.debug.IGemocDebugger;
import org.gemoc.execution.engine.debug.ui.breakpoint.GemocBreakpoint;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent;
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public class GenericSequentialModelDebugger extends AbstractGemocDebugger implements IEngineAddon, IGemocDebugger {

	/**
	 * Is the hardware stack frame pushed yet.
	 */
	private boolean hardwareFramePushed;

	/**
	 * {@link Value} delta values.
	 */
	private Map<MutableData, Object> lastSuspendMutableDatas;
	
	/**
	 * {@link Value} delta values.
	 */
	private Map<MutableData, Object> nextSuspendMutableDatas;
	
	private List<MutableData> mutableDatas;
	
	/**
	 * A fake instruction to prevent the stepping return to stop on each event.
	 */
	private static final EObject FAKE_INSTRUCTION = EcorePackage.eINSTANCE;

	/**
	 * The {@link NonDeterministicExecutionEngine} to debug.
	 */
	private final ISequentialExecutionEngine engine;

	private EObject root;

	private boolean rootToInitialize = true;
	
	private String bundleSymbolicName;

	public GenericSequentialModelDebugger(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine) {
		super(target);
		this.engine = engine;
		bundleSymbolicName = getLanguageDefinition(engine.getExecutionContext()
				.getLanguageDefinitionExtension().getXDSMLFilePath())
				.getDsaProject().getProjectName();
		
		
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
		return true;
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
		//To send notifications, but probably useless
		super.steppingInto(threadName);
		//We add a future break asap
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
			@Override
			public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
				//We finished stepping as soon as we encounter a new step
				return true;
			}
		});
	}
	
	private void initializeMutableDatas() {
		mutableDatas = new ArrayList<MutableData>();
		lastSuspendMutableDatas = new HashMap<MutableData,Object>();
		nextSuspendMutableDatas = new HashMap<MutableData,Object>();
		
		TreeIterator<EObject> iterator = root.eAllContents();
		System.out.println("BUNDLE NAME = " + bundleSymbolicName);
		IntrospectiveMutableDataExtractor extractor = new IntrospectiveMutableDataExtractor(bundleSymbolicName);
		
		while(iterator.hasNext()) {
			EObject eObject = iterator.next();
			mutableDatas.addAll(extractor.extractMutableData(eObject));
			Arrays.asList(eObject.getClass().getDeclaredFields()).stream()
			.forEach((f)->{
				try {
					f.setAccessible(true);
					Object value = f.get(eObject);
					if(value instanceof EObject) {
						mutableDatas.addAll(extractor.extractMutableData((EObject) value));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			});
		}
	}

	@Override
	/*
	 * This operation is called lots of time to update the stackframe view. We
	 * have to call "pushStackFrame" and "popStackFrame" to construct the
	 * stackframe.
	 * 
	 * TODO When using "pushStackFrame", we give the big step MSEOcc as
	 * the context, and the small step MSEOcc as the currentInstruction
	 * (non-Javadoc)
	 * 
	 * @see fr.obeo.dsl.debug.ide.IDSLDebugger#updateData(java.lang.String,
	 * org.eclipse.emf.ecore.EObject)
	 */
	public void updateData(String threadName, EObject instruction) {
		
		if(rootToInitialize) {
			root = lookForRoot();
			rootToInitialize = false;
			initializeMutableDatas();
		}
		
		if (!hardwareFramePushed) {
			pushStackFrame(Thread.currentThread().getName(),
					root.eClass().getName(),
					root, instruction);
			hardwareFramePushed = true;
		} else {
			setCurrentInstruction(Thread.currentThread().getName(), instruction);
		}
		
		mutableDatas.forEach(e->{
			mutableDataChanged(e, e.getValue());
		});
		
		for(Entry<MutableData, Object> entry : nextSuspendMutableDatas.entrySet()) {
			variable(threadName,
					root.eClass().getName(), "mutable data",
					entry.getKey().getName(), entry.getValue(), true);
		}
		if (!nextSuspendMutableDatas.isEmpty()) {
			lastSuspendMutableDatas = nextSuspendMutableDatas;
			nextSuspendMutableDatas = new HashMap<MutableData, Object>();
		}
		
		for (ToPushPop m : toPushPop) {
			if (m.push) {
				pushStackFrame(threadName, m.mseOccurrence.getMse().getName(), m.mseOccurrence, m.mseOccurrence);
			} else {
				popStackFrame(threadName);
			}
		}

		toPushPop.clear();

	}
	
	public void mutableDataChanged(MutableData mutableData, Object value) {
		final Object lastValue = lastSuspendMutableDatas.get(mutableData);
		if ((lastValue == null && value != null)
				|| (lastValue != null && !lastValue.equals(value))) {
			nextSuspendMutableDatas.put(mutableData, value);
		} else {
			nextSuspendMutableDatas.remove(mutableData);
		}
	}
	
	private EObject lookForRoot() {
		EObject eObject = engine.getCurrentMSEOccurrence().getMse().getCaller();
		while(eObject.eContainer() != null) {
			eObject = eObject.eContainer();
		}
		return eObject;
	}
	
	private MutableData lookForMutableData(String variableName) {
		return mutableDatas.stream()
				.filter(m->m.getName().equals(variableName))
				.findFirst().get();
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
		spawnRunningThread(Thread.currentThread().getName(), engine.getExecutionContext().getResourceModel()
				.getContents().get(0));
	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) {
		if (!isTerminated(Thread.currentThread().getName())) {
			terminated(Thread.currentThread().getName());
		}
	}

	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine executionEngine, LogicalStep logicalStepToApply) {
		if (!control(Thread.currentThread().getName(), logicalStepToApply)) {
			throw new EngineStoppedException("Debug thread has stopped.");
		}
	}

	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		ToPushPop aaa = new ToPushPop(mseOccurrence, true);
		toPushPop.add(aaa);
		if (!control(Thread.currentThread().getName(), mseOccurrence)) {
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
	 * Checks if the given string can be interpreted as a valid value for the given variable.
	 */
	@Override
	public boolean validateVariableValue(String threadName, String variableName, String value) {
		final MutableData data = lookForMutableData(variableName);
		return getValue(data,value) != null;
	}

	/*
	 * Returns the given string interpreted as a value of the same type as the current value of the data.
	 */
	private Object getValue(MutableData data, String value) {
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
		final MutableData data = lookForMutableData(variableName);
		return getValue(data,value);
	}

	@Override
	public void setVariableValue(String threadName, String stackName, String variableName, Object value) {
		final MutableData data = lookForMutableData(variableName);
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
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine,
			Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine,
			Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine,
			LogicalStep selectedLogicalStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine,
			LogicalStep logicalStepExecuted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engine,
			RunStatus newStatus) {
		// TODO Auto-generated method stub
		
	}

}
