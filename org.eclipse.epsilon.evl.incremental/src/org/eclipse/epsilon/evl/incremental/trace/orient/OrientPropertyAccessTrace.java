package org.eclipse.epsilon.evl.incremental.trace.orient;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.trace.TContext;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TEvaluates;
import org.eclipse.epsilon.evl.incremental.trace.TIn;
import org.eclipse.epsilon.evl.incremental.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TRootOf;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

/**
 * Implementation of {@link IPropertyAccessTrace} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientPropertyAccessTrace implements IPropertyAccessTrace {

	private final FramedGraph<OrientBaseGraph> framedGraph;
	private final OrientBaseGraph baseGraph;

	/**
	 * Package private constructor - use {@link OrientPropertyAccessTraceFactory} to
	 * create.
	 * 
	 * @param baseGraph
	 */
	OrientPropertyAccessTrace(OrientBaseGraph baseGraph) {
		this.baseGraph = baseGraph;
		FramedGraphFactory fgf = new FramedGraphFactory();
		this.framedGraph = fgf.create(this.baseGraph);
	}

	@Override
	public TContext createContext(String contextName) {
		TContext context = this.getContext(contextName);
		if (context == null) {
			context = this.addVertex(TContext.TRACE_TYPE, TContext.class);
			context.setName(contextName);
		}
		return context;
	}

	@Override
	public TConstraint createConstraint(String constraintName, String contextName) {
		return this.createConstraint(constraintName, this.createContext(contextName));
	}

	@Override
	public TConstraint createConstraint(String constraintName, TContext context) {
		if (context == null) return null;
		
		TConstraint constraint = this.getConstraint(constraintName, context);
		if (constraint == null) {
			constraint = this.addVertex(TConstraint.TRACE_TYPE, TConstraint.class);
			constraint.setName(constraintName);
			context.addConstraint(constraint);
		}
		return constraint;
	}

	@Override
	public TElement createElement(String elementId) {
		TElement element = this.getElement(elementId);
		if (element == null) {
			element = this.addVertex(TElement.TRACE_TYPE, TElement.class);
			element.setElementId(elementId);
		}
		return element;
	}

	@Override
	public TScope createScope(String elementId, String constraintName, String contextName) {
		return this.createScope(this.createElement(elementId), 
				this.createConstraint(constraintName, contextName));
	}

	@Override
	public TScope createScope(String elementId, TConstraint constraint) {
		return this.createScope(this.createElement(elementId), constraint);
	}

	@Override
	public TScope createScope(TElement element, String constraintName, String contextName) {
		return this.createScope(element, this.createConstraint(constraintName, contextName));
	}

	@Override
	public TScope createScope(TElement element, final TConstraint constraint) {
		if (element == null || constraint == null) return null;
		
		TScope scope = this.getScope(element, constraint);
		if (scope == null) {
			scope = this.addVertex(TScope.TRACE_TYPE, TScope.class);
			scope.setRootElement(element);
			scope.setConstraint(constraint);
		}
		return scope;
	}

	@Override
	public TProperty createProperty(String propertyName, String elementId) {
		return this.createProperty(propertyName, this.createElement(elementId));
	}

	@Override
	public TProperty createProperty(String propertyName, TElement element) {
		if (element == null) return null;
		
		TProperty property = this.getProperty(propertyName, element);
		if (property == null) {
			property = this.addVertex(TProperty.TRACE_TYPE, TProperty.class);
			property.setOwner(element);
			property.setName(propertyName);
		}
		return property;
	}

	@Override
	public TContext getContext(String contextName) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TContext.TRACE_TYPE, TContext.NAME),
				contextName);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				TContext.class);
	}

	@Override
	public TConstraint getConstraint(String constraintName, String contextName) {
		return this.getConstraint(constraintName, this.getContext(contextName));
	}

	@Override
	public TConstraint getConstraint(String constraintName, TContext context) {
		if (context == null) return null;
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(context.asVertex())
		.inE(TIn.TRACE_TYPE)
		.outV()
		.has(TConstraint.NAME, constraintName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), TConstraint.class)
				: null;
	}

	@Override
	public TElement getElement(String elementId) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TElement.TRACE_TYPE, TElement.ELEMENT_ID),
				elementId);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				TElement.class);
	}

	@Override
	public TScope getScope(String elementId, String constraintName, String contextName) {
		return this.getScope(this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	@Override
	public TScope getScope(String elementId, TConstraint constraint) {
		return this.getScope(this.getElement(elementId), constraint);
	}

	@Override
	public TScope getScope(TElement element, String constraintName,  String contextName) {
		return this.getScope(element, this.getConstraint(constraintName, contextName));
	}

	@Override
	public TScope getScope(TElement element, final TConstraint constraint) {
		if (element == null || constraint == null) return null;
		
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
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
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), TScope.class)
				: null;
	}

	@Override
	public TProperty getProperty(String propertyName, String elementId) {
		return this.getProperty(propertyName, this.getElement(elementId));
	}

	@Override
	public TProperty getProperty(String propertyName, TElement element) {
		if (element == null) return null;
		
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(element.asVertex())
		.out(TOwns.TRACE_TYPE)
		.has(TProperty.NAME, propertyName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), TProperty.class) 
				: null;
	}

	@Override
	public Iterable<TContext> getAllContexts() {
		return this.getAll(TContext.TRACE_TYPE, TContext.class);
	}

	@Override
	public Iterable<TConstraint> getAllConstraints() {
		return this.getAll(TConstraint.TRACE_TYPE, TConstraint.class);
	}

	@Override
	public Iterable<TElement> getAllElements() {
		return this.getAll(TElement.TRACE_TYPE, TElement.class);
	}

	@Override
	public Iterable<TScope> getAllScopes() {
		return this.getAll(TScope.TRACE_TYPE, TScope.class);
	}

	@Override
	public Iterable<TProperty> getAllProperties() {
		return this.getAll(TProperty.TRACE_TYPE, TProperty.class);
	}

	@Override
	public Iterable<TScope> getScopesPartOf(TElement element) {
		if (element == null) {
			return new LinkedList<TScope>();
		}
		final List<Vertex> results = new LinkedList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(element.asVertex())
			.out(TOwns.TRACE_TYPE)
			.in(TAccesses.TRACE_TYPE)
			.aggregate(results)
			.next();
		return this.framedGraph.frameVertices(results, TScope.class);
	}

	@Override
	public Iterable<TScope> getScopesPartOf(String elementId) {
		return this.getScopesPartOf(this.getElement(elementId));
	}

	@Override
	public void removeContext(String contextName) {
		this.removeContext(this.getContext(contextName));
	}
	
	@Override
	public void removeContext(TContext context) {
		if (context != null) {
			this.baseGraph.removeVertex(context.asVertex());
		}
	}

	@Override
	public void removeConstraint(TConstraint constraint) {
		if (constraint != null) {
			this.baseGraph.removeVertex(constraint.asVertex());
		}
	}

	@Override
	public void removeConstraint(String constraintName,
			String contextName) {
		this.removeConstraint(this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeConstraint(String constraintName, TContext context) {
		this.removeConstraint(this.getConstraint(constraintName, context));
	}

	@Override
	public void removeElement(TElement element) {
		if (element != null) {
			this.baseGraph.removeVertex(element.asVertex());
		}
	}

	@Override
	public void removeElement(String elementId) {
		this.removeElement(this.getElement(elementId));
	}

	@Override
	public void removeScope(TScope scope) {
		if (scope != null) {
			this.baseGraph.removeVertex(scope.asVertex());
		}
	}

	@Override
	public void removeScope(String elementId, String constraintName, String contextName) {
		this.removeScope(
				this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeScope(String elementId, TConstraint constraint) {
		this.removeScope(this.getElement(elementId), constraint);
	}

	@Override
	public void removeScope(TElement element, String constraintName, String contextName) {
		this.removeScope(element, this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeScope(TElement element, TConstraint constraint) {
		this.removeScope(this.getScope(element, constraint));
	}

	@Override
	public void removeProperty(TProperty property) {
		if ( property != null) {
			this.baseGraph.removeVertex( property.asVertex());
		}
	}

	@Override
	public void removeProperty(String propertyName, String elementId) {
		this.removeProperty(this.getProperty(propertyName, elementId));
	}

	@Override
	public void removeProperty(String propertyName, TElement element) {
		this.removeProperty(this.getProperty(propertyName, element));
	}

	public OrientBaseGraph getBaseGraph() {
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
	public void shutdown() {
		this.baseGraph.shutdown();
	}

	private <F> F addVertex(String traceType, Class<F> clazz) {
		return this.framedGraph.addVertex(
				String.format("class:%s", traceType),
				clazz);
	}

	private String getIndexName(String classname, String key) {
		return String.format("%s.%s", classname, key).toLowerCase();
	}
	
	private <F> Iterable<F> getAll(String traceType, Class<F> clazz) {
		return this.framedGraph.frameVertices(
				this.baseGraph.getVerticesOfClass(traceType),
				clazz);
	}

}
