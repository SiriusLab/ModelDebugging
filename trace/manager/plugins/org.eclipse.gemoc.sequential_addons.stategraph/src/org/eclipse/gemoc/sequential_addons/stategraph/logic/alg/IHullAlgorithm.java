package org.gemoc.sequential_addons.stategraph.logic.alg;

import java.util.List;

public interface IHullAlgorithm {
	List<double[]> convexHull(List<double[]> points);
}
