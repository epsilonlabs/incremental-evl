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
import org.eclipse.epsilon.eol.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
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
	
	/**
	 * The DB url
	 */
	private String url;

	/**
	 * THe user to use when connecting to the DB
	 */
	private String user;

	/**
	 * The password to use when connecting to the DB
	 */
	private String password;

	/**
	 * The DAO for this manager
	 */
	private TraceOrientDbDAO orientDbDAO;

	/**
	 * A flag to indicate if the DB should be initialised (i.e. craete schema)
	 */
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
			return true;
		}
		return false;
	}

	@Override
	public boolean createExecutionTraces(String moduleElementId, String elementId, List<String> properties) throws EOLIncrementalExecutionException {
		for (String pName : properties) {
			createExecutionTrace(moduleElementId, elementId, pName);
		}
		return true;
	}
	
	@Override
	public void batchExecutionFinished() {
		factory.close();
	}
	

	@Override
	public void batchExecutionStarted() throws EOLIncrementalExecutionException {
		
		if (url.startsWith("memory:")) {
			String name = url.split(":")[1];
			factory = OrientDbUtil.getInMemoryFactory(name);
		}
		// TODO Complete for other types
		
		orientDbDAO = new TraceOrientDbDAO(factory);
		if (url.startsWith("memory:")) {
			try {
				orientDbDAO.setupSchema();
			}
			catch (Exception ex) {
				throw new EOLIncrementalExecutionException("Error setting up the schema.", ex);
			}
		}
		
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

	@Override
	public List<Trace> findExecutionTraces(String elementId, String propertyName)
			throws EOLIncrementalExecutionException {
		
		final GremlinPipeline<Vertex, Vertex> pipe = new GremlinPipeline<Vertex, Vertex>();
		FramedTransactionalGraph<OrientGraph> manager = orientDbDAO.getManager();
		VExecutionContext vertex = manager.frame(manager.getVertex(executionContextId), VExecutionContext.class);
		List<Trace> result = new ArrayList<>();
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
	public void eventExecutionFinished() throws EOLIncrementalExecutionException {
		factory.close();
	}
	
	
	@Override
	public void eventExecutionStarted() throws EOLIncrementalExecutionException {
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

	private ExecutionContextOrientDbImpl acquireExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		ExecutionContextOrientDbImpl ec = findExecutionContext(scriptId, modelsIds);
		if (ec == null) {
			ec = createExecutionContext(scriptId, modelsIds);
		}
		return ec;
	}

	private ModelElementOrientDbImpl acquireModelElement(String elementId) throws EOLIncrementalExecutionException {
		ModelElementOrientDbImpl me = getModelElement(elementId);
		if (me == null) {
			me = createModelElement(elementId);
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

	private ModelElementOrientDbImpl createModelElement(String elementId) throws EOLIncrementalExecutionException {
		VModelElement meV = orientDbDAO.createModelElement(elementId);
		
		if (meV == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
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

	@Override
	public void liveExecutionStarted() throws EOLIncrementalExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liveExecutionFinished() throws EOLIncrementalExecutionException {
		// TODO Auto-generated method stub
		
	}



}
