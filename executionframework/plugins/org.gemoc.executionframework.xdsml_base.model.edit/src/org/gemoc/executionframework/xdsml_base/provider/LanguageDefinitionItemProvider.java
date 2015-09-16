/**
 */
package org.gemoc.executionframework.xdsml_base.provider;


import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.executionframework.xdsml_base.Xdsml_baseFactory;
import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * This is the item provider adapter for a {@link org.gemoc.executionframework.xdsml_base.LanguageDefinition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LanguageDefinitionItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LanguageDefinitionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addMelangeURIPropertyDescriptor(object);
			addNeedMelangeSynchronizationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LanguageDefinition_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LanguageDefinition_name_feature", "_UI_LanguageDefinition_type"),
				 Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Melange URI feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMelangeURIPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LanguageDefinition_melangeURI_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LanguageDefinition_melangeURI_feature", "_UI_LanguageDefinition_type"),
				 Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__MELANGE_URI,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Need Melange Synchronization feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNeedMelangeSynchronizationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LanguageDefinition_needMelangeSynchronization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LanguageDefinition_needMelangeSynchronization_feature", "_UI_LanguageDefinition_type"),
				 Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT);
			childrenFeatures.add(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__EDITOR_PROJECTS);
			childrenFeatures.add(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns LanguageDefinition.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LanguageDefinition"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((LanguageDefinition)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_LanguageDefinition_type") :
			getString("_UI_LanguageDefinition_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(LanguageDefinition.class)) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NAME:
			case Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI:
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT,
				 Xdsml_baseFactory.eINSTANCE.createDomainModelProject()));

		newChildDescriptors.add
			(createChildParameter
				(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__EDITOR_PROJECTS,
				 Xdsml_baseFactory.eINSTANCE.createSiriusEditorProject()));

		newChildDescriptors.add
			(createChildParameter
				(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__EDITOR_PROJECTS,
				 Xdsml_baseFactory.eINSTANCE.createXTextEditorProject()));

		newChildDescriptors.add
			(createChildParameter
				(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__EDITOR_PROJECTS,
				 Xdsml_baseFactory.eINSTANCE.createTreeEditorProject()));

		newChildDescriptors.add
			(createChildParameter
				(Xdsml_basePackage.Literals.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS,
				 Xdsml_baseFactory.eINSTANCE.createSiriusAnimatorProject()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Xdsml_baseEditPlugin.INSTANCE;
	}

}
