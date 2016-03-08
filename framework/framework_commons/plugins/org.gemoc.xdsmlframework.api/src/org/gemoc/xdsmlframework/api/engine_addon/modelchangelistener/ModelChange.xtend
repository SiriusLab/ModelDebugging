package org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List
import org.eclipse.emf.common.notify.Notification

abstract class ModelChange {

	@Accessors(PROTECTED_SETTER,PUBLIC_GETTER)
	EObject changedObject

	new(EObject changedObject) {
		this.changedObject = changedObject
	}

}

class NewObjectModelChange extends ModelChange {

	new(EObject changedObject) {
		super(changedObject)
	}

}

class RemovedObjectModelChange extends ModelChange {

	new(EObject changedObject) {
		super(changedObject)
	}

}

abstract class FieldModelChange extends ModelChange {

	@Accessors(PROTECTED_SETTER,PUBLIC_GETTER)
	EStructuralFeature changedField

	new(EObject changedObject, EStructuralFeature changedField) {
		super(changedObject)
		this.changedField = changedField

	}

}

class PotentialCollectionFieldModelChange extends FieldModelChange {

	@Accessors(PROTECTED_SETTER,PUBLIC_GETTER)
	Object emfNotifications

	new(EObject changedObject, EStructuralFeature changedField, List<Notification> notifications) {
		super(changedObject, changedField)
		this.emfNotifications = notifications
	}

}

class ChangeElementFieldModelChange extends FieldModelChange {

	@Accessors(PROTECTED_SETTER,PUBLIC_GETTER)
	Object newValue

	new(EObject changedObject, EStructuralFeature changedField, Object newValue) {
		super(changedObject, changedField)
		this.newValue = newValue
	}

}
 