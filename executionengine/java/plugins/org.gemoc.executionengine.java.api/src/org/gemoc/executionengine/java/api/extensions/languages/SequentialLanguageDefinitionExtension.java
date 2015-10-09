package org.gemoc.executionengine.java.api.extensions.languages;

import org.eclipse.core.runtime.CoreException;
import org.gemoc.gemoc_language_workbench.api.extensions.languages.LanguageDefinitionExtension;
import org.gemoc.execution.engine.debug.AbstractGemocDebugger;

import fr.obeo.dsl.debug.ide.IDSLDebugger;

public class SequentialLanguageDefinitionExtension extends LanguageDefinitionExtension {

	final public AbstractGemocDebugger instanciateDSLDebugger() throws CoreException {
		Object instance = instanciate(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT_XDSML_DEF_GEMOCDEBUGGER_ATT);
		if (instance instanceof IDSLDebugger) {
			return (AbstractGemocDebugger) instance;
		}
		throwInstanciationCoreException();
		return null;
	}


//	final public Collection<IEngineAddon> instanciateEngineAddons() throws CoreException {
//		ArrayList<IEngineAddon> addons = new ArrayList<IEngineAddon>();
//		for (IConfigurationElement childConfElement : _configurationElement
//				.getChildren(ConcurrentLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF)) {
//			childConfElement.getName();
//			final Object addon = childConfElement
//					.createExecutableExtension(ConcurrentLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF_ENGINE_ADDON_ATT);
//			if (addon instanceof IEngineAddon) {
//				addons.add((IEngineAddon) addon);
//			}
//		}
//		return addons;
//	}

//	final public Collection<IMSEStateController> instanciateMSEStateControllers() throws CoreException {
//		ArrayList<IMSEStateController> controllers = new ArrayList<IMSEStateController>();
//		for (IConfigurationElement childConfElement : _configurationElement
//				.getChildren(SequentialLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_DEFINITION)) {
//			childConfElement.getName();
//			final Object c = childConfElement
//					.createExecutableExtension(SequentialLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_CLASS_DEFINITION);
//			if (c instanceof IMSEStateController) {
//				controllers.add((IMSEStateController) c);
//			}
//		}
//		return controllers;
//	}


//	private LanguageDefinition _languageDefinitionCache;
//
//	public LanguageDefinition getLanguageDefinition() {
//		if (_languageDefinitionCache == null) {
//
//			// Loading languagedef model
//			ResourceSet rs = new ResourceSetImpl();
//			URI uri = URI.createPlatformPluginURI(getXDSMLFilePath(), true);
//			Resource res = rs.createResource(uri);
//			try {
//				res.load(null);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			EcoreUtil.resolveAll(rs);// IMPORTANT
//
//			if (res != null) {
//				EObject first = res.getContents().get(0);
//
//				// Follow-up in other operation...
//				if (first instanceof LanguageDefinition) {
//					_languageDefinitionCache = (LanguageDefinition) first;
//				}
//			}
//		}
//		return _languageDefinitionCache;
//	}

}
