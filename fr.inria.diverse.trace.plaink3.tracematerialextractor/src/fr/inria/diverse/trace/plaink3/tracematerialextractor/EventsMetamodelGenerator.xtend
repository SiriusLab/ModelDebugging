package fr.inria.diverse.trace.plaink3.tracematerialextractor

import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom
import org.eclipse.xtext.xbase.impl.XMemberFeatureCallImplCustom
import org.eclipse.xtext.common.types.impl.JvmAnnotationTypeImpl
import org.eclipse.xtext.common.types.impl.JvmEnumerationTypeImplCustom

class EventsMetamodelGenerator {

	// Input
	private val IJavaProject javaProject
	private val EPackage extendedMetamodel

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var EPackage eventsMM

	new(IJavaProject p, String languageName, EPackage extendedMetamodel) {
		this.javaProject = p
		this.eventsMM = EcoreFactory.eINSTANCE.createEPackage
		val mmname = languageName + "Events"
		this.eventsMM.name = mmname
		this.eventsMM.nsURI = mmname //TODO
		this.eventsMM.nsPrefix = mmname //TODO
		this.extendedMetamodel = extendedMetamodel
	}

	public def void generate() {

		val loader = new XtendLoader(javaProject)
		loader.loadXtendModel

		for (f : loader.xtendModel) {
			generateEventsFromXtend(f, loader.aspectAnnotation, loader.transactionSupport)
		}

	}

	private def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	private def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name
		}
	}

	/**
	 * Very weak for now: xtend parsing not working very well.
	 */
	private def generateEventsFromXtend(XtendFile f, JvmAnnotationTypeImpl aspectAnnotation,
		JvmEnumerationTypeImplCustom transactionType) {

		val className = aspectAnnotation.members.findFirst[m|m.simpleName.equals("className")]
		val transactionSupport = aspectAnnotation.members.findFirst[m|m.simpleName.equals("transactionSupport")]
		val transactionEMF = transactionType.literals.findFirst[l|l.simpleName.equals("EMF")]

		// For each declared class
		for (type : f.xtendTypes) {

			// For each aspect annotation of the class 
			for (a : type.annotations.filter[a1|a1 != null && a1.annotationType == aspectAnnotation]) {

				// If the class has transaction support
				if (a.elementValuePairs.exists[p|
					p.element == transactionSupport &&
						(p.value as XMemberFeatureCallImplCustom).feature == transactionEMF]) {

					// We find the ecore class matching the aspected java class 
					val String aspectedClassName = (a.elementValuePairs.findFirst[p|p.element == className].value as XFeatureCallImplCustom).
						feature.simpleName
					val EClass aspectedClass = extendedMetamodel.eAllContents.filter(EClass).findFirst[c|
						aspectedClassName.equals(c.name)]
					val String prefix = getFQN(aspectedClass, "_").toFirstUpper + "_"

					// We go through all its operations
					for (m : type.members.filter(XtendFunction)) {

						// For each operation, we create an event class
						val EClass eventClass = EcoreFactory.eINSTANCE.createEClass
						this.eventsMM.EClassifiers.add(eventClass)
						eventClass.name = prefix + m.name.toFirstUpper

						// With a single "this" parameter
						EcoreCraftingUtil.addReferenceToClass(eventClass, "this", aspectedClass)

					// TODO handle all kinds of parameters: args and return
					}

				}
			}
		}

	}

}
