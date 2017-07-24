package fr.inria.diverse.event.commons.interpreter.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.event.commons.interpreter.property.PropertyMonitor;
import fr.inria.diverse.event.commons.interpreter.scenario.ArbiterManager;
import fr.inria.diverse.event.commons.interpreter.scenario.ScenarioManager;
import fr.inria.diverse.event.commons.model.EventInstance;
import fr.inria.diverse.event.commons.model.EventManagerRegistry;
import fr.inria.diverse.event.commons.model.IEventManager;
import fr.inria.diverse.event.commons.model.IEventManagerListener;
import fr.inria.diverse.event.commons.model.scenario.Arbiter;
import fr.inria.diverse.event.commons.model.scenario.Scenario;
import fr.inria.diverse.trace.commons.model.trace.Step;

public abstract class AbstractEventManager implements IEventManager {

	private Resource executedModel;

	private final Queue<EventInstance> eventQueue = new ConcurrentLinkedQueue<>();

	private boolean canManageEvents = true;

	private boolean waitForEvents = false;

	private Thread t = null;

	protected ScenarioManager scenarioManager;

	protected ArbiterManager arbiterManager;

	@Override
	public void sendEvent(Object input) {
		if (input instanceof EventInstance) {
			eventQueue.add((EventInstance) input);
			if (t != null) {
				synchronized (t) {
					t.notify();
				}
				t = null;
			}
		}
	}

	@Override
	public void receiveEvent(Object result, Object caller, String className, String methodName) {
		final EventInstance event = rebuildEvent(result, caller, className, methodName);
		if (event != null) {
			listeners.forEach(l -> l.eventReceived(event));
		}
	}

	private List<IEventManagerListener> listeners = new ArrayList<>();

	@Override
	public void addListener(IEventManagerListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IEventManagerListener listener) {
		listeners.remove(listener);
	}

	protected abstract EventInstance rebuildEvent(Object result, Object caller, String className, String methodName);

	@Override
	public void manageEvents() {
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
				dispatchEvent(event);
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

	protected abstract void dispatchEvent(EventInstance event);

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
	}

	@Override
	public void engineInitialized(IExecutionEngine executionEngine) {
	}

	@Override
	public void engineStarted(IExecutionEngine executionEngine) {
		EventManagerRegistry.getInstance().registerManager(this);
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
	public void engineAboutToStop(IExecutionEngine engine) {
	}

	@Override
	public void engineStopped(IExecutionEngine engine) {
		if (scenarioManager != null) {
			scenarioManager.saveScenarioReport();
		}
		EventManagerRegistry.getInstance().unregisterManager(this);
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine engine) {
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine engine, Collection<Step<?>> steps) {
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine engine, Collection<Step<?>> steps) {
	}

	@Override
	public void stepSelected(IExecutionEngine engine, Step<?> selectedStep) {
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine engine, Step<?> stepToExecute) {
	}

	@Override
	public void stepExecuted(IExecutionEngine engine, Step<?> stepExecuted) {
	}

	@Override
	public void engineStatusChanged(IExecutionEngine engine, RunStatus newStatus) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return Collections.emptyList();
	}
}
