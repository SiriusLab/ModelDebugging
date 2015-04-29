package fr.inria.diverse.trace.plaink3.tracematerialextractor;

import org.eclipse.emf.ecore.EClass;

public class Plaink3MaterialStrings {

	public static final String fillEventSuffix =  "_FillEvent";
	
	public static final String globalFillEventName = "FillEvent";
	
	public static final String package_BigSteps = "BigSteps";
	
	public static final String ref_BigStepToSub = "subSteps";
	
	public static final String abstractSubStepSuffix = "_AbstractSubEvent"; 
	
	public static String fillEventClassName(EClass macroEventClass) {
		return macroEventClass.getName() + fillEventSuffix;
	}
	
}
