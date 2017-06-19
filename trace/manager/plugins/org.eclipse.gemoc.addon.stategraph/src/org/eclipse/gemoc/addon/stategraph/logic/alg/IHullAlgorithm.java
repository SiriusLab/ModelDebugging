package org.eclipse.gemoc.addon.stategraph.logic.alg;

import java.util.List;

public interface IHullAlgorithm {
	List<double[]> convexHull(List<double[]> points);
}
