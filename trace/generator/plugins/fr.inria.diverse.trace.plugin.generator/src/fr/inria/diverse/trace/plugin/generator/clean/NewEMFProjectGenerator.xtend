package fr.inria.diverse.trace.plugin.generator.clean

import fr.inria.diverse.trace.plugin.generator.AbstractEMFProjectGenerator
import java.io.IOException
import java.util.Collection
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import java.util.jar.Manifest
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.generator.Generator
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil
import org.eclipse.emf.codegen.util.CodeGenUtil
import org.eclipse.emf.common.util.BasicMonitor
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.common.util.UniqueEList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EParameter
import org.eclipse.emf.ecore.EStructuralFeature.Setting
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.plugin.EcorePlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.EcoreUtil.Copier

public class NewEMFProjectGenerator extends AbstractEMFProjectGenerator {

	public static val String MODEL_GEN_FOLDER = "model"

	protected IProgressMonitor progressMonitor

	protected ResourceSet resourceSet

	protected URI modelGenFolderURI
	protected String srcFolderPathString

	protected Resource ecoreModelResource

	new(String projectName, URI ecoreURI) {
		super(projectName, ecoreURI)
	}

	override generateBaseEMFProject(IProgressMonitor m) {

		this.progressMonitor = m;
		this.resourceSet = new ResourceSetImpl

		// Create new EMF project
		this.project = PluginProjectHelper.createPluginProject(projectName, // Project name
		#["src"], // Src folders
		#[], // referenced projects
		#{}, // required bundles (plugins) TODO
		#[], // exported packages TODO
		m)

		// setup model-gen folder for saving temporary models
		this.modelGenFolderURI = setupModelGenFolder();

		// setup src folder for model code generation
		this.srcFolderPathString = setupSrcFolder();

		// TODO copy trace ecore in folder
		// TODO load copied trace ecore
		this.ecoreModelResource = null

		// ???
		checkReferencedPackages(ecoreModelResource);

		this.genModel = generateGenModel((ecoreModelResource.getContents().get(0)) as EPackage, modelGenFolderURI);
	}

	override generateModelCode(IProgressMonitor m) {
		generateCode(progressMonitor);
	}

	private def URI setupModelGenFolder() {
		var URI modelGenFolderURI = null;
		val IFolder modelGenFolder = project.getFolder(MODEL_GEN_FOLDER);
		if (!modelGenFolder.exists()) {
			try {
				modelGenFolder.create(true, true, null);
			} catch (CoreException e) {
				throw new RuntimeException('''The folder '«MODEL_GEN_FOLDER»' could not be created.''', e);
			}
		}
		modelGenFolderURI = URI.createPlatformResourceURI(modelGenFolder.getFullPath().toString(), true);
		return modelGenFolderURI;
	}

	private def String setupSrcFolder() {
		var String srcFolderPathString = null;
		val IFolder srcFolder = project.getFolder("src");
		if (!srcFolder.exists()) {
			try {
				srcFolder.create(true, true, null);
			} catch (CoreException e) {
				throw new RuntimeException("The source folder \'src\' could not be created.", e);
			}
		}
		srcFolderPathString = srcFolder.getFullPath().toString();
		return srcFolderPathString;
	}

	protected def EPackage generateEcoreModel(Resource xmofModelResource) {
		val Resource ecoreModelResource = copyXMOFModel(xmofModelResource);
		val EPackage rootEPackage = ecoreModelResource.getContents().get(0) as EPackage;
		// TODO important to remove these behaviors? but we are doing non-xmod-specific code....
		// removeBehaviors(rootEPackage);
		save(ecoreModelResource);
		return rootEPackage;
	}

