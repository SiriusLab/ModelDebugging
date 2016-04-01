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
import java.util.stream.Collectors;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.parse.EolParser;

public class HelperMethodAnalyzer extends DefaultASTAnalyzer {

	public HelperMethodAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		List<AST> parameterlistASTs = getChildrenASTsByType(ast, EolParser.PARAMLIST);
		List<AST> parameterASTs = parameterlistASTs.stream()
				.flatMap(p -> factory.getAnalyzer(p).getDeclaredVariableASTs(p).stream()).collect(Collectors.toList());
		return parameterASTs;
	}

}
