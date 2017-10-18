 /*******************************************************************************
 * This file was automatically generated on: Wed Oct 18 11:16:55 BST 2017.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.*;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.storage.ORecordDuplicatedException;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * The OrientDbDAO provides methods for CRUD operations on the graph db. 
 */
public class EvlOrientDbDAO {

    /** Factory to retrieve graph instances */
	private OrientGraphFactory factory;
	
	/** The class loger */
	private Logger logger;
	
	public EvlOrientDbDAO(OrientGraphFactory factory) {
		this.factory = factory;
		logger = LoggerFactory.getLogger(EvlOrientDbDAO.class);
	}
	
	// Schema Methods
	
    /**
     * Setup the DB schema. This operation uses a Non-Transactional Graph. Extending classes should implement the 
     * {@link #createDbTypes(OrientGraphNoTx)} to add the required vertex and edge types to the db.
     */
    public static void setupSchema(OrientGraphFactory factory) {
        OrientGraphNoTx graph = factory.getNoTx();
        createDbTypes(graph);
        graph.shutdown();
    }
	
	/**
     * Add vertex types to OrientDB, define keys and setup node properties
     *
     * @param graph The graph to add vertex types to
     */
    private static void createDbTypes(OrientBaseGraph g) {
        
  
        OrientVertexType evlConstraintType = g.getVertexType(EvlConstraintOrientDbImpl.VERTEX_TYPE);
        if (evlConstraintType  == null) {
            evlConstraintType  = g.createVertexType(EvlConstraintOrientDbImpl.VERTEX_TYPE);       
            evlConstraintType.createProperty(EvlConstraintOrientDbImpl.URI, OType.STRING);
            
            g.createKeyIndex(EvlConstraintOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", EvlConstraintOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType evlContextType = g.getVertexType(EvlContextOrientDbImpl.VERTEX_TYPE);
        if (evlContextType  == null) {
            evlContextType  = g.createVertexType(EvlContextOrientDbImpl.VERTEX_TYPE);       
            evlContextType.createProperty(EvlContextOrientDbImpl.URI, OType.STRING);
            
            g.createKeyIndex(EvlContextOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", EvlContextOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType guardType = g.getVertexType(GuardOrientDbImpl.VERTEX_TYPE);
        if (guardType  == null) {
            guardType  = g.createVertexType(GuardOrientDbImpl.VERTEX_TYPE);       
            guardType.createProperty(GuardOrientDbImpl.URI, OType.STRING);
       
            guardType.createProperty(GuardOrientDbImpl.RESULT, OType.STRING);
            
            g.createKeyIndex(GuardOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", GuardOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType checkType = g.getVertexType(CheckOrientDbImpl.VERTEX_TYPE);
        if (checkType  == null) {
            checkType  = g.createVertexType(CheckOrientDbImpl.VERTEX_TYPE);       
            checkType.createProperty(CheckOrientDbImpl.URI, OType.STRING);
       
            checkType.createProperty(CheckOrientDbImpl.RESULT, OType.STRING);
            
            g.createKeyIndex(CheckOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", CheckOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType messageType = g.getVertexType(MessageOrientDbImpl.VERTEX_TYPE);
        if (messageType  == null) {
            messageType  = g.createVertexType(MessageOrientDbImpl.VERTEX_TYPE);       
            messageType.createProperty(MessageOrientDbImpl.URI, OType.STRING);
       
            messageType.createProperty(MessageOrientDbImpl.RESULT, OType.STRING);
            
            g.createKeyIndex(MessageOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", MessageOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType elementTraceType = g.getVertexType(ElementTraceOrientDbImpl.VERTEX_TYPE);
        if (elementTraceType  == null) {
            elementTraceType  = g.createVertexType(ElementTraceOrientDbImpl.VERTEX_TYPE);        }
  
        OrientVertexType modelElementType = g.getVertexType(ModelElementOrientDbImpl.VERTEX_TYPE);
        if (modelElementType  == null) {
            modelElementType  = g.createVertexType(ModelElementOrientDbImpl.VERTEX_TYPE);       
            modelElementType.createProperty(ModelElementOrientDbImpl.URI, OType.STRING);
            
            g.createKeyIndex(ModelElementOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", ModelElementOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType propertyType = g.getVertexType(PropertyOrientDbImpl.VERTEX_TYPE);
        if (propertyType  == null) {
            propertyType  = g.createVertexType(PropertyOrientDbImpl.VERTEX_TYPE);       
            propertyType.createProperty(PropertyOrientDbImpl.URI, OType.STRING);
       
            propertyType.createProperty(PropertyOrientDbImpl.VALUE, OType.STRING);
            
            g.createKeyIndex(PropertyOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", PropertyOrientDbImpl.VERTEX_TYPE));
        }
  
        OrientVertexType typeTraceType = g.getVertexType(TypeTraceOrientDbImpl.VERTEX_TYPE);
        if (typeTraceType  == null) {
            typeTraceType  = g.createVertexType(TypeTraceOrientDbImpl.VERTEX_TYPE);        }
  
        OrientVertexType typeType = g.getVertexType(TypeOrientDbImpl.VERTEX_TYPE);
        if (typeType  == null) {
            typeType  = g.createVertexType(TypeOrientDbImpl.VERTEX_TYPE);       
            typeType.createProperty(TypeOrientDbImpl.URI, OType.STRING);
            
            g.createKeyIndex(TypeOrientDbImpl.VERTEX_INDEX, Vertex.class,
                    new Parameter<String, String>("type", "UNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", TypeOrientDbImpl.VERTEX_TYPE));
        }
        setupEdgeTypes(g, "Blocks", "Owner", "Elements", "Accesses", "Types");
    }
    
   /**
     * Add edge types to the OrientDB.
     *
     * @param graph the graph
     * @param types the types for the edges
     */
    private static void setupEdgeTypes(OrientBaseGraph g, String... types) {
        for (String type : types) {
            if (g.getEdgeType(type) == null) g.createEdgeType(type);
        }
    }
    
    // Direct DB Access
    /**
     * Get a transactional graph. The caller is responsible for the life cycle of the graph.
     * 
     * @return an OrientGraph
     */
    public OrientGraph getTransactionalGraph() {
        
        return factory.getTx();
    }
    
    /**
     * Get a non-transactional graph. The caller is responsible for the life cycle of the graph.
     * 
     * @return an OrientGraphNoTx
     */
    public OrientGraphNoTx getNonTransactionalGraph() {
        
        return factory.getNoTx();
    }
    
    /**
     * Create a vertex of type EvlConstraint
     *
     * @return EvlConstraintOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public EvlConstraintOrientDbImpl createEvlConstraint(
            	String uri) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:EvlConstraint", "uri", uri);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - EvlConstraint:{}", uri);
            v = (OrientVertex) graph.getVertices("EvlConstraint.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - EvlConstraint:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating EvlConstraint", e2);
        }
        logger.debug("Record created - EvlConstraint:{}", uri);
        graph.shutdown();
        return new EvlConstraintOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type EvlConstraint
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteEvlConstraint(EvlConstraintOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - EvlConstraint:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting EvlConstraint", e);
        }
        logger.debug("Record deleted - EvlConstraint:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type EvlConstraint. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateEvlConstraint(EvlConstraintOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - EvlConstraint:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating EvlConstraint", e);
        }
        logger.debug("Record updated - EvlConstraint:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type EvlConstraint by id.
     *
     * @return EvlConstraintOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public EvlConstraintOrientDbImpl getEvlConstraintById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - EvlConstraint:{}", id);
            throw new EolIncrementalExecutionException("No EvlConstraint found with id: " + id);
        }
        graph.shutdown();
        return new EvlConstraintOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type EvlConstraint by index
     * 
     * @return EvlConstraintOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public EvlConstraintOrientDbImpl getEvlConstraintByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("EvlConstraint.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - EvlConstraint:{}", uri);
            throw new EolIncrementalExecutionException("No EvlConstraint found with index (uri): " + uri);
        }
        graph.shutdown();
        return new EvlConstraintOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type EvlContext
     *
     * @return EvlContextOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public EvlContextOrientDbImpl createEvlContext(
            	String uri) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:EvlContext", "uri", uri);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - EvlContext:{}", uri);
            v = (OrientVertex) graph.getVertices("EvlContext.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - EvlContext:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating EvlContext", e2);
        }
        logger.debug("Record created - EvlContext:{}", uri);
        graph.shutdown();
        return new EvlContextOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type EvlContext
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteEvlContext(EvlContextOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - EvlContext:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting EvlContext", e);
        }
        logger.debug("Record deleted - EvlContext:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type EvlContext. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateEvlContext(EvlContextOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - EvlContext:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating EvlContext", e);
        }
        logger.debug("Record updated - EvlContext:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type EvlContext by id.
     *
     * @return EvlContextOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public EvlContextOrientDbImpl getEvlContextById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - EvlContext:{}", id);
            throw new EolIncrementalExecutionException("No EvlContext found with id: " + id);
        }
        graph.shutdown();
        return new EvlContextOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type EvlContext by index
     * 
     * @return EvlContextOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public EvlContextOrientDbImpl getEvlContextByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("EvlContext.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - EvlContext:{}", uri);
            throw new EolIncrementalExecutionException("No EvlContext found with index (uri): " + uri);
        }
        graph.shutdown();
        return new EvlContextOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type Guard
     *
     * @return GuardOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public GuardOrientDbImpl createGuard(
            	String uri,
            	String result) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:Guard", "uri", uri, "result", result);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - Guard:{}", uri);
            v = (OrientVertex) graph.getVertices("Guard.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - Guard:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating Guard", e2);
        }
        logger.debug("Record created - Guard:{}", uri);
        graph.shutdown();
        return new GuardOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type Guard
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteGuard(GuardOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - Guard:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting Guard", e);
        }
        logger.debug("Record deleted - Guard:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type Guard. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateGuard(GuardOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - Guard:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating Guard", e);
        }
        logger.debug("Record updated - Guard:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type Guard by id.
     *
     * @return GuardOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public GuardOrientDbImpl getGuardById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - Guard:{}", id);
            throw new EolIncrementalExecutionException("No Guard found with id: " + id);
        }
        graph.shutdown();
        return new GuardOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type Guard by index
     * 
     * @return GuardOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public GuardOrientDbImpl getGuardByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("Guard.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - Guard:{}", uri);
            throw new EolIncrementalExecutionException("No Guard found with index (uri): " + uri);
        }
        graph.shutdown();
        return new GuardOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type Check
     *
     * @return CheckOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public CheckOrientDbImpl createCheck(
            	String uri,
            	String result) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:Check", "uri", uri, "result", result);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - Check:{}", uri);
            v = (OrientVertex) graph.getVertices("Check.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - Check:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating Check", e2);
        }
        logger.debug("Record created - Check:{}", uri);
        graph.shutdown();
        return new CheckOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type Check
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteCheck(CheckOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - Check:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting Check", e);
        }
        logger.debug("Record deleted - Check:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type Check. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateCheck(CheckOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - Check:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating Check", e);
        }
        logger.debug("Record updated - Check:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type Check by id.
     *
     * @return CheckOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public CheckOrientDbImpl getCheckById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - Check:{}", id);
            throw new EolIncrementalExecutionException("No Check found with id: " + id);
        }
        graph.shutdown();
        return new CheckOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type Check by index
     * 
     * @return CheckOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public CheckOrientDbImpl getCheckByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("Check.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - Check:{}", uri);
            throw new EolIncrementalExecutionException("No Check found with index (uri): " + uri);
        }
        graph.shutdown();
        return new CheckOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type Message
     *
     * @return MessageOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public MessageOrientDbImpl createMessage(
            	String uri,
            	String result) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:Message", "uri", uri, "result", result);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - Message:{}", uri);
            v = (OrientVertex) graph.getVertices("Message.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - Message:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating Message", e2);
        }
        logger.debug("Record created - Message:{}", uri);
        graph.shutdown();
        return new MessageOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type Message
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteMessage(MessageOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - Message:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting Message", e);
        }
        logger.debug("Record deleted - Message:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type Message. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateMessage(MessageOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - Message:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating Message", e);
        }
        logger.debug("Record updated - Message:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type Message by id.
     *
     * @return MessageOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public MessageOrientDbImpl getMessageById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - Message:{}", id);
            throw new EolIncrementalExecutionException("No Message found with id: " + id);
        }
        graph.shutdown();
        return new MessageOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type Message by index
     * 
     * @return MessageOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public MessageOrientDbImpl getMessageByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("Message.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - Message:{}", uri);
            throw new EolIncrementalExecutionException("No Message found with index (uri): " + uri);
        }
        graph.shutdown();
        return new MessageOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type ElementTrace
     *
     * @return ElementTraceOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public ElementTraceOrientDbImpl createElementTrace() throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {        
            v = graph.addVertex("class:ElementTrace");
            graph.commit();   
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record creation - ElementTrace", e);
            throw new EolIncrementalExecutionException("Error creating ElementTrace", e);
        }
        logger.debug("Record created - ElementTrace");
        graph.shutdown();
        return new ElementTraceOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type ElementTrace
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteElementTrace(ElementTraceOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - ElementTrace:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting ElementTrace", e);
        }
        logger.debug("Record deleted - ElementTrace:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type ElementTrace. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateElementTrace(ElementTraceOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - ElementTrace:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating ElementTrace", e);
        }
        logger.debug("Record updated - ElementTrace:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type ElementTrace by id.
     *
     * @return ElementTraceOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public ElementTraceOrientDbImpl getElementTraceById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - ElementTrace:{}", id);
            throw new EolIncrementalExecutionException("No ElementTrace found with id: " + id);
        }
        graph.shutdown();
        return new ElementTraceOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type ModelElement
     *
     * @return ModelElementOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public ModelElementOrientDbImpl createModelElement(
            	String uri) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:ModelElement", "uri", uri);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - ModelElement:{}", uri);
            v = (OrientVertex) graph.getVertices("ModelElement.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - ModelElement:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating ModelElement", e2);
        }
        logger.debug("Record created - ModelElement:{}", uri);
        graph.shutdown();
        return new ModelElementOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type ModelElement
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteModelElement(ModelElementOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - ModelElement:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting ModelElement", e);
        }
        logger.debug("Record deleted - ModelElement:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type ModelElement. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateModelElement(ModelElementOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - ModelElement:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating ModelElement", e);
        }
        logger.debug("Record updated - ModelElement:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type ModelElement by id.
     *
     * @return ModelElementOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public ModelElementOrientDbImpl getModelElementById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - ModelElement:{}", id);
            throw new EolIncrementalExecutionException("No ModelElement found with id: " + id);
        }
        graph.shutdown();
        return new ModelElementOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type ModelElement by index
     * 
     * @return ModelElementOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public ModelElementOrientDbImpl getModelElementByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("ModelElement.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - ModelElement:{}", uri);
            throw new EolIncrementalExecutionException("No ModelElement found with index (uri): " + uri);
        }
        graph.shutdown();
        return new ModelElementOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type Property
     *
     * @return PropertyOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public PropertyOrientDbImpl createProperty(
            	String uri,
            	String value) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:Property", "uri", uri, "value", value);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - Property:{}", uri);
            v = (OrientVertex) graph.getVertices("Property.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - Property:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating Property", e2);
        }
        logger.debug("Record created - Property:{}", uri);
        graph.shutdown();
        return new PropertyOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type Property
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteProperty(PropertyOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - Property:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting Property", e);
        }
        logger.debug("Record deleted - Property:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type Property. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateProperty(PropertyOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - Property:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating Property", e);
        }
        logger.debug("Record updated - Property:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type Property by id.
     *
     * @return PropertyOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public PropertyOrientDbImpl getPropertyById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - Property:{}", id);
            throw new EolIncrementalExecutionException("No Property found with id: " + id);
        }
        graph.shutdown();
        return new PropertyOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type Property by index
     * 
     * @return PropertyOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public PropertyOrientDbImpl getPropertyByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("Property.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - Property:{}", uri);
            throw new EolIncrementalExecutionException("No Property found with index (uri): " + uri);
        }
        graph.shutdown();
        return new PropertyOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type TypeTrace
     *
     * @return TypeTraceOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public TypeTraceOrientDbImpl createTypeTrace() throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {        
            v = graph.addVertex("class:TypeTrace");
            graph.commit();   
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record creation - TypeTrace", e);
            throw new EolIncrementalExecutionException("Error creating TypeTrace", e);
        }
        logger.debug("Record created - TypeTrace");
        graph.shutdown();
        return new TypeTraceOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type TypeTrace
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteTypeTrace(TypeTraceOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - TypeTrace:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting TypeTrace", e);
        }
        logger.debug("Record deleted - TypeTrace:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type TypeTrace. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateTypeTrace(TypeTraceOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - TypeTrace:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating TypeTrace", e);
        }
        logger.debug("Record updated - TypeTrace:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type TypeTrace by id.
     *
     * @return TypeTraceOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public TypeTraceOrientDbImpl getTypeTraceById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - TypeTrace:{}", id);
            throw new EolIncrementalExecutionException("No TypeTrace found with id: " + id);
        }
        graph.shutdown();
        return new TypeTraceOrientDbImpl(vertex);
    }
    
    /**
     * Create a vertex of type Type
     *
     * @return TypeOrientDbImpl wrapping the vertex
     * @throws EolIncrementalExecutionException if there was an error creating the vertex
     */
    public TypeOrientDbImpl createType(
            	String uri) throws EolIncrementalExecutionException {    
        
        OrientGraph graph = factory.getTx();
        OrientVertex v = null;
        try {
            v = graph.addVertex("class:Type", "uri", uri);
            graph.commit();
        } catch (ORecordDuplicatedException e) {
            logger.debug("Duplicate Record creation - Type:{}", uri);
            v = (OrientVertex) graph.getVertices("Type.uri", uri).iterator().next();
            // TODO we would probably want a null test and/or a NoSuchElementException handler
        } catch (Exception e2) {
            graph.rollback();
            logger.debug("Error in Record creation - Type:{}", uri, e2);
            throw new EolIncrementalExecutionException("Error creating Type", e2);
        }
        logger.debug("Record created - Type:{}", uri);
        graph.shutdown();
        return new TypeOrientDbImpl(v);
    }
    
    /**
     * Delete a vertex of type Type
     *
     * @throws EolIncrementalExecutionException if there was an error deleting the vertex
     */
    public void deleteType(TypeOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            graph.removeVertex(vertex.getDelegate());
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record deletion - Type:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error deleting Type", e);
        }
        logger.debug("Record deleted - Type:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Update a a vertex of type Type. This only updates the vertex properties,
     * not its references!
     * 
     * * @throws EolIncrementalExecutionException if there was an error updating the vertex
     */
    public void updateType(TypeOrientDbImpl vertex) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        try{
            vertex.getDelegate().attach(graph);
            vertex.getDelegate().save();
            vertex.getDelegate().detach();
        } catch (Exception e) {
            graph.rollback();
            logger.debug("Error in Record update - Type:{}", vertex.getDelegate(), e);
            throw new EolIncrementalExecutionException("Error updating Type", e);
        }
        logger.debug("Record updated - Type:{}", vertex);
        graph.shutdown();
    }
    
    /**
     * Find a vertex of type Type by id.
     *
     * @return TypeOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException in no vertex for that id is found
     */
    public TypeOrientDbImpl getTypeById(Object id) throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = graph.getVertex(id);
        if (vertex == null) {
            logger.debug("Finding Record by id was null - Type:{}", id);
            throw new EolIncrementalExecutionException("No Type found with id: " + id);
        }
        graph.shutdown();
        return new TypeOrientDbImpl(vertex);
    }
    
    
    /**
     * Find a vertex of type Type by index
     * 
     * @return TypeOrientDbImpl wrapping the vertex, if found
     * @throws EolIncrementalExecutionException if no vertex for that index is found
     */
    public TypeOrientDbImpl getTypeByIndex(String uri)  throws EolIncrementalExecutionException {
        OrientGraph graph = factory.getTx();
        OrientVertex vertex = (OrientVertex) graph.getVertices("Type.uri", uri).iterator().next();
        if (vertex == null) {
            logger.debug("Finding Record by index was null - Type:{}", uri);
            throw new EolIncrementalExecutionException("No Type found with index (uri): " + uri);
        }
        graph.shutdown();
        return new TypeOrientDbImpl(vertex);
    }
    
   
    
}

