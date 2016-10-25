package org.eclipse.epsilon.evl.incremental.orientdb;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.evl.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IModelElement;
import org.eclipse.epsilon.evl.incremental.trace.IScopeConstraintTrace;
import org.eclipse.epsilon.evl.incremental.trace.IConstraintInContext;
import org.eclipse.epsilon.evl.incremental.trace.IElementOwnsProperty;
import org.eclipse.epsilon.evl.incremental.trace.IElementProperty;
import org.eclipse.epsilon.evl.incremental.trace.IElementRootOfScope;
import org.eclipse.epsilon.evl.incremental.trace.ITraceScope;

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
		IContextTrace context = this.getContext(contextName);
		if (context == null) {
			context = this.addVertex(IContextTrace.TRACE_TYPE, IContextTrace.class);
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
		
		IConstraintTrace constraint = this.getConstraint(constraintName, context);
		if (constraint == null) {
			constraint = this.addVertex(IConstraintTrace.TRACE_TYPE, IConstraintTrace.class);
			constraint.setName(constraintName);
			context.addConstraint(constraint);
		}
		return constraint;
	}

	@Override
	public IModelElement createElement(String elementId) {
		IModelElement element = this.getElement(elementId);
		if (element == null) {
			element = this.addVertex(IModelElement.TRACE_TYPE, IModelElement.class);
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
		
		ITraceScope scope = this.getScope(element, constraint);
		if (scope == null) {
			scope = this.addVertex(ITraceScope.TRACE_TYPE, ITraceScope.class);
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
		
		IElementProperty property = this.getProperty(propertyName, element);
		if (property == null) {
			property = this.addVertex(IElementProperty.TRACE_TYPE, IElementProperty.class);
			property.setOwner(element);
			property.setName(propertyName);
		}
		return property;
	}

	@Override
	public IContextTrace getContext(String contextName) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(IContextTrace.TRACE_TYPE, IContextTrace.NAME),
				contextName);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				IContextTrace.class);
	}

	@Override
	public IConstraintTrace getConstraint(String constraintName, String contextName) {
		return this.getConstraint(constraintName, this.getContext(contextName));
	}

	@Override
	public IConstraintTrace getConstraint(String constraintName, IContextTrace context) {
		if (context == null) return null;
		GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(context.asVertex())
		.inE(IConstraintInContext.TRACE_TYPE)
		.outV()
		.has(IConstraintTrace.NAME, constraintName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), IConstraintTrace.class)
				: null;
	}

	@Override
	public IModelElement getElement(String elementId) {
		final Vertex vertex = this.baseGraph.getVertexByKey(
				this.getIndexName(IModelElement.TRACE_TYPE, IModelElement.ELEMENT_ID),
				elementId);
		return vertex == null ? null : this.framedGraph.frame(vertex,
				IModelElement.class);
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
		p.start(element.asVertex())
		.outE(IElementRootOfScope.TRACE_TYPE)
		.inV()
		.as("scopes")
		.inE(IScopeConstraintTrace.TRACE_TYPE)
		.outV()
		.filter(new PipeFunction<Vertex, Boolean>() {
			@Override
			public Boolean compute(Vertex vertex) {
				return vertex.getProperty(IConstraintTrace.NAME).equals(constraint.getName());
			}
		})
		.back("scopes");
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), ITraceScope.class)
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
		p.start(element.asVertex())
		.out(IElementOwnsProperty.TRACE_TYPE)
		.has(IElementProperty.NAME, propertyName);
		
		return p.hasNext() 
				? this.framedGraph.frame(p.next(), IElementProperty.class) 
				: null;
	}

	@Override
	public Iterable<IContextTrace> getAllContexts() {
		return this.getAll(IContextTrace.TRACE_TYPE, IContextTrace.class);
	}

	@Override
	public Iterable<IConstraintTrace> getAllConstraints() {
		return this.getAll(IConstraintTrace.TRACE_TYPE, IConstraintTrace.class);
	}

	@Override
	public Iterable<IModelElement> getAllElements() {
		return this.getAll(IModelElement.TRACE_TYPE, IModelElement.class);
	}

	@Override
	public Iterable<ITraceScope> getAllScopes() {
		return this.getAll(ITraceScope.TRACE_TYPE, ITraceScope.class);
	}

	@Override
	public Iterable<IElementProperty> getAllProperties() {
		return this.getAll(IElementProperty.TRACE_TYPE, IElementProperty.class);
	}

	@Override
	public Iterable<ITraceScope> getScopesOf(IModelElement element) {
		if (element == null) {
			return new LinkedList<ITraceScope>();
		}
		final List<Vertex> results = new LinkedList<Vertex>();
		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
		p.start(element.asVertex())
			.out(IElementOwnsProperty.TRACE_TYPE)
			.in(TAccesses.TRACE_TYPE)
			.aggregate(results)
			.next();
		return this.framedGraph.frameVertices(results, ITraceScope.class);
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
			this.baseGraph.removeVertex(context.asVertex());
		}
	}

	@Override
	public void removeConstraint(IConstraintTrace constraint) {
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
	public void removeConstraint(String constraintName, IContextTrace context) {
		this.removeConstraint(this.getConstraint(constraintName, context));
	}

	@Override
	public void removeElement(IModelElement element) {
		if (element != null) {
			this.baseGraph.removeVertex(element.asVertex());
		}
	}

	@Override
	public void removeElement(String elementId) {
		this.removeElement(this.getElement(elementId));
	}

	@Override
	public void removeScope(ITraceScope scope) {
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
			this.baseGraph.removeVertex( property.asVertex());
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
