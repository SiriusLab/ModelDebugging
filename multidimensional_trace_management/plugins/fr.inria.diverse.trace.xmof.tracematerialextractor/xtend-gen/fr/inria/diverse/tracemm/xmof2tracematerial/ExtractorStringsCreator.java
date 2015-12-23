package fr.inria.diverse.tracemm.xmof2tracematerial;

import org.eclipse.emf.ecore.EClass;

@SuppressWarnings("all")
public class ExtractorStringsCreator {
  public final static String ref_StepToEntry = "correspondingEntryStep";
  
  public final static String ref_StepToThis = "thisParam";
  
  public static String class_createStepClassName(final EClass confClass, final /* Activity */Object activity) {
    throw new Error("Unresolved compilation problems:"
      + "\nname cannot be resolved");
  }
  
  public static String class_createStepClassName(final EClass confClass, final String activityName) {
    String _name = confClass.getName();
    String _replace = _name.replace("Configuration", "");
    String _plus = (_replace + "_");
    String _plus_1 = (_plus + activityName);
    return (_plus_1 + "EntryStepOccurrence");
  }
  
  public static String ref_createStepToParam(final /* DirectedParameter */Object param) {
    throw new Error("Unresolved compilation problems:"
      + "\nname cannot be resolved");
  }
  
  public static String ref_createStepToParam(final String paramName) {
    return (paramName + "Param");
  }
  
  public static String ref_createStepToReturn(final /* DirectedParameter */Object param) {
    throw new Error("Unresolved compilation problems:"
      + "\nname cannot be resolved");
  }
  
  public static String ref_createStepToReturn(final String paramName) {
    return (paramName + "Return");
  }
}
