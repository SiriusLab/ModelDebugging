package fr.inria.diverse.trace.benchmark.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.gemoc.execution.engine.core.PlainK3ExecutionEngine;

import fr.inria.diverse.trace.benchmark.Language;

public interface IDebuggerHelper {

	boolean canJump();

	void jump(int i);

	int getTraceSize();

	int getTraceMemoryFootprint(Language l, File dumpFolder, int traceSize)  throws Exception ;
	
	int computeTraceMemoryFootprint(Language l, File dumpFile, int traceSize) throws Exception;

	String getDebuggerName();

	Collection<? extends String> getAddons();

	void setExecutionEngine(PlainK3ExecutionEngine _executionEngine);

	void init();

	
	
}