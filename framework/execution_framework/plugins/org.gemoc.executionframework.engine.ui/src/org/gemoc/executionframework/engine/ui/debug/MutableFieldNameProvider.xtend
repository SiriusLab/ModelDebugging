package org.gemoc.executionframework.engine.ui.debug

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
 
public class MutableFieldNameProvider extends DefaultDeclarativeQualifiedNameProvider{
 
    def QualifiedName qualifiedName(MutableField e) {
        var qnameSegments = this.getFullyQualifiedName(e.geteObject).segments
        qnameSegments.add(e.mutableProperty.name)
        return QualifiedName.create(qnameSegments);
    }
 
}