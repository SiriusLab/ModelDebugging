package org.gemoc.sequential_addons.stategraph.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gemoc.sequential_addons.stategraph.logic.DirectedGraph.Edge;
import org.gemoc.sequential_addons.stategraph.logic.StateGraph;
import org.gemoc.sequential_addons.stategraph.logic.StateVertex;
import org.gemoc.sequential_addons.stategraph.logic.alg.IHullAlgorithm;
import org.gemoc.sequential_addons.stategraph.logic.alg.JarvisMarch;
import org.gemoc.sequential_addons.stategraph.logic.alg.JohnsonSimpleCycles;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class StateGraphRenderer extends Pane {

	private StateGraph stateGraph;
	
	final private Map<StateVertex,Circle> nodeToShape = new HashMap<>();
	
	final private Map<Edge<StateVertex>,Shape> edgeToShape = new HashMap<>();
	
	final private List<Polygon> hulls = new ArrayList<>();

	private double mouseX;

	private double mouseY;
	
	public StateGraphRenderer(StateGraph stateGraph) {
		this.stateGraph = stateGraph;
		this.stateGraph.setUpdateCommand((clear, currentVertex)->render(clear, currentVertex));
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
	
	public void setStateGraph(StateGraph stateGraph) {
		this.stateGraph = stateGraph;
		this.stateGraph.setUpdateCommand((clear, currentVertex)->render(clear, currentVertex));
	}

	private Circle createVertexShape(StateVertex node) {
		final double radius = Math.log(1 + (stateGraph.getIncomingEdges(node).size() + stateGraph.getOutgoingEdges(node).size())) * 5;
		Circle circle = new Circle(radius);
		
		final int n = stateGraph.getVertice().indexOf(node);
		if (n / 5 == 1) {
			circle.setTranslateX(500 - n % 5 * 100);
		} else {
			circle.setTranslateX(100 + n % 5 * 100);
		}
		circle.setTranslateY(100 + n / 5 * 100);
		circle.setFill(Color.SLATEBLUE);
		
		circle.setOnMousePressed(event -> {
			mouseX = event.getX();
			mouseY = event.getY();
		});

		circle.setOnMouseDragged(event -> {
			double deltaX = event.getX() - mouseX;
			double deltaY = event.getY() - mouseY;
			circle.setTranslateX(circle.getTranslateX() + deltaX);
			circle.setTranslateY(circle.getTranslateY() + deltaY);
		});
		
		final String s = node.getTooltip();
		final Tooltip t = new Tooltip(s);
		Tooltip.install(circle, t);
		node.setOnTooltipUpdateCommand(text -> Platform.runLater(() -> t.setText(text)));
		
		return circle;
	}
	
	private Shape createEdgeShape(Edge<StateVertex> edge, Circle startVertex, Circle endVertex) {
		final Path path = new Path();
		path.setStrokeWidth(1.5);
		path.setStroke(Color.BLUE);
		final MoveTo moveTo = new MoveTo();
		moveTo.xProperty().bind(startVertex.translateXProperty());
		moveTo.yProperty().bind(startVertex.translateYProperty());
		
		final DoubleBinding a = new DoubleBinding() {
			private final DoubleBinding dX = startVertex.translateXProperty().subtract(endVertex.translateXProperty());
			private final DoubleBinding dY = startVertex.translateYProperty().subtract(endVertex.translateYProperty());
			{
				super.bind(dX, dY);
			}
			@Override
			protected double computeValue() {
				return Math.atan2(dY.get(), dX.get());
			}
		};

		final DoubleBinding x = new DoubleBinding() {
			private final DoubleProperty cX = endVertex.translateXProperty();
			private final DoubleProperty r = endVertex.radiusProperty();
			{
				super.bind(cX, r, a);
			}
			@Override
			protected double computeValue() {
				return cX.get() + Math.cos(a.get()) * r.get();
			}
		};
		
		final DoubleBinding y = new DoubleBinding() {
			private final DoubleProperty cY = endVertex.translateYProperty();
			private final DoubleProperty r = endVertex.radiusProperty();
			{
				super.bind(cY, r, a);
			}
			@Override
			protected double computeValue() {
				return cY.get() + Math.sin(a.get()) * r.get();
			}
		};
		
		final LineTo lineTo = new LineTo();
		lineTo.xProperty().bind(x);
		lineTo.yProperty().bind(y);
		
		final DoubleBinding aX1 = new DoubleBinding() {
			{
				super.bind(a,x);
			}
			@Override
			protected double computeValue() {
				return x.get() + Math.cos(a.get() - Math.PI / 6) * 5;
			}
		};
		
		final DoubleBinding aY1 = new DoubleBinding() {
			{
				super.bind(a,y);
			}
			@Override
			protected double computeValue() {
				return y.get() + Math.sin(a.get() - Math.PI / 6) * 5;
			}
		};
		
		final DoubleBinding aX2 = new DoubleBinding() {
			{
				super.bind(a,x);
			}
			@Override
			protected double computeValue() {
				return x.get() + Math.cos(a.get() + Math.PI / 6) * 5;
			}
		};
		
		final DoubleBinding aY2 = new DoubleBinding() {
			{
				super.bind(a,y);
			}
			@Override
			protected double computeValue() {
				return y.get() + Math.sin(a.get() + Math.PI / 6) * 5;
			}
		};

		final LineTo lineTo2 = new LineTo();
		lineTo2.xProperty().bind(aX1);
		lineTo2.yProperty().bind(aY1);
		
		final MoveTo moveTo2 = new MoveTo();
		moveTo2.xProperty().bind(x);
		moveTo2.yProperty().bind(y);
		
		final LineTo lineTo3 = new LineTo();
		lineTo3.xProperty().bind(aX2);
		lineTo3.yProperty().bind(aY2);
		
		path.getElements().addAll(moveTo,lineTo,lineTo2,moveTo2,lineTo3);
		
		return path;
	}
	
	private boolean changed = false;
	private StateVertex currentVertex = null;
	
	private void render(boolean clear, StateVertex currentVertex) {
		this.currentVertex = currentVertex;
		Platform.runLater(() -> {
			if (clear) {
				getChildren().clear();
				nodeToShape.clear();
				edgeToShape.clear();
			}
			
			final Map<Edge<StateVertex>, StateVertex> workList = new HashMap<>();
			final List<Shape> vertexShapesToAdd = new ArrayList<>();
			final List<Shape> edgeShapesToAdd = new ArrayList<>();
			final List<StateVertex> vertice = stateGraph.getVertice();
			final List<StateVertex> verticeToRemove = new ArrayList<>(nodeToShape.keySet());
			verticeToRemove.removeAll(vertice);
			
			if (!verticeToRemove.isEmpty()) {
				changed = true;
			}
			
			verticeToRemove.forEach(n->{
				final Shape vertexShape = nodeToShape.get(n);
				nodeToShape.remove(n);
				if (vertexShape != null) {
					getChildren().remove(vertexShape);
				}
			});
			
			for (StateVertex v : vertice) {
				Circle vertexShape = nodeToShape.get(v);
				if (vertexShape == null) {
					changed = true;
					vertexShape = createVertexShape(v);
					nodeToShape.put(v, vertexShape);
					vertexShapesToAdd.add(vertexShape);
				} else {
					final double radius = Math.log(1 + (stateGraph.getIncomingEdges(v).size() + stateGraph.getOutgoingEdges(v).size())) * 5;
					vertexShape.setRadius(radius);
				}
				if (currentVertex == v) {
					vertexShape.setFill(Color.CORAL);
				} else {
					vertexShape.setFill(Color.SLATEBLUE);
				}
				
				for (Edge<StateVertex> e : stateGraph.getOutgoingEdges(v)) {
					workList.put(e, v);
				}
			}
			
			final List<Edge<StateVertex>> edgesToRemove = new ArrayList<>(edgeToShape.keySet());
			
			for (StateVertex v : vertice) {
				for (Edge<StateVertex> e : stateGraph.getIncomingEdges(v)) {
					final StateVertex w = workList.get(e);
					if (w != null && v != w) {
						Shape edgeShape = edgeToShape.get(e);
						if (edgeShape == null) {
							changed = true;
							edgeShape = createEdgeShape(e, nodeToShape.get(w), nodeToShape.get(v));
							edgeToShape.put(e, edgeShape);
							edgeShapesToAdd.add(edgeShape);
						}
						edgesToRemove.remove(e);
					}
				}
			}
			
			if (!edgesToRemove.isEmpty()) {
				changed = true;
			}
			
			edgesToRemove.forEach(e->{
				final Shape edgeShape = edgeToShape.get(e);
				edgeToShape.remove(e);
				if (edgeShape != null) {
					getChildren().remove(edgeShape);
				}
			});
			
			getChildren().addAll(0,edgeShapesToAdd);
			getChildren().addAll(vertexShapesToAdd);
			
			if (!isCycleColorationEnabled) {
				getChildren().removeAll(hulls);
				hulls.clear();
			} else if (changed) {
				getChildren().removeAll(hulls);
				hulls.clear();
				
				final JohnsonSimpleCycles<StateVertex> algo = new JohnsonSimpleCycles<>(stateGraph);
				final List<List<StateVertex>> cycles = algo.findSimpleCycles();
				final IHullAlgorithm hullAlgo = new JarvisMarch();
				
				if (!cycles.isEmpty()) {
					double hueInterval = 360. / (double) cycles.size();
					
					for (int i = 0; i < cycles.size(); i++) {
						Polygon hull = new Polygon();
						Paint p = Color.hsb(i * hueInterval, 0.75, 0.70, 0.25);
						hull.setFill(p);
						hull.setStroke(p);
						hull.setStrokeWidth(20);
						hull.setStrokeLineJoin(StrokeLineJoin.ROUND);
						hull.setStrokeType(StrokeType.OUTSIDE);
						getChildren().add(0, hull);
						hulls.add(hull);
						List<Node> nodes = cycles.get(i).stream().map(v -> (Node) nodeToShape.get(v))
								.collect(Collectors.toList());
						
						updateHull(hull, nodes, hullAlgo);
						
						final ChangeListener<Number> listener = (_0,_1,_2) -> {
							updateHull(hull, nodes, hullAlgo);
						};
						
						for (Node n : nodes) {
							n.translateXProperty().addListener(listener);
							n.translateYProperty().addListener(listener);
						}
					}
				}
				
				changed = false;
			}
		});
	}
	
	private void updateHull(Polygon hull, List<Node> nodes, IHullAlgorithm hullAlgo) {
		List<double[]> points = new ArrayList<>();
		
		for (Node n : nodes) {
			points.add(new double[] {n.getTranslateX(), n.getTranslateY()});
		}
		
		List<Double> coordinates = new ArrayList<>();
		if (points.size() <= 3) {
			for (double[] point : points) {
				coordinates.add(point[0]);
				coordinates.add(point[1]);
			}
		} else {
			for (double[] point : hullAlgo.convexHull(points)) {
				coordinates.add(point[0]);
				coordinates.add(point[1]);
			}
		}
		
		hull.getPoints().clear();
		hull.getPoints().addAll(coordinates);
	}

	private boolean isCycleColorationEnabled = false;
	
	public void setCycleColorationEnabled(boolean checked) {
		if (isCycleColorationEnabled != checked) {
			isCycleColorationEnabled = checked;
			changed = true;
			render(false, currentVertex);
		}
	}
}
