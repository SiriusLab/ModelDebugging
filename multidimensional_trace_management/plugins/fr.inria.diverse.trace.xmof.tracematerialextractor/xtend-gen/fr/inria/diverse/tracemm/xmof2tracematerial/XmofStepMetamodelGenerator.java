package fr.inria.diverse.tracemm.xmof2tracematerial;

import com.google.common.collect.Iterables;
import java.util.Set;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class XmofStepMetamodelGenerator {
  protected final Resource xmofModel;
  
  protected boolean done = false;
  
  protected EcoreUtil.Copier copier;
  
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PROTECTED_SETTER })
  private EPackage stepmmResult;
  
  public XmofStepMetamodelGenerator(final Set<EPackage> ecore, final Resource xmofModel, final EcoreUtil.Copier copier) {
    this.xmofModel = xmofModel;
    this.copier = copier;
  }
  
  public XmofStepMetamodelGenerator(final Resource ecoreModel, final Resource xmofModel, final EcoreUtil.Copier copier) {
    this(IterableExtensions.<EPackage>toSet(Iterables.<EPackage>filter(ecoreModel.getContents(), EPackage.class)), xmofModel, copier);
  }
  
  protected void computeStepMM() {
    throw new Error("Unresolved compilation problems:"
      + "\nActivity cannot be resolved to a type."
      + "\nThe method or field BehavioredEClass is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method ownedBehavior is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method or field Activity is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method or field ParameterDirectionKind is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method or field ParameterDirectionKind is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method or field ParameterDirectionKind is undefined for the type XmofStepMetamodelGenerator"
      + "\nThe method or field ParameterDirectionKind is undefined for the type XmofStepMetamodelGenerator"
      + "\nType mismatch: cannot convert from EObject to EClass"
      + "\nfilter cannot be resolved"
      + "\nownedParameter cannot be resolved"
      + "\nEType cannot be resolved"
      + "\nEType cannot be resolved"
      + "\nEType cannot be resolved"
      + "\ndirection cannot be resolved"
      + "\n== cannot be resolved"
      + "\nIN cannot be resolved"
      + "\n|| cannot be resolved"
      + "\ndirection cannot be resolved"
      + "\n== cannot be resolved"
      + "\nINOUT cannot be resolved"
      + "\ndirection cannot be resolved"
      + "\n== cannot be resolved"
      + "\nOUT cannot be resolved"
      + "\n|| cannot be resolved"
      + "\ndirection cannot be resolved"
      + "\n== cannot be resolved"
      + "\nRETURN cannot be resolved"
      + "\nunique cannot be resolved"
      + "\nordered cannot be resolved"
      + "\nlowerBound cannot be resolved"
      + "\nupperBound cannot be resolved");
  }
  
  @Pure
  public EPackage getStepmmResult() {
    return this.stepmmResult;
  }
  
  protected void setStepmmResult(final EPackage stepmmResult) {
    this.stepmmResult = stepmmResult;
  }
}
