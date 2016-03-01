package org.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.gemoc.execution.sequential.javaengine.ui.Activator;

import fr.obeo.dsl.debug.DebugTarget;
import fr.obeo.dsl.debug.StackFrame;
import fr.obeo.dsl.debug.ide.adapter.DSLDebugTargetAdapter;
import fr.obeo.dsl.debug.ide.adapter.DSLThreadAdapter;

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
		if (element instanceof DSLDebugTargetAdapter || element instanceof DSLThreadAdapter) {
			ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/debugt_obj.png");
			return id.createImage();
		}
		return super.getImage(element);
	}

}