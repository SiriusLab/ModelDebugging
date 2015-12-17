package org.gemoc.execution.engine.debug;

import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public abstract class AbstractGemocDebuggerFactory {

	public AbstractGemocDebuggerFactory(){
		
	}
	
	public abstract AbstractGemocDebugger createDebugger(IDSLDebugEventProcessor target, IBasicExecutionEngine engine); 
}
