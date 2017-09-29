package org.eclipse.epsilon.incremental.arangodb.execute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.generation.*;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.incremental.arangodb.dialog.ArangoDBConfiguration;
import org.eclipse.epsilon.incremental.arangodb.trace.impl.*;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.Protocol;
import com.arangodb.util.MapBuilder;

public class ArangoDbTraceManager implements IEolExecutionTraceManager {
	
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
	 * The ArangoDB connection of the manager
	 */
	private ArangoDB arangoDB;
	
	/**
	 * The name of the database to use for this manager
	 */
	private String dbName;
	
	/**
	 * The DAO of this manager
	 */
	private TraceArangoDbDAO dao;
	
	/**
	 * A flag to indicate that the default DB connection parameters should be used.
	 */
	private boolean useDefaultCfg;
	
	/** 
	 * Whether execution is on-line of off-line. In line mode, the graph will be dumped after
	 * Execution is finished.
	 */
	private boolean online;
	
	/**
	 * The network address of the db server
	 */
	private String host;

	/**
	 * The port in which the db server is listening for connections
	 */
	private int port;
	
	/**
	 * The user name to use when connecting to the DB
	 */
	private String user;

	/**
	 * The password to use when connecting to the DB
	 */
	private String password;
	
	/**
	 * The connection timeout (in miliseconds)
	 */
	private Integer timeout;

	/**
	 * A flag to indicate if a secure SSL connection should be used
	 */
	private Boolean useSsl;

	/**
	 * The size of the data chunks to send
	 */
	private int chunksize;

	/**
	 * The maximun number of connections allowed
	 */
	private int maxConnections;

	/**
	 * The protocl to use for the connection
	 */
	private Protocol protocol;

	/**
	 * The name of the graph to use to store the traces
	 */
	private String graphName;

	/**
	 * A flag to indicate if the DB should be initialised (i.e. create the collections)
	 */
	private boolean setupSchema;
	
	/**
	 * A flag to indicate if the graph should be created.
	 */
	private boolean createGraph;
	
	@Override
	public void batchExecutionFinished() {
//		if (online) {
//			ArangoDatabase db = arangoDB.db(dbName);
//			db.graph(graphName).drop();
//			db.drop();
//		}
	}

	@Override
	public void batchExecutionStarted() throws EOLIncrementalExecutionException {
		if (useDefaultCfg) {
			arangoDB = new ArangoDB.Builder().build();
		}
		else {
			arangoDB = new ArangoDB.Builder()
					.host(host,  port)
					.timeout(timeout)
					.user(user)
					.password(password)
					.useSsl(useSsl)
					.chunksize(chunksize)
					.maxConnections(maxConnections)
					.useProtocol(protocol)
					.build();
					
		}
		Collection<String> existing = arangoDB.getDatabases();	
		if (!existing.contains(dbName)) {
			try {
				Boolean success = arangoDB.createDatabase(dbName);
				if (success) {
					System.out.println("Database created: " + dbName);
				}
				else {
					System.err.println("Database not created: " + dbName);
					throw new EOLIncrementalExecutionException("Failed to create the databse: " + dbName);
				}
			} catch (ArangoDBException e) {
				System.err.println("Failed to create database: " + dbName + "; " + e.getMessage());
				throw new EOLIncrementalExecutionException("Failed to create the databse: " + dbName);
			} 
		}
		dao = new TraceArangoDbDAO(arangoDB, dbName, graphName);
		if (online) {
			// FIXME createGraph does not work without setupSchema... if the collections already exists... what to do?
			dao.setupSchema();
			//dao.createGraph();
		} else {
			if (setupSchema) {
				dao.setupSchema();
			}
//			if (createGraph) {
//				dao.createGraph();
//			}
		}
		
	}

