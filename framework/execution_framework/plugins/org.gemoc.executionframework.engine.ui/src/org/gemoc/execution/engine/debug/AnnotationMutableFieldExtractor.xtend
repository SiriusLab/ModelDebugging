package org.gemoc.execution.engine.debug

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.gemoc.execution.engine.core.CommandExecution
import org.gemoc.execution.engine.debug.IMutableFieldExtractor
import org.gemoc.execution.engine.debug.MutableField
import org.gemoc.execution.sequential.java.commons.DynamicAnnotationHelper

class AnnotationMutableFieldExtractor implements IMutableFieldExtractor {

	val Map<EClass, Integer> counters = new HashMap

	override extractMutableField(EObject eObject) {

		val List<MutableField> result = new ArrayList<MutableField>()

		val idProp = eObject.eClass.EIDAttribute
		val String objectName = if (idProp != null) {
				val id = eObject.eGet(idProp);
				if (id != null) {
					val NumberFormat formatter = new DecimalFormat("00");
					val String idString = if(id instanceof Integer) formatter.format((id as Integer)) else id.toString;
					eObject.eClass.name + "_" + idString // "returned" value 
				} else {
					if (!counters.containsKey(eObject.eClass)) {
						counters.put(eObject.eClass, 0)
					}
					val Integer counter = counters.get(eObject.eClass)
					counters.put(eObject.eClass, counter + 1)
					eObject.eClass.name + "_" + counter
				}

			} else
				eObject.toString

		for (prop : eObject.eClass.EAllStructuralFeatures) {
			if (DynamicAnnotationHelper.isDynamic(prop)) {
				val mut = new MutableField(
					/* name    */ objectName + "_" + prop.name,
					/* eObject */ eObject,
					/* mutProp */ prop,
					/* getter  */ [eObject.eGet(prop)],
					/* setter  */ [ o |

						val ed = TransactionUtil.getEditingDomain(eObject.eResource);
						var RecordingCommand command = new RecordingCommand(ed,
							"Setting value " + o + " in " + eObject + " from the debugger") {
							protected override void doExecute() {
								eObject.eSet(prop, o)
							}
						};
						CommandExecution.execute(ed, command);

					]
				)
				result.add(mut)

			}
		}
		return result
	}

}