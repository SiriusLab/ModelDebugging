package org.gemoc.sigpmlextended.k3dsa;

import com.google.common.collect.LinkedListMultimap;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.InputOutput;
import sigpmlextended.InputPort;
import sigpmlextended.OutputPort;
import sigpmlextended.Place;
import org.gemoc.sigpmlextended.k3dsa.NamedElementAspect;
import org.gemoc.sigpmlextended.k3dsa.OutputPortAspect;
import org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties;
import org.gemoc.sigpmlextended.k3dsa.SystemAspect;

@Aspect(className = Place.class)
@SuppressWarnings("all")
public class PlaceAspect extends NamedElementAspect {
  private static void initialize(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_initialize(_self_, _self);
  }
  
  public static void push(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_push(_self_, _self);
  }
  
  public static void pop(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_pop(_self_, _self);
  }
  
  public static ArrayList<Object> fifo(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    Object result = null;
    result =_privk3_fifo(_self_, _self);
    return (java.util.ArrayList<java.lang.Object>)result;
  }
  
  public static void fifo(final Place _self, final ArrayList<Object> fifo) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_fifo(_self_, _self,fifo);
  }
  
  public static boolean isInitialized(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    Object result = null;
    result =_privk3_isInitialized(_self_, _self);
    return (boolean)result;
  }
  
  public static void isInitialized(final Place _self, final boolean isInitialized) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_isInitialized(_self_, _self,isInitialized);
  }
  
  public static int currentSize(final Place _self) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    Object result = null;
    result =_privk3_currentSize(_self_, _self);
    return (int)result;
  }
  
  public static void currentSize(final Place _self, final int currentSize) {
    org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectProperties _self_ = org.gemoc.sigpmlextended.k3dsa.PlaceAspectPlaceAspectContext.getSelf(_self);
    _privk3_currentSize(_self_, _self,currentSize);
  }
  
  protected static void _privk3_initialize(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
    String _name = _self.getName();
    String _plus = ("place " + _name);
    String _plus_1 = (_plus + "is initializing");
    InputOutput.<String>println(_plus_1);
    ArrayList<Object> _newArrayList = CollectionLiterals.<Object>newArrayList();
    PlaceAspect.fifo(_self, _newArrayList);
    int _delay = _self.getDelay();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _delay, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Object initialObject = null;
        ArrayList<Object> _fifo = PlaceAspect.fifo(_self);
        _fifo.add(initialObject);
      }
    }
    ArrayList<Object> _fifo = PlaceAspect.fifo(_self);
    int _size = _fifo.size();
    PlaceAspect.currentSize(_self, _size);
  }
  
  protected static void _privk3_push(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
    boolean _isInitialized = PlaceAspect.isInitialized(_self);
    boolean _not = (!_isInitialized);
    if (_not) {
      PlaceAspect.initialize(_self);
      PlaceAspect.isInitialized(_self, true);
    }
    OutputPort _itsOutputPort = _self.getItsOutputPort();
    OutputPort _itsOutputPort_1 = _self.getItsOutputPort();
    int _sizeWritten = OutputPortAspect.sizeWritten(_itsOutputPort_1);
    int _minus = (_sizeWritten - 1);
    OutputPortAspect.sizeWritten(_itsOutputPort, _minus);
    String _name = _self.getName();
    String _plus = (_name + "push");
    InputOutput.<String>println(_plus);
    sigpmlextended.System _system = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory = SystemAspect.sharedMemory(_system);
    String _plus_1 = ("sharedMemory: " + _sharedMemory);
    InputOutput.<String>println(_plus_1);
    ArrayList<Object> fifo_view = PlaceAspect.fifo(_self);
    sigpmlextended.System _system_1 = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory_1 = SystemAspect.sharedMemory(_system_1);
    OutputPort _itsOutputPort_2 = _self.getItsOutputPort();
    String _name_1 = _itsOutputPort_2.getName();
    List _get = _sharedMemory_1.get(_name_1);
    final Object objTowrite = _get.get(0);
    sigpmlextended.System _system_2 = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory_2 = SystemAspect.sharedMemory(_system_2);
    OutputPort _itsOutputPort_3 = _self.getItsOutputPort();
    String _name_2 = _itsOutputPort_3.getName();
    _sharedMemory_2.remove(_name_2, objTowrite);
    ArrayList<Object> _fifo = PlaceAspect.fifo(_self);
    _fifo.add(objTowrite);
    ArrayList<Object> _fifo_1 = PlaceAspect.fifo(_self);
    int _size = _fifo_1.size();
    PlaceAspect.currentSize(_self, _size);
    ArrayList<Object> _fifo_2 = PlaceAspect.fifo(_self);
    fifo_view = _fifo_2;
    InputOutput.<ArrayList<Object>>println(fifo_view);
    sigpmlextended.System _system_3 = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory_3 = SystemAspect.sharedMemory(_system_3);
    String _plus_2 = ("sharedMemory: " + _sharedMemory_3);
    InputOutput.<String>println(_plus_2);
  }
  
  protected static void _privk3_pop(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
    boolean _isInitialized = PlaceAspect.isInitialized(_self);
    boolean _not = (!_isInitialized);
    if (_not) {
      PlaceAspect.initialize(_self);
      PlaceAspect.isInitialized(_self, true);
    }
    String _name = _self.getName();
    String _plus = (_name + "pop");
    InputOutput.<String>println(_plus);
    sigpmlextended.System _system = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory = SystemAspect.sharedMemory(_system);
    String _plus_1 = ("sharedMemory: " + _sharedMemory);
    InputOutput.<String>println(_plus_1);
    ArrayList<Object> _fifo = PlaceAspect.fifo(_self);
    InputOutput.<ArrayList<Object>>println(_fifo);
    ArrayList<Object> _fifo_1 = PlaceAspect.fifo(_self);
    final Object readedObject = _fifo_1.get(0);
    ArrayList<Object> _fifo_2 = PlaceAspect.fifo(_self);
    _fifo_2.remove(0);
    ArrayList<Object> _fifo_3 = PlaceAspect.fifo(_self);
    int _size = _fifo_3.size();
    PlaceAspect.currentSize(_self, _size);
    sigpmlextended.System _system_1 = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory_1 = SystemAspect.sharedMemory(_system_1);
    InputPort _itsInputPort = _self.getItsInputPort();
    String _name_1 = _itsInputPort.getName();
    _sharedMemory_1.put(_name_1, readedObject);
    sigpmlextended.System _system_2 = NamedElementAspect.getSystem(_self);
    LinkedListMultimap _sharedMemory_2 = SystemAspect.sharedMemory(_system_2);
    String _plus_2 = ("sharedMemory: " + _sharedMemory_2);
    InputOutput.<String>println(_plus_2);
  }
  
  protected static ArrayList<Object> _privk3_fifo(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
     return _self_.fifo; 
  }
  
  protected static void _privk3_fifo(final PlaceAspectPlaceAspectProperties _self_, final Place _self, final ArrayList<Object> fifo) {
    _self_.fifo = fifo; try {
    
    			for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    				if (m.getName().equals("set" + "Fifo")
    						&& m.getParameterTypes().length == 1) {
    					m.invoke(_self, fifo);
    
    				}
    			}
    		} catch (Exception e) {
    			// Chut !
    		} 
  }
  
  protected static boolean _privk3_isInitialized(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
     return _self_.isInitialized; 
  }
  
  protected static void _privk3_isInitialized(final PlaceAspectPlaceAspectProperties _self_, final Place _self, final boolean isInitialized) {
    _self_.isInitialized = isInitialized; try {
    
    			for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    				if (m.getName().equals("set" + "IsInitialized")
    						&& m.getParameterTypes().length == 1) {
    					m.invoke(_self, isInitialized);
    
    				}
    			}
    		} catch (Exception e) {
    			// Chut !
    		} 
  }
  
  protected static int _privk3_currentSize(final PlaceAspectPlaceAspectProperties _self_, final Place _self) {
     return _self_.currentSize; 
  }
  
  protected static void _privk3_currentSize(final PlaceAspectPlaceAspectProperties _self_, final Place _self, final int currentSize) {
    _self_.currentSize = currentSize; try {
    
    			for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    				if (m.getName().equals("set" + "CurrentSize")
    						&& m.getParameterTypes().length == 1) {
    					m.invoke(_self, currentSize);
    
    				}
    			}
    		} catch (Exception e) {
    			// Chut !
    		} 
  }
}
