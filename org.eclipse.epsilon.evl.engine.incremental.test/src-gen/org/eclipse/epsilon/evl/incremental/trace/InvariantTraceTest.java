 /*******************************************************************************
 * This file was automatically generated on: 2018-06-14.
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

import org.eclipse.epsilon.evl.incremental.trace.impl.InvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.ContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.GuardTraceHasLimits;
import org.eclipse.epsilon.evl.incremental.trace.IAccess;
import org.eclipse.epsilon.evl.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.CheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.CheckTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.MessageTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.SatisfiesTraceHasInvariant;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class InvariantTraceTest {
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /** Mock the container. */
    @Mock
    private IContextTrace containerMock;
    
    private InvariantTrace classUnderTest;

    @Test
    public void testInit() throws Exception {
        
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        
        assertThat(containerReference.get(), contains(classUnderTest));
        
        // protected region InvariantTraceAttributes on begin
        // TODO Add test code for parameters (to hard to generate correct code for any/all types).                    
        // protected region InvariantTraceAttributes end
    }
    
    @Test
    public void testGuardFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        
        IGuardTrace child1 = classUnderTest.createGuardTrace();
        assertThat(classUnderTest.guard().get(), hasItem(child1));
    @Test
    public void testCheckFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        
        ICheckTrace child1 = classUnderTest.createCheckTrace();
        assertThat(classUnderTest.check().get(), hasItem(child1));
    @Test
    public void testMessageFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        
        IMessageTrace child1 = classUnderTest.createMessageTrace();
        assertThat(classUnderTest.message().get(), hasItem(child1));
    @Test
    public void testSatisfiesFactory() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        
        ISatisfiesTrace child1 = classUnderTest.createSatisfiesTrace();
        assertThat(classUnderTest.satisfies().get(), hasItem(child1));
    
    @Test
    public void testAccessesReference() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        // TODO Implement multivalue ref test
    
    }
    
    
    @Test
    public void testParentTraceReference() throws Exception {
        ContextTraceHasConstraints containerReference = new ContextTraceHasConstraints(containerMock);
        when(containerMock.constraints()).thenReturn(containerReference);
        // protected region InvariantTraceInit on begin
        // Default init parameters can be modified
        classUnderTest = new InvariantTrace("name1", containerMock);                    
        // protected region InvariantTraceInit end
        IContextModuleElementTrace ref = mock(IContextModuleElementTrace.class);
        
        boolean result = classUnderTest.parentTrace().create(ref);
        assertFalse("A new reference can not be created before destroy", result);
        assertThat(classUnderTest.parentTrace().get(), is(_parentTrace));
        result = classUnderTest.parentTrace().destroy(ref);
        assertFalse("Can't destroy unexisting reference", result);
        result = classUnderTest.parentTrace().destroy(_parentTrace);
        assertTrue("Exising references can be destroyed", result);
        assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
        result = classUnderTest.parentTrace().create(ref);
        assertTrue("New references can be craeted if was null", result);
        assertThat(classUnderTest.parentTrace().get(), is(ref));
    
    }
    
    // protected region InvariantTraceOperations on begin
    // TODO Add test code for additional operations                 
    // protected region InvariantTraceOperations end
}

