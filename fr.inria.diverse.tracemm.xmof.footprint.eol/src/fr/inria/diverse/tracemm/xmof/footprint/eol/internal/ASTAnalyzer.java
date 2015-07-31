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
