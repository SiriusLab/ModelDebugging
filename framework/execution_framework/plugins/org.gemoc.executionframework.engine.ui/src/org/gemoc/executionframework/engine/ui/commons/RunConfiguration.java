package org.gemoc.executionframework.engine.ui.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint;
import org.omg.CORBA._IDLTypeStub;

import fr.obeo.dsl.debug.ide.launch.AbstractDSLLaunchConfigurationDelegate;

public class RunConfiguration implements IRunConfiguration {

	// main launch parameters
	public static final String LAUNCH_MODEL_PATH = "GEMOC_LAUNCH_MODEL_PATH";
	public static final String LAUNCH_DELAY = "GEMOC_ANIMATE_DELAY";
	public static final String LAUNCH_SELECTED_LANGUAGE = "GEMOC_LAUNCH_SELECTED_LANGUAGE";
	public static final String LAUNCH_MELANGE_QUERY = "GEMOC_LAUNCH_MELANGE_QUERY";
	public static final String LAUNCH_ENTRY_POINT = "GEMOC_LAUNCH_ENTRY_POINT";
	public static final String LAUNCH_INITIALIZATION_METHOD = "GEMOC_LAUNCH_INITIALIZATION_METHOD";
	public static final String LAUNCH_INITIALIZATION_ARGUMENTS = "GEMOC_LAUNCH_INITIALIZATION_ARGUMENTS";
	public static final String LAUNCH_BREAK_START = "GEMOC_LAUNCH_BREAK_START";

	// parameters that should be derived from the language in future version
	public static final String LAUNCH_DEADLOCK_DETECTION_DEPTH = "GEMOC_LAUNCH_DEADLOCK_DETECTION_DEPTH";

	protected ILaunchConfiguration _launchConfiguration;

	public RunConfiguration(ILaunchConfiguration launchConfiguration) throws CoreException {
		_launchConfiguration = launchConfiguration;
		extractInformation();
	}

	protected void extractInformation() throws CoreException {
		_languageName = getAttribute(LAUNCH_SELECTED_LANGUAGE, "");
		_modelURI = URI.createPlatformResourceURI(
				getAttribute(AbstractDSLLaunchConfigurationDelegate.RESOURCE_URI, ""), true);
		_melangeQuery = getAttribute(LAUNCH_MELANGE_QUERY, "");
		String animatorURIAsString = getAttribute("airdResource", "");
		if (animatorURIAsString != null && !animatorURIAsString.equals("")) {
			_animatorURI = URI.createPlatformResourceURI(animatorURIAsString, true);
			_animationDelay = getAttribute(LAUNCH_DELAY, 0);
		}
		_deadlockDetectionDepth = getAttribute(LAUNCH_DEADLOCK_DETECTION_DEPTH, 10);
		_entryPoint = getAttribute(LAUNCH_ENTRY_POINT, "");
		_modelInitializationMethod = getAttribute(LAUNCH_INITIALIZATION_METHOD, "");
		_modelInitializationArguments = getAttribute(LAUNCH_INITIALIZATION_ARGUMENTS, "");

		for (EngineAddonSpecificationExtension extension : EngineAddonSpecificationExtensionPoint.getSpecifications()) {
			_engineAddonExtensions.put(extension, getAttribute(extension.getName(), false));
		}

		_breakStart = getAttribute(LAUNCH_BREAK_START, Boolean.FALSE);
	}

	protected String getAttribute(String attributeName, String defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	protected Integer getAttribute(String attributeName, Integer defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	protected Boolean getAttribute(String attributeName, Boolean defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	private String _languageName;

	public String getLanguageName() {
		return _languageName;
	}

	private int _animationDelay = 0;

	public int getAnimationDelay() {
		return _animationDelay;
	}



	private URI _modelURI;

	@Override
	public URI getExecutedModelURI() {
		return _modelURI;
	}

	private String _melangeQuery;

	@Override
	public String getMelangeQuery() {
		return _melangeQuery;
	}

	@Override
	public URI getExecutedModelAsMelangeURI() {
		if (_melangeQuery.isEmpty())
			return _modelURI;
		String melangeURIString = _modelURI.toString().replace("platform:/", "melange:/") + _melangeQuery;
		return URI.createURI(melangeURIString);
	}

	private URI _animatorURI;

	@Override
	public URI getAnimatorURI() {
		return _animatorURI;
	}

	private int _deadlockDetectionDepth = 0;

	@Override
	public int getDeadlockDetectionDepth() {
		return _deadlockDetectionDepth;
	}

	private HashMap<EngineAddonSpecificationExtension, Boolean> _engineAddonExtensions = new HashMap<>();

	@Override
	public Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions() {
		ArrayList<EngineAddonSpecificationExtension> result = new ArrayList<EngineAddonSpecificationExtension>();
		for (Entry<EngineAddonSpecificationExtension, Boolean> e : _engineAddonExtensions.entrySet()) {
			if (e.getValue())
				result.add(e.getKey());
		}
		return result;
	}

	private String _entryPoint;

	@Override
	public String getExecutionEntryPoint() {
		return _entryPoint;
	}

	private String _modelInitializationMethod;

	@Override
	public String getModelInitializationMethod() {
		return _modelInitializationMethod;
	}
	
	private String _modelInitializationArguments;

	@Override
	public String getModelInitializationArguments() {
		return _modelInitializationArguments;
	}
	
	private boolean _breakStart;

	public boolean getBreakStart() {
		return _breakStart;
	}

}
