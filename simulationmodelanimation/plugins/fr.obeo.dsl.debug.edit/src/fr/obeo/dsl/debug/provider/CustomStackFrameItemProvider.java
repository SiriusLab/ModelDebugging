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
import fr.obeo.dsl.debug.StackFrame;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * Custom implementation of {@link StackFrameItemProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CustomStackFrameItemProvider extends StackFrameItemProvider {

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
	public CustomStackFrameItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

		efactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		efactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		CustomDebugItemProviderAdapterFactory debugFactory = new CustomDebugItemProviderAdapterFactory();
		efactory.addAdapterFactory(debugFactory);
		efactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	@Override
	public Object getImage(Object object) {
		assert object instanceof StackFrame;
		final Object res;

		final StackFrame frame = (StackFrame)object;
		final EObject context = frame.getContext();
		final IItemLabelProvider provider = (IItemLabelProvider)efactory.adapt(context,
				IItemLabelProvider.class);
		// final Thread thread = ThreadUtils.getThread(frame);
		// final Object decorator;
		// if (thread != null) {
		// decorator = CustomThreadItemProvider.getDecorator(thread);
		// } else {
		// decorator = null;
		// }
		// if (decorator != null) {
		// List<Object> images = new ArrayList<Object>(2);
		// images.add(provider.getImage(context));
		// images.add(decorator);
		// res = new ComposedImage(images);
		// } else {
		res = provider.getImage(context);
		// }
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
