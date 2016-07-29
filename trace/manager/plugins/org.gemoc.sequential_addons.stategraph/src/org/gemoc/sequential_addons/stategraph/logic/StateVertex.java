package org.gemoc.sequential_addons.stategraph.logic;

import java.util.function.Consumer;

public class StateVertex {
	
	private String tooltip;
	private Consumer<String> command;
	
	public StateVertex() {
	}
	
	public StateVertex(String tooltip) {
		this.tooltip = tooltip;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		if (command != null) {
			command.accept(tooltip);
		}
	}
	
	public void setOnTooltipUpdateCommand(Consumer<String> command) {
		this.command = command;
	}
}
