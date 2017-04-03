package fr.inria.diverse.opsemanticsview.gen.xmof

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.opsemanticsview.gen.OperationalSemanticsViewGenerator
import opsemanticsview.OpsemanticsviewFactory
import org.eclipse.core.resources.IProject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil

class XMOFOperationalSemanticsViewGenerator implements OperationalSemanticsViewGenerator {

	override canHandle(Language melangeLanguage, IProject melangeProject) {
		return melangeLanguage.xmof != null && melangeLanguage.xmof != ""
	}

	override generate(Language melangeLanguage,  IProject melangeProject) {
		
		val result = OpsemanticsviewFactory.eINSTANCE.createOperationalSemanticsView

		val rs = new ResourceSetImpl

		val asURI = URI.createURI(melangeLanguage.syntax.ecoreUri,true);
		val asResource= rs.getResource(asURI,true)
		val abstractSyntax = asResource.contents.filter(EPackage).head
		
		// Register all packages in registry
		// TODO remove them afterwards?
		for (p : asResource.allContents.filter(EPackage).toSet)
			EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
		
		val xmofStringURI = melangeLanguage.getXmof();
		val xmofURI = URI.createURI(xmofStringURI, true)
		val xmofModel = rs.getResource(xmofURI,true)
		val executionMetamodel = xmofModel.contents.filter(EPackage).head
		
		// Register all packages in registry
		// TODO remove them afterwards?
		for (p : xmofModel.allContents.filter(EPackage).toSet)
			EPackage.Registry.INSTANCE.put(p.getNsURI(), p);

		EcoreUtil.resolveAll(rs)

		result.abstractSyntax = abstractSyntax
		result.executionMetamodel = executionMetamodel
		
		
		val XMOFAnalyzer eventsgen = new XMOFAnalyzer(abstractSyntax, executionMetamodel, result);
		eventsgen.generate();
		

		return result
	}

}