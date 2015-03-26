package fr.inria.diverse.trace.plugin.generator

import java.util.HashSet
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.codegen.ecore.generator.Generator
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.common.util.BasicMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * TODO generate code
 * TODO change the plugin information?
 */
class EMFProjectGenerator {

	// Inputs
	private val URI ecoreURI
	private val String projectName

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IProject project 

	// Transient
	private var GenModel genModel
	private val Set<EPackage> rootPackages = new HashSet

	new(String projectName, URI ecoreURI) {
		this.projectName = projectName
		this.ecoreURI = ecoreURI
	}

	def void generateBaseEMFProject() {
 
		// We skip the previous wizard (emf project) and we directly configure the ecore import wizard appropriately
		val Composite rootParent = new Shell
		val FakeEcoreImporterWizard f = new FakeEcoreImporterWizard(rootParent)

		// Creating the project and retrieving its path
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName)
		val projectPath = project.getFullPath();
		f.genModelProjectPath = projectPath

		// Setting up ecore URI and loading it
		f.fakeDetailPage.fakeSetURI(ecoreURI.toString)
		f.fakeDetailPage.fakeLoad

		// We choose to use all referenced gen models and ann (remaining) epackages
		f.fakePackagePage.checkAll

		// Here we do the actual projet generation
		f.performFinish

		// Storing the genmodel
		genModel = f.modelImporter.genModel
		rootPackages.addAll(f.fakePackagePage.checkedEPackages)

		// Finally we disband our fakes wizard and root
		f.dispose
		rootParent.dispose
	}

	def void generateModelCode() {

		// Setting up the genmodel
		genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
		genModel.initialize(rootPackages);
		genModel.reconcile();
		genModel.setCanGenerate(true);
		genModel.setValidateModel(true);

		// Generating the model code from the genmodel
		val Generator generator = new Generator();
		generator.setInput(genModel);
		
	
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true, [m|
			generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, "model project",BasicMonitor.toMonitor(m));
		]);
	}

}
