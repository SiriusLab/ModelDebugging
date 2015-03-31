package fr.inria.diverse.trace.plaink3.tracematerialextractor

import fr.inria.diverse.trace.commons.EclipseUtil
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.ecore.EClass
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtext.xbase.impl.XMemberFeatureCallImplCustom
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom
import org.eclipse.xtend.lib.annotations.Accessors

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

	public def generate() {

		for (IFolder scrFolder : EclipseUtil.findSrcFoldersOf(javaProject)) {
			for (f : EclipseUtil.findAllFilesOf(scrFolder)) { //.filter(IFile).filter[f|f.name.endsWith(".xtend")]) {
				if (f instanceof IFile) {
					if (f.name.endsWith(".xtend")) {
						val XtendFile x = XtendLoader.load(f);
						generateEventsFromXtend(x)
						var XtextResourceSet r
					}
				}

			}
		}
	}

	private def generateEventsFromXtend(XtendFile f) {

		// For each declared class
		for (type : f.xtendTypes) {

			// For each annotation of the class 
			for (a : type.annotations) {

				// We gather the values of the key/value pairs of the properties of the aspect
				val values = a.elementValuePairs.map[q|q.value]

				// If the class has transaction support
				if (values.filter(XMemberFeatureCallImplCustom).exists[q|
					q.memberCallTarget.toString.equals("TransactionSupport")]) {

					// We go through all its operations
					for (m : type.members.filter(XtendFunction)) {

						// For each operation, we create an event class
						val EClass eventClass = EcoreFactory.eINSTANCE.createEClass
						this.eventsMM.EClassifiers.add(eventClass)
						eventClass.name = m.name.toFirstUpper + "Event"

						// We find the ecore class matching the aspected java class 
						val String aspectedClassName = values.filter(XFeatureCallImplCustom).get(0).toString
						val EClass aspectedClass = extendedMetamodel.eAllContents.filter(EClass).findFirst[c|
							c.name.equals(aspectedClassName)]

						// With a single "this" parameter
						EcoreCraftingUtil.addReferenceToClass(eventClass, "thisParam", aspectedClass)

					// TODO handle all kinds of parameters: args and return
					}

				}
			}
		}

	}

}
