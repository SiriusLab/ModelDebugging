package fr.inria.diverse.tracemm.xmof.footprint.eol.test.internal;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.ecl.parse.EclParser;
import org.eclipse.epsilon.eol.parse.EolParser;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation;

public abstract class EpsilonUtil {

	public static void print(AST ast) {
		print(ast, "");
	}

	private static void print(AST ast, String indent) {
		System.out.println(indent + ast + " (" + getTypeText(ast.getType()) + ") " + getLocationText(ast));
		for (AST children : ast.getChildren()) {
			print(children, indent + "\t");
		}
	}

	private static String getTypeText(int type) {
		String typeText = "?? " + type + " ??";
		switch (type) {
		case EolParser.OPERATOR:
			typeText = "operator";
			break;
		case EolParser.INT:
			typeText = "int";
			break;
		case EolParser.STRING:
			typeText = "string";
			break;
		case EolParser.ASSIGNMENT:
			typeText = "assignment";
			break;
		case EolParser.SPECIAL_ASSIGNMENT:
			typeText = "special assignment";
			break;
		case EolParser.POINT:
			typeText = "point";
			break;
		case EolParser.NAME:
			typeText = "name";
			break;
		case EolParser.FEATURECALL:
			typeText = "feature call";
			break;
		case EolParser.BLOCK:
			typeText = "block";
			break;
		case EolParser.IF:
			typeText = "if";
			break;
		case EolParser.SWITCH:
			typeText = "switch";
			break;
		case EolParser.BOOLEAN:
			typeText = "boolean";
			break;
		case EolParser.ARROW:
			typeText = "arrow";
			break;
		case EolParser.VAR:
			typeText = "var";
			break;
		case EolParser.COLLECTION:
			typeText = "collection";
			break;
		case EolParser.MAP:
			typeText = "map";
			break;
		case EolParser.FLOAT:
			typeText = "float";
			break;
		case EolParser.FOR:
			typeText = "for";
			break;
		case EolParser.PARAMLIST:
			typeText = "paramlist";
			break;
		case EolParser.EOLMODULE:
			typeText = "eolmodule";
			break;
		case EolParser.PARAMETERS:
			typeText = "parameters";
			break;
		case EolParser.TYPE:
			typeText = "type";
			break;
		case EolParser.RETURN:
			typeText = "return";
			break;
		case EolParser.ITEMSELECTOR:
			typeText = "itemselector";
			break;
		case EolParser.BREAK:
			typeText = "break";
			break;
		case EolParser.BREAKALL:
			typeText = "break all";
			break;
		case EolParser.ENUMERATION_VALUE:
			typeText = "enumeration value";
			break;
		case EolParser.CONTINUE:
			typeText = "continue";
			break;
		case EolParser.WHILE:
			typeText = "while";
			break;
		case EolParser.THROW:
			typeText = "throw";
			break;
		case EolParser.DELETE:
			typeText = "delete";
			break;
		case EolParser.NEW:
			typeText = "new";
			break;
		case EolParser.TRANSACTION:
			typeText = "transaction";
			break;
		case EolParser.ABORT:
			typeText = "abort";
			break;
		case EclParser.ECLMODULE:
			typeText = "ecl module";
			break;
		case EclParser.MATCH:
			typeText = "match";
			break;
		case EclParser.FORMAL:
			typeText = "formal";
			break;
		case EclParser.COMPARE:
			typeText = "compare";
			break;
		case EclParser.HELPERMETHOD:
			typeText = "helper method";
			break;
		case EclParser.ANNOTATIONBLOCK:
			typeText = "annotation block";
			break;
		case EclParser.Annotation:
			typeText = "annotation";
			break;
		}
		return typeText;
	}

	private static String getLocationText(AST ast) {
		return ast.getLine() + ":" + ast.getColumn() + " " + ast.getTokenStartIndex() + "-" + ast.getTokenStopIndex();
	}

	public static final void print(Footprint footprint) {
		for (EModelElementAccess eModelElementAccess : footprint.getEModelElementAccesses()) {
			System.out.println(eModelElementAccess.getEModelElement() + ":");
			for (Location l : eModelElementAccess.getAccessLocations()) {
				EOLLocation location = (EOLLocation) l;
				System.out.println("\t" + location.getLine() + ":" + location.getColumn() + " "
						+ location.getTokenStartIndex() + "-" + location.getTokenStopIndex());
			}
		}
	}
}
