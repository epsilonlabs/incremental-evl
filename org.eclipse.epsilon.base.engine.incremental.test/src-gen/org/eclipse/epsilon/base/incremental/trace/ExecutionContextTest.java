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

import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.ContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementVariable;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ExecutionContextTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IContextModuleElementTrace containerMock;
    
    private ExecutionContext classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ContextModuleElementTraceHasExecutionContext containerReference = new ContextModuleElementTraceHasExecutionContext(containerMock);
        when(containerMock.executionContext()).thenReturn(containerReference);
        // protected region ExecutionContextInit on begin
        // Default init parameters can be modified
        classUnderTest = new ExecutionContext(containerMock);                    
        // protected region ExecutionContextInit end
        
        assertThat(containerReference.get(), is(classUnderTest));
        
        // protected region ExecutionContextAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ExecutionContextAttributes end
    }
    
    @Test
    public void testContextVariablesFactory() throws Exception {
        ContextModuleElementTraceHasExecutionContext containerReference = new ContextModuleElementTraceHasExecutionContext(containerMock);
        when(containerMock.executionContext()).thenReturn(containerReference);
        // protected region ExecutionContextInit on begin
        // Default init parameters can be modified
        classUnderTest = new ExecutionContext(containerMock);                    
        // protected region ExecutionContextInit end
        
        IModelElementTrace valueMock = mock(IModelElementTrace.class);
        IModelElementVariable child1 = classUnderTest.createModelElementVariable("name1", valueMock);
        assertThat(classUnderTest.contextVariables().get(), hasItem(child1));
        IModelElementVariable child2 = classUnderTest.createModelElementVariable("name2", valueMock);
        assertThat(classUnderTest.contextVariables().get(), hasItem(child2));
        assertThat(classUnderTest.contextVariables().get(), hasSize(2));
        IModelElementVariable child3 = classUnderTest.createModelElementVariable("name1", valueMock);
        assertThat(classUnderTest.contextVariables().get(), hasSize(2));
        
        assertThat(classUnderTest.contextVariables().get(), containsInAnyOrder(child1, child2));
        assertThat(child3, is(child1));
	}
    // protected region ExecutionContextOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ExecutionContextOperations end
}

