 /*******************************************************************************
 * This file was automatically generated on: 2019-06-04.
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

import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
// import org.eclipse.epsilon.evl.incremental.trace.impl.GuardedElementTraceHasGuard;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GuardTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IGuardedElementTrace containerMock;
    
    private GuardTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        GuardedElementTraceHasGuard containerReference = new GuardedElementTraceHasGuard(containerMock);
        when(containerMock.guard()).thenReturn(containerReference);
        // protected region GuardTraceInit_init on begin
        classUnderTest = new GuardTrace(containerMock);                    
        // protected region GuardTraceInit_init end
        

        assertThat(containerReference.get(), is(classUnderTest));
        
        // protected region GuardTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region GuardTraceAttributes end
    }
    
    @Test
    public void testResultFactory() throws Exception {
        GuardedElementTraceHasGuard containerReference = new GuardedElementTraceHasGuard(containerMock);
        when(containerMock.guard()).thenReturn(containerReference);
        // protected region GuardTraceInit_ResultFactory on begin
        classUnderTest = new GuardTrace(containerMock);                    
        // protected region GuardTraceInit_ResultFactory end
        
        IExecutionContext contextMock = mock(IExecutionContext.class);
        List<Object> list = new ArrayList<>();
        IGuardResult child1 = classUnderTest.getOrCreateGuardResult(contextMock);
        classUnderTest.result().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IGuardResult child2 = classUnderTest.getOrCreateGuardResult(contextMock);
        classUnderTest.result().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
    }
    
    // protected region GuardTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region GuardTraceOperations end
}

