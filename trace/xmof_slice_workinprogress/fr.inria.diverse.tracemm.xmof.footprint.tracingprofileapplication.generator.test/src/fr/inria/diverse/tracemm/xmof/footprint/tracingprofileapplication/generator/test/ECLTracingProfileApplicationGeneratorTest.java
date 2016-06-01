package fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator.test;

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
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator.TracingProfileApplicationGenerator;

public class ECLTracingProfileApplicationGeneratorTest {

	private static final String TRACING_PA_PROJECT = "fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator.test.profileapplications";

	private static final String FUML_XMOF_MODEL_PATH = "fr.inria.diverse.tracemm.xmof.footprint.eol.test/xmofmodels/fuml.xmof";
	private static final String FUML_FOOTPRINT_PATH = "fr.inria.diverse.tracemm.xmof.footprint.eol.test/footprints/actionExecutionOrder_fumltracemmWithAnnotations.xmi";
	private static final String FUML_TRACING_PA_PATH = TRACING_PA_PROJECT + "/actionExecutionOrder.pa.xmi";

	@Test
	public void testTracingProfileApplicationGeneratior_FUML() throws IOException, CoreException {
		URI footprintURI = URI.createPlatformPluginURI(FUML_FOOTPRINT_PATH, true);
		URI xmofModelURI = URI.createPlatformPluginURI(FUML_XMOF_MODEL_PATH, true);
		URI profileApplicationURI = URI.createPlatformResourceURI(FUML_TRACING_PA_PATH, true);

		TracingProfileApplicationGenerator generator = new TracingProfileApplicationGenerator(footprintURI,
				xmofModelURI);
		generator.generateProfileApplication(profileApplicationURI);
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
}
