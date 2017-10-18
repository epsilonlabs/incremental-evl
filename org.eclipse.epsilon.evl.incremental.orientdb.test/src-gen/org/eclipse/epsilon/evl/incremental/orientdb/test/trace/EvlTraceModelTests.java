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
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.test.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Iterator;

import org.eclipse.epsilon.evl.incremental.orientdb.execute.EvlOrientDbDAO;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * The OrientDbDAO provides methods for CRUD operations on the graph db. 
 */
public class EvlTraceModelTests {

	private OrientGraphFactory factory;
    
    @Before
    /**
     * Create some data in the graph
     * - One vertex of each type so we can check its properties.
     * - One each of each type so we can check navigation
     */ 
    public void setupDb() {
        factory = new OrientGraphFactory("memory:evlTest");
        EvlOrientDbDAO.setupSchema(factory);
        OrientGraph graph = factory.getTx();
        OrientVertex evlconstraint = graph.addVertex("class:EvlConstraint");
        evlconstraint.setProperty("uri", "uri.evlconstraint");        
        OrientVertex evlcontext = graph.addVertex("class:EvlContext");
        evlcontext.setProperty("uri", "uri.evlcontext");        
        OrientVertex guard = graph.addVertex("class:Guard");
        guard.setProperty("uri", "uri.guard");        
        guard.setProperty("result", "result.guard");        
        OrientVertex check = graph.addVertex("class:Check");
        check.setProperty("uri", "uri.check");        
        check.setProperty("result", "result.check");        
        OrientVertex message = graph.addVertex("class:Message");
        message.setProperty("uri", "uri.message");        
        message.setProperty("result", "result.message");        
        OrientVertex elementtrace = graph.addVertex("class:ElementTrace");
        OrientVertex modelelement = graph.addVertex("class:ModelElement");
        modelelement.setProperty("uri", "uri.modelelement");        
        OrientVertex property = graph.addVertex("class:Property");
        property.setProperty("uri", "uri.property");        
        property.setProperty("value", "value.property");        
        OrientVertex typetrace = graph.addVertex("class:TypeTrace");
        OrientVertex type = graph.addVertex("class:Type");
        type.setProperty("uri", "uri.type");        
        graph.addEdge("class:Blocks", elementtrace, guard, null);
        graph.addEdge("class:Elements", elementtrace, modelelement, null);
        graph.addEdge("class:Accesses", elementtrace, property, null);
        graph.addEdge("class:Blocks", typetrace, check, null);
        graph.addEdge("class:Types", typetrace, type, null);
        graph.addEdge("class:Owner", guard, evlcontext, null);
        graph.addEdge("class:Owner", check, evlcontext, null);
        graph.addEdge("class:Owner", message, evlcontext, null);
        graph.commit();
        graph.shutdown();
    }
        
    @After
    public void setupDB() {
        factory.drop();
        factory.close();
    }
    
    @Test
    // FIXME This should be in the DAO tests
    public void testSchemaIsComplete() {
        OrientGraph graph = factory.getTx();
        OrientVertexType vt;
        vt = graph.getVertexType("EvlConstraint");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("EvlContext");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("Guard");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("Check");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("Message");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("ElementTrace");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("ModelElement");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("Property");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("TypeTrace");
        assertThat(vt, is(notNullValue()));
        vt = graph.getVertexType("Type");
        assertThat(vt, is(notNullValue()));

        OrientEdgeType et;
        et = graph.getEdgeType("Blocks");
        assertThat(et, is(notNullValue()));
        et = graph.getEdgeType("Owner");
        assertThat(et, is(notNullValue()));
        et = graph.getEdgeType("Elements");
        assertThat(et, is(notNullValue()));
        et = graph.getEdgeType("Accesses");
        assertThat(et, is(notNullValue()));
        et = graph.getEdgeType("Types");
        assertThat(et, is(notNullValue()));
        graph.shutdown();
    }
    
