package fr.inria.diverse.trace.benchmark.debuggers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.inria.diverse.trace.benchmark.Language;
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;
import fr.inria.diverse.trace.benchmark.memory.HeapDump;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer;

public abstract class AbstractTraceDebugger implements IDebuggerHelper {

	@Override
	public void init() {
		memory = -1;
	}


	int memory = -1;

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
	/**
	 * TODO
	 * In fact we forget lots of stuff: clones are stored at the root of the resource!
	 */
	public int getTraceMemoryFootprint(Language l, File dumpFolder, int traceSize) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		String folderName = dateString + "_" + this.getDebuggerName() + "_" + l.languageName;

		if (memory == -1) {
			if (dumpFolder == null) {
				dumpFolder = File.createTempFile(folderName, "");
				dumpFolder.delete();
				dumpFolder.mkdir();
			}

			deleteFolder(dumpFolder);

			File innerDumpFolder = new File(dumpFolder, folderName);
			innerDumpFolder.mkdirs();

			File dumpFile = new File(innerDumpFolder, "heapDump");
			HeapDump.dumpHeap(dumpFile.getAbsolutePath(), true);
//			if (rootClassName != null)
//				memory = MemoryAnalyzer.computeRetainedSizeOfClass(rootClassName, dumpFile);
//			else
//				memory = MemoryAnalyzer.computeRetainedSizeOfClass(l.rootClassName, dumpFile);
			
			memory = computeTraceMemoryFootprint(l, dumpFile, traceSize);
			
			deleteFolder(dumpFolder);
		}
		return memory;

	}
	
		
	

}
