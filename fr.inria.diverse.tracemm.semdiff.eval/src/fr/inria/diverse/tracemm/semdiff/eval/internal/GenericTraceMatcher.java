package fr.inria.diverse.tracemm.semdiff.eval.internal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.MatchRule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.util.EMFUtil;

public class GenericTraceMatcher {

	private static final String LEFT_MODEL_NAME = "Left";
	private static final String RIGHT_MODEL_NAME = "Right";

	private ResourceSet resourceSet;
	
	private Resource metamodelResource;
	private Resource configurationResource;
	private Resource leftModelResource;
	private Resource rightModelResource;
	
	private File matchRules;
	
	private boolean eolRuntimeException = false;
	
	public GenericTraceMatcher() {
		setupResourceSet();
	}
	
	private void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
	}
	
	public boolean match(String leftModelPath, String rightModelPath, String metamodelPath, String configurationPath, String matchRulesPath) {
		loadResources(leftModelPath, rightModelPath, metamodelPath,
				configurationPath, matchRulesPath);
		EclModule eclModule = createEclModuleForTraceMatching();
		EpsilonUtil.initEclModule(eclModule);

		StateSystem left = (StateSystem)leftModelResource.getContents().get(0);
		StateSystem right = (StateSystem)rightModelResource.getContents().get(0);
		MatchRule semanticMatchRule = EpsilonUtil.getSemanticMatchRule(eclModule, left, right);
		
		Match match = null;
		try {
			match = EpsilonUtil.matchRule(eclModule, semanticMatchRule, left, right);
		} catch(EolRuntimeException e) {
			eolRuntimeException = true;
		}
		return match != null? match.isMatching() : false;
	}
	
	public boolean matchedWithoutErrors() {
		return !eolRuntimeException;
	}

	private void loadResources(String leftModelPath, String rightModelPath,
			String metamodelPath, String configurationPath, String matchRulesPath) {
		if(metamodelResource != null) {
			metamodelResource = Util.loadResource(resourceSet, metamodelPath);
		}
		configurationResource = Util.loadResource(resourceSet, configurationPath);
		leftModelResource = Util.loadResource(resourceSet, leftModelPath);
		rightModelResource = Util.loadResource(resourceSet, rightModelPath);
		matchRules = EMFUtil.createFile(matchRulesPath);
	}
	
	private EclModule createEclModuleForTraceMatching() {
		EPackage traceEPackage = TracemodelPackage.eINSTANCE;
		EPackage statesEPackage = StatesPackage.eINSTANCE;
		EPackage umlEPackage = UMLPackage.eINSTANCE;

		Collection<EPackage> ePackages = new HashSet<EPackage>();
		if(metamodelResource != null)
			ePackages.add(EMFUtil.getRootEPackage(metamodelResource));
		ePackages.add(traceEPackage);
		ePackages.add(statesEPackage);
		ePackages.add(umlEPackage);
		ePackages.addAll(EMFUtil.getEPackages(configurationResource));

		EclModule moduleSemantics = EpsilonUtil.createEclModule(
				matchRules, leftModelResource,
				LEFT_MODEL_NAME, rightModelResource, RIGHT_MODEL_NAME,
				ePackages);

		EpsilonUtil.setNativeTypeDelegateToModule(
				moduleSemantics,
				 this.getClass()
						.getClassLoader());

		moduleSemantics.getContext().setWarningStream(new PrintStream(new OutputStream() {			
			@Override
			public void write(int b) throws IOException {
			}
		}));

		return moduleSemantics;
	}
}