    @Test
    public void testEvlConstraint() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("EvlConstraint").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        EvlConstraintOrientDbImpl impl = new EvlConstraintOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.evlconstraint"));        
        graph.shutdown();
    }
    
    @Test
    public void testEvlContext() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("EvlContext").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        EvlContextOrientDbImpl impl = new EvlContextOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.evlcontext"));        
        graph.shutdown();
    }
    
    @Test
    public void testGuard() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Guard").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        GuardOrientDbImpl impl = new GuardOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.guard"));        
        assertThat(impl.getResult(), is("result.guard"));        
        graph.shutdown();
    }
    
    @Test
    public void testCheck() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Check").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        CheckOrientDbImpl impl = new CheckOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.check"));        
        assertThat(impl.getResult(), is("result.check"));        
        graph.shutdown();
    }
    
    @Test
    public void testMessage() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Message").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        MessageOrientDbImpl impl = new MessageOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.message"));        
        assertThat(impl.getResult(), is("result.message"));        
        graph.shutdown();
    }
    
    @Test
    public void testElementTrace() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("ElementTrace").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        ElementTraceOrientDbImpl impl = new ElementTraceOrientDbImpl(v);
        graph.shutdown();
    }
    
    @Test
    public void testModelElement() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("ModelElement").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        ModelElementOrientDbImpl impl = new ModelElementOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.modelelement"));        
        graph.shutdown();
    }
    
    @Test
    public void testProperty() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Property").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        PropertyOrientDbImpl impl = new PropertyOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.property"));        
        assertThat(impl.getValue(), is("value.property"));        
        graph.shutdown();
    }
    
    @Test
    public void testTypeTrace() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("TypeTrace").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        TypeTraceOrientDbImpl impl = new TypeTraceOrientDbImpl(v);
        graph.shutdown();
    }
    
    @Test
    public void testType() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Type").iterator();
        assertThat(it.hasNext(), is(true));
        OrientVertex v = (OrientVertex) it.next();
        TypeOrientDbImpl impl = new TypeOrientDbImpl(v);
        assertThat(impl.getUri(), is("uri.type"));        
        graph.shutdown();
    }
    
    @Test
    public void testElementTracePattern() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("ElementTrace").iterator();
        ElementTraceOrientDbImpl impl = new ElementTraceOrientDbImpl((OrientVertex) it.next());
        OrientVertex refV;
        Iterator<Vertex> refIt;
        refIt = graph.getVerticesOfClass("Guard").iterator();
        refV = (OrientVertex) refIt.next();
        GuardOrientDbImpl guard = (GuardOrientDbImpl) impl.getBlocks().get(0);
        assertThat(refV, is(guard.getDelegate()));
        refIt = graph.getVerticesOfClass("ModelElement").iterator();
        refV = (OrientVertex) refIt.next();
        ModelElementOrientDbImpl modelelement = (ModelElementOrientDbImpl) impl.getElements().get(0);
        assertThat(refV, is(modelelement.getDelegate()));
        refIt = graph.getVerticesOfClass("Property").iterator();
        refV = (OrientVertex) refIt.next();
        PropertyOrientDbImpl property = (PropertyOrientDbImpl) impl.getAccesses().get(0);
        assertThat(refV, is(property.getDelegate()));
        graph.shutdown();
    }
    
    @Test
    public void testTypeTracePattern() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("TypeTrace").iterator();
        TypeTraceOrientDbImpl impl = new TypeTraceOrientDbImpl((OrientVertex) it.next());
        OrientVertex refV;
        Iterator<Vertex> refIt;
        refIt = graph.getVerticesOfClass("Type").iterator();
        refV = (OrientVertex) refIt.next();
        TypeOrientDbImpl type = (TypeOrientDbImpl) impl.getTypes().get(0);
        assertThat(refV, is(type.getDelegate()));
        refIt = graph.getVerticesOfClass("Check").iterator();
        refV = (OrientVertex) refIt.next();
        CheckOrientDbImpl check = (CheckOrientDbImpl) impl.getBlocks().get(0);
        assertThat(refV, is(check.getDelegate()));
        graph.shutdown();
    }
    
    @Test
    public void testMessagePattern() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Message").iterator();
        MessageOrientDbImpl impl = new MessageOrientDbImpl((OrientVertex) it.next());
        OrientVertex refV;
        Iterator<Vertex> refIt;
        refIt = graph.getVerticesOfClass("EvlContext").iterator();
        refV = (OrientVertex) refIt.next();
        EvlContextOrientDbImpl evlcontext = (EvlContextOrientDbImpl) impl.getOwner();
        assertThat(refV, is(evlcontext.getDelegate()));
        graph.shutdown();
    }
    
    @Test
    public void testCheckPattern() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Check").iterator();
        CheckOrientDbImpl impl = new CheckOrientDbImpl((OrientVertex) it.next());
        OrientVertex refV;
        Iterator<Vertex> refIt;
        refIt = graph.getVerticesOfClass("EvlContext").iterator();
        refV = (OrientVertex) refIt.next();
        EvlContextOrientDbImpl evlcontext = (EvlContextOrientDbImpl) impl.getOwner();
        assertThat(refV, is(evlcontext.getDelegate()));
        graph.shutdown();
    }
    
    @Test
    public void testGuardPattern() {
        OrientGraph graph = factory.getTx();
        Iterator<Vertex> it = graph.getVerticesOfClass("Guard").iterator();
        GuardOrientDbImpl impl = new GuardOrientDbImpl((OrientVertex) it.next());
        OrientVertex refV;
        Iterator<Vertex> refIt;
        refIt = graph.getVerticesOfClass("EvlContext").iterator();
        refV = (OrientVertex) refIt.next();
        EvlContextOrientDbImpl evlcontext = (EvlContextOrientDbImpl) impl.getOwner();
        assertThat(refV, is(evlcontext.getDelegate()));
        graph.shutdown();
    }
    
        
}