	@Override
	public void configure(StringProperties configParameters) {
		dbName = configParameters.getProperty(ArangoDBConfiguration.DB_NAME);
		online = configParameters.getBooleanProperty(IIncrementalModule.ONLINE_MODE, true);
		if (online) {
			graphName = "onlineGraph";
			setupSchema = true;
		}
		else {
			graphName = configParameters.getProperty(ArangoDBConfiguration.DB_GRAPH_NAME);
			setupSchema = configParameters.getBooleanProperty(ArangoDBConfiguration.DB_SETUP_SCHEMA, false);
			createGraph = configParameters.getBooleanProperty(ArangoDBConfiguration.DB_CREATE_GRAPH, false);
		}
		useDefaultCfg = configParameters.getBooleanProperty(ArangoDBConfiguration.DB_USE_DEFAULT, true);
		if (!useDefaultCfg) {
			host = configParameters.getProperty(ArangoDBConfiguration.DB_HOST);
			port = configParameters.getIntegerProperty(ArangoDBConfiguration.DB_PORT, 8529);
			user = configParameters.getProperty(ArangoDBConfiguration.DB_USER);
			password = configParameters.getProperty(ArangoDBConfiguration.DB_PASSWORD);
			useSsl = configParameters.getBooleanProperty(ArangoDBConfiguration.DB_USE_SSL, false);
			chunksize = configParameters.getIntegerProperty(ArangoDBConfiguration.DB_CHUNK_SIZE, 30000);
			maxConnections = configParameters.getIntegerProperty(ArangoDBConfiguration.DB_MAX_CONNECTIONS, 1);
			String protName = configParameters.getProperty(ArangoDBConfiguration.DB_PROTOCOL);
			switch(protName) {
			case "HTTP_JSON":
				protocol = Protocol.HTTP_JSON;
				break;
			case "HTTP_VSTAK":
				protocol = Protocol.HTTP_VPACK;
				break;
			case "VST":
			default:
				protocol = Protocol.VST;
			}
			 
		}
		
	}

	@Override
	public boolean createExecutionTrace(String moduleElementId, String elementId, String propertyName)
			throws EOLIncrementalExecutionException {
		
		ModuleElement moduE = acquireModuleElement(moduleElementId);
		ModelElement modeE = acquireModelElement(elementId);
		Trace trace = acquireTrace(moduE, modeE);
		Property property = findProperty(trace, modeE, propertyName);
		if (property == null) {
			property = dao.createProperty(propertyName);
			dao.createEdge(trace.getId(), property.getId(), TraceArangoDbDAO.EDGE_COLLECTION_ACCESSES);
			dao.createEdge(modeE.getId(), property.getId(), TraceArangoDbDAO.EDGE_COLLECTION_OWNS);
			return true;
		}
		return false;
	}

	@Override
	public boolean createExecutionTraces(String moduleElementId, String elementId, List<String> properties)
			throws EOLIncrementalExecutionException {
		
		for (String p : properties) {
			if (!createExecutionTrace(moduleElementId, elementId, p)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void eventExecutionFinished() throws EOLIncrementalExecutionException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void eventExecutionStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trace> findExecutionTraces(String objectId) throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 3 OUTBOUND @ecId @@I, INBOUND @@R, INBOUND @@C"
				+ "			FILTER p.vertices[1].elementId == @elementId"
				+ "			FILTER p.vertices[3]._id == @ecId"
				+ "			RETURN p.vertices[2]";
	    Map<String, Object> bindVars = new MapBuilder()
	    		.put("@C", TraceArangoDbDAO.EDGE_COLLECTION_CONTAINS)
	    		.put("@R", TraceArangoDbDAO.EDGE_COLLECTION_REACHES)
	    		.put("@I", TraceArangoDbDAO.EDGE_COLLECTION_INVOLVES)
	    		.put("ecId", executionContextId)
	            .put("elementId", objectId)
	            .get();
		List<Trace> result = new ArrayList<Trace>();
		result.addAll(dao.executeQuery(query, bindVars, TraceArangoDbImpl.class));
		return result;
	}

	@Override
	public List<Trace> findExecutionTraces(String objectId, String propertyName)
			throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 4 OUTBOUND @ecId @@I, @@O, INBOUND @@A, @@R"
				+ "			FILTER p.vertices[1].elementId == @elementId"
				+ "			FILTER p.vertices[2].name == @name"
				+ "			FILTER p.vertices[4].elementId == @elementId"
				+ "			RETURN p.vertices[3]";
	    Map<String, Object> bindVars = new MapBuilder()
	    		.put("@F", TraceArangoDbDAO.EDGE_COLLECTION_FOR)
	    		.put("@T", TraceArangoDbDAO.EDGE_COLLECTION_TRACES)
	    		.put("@R", TraceArangoDbDAO.EDGE_COLLECTION_REACHES)
	    		.put("@I", TraceArangoDbDAO.EDGE_COLLECTION_INVOLVES)
	    		.put("ecId", executionContextId)
	            .put("elementId", objectId)
	            .put("name", propertyName)
	            .get();
		List<Trace> result = new ArrayList<Trace>();
		result.addAll(dao.executeQuery(query, bindVars, TraceArangoDbImpl.class));
		return result;
	}

	
	@Override
	public void liveExecutionFinished() throws EOLIncrementalExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liveExecutionStarted() throws EOLIncrementalExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExecutionContext(String scriptId, List<String> models) throws EOLIncrementalExecutionException {
		ExecutionContextArangoDbImpl ec = acquireExecutionContext(scriptId, models);
		executionContextId = ec.getId();		
	}

	private ExecutionContextArangoDbImpl acquireExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		ExecutionContextArangoDbImpl ec = findExecutionContext(scriptId, modelsIds);
		if (ec == null) {
			ec = createExecutionContext(scriptId, modelsIds);
		}
		return ec;
	}

