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

import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
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

    private ModelTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        // protected region ModelTraceInit on begin
        classUnderTest = new ModelTrace("uri1");                    
        // protected region ModelTraceInit end
        
    }
    
    @Test
    public void testElementsFactory() throws Exception {
        // protected region ModelTraceInit on begin
        classUnderTest = new ModelTrace("uri1");                    
        // protected region ModelTraceInit end
        
        List<Object> list = new ArrayList<>();
        IModelElementTrace child1 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/1");
        classUnderTest.elements().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child1));
        list.clear();
        IModelElementTrace child2 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/2");
        classUnderTest.elements().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IModelElementTrace child3 = classUnderTest.createModelElementTrace("url://path/in/model/to/uri/1");
        classUnderTest.elements().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
	}
    @Test
    public void testTypesFactory() throws Exception {
        // protected region ModelTraceInit on begin
        classUnderTest = new ModelTrace("uri1");                    
        // protected region ModelTraceInit end
        
        List<Object> list = new ArrayList<>();
        IModelTypeTrace child1 = classUnderTest.createModelTypeTrace("name1");
        classUnderTest.types().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child1));
        list.clear();
        IModelTypeTrace child2 = classUnderTest.createModelTypeTrace("name2");
        classUnderTest.types().get().forEachRemaining(list::add);
        assertThat(list, hasItem(child2));
        assertThat(list, hasSize(2));
        list.clear();
        IModelTypeTrace child3 = classUnderTest.createModelTypeTrace("name1");
        classUnderTest.types().get().forEachRemaining(list::add);
        assertThat(list, hasSize(2));
        assertThat(list, contains(child1, child2));
        assertThat(child3, is(child1));
	}
    // protected region ModelTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region ModelTraceOperations end
}

