 /*******************************************************************************
 * This file was automatically generated on: 2019-06-06.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContext;
// import org.eclipse.epsilon.base.incremental.trace.impl.ContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ExecutionContextTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IContextModuleElementTrace containerMock;
    
    private ExecutionContext classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ContextModuleElementTraceHasExecutionContext containerReference = new ContextModuleElementTraceHasExecutionContext(containerMock);
        when(containerMock.executionContext()).thenReturn(containerReference);
        // protected region ExecutionContextInit_init on begin
        classUnderTest = new ExecutionContext(containerMock);                    
        // protected region ExecutionContextInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ExecutionContextAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ExecutionContextAttributes end
    }
    
    @Test
    public void testContextVariablesFactory() throws Exception {
        ContextModuleElementTraceHasExecutionContext containerReference = new ContextModuleElementTraceHasExecutionContext(containerMock);
        when(containerMock.executionContext()).thenReturn(containerReference);
        // protected region ExecutionContextInit_ContextVariablesFactory on begin
        classUnderTest = new ExecutionContext(containerMock);                    
        // protected region ExecutionContextInit_ContextVariablesFactory end
        
        IModelElementTrace valueMock = mock(IModelElementTrace.class);
        List<Object> list = new ArrayList<>();
        IModelElementVariable child1 = classUnderTest.getOrCreateModelElementVariable("name1", valueMock);
        classUnderTest.contextVariables().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IModelElementVariable child2 = classUnderTest.getOrCreateModelElementVariable("name2", valueMock);
        classUnderTest.contextVariables().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IModelElementVariable child3 = classUnderTest.getOrCreateModelElementVariable("name1", valueMock);
        classUnderTest.contextVariables().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, containsInAnyOrder(child1, child2));
        assertThat(child3, is(child1));
    }
    
    // protected region ExecutionContextOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ExecutionContextOperations end
}

