package fr.inria.diverse.trace.plugin.generator

import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.codegen.ecore.genmodel.presentation.GeneratorUIUtil.GeneratorOperation
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil
import org.eclipse.emf.common.command.BasicCommandStack
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.plugin.EcorePlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.provider.ComposedAdapterFactory
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors

class EMFProjectGenerator {

	// Inputs
	private val URI ecoreURI
	private val String projectName

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IProject project
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var List<GenPackage> referencedGenPackages
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	val Set<EPackage> rootPackages = new HashSet

	// Transient
	private var GenModel genModel

	new(String projectName, URI ecoreURI) {
		this.projectName = projectName
		this.ecoreURI = ecoreURI
	}

	private var Composite rootParent
	private var FakeEcoreImporterWizard f

	def void generateBaseEMFProject() {

		// Creating the project and retrieving its path
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName)
		val projectPath = project.getFullPath();

		// TODO This is horrible: the display is locked during the fake UI handling :D
		Display.getDefault().syncExec(
			[
			// We skip the previous wizard (emf project) and we directly configure the ecore import wizard appropriately
			rootParent = new Shell
			f = new FakeEcoreImporterWizard(rootParent)
			f.genModelProjectPath = projectPath
			// Setting up ecore URI and loading it
			f.fakeDetailPage.fakeSetURI(ecoreURI.toString)
			f.fakeDetailPage.fakeLoad
			// We choose to use all referenced gen models and ann (remaining) epackages
			f.fakePackagePage.checkAll(ecoreURI)
			// Here we do the actual projet generation
			f.performFinish
			// Storing the genmodel
			genModel = f.modelImporter.genModel
			referencedGenPackages = f.fakePackagePage.referencedGenPackages.immutableCopy
			rootPackages.addAll(f.fakePackagePage.checkedEPackages)
			// Finally we disband our fakes wizard and root
			f.dispose
			rootParent.dispose
		])

	}

	def void generateModelCode() {
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true, [ m |
			generateModelCode(m)
		])
	}

	/**
	 * Performs the code generation from the genmodel.
	 * 
	 * We try to precisely follow the actions made by the GenModelEditor when loading the model,
	 * since otherwise for some mysterious reason the generated code has errors regarding generic
	 * type parameters.
	 * 
	 * Note that this code can certainly by simplified, if time is taken to find useless lines. 
	 */
	def void generateModelCode(IProgressMonitor m) {

		// --------- Start code from org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.initializeEditingDomain()
		//
		// Create an adapter factory that yields item providers.
		val adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		// Create the command stack that will notify this editor as commands are executed.
		val BasicCommandStack commandStack = new BasicCommandStack();

		// Create the editing domain with a special command stack.
		val editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack) {
			override isReadOnly(Resource resource) {
				return super.isReadOnly(resource) || getResourceSet().getResources().indexOf(resource) != 0;
			}
		};

		editingDomain.getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
		//
		// --------- End code from org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.initializeEditingDomain()
		//
		//
		// --------- Start code from org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.createModel()
		//
		val URI resourceURI = genModel.eResource.URI
		var Exception exception = null;
		var Resource resource = null;
		try {
			// Load the resource through the editing domain.
			//
			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
		} catch (Exception e) {
			exception = e;
			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		}
		// --------- End code from org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.createModel()
		//
		//
		// --------- Start code from org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.initialize(GenModel)
		val GenModel genModel = resource.getContents().get(0) as GenModel;

		genModel.reconcile();
		genModel.setCanGenerate(true);
		val generator = GenModelUtil.createGenerator(genModel);

		// --------- End code org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor.initialize(GenModel)
		//
		//
		genModel.setValidateModel(true);
		val operation = new GeneratorOperation(null);
		operation.addGeneratorAndArguments(generator, genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
			"model project");
		operation.run(m)

	}

}
