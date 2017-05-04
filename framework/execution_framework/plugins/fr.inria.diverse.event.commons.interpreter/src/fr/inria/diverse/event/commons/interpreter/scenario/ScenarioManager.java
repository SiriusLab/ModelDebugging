package fr.inria.diverse.event.commons.interpreter.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;

import fr.inria.diverse.event.commons.interpreter.property.ClassPropertyAspect;
import fr.inria.diverse.event.commons.model.EventInstance;
import fr.inria.diverse.event.commons.model.property.StateProperty;
import fr.inria.diverse.event.commons.model.report.EventReport;
import fr.inria.diverse.event.commons.model.report.Report;
import fr.inria.diverse.event.commons.model.report.ReportFactory;
import fr.inria.diverse.event.commons.model.report.StageReport;
import fr.inria.diverse.event.commons.model.scenario.Date;
import fr.inria.diverse.event.commons.model.scenario.ElementProvider;
import fr.inria.diverse.event.commons.model.scenario.Event;
import fr.inria.diverse.event.commons.model.scenario.Phase;
import fr.inria.diverse.event.commons.model.scenario.Scenario;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;
import fr.inria.diverse.event.commons.model.scenario.Stage;
import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;

public class ScenarioManager implements IScenarioManager {

	private final Scenario<?> scenario;
	private final Iterator<Phase<?>> stageIterator;
	private final Resource executedModel;
	private final Supplier<Deque<MSEOccurrence>> stackSupplier;
	private final Report report;
	private Phase<?> currentPhase;
	private EventInstance currentPendingEvent;
	private long scheduledTime = -1;
	private StageReport currentStageReport;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScenarioManager(Scenario scenario, Resource executedModel, Supplier<Deque<MSEOccurrence>> stackSupplier) {
		this.scenario = scenario;
		this.executedModel = executedModel;
		this.stackSupplier = stackSupplier;
		this.stageIterator = ((List<Phase<?>>) this.scenario.getPhases()).iterator();
		this.currentPhase = stageIterator.next();
		this.report = ReportFactory.eINSTANCE.createReport();
	}

	@Override
	public List<EventInstance> getEvents() {
		if (stageIterator == null || System.nanoTime() < scheduledTime) {
			return Collections.emptyList();
		} else {
			final List<EventInstance> events = new ArrayList<>();
			if (currentPendingEvent != null && System.nanoTime() >= scheduledTime) {
				events.add(currentPendingEvent);
				currentPendingEvent = null;
				scheduledTime = -1;
			}
			final List<EObject> matches = new ArrayList<>();
			boolean searchForEvents = true;
			while (searchForEvents) {
				if (currentPhase instanceof Stage<?, ?>) {
					final Stage<?, ?> currentStage = (Stage<?, ?>) currentPhase;
					if (currentStage.getProperty() == null) {
						matches.add(currentStage.getEvent());
					} else {
						matches.addAll(evaluateStateProperty(currentStage.getProperty()));
					}
				} else if (currentPhase instanceof Date<?>) {
					final Date<?> currentDate = (Date<?>) currentPhase;
					currentPendingEvent = createEvent(currentDate.getEvent());
					searchForEvents = false;
					scheduledTime = currentDate.getTime() + System.nanoTime();
				}
				
				if (!matches.isEmpty()) {
//					currentStageReport = ReportFactory.eINSTANCE.createStageReport();
//					report.getStages().add(currentStageReport);
//					currentStageReport.setStage(currentPhase);
//					currentStageReport.getMatches().addAll(matches);
					if (currentPhase.getEvent() != null) {
						events.add(createEvent(currentPhase.getEvent()));
					}
					if (stageIterator.hasNext()) {
						currentPhase = stageIterator.next();
					} else {
						System.out.println("Scenario completed");
						currentPhase = null;
						searchForEvents = false;
					}
				} else {
					searchForEvents = false;
				}
			}
			return events;
		}
	}

	@Override
	public boolean isScenarioComplete() {
		return stageIterator != null && currentPhase == null;
	}

	@Override
	public void saveScenarioReport() {
		final ResourceSet resourceSet = scenario.eResource().getResourceSet();
		final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE
				.getEditingDomain(resourceSet);
		final URI scenarioURI = scenario.eResource().getURI();
		final URI reportURI = scenarioURI.trimFileExtension().appendFileExtension("report");
		final Resource resource = resourceSet.createResource(reportURI);
		final RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			public void doExecute() {
				resource.getContents().add(report);
			}
		};
		EMFCommandTransaction transaction = new EMFCommandTransaction(command,
				(InternalTransactionalEditingDomain) editingDomain, null);
		try {
			transaction.start();
			command.execute();
			transaction.commit();
		} catch (InterruptedException | RollbackException e) {
			e.printStackTrace();
		}
		try {
			resource.save((new PlatformResourceURIHandlerImpl()).createOutputStream(reportURI, Collections.emptyMap()),
					Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private EventInstance createEvent(Event<?> originalEvent) {
		final ElementProvider<?> targetProvider = originalEvent.getTargetProvider();
		final List<EObject> eventParameterMatches = new ArrayList<>();
		if (targetProvider != null) {
			final EObject target = ElementProviderAspect.resolve(targetProvider, executedModel);
			if (target != null) {
				eventParameterMatches.add(target);
				final Map<EStructuralFeature, Object> parameters = new HashMap<>();
				parameters.put(ScenarioPackage.Literals.EVENT__TARGET_PROVIDER, target);
				for (EStructuralFeature f : originalEvent.eClass().getEStructuralFeatures()) {
					if (f instanceof EAttribute) {
						parameters.put(f, originalEvent.eGet(f));
					} else {
						final ElementProvider<?> paramProvider = (ElementProvider<?>) originalEvent.eGet(f);
						final EObject parameter = ElementProviderAspect.resolve(paramProvider, executedModel);
						if (parameter != null) {
							parameters.put(f, parameter);
							eventParameterMatches.add(parameter);
						}
					}
				}
//				final EventReport eventReport = ReportFactory.eINSTANCE.createEventReport();
//				currentStageReport.getEvents().add(eventReport);
//				eventReport.setEvent(originalEvent);
				// TODO eventReport.getMatches().addAll(eventParameterMatches);
				return new EventInstance(originalEvent, parameters);
			}
		}
		return null;
	}

	private List<EObject> evaluateStateProperty(StateProperty<?> property) {
		final List<EObject> eventReceivers = new ArrayList<>();
		final Object target = property.getTarget();
		if (target == null) {
			Set<EClassifier> eClasses = new HashSet<>();
			property.eClass().getEAllGenericSuperTypes().stream()
					.forEach(t -> t.getETypeArguments().stream().forEach(a -> eClasses.add(a.getEClassifier())));
			final Iterator<EObject> it = executedModel.getAllContents();
			while (it.hasNext()) {
				EObject o = it.next();
				if (eClasses.contains(o.eClass()) && ClassPropertyAspect.evaluate(property, o)) {
					eventReceivers.add(o);
				}
			}
		} else {
			final EObject target_cast = (EObject) target;
			if (ClassPropertyAspect.evaluate(property, target_cast)) {
				eventReceivers.add(target_cast);
			}
		}
		return eventReceivers;
	}
}
