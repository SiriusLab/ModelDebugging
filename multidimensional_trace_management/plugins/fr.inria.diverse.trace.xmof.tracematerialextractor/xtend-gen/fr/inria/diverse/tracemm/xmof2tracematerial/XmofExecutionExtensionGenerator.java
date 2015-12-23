package fr.inria.diverse.tracemm.xmof2tracematerial;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import ecorext.Ecorext;
import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class XmofExecutionExtensionGenerator {
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PROTECTED_SETTER })
  private Ecorext mmextensionResult;
  
  protected final Resource xmofModel;
  
  protected boolean done = false;
  
  protected EcoreUtil.Copier copier;
  
  private Set<EClass> ecoreClasses;
  
  public XmofExecutionExtensionGenerator(final Set<EPackage> ecore, final Resource xmofModel, final EcoreUtil.Copier copier) {
    this.xmofModel = xmofModel;
    final Function1<EPackage, Set<EObject>> _function = (EPackage p) -> {
      TreeIterator<EObject> _eAllContents = p.eAllContents();
      return IteratorExtensions.<EObject>toSet(_eAllContents);
    };
    Iterable<Set<EObject>> _map = IterableExtensions.<EPackage, Set<EObject>>map(ecore, _function);
    Iterable<EObject> _flatten = Iterables.<EObject>concat(_map);
    Iterable<EClass> _filter = Iterables.<EClass>filter(_flatten, EClass.class);
    Set<EClass> _set = IterableExtensions.<EClass>toSet(_filter);
    this.ecoreClasses = _set;
    this.copier = copier;
  }
  
  public XmofExecutionExtensionGenerator(final Resource ecoreModel, final Resource xmofModel, final EcoreUtil.Copier copier) {
    this(IterableExtensions.<EPackage>toSet(Iterables.<EPackage>filter(ecoreModel.getContents(), EPackage.class)), xmofModel, copier);
  }
  
  protected EClass findExtendedClass(final EClass confClass) {
    EClass res = null;
    final HashSet<EClass> otherResultsFar = new HashSet<EClass>();
    EList<EClass> _eSuperTypes = confClass.getESuperTypes();
    final Function1<EClass, Boolean> _function = (EClass c) -> {
      return Boolean.valueOf((!Objects.equal(c, confClass)));
    };
    Iterable<EClass> _filter = IterableExtensions.<EClass>filter(_eSuperTypes, _function);
    for (final EClass superType : _filter) {
      boolean _contains = this.ecoreClasses.contains(superType);
      if (_contains) {
        res = superType;
      } else {
        EClass indirectSuperType = this.findExtendedClass(superType);
        boolean _notEquals = (!Objects.equal(indirectSuperType, null));
        if (_notEquals) {
          otherResultsFar.add(indirectSuperType);
        }
      }
    }
    boolean _notEquals_1 = (!Objects.equal(res, null));
    if (_notEquals_1) {
      return res;
    } else {
      int _size = otherResultsFar.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        return ((EClass[])Conversions.unwrapArray(otherResultsFar, EClass.class))[0];
      } else {
        return null;
      }
    }
  }
  
  protected EPackage obtainExtensionPackage(final EPackage runtimePackage) {
    EPackage result = null;
    boolean _notEquals = (!Objects.equal(runtimePackage, null));
    if (_notEquals) {
      EPackage _eSuperPackage = runtimePackage.getESuperPackage();
      final EPackage tracedSuperPackage = this.obtainExtensionPackage(_eSuperPackage);
      boolean _equals = Objects.equal(tracedSuperPackage, null);
      if (_equals) {
        EList<EPackage> _newPackages = this.mmextensionResult.getNewPackages();
        final Function1<EPackage, Boolean> _function = (EPackage p) -> {
          String _name = p.getName();
          String _name_1 = runtimePackage.getName();
          return Boolean.valueOf(_name.equals(_name_1));
        };
        EPackage _findFirst = IterableExtensions.<EPackage>findFirst(_newPackages, _function);
        result = _findFirst;
      } else {
        EList<EPackage> _eSubpackages = tracedSuperPackage.getESubpackages();
        final Function1<EPackage, Boolean> _function_1 = (EPackage p) -> {
          String _name = p.getName();
          String _name_1 = runtimePackage.getName();
          return Boolean.valueOf(_name.equals(_name_1));
        };
        EPackage _findFirst_1 = IterableExtensions.<EPackage>findFirst(_eSubpackages, _function_1);
        result = _findFirst_1;
      }
      boolean _equals_1 = Objects.equal(result, null);
      if (_equals_1) {
        EPackage _createEPackage = EcoreFactory.eINSTANCE.createEPackage();
        result = _createEPackage;
        String _name = runtimePackage.getName();
        result.setName(_name);
        String _name_1 = result.getName();
        result.setNsURI(_name_1);
        result.setNsPrefix("");
        boolean _equals_2 = Objects.equal(tracedSuperPackage, null);
        if (_equals_2) {
          EList<EPackage> _newPackages_1 = this.mmextensionResult.getNewPackages();
          _newPackages_1.add(result);
        } else {
          EList<EPackage> _eSubpackages_1 = tracedSuperPackage.getESubpackages();
          _eSubpackages_1.add(result);
        }
      }
    }
    return result;
  }
  
  protected void computeMMExtension() {
    throw new Error("Unresolved compilation problems:"
      + "\nActivity cannot be resolved to a type."
      + "\nBehavioredEClass cannot be resolved to a type."
      + "\nBehavioredEClass cannot be resolved to a type."
      + "\nBehavioredEClass cannot be resolved to a type."
      + "\nBehavioredEClass cannot be resolved to a type."
      + "\nThe method classifierBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nThe method ownedBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nThe method or field classifierBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nThe method ownedBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nThe method or field classifierBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nThe method ownedBehavior is undefined for the type XmofExecutionExtensionGenerator"
      + "\nclear cannot be resolved"
      + "\naddAll cannot be resolved");
  }
  
  private void addTraceabilityAnnotations(final EClass executionClass) {
    EObject _get = this.copier.get(executionClass);
    this.addTraceabilityAnnotations(executionClass, ((EClass) _get));
    EList<EAttribute> _eAttributes = executionClass.getEAttributes();
    this.addTraceabilityAnnotations(_eAttributes);
    EList<EReference> _eReferences = executionClass.getEReferences();
    this.addTraceabilityAnnotations(_eReferences);
  }
  
  private void addTraceabilityAnnotations(final Collection<? extends EStructuralFeature> executionClassProperties) {
    final Consumer<EStructuralFeature> _function = (EStructuralFeature property) -> {
      EObject _get = this.copier.get(property);
      this.addTraceabilityAnnotations(property, ((EStructuralFeature) _get));
    };
    executionClassProperties.forEach(_function);
  }
  
  private void addTraceabilityAnnotations(final EModelElement originalExecutionMetamodelElement, final EModelElement extensionModelElement) {
    Resource _eResource = originalExecutionMetamodelElement.eResource();
    final String executionMetamodelElementURI = _eResource.getURIFragment(originalExecutionMetamodelElement);
    ExecutionMetamodelTraceability.createTraceabilityAnnotation(extensionModelElement, executionMetamodelElementURI);
  }
  
  /**
   * Note: we consider the xmof model to be immutable (we reuse its objects without copying them)
   */
  protected static EClass behavioredToNormal(final /* BehavioredEClass */Object b) {
    throw new Error("Unresolved compilation problems:"
      + "\neGet cannot be resolved");
  }
  
  @Pure
  public Ecorext getMmextensionResult() {
    return this.mmextensionResult;
  }
  
  protected void setMmextensionResult(final Ecorext mmextensionResult) {
    this.mmextensionResult = mmextensionResult;
  }
}
