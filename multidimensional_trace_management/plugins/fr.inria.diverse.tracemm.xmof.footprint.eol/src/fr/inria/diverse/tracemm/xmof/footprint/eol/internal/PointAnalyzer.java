package fr.inria.diverse.tracemm.xmof.footprint.eol.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.parse.EolParser;

public class PointAnalyzer extends DefaultASTAnalyzer {

	private static final String SELF_VARIABLE_NAME = "self";

	public PointAnalyzer(EolLibraryModule module, ASTAnalyzerFactory factory) {
		super(module, factory);
	}

	@Override
	public EModelElement getAccessedEModelElement(AST ast) {
		EModelElement accessedEModelElement = null;
		AST objectAST = ast.getFirstChild();
		AST featureAST = ast.getSecondChild();
		String featureName = featureAST.getText();

		if (isOperationCall(featureAST) || isBuiltInOperationCall(featureAST)) {
			// operation call > do nothing
		} else {
			// structural feature access
			EClass objectType = getObjectType(objectAST);
			accessedEModelElement = objectType.getEStructuralFeature(featureName);
		}
		return accessedEModelElement;
	}

	private boolean isOperationCall(AST ast) {
		return ast.getChildCount() == 1 && ast.getFirstChild().getType() == EolParser.PARAMETERS;
	}

	private boolean isBuiltInOperationCall(AST ast) {
		return ast.getChildCount() >= 1 && ast.getFirstChild().getType() == EolParser.PARAMLIST;
	}

	private EClass getObjectType(AST ast) {
		EClass objectType = null;
		if (ast.getType() == EolParser.FEATURECALL) {
			// variable
			String objectName = ast.getText();
			AST variableAST = findVariable(objectName, ast);
			AST typeAST = factory.getAnalyzer(variableAST).getTypeAST(variableAST);
			objectType = getEClassForType(typeAST);
		} else if (ast.getType() == EolParser.POINT) {
			// nested feature calls
			AST objectAST = ast.getFirstChild();
			AST featureAST = ast.getSecondChild();
			EClass objectASTType = getObjectType(objectAST);
			objectType = getFeatureType(objectASTType, featureAST);
		}
		return objectType;
	}

	private EClass getFeatureType(EClass objectType, AST featureAST) {
		EClass featureType = null;

		if (isOperationCall(featureAST)) {
			// operation call
			String operationName = featureAST.getText();
			AST operationAST = findOperation(operationName, featureAST);
			if (operationAST != null) {
				AST operationTypeAST = factory.getAnalyzer(operationAST).getTypeAST(operationAST);
				if (operationTypeAST != null) {
					featureType = getEClassForType(operationTypeAST);
				}
			}
		} else {
			// structural feature access
			String featureName = featureAST.getText();
			EStructuralFeature eStructuralFeature = objectType.getEStructuralFeature(featureName);
			EClassifier eType = eStructuralFeature.getEType();
			if (eType instanceof EClass) {
				featureType = (EClass) eType;
			}
		}

		return featureType;
	}

	private EClass getEClassForType(AST typeAST) {
		EClass eClassForType = null;
		EModelElement accessedEModelElement = factory.getAnalyzer(typeAST).getAccessedEModelElement(typeAST);
		eClassForType = accessedEModelElement instanceof EClass ? (EClass) accessedEModelElement : null;
		return eClassForType;
	}

	private AST findOperation(String operationName, AST operationCallAST) {
		AST definingHelperMethod = factory.getAnalyzer(operationCallAST).getDefiningHelperMethod(operationCallAST);
		return definingHelperMethod.getParent().getChildren().stream()
				.filter(a -> a.getType() == EolParser.HELPERMETHOD)
				.filter(a -> factory.getAnalyzer(a).getNameAST(a).getText().equals(operationName)).findFirst()
				.orElse(null);
	}

	private AST findVariable(String variableName, AST context) {
		return isSelfVariable(variableName) ? findSelfVariable(context)
				: findVariableInParentASTs(variableName, context);
	}

	private AST findVariableInParentASTs(String variableName, AST context) {
		AST variableAST = null;
		AST currentAST = context;
		while (variableAST == null && currentAST.getParent() != null) {
			currentAST = currentAST.getParent();
			List<AST> declaredVariableASTs = factory.getAnalyzer(currentAST).getDeclaredVariableASTs(currentAST);
			variableAST = declaredVariableASTs.stream().filter(v -> variableName.equals(v.getFirstChild().getText()))
					.findFirst().orElse(null);
		}
		return variableAST;
	}

	private AST findSelfVariable(AST context) {
		AST methodAST = factory.getAnalyzer(context).getDefiningHelperMethod(context);
		return methodAST.getFirstChild();
	}

	private boolean isSelfVariable(String variableName) {
		return variableName.equals(SELF_VARIABLE_NAME);
	}

	@Override
	public AST getNameAST(AST ast) {
		return null;
	}

	@Override
	public AST getTypeAST(AST ast) {
		return null;
	}

	@Override
	public List<AST> getDeclaredVariableASTs(AST ast) {
		return new ArrayList<AST>();
	}

}
