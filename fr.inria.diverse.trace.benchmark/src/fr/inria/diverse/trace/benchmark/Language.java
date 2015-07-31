package fr.inria.diverse.trace.benchmark;

public enum Language {
	TFSM("tfsmextendedTrace Addon",
			"org.gemoc.sample.tfsm.plaink3.dsa.Main",
			"tfsmplainK3","tfsmextendedTrace", "XXXXXXX", "XXXXXX"),
	AD("activitydiagramTrace Addon",
			"org.modelexecution.operationalsemantics.gemoc.sequential.dynamic.Main",
			"xad_sequential","activitydiagramTrace", "activitydiagram.impl.ActivityImpl", "activitydiagramTrace.impl.TraceImpl");
	
	
	
	
	private Language(String traceAddonName,String entryPoint, String languageName, String javaTracePackageName, String languageRootClassName, String javaTraceRootName) {
		this.traceAddonName = traceAddonName;
		this.entryPoint = entryPoint;
		this.languageName = languageName;
		this.javaTracePackageName = javaTracePackageName;
		this.languageRootClassName = languageRootClassName;
		this.javaTraceRootName = javaTraceRootName;
	}
	
	
	public final String traceAddonName; 
	public final String entryPoint;
	public final String languageName;
	public final String javaTracePackageName;
	public final String languageRootClassName;
	public final String javaTraceRootName;
	
	
}
