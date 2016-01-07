package org.gemoc.executionframework.ui.utils;

import org.eclipse.xtext.util.SimpleAttributeResolver;
import org.gemoc.executionframework.engine.mse.MSE;

public class ViewUtils {

	public static String eventToString(MSE mse) {
		StringBuilder builder = new StringBuilder();
		if (mse.getCaller() != null)
		{
			builder.append(mse.getCaller().eClass().getName());
			builder.append("->");
			builder.append(SimpleAttributeResolver.NAME_RESOLVER.apply(mse.getCaller()));
			
		}
		if (mse.getAction() != null)
		{
			builder.append(".");
			builder.append(mse.getAction().getName());			
			builder.append("()");
		}
		return builder.toString();
	}
	
}
