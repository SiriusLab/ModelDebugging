package org.gemoc.execution.engine.debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.gemoc.execution.engine.mse.engine_mse.LogicalStep;
import org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.execution.engine.debug.ui.semanticsopener.OpenSemanticsHandler;
import org.gemoc.execution.engine.ui.Activator;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.FieldChange;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.IModelChangeListenerAddon;
import org.gemoc.gemoc_language_workbench.api.engine_addon.modelchangelistener.SimpleModelChangeListenerAddon;

import fr.obeo.dsl.debug.ide.AbstractDSLDebugger;
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public abstract class AbstractGemocDebugger extends AbstractDSLDebugger implements IGemocDebugger, IEngineAddon {

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

	private IModelChangeListenerAddon modelChangeListenerAddon;

	protected EObject executedModelRoot = null;

	protected final IBasicExecutionEngine engine;

	private String bundleSymbolicName;

	public AbstractGemocDebugger(IDSLDebugEventProcessor target, IBasicExecutionEngine engine) {
		super(target);
		this.engine = engine;
		
		// This prevents a null pointer exception if the engine does not have a Language Definition Extension. 
		// In that case, the getLanguageDefinitionExtension() returns null 
		// e.g., the coordination engine 
		if (engine.getExecutionContext().getLanguageDefinitionExtension() != null) {
		bundleSymbolicName = getBundleSymbolicName(getLanguageDefinition(engine.getExecutionContext()
				.getLanguageDefinitionExtension().getXDSMLFilePath()));
		}
		registerModelChangeListener();

		Activator openSourceActivator = Activator.getDefault();
		if (openSourceActivator != null) {
			OpenSemanticsHandler openSourceHandler = openSourceActivator.getHandler();
			if (openSourceHandler != null) {
				openSourceHandler.setBundleSymbolicName(bundleSymbolicName);
				openSourceHandler.setEngine(this.engine);
			} else {
				openSourceActivator.setHandlerFieldSuppliers(() -> this.engine, () -> this.bundleSymbolicName);
			}
		}
	}

	private LanguageDefinition getLanguageDefinition(String xDSMLFilePath) {
		// Loading languagedef model
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createPlatformPluginURI(xDSMLFilePath, true);
		Resource res = rs.createResource(uri);
		try {
			res.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EcoreUtil.resolveAll(rs);// IMPORTANT

		if (res != null) {
			EObject first = res.getContents().get(0);

			// Follow-up in other operation...
			if (first instanceof LanguageDefinition) {
				return (LanguageDefinition) first;
			}
		}
		return null;
	}

	protected void registerModelChangeListener() {
		Set<IModelChangeListenerAddon> listenerAddons = engine.getAddonsTypedBy(IModelChangeListenerAddon.class);
		if (listenerAddons.isEmpty()) {
			modelChangeListenerAddon = new SimpleModelChangeListenerAddon(engine);
		} else {
			modelChangeListenerAddon = listenerAddons.stream().findFirst().get();
		}
		modelChangeListenerAddon.registerAddon(this);
	}

	abstract protected String getBundleSymbolicName(LanguageDefinition languageDefinition);

	protected List<IMutableFieldExtractor> getMutableFieldExtractors() {
		// We create a list of all mutable data extractors we want to try
		List<IMutableFieldExtractor> extractors = new ArrayList<IMutableFieldExtractor>();
		// We put annotation first
		extractors.add(new AnnotationMutableFieldExtractor());
		// Then introspection
		extractors.add(new IntrospectiveMutableFieldExtractor(bundleSymbolicName));
		return extractors;
	}

	private Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> predicateBreakPoints = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();
	private Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> predicateBreaks = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();

	@Override
	/**
	 * Breakpoints are persistent, and can trigger pauses as long as they are not removed.
	 */
	public void addPredicateBreakpoint(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate) {
		predicateBreakPoints.add(predicate);
	}

	@Override
	/**
	 * A Break only trigger a single pause, then is removed.
	 */
	public void addPredicateBreak(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate) {
		predicateBreaks.add(predicate);
	}

	protected boolean shouldBreakPredicates(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {

		// We look at predicate breaks to remove the ones that are true
		boolean shouldBreak2 = false;
		Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> toRemove = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();
		for (BiPredicate<IBasicExecutionEngine, MSEOccurrence> pred : predicateBreaks) {
			if (pred.test(engine, mseOccurrence)) {
				shouldBreak2 = true;
				toRemove.add(pred);
			}
		}
		predicateBreaks.removeAll(toRemove);
		if (shouldBreak2)
			return true;

		// If no break yet, we look at predicate breakpoints
		for (BiPredicate<IBasicExecutionEngine, MSEOccurrence> pred : predicateBreakPoints) {
			if (pred.test(engine, mseOccurrence)) {
				return true;
			}
		}

		return false;

	}

	protected EObject getModelRoot() {
		if (executedModelRoot == null) {
			if (engine != null) {
				executedModelRoot = engine.getExecutionContext().getResourceModel().getContents().get(0);
			}
		}
		return executedModelRoot;
	}

	private boolean updateMutableFieldList(EObject eObject) {
		Iterator<IMutableFieldExtractor> extractors = getMutableFieldExtractors().iterator();
		List<MutableField> newMutableFields = Collections.emptyList();
		while (newMutableFields.isEmpty() && extractors.hasNext()) {
			newMutableFields = extractors.next().extractMutableField(eObject);
		}
		return mutableFields.addAll(newMutableFields);
	}

	private void initializeMutableDatas() {
		mutableFields = new ArrayList<MutableField>();
		lastSuspendMutableFields = new HashMap<MutableField, Object>();
		nextSuspendMutableFields = new HashMap<MutableField, Object>();

		// We fetch all resources concerned by the execution,
		// since they may contain mutable fields
		Resource executedResource = getModelRoot().eResource();
		Set<Resource> allResources = org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(executedResource);
		allResources.add(executedResource);

		// We try each extractor
		for (IMutableFieldExtractor extractor : getMutableFieldExtractors()) {

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

	private MutableField lookForMutableField(String variableName) {
		return mutableFields.stream().filter(m -> m.getName().equals(variableName)).findFirst().get();
	}

	private List<MutableField> lookForMutableFields(EObject eObject) {
		return mutableFields.stream().filter(m -> m.geteObject() == eObject).collect(Collectors.toList());
	}

	private boolean mutableDataChanged(MutableField mutableData, Object value) {
		final Object lastValue = lastSuspendMutableFields.get(mutableData);
		return (lastValue != null && value == null) || (lastValue == null && value != null)
				|| (lastValue != null && value != null && !lastValue.equals(value));
	}

	abstract protected void updateStack(String threadName, EObject instruction);

	protected void updateVariables(String threadName) {
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
				Object value = change.getValue();
				if (value instanceof EObject && ((EObject) value).eContainer() == null) {
					List<MutableField> toRemove = lookForMutableFields((EObject) value);
					// deleteVariable will never work in our case in the current
					// state of the debug infrastructure, as it only searches
					// for variables in the top stackframe
					toRemove.stream().forEach(m -> deleteVariable(threadName, m.getName()));
					mutableFields.removeAll(toRemove);
				} else if (value instanceof List) {
					List<EObject> eObjects = ((List<?>) value).stream().filter(e -> e instanceof EObject)
							.map(e -> (EObject) e).collect(Collectors.toList());
					eObjects.forEach(e -> {
						if (e.eContainer() == null) {
							List<MutableField> toRemove = lookForMutableFields(e);
							toRemove.stream().forEach(m -> deleteVariable(threadName, m.getName()));
							mutableFields.removeAll(toRemove);
						}
					});
				}
				break;
			}
		}
		List<MutableField> changed = new ArrayList<MutableField>();
		mutableFields.forEach(e -> {
			nextSuspendMutableFields.put(e, e.getValue());
			if (mutableDataChanged(e, e.getValue())) {
				changed.add(e);
			}
		});

		for (MutableField m : changed) {
			variable(threadName, getModelRoot().eClass().getName(), "mutable data", m.getName(), m.getValue(), true);
		}

		if (!nextSuspendMutableFields.isEmpty()) {
			lastSuspendMutableFields = nextSuspendMutableFields;
			nextSuspendMutableFields = new HashMap<MutableField, Object>();
		}
	}

	/*
	 * Checks if the given string can be interpreted as a valid value for the given variable.
	 */
	@Override
	public boolean validateVariableValue(String threadName, String variableName, String value) {
		final MutableField data = lookForMutableField(variableName);
		return getValue(data, value) != null;
	}

	/*
	 * Returns the given string interpreted as a value of the same type as the current value of the data.
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
		final MutableField data = lookForMutableField(variableName);
		return getValue(data, value);
	}

	@Override
	public void setVariableValue(String threadName, String stackName, String variableName, Object value) {
		final MutableField data = lookForMutableField(variableName);
		data.setValue(value);
	}

	@Override
	public void updateData(String threadName, EObject instruction) {
		if (executedModelRoot == null) {
			executedModelRoot = getModelRoot();
			initializeMutableDatas();
			pushStackFrame(threadName, executedModelRoot.eClass().getName(), executedModelRoot, instruction);

			for (MutableField m : mutableFields) {
				variable(threadName, executedModelRoot.eClass().getName(), "mutable data", m.getName(), m.getValue(),
						true);
			}
		} else {
			// Updating mutable datas
			updateVariables(threadName);
		}

		updateStack(threadName, instruction);
	}

	@Override
	public void engineAboutToStart(IBasicExecutionEngine engine) {
	}

	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) {
	}

	@Override
	public void engineAboutToDispose(IBasicExecutionEngine engine) {
	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engine, RunStatus newStatus) {
	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
	}

	@Override
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine, LogicalStep selectedLogicalStep) {
	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine, LogicalStep logicalStepExecuted) {
	}
	
	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return new ArrayList<String>();
	}
}
