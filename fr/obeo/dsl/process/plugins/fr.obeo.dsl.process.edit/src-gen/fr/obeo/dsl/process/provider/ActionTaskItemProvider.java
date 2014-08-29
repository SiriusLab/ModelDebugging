/**
 */
package fr.obeo.dsl.process.provider;

import fr.obeo.dsl.process.ActionTask;
import fr.obeo.dsl.process.ProcessFactory;
import fr.obeo.dsl.process.ProcessPackage;

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

/**
 * This is the item provider adapter for a {@link fr.obeo.dsl.process.ActionTask} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ActionTaskItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public ActionTaskItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addPrecedingTasksPropertyDescriptor(object);
			addFollowingTasksPropertyDescriptor(object);
			addMultipleExecutionPropertyDescriptor(object);
			addWrittenVariablesPropertyDescriptor(object);
			addObservedVariablesPropertyDescriptor(object);
			addInstanceClassNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(), getString("_UI_Task_name_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Task_name_feature", "_UI_Task_type"),
				ProcessPackage.Literals.TASK__NAME, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(), getString("_UI_Task_id_feature"), getString(
				"_UI_PropertyDescriptor_description", "_UI_Task_id_feature", "_UI_Task_type"),
				ProcessPackage.Literals.TASK__ID, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(), getString("_UI_Task_description_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Task_description_feature",
						"_UI_Task_type"), ProcessPackage.Literals.TASK__DESCRIPTION, true, true, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Preceding Tasks feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPrecedingTasksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(), getString("_UI_Task_precedingTasks_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Task_precedingTasks_feature",
						"_UI_Task_type"), ProcessPackage.Literals.TASK__PRECEDING_TASKS, true, false, true,
				null, null, null));
	}

	/**
	 * This adds a property descriptor for the Following Tasks feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFollowingTasksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(), getString("_UI_Task_followingTasks_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Task_followingTasks_feature",
						"_UI_Task_type"), ProcessPackage.Literals.TASK__FOLLOWING_TASKS, true, false, true,
				null, null, null));
	}

	/**
	 * This adds a property descriptor for the Instance Class Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addInstanceClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ActionTask_instanceClassName_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_ActionTask_instanceClassName_feature",
						"_UI_ActionTask_type"), ProcessPackage.Literals.ACTION_TASK__INSTANCE_CLASS_NAME,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Written Variables feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWrittenVariablesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ActionTask_writtenVariables_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_ActionTask_writtenVariables_feature",
						"_UI_ActionTask_type"), ProcessPackage.Literals.ACTION_TASK__WRITTEN_VARIABLES, true,
				false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Observed Variables feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addObservedVariablesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ActionTask_observedVariables_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_ActionTask_observedVariables_feature",
						"_UI_ActionTask_type"), ProcessPackage.Literals.ACTION_TASK__OBSERVED_VARIABLES,
				true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Multiple Execution feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMultipleExecutionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory)
				.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ActionTask_multipleExecution_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_ActionTask_multipleExecution_feature",
						"_UI_ActionTask_type"), ProcessPackage.Literals.ACTION_TASK__MULTIPLE_EXECUTION,
				true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for
	 * an {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand}
	 * or {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ProcessPackage.Literals.TASK__PRECONDITION);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ActionTask.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ActionTask"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ActionTask)object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ActionTask_type")
				: getString("_UI_ActionTask_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and
	 * by creating a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ActionTask.class)) {
			case ProcessPackage.ACTION_TASK__NAME:
			case ProcessPackage.ACTION_TASK__ID:
			case ProcessPackage.ACTION_TASK__DESCRIPTION:
			case ProcessPackage.ACTION_TASK__MULTIPLE_EXECUTION:
			case ProcessPackage.ACTION_TASK__INSTANCE_CLASS_NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false,
						true));
				return;
			case ProcessPackage.ACTION_TASK__PRECONDITION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true,
						false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be
	 * created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createAllDone()));

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createAnyDone()));

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createOneDone()));

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.TASK__PRECONDITION,
				ProcessFactory.eINSTANCE.createOr()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ProcessEditPlugin.INSTANCE;
	}

}
