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
package org.eclipse.epsilon.base.incremental.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTrace;
// import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceHasTypes;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModelTypeTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModelTrace containerMock;
    
    private ModelTypeTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModelTraceHasTypes containerReference = new ModelTraceHasTypes(containerMock);
        when(containerMock.types()).thenReturn(containerReference);
        // protected region ModelTypeTraceInit_init on begin
        classUnderTest = new ModelTypeTrace("name1", containerMock);                    
        // protected region ModelTypeTraceInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ModelTypeTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ModelTypeTraceAttributes end
    }
    
    // protected region ModelTypeTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelTypeTraceOperations end
}

