package fr.inria.diverse.tracemm.xmof2tracematerial

import java.util.Set
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import ecorext.Ecorext

class XmofTraceMaterialExtractor {
	protected val Resource xmofModel
	protected boolean done = false
	protected Copier copier

	protected XmofStepMetamodelGenerator stepGenerator
	protected XmofExecutionExtensionGenerator exeExtGenerator
	protected Set<EPackage> ecore

	new(Set<EPackage> ecore, Resource xmofModel) {
		this.xmofModel = xmofModel
		this.ecore = ecore
	}

	new(Resource ecoreModel, Resource xmofModel) {
		this(ecoreModel.contents.filter(EPackage).toSet, xmofModel)
	}

	public def void computeAllMaterial() {
		if(!done) {
			copier = new Copier
			exeExtGenerator = new XmofExecutionExtensionGenerator(ecore, xmofModel, copier)
			stepGenerator = new XmofStepMetamodelGenerator(ecore, xmofModel, copier)
			exeExtGenerator.computeMMExtension
			stepGenerator.computeStepMM
			copier.copyReferences
		} else {
			println("ERROR: already computed.")
		}
	}

	public def EPackage getStepMMResult() {
		return stepGenerator.stepmmResult
	}

	public def Ecorext getExeExtResult() {
		return exeExtGenerator.mmextensionResult
	}
}
