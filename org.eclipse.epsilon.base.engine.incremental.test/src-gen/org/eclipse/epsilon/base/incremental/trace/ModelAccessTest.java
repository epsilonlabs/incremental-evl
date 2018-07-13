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

import org.eclipse.epsilon.base.incremental.trace.impl.ModelAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModelAccessTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModuleExecutionTrace containerMock;
    
    private ModelAccess classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModuleExecutionTraceHasModels containerReference = new ModuleExecutionTraceHasModels(containerMock);
        when(containerMock.models()).thenReturn(containerReference);
        IModelTrace _modelTrace = mock(IModelTrace.class);
                
        // protected region ModelAccessInit on begin
        // Default init parameters can be modified
        classUnderTest = new ModelAccess("modelName1", _modelTrace);                    
        // protected region ModelAccessInit end
        
    }
    
    
    @Test
    public void testModelTraceReference() throws Exception {
        IModelTrace _modelTrace = mock(IModelTrace.class);
                
        // protected region ModelAccessInit on begin
        // Default init parameters can be modified
        classUnderTest = new ModelAccess("modelName1", _modelTrace);                    
        // protected region ModelAccessInit end
        IModelTrace ref = mock(IModelTrace.class);
        
        boolean result = classUnderTest.modelTrace().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.modelTrace().get(), is(_modelTrace));
        result = classUnderTest.modelTrace().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.modelTrace().destroy(_modelTrace);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.modelTrace().get(), is(nullValue()));
        result = classUnderTest.modelTrace().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.modelTrace().get(), is(ref));
    
    }
    
    // protected region ModelAccessOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelAccessOperations end
}

