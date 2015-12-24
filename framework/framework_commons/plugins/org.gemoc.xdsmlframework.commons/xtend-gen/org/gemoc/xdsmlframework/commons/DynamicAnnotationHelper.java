package org.gemoc.xdsmlframework.commons;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class DynamicAnnotationHelper {
  public final static String DYNAMIC_ANNOTATION_URI = "dynamic";
  
  private static boolean isDynamic(final EModelElement o) {
    EList<EAnnotation> _eAnnotations = o.getEAnnotations();
    final Function1<EAnnotation, Boolean> _function = (EAnnotation a) -> {
      String _source = a.getSource();
      return Boolean.valueOf(_source.equals(DynamicAnnotationHelper.DYNAMIC_ANNOTATION_URI));
    };
    return IterableExtensions.<EAnnotation>exists(_eAnnotations, _function);
  }
  
  public static boolean isDynamic(final EClass c) {
    return DynamicAnnotationHelper.isDynamic(((EModelElement) c));
  }
  
  public static boolean isDynamic(final EStructuralFeature p) {
    boolean _or = false;
    boolean _isDynamic = DynamicAnnotationHelper.isDynamic(((EModelElement) p));
    if (_isDynamic) {
      _or = true;
    } else {
      EClass _eContainingClass = p.getEContainingClass();
      boolean _isDynamic_1 = DynamicAnnotationHelper.isDynamic(_eContainingClass);
      _or = _isDynamic_1;
    }
    return _or;
  }
}
