package org.eclipse.epsilon.evl.incremental.trace;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.eol.incremental.trace.*;
import org.eclipse.epsilon.eol.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EvlTraceModelTests.EvlModuleExecutionTests.class,
                     EvlTraceModelTests.EvlModuleTraceTests.class,
                     EvlTraceModelTests.ContextTraceTests.class,
                     EvlTraceModelTests.InvariantTraceTests.class,
                     EvlTraceModelTests.GuardTraceTests.class,
                     EvlTraceModelTests.CheckTraceTests.class,
                     EvlTraceModelTests.MessageTraceTests.class,
                     EvlTraceModelTests.SatisfiesTraceTests.class})
public class EvlTraceModelTests {

    
    public static class EvlModuleExecutionTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleMock2;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock2;
        
        /** Mock the target of the executions reference. */
        @Mock
        private IExecutionTrace executionsMock1;
        
        /** Mock the target of the executions reference. */
        @Mock
        private IExecutionTrace executionsMock2;
        
        private EvlModuleExecution classUnderTest;
        
        @Test
        public void testEvlModuleExecutionInstantiation() throws Exception {
            classUnderTest = new EvlModuleExecution();
	    }
	    
// protected region IgnoreEvlModuleExecutionAttributes on begin
	    @Ignore
// protected region IgnoreEvlModuleExecutionAttributes end	    
	    @Test
        public void testEvlModuleExecutionAttributes() throws Exception {
            classUnderTest = new EvlModuleExecution();
// protected region EvlModuleExecutionAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region EvlModuleExecutionAttributes end
        }

