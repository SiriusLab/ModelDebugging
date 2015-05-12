package fr.inria.diverse.trace.benchmark.debuggers;

import java.io.File;
import java.io.IOException;

import fr.inria.diverse.trace.benchmark.Language;
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;
import fr.inria.diverse.trace.benchmark.memory.HeapDump;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer;

public abstract class AbstractTraceDebugger implements IDebuggerHelper {
	
	private String rootClassName;
	int memory = -1;
	
	public AbstractTraceDebugger(String rootClassName) {
		this.rootClassName =rootClassName;
	}

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	@Override
	public int getTraceMemoryFootprint(Language l, File dumpFolder) throws IOException {

		if (memory == -1) {
			if (dumpFolder == null) {
				dumpFolder = File.createTempFile("omniscientDebuggingBenchmark", "");
				dumpFolder.delete();
				dumpFolder.mkdir();
				dumpFolder.deleteOnExit();
			}
			
			deleteFolder(dumpFolder);
			dumpFolder.mkdirs();
			
			File dumpFile = new File(dumpFolder, "heapDump");
			HeapDump.dumpHeap(dumpFile.getAbsolutePath(), true);
			if (rootClassName != null)
				memory = MemoryAnalyzer.computeRetainedSizeOfClass(rootClassName, dumpFile);
			else
				memory = MemoryAnalyzer.computeRetainedSizeOfClass(l.rootClassName, dumpFile);
			deleteFolder(dumpFolder);
		}
		return memory;
		
	}

}
