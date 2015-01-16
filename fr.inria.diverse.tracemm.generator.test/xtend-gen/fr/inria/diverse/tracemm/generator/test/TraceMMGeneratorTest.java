package fr.inria.diverse.tracemm.generator.test;

import ecorext.Ecorext;
import fr.inria.diverse.tracemm.generator.TraceMMGenerator;
import fr.inria.diverse.tracemm.test.util.EMFCompareUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.xmof.vm.util.EMFUtil;

@SuppressWarnings("all")
public class TraceMMGeneratorTest {
  private final static String MODEL2_ECORE_PATH = "model_inputs/model2.ecore";
  
  private final static String MODEL2_ECOREXT_PATH = "model_inputs/model2ext.xmi";
  
  private final static String MODEL2_EVENTS_PATH = "model_inputs/model2events.ecore";
  
  private final static String MODEL2_TMM_EXPECTED_PATH = "model_expected/model2tracemm.ecore";
  
  private static boolean saveInFiles = true;
  
  private ResourceSet rs;
  
  @Before
  public void init() {
    ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
    this.rs = _resourceSetImpl;
    EMFUtil.registerEcoreFactory(this.rs);
    EMFUtil.registerXMIFactory(this.rs);
  }
  
  public Resource loadModel(final String path) {
    try {
      URI _createFileURI = EMFUtil.createFileURI(path);
      final Resource res = this.rs.createResource(_createFileURI);
      res.load(null);
      EcoreUtil.resolveAll(this.rs);
      return res;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testModel2ExtensionTMMGeneration() {
    try {
      this.loadModel(TraceMMGeneratorTest.MODEL2_ECORE_PATH);
      final Resource ecorextResource = this.loadModel(TraceMMGeneratorTest.MODEL2_ECOREXT_PATH);
      final Resource eventsResource = this.loadModel(TraceMMGeneratorTest.MODEL2_EVENTS_PATH);
      final Resource expectedTraceMMResource = this.loadModel(TraceMMGeneratorTest.MODEL2_TMM_EXPECTED_PATH);
      EList<EObject> _contents = ecorextResource.getContents();
      EObject _get = _contents.get(0);
      final Ecorext ecorext = ((Ecorext) _get);
      EList<EObject> _contents_1 = eventsResource.getContents();
      EObject _get_1 = _contents_1.get(0);
      final EPackage events = ((EPackage) _get_1);
      EList<EObject> _contents_2 = expectedTraceMMResource.getContents();
      final EObject expectedTraceMM = _contents_2.get(0);
      final TraceMMGenerator stuff = new TraceMMGenerator(ecorext, events);
      stuff.computeAllMaterial();
      if (TraceMMGeneratorTest.saveInFiles) {
        URI _createFileURI = EMFUtil.createFileURI("tmp/tracemmResult.ecore");
        final Resource r1 = this.rs.createResource(_createFileURI);
        EList<EObject> _contents_3 = r1.getContents();
        EPackage _tracemmresult = stuff.getTracemmresult();
        _contents_3.add(_tracemmresult);
        r1.save(null);
      }
      EPackage _tracemmresult_1 = stuff.getTracemmresult();
      EMFCompareUtil.assertEqualsEMF("Generated trace mm does not match expected", _tracemmresult_1, expectedTraceMM);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
