package fr.inria.diverse.event.commons.interpreter.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;

import fr.inria.diverse.event.commons.interpreter.property.ClassPropertyAspect;
import fr.inria.diverse.event.commons.model.property.ClassProperty;
import fr.inria.diverse.event.commons.model.report.Report;
import fr.inria.diverse.event.commons.model.report.ReportFactory;
import fr.inria.diverse.event.commons.model.report.StageReport;
import fr.inria.diverse.event.commons.model.scenario.Event;
import fr.inria.diverse.event.commons.model.scenario.Scenario;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;
import fr.inria.diverse.event.commons.model.scenario.Stage;

public class ScenarioManager implements IScenarioManager {

	private final Scenario<?> scenario;
	private Stage<?,?> currentStage;
	private final Iterator<Stage<?,?>> stageIterator;
	private final Resource executedModel;
	private final Report report;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScenarioManager(Scenario scenario, Resource executedModel) {
		this.scenario = scenario;
		this.executedModel = executedModel;
		this.stageIterator = ((List<Stage<?,?>>) this.scenario.getStages()).iterator();
		this.currentStage = stageIterator.next();
		this.report = ReportFactory.eINSTANCE.createReport();
	}
	
	@Override
	public List<Event<?>> getEvents() {
		if (stageIterator == null) {
			return Collections.emptyList();
		} else {
			List<EObject> matches = new ArrayList<>();
			if (currentStage.getProperty() == null) {
				matches.add(currentStage.getEvent());
			} else {
				matches.addAll(evaluateClassProperty(currentStage.getProperty()));
			}
			if (currentStage.getEvent() == null) {
				if (!matches.isEmpty()) {
					final StageReport stageReport = ReportFactory.eINSTANCE.createStageReport();
					report.getStages().add(stageReport);
					stageReport.setStage(currentStage);
					stageReport.getMatches().addAll(matches);
					if (stageIterator.hasNext()) {
						currentStage = stageIterator.next();
					} else {
						System.out.println("Scenario completed");
						currentStage = null;
					}
				}
				return Collections.emptyList();
			} else {
				List<Event<?>> events = matches.stream().map(o -> {
					return createEvent(currentStage.getEvent(), o);
				}).collect(Collectors.toList());
				if (!events.isEmpty()) {
					final StageReport stageReport = ReportFactory.eINSTANCE.createStageReport();
					report.getStages().add(stageReport);
					stageReport.setStage(currentStage);
					stageReport.getMatches().addAll(matches);
					if (stageIterator.hasNext()) {
						currentStage = stageIterator.next();
					} else {
						System.out.println("Scenario completed");
						currentStage = null;
					}
				}
				return events;
			}
		}
	}
	
	@Override
	public boolean isScenarioComplete() {
		return stageIterator != null && currentStage == null;
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
		EMFCommandTransaction transaction = new EMFCommandTransaction(command, (InternalTransactionalEditingDomain) editingDomain, null);
		try {
			transaction.start();
			command.execute();
			transaction.commit();
		} catch (InterruptedException | RollbackException e) {
			e.printStackTrace();
		}
		try {
			resource.save((new PlatformResourceURIHandlerImpl()).createOutputStream(reportURI, Collections.emptyMap()) , Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Event<?> createEvent(Event<?> originalEvent, EObject eventTarget) {
		//TODO handle the case where originalEvent target is already set and different than eventTarget
		final Event event = (Event<?>) EcoreUtil.create(originalEvent.eClass());
		if (originalEvent.getTarget() == null) {
			event.setTarget(eventTarget);
		} else {
			event.setTarget(originalEvent.getTarget());
		}
		for (EStructuralFeature f : originalEvent.eClass().getEAllStructuralFeatures()) {
			if (f != ScenarioPackage.Literals.EVENT__TARGET) {
				event.eSet(f, originalEvent.eGet(f));
			}
		}
		return event;
	}

	private List<EObject> evaluateClassProperty(ClassProperty<?> property) {
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
