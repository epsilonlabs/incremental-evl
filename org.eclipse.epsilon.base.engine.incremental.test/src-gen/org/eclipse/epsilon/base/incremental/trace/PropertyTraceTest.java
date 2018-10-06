 /*******************************************************************************
 * This file was automatically generated on: 2018-09-12.
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

import org.eclipse.epsilon.base.incremental.trace.impl.PropertyTrace;
// import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTraceHasProperties;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class PropertyTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModelElementTrace containerMock;
    
    private PropertyTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModelElementTraceHasProperties containerReference = new ModelElementTraceHasProperties(containerMock);
        when(containerMock.properties()).thenReturn(containerReference);
        // protected region PropertyTraceInit_init on begin
        classUnderTest = new PropertyTrace("name1", containerMock);                    
        // protected region PropertyTraceInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region PropertyTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region PropertyTraceAttributes end
    }
    
    // protected region PropertyTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region PropertyTraceOperations end
}

