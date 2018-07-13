 /*******************************************************************************
 * This file was automatically generated on: 2018-07-13.
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
import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.impl.AllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleElementTraceHasAccesses;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTrace;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AllInstancesAccessTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModuleExecutionTrace containerMock;
    
    private AllInstancesAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        ModuleElementTraceHasAccesses _moduleElementTrace = new ModuleElementTraceHasAccesses(_executionTrace );
        when(_executionTrace.accesses()).thenReturn(_moduleElementTrace);
        //when(_moduleElementTrace.get()).thenReturn(null);
                
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region AllInstancesAccessInit on begin
        classUnderTest = new AllInstancesAccess(false, _executionTrace, _type, containerMock);                    
        // protected region AllInstancesAccessInit end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region AllInstancesAccessAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region AllInstancesAccessAttributes end
    }
    
    
    @Test
    public void testExecutionTraceReference() throws Exception {
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        ModuleElementTraceHasAccesses _moduleElementTrace = new ModuleElementTraceHasAccesses(_executionTrace );
        when(_executionTrace.accesses()).thenReturn(_moduleElementTrace);
        //when(_moduleElementTrace.get()).thenReturn(null);
                
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region AllInstancesAccessInit on begin
        classUnderTest = new AllInstancesAccess(false, _executionTrace, _type, containerMock);                    
        // protected region AllInstancesAccessInit end
        IModuleElementTrace ref = mock(IModuleElementTrace.class);
        IModuleElementTraceHasAccesses _moduleElementTrace2 = mock(IModuleElementTraceHasAccesses.class);
        when(ref.accesses()).thenReturn(_moduleElementTrace2);
        
        boolean result = classUnderTest.executionTrace().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.executionTrace().get(), is(_executionTrace));
        result = classUnderTest.executionTrace().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.executionTrace().destroy(_executionTrace);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.executionTrace().get(), is(nullValue()));
        result = classUnderTest.executionTrace().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.executionTrace().get(), is(ref));
    
    }
    
    
    @Test
    public void testTypeReference() throws Exception {
        ModuleExecutionTraceHasAccesses containerReference = new ModuleExecutionTraceHasAccesses(containerMock);
        when(containerMock.accesses()).thenReturn(containerReference);
        IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        ModuleElementTraceHasAccesses _moduleElementTrace = new ModuleElementTraceHasAccesses(_executionTrace );
        when(_executionTrace.accesses()).thenReturn(_moduleElementTrace);
        //when(_moduleElementTrace.get()).thenReturn(null);
                
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region AllInstancesAccessInit on begin
        classUnderTest = new AllInstancesAccess(false, _executionTrace, _type, containerMock);                    
        // protected region AllInstancesAccessInit end
        IModelTypeTrace ref = mock(IModelTypeTrace.class);
        
        boolean result = classUnderTest.type().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.type().get(), is(_type));
        result = classUnderTest.type().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.type().destroy(_type);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.type().get(), is(nullValue()));
        result = classUnderTest.type().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.type().get(), is(ref));
    
    }
    
    // protected region AllInstancesAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region AllInstancesAccessOperations end
}

