package org.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import fr.obeo.dsl.debug.ide.adapter.ILocator;

public class XtextLocator implements ILocator {

	@Override
	public Location getLocation(EObject eObject) {
		INode node = NodeModelUtils.getNode(eObject);
		if (node != null) {
			return new Location(Type.XTEXT_LOCATION, node.getStartLine());
		}
		return new Location(Type.XTEXT_LOCATION, -1);
	}
}
