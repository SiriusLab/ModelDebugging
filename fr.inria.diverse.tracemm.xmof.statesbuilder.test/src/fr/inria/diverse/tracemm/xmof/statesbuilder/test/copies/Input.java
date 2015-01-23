package fr.inria.diverse.tracemm.xmof.statesbuilder.test.copies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

public class Input {
	
	private List<Resource> inputResourcesLeft = new ArrayList<>();
	private List<Resource> inputResourcesRight = new ArrayList<>();

	public void addInputResources(Resource inputResourceLeft, Resource inputResourceRight) {
		inputResourcesLeft.add(inputResourceLeft);
		inputResourcesRight.add(inputResourceRight);
	}
	
	public List<Resource> getInputResourcesLeft() {
		return inputResourcesLeft;
	}
	
	public List<Resource> getInputResourcesRight() {
		return inputResourcesRight;
	}
	
}
