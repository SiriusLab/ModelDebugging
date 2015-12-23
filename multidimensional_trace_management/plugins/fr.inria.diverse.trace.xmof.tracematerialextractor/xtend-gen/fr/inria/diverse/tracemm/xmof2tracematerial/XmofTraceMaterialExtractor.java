package fr.inria.diverse.tracemm.xmof2tracematerial;

import com.google.common.collect.Iterables;
import ecorext.Ecorext;
import fr.inria.diverse.tracemm.xmof2tracematerial.XmofExecutionExtensionGenerator;
import fr.inria.diverse.tracemm.xmof2tracematerial.XmofStepMetamodelGenerator;
import java.util.Set;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class XmofTraceMaterialExtractor {
  protected final Resource xmofModel;
  
  protected boolean done = false;
  
  protected EcoreUtil.Copier copier;
  
  protected XmofStepMetamodelGenerator stepGenerator;
  
  protected XmofExecutionExtensionGenerator exeExtGenerator;
  
  protected Set<EPackage> ecore;
  
  public XmofTraceMaterialExtractor(final Set<EPackage> ecore, final Resource xmofModel) {
    this.xmofModel = xmofModel;
    this.ecore = ecore;
  }
  
  public XmofTraceMaterialExtractor(final Resource ecoreModel, final Resource xmofModel) {
    this(IterableExtensions.<EPackage>toSet(Iterables.<EPackage>filter(ecoreModel.getContents(), EPackage.class)), xmofModel);
  }
  
  public void computeAllMaterial() {
    if ((!this.done)) {
      EcoreUtil.Copier _copier = new EcoreUtil.Copier();
      this.copier = _copier;
      XmofExecutionExtensionGenerator _xmofExecutionExtensionGenerator = new XmofExecutionExtensionGenerator(this.ecore, this.xmofModel, this.copier);
      this.exeExtGenerator = _xmofExecutionExtensionGenerator;
      XmofStepMetamodelGenerator _xmofStepMetamodelGenerator = new XmofStepMetamodelGenerator(this.ecore, this.xmofModel, this.copier);
      this.stepGenerator = _xmofStepMetamodelGenerator;
      this.exeExtGenerator.computeMMExtension();
      this.stepGenerator.computeStepMM();
      this.copier.copyReferences();
    } else {
      InputOutput.<String>println("ERROR: already computed.");
    }
  }
  
  public EPackage getStepMMResult() {
    return this.stepGenerator.getStepmmResult();
  }
  
  public Ecorext getExeExtResult() {
    return this.exeExtGenerator.getMmextensionResult();
  }
}
