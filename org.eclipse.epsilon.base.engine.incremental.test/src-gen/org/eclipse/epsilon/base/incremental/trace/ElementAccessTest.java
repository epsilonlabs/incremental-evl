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

import org.eclipse.epsilon.base.incremental.trace.impl.ElementAccess;
// import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ElementAccessTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IExecutionContext containerMock;
    
    private ElementAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_init on begin
        classUnderTest = new ElementAccess(_from, _element, containerMock);                    
        // protected region ElementAccessInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ElementAccessAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ElementAccessAttributes end
    }
    
    
    @Test
    public void testElementReference() throws Exception {
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_ElementReference on begin
        classUnderTest = new ElementAccess(_from, _element, containerMock);                    
        // protected region ElementAccessInit_ElementReference end
        IModelElementTrace ref = mock(IModelElementTrace.class);
        
        boolean result = classUnderTest.element().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.element().get(), is(_element));
        result = classUnderTest.element().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.element().destroy(_element);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.element().get(), is(nullValue()));
        result = classUnderTest.element().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.element().get(), is(ref));
    
    }
    
    // protected region ElementAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ElementAccessOperations end
}

