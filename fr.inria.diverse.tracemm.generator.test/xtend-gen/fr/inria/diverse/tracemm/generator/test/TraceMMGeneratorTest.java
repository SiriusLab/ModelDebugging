package fr.inria.diverse.tracemm.generator.test;

import ecorext.Ecorext;
import fr.inria.diverse.tracemm.generator.TraceMMGenerator;
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
  private final static String SIMPLEST_ECORE_PATH = "model_inputs/simplestmm.ecore";
  
  private final static String SIMPLEST_ECOREXT_PATH = "model_inputs/simplestmmext.xmi";
  
  private final static String SIMPLEST_EVENTS_PATH = "model_inputs/simplestmmevents.ecore";
  
  private final static String CURRENT_BUNDLE = "fr.inria.diverse.tracemm.generator.test";
  
  private final static String SIMPLEST_TMM_EXPECTED_PATH = "expected/yay.xmi";
  
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
  public void testSimplestExtensionTMMGeneration() {
    try {
      this.loadModel(TraceMMGeneratorTest.SIMPLEST_ECORE_PATH);
      final Resource ecorextResource = this.loadModel(TraceMMGeneratorTest.SIMPLEST_ECOREXT_PATH);
      final Resource eventsResource = this.loadModel(TraceMMGeneratorTest.SIMPLEST_EVENTS_PATH);
      EList<EObject> _contents = ecorextResource.getContents();
      EObject _get = _contents.get(0);
      final Ecorext ecorext = ((Ecorext) _get);
      EList<EObject> _contents_1 = eventsResource.getContents();
      EObject _get_1 = _contents_1.get(0);
      final EPackage events = ((EPackage) _get_1);
      final TraceMMGenerator stuff = new TraceMMGenerator(ecorext, events);
      stuff.computeAllMaterial();
      if (TraceMMGeneratorTest.saveInFiles) {
        URI _createFileURI = EMFUtil.createFileURI("tmp/tracemmResult.ecore");
        final Resource r1 = this.rs.createResource(_createFileURI);
        EList<EObject> _contents_2 = r1.getContents();
        EPackage _tracemmresult = stuff.getTracemmresult();
        _contents_2.add(_tracemmresult);
        r1.save(null);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