	private def Resource copyXMOFModel(Resource xmofModelResource) {
		val String ecoreModelFileName = xmofModelResource.getURI().trimFileExtension().appendFileExtension("ecore").
			lastSegment().toString();
		val URI ecoreModelURI = this.modelGenFolderURI.appendSegment(ecoreModelFileName);
		val Resource ecoreModelResource = resourceSet.createResource(ecoreModelURI);

		val Copier copier = new Copier();
		ecoreModelResource.getContents().addAll((copier.copyAll(xmofModelResource.getContents())));
		copier.copyReferences();
		return ecoreModelResource;
	}

//	private def void removeBehaviors(EPackage ePackage) {
//		for (TreeIterator < EObject > eAllContents = ePackage.eAllContents(); eAllContents.hasNext();) {
//			var EObject eObject = eAllContents.next();
//			if (eObject instanceof BehavioredEClass) {
//				val BehavioredEClass behavioredEClass = (BehavioredEClass)
//				eObject;
//				behavioredEClass.getOwnedBehavior().clear();
//			} else if (eObject instanceof BehavioredEOperation) {
//				val BehavioredEOperation behavioredEOperation = (BehavioredEOperation)
//				eObject;
//				behavioredEOperation.getMethod().clear();
//				behavioredEOperation.getEContainingClass().getEOperations().remove(behavioredEOperation);
//			} else if (eObject instanceof CallBehaviorAction) {
//				val CallBehaviorAction callBehaviorAction = (CallBehaviorAction)
//				eObject;
//				callBehaviorAction.setBehavior(null);
//			} else if (eObject instanceof DecisionNode) {
//				val DecisionNode decisionNode = (DecisionNode)
//				eObject;
//				decisionNode.setDecisionInput(null);
//			} else if (eObject instanceof ReduceAction) {
//				val ReduceAction reduceAction = (ReduceAction)
//				eObject;
//				reduceAction.setReducer(null);
//			}
//		}
//	}
	private def void checkReferencedPackages(Resource xmofModelResource) {
		val Set<URI> missingPackages = new HashSet<URI>();
		val Map<EObject, Collection<Setting>> externalCrossReferences = EcoreUtil.ExternalCrossReferencer.find(
			xmofModelResource);
		for (EObject eObject : externalCrossReferences.keySet()) {
			if (eObject.eIsProxy()) {
				missingPackages.add(EcoreUtil.getURI(eObject).trimFragment());
			}
		}

		if (missingPackages.size() > 0) {

			val String message = "Unable to load the following referenced resource" +
				(if(missingPackages.size() == 1) "" else "s") + ": " + missingPackages.toString()

			throw new RuntimeException(message);
		}
	}

	protected def GenModel generateGenModel(EPackage rootEPackage, URI modelGenFolderURI) {
		val Resource genModelResource = createGenModel(rootEPackage);
		val GenModel genModel = genModelResource.getContents().get(0) as GenModel;
		setInitializeByLoad(genModel);
		save(genModelResource);
		return genModel;
	}

	protected def Resource createGenModel(EPackage rootEPackage) {
		val Resource ecoreModelResource = rootEPackage.eResource();
		val String genModelFileName = ecoreModelResource.getURI().trimFileExtension().appendFileExtension("genmodel").
			lastSegment().toString();
		val URI genModelURI = this.modelGenFolderURI.appendSegment(genModelFileName);

		val Resource genModelResource = resourceSet.createResource(genModelURI);
		val GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModelResource.getContents().add(genModel);

		val IFolder srcFolder = project.getFolder("src");
		genModel.setModelDirectory(srcFolder.getFullPath().toString());
		genModel.getForeignModel().add(ecoreModelResource.getURI().toString());
		genModel.setModelName(getModelName(genModelURI));
		genModel.setModelPluginID(getPluginID(genModelURI));

		// genModel.setOperationReflection(true);
		// genModel.setMinimalReflectiveMethods(true);
		genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
		genModel.setComplianceLevel(getComplicanceLevel());
		genModel.setImportOrganizing(true);

		genModel.initialize(Collections.singleton(rootEPackage));
		setMissingParameterTypes(genModel);

		val List<GenPackage> missingGenPackages = computeMissingGenPackages(genModel);
		genModel.getUsedGenPackages().addAll(missingGenPackages);

		return genModelResource;
	}

	protected def String getModelName(URI genModelURI) {
		val String genModelFileName = genModelURI.trimFileExtension().lastSegment();
		val String modelName = genModelFileName.substring(0, 1).toUpperCase() + genModelFileName.substring(1);
		return modelName;
	}

	protected def String getPluginID(URI uri) {
		var String pluginID = "";
		val IFile manifestFile = project.getFolder("META-INF").getFile("MANIFEST.MF");
		try {
			val Manifest manifest = new Manifest(manifestFile.getContents());
			var String symbolicName = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
			if (symbolicName != null) {
				val int index = symbolicName.indexOf(";");
				if (index > 0) {
					symbolicName = symbolicName.substring(0, index);
				}
				pluginID = symbolicName.trim();
			}
		} catch (Exception e) {
			throw new RuntimeException(
				"Could not find manifest file \'" + manifestFile.getFullPath().toString() + "\'.", e);
		}
		return pluginID;
	}

