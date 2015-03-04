package fr.inria.diverse.tracemm.semdiff.eval;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.tracemm.semdiff.eval.internal.ModelExecutor;

public abstract class TraceExtraction extends Evaluation {

	public EObject executeFumlTestmodel(int modelNumber, boolean domainSpecific) {
		String modelPath = getFumlTestmodelModelPath();
		String parameterDefinitionPath = deriveFumlTestmodelParameterDefinitionPath(modelNumber);
		String tracemodelPath = deriveFumlTestmodelTracemodelPath(modelNumber,
				domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
	}

	public EObject executeAnonExampleB(int version, boolean exists,
			boolean found, boolean acc, boolean domainSpecific) {
		String modelPath = deriveAnonExampleModelPath(version);
		String parameterDefinitionPath = deriveAnonExampleParameterDefinitionPath(
				version, exists, found, acc);
		String tracemodelPath = deriveAnonExampleTracemodelPath(version,
				exists, found, acc, domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
	}

	private EObject executeFumlModel(String modelPath,
			String parameterDefinitionPath, String tracemodelPath,
			boolean domainSpecific) {
		ModelExecutor executor = new ModelExecutor();
		return executor.execute(modelPath, parameterDefinitionPath,
				FUML_METMODEL_PATH, FUML_CONFIGURATION_PATH,
				getFumlTracemetamodelPath(domainSpecific), tracemodelPath,
				getFumlAdditionalModelInputPaths());
	}

}
