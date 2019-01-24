 /*******************************************************************************
 * This file was automatically generated on: 2019-01-23.
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

import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTrace;
// import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTraceHasConstraints;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class InvariantTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IContextTrace containerMock;
    
    private InvariantTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit_init on begin
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region InvariantTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region InvariantTraceAttributes end
    }
    
    @Test
    public void testGuardFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit_GuardFactory on begin
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit_GuardFactory end
        
        List<Object> list = new ArrayList<>();
        IGuardTrace child1 = classUnderTest.getOrCreateGuardTrace();
        list.add(classUnderTest.guard().get());
        
        assertThat(list, hasItem(child1));
        list.clear();
    }
    
    @Test
    public void testCheckFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit_CheckFactory on begin
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit_CheckFactory end
        
        List<Object> list = new ArrayList<>();
        ICheckTrace child1 = classUnderTest.getOrCreateCheckTrace();
        list.add(classUnderTest.check().get());
        
        assertThat(list, hasItem(child1));
        list.clear();
    }
    
    @Test
    public void testMessageFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit_MessageFactory on begin
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit_MessageFactory end
        
        List<Object> list = new ArrayList<>();
        IMessageTrace child1 = classUnderTest.getOrCreateMessageTrace();
        list.add(classUnderTest.message().get());
        
        assertThat(list, hasItem(child1));
        list.clear();
    }
    
    // protected region InvariantTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region InvariantTraceOperations end
}

