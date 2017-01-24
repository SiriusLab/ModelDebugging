/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import org.eclipse.core.resources.IProject
import java.util.Set
import org.eclipse.emf.ecore.EPackage
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.emf.ecore.resource.ResourceSet

interface TraceAddonGeneratorIntegrationConfiguration {
	
	def void compute(Language melangeLanguage, String languageName, IProject melangeProject,
		Set<EPackage> abstractSyntax, ResourceSet rs)

	def Ecorext getExecutionExtension();

	def boolean canWorkWith(Language melangeLanguage, IProject melangeProject)
	
	def Set<EPackage> getExecutionMetamodel()

}