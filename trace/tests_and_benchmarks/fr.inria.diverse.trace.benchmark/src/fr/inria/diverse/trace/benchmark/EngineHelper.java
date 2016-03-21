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
package fr.inria.diverse.trace.benchmark;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.gemoc.execution.engine.commons.EngineContextException;
import org.gemoc.execution.engine.commons.ModelExecutionContext;
import org.gemoc.gemoc_language_workbench.api.core.ExecutionMode;
import org.gemoc.gemoc_language_workbench.api.core.IExecutionContext;
import org.gemoc.gemoc_language_workbench.api.core.IExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.core.IRunConfiguration;
import org.gemoc.gemoc_language_workbench.extensions.k3.PlainK3ExecutionEngine;
import org.kermeta.utils.provisionner4eclipse.Provisionner;
import org.osgi.framework.Bundle;

import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;

public class EngineHelper {

	static class DefaultSearchRequestor extends SearchRequestor {

		public IType _binaryType;

		@Override
		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			_binaryType = (IType) match.getElement();
			System.out.println(match.getElement());
		}

	}

	private PlainK3ExecutionEngine _executionEngine;

	// Parameters to create execution engine
	private IExecutionContext executionContext;
	private Object o;
	private Method method;
	private ArrayList<Object> parameters;

	public void prepareEngine(URI model, IDebuggerHelper debugger, Language language) throws CoreException,
			EngineContextException {

		IRunConfiguration runConfiguration = new BenchmarkRunConfiguration(debugger, language, model);

		// We don't want to debug actually, ie we don't want the animator
		ExecutionMode executionMode = ExecutionMode.Run;

		// In this construction, the addons are created and loaded as well
		executionContext = new ModelExecutionContext(runConfiguration, executionMode);

		String className = executionContext.getRunConfiguration().getExecutionEntryPoint();
		SearchPattern pattern = SearchPattern.createPattern(className, IJavaSearchConstants.CLASS,
				IJavaSearchConstants.DECLARATIONS, SearchPattern.R_EXACT_MATCH);
		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		DefaultSearchRequestor requestor = new DefaultSearchRequestor();
		SearchEngine engine = new SearchEngine();

		engine.search(pattern, new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, scope,
				requestor, null);

		IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot) requestor._binaryType.getPackageFragment()
				.getParent();

		parameters = new ArrayList<>();
		parameters.add(executionContext.getResourceModel().getContents().get(0));
		String bundleName = null;
		bundleName = packageFragmentRoot.getPath().removeLastSegments(1).lastSegment().toString();

		Class<?> c = null;

		Bundle bundle = Platform.getBundle(bundleName);

		// If not found, we try again with projects
		if (bundle == null) {

			String projectName = requestor._binaryType.getJavaProject().getElementName();
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if (project != null && project.exists()
					&& !project.getFullPath().equals(executionContext.getWorkspace().getProjectPath())) {
				Provisionner p = new Provisionner();
				IStatus status = p.provisionFromProject(project, null);
				if (!status.isOK()) {
					throw new CoreException(new Status(1, "EngineHelper", "couldn't provision project :("));
				}
			}
			bundleName = project.getName();
			bundle = Platform.getBundle(bundleName);

		}

		try {
			c = bundle.loadClass(executionContext.getRunConfiguration().getExecutionEntryPoint());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CoreException(new Status(1, "EngineHelper", "couldn't load Main class"));
		}
		method = null;
		try {
			method = c.getMethod("main", parameters.get(0).getClass().getInterfaces()[0]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(new Status(1, "EngineHelper", "couldn't find main method"));
		}
		o = null;
		try {
			o = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(new Status(1, "EngineHelper", "couldn't create Main object"));
		}

		_executionEngine = new PlainK3ExecutionEngine(executionContext, o, method, parameters);
		debugger.setExecutionEngine(_executionEngine);

	}

	public void execute() {
		_executionEngine.start();
		_executionEngine.joinThread();
	}

	public Resource getModel() {
		return _executionEngine.getExecutionContext().getResourceModel();
	}

	public void removeStoppedEngines() {
		for (Entry<String, IExecutionEngine> engineEntry : org.gemoc.execution.engine.Activator.getDefault().gemocRunningEngineRegistry
				.getRunningEngines().entrySet()) {
			switch (engineEntry.getValue().getRunningStatus()) {
			case Stopped:
				org.gemoc.execution.engine.Activator.getDefault().gemocRunningEngineRegistry
						.unregisterEngine(engineEntry.getKey());
				break;
			default:
			}
		}
	}

	public void clearCommandStackAndAdapters() {
		TransactionUtil.getEditingDomain(executionContext.getResourceModel()).getCommandStack().flush();;
		executionContext.getResourceModel().eAdapters().clear();
		executionContext.getResourceModel().unload();
	}
}
