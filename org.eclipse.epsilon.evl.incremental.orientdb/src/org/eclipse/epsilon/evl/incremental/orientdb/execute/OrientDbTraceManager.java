/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.engine.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.evl.incremental.orientdb.dialog.OrientDBManagerConfiguration;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EAccesses;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EContains;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EFor;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EInvolves;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.EReaches;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VExecutionContext;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModelElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VModuleElement;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VProperty;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.VTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.ExecutionContextOrientDbImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.ModelElementOrientDbImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.ModuleElementOrientDbImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.PropertyOrientDbImpl;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.TraceOrientDbImpl;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.frames.FramedTransactionalGraph;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

/**
 * Implementation of {@link IExecutionTraceManager} that uses Orient DB as its underlying
 * database.
 * 
 * @author Jonathan Co
 *
 */
public class OrientDbTraceManager implements IExecutionTraceManager {
	
	private OrientGraphFactory factory;
	
	/**
	 * The execution context id of this trace manager. We keep a reference to the id because all operations are atomic
	 * and hence keeping the vertex reference will result in it being detached in future operations.
	 * 
	 * If not unique for a session, the {@link OrientDbTraceManager#setExecutionContext(String, Collection)} 
	 * method can be invoked before creating or retrieving records form the database.
	 * 
	 */
	private Object executionContextId;

	private String url;

	private String user;

	private String password;

	private TraceOrientDbDAO orientDbDAO;

	private boolean setup;
	
	/**
	 * Set the configuration parameters for the OrientDB. The first three array elements should be the url, user and
	 * password. For Persistent Embedded (plocal) databases a 4th parameter can be passed to indicate if the DB schema
	 * should be created (e.g the DB is new). For In-Memory Embedded (memory) databases the schema is always created.
	 * For Persistent Remote (remote) databases the DB should exist and hence the schema can not be defined
	 */
	@Override
	public void configure(StringProperties configParameters) {
		
		url = configParameters.getProperty(OrientDBManagerConfiguration.DB_URL);
		user = configParameters.getProperty(OrientDBManagerConfiguration.DB_USER);
		password = configParameters.getProperty(OrientDBManagerConfiguration.DB_PASS);
		// "memory:EVLTrace"
		setup = configParameters.getBooleanProperty(OrientDBManagerConfiguration.DB_CREATE, false);
		
	}

	@Override
	public void executionStarted() {
		
		if (url.startsWith("memory:")) {
			String name = url.split(":")[1];
			factory = OrientDbUtil.getInMemoryFactory(name);
		}
		// TODO Complete for other types
		
		orientDbDAO = new TraceOrientDbDAO(factory);
		if (url.startsWith("memory:")) {
			orientDbDAO.setupSchema();
		}
		
	}

	@Override
	public void executionFinished() {
		factory.close();
	}
	
	@Override
	public void incrementalExecutionStarted() {
		if (url.startsWith("memory:")) {
			String name = url.split(":")[1];
			factory = OrientDbUtil.getInMemoryFactory(name);
		}
		// TODO Complete for other types
	}
	


