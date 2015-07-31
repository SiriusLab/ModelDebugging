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
