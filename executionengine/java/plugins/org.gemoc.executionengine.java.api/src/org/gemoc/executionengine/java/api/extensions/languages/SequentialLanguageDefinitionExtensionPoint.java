package org.gemoc.executionengine.java.api.extensions.languages;

import java.util.Collection;

import org.gemoc.gemoc_language_workbench.api.extensions.ExtensionPoint;

public class SequentialLanguageDefinitionExtensionPoint extends ExtensionPoint<SequentialLanguageDefinitionExtension>
{


	public static final String GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT = "org.gemoc.gemoc_language_workbench.sequential.xdsml";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF = "XDSML_Definition";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_NAME_ATT = "name";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT = "modelLoader_class";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_SOLVER_ATT = "solver_class";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_CODEEXECUTOR_ATT = "codeExecutor_class";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT = "xdsmlFilePath";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_TO_CCSL_QVTO_FILE_PATH_ATT = "toCCSLQVTOFilePath";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF = "EngineAddon_Definition";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF_ENGINE_ADDON_ATT = "engineAddon_class";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_DEFINITION = "MSE_State_Controller_Definition";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_CLASS_DEFINITION= "MSE_State_Controller_Class";
	
	
	protected SequentialLanguageDefinitionExtensionPoint() 
	{
		super(SequentialLanguageDefinitionExtension.class);
	}

	private static SequentialLanguageDefinitionExtensionPoint _singleton;
	
	private static SequentialLanguageDefinitionExtensionPoint getExtensionPoint()
	{
		if (_singleton == null)
		{
			_singleton = new SequentialLanguageDefinitionExtensionPoint();
		}
		return _singleton;
	}
		
	static public Collection<SequentialLanguageDefinitionExtension> getSpecifications() 
	{
		return getExtensionPoint().internal_getSpecifications();
	}
	
	static public SequentialLanguageDefinitionExtension findDefinition(String languageName)
	{
		for (SequentialLanguageDefinitionExtension extension : getSpecifications())
		{
			if (extension.getName().equals(languageName))
			{
				return extension;
			}
		}
		return null;
	}


	@Override
	protected String getExtensionPointName()
	{
		return GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT;
	}
		
}
