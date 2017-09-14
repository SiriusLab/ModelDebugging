package org.eclipse.gemoc.event.commons.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gemoc.event.commons.interpreter.property.PropertyMonitor;
import org.eclipse.gemoc.event.commons.interpreter.scenario.ArbiterManager;
import org.eclipse.gemoc.event.commons.interpreter.scenario.IArbiterManager;
import org.eclipse.gemoc.event.commons.interpreter.scenario.IScenarioManager;
import org.eclipse.gemoc.event.commons.interpreter.scenario.ScenarioManager;
import org.eclipse.gemoc.event.commons.model.scenario.Arbiter;
import org.eclipse.gemoc.event.commons.model.scenario.Scenario;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class EventInterpreter implements IEventInterpreter {

	private Resource executedModel;

	private final Queue<EventInstance> eventQueue = new ConcurrentLinkedQueue<>();

	private boolean canManageEvents = true;

	private boolean waitForEvents = false;

	private Thread t = null;

	private IScenarioManager scenarioManager;

	private IArbiterManager arbiterManager;
	
	private IBehavioralAPI api;

	@Override
	public void queueEvent(EventInstance input) {
		eventQueue.add((EventInstance) input);
		if (t != null) {
			synchronized (t) {
				t.notify();
			}
			t = null;
		}
	}
	
	@Override
	public boolean canSendEvent(EventInstance event) {
		return api.canSendEvent(event);
	}

	private List<IEventInterpreterListener> listeners = new ArrayList<>();

	@Override
	public void addListener(IEventInterpreterListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IEventInterpreterListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void processEvents() {
		if (canManageEvents) {
			canManageEvents = false;
			if (scenarioManager != null && !scenarioManager.isScenarioComplete()) {
				if (waitForEvents && eventQueue.isEmpty()) {
					throw new IllegalStateException("Event manager asked to wait for events while playing a scenario");
				}
			} else if (waitForEvents && eventQueue.isEmpty()) {
				t = Thread.currentThread();
				synchronized (t) {
					try {
						t.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				waitForEvents = false;
			}
			EventInstance event = eventQueue.poll();
			while (event != null) {
				api.dispatchEvent(event);
				event = eventQueue.poll();
			}
			canManageEvents = true;
		}
	}

	@Override
	public void waitForEvents() {
		waitForEvents = true;
	}

	private Scenario<?> pendingScenario;

	private Arbiter<?, ?, ?> pendingArbiter;

	@Override
	public void loadScenario(URI uri, ResourceSet resourceSet) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.getResource(uri.trimFragment(), true);
		EcoreUtil.resolveAll(resource);
		if (uri.hasFragment()) {
			pendingScenario = (Scenario<?>) resource.getEObject(uri.fragment());
		} else {
			pendingScenario = (Scenario<?>) resource.getContents().get(0);
		}
	}

	@Override
	public void loadArbiter(URI uri, ResourceSet resourceSet) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.getResource(uri.trimFragment(), true);
		EcoreUtil.resolveAll(resource);
		if (uri.hasFragment()) {
			pendingArbiter = (Arbiter<?, ?, ?>) resource.getEObject(uri.fragment());
		} else {
			pendingArbiter = (Arbiter<?, ?, ?>) resource.getContents().get(0);
		}
	}

	@Override
	public void engineAboutToStart(IExecutionEngine engine) {
		executedModel = engine.getExecutionContext().getResourceModel();
		if (pendingScenario != null) {
			PropertyMonitor monitor = engine.getAddon(PropertyMonitor.class);
			if (monitor != null) {
				scenarioManager = new ScenarioManager(executedModel, this, monitor);
			} else {

			}
		}
		if (pendingArbiter != null) {
			PropertyMonitor monitor = engine.getAddon(PropertyMonitor.class);
			if (monitor != null) {
				arbiterManager = new ArbiterManager(monitor);
			} else {

			}
		}
		
		final Set<IBehavioralAPI> apis = engine.getAddonsTypedBy(IBehavioralAPI.class);
		api = apis.iterator().next();
	}

	@Override
	public void engineStarted(IExecutionEngine executionEngine) {
		if (pendingScenario != null) {
			scenarioManager.loadScenario(pendingScenario);
			pendingScenario = null;
		}
		if (pendingArbiter != null) {
			arbiterManager.loadArbiter(pendingArbiter);
			pendingArbiter = null;
		}
	}

	@Override
	public void engineStopped(IExecutionEngine engine) {
		if (scenarioManager != null) {
			scenarioManager.saveScenarioReport();
		}
	}
}
