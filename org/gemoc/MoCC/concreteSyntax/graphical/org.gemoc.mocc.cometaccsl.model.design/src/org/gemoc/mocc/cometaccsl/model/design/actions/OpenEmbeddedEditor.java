/**
 * Copyright (c) 2012-2016 GEMOC consortium.
 * 
 * http://www.gemoc.org
 * 
 * Contributors:
 *   Stephen Creff - ENSTA Bretagne [stephen.creff@ensta-bretagne.fr]
 *   
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * $Id$
 */
package org.gemoc.mocc.cometaccsl.model.design.actions;

import org.gemoc.mocc.cometaccsl.model.xtext.ui.internal.MoCDslActivator;
import com.google.inject.Injector;

public class OpenEmbeddedEditor extends OpenXtextEmbeddedEditor {
	
    @Override
    protected Injector getInjector() {
        return  MoCDslActivator.getInstance().getInjector(MoCDslActivator.ORG_GEMOC_MOCC_COMETACCSL_MODEL_XTEXT_MOCDSL);
    }
}

