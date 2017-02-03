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
package fr.inria.diverse.trace.plugin.generator.codegen

import ecorext.ClassExtension
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import java.util.Collection
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class StateManagerGeneratorJava {
	
	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val TraceMMGenerationTraceability traceability
	private val Set<GenPackage> refGenPackages
	
	// Shortcuts
	private val String stateFQN
	private val String valueFQN
	
	private boolean getTracedToExeUsed = false
	
	public def String getClassName() {
		return className
	}
	
	new(String languageName, String packageQN, EPackage traceMM, TraceMMGenerationTraceability traceability, Set<GenPackage> refGenPackages) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "StateManager"
		this.packageQN = packageQN
		this.traceability = traceability
		this.refGenPackages = refGenPackages
		
		stateFQN = getJavaFQN(traceability.traceMMExplorer.getSpecificStateClass)
		valueFQN = getJavaFQN(traceability.traceMMExplorer.specificValueClass)
	}
	
	private def String getFQN(EStructuralFeature eFeature) {
		return EcoreCraftingUtil.getBaseFQN(eFeature.EContainingClass) + "." + eFeature.name
	}
	
	private def String getJavaFQN(EClassifier c) {
		return getJavaFQN(c,false)
	}
	
	private def String getJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		return EcoreCraftingUtil.getJavaFQN(c,refGenPackages,enforcePrimitiveJavaClass)
	}
	
	public def String generateCode() {
		val String code = generateStateManagerClass()
		try {
			return CodeGenUtil.formatJavaCode(code)
		} catch (Throwable t) {
			return code
		}
	}
	
	private def String stringGetterExeValue(String javaVarName, EStructuralFeature p, EClass typeToCastTo) {
		return '''
		«IF (p instanceof EReference && traceability.hasTracedClass(p.EType as EClass))»
		
		««« If many elements are in this fields, we have to cast the element with a collection
		«IF p.many»
		(Collection<? extends «getJavaFQN(p.EType,true)»>) 
		«ELSE»
		(«getJavaFQN(p.EType, true)»)
		«ENDIF»
		«getTracedToExeMethodName»(((«getJavaFQN(typeToCastTo)») «javaVarName»).«EcoreCraftingUtil.stringGetter(p)»)
		«ELSE»
		((«getJavaFQN(typeToCastTo)») «javaVarName»).«EcoreCraftingUtil.stringGetter(p)»
		«ENDIF»'''
	}

	private def Set<EClass> getConcreteSubtypesTraceClassOf(EClass tracedClass) {
		val Set<EClass> result = new HashSet()
		result.addAll(this.traceMM.eAllContents.filter(EClass).filter [ c |
			!c.abstract && c.EAllSuperTypes.contains(tracedClass)
		].toSet)
		if (!tracedClass.abstract)
			result.add(tracedClass)
		return result
	}
	
	private def String getTracedToExeMethodName() {
		getTracedToExeUsed = true
		return "getTracedToExe"
	}
	
	private def String generateImports() {
		return
				'''
					import java.util.ArrayList;
					import java.util.Collection;
					import java.util.Map;
					
					import org.eclipse.emf.ecore.EObject;
					import org.eclipse.emf.ecore.resource.Resource;
					import org.eclipse.emf.transaction.RecordingCommand;
					import org.eclipse.emf.transaction.TransactionalEditingDomain;
					import org.eclipse.emf.transaction.util.TransactionUtil;
					import org.gemoc.executionframework.engine.core.CommandExecution;
					
					import fr.inria.diverse.trace.commons.model.trace.State;
					import fr.inria.diverse.trace.gemoc.api.IStateManager;
				'''
	}
	
	private def String generateFields() {
		return
				'''
					private final Resource modelResource;
					
					private final Map<EObject, EObject> tracedToExe;
				'''
	}
	
	private def String generateConstructors() {
		return
				'''
					public «className»(Resource modelResource, Map<EObject, EObject> tracedToExe) {
						this.modelResource = modelResource;
						this.tracedToExe = tracedToExe;
					}
				'''
	}
	
	private def String generateMethods() {
		return
				'''
					@Override
					public void restoreState(State<?, ?> state) {
						if (modelResource != null && state instanceof «stateFQN») {
							try {
								final TransactionalEditingDomain ed = TransactionUtil.getEditingDomain(modelResource);
								if (ed != null) {
									final RecordingCommand command = new RecordingCommand(ed, "") {
										protected void doExecute() {
											restoreStateExecute((«stateFQN») state);
										}
									};
									CommandExecution.execute(ed, command);
								}
							} catch (Exception e) {
								throw e;
							}
						}
					}
					
					private EObject getTracedToExe(EObject tracedObject) {
						return tracedToExe.get(tracedObject);
					}
					
					private Collection<? extends EObject> getTracedToExe(Collection<? extends EObject> tracedObjects) {
						Collection<EObject> result = new ArrayList<EObject>();
						for (EObject tracedObject : tracedObjects) {
							result.add(tracedToExe.get(tracedObject));
						}
						return result;
					}
					
					@SuppressWarnings("unchecked")
					private void restoreStateExecute(«stateFQN» state) {
						for («valueFQN» value : state.getValues()) {
							final EObject parent = value.eContainer().eContainer();
							«FOR p : traceability.allMutableProperties.sortBy[FQN] SEPARATOR "else"»
							«val EReference pdimension = traceability.getDimensionRef(p)»
							«val EClass valueClass = traceability.getValueClass(p)»
							if (value instanceof «getJavaFQN(valueClass)») {
								««« Case in which we can use the "originalObject" reference and simply set its values
								«IF p.eContainer instanceof ClassExtension»
								««« We have to test at runtime be can't know at design time the type of the object containing the property
								««« The reason is that we keep the same class hierarchy in the trace. Maybe we should remove that.
								«val concreteSubTypes = getConcreteSubtypesTraceClassOf(pdimension.getEContainingClass).sortBy[name]»
								«IF concreteSubTypes.size > 1»
								«FOR concreteSubType : concreteSubTypes»
								if (parent instanceof «getJavaFQN(concreteSubType)») {
									«val Collection<EReference> origRefs = traceability.getRefs_originalObject(concreteSubType).sortBy[name]»
									«getJavaFQN(concreteSubType)» parent_cast = («getJavaFQN(concreteSubType)») parent;
									«IF !origRefs.isEmpty»
									«val EReference origRef = origRefs.get(0)»
									«IF p.many»
									«EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)» originalObject = («EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)») parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»;
									originalObject.«EcoreCraftingUtil.stringGetter(p)».clear();
									originalObject.«EcoreCraftingUtil.stringGetter(p)».addAll(«stringGetterExeValue("value", p, valueClass)»);
									«ELSE»
									«getJavaFQN(p.EType)» toset = «stringGetterExeValue("value", p, valueClass)»;
									«getJavaFQN(p.EType)» current = ((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringGetter(p)»;
									if (current != toset) {
										((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringSetter(p, "toset", refGenPackages)»;
									}
									«ENDIF»
									«ENDIF»
								}
								«ENDFOR»
								«ELSEIF concreteSubTypes.size == 1»
								«val concreteSubType = concreteSubTypes.head»
								«val Collection<EReference> origRefs = traceability.getRefs_originalObject(concreteSubType).sortBy[name]»
								«getJavaFQN(concreteSubType)» parent_cast = («getJavaFQN(concreteSubType)») parent;
								«IF !origRefs.isEmpty»
								«val EReference origRef = origRefs.get(0)»
								«IF p.many»
								«EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)» originalObject = («EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)») parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»;
								originalObject.«EcoreCraftingUtil.stringGetter(p)».clear();
								originalObject.«EcoreCraftingUtil.stringGetter(p)».addAll(«stringGetterExeValue("value", p, valueClass)»);
								«ELSE»
								«getJavaFQN(p.EType)» toset = «stringGetterExeValue("value", p, valueClass)»;
								«getJavaFQN(p.EType)» current = ((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringGetter(p)»;
								if (current != toset) {
									((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringSetter(p, "toset", refGenPackages)»;
								}
								«ENDIF»
								«ENDIF»
								«ENDIF»
								««« Case in which we have to recreate/restore execution objects in the model
								«ELSEIF p.eContainer instanceof EClass»
								«getJavaFQN(p.EContainingClass)» exeObject = («getJavaFQN(p.EContainingClass)») «getTracedToExeMethodName»(parent);
								«IF p.many»
								exeObject.«EcoreCraftingUtil.stringGetter(p)».clear();
								«IF p instanceof EReference»
								exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) «getTracedToExeMethodName»(((«getJavaFQN(valueClass)») value).«EcoreCraftingUtil.stringGetter(p)»));
								«ELSE»
								exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) ((«getJavaFQN(valueClass)») value).«EcoreCraftingUtil.stringGetter(p)»);
								«ENDIF»
								«ELSE»
								exeObject.«EcoreCraftingUtil.stringSetter(p, stringGetterExeValue("value", p, valueClass), refGenPackages)»;
								«ENDIF»
								«ENDIF»
							}
							«ENDFOR»
						}
					}
					
«««					@SuppressWarnings("unchecked")
«««					private void restoreStateExecute(«stateFQN» state) {
«««						«FOR p : traceability.allMutableProperties.sortBy[FQN]»
«««						«val EReference pdimension = traceability.getDimensionRef(p)»
«««						«val EClass stateClass = traceability.getValueClass(p)»
«««						for («valueFQN» value : state.«EcoreCraftingUtil.stringGetter(TraceMMStrings.ref_createGlobalToState(stateClass))») {
«««							final EObject parent = value.eContainer().eContainer();
«««							««« Case in which we can use the "originalObject" reference and simply set its values
«««							«IF p.eContainer instanceof ClassExtension»
«««							««« We have to test at runtime be can't know at design time the type of the object containing the property
«««							««« The reason is that we keep the same class hierarchy in the trace. Maybe we should remove that.
«««							«FOR concreteSubType : getConcreteSubtypesTraceClassOf(pdimension.getEContainingClass).sortBy[name]»
«««							if (parent instanceof «getJavaFQN(concreteSubType)») {
«««								«val Collection<EReference> origRefs = traceability.getRefs_originalObject(concreteSubType).sortBy[name]»
«««								«getJavaFQN(concreteSubType)» parent_cast = («getJavaFQN(concreteSubType)») parent;
«««								«IF !origRefs.isEmpty»
«««								«val EReference origRef = origRefs.get(0)»
«««								«IF p.many»
«««								«EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)» originalObject = («EcoreCraftingUtil.getJavaFQN(traceability.getExeClass(pdimension.getEContainingClass),refGenPackages)») parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»;
«««								originalObject.«EcoreCraftingUtil.stringGetter(p)».clear();
«««								originalObject.«EcoreCraftingUtil.stringGetter(p)».addAll(«stringGetterExeValue("value",p)»);
«««								«ELSE»
«««								«getJavaFQN(p.EType)» toset = «stringGetterExeValue("value", p)»;
«««								«getJavaFQN(p.EType)» current = ((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringGetter(p)»;
«««								if (current != toset) {
«««									((«getJavaFQN((p.eContainer as ClassExtension).extendedExistingClass)»)parent_cast.«EcoreCraftingUtil.stringGetter(origRef)»).«EcoreCraftingUtil.stringSetter(p, "toset", refGenPackages)»;
«««								}
«««								«ENDIF»
«««								«ENDIF»
«««							}
«««							«ENDFOR»
«««							««« Case in which we have to recreate/restore execution objects in the model
«««							«ELSEIF p.eContainer instanceof EClass»
«««							«getJavaFQN(p.EContainingClass)» exeObject = («getJavaFQN(p.EContainingClass)») «getTracedToExeMethodName»(parent);
«««							«IF p.many»
«««							exeObject.«EcoreCraftingUtil.stringGetter(p)».clear();
«««							«IF p instanceof EReference»
«««							exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) «getTracedToExeMethodName»(value.«EcoreCraftingUtil.stringGetter(p)»));
«««							«ELSE»
«««							exeObject.«EcoreCraftingUtil.stringGetter(p)».addAll((Collection<? extends «getJavaFQN(p.EType,true)»>) value.«EcoreCraftingUtil.stringGetter(p)»);
«««							«ENDIF»
«««							«ELSE»
«««							exeObject.«EcoreCraftingUtil.stringSetter(p, stringGetterExeValue("value",p), refGenPackages)»;
«««							«ENDIF»
«««							«ENDIF»
«««						}
«««						«ENDFOR»
«««					}
				'''
	}
	
	private def String generateStateManagerClass() {
		return
				'''
					package «packageQN»;
					
					«generateImports»
										
					public class «className» implements IStateManager<State<?,?>> {
						
						«generateFields»
						«generateConstructors»
						«generateMethods»
					}
				'''
	}
	
}