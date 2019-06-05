 /*******************************************************************************
 * This file was automatically generated on: 2019-06-05.
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
// import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceHasElements;
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
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region ModelElementTraceInit_init on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _type, containerMock);                    
        // protected region ModelElementTraceInit_init end
        
        
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
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region ModelElementTraceInit_PropertiesFactory on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _type, containerMock);                    
        // protected region ModelElementTraceInit_PropertiesFactory end
        
        List<Object> list = new ArrayList<>();
        IPropertyTrace child1 = classUnderTest.getOrCreatePropertyTrace("name1");
        classUnderTest.properties().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IPropertyTrace child2 = classUnderTest.getOrCreatePropertyTrace("name2");
        classUnderTest.properties().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IPropertyTrace child3 = classUnderTest.getOrCreatePropertyTrace("name1");
        classUnderTest.properties().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
    }
    
    
    @Test
    public void testTypeReference() throws Exception {
        ModelTraceHasElements containerReference = new ModelTraceHasElements(containerMock);
        when(containerMock.elements()).thenReturn(containerReference);
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region ModelElementTraceInit_TypeReference on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _type, containerMock);                    
        // protected region ModelElementTraceInit_TypeReference end
        IModelTypeTrace ref_type = mock(IModelTypeTrace.class);
        
        boolean result = classUnderTest.type().create(ref_type);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.type().get(), is(ref_type));
        result = classUnderTest.type().destroy(ref_type);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.type().destroy(ref_type);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.type().get(), is(nullValue()));
        result = classUnderTest.type().create(ref_type);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.type().get(), is(ref_type));
    
    }
    
    
    @Test
    public void testKindReference() throws Exception {
        ModelTraceHasElements containerReference = new ModelTraceHasElements(containerMock);
        when(containerMock.elements()).thenReturn(containerReference);
        IModelTypeTrace _type = mock(IModelTypeTrace.class);
                
        // protected region ModelElementTraceInit_KindReference on begin
        classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _type, containerMock);                    
        // protected region ModelElementTraceInit_KindReference end
        // TODO Implement multivalue ref test
    
    }
    
    // protected region ModelElementTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelElementTraceOperations end
}

