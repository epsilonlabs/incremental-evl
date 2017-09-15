/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/

/*******************************************************************************
 ** trace OrientDB Node Interface automatically generated
 ** on Sat Sep 09 20:01:33 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.incremental.arangodb.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.epsilon.eol.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.generation.*;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TraceElement;
import org.eclipse.epsilon.incremental.arangodb.trace.impl.*;
import org.json.simple.JSONObject;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.entity.GraphEntity;
import com.arangodb.entity.VertexEntity;
import com.arangodb.util.MapBuilder;

/**
 * The OrientDbDAO provides methods for CRUD operations on the graph db. 
 */
public class TraceArangoDbDAO {

    public static final String VERTEX_COLLECTION_EXECUTION_CONTEXT = "ExecutionContext";    
    
    public static final String VERTEX_COLLECTION_MODULE_ELEMENT = "ModuleElement";    
    
    public static final String VERTEX_COLLECTION_TRACE = "Trace";    
    
    public static final String VERTEX_COLLECTION_MODEL_ELEMENT = "ModelElement";    
    
    public static final String VERTEX_COLLECTION_PROPERTY = "Property";    
    
    public static final String EDGE_COLLECTION_FOR = "For";    

    public static final String EDGE_COLLECTION_CONTAINS = "Contains";    

    public static final String EDGE_COLLECTION_INVOLVES = "Involves";    

    public static final String EDGE_COLLECTION_TRACES = "Traces";    

    public static final String EDGE_COLLECTION_REACHES = "Reaches";    

    public static final String EDGE_COLLECTION_ACCESSES = "Accesses";    

    public static final String EDGE_COLLECTION_OWNS = "Owns";    

    
    /**
     * The name of the graph to store the traces
     */
    private String graphName;
    
    /**
     * The ArangoDB driver instance to connect to the DB.
     */ 
    private final ArangoDB arangoDB;
    
    /**
     * The name of the database that this DAO will use
     */
    private final String dbName;
    
    /**
     * Create a new ArangoDB DAO
     */        
    public TraceArangoDbDAO(ArangoDB arangoDB, String dbName, String graphName) {
        super();
        this.arangoDB = arangoDB;
        this.dbName = dbName;
        this.graphName = graphName;
    }
    
    /**
     * Setup the DB schema. In ArangoDB edges need to be created first via EdgeDefinitions.
     */
    public void setupSchema() {
    
        // Edge definitions of the graph
        List<EdgeDefinition> edgeDefinitions = new ArrayList<EdgeDefinition>();
                        
        // for edge definition:
        EdgeDefinition edgeFor = new EdgeDefinition();
        edgeFor.collection(EDGE_COLLECTION_FOR);
        edgeFor.from(VERTEX_COLLECTION_EXECUTION_CONTEXT);
        edgeFor.to(VERTEX_COLLECTION_MODULE_ELEMENT);
        edgeDefinitions.add(edgeFor);
                        
        // contains edge definition:
        EdgeDefinition edgeContains = new EdgeDefinition();
        edgeContains.collection(EDGE_COLLECTION_CONTAINS);
        edgeContains.from(VERTEX_COLLECTION_EXECUTION_CONTEXT);
        edgeContains.to(VERTEX_COLLECTION_TRACE);
        edgeDefinitions.add(edgeContains);
                        
        // involves edge definition:
        EdgeDefinition edgeInvolves = new EdgeDefinition();
        edgeInvolves.collection(EDGE_COLLECTION_INVOLVES);
        edgeInvolves.from(VERTEX_COLLECTION_EXECUTION_CONTEXT);
        edgeInvolves.to(VERTEX_COLLECTION_MODEL_ELEMENT);
        edgeDefinitions.add(edgeInvolves);
                        
        // traces edge definition:
        EdgeDefinition edgeTraces = new EdgeDefinition();
        edgeTraces.collection(EDGE_COLLECTION_TRACES);
        edgeTraces.from(VERTEX_COLLECTION_TRACE);
        edgeTraces.to(VERTEX_COLLECTION_MODULE_ELEMENT);
        edgeDefinitions.add(edgeTraces);
                        
        // reaches edge definition:
        EdgeDefinition edgeReaches = new EdgeDefinition();
        edgeReaches.collection(EDGE_COLLECTION_REACHES);
        edgeReaches.from(VERTEX_COLLECTION_TRACE);
        edgeReaches.to(VERTEX_COLLECTION_MODEL_ELEMENT);
        edgeDefinitions.add(edgeReaches);
                        
        // accesses edge definition:
        EdgeDefinition edgeAccesses = new EdgeDefinition();
        edgeAccesses.collection(EDGE_COLLECTION_ACCESSES);
        edgeAccesses.from(VERTEX_COLLECTION_TRACE);
        edgeAccesses.to(VERTEX_COLLECTION_PROPERTY);
        edgeDefinitions.add(edgeAccesses);
                        
        // owns edge definition:
        EdgeDefinition edgeOwns = new EdgeDefinition();
        edgeOwns.collection(EDGE_COLLECTION_OWNS);
        edgeOwns.from(VERTEX_COLLECTION_MODEL_ELEMENT);
        edgeOwns.to(VERTEX_COLLECTION_PROPERTY);
        edgeDefinitions.add(edgeOwns);
 
        try {
            GraphEntity gEVLTraces = arangoDB.db(dbName).createGraph(graphName, edgeDefinitions);
        } catch (ArangoDBException ex) {
            // FIXME Add exception management
        }       
    }
    /**
     * Creates a ExecutionContext in the vertex collection
     *
     * @param scriptId
     * @param modelsIds
     
     * @return the new ExecutionContext
     */
    public ExecutionContext createExecutionContext(
            java.lang.String scriptId,
            List<java.lang.String> modelsIds) {
            
        ExecutionContextArangoDbImpl vertex = new ExecutionContextArangoDbImpl(scriptId, modelsIds);
        VertexEntity info = null;
        try {
            info = arangoDB.db(dbName).graph(graphName)
                .vertexCollection(VERTEX_COLLECTION_EXECUTION_CONTEXT).insertVertex(vertex, null);
            vertex.put("_id", info.getId());
            vertex.put("_key", info.getKey());
            vertex.put("_rev", info.getRev());
        } catch (ArangoDBException ex) {
            // FIXME log?
            vertex = null;
        }
        return vertex;
    }
    
