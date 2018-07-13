 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTraceHasLimits;
import org.eclipse.epsilon.evl.incremental.trace.IAccess;
import org.eclipse.epsilon.evl.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.impl.ExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTrace;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ContextTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModuleExecutionTrace containerMock;
    
    private ContextTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        // protected region ContextTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ContextTrace("kind1", 1, _executionContext, containerMock);                    
        // protected region ContextTraceInit end
        
        assertThat(containerReference.get(), contains(classUnderTest));
        
        // protected region ContextTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ContextTraceAttributes end
    }
    
    @Test
    public void testGuardFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        IExecutionContext _executionContext = mock(IExecutionContext.class);
                
        // protected region ContextTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ContextTrace("kind1", 1, _executionContext, containerMock);                    
        // protected region ContextTraceInit end
        
        IGuardTrace child1 = classUnderTest.createGuardTrace();
        assertThat(classUnderTest.guard().get(), hasItem(child1));
    @Test
    public void testExecutionContextFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        IExecutionContext _executionContext = mock(IExecutionContext.class);
                
        // protected region ContextTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ContextTrace("kind1", 1, _executionContext, containerMock);                    
        // protected region ContextTraceInit end
        
        IExecutionContext child1 = classUnderTest.createExecutionContext();
        assertThat(classUnderTest.executionContext().get(), hasItem(child1));
    @Test
    public void testConstraintsFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        IExecutionContext _executionContext = mock(IExecutionContext.class);
                
        // protected region ContextTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ContextTrace("kind1", 1, _executionContext, containerMock);                    
        // protected region ContextTraceInit end
        
        IInvariantTrace child1 = classUnderTest.createInvariantTrace("name1");
        assertThat(classUnderTest.constraints().get(), hasItem(child1));
        IInvariantTrace child2 = classUnderTest.createInvariantTrace("name2");
        assertThat(classUnderTest.constraints().get(), hasItem(child2));
        assertThat(classUnderTest.constraints().get(), hasSize(2));
        IInvariantTrace child3 = classUnderTest.createInvariantTrace("name1");
        assertThat(classUnderTest.constraints().get(), hasSize(2));
        
        assertThat(classUnderTest.constraints().get(), contains(child1, child2));
        assertThat(child3, is(child1));
	}
    
    @Test
    public void testAccessesReference() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        IExecutionContext _executionContext = mock(IExecutionContext.class);
                
        // protected region ContextTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ContextTrace("kind1", 1, _executionContext, containerMock);                    
        // protected region ContextTraceInit end
        // TODO Implement multivalue ref test
    
    }
    
    // protected region ContextTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ContextTraceOperations end
}

