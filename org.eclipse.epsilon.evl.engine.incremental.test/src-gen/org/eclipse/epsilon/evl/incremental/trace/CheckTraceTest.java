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

import org.eclipse.epsilon.evl.incremental.trace.impl.CheckTrace;
// import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasCheck;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class CheckTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IInvariantTrace containerMock;
    
    private CheckTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        InvariantTraceHasCheck containerReference = new InvariantTraceHasCheck(containerMock);
        when(containerMock.check()).thenReturn(containerReference);
        // protected region CheckTraceInit_init on begin
        classUnderTest = new CheckTrace(containerMock);                    
        // protected region CheckTraceInit_init end
        

        assertThat(containerReference.get(), is(classUnderTest));
        
        // protected region CheckTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region CheckTraceAttributes end
    }
    
    @Test
    public void testResultFactory() throws Exception {
        InvariantTraceHasCheck containerReference = new InvariantTraceHasCheck(containerMock);
        when(containerMock.check()).thenReturn(containerReference);
        // protected region CheckTraceInit_ResultFactory on begin
        classUnderTest = new CheckTrace(containerMock);                    
        // protected region CheckTraceInit_ResultFactory end
        
        IExecutionContext contextMock = mock(IExecutionContext.class);
        List<Object> list = new ArrayList<>();
        ICheckResult child1 = classUnderTest.getOrCreateCheckResult(contextMock);
        classUnderTest.result().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        ICheckResult child2 = classUnderTest.getOrCreateCheckResult(contextMock);
        classUnderTest.result().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
    }
    
    // protected region CheckTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region CheckTraceOperations end
}

