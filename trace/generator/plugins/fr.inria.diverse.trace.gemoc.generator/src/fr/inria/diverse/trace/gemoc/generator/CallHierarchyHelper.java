package fr.inria.diverse.trace.gemoc.generator;

import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.internal.corext.callhierarchy.CallHierarchy;
import org.eclipse.jdt.internal.corext.callhierarchy.MethodWrapper;

public class CallHierarchyHelper {

	public static HashSet<IMethod> getCallersOf(IMethod m) {

	    CallHierarchy callHierarchy = CallHierarchy.getDefault();

	    IMember[] members = { m };

	    MethodWrapper[] methodWrappers = callHierarchy.getCallerRoots(members);
	    HashSet<IMethod> callers = new HashSet<IMethod>();
	    for (MethodWrapper mw : methodWrappers) {
	        MethodWrapper[] mw2 = mw.getCalls(new NullProgressMonitor());
	        HashSet<IMethod> temp = getIMethods(mw2);
	        callers.addAll(temp);
	    }

	    return callers;
	}
	
	private static HashSet<IMethod> getIMethods(MethodWrapper[] methodWrappers) {
	    HashSet<IMethod> c = new HashSet<IMethod>();
	    for (MethodWrapper m : methodWrappers) {
	        IMethod im = getIMethodFromMethodWrapper(m);
	        if (im != null) {
	            c.add(im);
	        }
	    }
	    return c;
	}
	
	private static IMethod getIMethodFromMethodWrapper(MethodWrapper m) {
		try {
			IMember im = m.getMember();
			if (im.getElementType() == IJavaElement.METHOD) {
				return (IMethod) m.getMember();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
