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
