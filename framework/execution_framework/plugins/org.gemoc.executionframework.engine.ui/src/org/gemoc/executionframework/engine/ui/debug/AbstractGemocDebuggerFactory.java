package org.gemoc.executionframework.engine.ui.debug;

import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;

import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public abstract class AbstractGemocDebuggerFactory {

	public AbstractGemocDebuggerFactory(){
		
	}
	
	public abstract AbstractGemocDebugger createDebugger(IDSLDebugEventProcessor target, IBasicExecutionEngine engine); 
}
