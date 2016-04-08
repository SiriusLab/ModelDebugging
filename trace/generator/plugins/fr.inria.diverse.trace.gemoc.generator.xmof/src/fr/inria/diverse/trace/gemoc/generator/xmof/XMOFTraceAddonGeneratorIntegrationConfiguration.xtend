package fr.inria.diverse.trace.gemoc.generator.xmof

import fr.inria.diverse.tracemm.xmof2tracematerial.XmofExecutionExtensionExtractor
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import fr.inria.diverse.trace.gemoc.generator.TraceAddonGeneratorIntegrationConfiguration

/**
 * Plenty of ways to call the generator in an eclipse context
 */
class XMOFTraceAddonGeneratorIntegrationConfiguration implements TraceAddonGeneratorIntegrationConfiguration {

	override getExecutionExtension(Language melangeLanguage, String languageName, IProject melangeProject,
		Set<EPackage> abstractSyntax, ResourceSet rs) {

		val xmofStringURI = melangeLanguage.getXmof();
		val xmofURI = URI.createURI(xmofStringURI, true)
		val xmofModel = rs.createResource(xmofURI)
		xmofModel.load(null)
		EcoreUtil.resolveAll(rs)
		
		val rootPackages = xmofModel.contents.filter(EPackage).toSet
		
		for (p : xmofModel.allContents.filter(EPackage).toSet)
			EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
		
		val fakeURIGeneratedEcore = URI.createURI('''platform:/«xmofModel.URI.segment(0)»/«xmofModel.URI.segment(1)»/model-gen/«xmofModel.URI.lastSegment».ecore''')
		
		xmofModel.URI = fakeURIGeneratedEcore

		val XmofExecutionExtensionExtractor extractor = new XmofExecutionExtensionExtractor(abstractSyntax,
			rootPackages, false)
		extractor.computeMMExtension

		return extractor.mmextensionResult

	}
	
	override canWorkWith(Language melangeLanguage, IProject melangeProject) {
		return melangeLanguage.xmof != null && melangeLanguage.xmof != ""
	}

}