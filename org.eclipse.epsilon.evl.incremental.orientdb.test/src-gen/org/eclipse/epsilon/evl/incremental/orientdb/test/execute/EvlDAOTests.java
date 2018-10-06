 /*******************************************************************************
 * This file was automatically generated on: Thu Oct 19 08:58:19 BST 2017.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.test.execute;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.EvlOrientDbDAO;
import org.eclipse.epsilon.evl.incremental.orientdb.trace.impl.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
/**
 * Test the implementaion of the OrientDb DAO. 
 */
public class EvlDAOTests {

    private OrientGraphFactory factory;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    
    @Before
    /**
     * Create some data in the graph
     * - One vertex of each type so we can check its properties.
     * - One each of each type so we can check navigation
     */ 
    public void setupDb() {
        factory = new OrientGraphFactory("memory:evlTest");
        EvlOrientDbDAO.setupSchema(factory);
    }
    
    @After
    public void setupDB() {
        factory.drop();
        factory.close();
    }

    @Test
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
    public void testEvlConstraint() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        EvlConstraintOrientDbImpl v1 = dao.createEvlConstraint("uri.value1");
        EvlConstraintOrientDbImpl v2 = dao.createEvlConstraint("uri.value2");
        assertThat(dao.getEvlConstraintById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getEvlConstraintById(v2.getId()).getUri(), is("uri.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateEvlConstraint(v1);
        assertThat(dao.getEvlConstraintById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateEvlConstraint(v2);
        assertThat(dao.getEvlConstraintById(v2.getId()).getUri(), is("uri.value20"));
        
        // Find by index
        assertThat(dao.getEvlConstraintByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getEvlConstraintByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteEvlConstraint(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlConstraint found with id");
        dao.getEvlConstraintById(v1.getId());
        dao.deleteEvlConstraint(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlConstraint found with id");
        dao.getEvlConstraintById(v2.getId());
        
        // Create duplicate
        v1 = dao.createEvlConstraint( "uri.value1");
        v2 = dao.createEvlConstraint( "uri.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlConstraint found with index (uri): someindex");
        v1 = dao.getEvlConstraintByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlConstraint  found with id: someid");
        v1 = dao.getEvlConstraintById("someid");
    }  

    @Test
    public void testEvlContext() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        EvlContextOrientDbImpl v1 = dao.createEvlContext("uri.value1");
        EvlContextOrientDbImpl v2 = dao.createEvlContext("uri.value2");
        assertThat(dao.getEvlContextById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getEvlContextById(v2.getId()).getUri(), is("uri.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateEvlContext(v1);
        assertThat(dao.getEvlContextById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateEvlContext(v2);
        assertThat(dao.getEvlContextById(v2.getId()).getUri(), is("uri.value20"));
        
        // Find by index
        assertThat(dao.getEvlContextByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getEvlContextByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteEvlContext(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlContext found with id");
        dao.getEvlContextById(v1.getId());
        dao.deleteEvlContext(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlContext found with id");
        dao.getEvlContextById(v2.getId());
        
        // Create duplicate
        v1 = dao.createEvlContext( "uri.value1");
        v2 = dao.createEvlContext( "uri.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlContext found with index (uri): someindex");
        v1 = dao.getEvlContextByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No EvlContext  found with id: someid");
        v1 = dao.getEvlContextById("someid");
    }  

    @Test
    public void testGuard() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        GuardOrientDbImpl v1 = dao.createGuard("uri.value1", "result.value1");
        GuardOrientDbImpl v2 = dao.createGuard("uri.value2", "result.value2");
        assertThat(dao.getGuardById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getGuardById(v2.getId()).getUri(), is("uri.value2"));
        assertThat(dao.getGuardById(v1.getId()).getResult(), is("result.value1"));
        assertThat(dao.getGuardById(v2.getId()).getResult(), is("result.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateGuard(v1);
        assertThat(dao.getGuardById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateGuard(v2);
        assertThat(dao.getGuardById(v2.getId()).getUri(), is("uri.value20"));
        v1.setResult("result.value10");
        v1 = dao.updateGuard(v1);
        assertThat(dao.getGuardById(v1.getId()).getResult(), is("result.value10"));        
        v2.setResult("result.value20");
        v2 = dao.updateGuard(v2);
        assertThat(dao.getGuardById(v2.getId()).getResult(), is("result.value20"));
        
        // Find by index
        assertThat(dao.getGuardByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getGuardByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteGuard(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Guard found with id");
        dao.getGuardById(v1.getId());
        dao.deleteGuard(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Guard found with id");
        dao.getGuardById(v2.getId());
        
        // Create duplicate
        v1 = dao.createGuard( "uri.value1", "result.value1");
        v2 = dao.createGuard( "uri.value1", "result.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Guard found with index (uri): someindex");
        v1 = dao.getGuardByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Guard  found with id: someid");
        v1 = dao.getGuardById("someid");
    }  

    @Test
    public void testCheck() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        CheckOrientDbImpl v1 = dao.createCheck("uri.value1", "result.value1");
        CheckOrientDbImpl v2 = dao.createCheck("uri.value2", "result.value2");
        assertThat(dao.getCheckById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getCheckById(v2.getId()).getUri(), is("uri.value2"));
        assertThat(dao.getCheckById(v1.getId()).getResult(), is("result.value1"));
        assertThat(dao.getCheckById(v2.getId()).getResult(), is("result.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateCheck(v1);
        assertThat(dao.getCheckById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateCheck(v2);
        assertThat(dao.getCheckById(v2.getId()).getUri(), is("uri.value20"));
        v1.setResult("result.value10");
        v1 = dao.updateCheck(v1);
        assertThat(dao.getCheckById(v1.getId()).getResult(), is("result.value10"));        
        v2.setResult("result.value20");
        v2 = dao.updateCheck(v2);
        assertThat(dao.getCheckById(v2.getId()).getResult(), is("result.value20"));
        
        // Find by index
        assertThat(dao.getCheckByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getCheckByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteCheck(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Check found with id");
        dao.getCheckById(v1.getId());
        dao.deleteCheck(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Check found with id");
        dao.getCheckById(v2.getId());
        
        // Create duplicate
        v1 = dao.createCheck( "uri.value1", "result.value1");
        v2 = dao.createCheck( "uri.value1", "result.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Check found with index (uri): someindex");
        v1 = dao.getCheckByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Check  found with id: someid");
        v1 = dao.getCheckById("someid");
    }  

    @Test
    public void testMessage() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        MessageOrientDbImpl v1 = dao.createMessage("uri.value1", "result.value1");
        MessageOrientDbImpl v2 = dao.createMessage("uri.value2", "result.value2");
        assertThat(dao.getMessageById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getMessageById(v2.getId()).getUri(), is("uri.value2"));
        assertThat(dao.getMessageById(v1.getId()).getResult(), is("result.value1"));
        assertThat(dao.getMessageById(v2.getId()).getResult(), is("result.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateMessage(v1);
        assertThat(dao.getMessageById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateMessage(v2);
        assertThat(dao.getMessageById(v2.getId()).getUri(), is("uri.value20"));
        v1.setResult("result.value10");
        v1 = dao.updateMessage(v1);
        assertThat(dao.getMessageById(v1.getId()).getResult(), is("result.value10"));        
        v2.setResult("result.value20");
        v2 = dao.updateMessage(v2);
        assertThat(dao.getMessageById(v2.getId()).getResult(), is("result.value20"));
        
        // Find by index
        assertThat(dao.getMessageByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getMessageByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteMessage(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Message found with id");
        dao.getMessageById(v1.getId());
        dao.deleteMessage(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Message found with id");
        dao.getMessageById(v2.getId());
        
        // Create duplicate
        v1 = dao.createMessage( "uri.value1", "result.value1");
        v2 = dao.createMessage( "uri.value1", "result.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Message found with index (uri): someindex");
        v1 = dao.getMessageByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Message  found with id: someid");
        v1 = dao.getMessageById("someid");
    }  

    @Test
    public void testElementTrace() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        ElementTraceOrientDbImpl v1 = dao.createElementTrace();
        ElementTraceOrientDbImpl v2 = dao.createElementTrace();
        assertThat(dao.getElementTraceById(v1.getId()), is(notNullValue()));
        assertThat(dao.getElementTraceById(v2.getId()), is(notNullValue()));
        // Delete
        dao.deleteElementTrace(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ElementTrace found with id");
        dao.getElementTraceById(v1.getId());
        dao.deleteElementTrace(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ElementTrace found with id");
        dao.getElementTraceById(v2.getId());
        
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ElementTrace  found with id: someid");
        v1 = dao.getElementTraceById("someid");
    }  

    @Test
    public void testModelElement() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        ModelElementOrientDbImpl v1 = dao.createModelElement("uri.value1");
        ModelElementOrientDbImpl v2 = dao.createModelElement("uri.value2");
        assertThat(dao.getModelElementById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getModelElementById(v2.getId()).getUri(), is("uri.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateModelElement(v1);
        assertThat(dao.getModelElementById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateModelElement(v2);
        assertThat(dao.getModelElementById(v2.getId()).getUri(), is("uri.value20"));
        
        // Find by index
        assertThat(dao.getModelElementByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getModelElementByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteModelElement(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ModelElement found with id");
        dao.getModelElementById(v1.getId());
        dao.deleteModelElement(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ModelElement found with id");
        dao.getModelElementById(v2.getId());
        
        // Create duplicate
        v1 = dao.createModelElement( "uri.value1");
        v2 = dao.createModelElement( "uri.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ModelElement found with index (uri): someindex");
        v1 = dao.getModelElementByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No ModelElement  found with id: someid");
        v1 = dao.getModelElementById("someid");
    }  

    @Test
    public void testProperty() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        PropertyOrientDbImpl v1 = dao.createProperty("uri.value1", "value.value1");
        PropertyOrientDbImpl v2 = dao.createProperty("uri.value2", "value.value2");
        assertThat(dao.getPropertyById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getPropertyById(v2.getId()).getUri(), is("uri.value2"));
        assertThat(dao.getPropertyById(v1.getId()).getValue(), is("value.value1"));
        assertThat(dao.getPropertyById(v2.getId()).getValue(), is("value.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateProperty(v1);
        assertThat(dao.getPropertyById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateProperty(v2);
        assertThat(dao.getPropertyById(v2.getId()).getUri(), is("uri.value20"));
        v1.setValue("value.value10");
        v1 = dao.updateProperty(v1);
        assertThat(dao.getPropertyById(v1.getId()).getValue(), is("value.value10"));        
        v2.setValue("value.value20");
        v2 = dao.updateProperty(v2);
        assertThat(dao.getPropertyById(v2.getId()).getValue(), is("value.value20"));
        
        // Find by index
        assertThat(dao.getPropertyByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getPropertyByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteProperty(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Property found with id");
        dao.getPropertyById(v1.getId());
        dao.deleteProperty(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Property found with id");
        dao.getPropertyById(v2.getId());
        
        // Create duplicate
        v1 = dao.createProperty( "uri.value1", "value.value1");
        v2 = dao.createProperty( "uri.value1", "value.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Property found with index (uri): someindex");
        v1 = dao.getPropertyByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Property  found with id: someid");
        v1 = dao.getPropertyById("someid");
    }  

    @Test
    public void testTypeTrace() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        TypeTraceOrientDbImpl v1 = dao.createTypeTrace();
        TypeTraceOrientDbImpl v2 = dao.createTypeTrace();
        assertThat(dao.getTypeTraceById(v1.getId()), is(notNullValue()));
        assertThat(dao.getTypeTraceById(v2.getId()), is(notNullValue()));
        // Delete
        dao.deleteTypeTrace(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No TypeTrace found with id");
        dao.getTypeTraceById(v1.getId());
        dao.deleteTypeTrace(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No TypeTrace found with id");
        dao.getTypeTraceById(v2.getId());
        
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No TypeTrace  found with id: someid");
        v1 = dao.getTypeTraceById("someid");
    }  

    @Test
    public void testType() throws Exception {
        
        EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
        // Create
        TypeOrientDbImpl v1 = dao.createType("uri.value1");
        TypeOrientDbImpl v2 = dao.createType("uri.value2");
        assertThat(dao.getTypeById(v1.getId()).getUri(), is("uri.value1"));
        assertThat(dao.getTypeById(v2.getId()).getUri(), is("uri.value2"));
        // Update
        v1.setUri("uri.value10");
        v1 = dao.updateType(v1);
        assertThat(dao.getTypeById(v1.getId()).getUri(), is("uri.value10"));        
        v2.setUri("uri.value20");
        v2 = dao.updateType(v2);
        assertThat(dao.getTypeById(v2.getId()).getUri(), is("uri.value20"));
        
        // Find by index
        assertThat(dao.getTypeByIndex("uri.value10").getId(), is(v1.getId()));
        assertThat(dao.getTypeByIndex("uri.value20").getId(), is(v2.getId()));
        // Delete
        dao.deleteType(v1);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Type found with id");
        dao.getTypeById(v1.getId());
        dao.deleteType(v2);
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Type found with id");
        dao.getTypeById(v2.getId());
        
        // Create duplicate
        v1 = dao.createType( "uri.value1");
        v2 = dao.createType( "uri.value1");
        assertThat(v2.getId(), is(v1.getId()));
        
        //Unknown index
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Type found with index (uri): someindex");
        v1 = dao.getTypeByIndex("someindex");
        
        // Unknown id
        exception.expect(EolIncrementalExecutionException.class);
        exception.expectMessage("No Type  found with id: someid");
        v1 = dao.getTypeById("someid");
    }  
    
    @Test
    public void testTypeTraceTypes() throws Exception {
    	
    	EvlOrientDbDAO dao = new EvlOrientDbDAO(factory);
    	TypeTraceOrientDbImpl typeTrace = dao.createTypeTrace();
    	TypeOrientDbImpl type = dao.createType("uri.value1");
    	OrientEdge e = dao.addEdge(typeTrace, type);
    	assertThat(e.getLabel(), is("Types"));
    }
    


}