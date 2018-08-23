 /*******************************************************************************
 * This file was automatically generated on: 2018-08-23.
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

import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementVariable;
// import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextHasContextVariables;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModelElementVariableTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IExecutionContext containerMock;
    
    private ModelElementVariable classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ExecutionContextHasContextVariables containerReference = new ExecutionContextHasContextVariables(containerMock);
        when(containerMock.contextVariables()).thenReturn(containerReference);
        IModelElementTrace _value = mock(IModelElementTrace.class);
                
        // protected region ModelElementVariableInit_init on begin
        classUnderTest = new ModelElementVariable("name1", _value, containerMock);                    
        // protected region ModelElementVariableInit_init end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ModelElementVariableAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ModelElementVariableAttributes end
    }
    
    
    @Test
    public void testValueReference() throws Exception {
        ExecutionContextHasContextVariables containerReference = new ExecutionContextHasContextVariables(containerMock);
        when(containerMock.contextVariables()).thenReturn(containerReference);
        IModelElementTrace _value = mock(IModelElementTrace.class);
                
        // protected region ModelElementVariableInit_ValueReference on begin
        classUnderTest = new ModelElementVariable("name1", _value, containerMock);                    
        // protected region ModelElementVariableInit_ValueReference end
        IModelElementTrace ref = mock(IModelElementTrace.class);
        
        boolean result = classUnderTest.value().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.value().get(), is(_value));
        result = classUnderTest.value().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.value().destroy(_value);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.value().get(), is(nullValue()));
        result = classUnderTest.value().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.value().get(), is(ref));
    
    }
    
    // protected region ModelElementVariableOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelElementVariableOperations end
}

