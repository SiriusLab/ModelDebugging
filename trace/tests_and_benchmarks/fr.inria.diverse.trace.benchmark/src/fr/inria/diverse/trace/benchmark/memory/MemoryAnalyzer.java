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
import java.lang.management.ManagementFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.mat.SnapshotException;
import org.eclipse.mat.internal.acquire.HeapDumpProviderRegistry;
import org.eclipse.mat.query.IResultTable;
import org.eclipse.mat.snapshot.IOQLQuery;
import org.eclipse.mat.snapshot.ISnapshot;
import org.eclipse.mat.snapshot.OQLParseException;
import org.eclipse.mat.snapshot.SnapshotFactory;
import org.eclipse.mat.snapshot.acquire.IHeapDumpProvider;
import org.eclipse.mat.snapshot.acquire.VmInfo;
import org.eclipse.mat.snapshot.model.IClass;
import org.eclipse.mat.util.IProgressListener;
import org.eclipse.mat.util.VoidProgressListener;

public class MemoryAnalyzer {

	public static final IProgressListener progressListener = new VoidProgressListener();

	public static class QueryResult {
		public int nbElements;
		public int memorySum;
	}

	public ISnapshot snapshot;

	public MemoryAnalyzer(File dumpFile) {
		try {
			snapshot = SnapshotFactory.openSnapshot(dumpFile, new HashMap<String, String>(), progressListener);
		} catch (SnapshotException e) {
			System.err.println("Error while parsing dump!");
			e.printStackTrace();
		}
		assert (snapshot != null);
	}

	public QueryResult computeRetainedSizeWithOQLQuery(String query, File dumpFile) {
		// We open the dump with Eclipse Memory Analyzer, and obtain a snapshot
		// object
		QueryResult res = new QueryResult();
		try {
			IOQLQuery queryObj = SnapshotFactory.createQuery(query);
			Object result = queryObj.execute(snapshot, new VoidProgressListener());

			//System.out.println("OQL result: " + result);
			//System.out.println("OQL class: " + result.getClass().getCanonicalName());

			if (result instanceof IOQLQuery.Result) {
				IResultTable castResult2 = (IResultTable) result;
				int sum = 0;
				for (int i = 0; i < castResult2.getRowCount(); i++) {
					sum += (long) castResult2.getColumnValue(i, 0);
				}
				res.memorySum = sum;
				res.nbElements = castResult2.getRowCount();
			} else if (result instanceof int[]) {
				int[] castResult = (int[]) result;
				res.nbElements = castResult.length;
			}

		} catch (OQLParseException e) {
			System.out.println("Error: parsing of the OQL query failed. Line " + e.getLine() + ", Column "
					+ e.getColumn() + ".");
			e.printStackTrace();
		} catch (SnapshotException e) {
			System.err.println("Error while computing memory consumption!");
			e.printStackTrace();
		}
		return res;
	}

	public int computeRetainedSizeOfClass(String className, File dumpFile) {

		// We open the dump with Eclipse Memory Analyzer, and obtain a snapshot
		// object
		int sum = 0;
		try {
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
				}
			}

			SnapshotFactory.dispose(snapshot);

		} catch (SnapshotException e) {
			System.err.println("Error while computing memory consumption!");
			e.printStackTrace();
		}
		return sum;

	}
	
	
	public static void dumpHeap(File dumpFile) throws SnapshotException {

			HeapDumpProviderRegistry registry = HeapDumpProviderRegistry.instance();
			IHeapDumpProvider dumpProvider = registry.getHeapDumpProvider("jmapheapdumpprovider").getHeapDumpProvider();
			List<? extends VmInfo> vms = dumpProvider.getAvailableVMs(progressListener);
			VmInfo currentVm = null;
			int pid = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
			for (VmInfo vm : vms) {
				if (vm.getPid() == pid) {
					currentVm = vm;
					break;
				}
			}
						
			dumpProvider.acquireDump(currentVm, dumpFile, progressListener);
			
			
		

	}



}
