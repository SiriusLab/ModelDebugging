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
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
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

	def void generateModelCode(IProgressMonitor m) {

		// We get the file in the workspace
		val genModelFile = project.getFolder("model").getFile(genModel.eResource.URI.lastSegment)
		
		// We start a fake GenModelEditor, so that the genmodel gets correctly loaded
		val fakeEditor = new FakeGenModelEditor(genModelFile)
		Display.getDefault().syncExec(
			[
				val root = new Shell
				fakeEditor.createPartControl(root)
			]
		)
		
		// We retrive the loaded genmodel
		val Resource genModelResource = fakeEditor.getResource
		val GenModel genModel = genModelResource.contents.filter(GenModel).head
		
		// And we start the generation similarly to the code from the UI
		genModel.reconcile();
		genModel.setCanGenerate(true);
		genModel.setValidateModel(true);
		val generator = GenModelUtil.createGenerator(genModel);
		val operation = new GeneratorOperation(null);
		operation.addGeneratorAndArguments(generator, genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
			"model project");
		operation.run(m)
	}

}
