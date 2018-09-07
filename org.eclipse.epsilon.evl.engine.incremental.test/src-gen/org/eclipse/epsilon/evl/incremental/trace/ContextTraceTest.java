 /*******************************************************************************
 * This file was automatically generated on: 2018-09-05.
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
import java.util.HashSet;
import java.util.List;

import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTrace;
// import org.eclipse.epsilon.evl.incremental.trace.impl.ModuleExecutionTraceHasModuleElements;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

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
        // protected region ContextTraceInit_init on begin
		classUnderTest = new ContextTrace("kind1", 1, containerMock);
		// protected region ContextTraceInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ContextTraceAttributes on begin
		// TODO Add test code for parameters (to hard to generate correct code for
		// any/all types).
		// protected region ContextTraceAttributes end
    }
    
    @Test
    public void testGuardFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        // protected region ContextTraceInit_GuardFactory on begin
		classUnderTest = new ContextTrace("kind1", 1, containerMock);
		// protected region ContextTraceInit_GuardFactory end
        
        List<Object> list = new ArrayList<>();
        IGuardTrace child1 = classUnderTest.getOrCreateGuardTrace();
        list.add(classUnderTest.guard().get());
        
        assertThat(list, hasItem(child1));
        list.clear();
    }
    
    @Test
    public void testExecutionContextFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        // protected region ContextTraceInit_ExecutionContextFactory on begin
		classUnderTest = new ContextTrace("kind1", 1, containerMock);
		// protected region ContextTraceInit_ExecutionContextFactory end
        
        List<Object> list = new ArrayList<>();
        IExecutionContext child1 = classUnderTest.getOrCreateExecutionContext();
        classUnderTest.executionContext().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IExecutionContext child2 = classUnderTest.getOrCreateExecutionContext();
        classUnderTest.executionContext().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
    }
    
    @Test
    public void testConstraintsFactory() throws Exception {
        ModuleExecutionTraceHasModuleElements containerReference = new ModuleExecutionTraceHasModuleElements(containerMock);
        when(containerMock.moduleElements()).thenReturn(containerReference);
        // protected region ContextTraceInit_ConstraintsFactory on begin
		classUnderTest = new ContextTrace("kind1", 1, containerMock);
		// protected region ContextTraceInit_ConstraintsFactory end
        
        List<Object> list = new ArrayList<>();
        IInvariantTrace child1 = classUnderTest.getOrCreateInvariantTrace("name1");
        classUnderTest.constraints().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IInvariantTrace child2 = classUnderTest.getOrCreateInvariantTrace("name2");
        classUnderTest.constraints().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IInvariantTrace child3 = classUnderTest.getOrCreateInvariantTrace("name1");
        classUnderTest.constraints().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
    }
    
    // protected region ContextTraceOperations on begin
	// TODO Add test code for additional operations
	// protected region ContextTraceOperations end
}

