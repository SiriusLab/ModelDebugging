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

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;

public abstract class ASTAnalyzer {

	protected EolLibraryModule module;
	protected ASTAnalyzerFactory factory;

	public ASTAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		this.module = module;
		this.factory = factory;
	}

	public abstract EModelElement getAccessedEModelElement(AST ast);

	public abstract AST getNameAST(AST ast);

	public abstract AST getTypeAST(AST ast);

	public abstract List<AST> getDeclaredVariableASTs(AST ast);

	public abstract AST getDefiningHelperMethod(AST ast);
}