        @Test
        public void testEvlModuleExecutionCreateModuleReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.module().create(moduleMock1);
            assertTrue(result);
            result = classUnderTest.module().create(moduleMock2);
            assertFalse(result);
            result = classUnderTest.module().create(moduleMock1);
            assertFalse(result);
            // Create a second one
            IEvlModuleExecution classUnderTest2 = new EvlModuleExecution();
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testEvlModuleExecutionDestroyModuleReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.module().create(moduleMock1);
            boolean result = classUnderTest.module().destroy(moduleMock1);
            assertTrue(result);
            assertThat(classUnderTest.module().get(), is(nullValue()));
            result = classUnderTest.module().destroy(moduleMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleExecutionCreateModelReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.model().create(modelMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock1);
            assertFalse(result);
            // Create a second one
            IEvlModuleExecution classUnderTest2 = new EvlModuleExecution();
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testEvlModuleExecutionDestroyModelReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.model().create(modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
            assertThat(classUnderTest.model().get(), not(hasItem(modelMock1)));
            result = classUnderTest.model().destroy(modelMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleExecutionCreateExecutionsReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.executions().create(executionsMock1);
            assertTrue(result);
            result = classUnderTest.executions().create(executionsMock2);
            assertTrue(result);
            result = classUnderTest.executions().create(executionsMock1);
            assertFalse(result);
            // Create a second one
            IEvlModuleExecution classUnderTest2 = new EvlModuleExecution();
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testEvlModuleExecutionDestroyExecutionsReference() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.executions().create(executionsMock1);
            boolean result = classUnderTest.executions().destroy(executionsMock1);
            assertTrue(result);
            assertThat(classUnderTest.executions().get(), not(hasItem(executionsMock1)));
            result = classUnderTest.executions().destroy(executionsMock2);
            assertFalse(result);
        }
    }
    
    public static class EvlModuleTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the container. */
        @Mock
        private IModuleExecution containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionHasModule moduleExecution1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionHasModule moduleExecution2;

        private EvlModuleTrace classUnderTest;
        
        @Test
        public void testEvlModuleTraceInstantiation() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModule(containerMock);
            expect(containerMock.module()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleTrace("source", containerMock);
            assertThat(containerMock.module().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreEvlModuleTraceAttributes on begin
	    @Ignore
// protected region IgnoreEvlModuleTraceAttributes end	    
	    @Test
        public void testEvlModuleTraceAttributes() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModule(containerMock);
            expect(containerMock.module()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleTrace("source", containerMock);
// protected region EvlModuleTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region EvlModuleTraceAttributes end
        }

    }
    
    public static class ContextTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace2;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private IInvariantTrace constraintsMock1;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private IInvariantTrace constraintsMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasInvariantContext invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasInvariantContext invariantTrace2;
        
        /** Mock the target of the context reference. */
        @Mock
        private IModelElementTrace contextMock1;
        
        /** Mock the target of the context reference. */
        @Mock
        private IModelElementTrace contextMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecution containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionHasExecutions moduleExecution1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionHasExecutions moduleExecution2;

        private ContextTrace classUnderTest;
        
        @Test
        public void testContextTraceInstantiation() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            Queue<IExecutionTrace> values = containerMock.executions().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreContextTraceAttributes on begin
	    //@Ignore
// protected region IgnoreContextTraceAttributes end	    
	    @Test
        public void testContextTraceAttributes() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
// protected region ContextTraceAttributes on begin
            assertThat(classUnderTest.getKind(), is("kind"));
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
// protected region ContextTraceAttributes end
        }

        @Test
        public void testContextTraceCreateAccessesReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelElementTrace _context2 = mock(IModelElementTrace.class);
            IContextTrace classUnderTest2 = new ContextTrace("kind2", _context2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyAccessesReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateGuardReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            guardTrace1 = new GuardTraceHasLimits(guardMock1);
            expect(guardMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardMock1);
            guardTrace2 = new GuardTraceHasLimits(guardMock2);
            expect(guardMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardMock2);
            boolean result;
            result = classUnderTest.guard().create(guardMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelElementTrace _context2 = mock(IModelElementTrace.class);
            IContextTrace classUnderTest2 = new ContextTrace("kind2", _context2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyGuardReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            guardTrace1 = new GuardTraceHasLimits(guardMock1);
            expect(guardMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardMock1);
            classUnderTest.guard().create(guardMock1);
            boolean result = classUnderTest.guard().destroy(guardMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardTrace2 = new GuardTraceHasLimits(guardMock2);
            expect(guardMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardMock2);
            result = classUnderTest.guard().destroy(guardMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateConstraintsReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            invariantTrace1 = new InvariantTraceHasInvariantContext(constraintsMock1);
            expect(constraintsMock1.invariantContext()).andReturn(invariantTrace1).anyTimes();
            replay(constraintsMock1);
            invariantTrace2 = new InvariantTraceHasInvariantContext(constraintsMock2);
            expect(constraintsMock2.invariantContext()).andReturn(invariantTrace2).anyTimes();
            replay(constraintsMock2);
            boolean result;
            result = classUnderTest.constraints().create(constraintsMock1);
            assertTrue(result);
            result = classUnderTest.constraints().create(constraintsMock2);
            assertTrue(result);
            result = classUnderTest.constraints().create(constraintsMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelElementTrace _context2 = mock(IModelElementTrace.class);
            IContextTrace classUnderTest2 = new ContextTrace("kind2", _context2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyConstraintsReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            invariantTrace1 = new InvariantTraceHasInvariantContext(constraintsMock1);
            expect(constraintsMock1.invariantContext()).andReturn(invariantTrace1).anyTimes();
            replay(constraintsMock1);
            classUnderTest.constraints().create(constraintsMock1);
            boolean result = classUnderTest.constraints().destroy(constraintsMock1);
            assertTrue(result);
            assertThat(classUnderTest.constraints().get(), not(hasItem(constraintsMock1)));
            invariantTrace2 = new InvariantTraceHasInvariantContext(constraintsMock2);
            expect(constraintsMock2.invariantContext()).andReturn(invariantTrace2).anyTimes();
            replay(constraintsMock2);
            result = classUnderTest.constraints().destroy(constraintsMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateContextReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            boolean result;
            result = classUnderTest.context().create(contextMock2);
            assertFalse(result);
            result = classUnderTest.context().create(_context);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelElementTrace _context2 = mock(IModelElementTrace.class);
            IContextTrace classUnderTest2 = new ContextTrace("kind2", _context2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyContextReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasExecutions(containerMock);
            expect(containerMock.executions()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            IModelElementTrace _context = mock(IModelElementTrace.class);
            classUnderTest = new ContextTrace("kind", _context, containerMock);
            boolean result = classUnderTest.context().destroy(_context);
            assertTrue(result);
            assertThat(classUnderTest.context().get(), is(nullValue()));
            result = classUnderTest.context().destroy(contextMock2);
            assertFalse(result);
        }
    }
    
    public static class InvariantTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace2;
        
        /** Mock the target of the check reference. */
        @Mock
        private ICheckTrace checkMock1;
        
        /** Mock the target of the check reference. */
        @Mock
        private ICheckTrace checkMock2;
        
        /** Allow the target mock to populate the reference */
        private ICheckTraceHasInvariant checkTrace1;
        
        /** Allow the target mock to populate the reference */
        private ICheckTraceHasInvariant checkTrace2;
        
        /** Mock the target of the message reference. */
        @Mock
        private IMessageTrace messageMock1;
        
        /** Mock the target of the message reference. */
        @Mock
        private IMessageTrace messageMock2;
        
        /** Allow the target mock to populate the reference */
        private IMessageTraceHasInvariant messageTrace1;
        
        /** Allow the target mock to populate the reference */
        private IMessageTraceHasInvariant messageTrace2;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private ISatisfiesTrace satisfiesMock1;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private ISatisfiesTrace satisfiesMock2;
        
        /** Allow the target mock to populate the reference */
        private ISatisfiesTraceHasInvariant satisfiesTrace1;
        
        /** Allow the target mock to populate the reference */
        private ISatisfiesTraceHasInvariant satisfiesTrace2;
        
        /** Mock the target of the invariantContext reference. */
        @Mock
        private IContextTrace invariantContextMock1;
        
        /** Mock the target of the invariantContext reference. */
        @Mock
        private IContextTrace invariantContextMock2;
        
        /** Allow the target mock to populate the reference */
        private IContextTraceHasConstraints contextTrace1;
        
        /** Allow the target mock to populate the reference */
        private IContextTraceHasConstraints contextTrace2;
        
        private InvariantTrace classUnderTest;
        
        @Test
        public void testInvariantTraceInstantiation() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            assertThat(classUnderTest.invariantContext().get(), is(invariantContextMock1));
            Queue<IInvariantTrace> values = invariantContextMock1.constraints().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreInvariantTraceAttributes on begin
	    //@Ignore
// protected region IgnoreInvariantTraceAttributes end	    
	    @Test
        public void testInvariantTraceAttributes() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
// protected region InvariantTraceAttributes on begin
            assertThat(classUnderTest.getName(), is("name"));
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            boolean result = false;
            classUnderTest.setResult(result);
            assertThat(classUnderTest.getResult(), is(result));
            result = true;
            classUnderTest.setResult(result);
            assertThat(classUnderTest.getResult(), is(result));
// protected region InvariantTraceAttributes end
        }

        
        @Test
        public void testInvariantTraceCreateInvariantContextContainerReferenceConflict() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
        
            boolean result = classUnderTest.invariantContext().create(invariantContextMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testInvariantTraceDestroyInvariantContextContainerReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            boolean result = classUnderTest.invariantContext().destroy(invariantContextMock1);
            assertTrue(result);
        }
        
        @Test
        public void testInvariantTraceDestroyAndCreateInvariantContextContainerReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
  
            boolean result = classUnderTest.invariantContext().destroy(invariantContextMock1);
            assertTrue(result);
            result = classUnderTest.invariantContext().create(invariantContextMock2);
            assertTrue(result);
            result = classUnderTest.invariantContext().create(invariantContextMock2);
            assertFalse(result);
            result = classUnderTest.invariantContext().create(invariantContextMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceCreateAccessesReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroyAccessesReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateGuardReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            guardTrace1 = new GuardTraceHasLimits(guardMock1);
            expect(guardMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardMock1);
            guardTrace2 = new GuardTraceHasLimits(guardMock2);
            expect(guardMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardMock2);
            boolean result;
            result = classUnderTest.guard().create(guardMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardMock1);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroyGuardReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            guardTrace1 = new GuardTraceHasLimits(guardMock1);
            expect(guardMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardMock1);
            classUnderTest.guard().create(guardMock1);
            boolean result = classUnderTest.guard().destroy(guardMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardTrace2 = new GuardTraceHasLimits(guardMock2);
            expect(guardMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardMock2);
            result = classUnderTest.guard().destroy(guardMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateCheckReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            checkTrace1 = new CheckTraceHasInvariant(checkMock1);
            expect(checkMock1.invariant()).andReturn(checkTrace1).anyTimes();
            replay(checkMock1);
            checkTrace2 = new CheckTraceHasInvariant(checkMock2);
            expect(checkMock2.invariant()).andReturn(checkTrace2).anyTimes();
            replay(checkMock2);
            boolean result;
            result = classUnderTest.check().create(checkMock1);
            assertTrue(result);
            result = classUnderTest.check().create(checkMock2);
            assertFalse(result);
            result = classUnderTest.check().create(checkMock1);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroyCheckReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            checkTrace1 = new CheckTraceHasInvariant(checkMock1);
            expect(checkMock1.invariant()).andReturn(checkTrace1).anyTimes();
            replay(checkMock1);
            classUnderTest.check().create(checkMock1);
            boolean result = classUnderTest.check().destroy(checkMock1);
            assertTrue(result);
            assertThat(classUnderTest.check().get(), is(nullValue()));
            checkTrace2 = new CheckTraceHasInvariant(checkMock2);
            expect(checkMock2.invariant()).andReturn(checkTrace2).anyTimes();
            replay(checkMock2);
            result = classUnderTest.check().destroy(checkMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateMessageReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            messageTrace1 = new MessageTraceHasInvariant(messageMock1);
            expect(messageMock1.invariant()).andReturn(messageTrace1).anyTimes();
            replay(messageMock1);
            messageTrace2 = new MessageTraceHasInvariant(messageMock2);
            expect(messageMock2.invariant()).andReturn(messageTrace2).anyTimes();
            replay(messageMock2);
            boolean result;
            result = classUnderTest.message().create(messageMock1);
            assertTrue(result);
            result = classUnderTest.message().create(messageMock2);
            assertFalse(result);
            result = classUnderTest.message().create(messageMock1);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroyMessageReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            messageTrace1 = new MessageTraceHasInvariant(messageMock1);
            expect(messageMock1.invariant()).andReturn(messageTrace1).anyTimes();
            replay(messageMock1);
            classUnderTest.message().create(messageMock1);
            boolean result = classUnderTest.message().destroy(messageMock1);
            assertTrue(result);
            assertThat(classUnderTest.message().get(), is(nullValue()));
            messageTrace2 = new MessageTraceHasInvariant(messageMock2);
            expect(messageMock2.invariant()).andReturn(messageTrace2).anyTimes();
            replay(messageMock2);
            result = classUnderTest.message().destroy(messageMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateSatisfiesReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            satisfiesTrace1 = new SatisfiesTraceHasInvariant(satisfiesMock1);
            expect(satisfiesMock1.invariant()).andReturn(satisfiesTrace1).anyTimes();
            replay(satisfiesMock1);
            satisfiesTrace2 = new SatisfiesTraceHasInvariant(satisfiesMock2);
            expect(satisfiesMock2.invariant()).andReturn(satisfiesTrace2).anyTimes();
            replay(satisfiesMock2);
            boolean result;
            result = classUnderTest.satisfies().create(satisfiesMock1);
            assertTrue(result);
            result = classUnderTest.satisfies().create(satisfiesMock2);
            assertFalse(result);
            result = classUnderTest.satisfies().create(satisfiesMock1);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroySatisfiesReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            classUnderTest = new InvariantTrace("name", invariantContextMock1);
            satisfiesTrace1 = new SatisfiesTraceHasInvariant(satisfiesMock1);
            expect(satisfiesMock1.invariant()).andReturn(satisfiesTrace1).anyTimes();
            replay(satisfiesMock1);
            classUnderTest.satisfies().create(satisfiesMock1);
            boolean result = classUnderTest.satisfies().destroy(satisfiesMock1);
            assertTrue(result);
            assertThat(classUnderTest.satisfies().get(), is(nullValue()));
            satisfiesTrace2 = new SatisfiesTraceHasInvariant(satisfiesMock2);
            expect(satisfiesMock2.invariant()).andReturn(satisfiesTrace2).anyTimes();
            replay(satisfiesMock2);
            result = classUnderTest.satisfies().destroy(satisfiesMock2);
            assertFalse(result);
        }
    }
    
    public static class GuardTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the limits reference. */
        @Mock
        private IGuardedElementTrace limitsMock1;
        
        /** Mock the target of the limits reference. */
        @Mock
        private IGuardedElementTrace limitsMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardedElementTraceHasGuard guardedElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardedElementTraceHasGuard guardedElementTrace2;
        
        private GuardTrace classUnderTest;
        
        @Test
        public void testGuardTraceInstantiation() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            assertThat(classUnderTest.limits().get(), is(limitsMock1));
            assertThat(limitsMock1.guard().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreGuardTraceAttributes on begin
	    //@Ignore
// protected region IgnoreGuardTraceAttributes end	    
	    @Test
        public void testGuardTraceAttributes() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
// protected region GuardTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            boolean result = false;
            classUnderTest.setResult(result);
            assertThat(classUnderTest.getResult(), is(result));
            result = true;
            classUnderTest.setResult(result);
            assertThat(classUnderTest.getResult(), is(result));
// protected region GuardTraceAttributes end
        }

        
        @Test
        public void testGuardTraceCreateLimitsContainerReferenceConflict() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            guardedElementTrace2 = new GuardedElementTraceHasGuard(limitsMock2);
            expect(limitsMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(limitsMock2);
        
            boolean result = classUnderTest.limits().create(limitsMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testGuardTraceDestroyLimitsContainerReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            boolean result = classUnderTest.limits().destroy(limitsMock1);
            assertTrue(result);
        }
        
        @Test
        public void testGuardTraceDestroyAndCreateLimitsContainerReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            guardedElementTrace2 = new GuardedElementTraceHasGuard(limitsMock2);
            expect(limitsMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(limitsMock2);
  
            boolean result = classUnderTest.limits().destroy(limitsMock1);
            assertTrue(result);
            result = classUnderTest.limits().create(limitsMock2);
            assertTrue(result);
            result = classUnderTest.limits().create(limitsMock2);
            assertFalse(result);
            result = classUnderTest.limits().create(limitsMock1);
            assertFalse(result);
        }
        
        @Test
        public void testGuardTraceCreateAccessesReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            guardedElementTrace2 = new GuardedElementTraceHasGuard(limitsMock2);
            expect(limitsMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(limitsMock2);
            IGuardTrace classUnderTest2 = new GuardTrace(limitsMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testGuardTraceDestroyAccessesReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            classUnderTest = new GuardTrace(limitsMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
    }
    
    public static class CheckTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasCheck invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasCheck invariantTrace2;
        
        private CheckTrace classUnderTest;
        
        @Test
        public void testCheckTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.check().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreCheckTraceAttributes on begin
	    //@Ignore
// protected region IgnoreCheckTraceAttributes end	    
	    @Test
        public void testCheckTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
// protected region CheckTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));                    
// protected region CheckTraceAttributes end
        }

        
        @Test
        public void testCheckTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasCheck(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
        
            boolean result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testCheckTraceDestroyInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testCheckTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasCheck(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
  
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            result = classUnderTest.invariant().create(invariantMock1);
            assertFalse(result);
        }
        
        @Test
        public void testCheckTraceCreateAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasCheck(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            ICheckTrace classUnderTest2 = new CheckTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testCheckTraceDestroyAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
    }
    
    public static class MessageTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasMessage invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasMessage invariantTrace2;
        
        private MessageTrace classUnderTest;
        
        @Test
        public void testMessageTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.message().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreMessageTraceAttributes on begin
	    //@Ignore
// protected region IgnoreMessageTraceAttributes end	    
	    @Test
        public void testMessageTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
// protected region MessageTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));                    
// protected region MessageTraceAttributes end
        }

        
        @Test
        public void testMessageTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasMessage(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
        
            boolean result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testMessageTraceDestroyInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testMessageTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasMessage(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
  
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            result = classUnderTest.invariant().create(invariantMock1);
            assertFalse(result);
        }
        
        @Test
        public void testMessageTraceCreateAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasMessage(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            IMessageTrace classUnderTest2 = new MessageTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testMessageTraceDestroyAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
    }
    
    public static class SatisfiesTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasSatisfies invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasSatisfies invariantTrace2;
        
        /** Mock the target of the satisfiedInvariants reference. */
        @Mock
        private IInvariantTrace satisfiedInvariantsMock1;
        
        /** Mock the target of the satisfiedInvariants reference. */
        @Mock
        private IInvariantTrace satisfiedInvariantsMock2;
        
        private SatisfiesTrace classUnderTest;
        
        @Test
        public void testSatisfiesTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.satisfies().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreSatisfiesTraceAttributes on begin
	    //@Ignore
// protected region IgnoreSatisfiesTraceAttributes end	    
	    @Test
        public void testSatisfiesTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
// protected region SatisfiesTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));      
            boolean all = false;
            classUnderTest.setAll(all);
            assertThat(classUnderTest.getAll(), is(all));
            all = false;
            classUnderTest.setAll(all);
            assertThat(classUnderTest.getAll(), is(all));
// protected region SatisfiesTraceAttributes end
        }

        
        @Test
        public void testSatisfiesTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasSatisfies(invariantMock2);
            expect(invariantMock2.satisfies()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
        
            boolean result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testSatisfiesTraceDestroyInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testSatisfiesTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            invariantTrace2 = new InvariantTraceHasSatisfies(invariantMock2);
            expect(invariantMock2.satisfies()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
  
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
            result = classUnderTest.invariant().create(invariantMock1);
            assertFalse(result);
        }
        
        @Test
        public void testSatisfiesTraceCreateAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasSatisfies(invariantMock2);
            expect(invariantMock2.satisfies()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            ISatisfiesTrace classUnderTest2 = new SatisfiesTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testSatisfiesTraceDestroyAccessesReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            access1 = new AccessHasExecution(accessesMock1);
            expect(accessesMock1.execution()).andReturn(access1).anyTimes();
            replay(accessesMock1);
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            access2 = new AccessHasExecution(accessesMock2);
            expect(accessesMock2.execution()).andReturn(access2).anyTimes();
            replay(accessesMock2);
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testSatisfiesTraceCreateSatisfiedInvariantsReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            boolean result;
            result = classUnderTest.satisfiedInvariants().create(satisfiedInvariantsMock1);
            assertTrue(result);
            result = classUnderTest.satisfiedInvariants().create(satisfiedInvariantsMock2);
            assertTrue(result);
            result = classUnderTest.satisfiedInvariants().create(satisfiedInvariantsMock1);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasSatisfies(invariantMock2);
            expect(invariantMock2.satisfies()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            ISatisfiesTrace classUnderTest2 = new SatisfiesTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testSatisfiesTraceDestroySatisfiedInvariantsReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new SatisfiesTrace(invariantMock1);
            classUnderTest.satisfiedInvariants().create(satisfiedInvariantsMock1);
            boolean result = classUnderTest.satisfiedInvariants().destroy(satisfiedInvariantsMock1);
            assertTrue(result);
            assertThat(classUnderTest.satisfiedInvariants().get(), not(hasItem(satisfiedInvariantsMock1)));
            result = classUnderTest.satisfiedInvariants().destroy(satisfiedInvariantsMock2);
            assertFalse(result);
        }
    }

}