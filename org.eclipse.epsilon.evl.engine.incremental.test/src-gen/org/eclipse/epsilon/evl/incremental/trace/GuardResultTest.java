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
package org.eclipse.epsilon.evl.incremental.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.epsilon.evl.incremental.trace.impl.GuardResult;
// import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTraceHasResult;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GuardResultTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IGuardTrace containerMock;
    
    private GuardResult classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        GuardTraceHasResult containerReference = new GuardTraceHasResult(containerMock);
        when(containerMock.result()).thenReturn(containerReference);
        IExecutionContext _context = mock(IExecutionContext.class);
                
        // protected region GuardResultInit_init on begin
        classUnderTest = new GuardResult(_context, containerMock);                    
        // protected region GuardResultInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region GuardResultAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region GuardResultAttributes end
    }
    
    
    @Test
    public void testContextReference() throws Exception {
        GuardTraceHasResult containerReference = new GuardTraceHasResult(containerMock);
        when(containerMock.result()).thenReturn(containerReference);
        IExecutionContext _context = mock(IExecutionContext.class);
                
        // protected region GuardResultInit_ContextReference on begin
        classUnderTest = new GuardResult(_context, containerMock);                    
        // protected region GuardResultInit_ContextReference end
        IExecutionContext ref_context = mock(IExecutionContext.class);
        
        boolean result = classUnderTest.context().create(ref_context);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.context().get(), is(ref_context));
        result = classUnderTest.context().destroy(ref_context);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.context().destroy(ref_context);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.context().get(), is(nullValue()));
        result = classUnderTest.context().create(ref_context);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.context().get(), is(ref_context));
    
    }
    
    // protected region GuardResultOperations on begin
    // TODO Add test code for additional operations                 
    // protected region GuardResultOperations end
}

