 /*******************************************************************************
 * This file was automatically generated on: 2018-08-16.
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

import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceHasElements;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModelElementTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IModelTrace containerMock;
    
    private ModelElementTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ModelTraceHasElements containerReference = new ModelTraceHasElements(containerMock);
        when(containerMock.elements()).thenReturn(containerReference);
        // protected region ModelElementTraceInit on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", containerMock);                    
        // protected region ModelElementTraceInit end
        
        
        List<Object> list = new ArrayList<>();
        containerReference.get().forEachRemaining(list::add);
        assertThat(list, contains(classUnderTest));
        
        // protected region ModelElementTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region ModelElementTraceAttributes end
    }
    
    @Test
    public void testPropertiesFactory() throws Exception {
        ModelTraceHasElements containerReference = new ModelTraceHasElements(containerMock);
        when(containerMock.elements()).thenReturn(containerReference);
        // protected region ModelElementTraceInit on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", containerMock);                    
        // protected region ModelElementTraceInit end
        
        List<Object> list = new ArrayList<>();
        IPropertyTrace child1 = classUnderTest.createPropertyTrace("name1");
        classUnderTest.properties().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child1));
        list.clear();
        IPropertyTrace child2 = classUnderTest.createPropertyTrace("name2");
        classUnderTest.properties().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IPropertyTrace child3 = classUnderTest.createPropertyTrace("name1");
        classUnderTest.properties().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
	}
    // protected region ModelElementTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelElementTraceOperations end
}

