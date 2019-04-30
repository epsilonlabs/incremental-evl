 /*******************************************************************************
 * This file was automatically generated on: 2019-04-29.
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

import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.*;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class EvlModuleTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private EvlModuleTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        // protected region EvlModuleTraceInit_init on begin
        classUnderTest = new EvlModuleTrace("uri1");                    
        // protected region EvlModuleTraceInit_init end
        
    }
    
    @Test
    public void testModelsFactory() throws Exception {
        // protected region EvlModuleTraceInit_ModelsFactory on begin
        classUnderTest = new EvlModuleTrace("uri1");                    
        // protected region EvlModuleTraceInit_ModelsFactory end
        
        IModelTrace modelTraceMock = mock(IModelTrace.class);
        List<Object> list = new ArrayList<>();
        IModelAccess child1 = classUnderTest.getOrCreateModelAccess("modelName1", modelTraceMock);
        classUnderTest.models().get().forEachRemaining(list::add);
        
        assertThat(list, hasItem(child1));
        list.clear();
        IModelAccess child2 = classUnderTest.getOrCreateModelAccess("modelName2", modelTraceMock);
        classUnderTest.models().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IModelAccess child3 = classUnderTest.getOrCreateModelAccess("modelName1", modelTraceMock);
        classUnderTest.models().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
    }
    
    // protected region EvlModuleTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region EvlModuleTraceOperations end
}

