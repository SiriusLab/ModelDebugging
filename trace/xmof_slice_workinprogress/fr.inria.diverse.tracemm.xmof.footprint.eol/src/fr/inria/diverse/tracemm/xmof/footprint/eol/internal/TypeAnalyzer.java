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
package fr.inria.diverse.tracemm.xmof.footprint.eol.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.types.EolModelElementType;

public class TypeAnalyzer extends DefaultASTAnalyzer {

	public TypeAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public EModelElement getAccessedEModelElement(AST ast) {
		EClass eClassForType = null;
		String typeName = ast.getText();
		try {
			EolModelElementType eolModelElementType = EolModelElementType.forName(typeName, module.getContext());
			IModel model = eolModelElementType.getModel();
			if (model instanceof AbstractEmfModel) {
				eClassForType = ((AbstractEmfModel) model).classForName(eolModelElementType.getTypeName());
			}
		} catch (EolModelNotFoundException ex) {
			// ignore
		} catch (EolModelElementTypeNotFoundException mex) {
			// ignore
		}
		return eClassForType;
	}

	@Override
	public AST getNameAST(AST ast) {
		return null;
	}

	@Override
	public AST getTypeAST(AST ast) {
		return ast;
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		return new ArrayList<AST>();
	}

}
