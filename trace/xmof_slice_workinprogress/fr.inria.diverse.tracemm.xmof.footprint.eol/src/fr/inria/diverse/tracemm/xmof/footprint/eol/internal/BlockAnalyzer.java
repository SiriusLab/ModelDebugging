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

import java.util.List;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.parse.EolParser;

public class BlockAnalyzer extends DefaultASTAnalyzer {

	public BlockAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		List<AST> declaredVariableASTs = super.getDeclaredVariableASTs(ast);
		List<AST> operatorsASTs = getChildrenASTsByType(ast, EolParser.OPERATOR);
		for (AST operatorAST : operatorsASTs) {
			declaredVariableASTs.addAll(factory.getAnalyzer(operatorAST).getDeclaredVariableASTs(operatorAST));
		}
		return declaredVariableASTs;
	}
}