	@Override
	public void setExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		ExecutionContextOrientDbImpl executionContext = (ExecutionContextOrientDbImpl) acquireExecutionContext(scriptId, modelsIds);
		executionContextId = executionContext.getId();
	}


	@Override
	public boolean createExecutionTraces(String moduleElementId, String elementId, List<String> properties) throws EOLIncrementalExecutionException {
		for (String pName : properties) {
			createExecutionTrace(moduleElementId, elementId, pName);
		}
		return true;
	}

	@Override
	public boolean createExecutionTrace(String moduleElementId, String elementId, String propertyName) throws EOLIncrementalExecutionException {
		
		ModuleElementOrientDbImpl moduleElement = acquireModuleElement(moduleElementId);
		ModelElementOrientDbImpl modelElement = acquireModelElement(elementId);
		TraceOrientDbImpl trace = acquireTrace(moduleElement, modelElement);
		PropertyOrientDbImpl property = findProperty(trace, modelElement, propertyName);
		if (property == null) {
			property = craeteProperty(propertyName);
			FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
			VTrace traceV = manager.frame(manager.getVertex(trace.getId()), VTrace.class);
			VProperty pV = manager.frame(manager.getVertex(property.getId()), VProperty.class);
			traceV.addAccesses(pV);
			VModelElement meV = manager.frame(manager.getVertex(modelElement.getId()), VModelElement.class);
			meV.addOwns(pV);
			try {
				trace = OrientDbUtil.wrap(TraceOrientDbImpl.class, traceV);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
			} finally {
				manager.commit();
				manager.shutdown();
			}
			return true;
		}
		return false;
	}
	
	
	private ExecutionContextOrientDbImpl acquireExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		ExecutionContextOrientDbImpl ec = findExecutionContext(scriptId, modelsIds);
		if (ec == null) {
			ec = createExecutionContext(scriptId, modelsIds);
		}
		return ec;
	}

	private ExecutionContextOrientDbImpl createExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		VExecutionContext ecV = orientDbDAO.createExecutionContext(scriptId, modelsIds);
		if (ecV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		ExecutionContextOrientDbImpl ec = null;
		try {
			ec = OrientDbUtil.wrap(ExecutionContextOrientDbImpl.class, ecV);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance.", e);
		}
		return ec;
	}

	private PropertyOrientDbImpl craeteProperty(String propertyName) throws EOLIncrementalExecutionException {
		VProperty proeprtyV = orientDbDAO.createProperty(propertyName);
		if (proeprtyV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		PropertyOrientDbImpl property = null;
		try {
			property = OrientDbUtil.wrap(PropertyOrientDbImpl.class, proeprtyV);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		}
		return property;
	}

	private PropertyOrientDbImpl findProperty(TraceOrientDbImpl trace, ModelElementOrientDbImpl modelElement, String propertyName) throws EOLIncrementalExecutionException {
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		pipe.start(vertex.asVertex())
			.out(EContains.TRACE_TYPE)
			.filter(new PipeFunction<Vertex, Boolean>() {
				
				@Override
				public Boolean compute(Vertex v) {
					return v.getId().equals(trace.getId());		// Detach issue?
				}
			})
			.out(EAccesses.TRACE_TYPE)
			.has(VProperty.NAME, propertyName);
		PropertyOrientDbImpl result = null;
		try {
			result = OrientDbUtil.wrap(PropertyOrientDbImpl.class, manager.frame(pipe.next(), VProperty.class));
		} catch (NoSuchElementException e) {
			// Return null as indication of not finding any;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		} finally {
			manager.shutdown();
		}
		return result;
		
	}

	private TraceOrientDbImpl acquireTrace(ModuleElementOrientDbImpl moduleElement,
			ModelElementOrientDbImpl modelElement) throws EOLIncrementalExecutionException {
		
		TraceOrientDbImpl trace = getTrace(moduleElement, modelElement);
		if (trace == null) {
			trace = createTrace();
			FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
			VTrace traceV = manager.frame(manager.getVertex(trace.getId()), VTrace.class);
			VModuleElement moduEV = manager.frame(manager.getVertex(moduleElement.getId()), VModuleElement.class);
			traceV.setTraces(moduEV);
			VModelElement modeEV = manager.frame(manager.getVertex(modelElement.getId()), VModelElement.class);
			traceV.addReaches(modeEV);
			VExecutionContext ecV = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
			ecV.addContains(traceV);
			try {
				trace = OrientDbUtil.wrap(TraceOrientDbImpl.class, traceV);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
			} finally {
				manager.commit();
				manager.shutdown();
			}
		}
		return trace;
	}

	private TraceOrientDbImpl createTrace() throws EOLIncrementalExecutionException {
		VTrace traceV = orientDbDAO.createTrace();
		if (traceV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		TraceOrientDbImpl trace = null;
		try {
			trace = OrientDbUtil.wrap(TraceOrientDbImpl.class, traceV);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		}
		return trace;
	}

	private TraceOrientDbImpl getTrace(ModuleElementOrientDbImpl moduleElement, ModelElementOrientDbImpl modelElement) throws EOLIncrementalExecutionException {
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		pipe.start(vertex.asVertex())
			.out(EInvolves.TRACE_TYPE)
			.filter(new PipeFunction<Vertex, Boolean>() {

				@Override
				public Boolean compute(Vertex v) {
					return v.getId().equals(modelElement.getId());	// Will this fail because of detachment?
				}
			})
			.in(EReaches.TRACE_TYPE)
			.filter(new PipeFunction<Vertex, Boolean>() {
				
				@Override
				public Boolean compute(Vertex v) {
					return v.getId().equals(moduleElement.getId());
				}
			});
		TraceOrientDbImpl trace = null;
		try {
			trace = OrientDbUtil.wrap(TraceOrientDbImpl.class, manager.frame(pipe.next(), VTrace.class));
		} catch(NoSuchElementException e) {
			// Return null as indication of not finding any;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		} finally {
			manager.shutdown();
		}
		return trace;
		
	}

	private ModelElementOrientDbImpl acquireModelElement(String elementId) throws EOLIncrementalExecutionException {
		ModelElementOrientDbImpl me = getModelElement(elementId);
		if (me == null) {
			me = createModelElement(elementId);
		}
		return me;
	}

	private ModelElementOrientDbImpl createModelElement(String elementId) throws EOLIncrementalExecutionException {
		System.out.println("createModelElement " + elementId);
		VModelElement meV = orientDbDAO.createModelElement(elementId);
		
		if (meV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		System.out.println("created for " + meV.getElement_id());
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		vertex.addInvolves(meV);
		manager.commit();
		manager.shutdown();
		ModelElementOrientDbImpl me = null;
		try {
			me = OrientDbUtil.wrap(ModelElementOrientDbImpl.class, meV);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		}
		return me;
	}

	private ModelElementOrientDbImpl getModelElement(String elementId) throws EOLIncrementalExecutionException {
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		pipe.start(vertex.asVertex())
			.out(EInvolves.TRACE_TYPE)
			.has(VModelElement.ELEMENT_ID, elementId);
		ModelElementOrientDbImpl me = null;
		try {
			me = OrientDbUtil.wrap(ModelElementOrientDbImpl.class, manager.frame(pipe.next(), VModelElement.class));
		} catch(NoSuchElementException e) {
			// Return null as indication of not finding any;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		} finally {
			manager.shutdown();
		}
		return me;
	}

	private ModuleElementOrientDbImpl acquireModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		ModuleElementOrientDbImpl me = findModuleElement(moduleElementId);
		if (me == null) {
			me = createModuleElement(moduleElementId);
		}
		return me;
	}

	private ModuleElementOrientDbImpl createModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		VModuleElement meV = orientDbDAO.createModuleElement(moduleElementId);
		if (meV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		// Add the module element to the context
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		vertex.addFor(meV);
		manager.commit();
		manager.shutdown();
		ModuleElementOrientDbImpl me = null;
		try {
			me = OrientDbUtil.wrap(ModuleElementOrientDbImpl.class, meV);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		}
		return me;
	}

	private ModuleElementOrientDbImpl findModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		pipe.start(vertex.asVertex())
			.out(EFor.TRACE_TYPE)
			.has(VModuleElement.MODULE_ID, moduleElementId);
		ModuleElementOrientDbImpl me = null;
		try {
			me = OrientDbUtil.wrap(ModuleElementOrientDbImpl.class, manager.frame(pipe.next(), VModuleElement.class));
		} catch(NoSuchElementException e) {
			// Return null as indication of not finding any;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		} finally {
			manager.shutdown();
		}
		return me;
	}

	@Override
	public List<Trace> findExecutionTraces(String elementId, String propertyName)
			throws EOLIncrementalExecutionException {
		
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		List<Trace> result = new ArrayList<>();
//		for (Edge e : manager.getEdges()) {
//			System.out.println(e);
//		}
//		pipe.start(vertex.asVertex())
//			.out(EContains.TRACE_TYPE)
//			.as("trace1")
//			.out(EReaches.TRACE_TYPE)
//			.has(VModelElement.ELEMENT_ID, elementId)
//			.back("trace1")
//			.as("trace2")
//			.out(EAccesses.TRACE_TYPE)
//			.has(VProperty.NAME, propertyName)
//			.back("trace2");
//			;while (pipe.hasNext()) {
//				VTrace v = manager.frame(pipe.next(), VTrace.class);
//				for (VProperty p : v.getAccesses()) {
//					System.out.println(p.getName());
//				}
//			}
//			;System.out.println(pipe.count());
			pipe.start(vertex.asVertex())
				.out(EContains.TRACE_TYPE)
				.as("trace1")
				.out(EReaches.TRACE_TYPE)
				.has(VModelElement.ELEMENT_ID, elementId)
				.back("trace1")
				.as("trace2")
				.out(EAccesses.TRACE_TYPE)
				.has(VProperty.NAME, propertyName)
				.back("trace2");

		while (pipe.hasNext()) {
			Vertex v = pipe.next();
			try {
				result.add(OrientDbUtil.wrap(TraceOrientDbImpl.class, manager.frame(v, VTrace.class)));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
			} finally {
				manager.shutdown();
			}
		} 
		return result;
	}


	@Override
	public List<Trace> findExecutionTraces(String elementId) throws EOLIncrementalExecutionException {
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		pipe.start(vertex.asVertex())
			.out(EContains.TRACE_TYPE)
			.as("trace")
			.out(EReaches.TRACE_TYPE)
			.has(VModelElement.ELEMENT_ID, elementId)
			.back("trace");
		List<Trace> result = new ArrayList<>();
		for(Vertex v : pipe.toList()) {
			try {
				result.add(OrientDbUtil.wrap(TraceOrientDbImpl.class, manager.frame(v, VTrace.class)));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
			} finally {
				manager.shutdown();
			}
		}
		return result;
	}
	
	
	/**
	 * Gets an Execution Context vertex from the graph identified by the IDs of the script and models used during
	 * execution.
	 * 
	 * @param scriptId The script's ID
	 * @param modelsIds The models' IDs
	 * @return
	 * @throws EOLIncrementalExecutionException 
	 */
	private ExecutionContextOrientDbImpl findExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		List<VExecutionContext> vertices = orientDbDAO.getExecutionContextByIndex(scriptId);
		VExecutionContext ec_vertex = null;
		for (VExecutionContext v : vertices) {
			List<String> v_models = v.getModelsIds();
			v_models.removeAll(modelsIds);
			if (v_models.isEmpty()) {
				ec_vertex = v;
				break;
			}
			
		}
		try {
			return ec_vertex == null ? null : OrientDbUtil.wrap(ExecutionContextOrientDbImpl.class, ec_vertex);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
		}
	}
	
	
	//***************************
	//*******    CRUD    ********
	//***************************
	
