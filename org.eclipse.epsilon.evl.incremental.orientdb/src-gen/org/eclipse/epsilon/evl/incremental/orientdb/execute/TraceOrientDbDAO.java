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

import org.eclipse.epsilon.evl.incremental.orientdb.trace.*;

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
public class TraceOrientDbDAO {

	private OrientGraphFactory factory;
	
	private final FramedGraphFactory framedFactory = new FramedGraphFactory();
	
	public TraceOrientDbDAO(OrientGraphFactory factory) {
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
        
  
        OrientVertexType executionContextType = g.getVertexType(VExecutionContext.TRACE_TYPE);
        if (executionContextType  == null) {
            executionContextType  = g.createVertexType(VExecutionContext.TRACE_TYPE);       
            executionContextType.createProperty(VExecutionContext.SCRIPT_ID, OType.STRING);       
            executionContextType.createProperty(VExecutionContext.MODELS_IDS, OType.EMBEDDEDLIST);            
            g.createKeyIndex(VExecutionContext.SCRIPT_ID, Vertex.class,
                    new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", VExecutionContext.TRACE_TYPE));
        }
  
        OrientVertexType moduleElementType = g.getVertexType(VModuleElement.TRACE_TYPE);
        if (moduleElementType  == null) {
            moduleElementType  = g.createVertexType(VModuleElement.TRACE_TYPE);       
            moduleElementType.createProperty(VModuleElement.MODULE_ID, OType.STRING);            
            g.createKeyIndex(VModuleElement.MODULE_ID, Vertex.class,
                    new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", VModuleElement.TRACE_TYPE));
        }
  
        OrientVertexType traceType = g.getVertexType(VTrace.TRACE_TYPE);
        if (traceType  == null) {
            traceType  = g.createVertexType(VTrace.TRACE_TYPE);        }
  
        OrientVertexType modelElementType = g.getVertexType(VModelElement.TRACE_TYPE);
        if (modelElementType  == null) {
            modelElementType  = g.createVertexType(VModelElement.TRACE_TYPE);       
            modelElementType.createProperty(VModelElement.ELEMENT_ID, OType.STRING);            
            g.createKeyIndex(VModelElement.ELEMENT_ID, Vertex.class,
                    new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", VModelElement.TRACE_TYPE));
        }
  
        OrientVertexType propertyType = g.getVertexType(VProperty.TRACE_TYPE);
        if (propertyType  == null) {
            propertyType  = g.createVertexType(VProperty.TRACE_TYPE);       
            propertyType.createProperty(VProperty.NAME, OType.STRING);            
            g.createKeyIndex(VProperty.NAME, Vertex.class,
                    new Parameter<String, String>("type", "NOTUNIQUE_HASH_INDEX"),
                    new Parameter<String, String>("class", VProperty.TRACE_TYPE));
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
	
	public VExecutionContext createExecutionContext(
			java.lang.String scriptId,
			List<java.lang.String>
			 modelsIds) {
	    OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    VExecutionContext vertex = null;
	    try{
			vertex = manager.addVertex("class:ExecutionContext", VExecutionContext.class);
		    vertex.setScriptId(scriptId);
		    vertex.setModelsIds(modelsIds);
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
		return vertex;
	}
	
	public void deleteExecutionContext(VExecutionContext vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			manager.removeVertex(vertex.asVertex());
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public void updateExecutionContext(VExecutionContext vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			VExecutionContext persisted =  manager.getVertex(vertex.asVertex().getId(), VExecutionContext.class);		
		    persisted.setScriptId(vertex.getScriptId());
		    List<java.lang.String> persistedModelsIdsVal = persisted.getModelsIds();
		    persistedModelsIdsVal.addAll(vertex.getModelsIds());
			Iterable<VModuleElement> persistedForValues = vertex.getFor();
			List<VModuleElement> vertexForValues = new ArrayList<>();
			for (VModuleElement nv : vertex.getFor()) {
				vertexForValues.add(nv);
			}
			for (VModuleElement ov : persistedForValues) {
				if (!vertexForValues.contains(ov)) {
					persisted.removeFor(ov);
				}
			}
			for (VModuleElement  nv : vertexForValues ) {
				persisted.addFor(nv);
			}
			Iterable<VTrace> persistedContainsValues = vertex.getContains();
			List<VTrace> vertexContainsValues = new ArrayList<>();
			for (VTrace nv : vertex.getContains()) {
				vertexContainsValues.add(nv);
			}
			for (VTrace ov : persistedContainsValues) {
				if (!vertexContainsValues.contains(ov)) {
					persisted.removeContains(ov);
				}
			}
			for (VTrace  nv : vertexContainsValues ) {
				persisted.addContains(nv);
			}
			Iterable<VModelElement> persistedInvolvesValues = vertex.getInvolves();
			List<VModelElement> vertexInvolvesValues = new ArrayList<>();
			for (VModelElement nv : vertex.getInvolves()) {
				vertexInvolvesValues.add(nv);
			}
			for (VModelElement ov : persistedInvolvesValues) {
				if (!vertexInvolvesValues.contains(ov)) {
					persisted.removeInvolves(ov);
				}
			}
			for (VModelElement  nv : vertexInvolvesValues ) {
				persisted.addInvolves(nv);
			}
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public VExecutionContext getExecutionContextById(Object id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        VExecutionContext vertex = null;
        vertex = manager.frame(manager.getVertex(id), VExecutionContext.class);            
        manager.shutdown();
        return vertex;
    }
    
    public List<VExecutionContext> getExecutionContextByIndex(java.lang.String script_id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        List<VExecutionContext> vertices = new ArrayList<>();
        for (Vertex v : manager.getVertices("ExecutionContext.script_id", script_id)) {
            vertices.add(manager.frame(v, VExecutionContext.class));
        }
        manager.shutdown();
        return vertices;
    }    
	public VModuleElement createModuleElement(
			java.lang.String moduleId) {
	    OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    VModuleElement vertex = null;
	    try{
			vertex = manager.addVertex("class:ModuleElement", VModuleElement.class);
		    vertex.setModuleId(moduleId);
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
		return vertex;
	}
	
	public void deleteModuleElement(VModuleElement vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			manager.removeVertex(vertex.asVertex());
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public void updateModuleElement(VModuleElement vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			VModuleElement persisted =  manager.getVertex(vertex.asVertex().getId(), VModuleElement.class);		
		    persisted.setModuleId(vertex.getModuleId());
			Iterable<VExecutionContext> persistedExecutionContextsValues = vertex.getExecutionContexts();
			List<VExecutionContext> vertexExecutionContextsValues = new ArrayList<>();
			for (VExecutionContext nv : vertex.getExecutionContexts()) {
				vertexExecutionContextsValues.add(nv);
			}
			for (VExecutionContext ov : persistedExecutionContextsValues) {
				if (!vertexExecutionContextsValues.contains(ov)) {
					persisted.removeExecutionContexts(ov);
				}
			}
			for (VExecutionContext  nv : vertexExecutionContextsValues ) {
				persisted.addExecutionContexts(nv);
			}
			Iterable<VTrace> persistedTracesValues = vertex.getTraces();
			List<VTrace> vertexTracesValues = new ArrayList<>();
			for (VTrace nv : vertex.getTraces()) {
				vertexTracesValues.add(nv);
			}
			for (VTrace ov : persistedTracesValues) {
				if (!vertexTracesValues.contains(ov)) {
					persisted.removeTraces(ov);
				}
			}
			for (VTrace  nv : vertexTracesValues ) {
				persisted.addTraces(nv);
			}
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public VModuleElement getModuleElementById(Object id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        VModuleElement vertex = null;
        vertex = manager.frame(manager.getVertex(id), VModuleElement.class);            
        manager.shutdown();
        return vertex;
    }
    
    public List<VModuleElement> getModuleElementByIndex(java.lang.String module_id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        List<VModuleElement> vertices = new ArrayList<>();
        for (Vertex v : manager.getVertices("ModuleElement.module_id", module_id)) {
            vertices.add(manager.frame(v, VModuleElement.class));
        }
        manager.shutdown();
        return vertices;
    }    
	public VTrace createTrace(
			) {
	    OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    VTrace vertex = null;
	    try{
			vertex = manager.addVertex("class:Trace", VTrace.class);
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
		return vertex;
	}
	
	public void deleteTrace(VTrace vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			manager.removeVertex(vertex.asVertex());
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public void updateTrace(VTrace vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			VTrace persisted =  manager.getVertex(vertex.asVertex().getId(), VTrace.class);		
			persisted.setExecutionContext(vertex.getExecutionContext());
			persisted.setTraces(vertex.getTraces());
			Iterable<VModelElement> persistedReachesValues = vertex.getReaches();
			List<VModelElement> vertexReachesValues = new ArrayList<>();
			for (VModelElement nv : vertex.getReaches()) {
				vertexReachesValues.add(nv);
			}
			for (VModelElement ov : persistedReachesValues) {
				if (!vertexReachesValues.contains(ov)) {
					persisted.removeReaches(ov);
				}
			}
			for (VModelElement  nv : vertexReachesValues ) {
				persisted.addReaches(nv);
			}
			Iterable<VProperty> persistedAccessesValues = vertex.getAccesses();
			List<VProperty> vertexAccessesValues = new ArrayList<>();
			for (VProperty nv : vertex.getAccesses()) {
				vertexAccessesValues.add(nv);
			}
			for (VProperty ov : persistedAccessesValues) {
				if (!vertexAccessesValues.contains(ov)) {
					persisted.removeAccesses(ov);
				}
			}
			for (VProperty  nv : vertexAccessesValues ) {
				persisted.addAccesses(nv);
			}
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public VTrace getTraceById(Object id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        VTrace vertex = null;
        vertex = manager.frame(manager.getVertex(id), VTrace.class);            
        manager.shutdown();
        return vertex;
    }
    
	public VModelElement createModelElement(
			java.lang.String elementId) {
	    OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    VModelElement vertex = null;
	    try{
			vertex = manager.addVertex("class:ModelElement", VModelElement.class);
		    vertex.setElementId(elementId);
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
		return vertex;
	}
	
	public void deleteModelElement(VModelElement vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			manager.removeVertex(vertex.asVertex());
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public void updateModelElement(VModelElement vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			VModelElement persisted =  manager.getVertex(vertex.asVertex().getId(), VModelElement.class);		
		    persisted.setElementId(vertex.getElementId());
			Iterable<VExecutionContext> persistedExecutionContextValues = vertex.getExecutionContext();
			List<VExecutionContext> vertexExecutionContextValues = new ArrayList<>();
			for (VExecutionContext nv : vertex.getExecutionContext()) {
				vertexExecutionContextValues.add(nv);
			}
			for (VExecutionContext ov : persistedExecutionContextValues) {
				if (!vertexExecutionContextValues.contains(ov)) {
					persisted.removeExecutionContext(ov);
				}
			}
			for (VExecutionContext  nv : vertexExecutionContextValues ) {
				persisted.addExecutionContext(nv);
			}
			Iterable<VTrace> persistedTracesValues = vertex.getTraces();
			List<VTrace> vertexTracesValues = new ArrayList<>();
			for (VTrace nv : vertex.getTraces()) {
				vertexTracesValues.add(nv);
			}
			for (VTrace ov : persistedTracesValues) {
				if (!vertexTracesValues.contains(ov)) {
					persisted.removeTraces(ov);
				}
			}
			for (VTrace  nv : vertexTracesValues ) {
				persisted.addTraces(nv);
			}
			Iterable<VProperty> persistedOwnsValues = vertex.getOwns();
			List<VProperty> vertexOwnsValues = new ArrayList<>();
			for (VProperty nv : vertex.getOwns()) {
				vertexOwnsValues.add(nv);
			}
			for (VProperty ov : persistedOwnsValues) {
				if (!vertexOwnsValues.contains(ov)) {
					persisted.removeOwns(ov);
				}
			}
			for (VProperty  nv : vertexOwnsValues ) {
				persisted.addOwns(nv);
			}
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public VModelElement getModelElementById(Object id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        VModelElement vertex = null;
        vertex = manager.frame(manager.getVertex(id), VModelElement.class);            
        manager.shutdown();
        return vertex;
    }
    
    public List<VModelElement> getModelElementByIndex(java.lang.String element_id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        List<VModelElement> vertices = new ArrayList<>();
        for (Vertex v : manager.getVertices("ModelElement.element_id", element_id)) {
            vertices.add(manager.frame(v, VModelElement.class));
        }
        manager.shutdown();
        return vertices;
    }    
	public VProperty createProperty(
			java.lang.String name) {
	    OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    VProperty vertex = null;
	    try{
			vertex = manager.addVertex("class:Property", VProperty.class);
		    vertex.setName(name);
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
		return vertex;
	}
	
	public void deleteProperty(VProperty vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			manager.removeVertex(vertex.asVertex());
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public void updateProperty(VProperty vertex) {
		OrientGraph txGraph = factory.getTx();
	    FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
	    try{
			VProperty persisted =  manager.getVertex(vertex.asVertex().getId(), VProperty.class);		
		    persisted.setName(vertex.getName());
			persisted.setModelElement(vertex.getModelElement());
			Iterable<VTrace> persistedTracesValues = vertex.getTraces();
			List<VTrace> vertexTracesValues = new ArrayList<>();
			for (VTrace nv : vertex.getTraces()) {
				vertexTracesValues.add(nv);
			}
			for (VTrace ov : persistedTracesValues) {
				if (!vertexTracesValues.contains(ov)) {
					persisted.removeTraces(ov);
				}
			}
			for (VTrace  nv : vertexTracesValues ) {
				persisted.addTraces(nv);
			}
			manager.commit();
		} catch (Exception e) {
		    manager.rollback();
		}    
	    manager.shutdown();
	}
	
	public VProperty getPropertyById(Object id) {
        OrientGraph txGraph = factory.getTx();
        FramedTransactionalGraph<OrientGraph> manager = framedFactory.create(txGraph);
        VProperty vertex = null;
        vertex = manager.frame(manager.getVertex(id), VProperty.class);            
        manager.shutdown();
        return vertex;
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

