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
package fr.inria.diverse.tracemm.xmof.slicer.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.diverse.tracemm.xmof.slicer.XMOFTraceMMSlicer;

public class XMOFTraceMMSlicerTest {

	private static final String FUML_TRACE_MM_PATH = "fr.inria.diverse.tracemm.xmof.footprint.eol.test/tracemms/fumltracemmNEWFormat.ecore";
	private static final String FUML_XMOF_MODEL_PATH = "fr.inria.diverse.tracemm.xmof.footprint.eol.test/xmofmodels/fuml.xmof";
	private static final String FUML_TRACING_PA_ORIGINAL_PATH = "fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator.test/tracingprofileapps/actionExecutionOrder.pa.xmi";

	private static final String TRACING_PA_PROJECT = "fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator.test.profileapplications";
	private static final String FUML_TRACING_PA_NEW_PATH = TRACING_PA_PROJECT
			+ "/actionExecutionOrder.pa.xmi";;

	@Test
	public void test() throws IOException {
		URI traceMMURI = URI.createPlatformPluginURI(FUML_TRACE_MM_PATH, true);
		URI xmofModelURI = URI.createPlatformPluginURI(FUML_XMOF_MODEL_PATH,
				true);

		URI tracingPaOriginalLocationURI = URI.createPlatformPluginURI(
				FUML_TRACING_PA_ORIGINAL_PATH, true);
		URI tracingPaNewLocationURI = URI.createPlatformResourceURI(
				FUML_TRACING_PA_NEW_PATH, true);

		copyProfileApplicationViaFileStream(tracingPaOriginalLocationURI,
				tracingPaNewLocationURI);

		XMOFTraceMMSlicer slicer = new XMOFTraceMMSlicer(traceMMURI, xmofModelURI,
				tracingPaNewLocationURI);
		boolean sliceCreated = slicer.computeSlice();
		assertTrue(sliceCreated);
	}

	@BeforeClass
	public static void createProfileApplicationProject() throws CoreException {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workspaceRoot.getProject(TRACING_PA_PROJECT);
		if (!project.exists()) {
			project.create(null);
			project.open(null);
		}
	}

	private void copyProfileApplicationViaFileStream(URI originalPaURI,
			URI newPaURI) throws IOException {
		URIConverter uriConverter = new ExtensibleURIConverterImpl();
		InputStream inputStream = uriConverter.createInputStream(originalPaURI);
		OutputStream outputStream = uriConverter.createOutputStream(newPaURI);
		int b;
		while ((b = inputStream.read()) != -1) {
			outputStream.write(b);
		}
		inputStream.close();
		outputStream.close();
	}

	private void copyProfileApplicationViaResources(URI originalPaURI,
			URI newPaURI) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource originalPaResource = resourceSet.getResource(originalPaURI,
				true);
		EcoreUtil.resolveAll(originalPaResource);
		Resource newPaResource = resourceSet.createResource(newPaURI);
		newPaResource.getContents().addAll(originalPaResource.getContents());
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		newPaResource.save(null);
		originalPaResource.unload();
		newPaResource.unload();
	}

	private void copyProfileApplicationViaCopier(URI originalPaURI, URI newPaURI)
			throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource originalPaResource = resourceSet.getResource(originalPaURI,
				true);
		EcoreUtil.resolveAll(originalPaResource);
		Resource newPaResource = resourceSet.createResource(newPaURI);
		Copier copier = new Copier();
		originalPaResource
				.getContents()
				.stream()
				.forEach(
						eObject -> newPaResource.getContents().add(
								copier.copy(eObject)));
		copier.copyReferences();
		newPaResource.save(null);
		originalPaResource.unload();
		newPaResource.unload();
	}
}
