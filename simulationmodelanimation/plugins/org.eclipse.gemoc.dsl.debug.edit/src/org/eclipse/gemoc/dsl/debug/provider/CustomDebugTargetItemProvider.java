/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.debug.provider;

import fr.obeo.dsl.debug.Contextual;
import fr.obeo.dsl.debug.DebugTarget;
import fr.obeo.dsl.debug.DebugTargetState;
import fr.obeo.dsl.debug.DebugTargetUtils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * Custom implementation of {@link DebugTargetItemProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CustomDebugTargetItemProvider extends DebugTargetItemProvider {

	/**
	 * {@link ComposedAdapterFactory} to get {@link fr.obeo.dsl.debug.Contextual#getContext() context} image
	 * and text.
	 */
	private final ComposedAdapterFactory efactory;

	/**
	 * Constructor.
	 * 
	 * @param adapterFactory
	 *            the {@link AdapterFactory}.
	 */
	public CustomDebugTargetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

		efactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		efactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		CustomDebugItemProviderAdapterFactory debugFactory = new CustomDebugItemProviderAdapterFactory();
		efactory.addAdapterFactory(debugFactory);
		efactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	@Override
	public Object getImage(Object object) {
		assert object instanceof DebugTarget;
		final Object res;

		final DebugTarget target = (DebugTarget)object;
		final EObject context = target.getContext();
		final IItemLabelProvider provider = (IItemLabelProvider)efactory.adapt(context,
				IItemLabelProvider.class);
		final Object decorator = getDecorator(target);
		if (decorator != null) {
			List<Object> images = new ArrayList<Object>(2);
			images.add(provider.getImage(context));
			images.add(decorator);
			res = new ComposedImage(images);
		} else {
			res = provider.getImage(context);
		}
		return res;
	}

	/**
	 * Gets the decorator according to the given {@link DebugTarget}.
	 * 
	 * @param target
	 *            the {@link DebugTarget}
	 * @return the decorator according to the given {@link DebugTarget}
	 */
	private Object getDecorator(DebugTarget target) {
		final Object res;

		if (DebugTargetUtils.isTerminated(target)) {
			res = DebugEditPlugin.INSTANCE.getImage("full/deco16/TERMINATED");
		} else if (DebugTargetUtils.isSuspended(target)) {
			res = DebugEditPlugin.INSTANCE.getImage("full/deco16/SUSPENDED");
		} else {
			if (target.getState() == DebugTargetState.CONNECTED) {
				res = DebugEditPlugin.INSTANCE.getImage("full/deco16/RUNNING");
			} else {
				res = null;
			}
		}
		return res;
	}

	@Override
	public String getText(Object object) {
		assert object instanceof Contextual;
		final EObject context = ((Contextual)object).getContext();
		IItemLabelProvider provider = (IItemLabelProvider)efactory.adapt(context, IItemLabelProvider.class);
		return provider.getText(context);
	}

	@Override
	public void dispose() {
		super.dispose();
		efactory.dispose();
	}

}