    public ExecutionContext getExecutionContextById(Object id) {
        String query = "RETURN DOCUMENT(@vertexId)";
        Map<String, Object> bindVars = new MapBuilder().put("vertexId", id).get();
        ExecutionContext  vertex = null;
        try {
            ArangoCursor<ExecutionContextArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ExecutionContextArangoDbImpl.class);
            vertex = result.next();
        } catch (ArangoDBException ex) {
            
        }
        return vertex;
    }
    
    public List<ExecutionContext> getExecutionContextByIndex(java.lang.String scriptId) {
	            
        String query = "FOR v IN @@collection FILTER v.scriptId == @scriptId return v";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@collection", VERTEX_COLLECTION_EXECUTION_CONTEXT)
                .put("scriptId", scriptId)
                .get();
        List<ExecutionContext > vertices = new ArrayList<>();
        try {
            ArangoCursor<ExecutionContextArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ExecutionContextArangoDbImpl.class);
            while (result.hasNext()) {
                vertices.add(result.next());
            }
        } catch (ArangoDBException ex) {
            
        }
        return vertices;
    }
    
    /**
     * Creates a ModuleElement in the vertex collection
     *
     * @param moduleId
     
     * @return the new ModuleElement
     */
    public ModuleElement createModuleElement(
            java.lang.String moduleId) {
            
        ModuleElementArangoDbImpl vertex = new ModuleElementArangoDbImpl(moduleId);
        VertexEntity info = null;
        try {
            info = arangoDB.db(dbName).graph(graphName)
                .vertexCollection(VERTEX_COLLECTION_MODULE_ELEMENT).insertVertex(vertex, null);
            vertex.put("_id", info.getId());
            vertex.put("_key", info.getKey());
            vertex.put("_rev", info.getRev());
        } catch (ArangoDBException ex) {
            // FIXME log?
            vertex = null;
        }
        return vertex;
    }
    
    public ModuleElement getModuleElementById(Object id) {
        String query = "RETURN DOCUMENT(@vertexId)";
        Map<String, Object> bindVars = new MapBuilder().put("vertexId", id).get();
        ModuleElement  vertex = null;
        try {
            ArangoCursor<ModuleElementArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ModuleElementArangoDbImpl.class);
            vertex = result.next();
        } catch (ArangoDBException ex) {
            
        }
        return vertex;
    }
    
    public List<ModuleElement> getModuleElementByIndex(java.lang.String moduleId) {
	            
        String query = "FOR v IN @@collection FILTER v.moduleId == @moduleId return v";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@collection", VERTEX_COLLECTION_MODULE_ELEMENT)
                .put("moduleId", moduleId)
                .get();
        List<ModuleElement > vertices = new ArrayList<>();
        try {
            ArangoCursor<ModuleElementArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ModuleElementArangoDbImpl.class);
            while (result.hasNext()) {
                vertices.add(result.next());
            }
        } catch (ArangoDBException ex) {
            
        }
        return vertices;
    }
    
    /**
     * Creates a Trace in the vertex collection
     *
     
     * @return the new Trace
     */
    public Trace createTrace(
            ) {
            
        TraceArangoDbImpl vertex = new TraceArangoDbImpl();
        VertexEntity info = null;
        try {
            info = arangoDB.db(dbName).graph(graphName)
                .vertexCollection(VERTEX_COLLECTION_TRACE).insertVertex(vertex, null);
            vertex.put("_id", info.getId());
            vertex.put("_key", info.getKey());
            vertex.put("_rev", info.getRev());
        } catch (ArangoDBException ex) {
            // FIXME log?
            vertex = null;
        }
        return vertex;
    }
    
    public Trace getTraceById(Object id) {
        String query = "RETURN DOCUMENT(@vertexId)";
        Map<String, Object> bindVars = new MapBuilder().put("vertexId", id).get();
        Trace  vertex = null;
        try {
            ArangoCursor<TraceArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, TraceArangoDbImpl.class);
            vertex = result.next();
        } catch (ArangoDBException ex) {
            
        }
        return vertex;
    }
    /**
     * Creates a ModelElement in the vertex collection
     *
     * @param elementId
     
     * @return the new ModelElement
     */
    public ModelElement createModelElement(
            java.lang.String elementId) {
            
        ModelElementArangoDbImpl vertex = new ModelElementArangoDbImpl(elementId);
        VertexEntity info = null;
        try {
            info = arangoDB.db(dbName).graph(graphName)
                .vertexCollection(VERTEX_COLLECTION_MODEL_ELEMENT).insertVertex(vertex, null);
            vertex.put("_id", info.getId());
            vertex.put("_key", info.getKey());
            vertex.put("_rev", info.getRev());
        } catch (ArangoDBException ex) {
            // FIXME log?
            vertex = null;
        }
        return vertex;
    }
    
    public ModelElement getModelElementById(Object id) {
        String query = "RETURN DOCUMENT(@vertexId)";
        Map<String, Object> bindVars = new MapBuilder().put("vertexId", id).get();
        ModelElement  vertex = null;
        try {
            ArangoCursor<ModelElementArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ModelElementArangoDbImpl.class);
            vertex = result.next();
        } catch (ArangoDBException ex) {
            
        }
        return vertex;
    }
    
    public List<ModelElement> getModelElementByIndex(java.lang.String elementId) {
	            
        String query = "FOR v IN @@collection FILTER v.elementId == @elementId return v";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@collection", VERTEX_COLLECTION_MODEL_ELEMENT)
                .put("elementId", elementId)
                .get();
        List<ModelElement > vertices = new ArrayList<>();
        try {
            ArangoCursor<ModelElementArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, ModelElementArangoDbImpl.class);
            while (result.hasNext()) {
                vertices.add(result.next());
            }
        } catch (ArangoDBException ex) {
            
        }
        return vertices;
    }
    
    /**
     * Creates a Property in the vertex collection
     *
     * @param name
     
     * @return the new Property
     */
    public Property createProperty(
            java.lang.String name) {
            
        PropertyArangoDbImpl vertex = new PropertyArangoDbImpl(name);
        VertexEntity info = null;
        try {
            info = arangoDB.db(dbName).graph(graphName)
                .vertexCollection(VERTEX_COLLECTION_PROPERTY).insertVertex(vertex, null);
            vertex.put("_id", info.getId());
            vertex.put("_key", info.getKey());
            vertex.put("_rev", info.getRev());
        } catch (ArangoDBException ex) {
            // FIXME log?
            vertex = null;
        }
        return vertex;
    }
    
    public Property getPropertyById(Object id) {
        String query = "RETURN DOCUMENT(@vertexId)";
        Map<String, Object> bindVars = new MapBuilder().put("vertexId", id).get();
        Property  vertex = null;
        try {
            ArangoCursor<PropertyArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, PropertyArangoDbImpl.class);
            vertex = result.next();
        } catch (ArangoDBException ex) {
            
        }
        return vertex;
    }
    
    public List<Property> getPropertyByIndex(java.lang.String name) {
	            
        String query = "FOR v IN @@collection FILTER v.name == @name return v";
        Map<String, Object> bindVars = new MapBuilder()
                .put("@collection", VERTEX_COLLECTION_PROPERTY)
                .put("name", name)
                .get();
        List<Property > vertices = new ArrayList<>();
        try {
            ArangoCursor<PropertyArangoDbImpl> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, PropertyArangoDbImpl.class);
            while (result.hasNext()) {
                vertices.add(result.next());
            }
        } catch (ArangoDBException ex) {
            
        }
        return vertices;
    }
    
    
    
    /*
     * Generic wrapper for executing queries on the db. Vertex are assumed to be returned.
     */
    public <T, W extends TraceElement> List<W> executeQuery(String query, Map<String, Object> bindVars, Class<W> T) throws EOLIncrementalExecutionException {
        List<W > vertices = new ArrayList<>();
        try {
            ArangoCursor<W> result = arangoDB.db(dbName)
                    .query(query, bindVars, null, T);
            while (result.hasNext()) {
                vertices.add(result.next());
            }
        } catch (ArangoDBException ex) {
            throw new EOLIncrementalExecutionException("Error executing query on DB.", ex);
        }
        return vertices;
    }
    
    public void createEdge(Object from_id, Object to_id, String edgeCollection) throws EOLIncrementalExecutionException {
        JSONObject edge = new JSONObject();
        edge.put("_from", from_id);
        edge.put("_to", to_id);
        try {
            arangoDB.db(dbName).graph(graphName)
                .edgeCollection(edgeCollection).insertEdge(edge.toJSONString());
        } catch (ArangoDBException ex) {
            throw new EOLIncrementalExecutionException("Can't create edge in DB.", ex);
        }
        
    }
    
}
