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
// import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasAccesses;
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
    private IModuleExecutionTrace containerMock;
    
    private ElementAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IExecutionContext _in = mock(IExecutionContext.class);
                
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_init on begin
        classUnderTest = new ElementAccess(_from, _in, _element, containerMock);                    
        // protected region ElementAccessInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ElementAccessAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ElementAccessAttributes end
    }
    
    
    @Test
    public void testFromReference() throws Exception {
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IExecutionContext _in = mock(IExecutionContext.class);
                
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_FromReference on begin
        classUnderTest = new ElementAccess(_from, _in, _element, containerMock);                 
        // protected region ElementAccessInit_FromReference end
        IModuleElementTrace ref_from = mock(IModuleElementTrace.class);
        
        boolean result = classUnderTest.from().create(ref_from);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.from().get(), is(ref_from));
        result = classUnderTest.from().destroy(ref_from);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.from().destroy(ref_from);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.from().get(), is(nullValue()));
        result = classUnderTest.from().create(ref_from);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.from().get(), is(ref_from));
    
    }
    
    
    @Test
    public void testInReference() throws Exception {
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IExecutionContext _in = mock(IExecutionContext.class);
                
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_InReference on begin
        classUnderTest = new ElementAccess(_from, _in, _element, containerMock);                    
        // protected region ElementAccessInit_InReference end
        IExecutionContext ref_in = mock(IExecutionContext.class);
        
        boolean result = classUnderTest.in().create(ref_in);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.in().get(), is(ref_in));
        result = classUnderTest.in().destroy(ref_in);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.in().destroy(ref_in);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.in().get(), is(nullValue()));
        result = classUnderTest.in().create(ref_in);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.in().get(), is(ref_in));
    
    }
    
    
    @Test
    public void testElementReference() throws Exception {
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _from = mock(IModuleElementTrace.class);
                
        IExecutionContext _in = mock(IExecutionContext.class);
                
        IModelElementTrace _element = mock(IModelElementTrace.class);
                
        // protected region ElementAccessInit_ElementReference on begin
        classUnderTest = new ElementAccess(_from, _in, _element, containerMock);                   
        // protected region ElementAccessInit_ElementReference end
        IModelElementTrace ref_element = mock(IModelElementTrace.class);
        
        boolean result = classUnderTest.element().create(ref_element);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.element().get(), is(ref_element));
        result = classUnderTest.element().destroy(ref_element);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.element().destroy(ref_element);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.element().get(), is(nullValue()));
        result = classUnderTest.element().create(ref_element);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.element().get(), is(ref_element));
    
    }
    
    // protected region ElementAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ElementAccessOperations end
}

