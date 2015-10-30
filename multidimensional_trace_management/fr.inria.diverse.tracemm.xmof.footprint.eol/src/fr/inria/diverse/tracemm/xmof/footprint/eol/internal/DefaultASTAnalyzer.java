package fr.inria.diverse.tracemm.xmof.footprint.eol.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.parse.EolParser;

public class DefaultASTAnalyzer extends ASTAnalyzer {

	public DefaultASTAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public EModelElement getAccessedEModelElement(AST ast) {
		return null;
	}

	@Override
	public AST getNameAST(AST ast) {
		List<AST> childrenNameASTs = getChildrenASTsByType(ast, EolParser.NAME);
		return childrenNameASTs.size() > 0 ? childrenNameASTs.get(0) : null;
	}

	@Override
	public AST getTypeAST(AST ast) {
		List<AST> childrenTypeASTs = getChildrenASTsByType(ast, EolParser.TYPE);
		return childrenTypeASTs.size() > 0 ? childrenTypeASTs.get(0) : null;
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		return getChildrenASTsByType(ast, EolParser.FORMAL, EolParser.VAR);
	}

	protected List<AST> getChildrenASTsByType(AST ast, Integer... types) {
		List<Integer> typesAsList = Arrays.asList(types);

		List<AST> childrenASTsOfType = new ArrayList<AST>();
		for (AST childAST : ast.getChildren()) {
			int type = childAST.getType();
			if (typesAsList.contains(type))
				childrenASTsOfType.add(childAST);
		}
		return childrenASTsOfType;
	}

	@Override
	public AST getDefiningHelperMethod(AST ast) {
		AST methodAST = null;
		AST currentAST = ast;
		while (methodAST == null && currentAST.parent != null) {
			currentAST = currentAST.getParent();
			if (currentAST.getType() == EolParser.HELPERMETHOD) {
				methodAST = currentAST;
			}
		}
		return methodAST;
	}

}
