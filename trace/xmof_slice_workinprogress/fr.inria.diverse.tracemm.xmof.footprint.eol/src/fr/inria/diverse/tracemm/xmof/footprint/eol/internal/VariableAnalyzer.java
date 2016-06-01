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

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;

public class VariableAnalyzer extends DefaultASTAnalyzer {

	public VariableAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public AST getNameAST(AST ast) {
		return ast.getFirstChild();
	}

	@Override
	public AST getTypeAST(AST ast) {
		AST typeAST = null;
		if (ast.getChildCount() == 2) {
			typeAST = ast.getSecondChild();
		}
		return typeAST;
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		return new ArrayList<AST>();
	}

}
