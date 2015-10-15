package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;

public class MutableData {
	private String name;
	private EObject eObject;
	private Supplier<Object> getValue;
	private Consumer<Object> setValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return this.getValue.get();
	}
	public void setValue(Object value) {
		this.setValue.accept(value);
	}
	public EObject geteObject() {
		return eObject;
	}
	public void seteObject(EObject eObject) {
		this.eObject = eObject;
	}
	public void setGetter(Supplier<Object> supplier) {
		getValue = supplier;
	}
	public void setSetter(Consumer<Object> consumer) {
		setValue = consumer;
	}
	@Override
	public String toString() {
		return name;
	}
}
