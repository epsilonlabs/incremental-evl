package org.eclipse.epsilon.evl.incremental.orientdb;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TEvaluates;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TIn;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TRootOf;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.TScope;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

/**
 * Implementation of {@link IIncrementalTraceManager} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientPropertyAccessTrace implements IIncrementalTraceManager {

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
	public IContextTrace createContext(String contextName) {
		TContext context = (TContext) this.getContext(contextName);
		if (context == null) {
			context = this.addVertex(TContext.TRACE_TYPE, TContext.class);
			context.setName(contextName);
		}
		return context;
	}

	@Override
	public IConstraintTrace createConstraint(String constraintName, String contextName) {
		return this.createConstraint(constraintName, this.createContext(contextName));
	}

	@Override
	public IConstraintTrace createConstraint(String constraintName, IContextTrace context) {
		if (context == null) return null;
		
		TConstraint constraint = (TConstraint) this.getConstraint(constraintName, context);
		if (constraint == null) {
			constraint = this.addVertex(TConstraint.TRACE_TYPE, TConstraint.class);
			constraint.setName(constraintName);
			context.addConstraint(constraint);
		}
		return constraint;
	}

	@Override
	public IModelElement createElement(String elementId) {
		TElement element = (TElement) this.getElement(elementId);
		if (element == null) {
			element = this.addVertex(TElement.TRACE_TYPE, TElement.class);
			element.setElementId(elementId);
		}
		return element;
	}

	@Override
	public ITraceScope createScope(String elementId, String constraintName, String contextName) {
		return this.createScope(this.createElement(elementId), 
				this.createConstraint(constraintName, contextName));
	}

	@Override
	public ITraceScope createScope(String elementId, IConstraintTrace constraint) {
		return this.createScope(this.createElement(elementId), constraint);
	}

	@Override
	public ITraceScope createScope(IModelElement element, String constraintName, String contextName) {
		return this.createScope(element, this.createConstraint(constraintName, contextName));
	}

	@Override
	public ITraceScope createScope(IModelElement element, final IConstraintTrace constraint) {
		if (element == null || constraint == null) return null;
		
		TScope scope = (TScope) this.getScope(element, constraint);
		if (scope == null) {
			scope = this.addVertex(TScope.TRACE_TYPE, TScope.class);
			scope.setRootElement(element);
			scope.setConstraint(constraint);
		}
		return scope;
	}

	@Override
	public IElementProperty createProperty(String propertyName, String elementId) {
		return this.createProperty(propertyName, this.createElement(elementId));
	}

	@Override
	public IElementProperty createProperty(String propertyName, IModelElement element) {
		if (element == null) return null;
		
		TProperty property = (TProperty) this.getProperty(propertyName, element);
		if (property == null) {
			property = this.addVertex(TProperty.TRACE_TYPE, TProperty.class);
			property.setOwner(element);
			property.setName(propertyName);
		}
		return property;
	}

	@Override
	public IContextTrace getContext(String contextName) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TContext.TRACE_TYPE, TContext.NAME),
				contextName);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				TContext.class);
	}

	@Override
	public IConstraintTrace getConstraint(String constraintName, String contextName) {
		return this.getConstraint(constraintName, this.getContext(contextName));
	}

	@Override
	public IConstraintTrace getConstraint(String constraintName, IContextTrace context) {
		if (context == null) return null;
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(((TContext) context).asVertex())
		.inE(TIn.TRACE_TYPE)
		.outV()
		.has(TConstraint.NAME, constraintName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), TConstraint.class)
				: null;
	}

	@Override
	public IModelElement getElement(String elementId) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(TElement.TRACE_TYPE, TElement.ELEMENT_ID),
				elementId);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				TElement.class);
	}

	@Override
	public ITraceScope getScope(String elementId, String constraintName, String contextName) {
		return this.getScope(this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	@Override
	public ITraceScope getScope(String elementId, IConstraintTrace constraint) {
		return this.getScope(this.getElement(elementId), constraint);
	}

	@Override
	public ITraceScope getScope(IModelElement element, String constraintName,  String contextName) {
		return this.getScope(element, this.getConstraint(constraintName, contextName));
	}

	@Override
	public ITraceScope getScope(IModelElement element, final IConstraintTrace constraint) {
		if (element == null || constraint == null) return null;
		
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(((TElement) element).asVertex())
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
	public IElementProperty getProperty(String propertyName, String elementId) {
		return this.getProperty(propertyName, this.getElement(elementId));
	}

	@Override
	public IElementProperty getProperty(String propertyName, IModelElement element) {
		if (element == null) return null;
		
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(((TElement) element).asVertex())
		.out(TOwns.TRACE_TYPE)
		.has(TProperty.NAME, propertyName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), TProperty.class) 
				: null;
	}

	@Override
	public Iterable<IContextTrace> getAllContexts() {
		return this.getAll(TContext.TRACE_TYPE, IContextTrace.class);
	}

	@Override
	public Iterable<IConstraintTrace> getAllConstraints() {
		return this.getAll(TConstraint.TRACE_TYPE, IConstraintTrace.class);
	}

	@Override
	public Iterable<IModelElement> getAllElements() {
		return this.getAll(TElement.TRACE_TYPE, IModelElement.class);
	}

	@Override
	public Iterable<ITraceScope> getAllScopes() {
		return this.getAll(TScope.TRACE_TYPE, ITraceScope.class);
	}

	@Override
	public Iterable<IElementProperty> getAllProperties() {
		return this.getAll(TProperty.TRACE_TYPE, IElementProperty.class);
	}

	@Override
	public Iterable<ITraceScope> getScopesOf(IModelElement element) {
		LinkedList<ITraceScope> result = new LinkedList<ITraceScope>();
		if (element == null) {
			return result;
		}
		final List<Vertex> results = new LinkedList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(((TElement) element).asVertex())
			.out(TOwns.TRACE_TYPE)
			.in(TAccesses.TRACE_TYPE)
			.aggregate(results)
			.next();
		Iterable<TScope> frameVertices = this.framedGraph.frameVertices(results, TScope.class);
		// FIXME is there another way to do this?
		for (TScope ts : frameVertices) {
			result.add(ts);
		}
		return result;
	}

	@Override
	public Iterable<ITraceScope> getScopesOfId(String elementId) {
		return this.getScopesOf(this.getElement(elementId));
	}

	@Override
	public void removeContext(String contextName) {
		this.removeContext(this.getContext(contextName));
	}
	
	@Override
	public void removeContext(IContextTrace context) {
		if (context != null) {
			this.baseGraph.removeVertex(((TContext) context).asVertex());
		}
	}

	@Override
	public void removeConstraint(IConstraintTrace constraint) {
		if (constraint != null) {
			this.baseGraph.removeVertex(((TConstraint) constraint).asVertex());
		}
	}

	@Override
	public void removeConstraint(String constraintName,
			String contextName) {
		this.removeConstraint(this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeConstraint(String constraintName, IContextTrace context) {
		this.removeConstraint(this.getConstraint(constraintName, context));
	}

	@Override
	public void removeElement(IModelElement element) {
		if (element != null) {
			this.baseGraph.removeVertex(((TElement) element).asVertex());
		}
	}

	@Override
	public void removeElement(String elementId) {
		this.removeElement(this.getElement(elementId));
	}

	@Override
	public void removeScope(ITraceScope scope) {
		if (scope != null) {
			this.baseGraph.removeVertex(((TScope) scope).asVertex());
		}
	}

	@Override
	public void removeScope(String elementId, String constraintName, String contextName) {
		this.removeScope(
				this.getElement(elementId), 
				this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeScope(String elementId, IConstraintTrace constraint) {
		this.removeScope(this.getElement(elementId), constraint);
	}

	@Override
	public void removeScope(IModelElement element, String constraintName, String contextName) {
		this.removeScope(element, this.getConstraint(constraintName, contextName));
	}

	@Override
	public void removeScope(IModelElement element, IConstraintTrace constraint) {
		this.removeScope(this.getScope(element, constraint));
	}

	@Override
	public void removeProperty(IElementProperty property) {
		if ( property != null) {
			this.baseGraph.removeVertex(((TProperty) property).asVertex());
		}
	}

	@Override
	public void removeProperty(String propertyName, String elementId) {
		this.removeProperty(this.getProperty(propertyName, elementId));
	}

	@Override
	public void removeProperty(String propertyName, IModelElement element) {
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
