 /*******************************************************************************
 * This file was automatically generated on: Tue Oct 17 12:38:47 BST 2017.
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

import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.*;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.FramedTransactionalGraph;

/**
 * The OrientDbDAO provides methods for CRUD operations on the graph db. 
 */
public class EvlOrientDbDAO {

	private OrientGraphFactory factory;
	
	public EvlOrientDbDAO(OrientGraphFactory factory) {
		this.factory = factory;
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
    /*
    public FramedTransactionalGraph<OrientGraph> getManager() {
        
        OrientGraph txGraph = factory.getTx();
        return framedFactory.create(txGraph);
    } 
    */  
    
}