//	/**
//	 * Create an Element Property vertex in the Graph.
//	 * 
//	 * @param propertyName the property name
//	 * @return
//	 * @throws EOLIncrementalExecutionException 
//	 */
//	private PropertyOrientDBImpl createElementProperty(String propertyName) throws EOLIncrementalExecutionException {
//		VProperty proeprtyV = orientDbDAO.createProperty(propertyName);
//		PropertyOrientDBImpl property = null;
//		try {
//			property = OrientDbUtil.wrap(PropertyOrientDBImpl.class, proeprtyV);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//			throw new EOLIncrementalExecutionException("Could not create new element in DB.", e);
//		}
//		return property;
//	}
//	
//	private PropertyOrientDBImpl getElementProperty(String pro)
//	
//	

//	
//	/**
//	 * Acquires an Execution Context vertex from the graph, creating one if a matching one cannot be found.
//	 * 
//	 * @param scriptId
//	 * @param modelsIds
//	 * @return
//	 */
//	private ExecutionContextOrientDBImpl acquireExecutionContext(String scriptId, List<String> modelsIds) {
//		ExecutionContextOrientDBImpl ec = this.getExecutionContext(scriptId, modelsIds);
//		if (ec == null) {
//			ec = createExecutionContext(scriptId, modelsIds);
//		}
//		return ec;
//	}
//	
//	
//	
//	
//	
//	
//	/**
//	 * Creates an Execution Context vertex in the Graph.
//	 * 
//	 * @param scriptId	The script's ID
//	 * @param modelsIds	The models' IDs
//	 * @return
//	 */
//	private ExecutionContextOrientDBImpl createExecutionContext(String scriptId, List<String> modelsIds) {
//		
//		ExecutionContextOrientDBImpl ec = new ExecutionContextOrientDBImpl(this.addVertex(NExecutionContext.TRACE_TYPE, NExecutionContext.class));
//		ec.setScriptId(scriptId);
//		ec.setModelsIds(modelsIds);
//		return ec;
//	}
//	
//	/**
//	 * Gets an Execution Context vertex from the graph identified by the IDs of the script and models used during
//	 * execution.
//	 * 
//	 * @param scriptId The script's ID
//	 * @param modelsIds The models' IDs
//	 * @return
//	 */
//	private ExecutionContextOrientDBImpl getExecutionContext(String scriptId, List<String> modelsIds) {
//		Iterable<Vertex> vertices = this.baseGraph.getVertices(
//				this.getIndexName(NExecutionContext.TRACE_TYPE, NExecutionContext.SCRIPT_ID),
//				scriptId);
//		
//		Vertex ec_vertex = null;
//		for (Vertex v : vertices) {
//			List<String> v_models = v.getProperty(NExecutionContext.MODELS_IDS);
//			v_models.removeAll(modelsIds);
//			if (v_models.isEmpty()) {
//				ec_vertex = v;
//				break;
//			}
//			
//		}
//		return ec_vertex == null ? null : new ExecutionContextOrientDBImpl(this.framedGraph.frame(ec_vertex,
//				NExecutionContext.class));
//	}
//	
//	/**
//	 * Acquires an Execution Context vertex from the graph, creating one if a matching one cannot be found.
//	 * 
//	 * @param scriptId
//	 * @param modelsIds
//	 * @return
//	 */
//	private ExecutionContextOrientDBImpl acquireExecutionContext(String scriptId, List<String> modelsIds) {
//		ExecutionContextOrientDBImpl ec = this.getExecutionContext(scriptId, modelsIds);
//		if (ec == null) {
//			ec = createExecutionContext(scriptId, modelsIds);
//		}
//		return ec;
//	}
//	
//	/**
//	 * Creates an Execution Trace vertex in the graph.
//	 * 
//	 * @return
//	 */
//	private ExecutionTraceOrientDBImpl createExecutionTrace() {
//		ExecutionTraceOrientDBImpl et = new ExecutionTraceOrientDBImpl(
//				this.addVertex(NExecutionTrace.TRACE_TYPE, NExecutionTrace.class));
//		return et;
//	}
//	
//	/**
//	 * Gets the execution trace for a given module element and model element.
//	 * 
//	 * @param moduleElementId	The module element id
//	 * @param modelElementId	The model element id
//	 * @return
//	 */
//	public ExecutionTraceOrientDBImpl getExecutionTrace(String moduleElementId, String modelElementId) {
//		
//		ModuleElementOrientDBImpl mue = (ModuleElementOrientDBImpl) getModuleElement(moduleElementId);
//		if (mue == null) {
//			return null;	// FIXME Probable an exception is better indicating that the module is not persisted
//		}
//		final ModelElementOrientDBImpl moe = getModelElement(modelElementId, mue);
//		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
//		p.start(mue.asVertex())
//			.in(ETraces.TRACE_TYPE)
//			.as("et")
//			.out(EReaches.TRACE_TYPE)
//			.filter(new PipeFunction<Vertex, Boolean>() {
//				
//				@Override
//				public Boolean compute(Vertex v) {
//					return v == moe;
//				}
//			})
//			.back("et");
//		ExecutionTraceOrientDBImpl et = null;
//		if (p.hasNext()) {
//			et = new ExecutionTraceOrientDBImpl(this.framedGraph.frame(p.next(),
//				NExecutionTrace.class));
//		}
//		return et;
//	}
//	
//
//	/**
//	 * Acquires an Execution Trace vertex from the graph, creating one if a matching one cannot be found.
//	 * @param moduleElementId
//	 * @return
//	 */
//	public ExecutionTraceOrientDBImpl acquireExecutionTrace(String moduleElementId, String modelElementId) {
//		ExecutionTraceOrientDBImpl et = getExecutionTrace(moduleElementId, modelElementId);
//		if (et == null) {
//			et = createExecutionTrace();
//			et.addModelElementTrace(acquireModelElement(modelElementId));
//		}
//		
//	}
//
//	
//	public IExecutionTrace getScope(String elementId, IConstraintTrace constraint) {
//		return this.geNExecutionTrace(this.getElement(elementId), constraint);
//	}
//
//	
//	public IExecutionTrace getScope(IModelElement element, String constraintName,  String contextName) {
//		return this.geNExecutionTrace(element, this.getConstraint(constraintName, contextName));
//	}
//
//	
//	
//	private ModelElementOrientDBImpl getModelElement(String modelElementId, IModuleElement mue) {
//		//TODO Add implementation
//		return null;
//	}
//	
//	
//	
//	
//	//////////////////////////////
//	
//	
//	
//
//	private IExecutionTrace getExecutionTrace(IModelElement element) {
//		if (element == null) return null;
//		
//		TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
//		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
//		p.start(tElement.asVertex())
//		.outE(EReaches.TRACE_TYPE)
//		.inV();
////		.as("scopes")
////		.inE(TTraces.TRACE_TYPE)
////		.outV()
////		.back("scopes");
//		
//		return p.hasNext() 
//				? new ExecutionTraceOrientDBImpl(this.framedGraph.frame(p.next(), NExecutionTrace.class))
//				: null;
//	}
//	
//	
//	
//	
//	
//	
//	/**
//	 * Creates Module Element vertex in the Graph
//	 * Create a Module Element in the database iff it does not exist. Module elements are uniquely identified by their
//	 * id and the current execution context.
//	 * @param id
//	 * @return
//	 */
//	private IModuleElement createModuleElement(String id) {
//		
//		IModuleElement moduleElement = this.getModuleElement(id);
//		if (moduleElement == null) {
//			moduleElement = new ModuleElementOrientDBImpl(this.addVertex(NModuleElement.TRACE_TYPE, NModuleElement.class));
//			moduleElement.setId(id);
//		}
//		return moduleElement;
//	}
//	
//
//	private IModuleElement getModuleElement(String id) {
//		
//		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
//		p.start(executionContext.asVertex())
//			.in(EFor.TRACE_TYPE)
//			.outV()
//			.has(NModuleElement.ID, id);
//		NModuleElement me_v = null;
//		if (p.hasNext()) {
//			me_v  = (NModuleElement) p.next(); 
//		}
//		return me_v == null ? null : new ModuleElementOrientDBImpl(this.framedGraph.frame(me_v, NModuleElement.class));
//	}
//
//
//
//	private IModelElement getElement(String elementId) {
//		final Vertex vertex = this.baseGraph.getVertexByKey(
//				this.getIndexName(TModelElement.TRACE_TYPE, TModelElement.ELEMENT_ID),
//				elementId);
//		return vertex == null ? null : new ModelElementOrientDBImpl(this.framedGraph.frame(vertex,
//				TModelElement.class));
//	}
//	
//	
//	
//	
//	
//	
//
//	private IModelElement createModelElement(String elementId) {
//		IModelElement element = this.getElement(elementId);
//		if (element == null) {
//			element = new ModelElementOrientDBImpl(this.addVertex(TModelElement.TRACE_TYPE, TModelElement.class));
//			element.setElementId(elementId);
//		}
//		return element;
//	}
//
//
//	
//
//
//	
//
//
//
//	
//
//	
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllContexts()
//	 */
//	@Override
//	public Iterable<IContextTrace> getAllContexts() {
//		List<IContextTrace> result = new ArrayList<IContextTrace>();
//		for (TContext iter :this.getAll(TContext.TRACE_TYPE, TContext.class) ) {
//			result.add(new ExecutionContextOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllConstraints()
//	 */
//	@Override
//	public Iterable<IConstraintTrace> getAllConstraints() {
//		List<IConstraintTrace> result = new ArrayList<IConstraintTrace>();
//		for (NModuleElement iter :this.getAll(NModuleElement.TRACE_TYPE, NModuleElement.class) ) {
//			result.add(new ModuleElementOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllElements()
//	 */
//	@Override
//	public Iterable<IModelElement> getAllElements() {
//		List<IModelElement> result = new ArrayList<IModelElement>();
//		for (TModelElement iter :this.getAll(TModelElement.TRACE_TYPE, TModelElement.class) ) {
//			result.add(new ModelElementOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllScopes()
//	 */
//	@Override
//	public Iterable<IExecutionTrace> getAllScopes() {
//		List<IExecutionTrace> result = new ArrayList<IExecutionTrace>();
//		for (NExecutionTrace iter :this.getAll(NExecutionTrace.TRACE_TYPE, NExecutionTrace.class) ) {
//			result.add(new ExecutionTraceOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getAllProperties()
//	 */
//	@Override
//	public Iterable<IElementProperty> getAllProperties() {
//		List<IElementProperty> result = new ArrayList<IElementProperty>();
//		for (TProperty iter :this.getAll(TProperty.TRACE_TYPE, TProperty.class) ) {
//			result.add(new ElementPropertyOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScopesOf(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
//	 */
//	@Override
//	public Iterable<IExecutionTrace> getScopesOf(IModelElement element) {
//		if (element == null) {
//			return new LinkedList<IExecutionTrace>();
//		}
//		TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
//		final List<Vertex> results = new LinkedList<Vertex>();
//		final GremlinPipeline<Vertex, Vertex> p = new GremlinPipeline<Vertex, Vertex>();
//		p.start(tElement.asVertex())
//			.out(TOwns.TRACE_TYPE)
//			.in(TAccesses.TRACE_TYPE)
//			.aggregate(results)
//			.next();
//		
//		
//		List<IExecutionTrace> result = new ArrayList<IExecutionTrace>();
//		for (NExecutionTrace iter : this.framedGraph.frameVertices(results, NExecutionTrace.class)) {
//			result.add(new ExecutionTraceOrientDBImpl(iter));
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#getScopesOfId(java.lang.String)
//	 */
//	@Override
//	public Iterable<IExecutionTrace> getScopesOfId(String elementId) {
//		return this.getScopesOf(this.getElement(elementId));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeContext(java.lang.String)
//	 */
//	@Override
//	public void removeContext(String contextName) {
//		this.removeContext(this.getContext(contextName));
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeContext(org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
//	 */
//	@Override
//	public void removeContext(IContextTrace context) {
//		if (context != null) {
//			TContext tContext = ((ExecutionContextOrientDBImpl) context).getDelegate();
//			this.baseGraph.removeVertex(tContext.asVertex());
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
//	 */
//	@Override
//	public void removeConstraint(IConstraintTrace constraint) {
//		if (constraint != null) {
//			NModuleElement tConstraint = ((ModuleElementOrientDBImpl) constraint).getDelegate();
//			this.baseGraph.removeVertex(tConstraint.asVertex());
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void removeConstraint(String constraintName,
//			String contextName) {
//		this.removeConstraint(this.getConstraint(constraintName, contextName));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeConstraint(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IContextTrace)
//	 */
//	@Override
//	public void removeConstraint(String constraintName, IContextTrace context) {
//		this.removeConstraint(this.getConstraint(constraintName, context));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeElement(org.eclipse.epsilon.eol.incremental.trace.IModelElement)
//	 */
//	@Override
//	public void removeElement(IModelElement element) {
//		if (element != null) {
//			TModelElement tElement = ((ModelElementOrientDBImpl) element).getDelegate();
//			this.baseGraph.removeVertex(tElement.asVertex());
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeElement(java.lang.String)
//	 */
//	@Override
//	public void removeElement(String elementId) {
//		this.removeElement(this.getElement(elementId));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.ITraceScope)
//	 */
//	@Override
//	public void removeScope(IExecutionTrace scope) {
//		if (scope != null) {
//			NExecutionTrace tScope = ((ExecutionTraceOrientDBImpl) scope).getDelegate();
//			this.baseGraph.removeVertex(tScope.asVertex());
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void removeScope(String elementId, String constraintName, String contextName) {
//		this.removeScope(
//				this.getElement(elementId), 
//				this.getConstraint(constraintName, contextName));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
//	 */
//	@Override
//	public void removeScope(String elementId, IConstraintTrace constraint) {
//		this.removeScope(this.getElement(elementId), constraint);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void removeScope(IModelElement element, String constraintName, String contextName) {
//		this.removeScope(element, this.getConstraint(constraintName, contextName));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeScope(org.eclipse.epsilon.eol.incremental.trace.IModelElement, org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace)
//	 */
//	@Override
//	public void removeScope(IModelElement element, IConstraintTrace constraint) {
//		this.removeModelElementTrace(this.geNExecutionTrace(element, constraint));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(org.eclipse.epsilon.eol.incremental.trace.IElementProperty)
//	 */
//	@Override
//	public void removeProperty(IElementProperty property) {
//		if ( property != null) {
//			TProperty tProperty = ((ElementPropertyOrientDBImpl) property).getDelegate();
//			this.baseGraph.removeVertex( tProperty.asVertex());
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void removeProperty(String propertyName, String elementId) {
//		this.removeProperty(this.getProperty(propertyName, elementId));
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#removeProperty(java.lang.String, org.eclipse.epsilon.eol.incremental.trace.IModelElement)
//	 */
//	@Override
//	public void removeProperty(String propertyName, IModelElement element) {
//		this.removeProperty(this.getProperty(propertyName, element));
//	}
//
//	/**
//	 * Gets the base graph.
//	 *
//	 * @return the base graph
//	 */
//	public OrientBaseGraph getBaseGraph() {
//		return this.baseGraph;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#isOpen()
//	 */
//	@Override
//	public boolean isOpen() {
//		return !this.baseGraph.isClosed();
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#commit()
//	 */
//	@Override
//	public void commit() {
//		this.baseGraph.commit();
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager#shutdown()
//	 */
//	@Override
//	public void shutdown() {
//		this.baseGraph.shutdown();
//	}
//
//	/**
//	 * Adds the vertex.
//	 *
//	 * @param <F> the generic type
//	 * @param traceType the trace type
//	 * @param clazz the clazz
//	 * @return the f
//	 */
//	private <F> F addVertex(String traceType, Class<F> clazz) {
//		return this.framedGraph.addVertex(
//				String.format("class:%s", traceType),
//				clazz);
//	}
//
//	/**
//	 * Gets the index name.
//	 *
//	 * @param classname the classname
//	 * @param key the key
//	 * @return the index name
//	 */
//	private String getIndexName(String classname, String key) {
//		return String.format("%s.%s", classname, key).toLowerCase();
//	}
//	
//	/**
//	 * Gets the all.
//	 *
//	 * @param <F> the generic type
//	 * @param traceType the trace type
//	 * @param clazz the clazz
//	 * @return the all
//	 */
//	private <F> Iterable<F> getAll(String traceType, Class<F> clazz) {
//		return this.framedGraph.frameVertices(
//				this.baseGraph.getVerticesOfClass(traceType),
//				clazz);
//	}
//
//
//
//






}
