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

import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTrace;
// import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTraceHasMessage;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MessageTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IInvariantTrace containerMock;
    
    private MessageTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        InvariantTraceHasMessage containerReference = new InvariantTraceHasMessage(containerMock);
        when(containerMock.message()).thenReturn(containerReference);
        // protected region MessageTraceInit_init on begin
        classUnderTest = new MessageTrace(containerMock);                    
        // protected region MessageTraceInit_init end
        

        assertThat(containerReference.get(), is(classUnderTest));
        
        // protected region MessageTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region MessageTraceAttributes end
    }
    
    // protected region MessageTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region MessageTraceOperations end
}

