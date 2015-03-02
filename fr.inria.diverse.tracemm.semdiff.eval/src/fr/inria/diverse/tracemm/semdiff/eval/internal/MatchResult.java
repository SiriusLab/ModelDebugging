package fr.inria.diverse.tracemm.semdiff.eval.internal;

public class MatchResult {

	private boolean matches;
	private boolean matchedWithoutErrors;
	
	public MatchResult(boolean matches, boolean matchedWithoutErrors) {
		this.matches = matches;
		this.matchedWithoutErrors = matchedWithoutErrors;
	}
	
	public boolean matches() {
		return matches;
	}
	
	public boolean matchedWithoutErrors() {
		return matchedWithoutErrors;
	}
	 
}