	private def GenJDKLevel getComplicanceLevel() {
		val String complianceLevel = CodeGenUtil.EclipseUtil.getJavaComplianceLevel(project);
		if ("1.4".equals(complianceLevel)) {
			return GenJDKLevel.JDK14_LITERAL;
		} else if ("1.5".equals(complianceLevel)) {
			return GenJDKLevel.JDK50_LITERAL;
		} else if ("1.6".equals(complianceLevel)) {
			return GenJDKLevel.JDK60_LITERAL;
		} else if ("1.7".equals(complianceLevel)) {
			return GenJDKLevel.JDK70_LITERAL;
		} else {
			return GenJDKLevel.JDK80_LITERAL;
		}
	}

	/**
	 * In case of missing parameter types, the types are temporarily set to
	 * EObject
	 * 
	 * @param genModel
	 */
	private def void setMissingParameterTypes(GenModel genModel) {
		for (genModelElement : genModel.eAllContents().toSet) {
			if (genModelElement instanceof GenParameter) {
				val GenParameter genParameter = genModelElement as GenParameter;
				val EParameter ecoreParameter = genParameter.getEcoreParameter();
				if (ecoreParameter.getEType() == null) {
					ecoreParameter.setEType(EcorePackage.eINSTANCE.getEObject());
				}
			}
		}
	}

	protected def List<GenPackage> computeMissingGenPackages(GenModel genModel) {
		val List<GenPackage> missingGenPackages = new UniqueEList<GenPackage>();
		val Map<String, URI> genModelLocationMapTargetEnvironment = EcorePlugin.
			getEPackageNsURIToGenModelLocationMap(true);
		val Map<String, URI> genModelLocationMapEnvironment = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
		for (EPackage ePackage : genModel.getMissingPackages()) {
			if (ePackage != null) { // happens for activities
				var URI missingGenModelURI = genModelLocationMapEnvironment.get(ePackage.getNsURI());
				if (missingGenModelURI == null) {
					missingGenModelURI = genModelLocationMapTargetEnvironment.get(ePackage.getNsURI());
				}
				if (missingGenModelURI == null) {
					throw new RuntimeException(
						"Unable to load generator model of required package \'" + ePackage.getNsURI() + "\'.");
				}
				var Resource missingGenModelResource = null;
				try {
					missingGenModelResource = resourceSet.getResource(missingGenModelURI, true);
				} catch (RuntimeException e) {
					throw new RuntimeException(
						"Unable to load generator model of required package \'" + ePackage.getNsURI() + "\'.");
				}
				val GenModel missingGenModel = missingGenModelResource.getContents().get(0) as GenModel;
				missingGenPackages.addAll(missingGenModel.getGenPackages());
			}
		}
		return missingGenPackages;
	}

	protected def void setInitializeByLoad(GenModel genModel) {
		for (GenPackage genPackage : genModel.getGenPackages()) {
			setInitializeByLoad(genPackage);
		}
	}

	private def void setInitializeByLoad(GenPackage genPackage) {
		genPackage.setLoadInitialization(true);
		for (GenPackage subGenPackage : genPackage.getSubGenPackages()) {
			setInitializeByLoad(subGenPackage);
		}
	}

	protected def boolean generateCode(IProgressMonitor progressMonitor) {
		var boolean success = false;
		prepareGenModelForCodeGeneration(genModel);
		val Generator generator = GenModelUtil.createGenerator(genModel);
		val boolean canGenerate = generator.canGenerate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE);
		if (canGenerate) {
			val Diagnostic diagnostic = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
				BasicMonitor.toMonitor(progressMonitor));
			if (diagnostic.getSeverity() == Diagnostic.OK) {
				success = true;
			}
		}
		return success;
	}

	protected def void prepareGenModelForCodeGeneration(GenModel genModel) {
		genModel.reconcile();
		genModel.setCanGenerate(true);
	}

	private def void save(Resource resource) {
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException("Could not save resource \'" + resource.getURI() + "\'.", e);
		}
	}

}
