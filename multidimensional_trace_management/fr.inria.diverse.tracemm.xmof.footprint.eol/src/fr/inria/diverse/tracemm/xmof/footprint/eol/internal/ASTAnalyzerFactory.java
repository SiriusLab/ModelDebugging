package fr.inria.diverse.tracemm.xmof.footprint.eol.internal;

import java.util.HashMap;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.parse.EolParser;

public class ASTAnalyzerFactory {

	private static final int DEFAULT_TYPE = -1;
	protected HashMap<Integer, ASTAnalyzer> analyzers = new HashMap<Integer, ASTAnalyzer>();

	public ASTAnalyzerFactory(EolLibraryModule module) {
		createAnalyzers(module);
	}

	protected void createAnalyzers(EolLibraryModule module) {
		analyzers.put(EolParser.TYPE, new TypeAnalyzer(module, this));
		analyzers.put(EolParser.POINT, new PointAnalyzer(module, this));
		analyzers.put(EolParser.VAR, new VariableAnalyzer(module, this));
		analyzers.put(EolParser.FORMAL, new VariableAnalyzer(module, this));
		analyzers.put(EolParser.BLOCK, new BlockAnalyzer(module, this));
		analyzers.put(EolParser.OPERATOR, new OperatorAnalyzer(module, this));
		analyzers.put(EolParser.HELPERMETHOD, new HelperMethodAnalyzer(module, this));
		analyzers.put(DEFAULT_TYPE, new DefaultASTAnalyzer(module, this));
	}

	public ASTAnalyzer getAnalyzer(AST ast) {
		int type = ast.getType();
		return analyzers.containsKey(type) ? analyzers.get(type) : analyzers.get(DEFAULT_TYPE);
	}

}
