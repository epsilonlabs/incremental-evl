 /*******************************************************************************
 * This file was automatically generated on: 2018-06-05.
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

import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModuleExecutionTraceHasModels;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTypeTrace;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModelTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModuleExecutionTrace containerMock;
    
    private ModelTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModuleExecutionTraceHasModels containerReference = new ModuleExecutionTraceHasModels(containerMock);
        when(containerMock.models()).thenReturn(containerReference);
        // protected region ModelTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ModelTrace("name1", containerMock);                    
        // protected region ModelTraceInit end
        
        assertThat(containerReference.get(), contains(classUnderTest));
        
        // protected region ModelTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ModelTraceAttributes end
    }
    
    @Test
    public void testElementsFactory() throws Exception {
        ModuleExecutionTraceHasModels containerReference = new ModuleExecutionTraceHasModels(containerMock);
        when(containerMock.models()).thenReturn(containerReference);
        // protected region ModelTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ModelTrace("name1", containerMock);                    
        // protected region ModelTraceInit end
        
        IModelElementTrace child1 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/1");
        assertThat(classUnderTest.elements().get(), hasItem(child1));
        IModelElementTrace child2 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/2");
        assertThat(classUnderTest.elements().get(), hasItem(child2));
        assertThat(classUnderTest.elements().get(), hasSize(2));
        IModelElementTrace child3 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/1");
        assertThat(classUnderTest.elements().get(), hasSize(2));
        
        assertThat(classUnderTest.elements().get(), contains(child1, child2));
        assertThat(child3, is(child1));
	}
    @Test
    public void testTypesFactory() throws Exception {
        ModuleExecutionTraceHasModels containerReference = new ModuleExecutionTraceHasModels(containerMock);
        when(containerMock.models()).thenReturn(containerReference);
        // protected region ModelTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new ModelTrace("name1", containerMock);                    
        // protected region ModelTraceInit end
        
        IModelTypeTrace child1 = classUnderTest.createModelTypeTrace("name1");
        assertThat(classUnderTest.types().get(), hasItem(child1));
        IModelTypeTrace child2 = classUnderTest.createModelTypeTrace("name2");
        assertThat(classUnderTest.types().get(), hasItem(child2));
        assertThat(classUnderTest.types().get(), hasSize(2));
        IModelTypeTrace child3 = classUnderTest.createModelTypeTrace("name1");
        assertThat(classUnderTest.types().get(), hasSize(2));
        
        assertThat(classUnderTest.types().get(), contains(child1, child2));
        assertThat(child3, is(child1));
	}
    // protected region ModelTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelTraceOperations end
}

