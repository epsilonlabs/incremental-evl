/*******************************************************************************
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

/*******************************************************************************
 ** trace OrientDB Node Interface automatically generated
 ** on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.parse.Eol_EolParserRules.returnStatement_return;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.*;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.FramedTransactionalGraph;

/**
 * The OrientDbDAO provides methods for CRUD operations on the graph db. 
 */
public class TraceOrientDbDAO2 {

	private OrientGraphFactory factory;
	
	private final FramedGraphFactory framedFactory = new FramedGraphFactory();

	private boolean micro;

	private OrientBaseGraph graphInstance;

	private boolean nonT;

	private boolean useTx;
	
	public TraceOrientDbDAO2(OrientGraphFactory factory) {
		this.factory = factory;
	}
	
	// Schema Methods
	
    /**
     * Setup the DB schema. This operation uses a Non-Transactional Graph. Extending classes should implement the 
     * {@link #createDbTypes(OrientGraphNoTx)} to add the required vertex and edge types to the db.
     */
    public void setupSchema() {
        OrientGraphNoTx graph = factory.getNoTx();
        createDbTypes(graph);
        graph.shutdown();
    }
	
	/**
     * Add vertex types to OrientDB, define keys and setup node properties
     *
     * @param graph The graph to add vertex types to
     */
    private void createDbTypes(OrientBaseGraph g) {
        
  
        
  
        OrientVertexType propertyType = g.getVertexType(PropertyOrientDbImpl.VERTEX_TYPE);
        if (propertyType  == null) {
            propertyType  = g.createVertexType(PropertyOrientDbImpl.VERTEX_TYPE);       
            propertyType.createProperty(PropertyOrientDbImpl.NAME, OType.STRING);
            propertyType.createProperty(PropertyOrientDbImpl.VALUE, OType.STRING);
            g.createKeyIndex(PropertyOrientDbImpl.NAME, Vertex.class,
                    new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", PropertyOrientDbImpl.VERTEX_TYPE));
        }
        setupEdgeTypes(g, EFor.TRACE_TYPE, EContains.TRACE_TYPE, EInvolves.TRACE_TYPE, ETraces.TRACE_TYPE, EReaches.TRACE_TYPE, EAccesses.TRACE_TYPE, EOwns.TRACE_TYPE);
    }
    
   /**
     * Add edge types to the OrientDB.
     *
     * @param graph the graph
     * @param types the types for the edges
     */
    private void setupEdgeTypes(OrientBaseGraph g, String... types) {
        for (String type : types) {
            if (g.getEdgeType(type) == null) g.createEdgeType(type);
        }
    }
    
    // Direct DB Access
    
    public FramedTransactionalGraph<OrientGraph> getManager() {
        
        OrientGraph txGraph = factory.getTx();
        return framedFactory.create(txGraph);
    }   
    
    // CRUD Methods
    public void setGraphInstance(boolean useTx) {
    	if (useTx) {
    		this.graphInstance = factory.getTx();
    	}
    	else {
    		this.graphInstance = factory.getNoTx();
    		
    	}
    	this.useTx = useTx;
    }
    
    /**
     * Configure the DAO to use micro transactions. In this mode, each CRUD operation is atomic, i.e. a new transaction
     * graph is requested when entering the operation and changes commited/rollbacked as necessary and the graph
     * is shutdown before exiting the operation.
     * 
     * If not in micro mode, the caller is responsible for requesting the graph instance (via {@link #setGraphInstance(boolean)}
     * and performing the graph control.
     * 
     * @param micro
     */
    public void setMicroTransactions(boolean micro) {
    	this.micro = micro;
    }
    
    /**
     * Rollback transactions, only meaningful if using a tx graph.
     */
    public void rollback() {
    	graphInstance.rollback();
    }
    
    /**
     * Shoutdown the previoulsy requesed graphInstance
     */
	public void shutdown() {
		graphInstance.shutdown();
		graphInstance = null;
	}
	
	/**
	 * Commit transactions, only meaningful if using a tx graph.
	 * 
	 * Note that the first graph addXXX operation implicitly starts a transaction.
	 */
	public void commit() {
		graphInstance.commit();
	}
	
	
	public PropertyOrientDbImpl createProperty(String name, String value) {
		setGraphInstance(micro);
		OrientVertex vertex = null;
	    try{
			vertex = graphInstance.addVertex("class:Property",
					"name", name,
					"value", value);
			if (micro) {
				commit();
			}
		} catch (Exception e) {
			if (micro) {
				rollback();
			}
			else {
				throw e;
			}
		}
	    if (micro) {
	    	shutdown();
	    }
		return new PropertyOrientDbImpl(vertex);
	}



	public void deleteProperty(PropertyOrientDbImpl vertex) {
		setGraphInstance(micro);
	    try{
	    	graphInstance.removeVertex(vertex.getDelegate());
	    	if (micro) {
				commit();
			}
		} catch (Exception e) {
			if (micro) {
				rollback();
			}
			else {
				throw e;
			}
		}    
	    if (micro) {
	    	shutdown();
	    }
	}
	
	
	public PropertyOrientDbImpl getPropertyById(Object id) {
        setGraphInstance(micro);
        PropertyOrientDbImpl result = null;
        OrientVertex vertex = graphInstance.getVertex(id);
        if (vertex != null) {
        	result = new PropertyOrientDbImpl(vertex);
        }
        if (micro) {
	    	shutdown();
	    }
        return result;
    }
    
    public List<VProperty> getPropertyByIndex(java.lang.String name) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        List<VProperty> vertices = new ArrayList<>();
        for (Vertex v : manager.getVertices("Property.name", name)) {
            vertices.add(manager.frame(v, VProperty.class));
        }
        manager.shutdown();
        return vertices;
    }    
}

