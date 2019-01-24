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

import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesAccess;
// import org.eclipse.epsilon.evl.incremental.trace.impl.ExecutionContextHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class SatisfiesAccessTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IExecutionContext containerMock;
    
    private SatisfiesAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModelElementTrace _modelElement = mock(IModelElementTrace.class);
                
        // protected region SatisfiesAccessInit_init on begin
        classUnderTest = new SatisfiesAccess(_modelElement, containerMock);                    
        // protected region SatisfiesAccessInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region SatisfiesAccessAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region SatisfiesAccessAttributes end
    }
    
    
    @Test
    public void testModelElementReference() throws Exception {
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModelElementTrace _modelElement = mock(IModelElementTrace.class);
                
        // protected region SatisfiesAccessInit_ModelElementReference on begin
        classUnderTest = new SatisfiesAccess(_modelElement, containerMock);                    
        // protected region SatisfiesAccessInit_ModelElementReference end
        IModelElementTrace ref = mock(IModelElementTrace.class);
        
        boolean result = classUnderTest.modelElement().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.modelElement().get(), is(_modelElement));
        result = classUnderTest.modelElement().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.modelElement().destroy(_modelElement);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.modelElement().get(), is(nullValue()));
        result = classUnderTest.modelElement().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.modelElement().get(), is(ref));
    
    }
    
    
    @Test
    public void testSatisfiedInvariantsReference() throws Exception {
        ExecutionContextHasAccesses containerReference = new ExecutionContextHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModelElementTrace _modelElement = mock(IModelElementTrace.class);
                
        // protected region SatisfiesAccessInit_SatisfiedInvariantsReference on begin
        classUnderTest = new SatisfiesAccess(_modelElement, containerMock);                    
        // protected region SatisfiesAccessInit_SatisfiedInvariantsReference end
        // TODO Implement multivalue ref test
    
    }
    
    // protected region SatisfiesAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region SatisfiesAccessOperations end
}

