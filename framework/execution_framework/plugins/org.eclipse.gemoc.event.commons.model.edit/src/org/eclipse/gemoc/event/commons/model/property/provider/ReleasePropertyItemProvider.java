/**
 */
package org.eclipse.gemoc.event.commons.model.property.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gemoc.event.commons.model.property.PropertyFactory;
import org.eclipse.gemoc.event.commons.model.property.PropertyPackage;
import org.eclipse.gemoc.event.commons.model.property.ReleaseProperty;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.event.commons.model.property.ReleaseProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ReleasePropertyItemProvider extends TemporalPropertyItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReleasePropertyItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA);
			childrenFeatures.add(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_ReleaseProperty_type");
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

		switch (notification.getFeatureID(ReleaseProperty.class)) {
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
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
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createAbstractProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createPropertyReference()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createCompositeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createEventPrecondition()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createContainerReferenceProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyBooleanAttributeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyIntegerAttributeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyStringAttributeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createAbstractProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createPropertyReference()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createCompositeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createEventPrecondition()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createContainerReferenceProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyBooleanAttributeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyIntegerAttributeProperty()));

		newChildDescriptors.add
			(createChildParameter
				(PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA,
				 PropertyFactory.eINSTANCE.createManyStringAttributeProperty()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == PropertyPackage.Literals.RELEASE_PROPERTY__LEFT_FORMULA ||
			childFeature == PropertyPackage.Literals.RELEASE_PROPERTY__RIGHT_FORMULA;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
