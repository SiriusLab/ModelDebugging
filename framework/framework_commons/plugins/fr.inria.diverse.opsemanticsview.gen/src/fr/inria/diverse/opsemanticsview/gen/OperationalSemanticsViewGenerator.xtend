package fr.inria.diverse.opsemanticsview.gen

import opsemanticsview.OperationalSemanticsView
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.core.resources.IProject

interface OperationalSemanticsViewGenerator {

	public def boolean canHandle(Language language, IProject melangeProject)

	public def OperationalSemanticsView generate(Language language, IProject melangeProject)

}
