package org.eclipse.gemoc.addon.diffviewer.logic;

public class Diff {

	public enum DiffKind {
		SUBST, IN, DEL, EQ
	}
	
	public final DiffKind kind;

	public final int idx1;
	
	public final int idx2;
	
	public Diff(DiffKind kind, int idx1, int idx2) {
		this.kind = kind;
		this.idx1 = idx1;
		this.idx2 = idx2;
	}
}
