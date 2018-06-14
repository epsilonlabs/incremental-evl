 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IAccess;
import org.eclipse.epsilon.evl.incremental.trace.IModelTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ModelTrace;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class EvlModuleTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private EvlModuleTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        // protected region EvlModuleTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new EvlModuleTrace("source1");                    
        // protected region EvlModuleTraceInit end
        
    }
    
    @Test
    public void testModuleElementsFactory() throws Exception {
        // protected region EvlModuleTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new EvlModuleTrace("source1");                    
        // protected region EvlModuleTraceInit end
        
        IModuleElementTrace child1 = classUnderTest.createModuleElementTrace();
        assertThat(classUnderTest.moduleElements().get(), hasItem(child1));
        IModuleElementTrace child2 = classUnderTest.createModuleElementTrace();
        assertThat(classUnderTest.moduleElements().get(), hasItem(child2));
        assertThat(classUnderTest.moduleElements().get(), hasSize(2));
        IModuleElementTrace child3 = classUnderTest.createModuleElementTrace();
        assertThat(classUnderTest.moduleElements().get(), hasSize(2));
        
        assertThat(classUnderTest.moduleElements().get(), contains(child1, child2));
        assertThat(child3, is(child1));
	}
    @Test
    public void testAccessesFactory() throws Exception {
        // protected region EvlModuleTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new EvlModuleTrace("source1");                    
        // protected region EvlModuleTraceInit end
        
        IModuleElementTrace executionTraceMock = mock(IModuleElementTrace.class);
        IAccess child1 = classUnderTest.createAccess(executionTraceMock);
        assertThat(classUnderTest.accesses().get(), hasItem(child1));
        IAccess child2 = classUnderTest.createAccess(executionTraceMock);
        assertThat(classUnderTest.accesses().get(), hasItem(child2));
        assertThat(classUnderTest.accesses().get(), hasSize(2));
        IAccess child3 = classUnderTest.createAccess(executionTraceMock);
        assertThat(classUnderTest.accesses().get(), hasSize(2));
        
        assertThat(classUnderTest.accesses().get(), containsInAnyOrder(child1, child2));
        assertThat(child3, is(child1));
	}
    @Test
    public void testModelsFactory() throws Exception {
        // protected region EvlModuleTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new EvlModuleTrace("source1");                    
        // protected region EvlModuleTraceInit end
        
        IModelTrace child1 = classUnderTest.createModelTrace("name1", "uri1");
        assertThat(classUnderTest.models().get(), hasItem(child1));
        IModelTrace child2 = classUnderTest.createModelTrace("name2", "uri2");
        assertThat(classUnderTest.models().get(), hasItem(child2));
        assertThat(classUnderTest.models().get(), hasSize(2));
        IModelTrace child3 = classUnderTest.createModelTrace("name1", "uri1");
        assertThat(classUnderTest.models().get(), hasSize(2));
        
        assertThat(classUnderTest.models().get(), contains(child1, child2));
        assertThat(child3, is(child1));
	}
    // protected region EvlModuleTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region EvlModuleTraceOperations end
}

