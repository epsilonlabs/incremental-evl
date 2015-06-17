package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;

import java.util.Iterator;

/**
 * Implementation of {@link TraceGraph} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientTraceGraph implements TraceGraph<OrientGraph> {

	private final FramedGraph<OrientGraph> framedGraph;
	private final OrientGraph baseGraph;

	OrientTraceGraph(OrientGraph baseGraph) {
		this.baseGraph = baseGraph;
		FramedGraphFactory fgf = new FramedGraphFactory();
		this.framedGraph = fgf.create(this.baseGraph);
	}

	@Override
	public TConstraint getConstraint(String name) {
		Iterator<TConstraint> it = this.framedGraph.getVertices(
				TConstraint.NAME, name, TConstraint.class).iterator();
		if (it.hasNext())
			return it.next();

		final TConstraint constraint = this.addVertex(TConstraint.TRACE_TYPE,
				TConstraint.class);
		constraint.setName(name);
		this.baseGraph.commit();
		return constraint;
	}

	@Override
	public Iterable<TConstraint> getAllConstraints() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TConstraint.TRACE_TYPE),
				TConstraint.class);
	}

	@Override
	public TScope getScope(TConstraint constraint, TElement element) {
		final TScope scope = this.addVertex(TScope.TRACE_TYPE, TScope.class);
		scope.setConstraint(constraint);
		scope.setRootElement(element);
		this.baseGraph.commit();
		return scope;
	}

	@Override
	public Iterable<TScope> getAllScopes() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TScope.TRACE_TYPE),
				TScope.class);
	}

	@Override
	public TElement getElement(String elementId) {
		Iterator<TElement> it = this.framedGraph.getVertices(
				TElement.ELEMENT_ID, elementId, TElement.class).iterator();
		if (it != null && it.hasNext())
			return it.next();

		// Element does not exist, create and return
		TElement element = this.addVertex(TElement.TRACE_TYPE, TElement.class);
		element.setElementId(elementId);
		this.baseGraph.commit();
		return element;
	}

	@Override
	public Iterable<TElement> getAllElements() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TElement.TRACE_TYPE),
				TElement.class);
	}

	@Override
	public TProperty getProperty(String name, TElement owner) {
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(owner.asVertex()).out(TOwns.TRACE_TYPE)
				.has(TProperty.NAME, name).cast(Vertex.class);

		if (p.hasNext()) {
			return this.framedGraph.frame(p.next(), TProperty.class);
		}

		TProperty property = this.addVertex(TProperty.TRACE_TYPE,
				TProperty.class);
		property.setOwner(owner);
		property.setName(name);
		this.baseGraph.commit();
		return property;
	}

	@Override
	public Iterable<TProperty> getProperties() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TProperty.TRACE_TYPE),
				TProperty.class);
	}

	@Override
	public OrientGraph getBaseGraph() {
		return this.baseGraph;
	}

	@Override
	public boolean isOpen() {
		return !this.baseGraph.isClosed();
	}

	@Override
	public void close() {
		this.baseGraph.shutdown();
	}

	private <F> F addVertex(String traceType, Class<F> clazz) {
		return this.framedGraph.addVertex(String.format("class:%s", traceType),
				clazz);
	}

}
