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
