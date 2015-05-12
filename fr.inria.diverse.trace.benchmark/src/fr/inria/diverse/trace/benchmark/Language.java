package fr.inria.diverse.trace.benchmark;

public enum Language {
	TFSM("tfsmextendedTrace Addon",
			"org.gemoc.sample.tfsm.plaink3.dsa.Main",
			"tfsmplainK3","tfsmextendedTrace.impl.TraceImpl"),
	AD("activitydiagramTrace Addon",
			"org.modelexecution.operationalsemantics.gemoc.sequential.dynamic.Main",
			"xad_sequential","activitydiagramTrace.impl.TraceImpl");
	
	
	
	
	private Language(String traceAddonName,String entryPoint, String languageName, String rootClassName) {
		this.traceAddonName = traceAddonName;
		this.entryPoint = entryPoint;
		this.languageName = languageName;
		this.rootClassName = rootClassName;
	}
	
	
	public final String traceAddonName; 
	public final String entryPoint;
	public final String languageName;
	public final String rootClassName;
	
	
}
