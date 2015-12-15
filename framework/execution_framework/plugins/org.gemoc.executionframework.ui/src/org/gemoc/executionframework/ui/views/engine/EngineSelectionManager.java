package org.gemoc.executionframework.ui.views.engine;

import java.util.ArrayList;
import java.util.List;

import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

/**
 * This class is in charge of knowing what was the last selected engine and keeping the list of all EngineSelectionListener
 *
 */
public class EngineSelectionManager implements IEngineSelectionListener{

	private IBasicExecutionEngine _lastSelectedEngine;
	private final List<IEngineSelectionListener> engineSelectionListeners;
	
	public EngineSelectionManager(){
		engineSelectionListeners = new ArrayList<>();
		// register self as IEngineSelectionListener in order to know the last selected engine even if no EngineStatusView is opened
		engineSelectionListeners.add(this);
	}
	
	public IBasicExecutionEngine get_lastSelectedEngine() {
		return _lastSelectedEngine;
	}

	@Override
	public void engineSelectionChanged(IBasicExecutionEngine engine) {
		_lastSelectedEngine = engine;		
	}

	/**
	 * Gets the {@link List} of registered {@link IEngineSelectionListener}s.
	 * 
	 * @return the {@link List} of registered {@link IEngineSelectionListener}s
	 */
	public List<IEngineSelectionListener> getEngineSelectionListeners() {
		return engineSelectionListeners;
	}
	public void addEngineSelectionListener(IEngineSelectionListener listener) {
		assert(listener != null);
		engineSelectionListeners.add(listener);
	}
	public void removeEngineSelectionListener(IEngineSelectionListener listener) {
		assert(listener != null);
		engineSelectionListeners.remove(listener);
	}
	
}
