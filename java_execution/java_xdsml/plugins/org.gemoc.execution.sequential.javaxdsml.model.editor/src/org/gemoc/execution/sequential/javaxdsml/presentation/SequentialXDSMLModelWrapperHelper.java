package org.gemoc.execution.sequential.javaxdsml.presentation;

import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;




/**
 * This helper is to workaround a strange problem with Windows Builder
 * if a method with non simple type is declared in the XDSMLModelWrapper then the data binding tool doesn't work correctly :-/
 * @author dvojtise
 *
 */
class SequentialXDSMLModelWrapperHelper{
	public static void init(SequentialXDSMLModelWrapper wrapper, SequentialLanguageDefinition languageDefinition) {
		wrapper.languageDefinition=languageDefinition;
		languageDefinition.eAdapters().add(new EMFEContent(wrapper));
	}
}