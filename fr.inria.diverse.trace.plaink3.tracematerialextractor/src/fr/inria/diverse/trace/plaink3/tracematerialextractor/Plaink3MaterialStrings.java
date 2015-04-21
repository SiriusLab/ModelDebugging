package fr.inria.diverse.trace.plaink3.tracematerialextractor;

import org.eclipse.emf.ecore.EClass;

public class Plaink3MaterialStrings {

	public static final String fillEventSuffix =  "_FillEvent";
	
	public static final String globalFillEventName = "FillEvent";
	
	public static String fillEventClassName(EClass macroEventClass) {
		return macroEventClass.getName() + fillEventSuffix;
	}
	
}