	private ModelElement acquireModelElement(String elementId) throws EOLIncrementalExecutionException {
		ModelElement moduE = findModelElement(elementId);
		if (moduE == null) {
			moduE = createModelElement(elementId);
		}
		return moduE;
	}

	private ModuleElement acquireModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		
		ModuleElement moduE = findModuleElement(moduleElementId);
		if (moduE == null) {
			moduE = createModuleElement(moduleElementId);
		}
		return moduE;
	}

	private Trace acquireTrace(ModuleElement moduE, ModelElement modeE) throws EOLIncrementalExecutionException {
		Trace trace = findTrace(moduE, modeE);
		if (trace == null) {
			trace = createTrace(moduE, modeE);
		}
		return trace;
	}

	private ExecutionContextArangoDbImpl createExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		ExecutionContext ec = dao.createExecutionContext(scriptId, modelsIds);
		if (ec == null) {
			throw new EOLIncrementalExecutionException("Error creating the DB vertex in the DB.");
		}
		return (ExecutionContextArangoDbImpl) ec;
	}

	private ModelElement createModelElement(String elementId) throws EOLIncrementalExecutionException {
		ModelElement me = dao.createModelElement(elementId);
		dao.createEdge(executionContextId, me.getId(), "Involves");
		return me;
	}
	
	
	private ModuleElement createModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		
		ModuleElement me = dao.createModuleElement(moduleElementId);
		dao.createEdge(executionContextId, me.getId(), "For");
		return me;
	}

	private Trace createTrace(ModuleElement moduE, ModelElement modeE) throws EOLIncrementalExecutionException {
		TraceArangoDbImpl trace = (TraceArangoDbImpl) dao.createTrace();
		dao.createEdge(trace.getId(), moduE.getId(), "Traces");
		dao.createEdge(trace.getId(), modeE.getId(), "Reaches");
		dao.createEdge(executionContextId, trace.getId(), "Contains");
		return trace;
	}

	private ExecutionContextArangoDbImpl findExecutionContext(String scriptId, List<String> modelsIds) throws EOLIncrementalExecutionException {
		String query = "FOR ec IN @@collection"
				+ "			FILTER ec.script_id == @scriptId AND ec.modelsIds ALL IN @modelsIds"
				+ "			RETURN ec";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@collection", TraceArangoDbDAO.VERTEX_COLLECTION_MODULE_ELEMENT)
                .put("scriptId", scriptId)
                .put("modelsIds", modelsIds)
                .get();
		List<ExecutionContextArangoDbImpl> me = dao.executeQuery(query, bindVars, ExecutionContextArangoDbImpl.class);
		if (me.isEmpty()) {
			return null;
		}
		return me.get(0);
	}

	private ModelElement findModelElement(String elementId) throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 1 OUTBOUND @ecId @@I"
					+  "	FILTER p.vertices[1].elementId == @elementId"
					+ "		RETURN p.vertices[1]";
        Map<String, Object> bindVars = new MapBuilder()
        		.put("@I", TraceArangoDbDAO.EDGE_COLLECTION_INVOLVES)
        		.put("ecId", executionContextId)
                .put("elementId", elementId)
                .get();
		List<ModelElementArangoDbImpl> qResult = dao.executeQuery(query, bindVars, ModelElementArangoDbImpl.class);
		if (qResult.isEmpty()) {
			return null;
		}
		return qResult.get(0);
	}

	/**
	 * A module element's id is only valid in the execution context
	 * @param moduleElementId
	 * @return
	 * @throws EOLIncrementalExecutionException 
	 */
	private ModuleElement findModuleElement(String moduleElementId) throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 1 OUTBOUND @ecId @@F"
				+  "	FILTER p.vertices[1].moduleId == @moduleId"
				+ "		RETURN p.vertices[1]";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@F", TraceArangoDbDAO.EDGE_COLLECTION_FOR)
                .put("ecId", executionContextId)
                .put("moduleId", moduleElementId)
                .get();
        
		List<ModuleElementArangoDbImpl> qResult = dao.executeQuery(query, bindVars, ModuleElementArangoDbImpl.class);
		if (qResult.isEmpty()) {
			return null;
		}
		return qResult.get(0);
	}

	private Property findProperty(Trace trace, ModelElement modeE, String propertyName) throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 3 OUTBOUND @trace @@R, @@O, INBOUND @@A"
				+ "        FILTER p.vertices[1].elementId == @elementId"
				+ "        FILTER p.vertices[2].name == @pname"
				+ "        RETURN p.vertices[2]";
	    Map<String, Object> bindVars = new MapBuilder()
	    		.put("@R", TraceArangoDbDAO.EDGE_COLLECTION_REACHES)
	    		.put("@O", TraceArangoDbDAO.EDGE_COLLECTION_OWNS)
	    		.put("@A", TraceArangoDbDAO.EDGE_COLLECTION_ACCESSES)
	    		.put("trace", trace.getId())
	            .put("elementId", modeE.getId())
	            .put("pname", propertyName)
	            .get();
		List<PropertyArangoDbImpl> qResult = dao.executeQuery(query, bindVars, PropertyArangoDbImpl.class);
		if (qResult.isEmpty()) {
			return null;
		}
		return qResult.get(0);
	}

	private Trace findTrace(ModuleElement moduE, ModelElement modeE) throws EOLIncrementalExecutionException {
		String query = "FOR v,e,p IN 4 OUTBOUND @ecId @@F, INBOUND @@T, @@R, INBOUND @@I"
				+ "			FILTER p.vertices[1].moduleId == @moduleId"
				+ "			FILTER p.vertices[3].elementId == @elementId"
				+ "			FILTER p.vertices[4]._id == @ecId"
				+ "			RETURN p.vertices[2]";
	    Map<String, Object> bindVars = new MapBuilder()
	    		.put("@F", TraceArangoDbDAO.EDGE_COLLECTION_FOR)
	    		.put("@T", TraceArangoDbDAO.EDGE_COLLECTION_TRACES)
	    		.put("@R", TraceArangoDbDAO.EDGE_COLLECTION_REACHES)
	    		.put("@I", TraceArangoDbDAO.EDGE_COLLECTION_INVOLVES)
	    		.put("ecId", executionContextId)
	            .put("elementId", modeE.getId())
	            .put("moduleId", moduE.getId())
	            .get();
		List<TraceArangoDbImpl> qResult = dao.executeQuery(query, bindVars, TraceArangoDbImpl.class);
		if (qResult.isEmpty()) {
			return null;
		}
		return qResult.get(0);
	}


}
