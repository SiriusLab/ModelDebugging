package org.gemoc.execution.sequential.javaengine;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.gemoc.executionframework.engine.commons.MelangeHelper;
import org.gemoc.executionframework.engine.core.AbstractSequentialExecutionEngine;
import org.gemoc.executionframework.engine.core.EngineStoppedException;
import org.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.kermeta.utils.provisionner4eclipse.Provisionner;
import org.osgi.framework.Bundle;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry;
import fr.inria.diverse.melange.adapters.EObjectAdapter;

public class PlainK3ExecutionEngine extends AbstractSequentialExecutionEngine implements IStepManager {

	private Runnable _entryPoint;
	
	private Runnable _initializeModelRunnable = null;

	class DefaultSearchRequestor extends SearchRequestor {

		public IType _binaryType;

		@Override
		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			_binaryType = (IType) match.getElement();
			System.out.println(match.getElement());
		}

	}

	/**
	 * Constructs a PlainK3 execution engine using an entry point (~ a main
	 * operation) The entrypoint will register itself as a StepManager into the
	 * K3 step manager registry, and unregister itself at the end. As a
	 * StepManager, the PlainK3ExecutionEngine will receive callbacks through
	 * its "executeStep" operation.
	 */
	@Override
	public void initialize(final IExecutionContext executionContext) {

		PlainK3ExecutionEngine.super.initialize(executionContext);

	/*
	 * Get info from the RunConfiguration
	 */
		String entryPoint = executionContext.getRunConfiguration().getExecutionEntryPoint();
		String mainModelElementURI = executionContext.getRunConfiguration().getModelEntryPoint();
		
	/*
	 * Find the entry point in the workspace
	 */
		final String PREFIX = "public static void ";
		int startName = PREFIX.length();
		int endName = entryPoint.lastIndexOf("(");
		String methodFullName = entryPoint.substring(startName, endName);
		
		String aspectClassName = methodFullName.substring(0, methodFullName.lastIndexOf("."));
		String methodName = methodFullName.substring(methodFullName.lastIndexOf(".")+1);
		

		Bundle bundle = findBundle(executionContext, aspectClassName);
		if (bundle == null)
			throw new RuntimeException("Could not find bundle for language \"" + executionContext.getRunConfiguration().getLanguageName()+"\"");
		
		// search the class
		Class<?> entryPointClass = null;
		try {
			entryPointClass = bundle.loadClass(aspectClassName);
		} catch (ClassNotFoundException e) {
			String bundleName = bundle.getHeaders().get("Bundle-Name");
			e.printStackTrace();
			throw new RuntimeException("Could not find class "
					+ executionContext.getRunConfiguration().getExecutionEntryPoint() + " in bundle " + bundleName
					+ ".");
		}
		
		// search the method
		final ArrayList<Object> parameters = new ArrayList<>();
		EObject root = executionContext.getResourceModel().getEObject(mainModelElementURI);
		if (root instanceof EObjectAdapter) {
			parameters.add(((EObjectAdapter<?>) root).getAdaptee());
		} else {
			parameters.add(root);
		}
		final Method method;
		try {
			method = MelangeHelper.findMethod(entryPointClass, root, methodName);
		} catch (Exception e) {
			String msg = "There is no \""+methodName+"\" method in "+entryPointClass.getName() +" with first parameter able to handle "+parameters.get(0).toString();
			msg += " from "+((EObject)parameters.get(0)).eClass().getEPackage().getNsURI();
			Activator.error(msg, e);
			throw new RuntimeException("Could not find method main with correct parameters.");
		}

	/*
	 * Set the entry point in the engine
	 */
		_entryPoint = new Runnable() {
			@Override
			public void run() {
				StepManagerRegistry.getInstance().registerManager(PlainK3ExecutionEngine.this);
				try {
					//since aspect's methods are static, first arg is null
					method.invoke(null, parameters.get(0));
				}
				catch (EngineStoppedException stopExeception){
					// not really an error, simply forward the stop exception
					throw stopExeception;
				}
				catch (java.lang.reflect.InvocationTargetException ite){
					// not really an error, simply forward the stop exception
					if(ite.getCause() instanceof  EngineStoppedException){
						throw (EngineStoppedException)ite.getCause();
					}
					else {
						throw new RuntimeException(ite);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					StepManagerRegistry.getInstance().unregisterManager(PlainK3ExecutionEngine.this);
				}
			}
		};
		
		// try to get the initializeModelRunnable 
		String modelInitializationMethodQName = executionContext.getRunConfiguration().getModelInitializationMethod();
		if(!modelInitializationMethodQName.isEmpty()){
			// the current system supposes that the modelInitialization method is in the same class as the entry point
			String modelInitializationMethodName = modelInitializationMethodQName.substring(modelInitializationMethodQName.lastIndexOf(".")+1);
			Method initializeMethod;
			boolean isListArgs = false;
			boolean isEListArgs = false;
			try {
				Class<?>[] modelInitializationParamType = new Class[]{parameters.get(0).getClass().getInterfaces()[0], String[].class};				
				initializeMethod = entryPointClass.getMethod(modelInitializationMethodName, modelInitializationParamType);
				isListArgs = false; // this is a java array
			} catch (Exception e) { 
				try{
					Class<?>[] modelInitializationParamType = new Class[]{parameters.get(0).getClass().getInterfaces()[0], List.class};								
					initializeMethod = entryPointClass.getMethod(modelInitializationMethodName, modelInitializationParamType);
					isListArgs = true; // this is a List

				} catch (Exception e2) { 
					try{
						Class<?>[] modelInitializationParamType = new Class[]{parameters.get(0).getClass().getInterfaces()[0], EList.class};								
						initializeMethod = entryPointClass.getMethod(modelInitializationMethodName, modelInitializationParamType);
						isEListArgs = true; // this is an EList
					} catch (Exception e3) {
					
						String msg = "There is no \""+modelInitializationMethodName+"\" method in "+entryPointClass.getName() +" with first parameter able to handle "+parameters.get(0).toString(); 
						msg += " and String[] or List<String> or EList<String> args as second parameter";
						msg += " from "+((EObject)parameters.get(0)).eClass().getEPackage().getNsURI();
						Activator.error(msg, e);
						// ((EObject)parameters.get(0)).eClass().getEPackage().getNsURI()
						throw new RuntimeException("Could not find method "+modelInitializationMethodName+" with correct parameters.");
					}
				}
			}
			final boolean final_isListArgs = isListArgs;
			final boolean final_isEListArgs = isEListArgs;
			final Method final_initializeMethod= initializeMethod;
			final ArrayList<Object> modelInitializationParameters = new ArrayList<>();
			modelInitializationParameters.add(parameters.get(0));
			if(final_isListArgs){
				final ArrayList<Object> modelInitializationListParameters = new ArrayList<>();
				for(String s : executionContext.getRunConfiguration().getModelInitializationArguments().split("\\r?\\n")){
					modelInitializationListParameters.add(s);
				}
				modelInitializationParameters.add(modelInitializationListParameters);
			} else if(final_isEListArgs){
				final EList<Object> modelInitializationListParameters = new BasicEList<>();
				for(String s : executionContext.getRunConfiguration().getModelInitializationArguments().split("\\r?\\n")){
					modelInitializationListParameters.add(s);
				}
				modelInitializationParameters.add(modelInitializationListParameters);
			} else {
				modelInitializationParameters.add(executionContext.getRunConfiguration().getModelInitializationArguments().split("\\r?\\n"));
			}
			_initializeModelRunnable = new Runnable() {
				@Override
				public void run() {
					StepManagerRegistry.getInstance().registerManager(PlainK3ExecutionEngine.this);
					try {
						final_initializeMethod.invoke(null, modelInitializationParameters.toArray());						
					} catch (EngineStoppedException stopExeception) {
						// not really an error, simply forward the stop exception
						throw stopExeception;
					} catch (java.lang.reflect.InvocationTargetException ite) {
						// not really an error, simply forward the stop exception
						if (ite.getCause() instanceof EngineStoppedException) {
							throw (EngineStoppedException) ite.getCause();
						} else {
							throw new RuntimeException(ite);
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						StepManagerRegistry.getInstance().unregisterManager(PlainK3ExecutionEngine.this);
					}
				}
			};
		}
	}

	/**
	 * Return a bundle containing 'aspectClassName'.
	 * 
	 * Return null if not found.
	 */
	private Bundle findBundle(final IExecutionContext executionContext, String aspectClassName) {
		
		// first look using JavaWorkspaceScope as this is safer and will look in dependencies
		IType mainIType = getITypeMainByWorkspaceScope(aspectClassName);
		
		Bundle bundle = null;
		String bundleName = null;
		if(mainIType != null){
			IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot) mainIType.getPackageFragment()
					.getParent();

			bundleName = packageFragmentRoot.getPath().removeLastSegments(1).lastSegment().toString();
			if(bundleName != null){

				// First we try to look into an already loaded bundle
				bundle = Platform.getBundle(bundleName);
		
				// If this doesn't work, we use the provisioner to load
				// the corresponding project
				if (bundle == null) {
		
					String projectName = mainIType.getJavaProject().getElementName();
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
					if (project != null && project.exists()
							&& !project.getFullPath().equals(executionContext.getWorkspace().getProjectPath())) {
						Provisionner p = new Provisionner();
						IStatus status = p.provisionFromProject(project, null);
						if (!status.isOK()) {
							// return status;
							throw new RuntimeException("Coudln't provision project.");
						}
					}
					bundleName = project.getName();
					bundle = Platform.getBundle(bundleName);
				}
			}
		}
		else {
			// the main isn't visible directly from the workspace, try another method 
			bundle = _executionContext.getMelangeBundle();
		}
		
		return bundle;
	}
	
	/**
	 * search the bundle that contains the Main class.
	 * The search is done in the workspace scope (ie. if it is defined in the current workspace it will find it
	 * @return the name of the bundle containing the Main class or null if not found
	 */
	protected IType getITypeMainByWorkspaceScope(String className){
		SearchPattern pattern = SearchPattern.createPattern(className, IJavaSearchConstants.CLASS,
				IJavaSearchConstants.DECLARATIONS, SearchPattern.R_EXACT_MATCH);
		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		

		DefaultSearchRequestor requestor = new DefaultSearchRequestor();
		SearchEngine engine = new SearchEngine();

		try {
			engine.search(pattern, new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, scope,
					requestor, null);
		} catch (CoreException e1) {
			throw new RuntimeException("Error while searching the bundle: " + e1.getMessage());
			// return new Status(IStatus.ERROR, Activator.PLUGIN_ID, );
		}

		return requestor._binaryType;
	}
	
	

	@Override
	public Runnable getEntryPoint() {
		return _entryPoint;
	}
	
	@Override
	public Runnable getInitializeModel() {
		return _initializeModelRunnable;
	}
	
	@Override
	/*
	 * This is the operation called from K3 code. We use this callback to pass
	 * the command to the generic executeOperation operation. (non-Javadoc)
	 * 
	 * @see fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager#
	 * executeStep(java.lang.Object,
	 * fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand,
	 * java.lang.String)
	 */
	public void executeStep(Object caller, final StepCommand command, String className, String methodName) {
		executeOperation(caller, className, methodName, new Runnable() {
			@Override
			public void run() {
				command.execute();
				engineStatus.incrementNbLogicalStepRun();
			}
		});
	}

	@Override
	/*
	 * This is the operation used to act as a StepManager in K3. We return true
	 * if we have the same editing domain as the object. (non-Javadoc)
	 * 
	 * @see
	 * fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager#canHandle
	 * (java.lang.Object)
	 */
	public boolean canHandle(Object caller) {
		if (caller instanceof EObject) {
			EObject caller_cast = (EObject) caller;
			org.eclipse.emf.transaction.TransactionalEditingDomain editingDomain = getEditingDomain(caller_cast);
			return editingDomain == this.editingDomain;

		}
		return false;
	}

	private static TransactionalEditingDomain getEditingDomain(EObject o) {
		return getEditingDomain(o.eResource().getResourceSet());
	}

	private static InternalTransactionalEditingDomain getEditingDomain(ResourceSet rs) {
		TransactionalEditingDomain edomain = org.eclipse.emf.transaction.TransactionalEditingDomain.Factory.INSTANCE
				.getEditingDomain(rs);
		if (edomain instanceof InternalTransactionalEditingDomain)
			return (InternalTransactionalEditingDomain) edomain;
		else
			return null;
	}
	
	@Override
	public String engineKindName() {
		return "GEMOC Sequential Engine";
	}
	
	public static Resource loadModel(URI modelURI) {
		Resource resource = null;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resource = resourceSet.createResource(modelURI);		
		try {
			resource.load(null);
		} catch (IOException e) {
			//chut
		}
		return resource;
	}
}
