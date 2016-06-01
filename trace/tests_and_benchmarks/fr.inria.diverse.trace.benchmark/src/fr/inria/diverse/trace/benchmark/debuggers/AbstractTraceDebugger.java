/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.benchmark.debuggers;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.ecore.resource.Resource;

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
			

			// MemoryAnalyzer.dumpHeap(dumpFile);

			boolean ok = false;
			for (int i = 0; i < 5 && !ok; i++) {
				
				try {
					
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
					memory = computeTraceMemoryFootprint(l, dumpFile, traceSize);
					deleteFolder(dumpFolder);
					ok = true;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR while analyzing memory - attempt "+i+"/4");
					if (i == 5)
						throw e;
				} 
			}

		}
		return memory;

	}
}
