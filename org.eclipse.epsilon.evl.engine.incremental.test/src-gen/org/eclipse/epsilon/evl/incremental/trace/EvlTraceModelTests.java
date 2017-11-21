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
        private IModuleTrace moduleTraceMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleTraceMock2;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock2;
        
        /** Mock the target of the executions reference. */
        @Mock
        private IExecutionTrace executionTraceMock1;
        
        /** Mock the target of the executions reference. */
        @Mock
        private IExecutionTrace executionTraceMock2;
        
        /** Mock the target of the moduleElements reference. */
        @Mock
        private IModuleElementTrace moduleElementTraceMock1;
        
        /** Mock the target of the moduleElements reference. */
        @Mock
        private IModuleElementTrace moduleElementTraceMock2;
        
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
        public void testEvlModuleExecutionCreateModuleTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.module().create(moduleTraceMock1);
            assertTrue(result);
            result = classUnderTest.module().create(moduleTraceMock2);
            assertFalse(result);
            result = classUnderTest.module().create(moduleTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlModuleExecutionDestroyModuleTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.module().create(moduleTraceMock1);
            boolean result = classUnderTest.module().destroy(moduleTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.module().get(), is(nullValue()));
            result = classUnderTest.module().destroy(moduleTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleExecutionCreateModelTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.model().create(modelTraceMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlModuleExecutionDestroyModelTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.model().create(modelTraceMock1);
            boolean result = classUnderTest.model().destroy(modelTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.model().get(), not(hasItem(modelTraceMock1)));
            result = classUnderTest.model().destroy(modelTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleExecutionCreateExecutionTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.executions().create(executionTraceMock1);
            assertTrue(result);
            result = classUnderTest.executions().create(executionTraceMock2);
            assertTrue(result);
            result = classUnderTest.executions().create(executionTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlModuleExecutionDestroyExecutionTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.executions().create(executionTraceMock1);
            boolean result = classUnderTest.executions().destroy(executionTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.executions().get(), not(hasItem(executionTraceMock1)));
            result = classUnderTest.executions().destroy(executionTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleExecutionCreateModuleElementTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            boolean result;
            result = classUnderTest.moduleElements().create(moduleElementTraceMock1);
            assertTrue(result);
            result = classUnderTest.moduleElements().create(moduleElementTraceMock2);
            assertTrue(result);
            result = classUnderTest.moduleElements().create(moduleElementTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlModuleExecutionDestroyModuleElementTrace() throws Exception {
            classUnderTest = new EvlModuleExecution();
            classUnderTest.moduleElements().create(moduleElementTraceMock1);
            boolean result = classUnderTest.moduleElements().destroy(moduleElementTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.moduleElements().get(), not(hasItem(moduleElementTraceMock1)));
            result = classUnderTest.moduleElements().destroy(moduleElementTraceMock2);
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

        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardTraceMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace2;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleTraceMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleTraceMock2;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private IInvariantTrace invariantTraceMock1;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private IInvariantTrace invariantTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasInvariantContext invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasInvariantContext invariantTrace2;
        
        /** Mock the target of the context reference. */
        @Mock
        private IModuleElementTrace moduleElementTraceMock1;
        
        /** Mock the target of the context reference. */
        @Mock
        private IModuleElementTrace moduleElementTraceMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecution containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionHasModuleElements moduleExecution1;

        private ContextTrace classUnderTest;
        
        @Test
        public void testContextTraceInstantiation() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            Queue<IModuleElementTrace> values = containerMock.moduleElements().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreContextTraceAttributes on begin
	    @Ignore
// protected region IgnoreContextTraceAttributes end	    
	    @Test
        public void testContextTraceAttributes() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
// protected region ContextTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ContextTraceAttributes end
        }

        @Test
        public void testContextTraceCreateGuardTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            guardTrace1 = new GuardTraceHasLimits(guardTraceMock1);
            expect(guardTraceMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardTraceMock1);
            guardTrace2 = new GuardTraceHasLimits(guardTraceMock2);
            expect(guardTraceMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardTraceMock2);
            boolean result;
            result = classUnderTest.guard().create(guardTraceMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardTraceMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextTraceDestroyGuardTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            guardTrace1 = new GuardTraceHasLimits(guardTraceMock1);
            expect(guardTraceMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardTraceMock1);
            classUnderTest.guard().create(guardTraceMock1);
            boolean result = classUnderTest.guard().destroy(guardTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardTrace2 = new GuardTraceHasLimits(guardTraceMock2);
            expect(guardTraceMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardTraceMock2);
            result = classUnderTest.guard().destroy(guardTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateAccess() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextTraceDestroyAccess() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateModuleTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            boolean result;
            result = classUnderTest.module().create(moduleTraceMock1);
            assertTrue(result);
            result = classUnderTest.module().create(moduleTraceMock2);
            assertTrue(result);
            result = classUnderTest.module().create(moduleTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextTraceDestroyModuleTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            classUnderTest.module().create(moduleTraceMock1);
            boolean result = classUnderTest.module().destroy(moduleTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.module().get(), not(hasItem(moduleTraceMock1)));
            result = classUnderTest.module().destroy(moduleTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateInvariantTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            invariantTrace1 = new InvariantTraceHasInvariantContext(invariantTraceMock1);
            expect(invariantTraceMock1.invariantContext()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            invariantTrace2 = new InvariantTraceHasInvariantContext(invariantTraceMock2);
            expect(invariantTraceMock2.invariantContext()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
            boolean result;
            result = classUnderTest.constraints().create(invariantTraceMock1);
            assertTrue(result);
            result = classUnderTest.constraints().create(invariantTraceMock2);
            assertTrue(result);
            result = classUnderTest.constraints().create(invariantTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextTraceDestroyInvariantTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            invariantTrace1 = new InvariantTraceHasInvariantContext(invariantTraceMock1);
            expect(invariantTraceMock1.invariantContext()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest.constraints().create(invariantTraceMock1);
            boolean result = classUnderTest.constraints().destroy(invariantTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.constraints().get(), not(hasItem(invariantTraceMock1)));
            invariantTrace2 = new InvariantTraceHasInvariantContext(invariantTraceMock2);
            expect(invariantTraceMock2.invariantContext()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
            result = classUnderTest.constraints().destroy(invariantTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateModuleElementTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            boolean result;
            result = classUnderTest.context().create(moduleElementTraceMock1);
            assertTrue(result);
            result = classUnderTest.context().create(moduleElementTraceMock2);
            assertTrue(result);
            result = classUnderTest.context().create(moduleElementTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextTraceDestroyModuleElementTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModuleElements(containerMock);
            expect(containerMock.moduleElements()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ContextTrace("kind", containerMock);
            classUnderTest.context().create(moduleElementTraceMock1);
            boolean result = classUnderTest.context().destroy(moduleElementTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.context().get(), not(hasItem(moduleElementTraceMock1)));
            result = classUnderTest.context().destroy(moduleElementTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class InvariantTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardTraceMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private IGuardTrace guardTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardTraceHasLimits guardTrace2;
        
        /** Mock the target of the check reference. */
        @Mock
        private ICheckTrace checkTraceMock1;
        
        /** Mock the target of the check reference. */
        @Mock
        private ICheckTrace checkTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private ICheckTraceHasInvariant checkTrace1;
        
        /** Allow the target mock to populate the reference */
        private ICheckTraceHasInvariant checkTrace2;
        
        /** Mock the target of the message reference. */
        @Mock
        private IMessageTrace messageTraceMock1;
        
        /** Mock the target of the message reference. */
        @Mock
        private IMessageTrace messageTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IMessageTraceHasInvariant messageTrace1;
        
        /** Allow the target mock to populate the reference */
        private IMessageTraceHasInvariant messageTrace2;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private ISatisfiesTrace satisfiesTraceMock1;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private ISatisfiesTrace satisfiesTraceMock2;
        
        /** Mock the target of the invariantContext reference. */
        @Mock
        private IContextTrace contextTraceMock1;
        
        /** Mock the target of the invariantContext reference. */
        @Mock
        private IContextTrace contextTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IContextTraceHasConstraints contextTrace1;
        
        /** Allow the target mock to populate the reference */
        private IContextTraceHasConstraints contextTrace2;
        
        private InvariantTrace classUnderTest;
        
        @Test
        public void testInvariantTraceInstantiation() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            assertThat(classUnderTest.invariantContext().get(), is(contextTraceMock1));
            Queue<IInvariantTrace> values = contextTraceMock1.constraints().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreInvariantTraceAttributes on begin
	    @Ignore
// protected region IgnoreInvariantTraceAttributes end	    
	    @Test
        public void testInvariantTraceAttributes() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
// protected region InvariantTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region InvariantTraceAttributes end
        }

        
        @Test
        public void testInvariantTraceCreateInvariantContextConflict() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            contextTrace2 = new ContextTraceHasConstraints(contextTraceMock2);
            expect(contextTraceMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(contextTraceMock2);
        
            boolean result = classUnderTest.invariantContext().create(contextTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceDestroyInvariantContext() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            boolean result = classUnderTest.invariantContext().destroy(contextTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testInvariantTraceDestroyAndCreateInvariantContext() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            contextTrace2 = new ContextTraceHasConstraints(contextTraceMock2);
            expect(contextTraceMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(contextTraceMock2);
  
            boolean result = classUnderTest.invariantContext().destroy(contextTraceMock1);
            assertTrue(result);
            result = classUnderTest.invariantContext().create(contextTraceMock2);
            assertTrue(result);
            result = classUnderTest.invariantContext().create(contextTraceMock2);
            assertFalse(result);
            result = classUnderTest.invariantContext().create(contextTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceCreateGuardTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            guardTrace1 = new GuardTraceHasLimits(guardTraceMock1);
            expect(guardTraceMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardTraceMock1);
            guardTrace2 = new GuardTraceHasLimits(guardTraceMock2);
            expect(guardTraceMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardTraceMock2);
            boolean result;
            result = classUnderTest.guard().create(guardTraceMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardTraceMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceDestroyGuardTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            guardTrace1 = new GuardTraceHasLimits(guardTraceMock1);
            expect(guardTraceMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardTraceMock1);
            classUnderTest.guard().create(guardTraceMock1);
            boolean result = classUnderTest.guard().destroy(guardTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardTrace2 = new GuardTraceHasLimits(guardTraceMock2);
            expect(guardTraceMock2.limits()).andReturn(guardTrace2).anyTimes();
            replay(guardTraceMock2);
            result = classUnderTest.guard().destroy(guardTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateCheckTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            checkTrace1 = new CheckTraceHasInvariant(checkTraceMock1);
            expect(checkTraceMock1.invariant()).andReturn(checkTrace1).anyTimes();
            replay(checkTraceMock1);
            checkTrace2 = new CheckTraceHasInvariant(checkTraceMock2);
            expect(checkTraceMock2.invariant()).andReturn(checkTrace2).anyTimes();
            replay(checkTraceMock2);
            boolean result;
            result = classUnderTest.check().create(checkTraceMock1);
            assertTrue(result);
            result = classUnderTest.check().create(checkTraceMock2);
            assertFalse(result);
            result = classUnderTest.check().create(checkTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceDestroyCheckTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            checkTrace1 = new CheckTraceHasInvariant(checkTraceMock1);
            expect(checkTraceMock1.invariant()).andReturn(checkTrace1).anyTimes();
            replay(checkTraceMock1);
            classUnderTest.check().create(checkTraceMock1);
            boolean result = classUnderTest.check().destroy(checkTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.check().get(), is(nullValue()));
            checkTrace2 = new CheckTraceHasInvariant(checkTraceMock2);
            expect(checkTraceMock2.invariant()).andReturn(checkTrace2).anyTimes();
            replay(checkTraceMock2);
            result = classUnderTest.check().destroy(checkTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateMessageTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            messageTrace1 = new MessageTraceHasInvariant(messageTraceMock1);
            expect(messageTraceMock1.invariant()).andReturn(messageTrace1).anyTimes();
            replay(messageTraceMock1);
            messageTrace2 = new MessageTraceHasInvariant(messageTraceMock2);
            expect(messageTraceMock2.invariant()).andReturn(messageTrace2).anyTimes();
            replay(messageTraceMock2);
            boolean result;
            result = classUnderTest.message().create(messageTraceMock1);
            assertTrue(result);
            result = classUnderTest.message().create(messageTraceMock2);
            assertFalse(result);
            result = classUnderTest.message().create(messageTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceDestroyMessageTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            messageTrace1 = new MessageTraceHasInvariant(messageTraceMock1);
            expect(messageTraceMock1.invariant()).andReturn(messageTrace1).anyTimes();
            replay(messageTraceMock1);
            classUnderTest.message().create(messageTraceMock1);
            boolean result = classUnderTest.message().destroy(messageTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.message().get(), is(nullValue()));
            messageTrace2 = new MessageTraceHasInvariant(messageTraceMock2);
            expect(messageTraceMock2.invariant()).andReturn(messageTrace2).anyTimes();
            replay(messageTraceMock2);
            result = classUnderTest.message().destroy(messageTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateSatisfiesTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            boolean result;
            result = classUnderTest.satisfies().create(satisfiesTraceMock1);
            assertTrue(result);
            result = classUnderTest.satisfies().create(satisfiesTraceMock2);
            assertFalse(result);
            result = classUnderTest.satisfies().create(satisfiesTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantTraceDestroySatisfiesTrace() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(contextTraceMock1);
            expect(contextTraceMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(contextTraceMock1);
            classUnderTest = new InvariantTrace("name", contextTraceMock1);
            classUnderTest.satisfies().create(satisfiesTraceMock1);
            boolean result = classUnderTest.satisfies().destroy(satisfiesTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.satisfies().get(), is(nullValue()));
            result = classUnderTest.satisfies().destroy(satisfiesTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class GuardTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the limits reference. */
        @Mock
        private IGuardedElementTrace guardedElementTraceMock1;
        
        /** Mock the target of the limits reference. */
        @Mock
        private IGuardedElementTrace guardedElementTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IGuardedElementTraceHasGuard guardedElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IGuardedElementTraceHasGuard guardedElementTrace2;
        
        private GuardTrace classUnderTest;
        
        @Test
        public void testGuardTraceInstantiation() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            assertThat(classUnderTest.limits().get(), is(guardedElementTraceMock1));
            assertThat(guardedElementTraceMock1.guard().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreGuardTraceAttributes on begin
	    @Ignore
// protected region IgnoreGuardTraceAttributes end	    
	    @Test
        public void testGuardTraceAttributes() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
// protected region GuardTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region GuardTraceAttributes end
        }

        
        @Test
        public void testGuardTraceCreateLimitsConflict() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            guardedElementTrace2 = new GuardedElementTraceHasGuard(guardedElementTraceMock2);
            expect(guardedElementTraceMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(guardedElementTraceMock2);
        
            boolean result = classUnderTest.limits().create(guardedElementTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testGuardTraceDestroyLimits() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            boolean result = classUnderTest.limits().destroy(guardedElementTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testGuardTraceDestroyAndCreateLimits() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            guardedElementTrace2 = new GuardedElementTraceHasGuard(guardedElementTraceMock2);
            expect(guardedElementTraceMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(guardedElementTraceMock2);
  
            boolean result = classUnderTest.limits().destroy(guardedElementTraceMock1);
            assertTrue(result);
            result = classUnderTest.limits().create(guardedElementTraceMock2);
            assertTrue(result);
            result = classUnderTest.limits().create(guardedElementTraceMock2);
            assertFalse(result);
            result = classUnderTest.limits().create(guardedElementTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testGuardTraceCreateAccess() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock1);
            assertFalse(result);
        }
        
        @Test
        public void testGuardTraceDestroyAccess() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(guardedElementTraceMock1);
            expect(guardedElementTraceMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(guardedElementTraceMock1);
            classUnderTest = new GuardTrace(guardedElementTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class CheckTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantTraceMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasCheck invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasCheck invariantTrace2;
        
        private CheckTrace classUnderTest;
        
        @Test
        public void testCheckTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantTraceMock1));
            assertThat(invariantTraceMock1.check().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreCheckTraceAttributes on begin
	    @Ignore
// protected region IgnoreCheckTraceAttributes end	    
	    @Test
        public void testCheckTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
// protected region CheckTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region CheckTraceAttributes end
        }

        
        @Test
        public void testCheckTraceCreateInvariantConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            invariantTrace2 = new InvariantTraceHasCheck(invariantTraceMock2);
            expect(invariantTraceMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
        
            boolean result = classUnderTest.invariant().create(invariantTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testCheckTraceDestroyInvariant() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            boolean result = classUnderTest.invariant().destroy(invariantTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testCheckTraceDestroyAndCreateInvariant() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            invariantTrace2 = new InvariantTraceHasCheck(invariantTraceMock2);
            expect(invariantTraceMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
  
            boolean result = classUnderTest.invariant().destroy(invariantTraceMock1);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantTraceMock2);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantTraceMock2);
            assertFalse(result);
            result = classUnderTest.invariant().create(invariantTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testCheckTraceCreateAccess() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock1);
            assertFalse(result);
        }
        
        @Test
        public void testCheckTraceDestroyAccess() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantTraceMock1);
            expect(invariantTraceMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new CheckTrace(invariantTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class MessageTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessMock2;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access1;
        
        /** Allow the target mock to populate the reference */
        private IAccessHasExecution access2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantTraceMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private IInvariantTrace invariantTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasMessage invariantTrace1;
        
        /** Allow the target mock to populate the reference */
        private IInvariantTraceHasMessage invariantTrace2;
        
        private MessageTrace classUnderTest;
        
        @Test
        public void testMessageTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantTraceMock1));
            assertThat(invariantTraceMock1.message().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreMessageTraceAttributes on begin
	    @Ignore
// protected region IgnoreMessageTraceAttributes end	    
	    @Test
        public void testMessageTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
// protected region MessageTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region MessageTraceAttributes end
        }

        
        @Test
        public void testMessageTraceCreateInvariantConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            invariantTrace2 = new InvariantTraceHasMessage(invariantTraceMock2);
            expect(invariantTraceMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
        
            boolean result = classUnderTest.invariant().create(invariantTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testMessageTraceDestroyInvariant() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            boolean result = classUnderTest.invariant().destroy(invariantTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testMessageTraceDestroyAndCreateInvariant() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            invariantTrace2 = new InvariantTraceHasMessage(invariantTraceMock2);
            expect(invariantTraceMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantTraceMock2);
  
            boolean result = classUnderTest.invariant().destroy(invariantTraceMock1);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantTraceMock2);
            assertTrue(result);
            result = classUnderTest.invariant().create(invariantTraceMock2);
            assertFalse(result);
            result = classUnderTest.invariant().create(invariantTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testMessageTraceCreateAccess() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            boolean result;
            result = classUnderTest.accesses().create(accessMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessMock1);
            assertFalse(result);
        }
        
        @Test
        public void testMessageTraceDestroyAccess() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantTraceMock1);
            expect(invariantTraceMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantTraceMock1);
            classUnderTest = new MessageTrace(invariantTraceMock1);
            access1 = new AccessHasExecution(accessMock1);
            expect(accessMock1.execution()).andReturn(access1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            access2 = new AccessHasExecution(accessMock2);
            expect(accessMock2.execution()).andReturn(access2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class SatisfiesTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the invariants reference. */
        @Mock
        private IInvariantTrace invariantTraceMock1;
        
        /** Mock the target of the invariants reference. */
        @Mock
        private IInvariantTrace invariantTraceMock2;
        
        /** Mock the container. */
        @Mock
        private IInvariantTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IInvariantTraceHasSatisfies invariantTrace1;

        private SatisfiesTrace classUnderTest;
        
        @Test
        public void testSatisfiesTraceInstantiation() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesTrace(containerMock);
            assertThat(containerMock.satisfies().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreSatisfiesTraceAttributes on begin
	    @Ignore
// protected region IgnoreSatisfiesTraceAttributes end	    
	    @Test
        public void testSatisfiesTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesTrace(containerMock);
// protected region SatisfiesTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region SatisfiesTraceAttributes end
        }

        @Test
        public void testSatisfiesTraceCreateInvariantTrace() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesTrace(containerMock);
            boolean result;
            result = classUnderTest.invariants().create(invariantTraceMock1);
            assertTrue(result);
            result = classUnderTest.invariants().create(invariantTraceMock2);
            assertTrue(result);
            result = classUnderTest.invariants().create(invariantTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testSatisfiesTraceDestroyInvariantTrace() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesTrace(containerMock);
            classUnderTest.invariants().create(invariantTraceMock1);
            boolean result = classUnderTest.invariants().destroy(invariantTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.invariants().get(), not(hasItem(invariantTraceMock1)));
            result = classUnderTest.invariants().destroy(invariantTraceMock2);
            assertFalse(result);
        }
    }

}