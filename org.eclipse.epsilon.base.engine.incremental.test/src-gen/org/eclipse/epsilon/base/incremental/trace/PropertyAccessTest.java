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
package org.eclipse.epsilon.base.incremental.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.impl.PropertyAccess;
// import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class PropertyAccessTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IExecutionContext containerMock;
    
    private PropertyAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IPropertyTrace _property = mock(IPropertyTrace.class);
                
        // protected region PropertyAccessInit_init on begin
        classUnderTest = new PropertyAccess(_executionTrace, _property, containerMock);                    
        // protected region PropertyAccessInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region PropertyAccessAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region PropertyAccessAttributes end
    }
    
    
    @Test
    public void testFromReference() throws Exception {
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IPropertyTrace _property = mock(IPropertyTrace.class);
                
        // protected region PropertyAccessInit_FromReference on begin
        classUnderTest = new PropertyAccess(_from, _property, containerMock);                    
        // protected region PropertyAccessInit_FromReference end
        IModuleElementTrace ref = mock(IModuleElementTrace.class);
        
        boolean result = classUnderTest.from().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.from().get(), is(_from));
        result = classUnderTest.from().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.from().destroy(_from);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.from().get(), is(nullValue()));
        result = classUnderTest.from().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.from().get(), is(ref));
    
    }
    
    
    @Test
    public void testPropertyReference() throws Exception {
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IPropertyTrace _property = mock(IPropertyTrace.class);
                
        // protected region PropertyAccessInit_PropertyReference on begin
        classUnderTest = new PropertyAccess(_executionTrace, _property, containerMock);                    
        // protected region PropertyAccessInit_PropertyReference end
        IPropertyTrace ref = mock(IPropertyTrace.class);
        
        boolean result = classUnderTest.property().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.property().get(), is(_property));
        result = classUnderTest.property().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.property().destroy(_property);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.property().get(), is(nullValue()));
        result = classUnderTest.property().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.property().get(), is(ref));
    
    }
    
    // protected region PropertyAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region PropertyAccessOperations end
}

