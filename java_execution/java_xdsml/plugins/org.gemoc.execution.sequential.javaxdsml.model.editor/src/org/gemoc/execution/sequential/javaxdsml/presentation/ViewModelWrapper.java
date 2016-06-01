/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.execution.sequential.javaxdsml.presentation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Wrapper dedicated to support changes on a complex model and to be used as support for a View
 * @author dvojtise
 *
 */
public class ViewModelWrapper {
	  private PropertyChangeSupport changeSupport = 
	      new PropertyChangeSupport(this);

	  public void addPropertyChangeListener(PropertyChangeListener 
	      listener) {
	    changeSupport.addPropertyChangeListener(listener);
	  }

	  public void removePropertyChangeListener(PropertyChangeListener 
	      listener) {
	    changeSupport.removePropertyChangeListener(listener);
	  }

	  public void addPropertyChangeListener(String propertyName,
	      PropertyChangeListener listener) {
	    changeSupport.addPropertyChangeListener(propertyName, listener);
	  }

	  public void removePropertyChangeListener(String propertyName,
	      PropertyChangeListener listener) {
	    changeSupport.removePropertyChangeListener(propertyName, listener);
	  }

	  protected void firePropertyChange(String propertyName, 
	      Object oldValue,
	      Object newValue) {
	    changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	  }
	} 
