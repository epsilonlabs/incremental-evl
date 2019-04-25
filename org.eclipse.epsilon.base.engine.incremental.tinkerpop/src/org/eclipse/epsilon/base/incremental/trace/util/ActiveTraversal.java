package org.eclipse.epsilon.base.incremental.trace.util;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.Computer;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.process.remote.RemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.Bytecode;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategies;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategy;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/** 
 * An autocloseable graph traversal source for use in try with resources
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ActiveTraversal implements AutoCloseable, TraversalSource {

	private final GraphTraversalSource gts;
	
	public ActiveTraversal(GraphTraversalSource gts) {
		super();
		this.gts = gts.clone();
	}

	@Override
	public void close() throws Exception {
		gts.close();
	}

	public int hashCode() {
		return gts.hashCode();
	}

	public Optional<Class> getAnonymousTraversalClass() {
		return gts.getAnonymousTraversalClass();
	}

	public boolean equals(Object obj) {
		return gts.equals(obj);
	}

	public TraversalStrategies getStrategies() {
		return gts.getStrategies();
	}

	public Graph getGraph() {
		return gts.getGraph();
	}

	public Bytecode getBytecode() {
		return gts.getBytecode();
	}

	public GraphTraversalSource clone() {
		return gts.clone();
	}

	public GraphTraversalSource withStrategies(TraversalStrategy... traversalStrategies) {
		return gts.withStrategies(traversalStrategies);
	}

	public GraphTraversalSource withoutStrategies(Class<? extends TraversalStrategy>... traversalStrategyClasses) {
		return gts.withoutStrategies(traversalStrategyClasses);
	}

	public GraphTraversalSource withComputer(Computer computer) {
		return gts.withComputer(computer);
	}

	public GraphTraversalSource withComputer(Class<? extends GraphComputer> graphComputerClass) {
		return gts.withComputer(graphComputerClass);
	}

	public GraphTraversalSource withComputer() {
		return gts.withComputer();
	}

	public <A> GraphTraversalSource withSideEffect(String key, Supplier<A> initialValue, BinaryOperator<A> reducer) {
		return gts.withSideEffect(key, initialValue, reducer);
	}

	public <A> GraphTraversalSource withSideEffect(String key, A initialValue, BinaryOperator<A> reducer) {
		return gts.withSideEffect(key, initialValue, reducer);
	}

	public <A> GraphTraversalSource withSideEffect(String key, A initialValue) {
		return gts.withSideEffect(key, initialValue);
	}

	public <A> GraphTraversalSource withSideEffect(String key, Supplier<A> initialValue) {
		return gts.withSideEffect(key, initialValue);
	}

	public <A> GraphTraversalSource withSack(Supplier<A> initialValue, UnaryOperator<A> splitOperator,
			BinaryOperator<A> mergeOperator) {
		return gts.withSack(initialValue, splitOperator, mergeOperator);
	}

	public <A> GraphTraversalSource withSack(A initialValue, UnaryOperator<A> splitOperator,
			BinaryOperator<A> mergeOperator) {
		return gts.withSack(initialValue, splitOperator, mergeOperator);
	}

	public <A> GraphTraversalSource withSack(A initialValue) {
		return gts.withSack(initialValue);
	}

	public <A> GraphTraversalSource withSack(Supplier<A> initialValue) {
		return gts.withSack(initialValue);
	}

	public <A> GraphTraversalSource withSack(Supplier<A> initialValue, UnaryOperator<A> splitOperator) {
		return gts.withSack(initialValue, splitOperator);
	}

	public <A> GraphTraversalSource withSack(A initialValue, UnaryOperator<A> splitOperator) {
		return gts.withSack(initialValue, splitOperator);
	}

	public <A> GraphTraversalSource withSack(Supplier<A> initialValue, BinaryOperator<A> mergeOperator) {
		return gts.withSack(initialValue, mergeOperator);
	}

	public <A> GraphTraversalSource withSack(A initialValue, BinaryOperator<A> mergeOperator) {
		return gts.withSack(initialValue, mergeOperator);
	}

	public GraphTraversalSource withBulk(boolean useBulk) {
		return gts.withBulk(useBulk);
	}

	public GraphTraversalSource withPath() {
		return gts.withPath();
	}

	public GraphTraversalSource withRemote(Configuration conf) {
		return gts.withRemote(conf);
	}

	public GraphTraversalSource withRemote(String configFile) throws Exception {
		return gts.withRemote(configFile);
	}

	public GraphTraversalSource withRemote(RemoteConnection connection) {
		return gts.withRemote(connection);
	}

	public GraphTraversal<Vertex, Vertex> addV(String label) {
		return gts.addV(label);
	}

	public GraphTraversal<Vertex, Vertex> addV(Traversal<?, String> vertexLabelTraversal) {
		return gts.addV(vertexLabelTraversal);
	}

	public GraphTraversal<Vertex, Vertex> addV() {
		return gts.addV();
	}

	public GraphTraversal<Edge, Edge> addE(String label) {
		return gts.addE(label);
	}

	public GraphTraversal<Edge, Edge> addE(Traversal<?, String> edgeLabelTraversal) {
		return gts.addE(edgeLabelTraversal);
	}

	public <S> GraphTraversal<S, S> inject(S... starts) {
		return gts.inject(starts);
	}

	public GraphTraversal<Vertex, Vertex> V(Object... vertexIds) {
		return gts.V(vertexIds);
	}

	public GraphTraversal<Edge, Edge> E(Object... edgesIds) {
		return gts.E(edgesIds);
	}

	public Transaction tx() {
		return gts.tx();
	}

	public String toString() {
		return gts.toString();
	}

}
