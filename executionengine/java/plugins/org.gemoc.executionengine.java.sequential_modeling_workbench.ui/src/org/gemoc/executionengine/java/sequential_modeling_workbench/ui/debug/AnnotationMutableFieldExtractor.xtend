package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug

import org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug.MutableFieldExtractor
import org.eclipse.emf.ecore.EObject
import java.util.List
import java.util.ArrayList
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.emf.transaction.RecordingCommand
import org.gemoc.execution.engine.core.CommandExecution
import java.text.NumberFormat
import java.text.DecimalFormat
import java.util.Map
import org.eclipse.emf.ecore.EClass
import java.util.HashMap

class AnnotationMutableFieldExtractor implements MutableFieldExtractor {

	val Map<EClass, Integer> counters = new HashMap

	override extractMutableField(EObject eObject) {

		val List<MutableField> result = new ArrayList<MutableField>()

		val allMutable = !eObject.eClass.EAnnotations.empty

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
			if (allMutable || (!prop.EAnnotations.empty)) {
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