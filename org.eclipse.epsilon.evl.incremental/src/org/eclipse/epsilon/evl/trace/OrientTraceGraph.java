package org.eclipse.epsilon.evl.trace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

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
	public TContext getContext(String name) {
		Iterator<TContext> it = this.framedGraph.getVertices(
				TContext.NAME, name, TContext.class).iterator();
		if (it.hasNext())
			return it.next();

		final TContext context = this.addVertex(TContext.TRACE_TYPE,
				TContext.class);
		context.setName(name);
		this.baseGraph.commit();
		return context;
	}
	
	@Override
	public Iterable<TContext> getAllContexts() {
		return this.getAll(TContext.TRACE_TYPE, TContext.class);
	}

	@Override
	public TConstraint getConstraint(String name, TContext context) {
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(context.asVertex())
		.inE(TIn.TRACE_TYPE)
		.outV()
		.has(TConstraint.NAME, name);
		
		if (p.hasNext()) return this.framedGraph.frame(p.next(), TConstraint.class);

		final TConstraint constraint = this.addVertex(TConstraint.TRACE_TYPE,
				TConstraint.class);
		constraint.setName(name);
		constraint.addContext(context);
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
	public TScope getScope(final TConstraint constraint, TElement element) {
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(element.asVertex())
		.outE(TRootOf.TRACE_TYPE)
		.inV()
		.as("scopes")
		.inE(TEvaluates.TRACE_TYPE)
		.outV()
		.filter(new PipeFunction<Vertex, Boolean>() {
			@Override
			public Boolean compute(Vertex vertex) {
				return vertex.getProperty(TConstraint.NAME).equals(constraint.getName());
			}
		})
		.back("scopes");
		
		if (p.hasNext()) return this.framedGraph.frame(p.next(), TScope.class);
		
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
	public Iterable<TScope> getScopesIn(TElement element) {
		List<Vertex> results = new ArrayList<Vertex>();
		if (element == null) {
			return this.framedGraph.frameVertices(results, TScope.class);
		}
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>(element.asVertex());
		p.out(TOwns.TRACE_TYPE).in(TAccesses.TRACE_TYPE).aggregate(results).next();
		return this.framedGraph.frameVertices(results, TScope.class);
	}
	
	@Override
	public Iterable<TScope> getScopesIn(String elementId) {
		return this.getScopesIn(this.getElement(elementId, false));
	}

	@Override
	public TElement getElement(String elementId) {
		return getElement(elementId, true);
	}
	
	@Override
	public TElement getElement(String elementId, boolean create) {
		Iterator<TElement> it = this.framedGraph.getVertices(
				TElement.ELEMENT_ID, elementId, TElement.class).iterator();
		if (it != null && it.hasNext())
			return it.next();

		if (create) {
			// Element does not exist, create and return
			TElement element = this.addVertex(TElement.TRACE_TYPE, TElement.class);
			element.setElementId(elementId);
			this.baseGraph.commit();
			return element;
		}
		return null;
	}

	@Override
	public Iterable<TElement> getAllElements() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TElement.TRACE_TYPE),
				TElement.class);
	}

	@Override
	public TProperty getProperty(String name, TElement owner) {
		return this.getProperty(name, owner, true);
	}
	
	@Override
	public TProperty getProperty(String name, String elementId) {
		return this.getProperty(name, this.getElement(elementId));
	}
	
	@Override
	public TProperty getProperty(String name, String elementId, boolean create) {
		return this.getProperty(name, this.getElement(elementId), create);
	}
	
	@Override
	public TProperty getProperty(String name, TElement owner, boolean create) {
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(owner.asVertex()).out(TOwns.TRACE_TYPE)
				.has(TProperty.NAME, name).cast(Vertex.class);

		if (p.hasNext()) {
			return this.framedGraph.frame(p.next(), TProperty.class);
		}

		if (create) {
			TProperty property = this.addVertex(TProperty.TRACE_TYPE,
					TProperty.class);
			property.setOwner(owner);
			property.setName(name);
			this.baseGraph.commit();
			return property;
		}
		return null;
	}

	@Override
	public Iterable<TProperty> getAllProperties() {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(TProperty.TRACE_TYPE),
				TProperty.class);
	}

	@Override
	public void removeElement(String elementId) {
		this.removeElement(this.getElement(elementId, false));
	}

	@Override
	public void removeElement(TElement element) {
		if (element == null)
			return;
		
		final List<Vertex> toRemove = new ArrayList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>(element.asVertex());
		p.aggregate(toRemove).both().aggregate(toRemove).next();
		Object o = null;
		for (Vertex vertex : toRemove) {
			o = vertex.getId();
			this.baseGraph.removeVertex(vertex);
		}
		
		this.commit();
	}
	
	@Override
	public void removeProperty(String name, String elementId) {
		this.removeProperty(this.getProperty(name, elementId, false));
	}
	
	@Override
	public void removeProperty(TProperty property) {
		if (property != null) {
			this.baseGraph.removeVertex(property.asVertex());
			this.commit();
		}
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
	public void commit() {
		this.baseGraph.commit();
	}

	@Override
	public void close() {
		this.baseGraph.shutdown();
	}

	private <F> F addVertex(String traceType, Class<F> clazz) {
		return this.framedGraph.addVertex(String.format("class:%s", traceType),
				clazz);
	}

	private <F> Iterable<F> getAll(String traceType, Class<F> clazz) {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(traceType),
				clazz);
	}

}
