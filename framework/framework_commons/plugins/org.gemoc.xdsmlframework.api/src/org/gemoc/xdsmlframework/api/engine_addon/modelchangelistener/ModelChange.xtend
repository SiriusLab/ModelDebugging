package org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtend.lib.annotations.Accessors

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
	@Accessors(PROTECTED_SETTER,PUBLIC_GETTER)
	Object newValue

	new(EObject changedObject, EStructuralFeature changedField, Object newValue) {
		super(changedObject)
		this.changedField = changedField
		this.newValue = newValue
	}

}

class AddElementFieldModelChange extends FieldModelChange {
	
	new(EObject changedObject, EStructuralFeature changedField, Object newValue) {
		super(changedObject, changedField, newValue)
	}
	
}

class RemoveElementFieldModelChange extends FieldModelChange {
	
	new(EObject changedObject, EStructuralFeature changedField, Object newValue) {
		super(changedObject, changedField, newValue)
	}
	
}

class ChangeElementFieldModelChange extends FieldModelChange {
	
	new(EObject changedObject, EStructuralFeature changedField, Object newValue) {
		super(changedObject, changedField, newValue)
	}
	
}
 