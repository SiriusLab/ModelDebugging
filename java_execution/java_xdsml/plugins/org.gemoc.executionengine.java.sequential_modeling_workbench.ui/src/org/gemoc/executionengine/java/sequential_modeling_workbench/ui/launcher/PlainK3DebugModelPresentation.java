package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.launcher;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.swt.graphics.Image;

import fr.obeo.dsl.debug.DebugTarget;
import fr.obeo.dsl.debug.StackFrame;

public class PlainK3DebugModelPresentation extends GemocDebugModelPresentation {
	
	@Override
	public String getText(Object element) {
		
		if(element instanceof Adapter) {
			Object target = ((Adapter)element).getTarget();
		
			if(target instanceof DebugTarget) {
				return ((DebugTarget)target).getName();
				
			} else if(target instanceof fr.obeo.dsl.debug.Thread) {
				return ((fr.obeo.dsl.debug.Thread)target).getName();
				
			} else if(target instanceof StackFrame) {
				return ((StackFrame) target).getName();
			}
			
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		
		return super.getImage(element);
	}

}