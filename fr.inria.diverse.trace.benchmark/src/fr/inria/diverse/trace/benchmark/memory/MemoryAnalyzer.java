/*******************************************************************************
 * Copyright (c) 2014 Université de Rennes 1.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erwan Bousse - initial API and implementation
 ******************************************************************************/
package fr.inria.diverse.trace.benchmark.memory;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.mat.SnapshotException;
import org.eclipse.mat.snapshot.ISnapshot;
import org.eclipse.mat.snapshot.SnapshotFactory;
import org.eclipse.mat.snapshot.model.IClass;
import org.eclipse.mat.util.IProgressListener;
import org.eclipse.mat.util.VoidProgressListener;

public class MemoryAnalyzer {

	public static final IProgressListener progressListener = new VoidProgressListener();

	public static int computeRetainedSizeOfClass(String className, File dumpFile) {

		// We open the dump with Eclipse Memory Analyzer, and obtain a snapshot
		// object
		ISnapshot snapshot;
		int sum = 0;
		try {
			snapshot = SnapshotFactory.openSnapshot(dumpFile, new HashMap<String, String>(), progressListener);
			// First we look for the class in the dump file
			Collection<IClass> foundClasses = snapshot.getClassesByName(className, false);
			if (foundClasses == null) {
				System.out.println("Warning: class " + className + " not found.");
			} else {
				// There can be multiple times the same class in weird
				// situations
				if (foundClasses.size() > 1) {
					System.out.println("Warning: multiple classes with name " + className + ".");
				}
				// For each class occurrence with this name found
				for (IClass foundClass : foundClasses) {
					// This is where the calculation is done.
					sum += foundClass.getRetainedHeapSizeOfObjects(true, false, null);
					// sum += foundClass.getRetainedHeapSizeOfObjects(true,
					// true, progressListener); // switched to
					// estimations
				}
			}

			SnapshotFactory.dispose(snapshot);

		} catch (SnapshotException e) {
			System.err.println("Error while computing memory consumption!");
			e.printStackTrace();
		}
		return sum;

	}
	/*
	 * public static void dumpHeap(File dumpFile) {
	 * 
	 * try { HeapDumpProviderRegistry registry =
	 * HeapDumpProviderRegistry.instance(); IHeapDumpProvider dumpProvider =
	 * registry
	 * .getHeapDumpProvider("jmapheapdumpprovider").getHeapDumpProvider();
	 * List<? extends VmInfo> vms =
	 * dumpProvider.getAvailableVMs(progressListener); VmInfo currentVm = null;
	 * int pid =
	 * Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().
	 * split("@")[0]); for (VmInfo vm : vms) { if (vm.getPid() == pid) {
	 * currentVm = vm; break; } }
	 * 
	 * dumpProvider.acquireDump(currentVm, dumpFile, progressListener);
	 * 
	 * 
	 * } catch (SnapshotException e) { // TODO Bloc catch généré automatiquement
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}
