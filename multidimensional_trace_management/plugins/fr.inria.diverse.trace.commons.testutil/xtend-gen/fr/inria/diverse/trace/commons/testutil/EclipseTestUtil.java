package fr.inria.diverse.trace.commons.testutil;

import com.google.common.base.Objects;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class EclipseTestUtil {
  public static void waitForJobs() {
    while ((!Job.getJobManager().isIdle())) {
      EclipseTestUtil.delay(100);
    }
  }
  
  public static void waitForJobsThenWindowClosed() {
    EclipseTestUtil.delay(1000000000);
  }
  
  private static boolean closed = false;
  
  private static void delay(final long waitTimeMillis) {
    final Display display = Display.getCurrent();
    IWorkbench _workbench = PlatformUI.getWorkbench();
    _workbench.addWindowListener(
      new IWindowListener() {
        @Override
        public void windowActivated(final IWorkbenchWindow window) {
        }
        
        @Override
        public void windowClosed(final IWorkbenchWindow window) {
          EclipseTestUtil.closed = true;
        }
        
        @Override
        public void windowDeactivated(final IWorkbenchWindow window) {
        }
        
        @Override
        public void windowOpened(final IWorkbenchWindow window) {
        }
      });
    boolean _notEquals = (!Objects.equal(display, null));
    if (_notEquals) {
      long _currentTimeMillis = System.currentTimeMillis();
      final long endTimeMillis = (_currentTimeMillis + waitTimeMillis);
      while (((System.currentTimeMillis() < endTimeMillis) && (!EclipseTestUtil.closed))) {
        boolean _readAndDispatch = display.readAndDispatch();
        boolean _not = (!_readAndDispatch);
        if (_not) {
          display.sleep();
        }
      }
      display.update();
    } else {
      try {
        Thread.sleep(waitTimeMillis);
      } catch (final Throwable _t) {
        if (_t instanceof InterruptedException) {
          final InterruptedException e = (InterruptedException)_t;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
}
