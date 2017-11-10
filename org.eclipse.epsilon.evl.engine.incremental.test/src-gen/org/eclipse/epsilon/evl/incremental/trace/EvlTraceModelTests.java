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
@Suite.SuiteClasses({EvlTraceModelTests.EvlExecutionTraceTests.class,
                     EvlTraceModelTests.EvlModuleTests.class,
                     EvlTraceModelTests.ContextTests.class,
                     EvlTraceModelTests.InvariantTests.class,
                     EvlTraceModelTests.GuardTests.class,
                     EvlTraceModelTests.CheckTests.class,
                     EvlTraceModelTests.MessageTests.class,
                     EvlTraceModelTests.SatisfiesTests.class})
public class EvlTraceModelTests {

    
    public static class EvlExecutionTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock2;
        
        /** Mock the target of the module reference. */
        @Mock
        private Module moduleMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private Module moduleMock2;
        
        private EvlExecutionTrace classUnderTest;
        
        @Test
        public void testEvlExecutionTraceInstantiation() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
	    }
	    
// protected region IgnoreEvlExecutionTraceAttributes on begin
	    @Ignore
// protected region IgnoreEvlExecutionTraceAttributes end	    
	    @Test
        public void testEvlExecutionTraceAttributes() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
// protected region EvlExecutionTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region EvlExecutionTraceAttributes end
        }

        @Test
        public void testEvlExecutionTraceCreateModel() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
            boolean result;
            result = classUnderTest.model().create(modelMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlExecutionTraceDestroyModel() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
            classUnderTest.model().create(modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
            assertThat(classUnderTest.model().get(), not(hasItem(modelMock1)));
            result = classUnderTest.model().destroy(modelMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlExecutionTraceCreateModule() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
            boolean result;
            result = classUnderTest.module().create(moduleMock1);
            assertTrue(result);
            result = classUnderTest.module().create(moduleMock2);
            assertTrue(result);
            result = classUnderTest.module().create(moduleMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlExecutionTraceDestroyModule() throws Exception {
            classUnderTest = new EvlExecutionTraceImpl();
            classUnderTest.module().create(moduleMock1);
            boolean result = classUnderTest.module().destroy(moduleMock1);
            assertTrue(result);
            assertThat(classUnderTest.module().get(), not(hasItem(moduleMock1)));
            result = classUnderTest.module().destroy(moduleMock2);
            assertFalse(result);
        }
    }
    
    public static class EvlModuleTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the moduleElements reference. */
        @Mock
        private ModuleElement moduleElementMock1;
        
        /** Mock the target of the moduleElements reference. */
        @Mock
        private ModuleElement moduleElementMock2;
        
        /** Allow the target mock to populate the reference */
        private ModuleElementHasModule moduleElementHasModule1;
        
        /** Allow the target mock to populate the reference */
        private ModuleElementHasModule moduleElementHasModule2;
        
        /** Mock the container. */
        @Mock
        private ExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private ExecutionTraceHasModule executionTraceHasModule1;

        private EvlModule classUnderTest;
        
        @Test
        public void testEvlModuleInstantiation() throws Exception {
            executionTraceHasModule1 = new ExecutionTraceHasModuleImpl(containerMock);
            expect(containerMock.module()).andReturn(executionTraceHasModule1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleImpl("source", containerMock);
            Queue<Module> values = containerMock.module().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreEvlModuleAttributes on begin
	    @Ignore
// protected region IgnoreEvlModuleAttributes end	    
	    @Test
        public void testEvlModuleAttributes() throws Exception {
            executionTraceHasModule1 = new ExecutionTraceHasModuleImpl(containerMock);
            expect(containerMock.module()).andReturn(executionTraceHasModule1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleImpl("source", containerMock);
// protected region EvlModuleAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region EvlModuleAttributes end
        }

        @Test
        public void testEvlModuleCreateModuleElement() throws Exception {
            executionTraceHasModule1 = new ExecutionTraceHasModuleImpl(containerMock);
            expect(containerMock.module()).andReturn(executionTraceHasModule1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleImpl("source", containerMock);
            moduleElementHasModule1 = new ModuleElementHasModuleImpl(moduleElementMock1);
            expect(moduleElementMock1.module()).andReturn(moduleElementHasModule1).anyTimes();
            replay(moduleElementMock1);
            moduleElementHasModule2 = new ModuleElementHasModuleImpl(moduleElementMock2);
            expect(moduleElementMock2.module()).andReturn(moduleElementHasModule2).anyTimes();
            replay(moduleElementMock2);
            boolean result;
            result = classUnderTest.moduleElements().create(moduleElementMock1);
            assertTrue(result);
            result = classUnderTest.moduleElements().create(moduleElementMock2);
            assertTrue(result);
            result = classUnderTest.moduleElements().create(moduleElementMock1);
            assertFalse(result);
        }
        
        @Test
        public void testEvlModuleDestroyModuleElement() throws Exception {
            executionTraceHasModule1 = new ExecutionTraceHasModuleImpl(containerMock);
            expect(containerMock.module()).andReturn(executionTraceHasModule1).anyTimes();
            replay(containerMock);
            classUnderTest = new EvlModuleImpl("source", containerMock);
            moduleElementHasModule1 = new ModuleElementHasModuleImpl(moduleElementMock1);
            expect(moduleElementMock1.module()).andReturn(moduleElementHasModule1).anyTimes();
            replay(moduleElementMock1);
            classUnderTest.moduleElements().create(moduleElementMock1);
            boolean result = classUnderTest.moduleElements().destroy(moduleElementMock1);
            assertTrue(result);
            assertThat(classUnderTest.moduleElements().get(), not(hasItem(moduleElementMock1)));
            moduleElementHasModule2 = new ModuleElementHasModuleImpl(moduleElementMock2);
            expect(moduleElementMock2.module()).andReturn(moduleElementHasModule2).anyTimes();
            replay(moduleElementMock2);
            result = classUnderTest.moduleElements().destroy(moduleElementMock2);
            assertFalse(result);
        }
    }
    
    public static class ContextTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the guard reference. */
        @Mock
        private Guard guardMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private Guard guardMock2;
        
        /** Allow the target mock to populate the reference */
        private GuardHasLimits guardHasLimits1;
        
        /** Allow the target mock to populate the reference */
        private GuardHasLimits guardHasLimits2;
        
        /** Mock the target of the module reference. */
        @Mock
        private Module moduleMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private Module moduleMock2;
        
        /** Allow the target mock to populate the reference */
        private ModuleHasModuleElements moduleHasModuleElements1;
        
        /** Allow the target mock to populate the reference */
        private ModuleHasModuleElements moduleHasModuleElements2;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private Invariant invariantMock1;
        
        /** Mock the target of the constraints reference. */
        @Mock
        private Invariant invariantMock2;
        
        /** Mock the target of the context reference. */
        @Mock
        private ModelElement modelElementMock1;
        
        /** Mock the target of the context reference. */
        @Mock
        private ModelElement modelElementMock2;
        
        private Context classUnderTest;
        
        @Test
        public void testContextInstantiation() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            assertThat(classUnderTest.module().get(), is(moduleMock1));
            Queue<ModuleElement> values = moduleMock1.moduleElements().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreContextAttributes on begin
	    @Ignore
// protected region IgnoreContextAttributes end	    
	    @Test
        public void testContextAttributes() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
// protected region ContextAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ContextAttributes end
        }

        
        @Test
        public void testContextCreateModuleConflict() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            moduleHasModuleElements2 = new ModuleHasModuleElementsImpl(moduleMock2);
            expect(moduleMock2.moduleElements()).andReturn(moduleHasModuleElements2).anyTimes();
            replay(moduleMock2);
        
            boolean result = classUnderTest.module().create(moduleMock2);
            assertFalse(result);
        }
        
        @Test
        public void testContextDestroyModule() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            boolean result = classUnderTest.module().destroy(moduleMock1);
            assertTrue(result);
        }
        
        @Test
        public void testContextDestroyAndCreateModule() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            moduleHasModuleElements2 = new ModuleHasModuleElementsImpl(moduleMock2);
            expect(moduleMock2.moduleElements()).andReturn(moduleHasModuleElements2).anyTimes();
            replay(moduleMock2);
  
            boolean result = classUnderTest.module().destroy(moduleMock1);
            assertTrue(result);
            result = classUnderTest.module().create(moduleMock2);
            assertTrue(result);
            result = classUnderTest.module().create(moduleMock2);
            assertFalse(result);
            result = classUnderTest.module().create(moduleMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextCreateGuard() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            guardHasLimits1 = new GuardHasLimitsImpl(guardMock1);
            expect(guardMock1.limits()).andReturn(guardHasLimits1).anyTimes();
            replay(guardMock1);
            guardHasLimits2 = new GuardHasLimitsImpl(guardMock2);
            expect(guardMock2.limits()).andReturn(guardHasLimits2).anyTimes();
            replay(guardMock2);
            boolean result;
            result = classUnderTest.guard().create(guardMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextDestroyGuard() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            guardHasLimits1 = new GuardHasLimitsImpl(guardMock1);
            expect(guardMock1.limits()).andReturn(guardHasLimits1).anyTimes();
            replay(guardMock1);
            classUnderTest.guard().create(guardMock1);
            boolean result = classUnderTest.guard().destroy(guardMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardHasLimits2 = new GuardHasLimitsImpl(guardMock2);
            expect(guardMock2.limits()).andReturn(guardHasLimits2).anyTimes();
            replay(guardMock2);
            result = classUnderTest.guard().destroy(guardMock2);
            assertFalse(result);
        }
        @Test
        public void testContextCreateInvariant() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            boolean result;
            result = classUnderTest.constraints().create(invariantMock1);
            assertTrue(result);
            result = classUnderTest.constraints().create(invariantMock2);
            assertTrue(result);
            result = classUnderTest.constraints().create(invariantMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextDestroyInvariant() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            classUnderTest.constraints().create(invariantMock1);
            boolean result = classUnderTest.constraints().destroy(invariantMock1);
            assertTrue(result);
            assertThat(classUnderTest.constraints().get(), not(hasItem(invariantMock1)));
            result = classUnderTest.constraints().destroy(invariantMock2);
            assertFalse(result);
        }
        @Test
        public void testContextCreateModelElement() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            boolean result;
            result = classUnderTest.context().create(modelElementMock1);
            assertTrue(result);
            result = classUnderTest.context().create(modelElementMock2);
            assertTrue(result);
            result = classUnderTest.context().create(modelElementMock1);
            assertFalse(result);
        }
        
        @Test
        public void testContextDestroyModelElement() throws Exception {
            moduleHasModuleElements1 = new ModuleHasModuleElementsImpl(moduleMock1);
            expect(moduleMock1.moduleElements()).andReturn(moduleHasModuleElements1).anyTimes();
            replay(moduleMock1);
            classUnderTest = new ContextImpl(moduleMock1);
            classUnderTest.context().create(modelElementMock1);
            boolean result = classUnderTest.context().destroy(modelElementMock1);
            assertTrue(result);
            assertThat(classUnderTest.context().get(), not(hasItem(modelElementMock1)));
            result = classUnderTest.context().destroy(modelElementMock2);
            assertFalse(result);
        }
    }
    
    public static class InvariantTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the guard reference. */
        @Mock
        private Guard guardMock1;
        
        /** Mock the target of the guard reference. */
        @Mock
        private Guard guardMock2;
        
        /** Allow the target mock to populate the reference */
        private GuardHasLimits guardHasLimits1;
        
        /** Allow the target mock to populate the reference */
        private GuardHasLimits guardHasLimits2;
        
        /** Mock the target of the check reference. */
        @Mock
        private Check checkMock1;
        
        /** Mock the target of the check reference. */
        @Mock
        private Check checkMock2;
        
        /** Allow the target mock to populate the reference */
        private CheckHasInvariant checkHasInvariant1;
        
        /** Allow the target mock to populate the reference */
        private CheckHasInvariant checkHasInvariant2;
        
        /** Mock the target of the message reference. */
        @Mock
        private Message messageMock1;
        
        /** Mock the target of the message reference. */
        @Mock
        private Message messageMock2;
        
        /** Allow the target mock to populate the reference */
        private MessageHasInvariant messageHasInvariant1;
        
        /** Allow the target mock to populate the reference */
        private MessageHasInvariant messageHasInvariant2;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private Satisfies satisfiesMock1;
        
        /** Mock the target of the satisfies reference. */
        @Mock
        private Satisfies satisfiesMock2;
        
        /** Mock the container. */
        @Mock
        private Context containerMock;
        
        /** Allow the container mock to populate the reference */
        private ContextHasConstraints contextHasConstraints1;

        private Invariant classUnderTest;
        
        @Test
        public void testInvariantInstantiation() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            Queue<Invariant> values = containerMock.constraints().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreInvariantAttributes on begin
	    @Ignore
// protected region IgnoreInvariantAttributes end	    
	    @Test
        public void testInvariantAttributes() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
// protected region InvariantAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region InvariantAttributes end
        }

        @Test
        public void testInvariantCreateGuard() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            guardHasLimits1 = new GuardHasLimitsImpl(guardMock1);
            expect(guardMock1.limits()).andReturn(guardHasLimits1).anyTimes();
            replay(guardMock1);
            guardHasLimits2 = new GuardHasLimitsImpl(guardMock2);
            expect(guardMock2.limits()).andReturn(guardHasLimits2).anyTimes();
            replay(guardMock2);
            boolean result;
            result = classUnderTest.guard().create(guardMock1);
            assertTrue(result);
            result = classUnderTest.guard().create(guardMock2);
            assertFalse(result);
            result = classUnderTest.guard().create(guardMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantDestroyGuard() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            guardHasLimits1 = new GuardHasLimitsImpl(guardMock1);
            expect(guardMock1.limits()).andReturn(guardHasLimits1).anyTimes();
            replay(guardMock1);
            classUnderTest.guard().create(guardMock1);
            boolean result = classUnderTest.guard().destroy(guardMock1);
            assertTrue(result);
            assertThat(classUnderTest.guard().get(), is(nullValue()));
            guardHasLimits2 = new GuardHasLimitsImpl(guardMock2);
            expect(guardMock2.limits()).andReturn(guardHasLimits2).anyTimes();
            replay(guardMock2);
            result = classUnderTest.guard().destroy(guardMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantCreateCheck() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            checkHasInvariant1 = new CheckHasInvariantImpl(checkMock1);
            expect(checkMock1.invariant()).andReturn(checkHasInvariant1).anyTimes();
            replay(checkMock1);
            checkHasInvariant2 = new CheckHasInvariantImpl(checkMock2);
            expect(checkMock2.invariant()).andReturn(checkHasInvariant2).anyTimes();
            replay(checkMock2);
            boolean result;
            result = classUnderTest.check().create(checkMock1);
            assertTrue(result);
            result = classUnderTest.check().create(checkMock2);
            assertFalse(result);
            result = classUnderTest.check().create(checkMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantDestroyCheck() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            checkHasInvariant1 = new CheckHasInvariantImpl(checkMock1);
            expect(checkMock1.invariant()).andReturn(checkHasInvariant1).anyTimes();
            replay(checkMock1);
            classUnderTest.check().create(checkMock1);
            boolean result = classUnderTest.check().destroy(checkMock1);
            assertTrue(result);
            assertThat(classUnderTest.check().get(), is(nullValue()));
            checkHasInvariant2 = new CheckHasInvariantImpl(checkMock2);
            expect(checkMock2.invariant()).andReturn(checkHasInvariant2).anyTimes();
            replay(checkMock2);
            result = classUnderTest.check().destroy(checkMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantCreateMessage() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            messageHasInvariant1 = new MessageHasInvariantImpl(messageMock1);
            expect(messageMock1.invariant()).andReturn(messageHasInvariant1).anyTimes();
            replay(messageMock1);
            messageHasInvariant2 = new MessageHasInvariantImpl(messageMock2);
            expect(messageMock2.invariant()).andReturn(messageHasInvariant2).anyTimes();
            replay(messageMock2);
            boolean result;
            result = classUnderTest.message().create(messageMock1);
            assertTrue(result);
            result = classUnderTest.message().create(messageMock2);
            assertFalse(result);
            result = classUnderTest.message().create(messageMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantDestroyMessage() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            messageHasInvariant1 = new MessageHasInvariantImpl(messageMock1);
            expect(messageMock1.invariant()).andReturn(messageHasInvariant1).anyTimes();
            replay(messageMock1);
            classUnderTest.message().create(messageMock1);
            boolean result = classUnderTest.message().destroy(messageMock1);
            assertTrue(result);
            assertThat(classUnderTest.message().get(), is(nullValue()));
            messageHasInvariant2 = new MessageHasInvariantImpl(messageMock2);
            expect(messageMock2.invariant()).andReturn(messageHasInvariant2).anyTimes();
            replay(messageMock2);
            result = classUnderTest.message().destroy(messageMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantCreateSatisfies() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            boolean result;
            result = classUnderTest.satisfies().create(satisfiesMock1);
            assertTrue(result);
            result = classUnderTest.satisfies().create(satisfiesMock2);
            assertFalse(result);
            result = classUnderTest.satisfies().create(satisfiesMock1);
            assertFalse(result);
        }
        
        @Test
        public void testInvariantDestroySatisfies() throws Exception {
            contextHasConstraints1 = new ContextHasConstraintsImpl(containerMock);
            expect(containerMock.constraints()).andReturn(contextHasConstraints1).anyTimes();
            replay(containerMock);
            classUnderTest = new InvariantImpl("name", containerMock);
            classUnderTest.satisfies().create(satisfiesMock1);
            boolean result = classUnderTest.satisfies().destroy(satisfiesMock1);
            assertTrue(result);
            assertThat(classUnderTest.satisfies().get(), is(nullValue()));
            result = classUnderTest.satisfies().destroy(satisfiesMock2);
            assertFalse(result);
        }
    }
    
    public static class GuardTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock2;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution1;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution2;
        
        /** Mock the target of the limits reference. */
        @Mock
        private GuardedElement guardedElementMock1;
        
        /** Mock the target of the limits reference. */
        @Mock
        private GuardedElement guardedElementMock2;
        
        /** Allow the target mock to populate the reference */
        private GuardedElementHasGuard guardedElementHasGuard1;
        
        /** Allow the target mock to populate the reference */
        private GuardedElementHasGuard guardedElementHasGuard2;
        
        private Guard classUnderTest;
        
        @Test
        public void testGuardInstantiation() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            assertThat(classUnderTest.limits().get(), is(guardedElementMock1));
            assertThat(guardedElementMock1.guard().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreGuardAttributes on begin
	    @Ignore
// protected region IgnoreGuardAttributes end	    
	    @Test
        public void testGuardAttributes() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
// protected region GuardAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region GuardAttributes end
        }

        
        @Test
        public void testGuardCreateLimitsConflict() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            guardedElementHasGuard2 = new GuardedElementHasGuardImpl(guardedElementMock2);
            expect(guardedElementMock2.guard()).andReturn(guardedElementHasGuard2).anyTimes();
            replay(guardedElementMock2);
        
            boolean result = classUnderTest.limits().create(guardedElementMock2);
            assertFalse(result);
        }
        
        @Test
        public void testGuardDestroyLimits() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            boolean result = classUnderTest.limits().destroy(guardedElementMock1);
            assertTrue(result);
        }
        
        @Test
        public void testGuardDestroyAndCreateLimits() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            guardedElementHasGuard2 = new GuardedElementHasGuardImpl(guardedElementMock2);
            expect(guardedElementMock2.guard()).andReturn(guardedElementHasGuard2).anyTimes();
            replay(guardedElementMock2);
  
            boolean result = classUnderTest.limits().destroy(guardedElementMock1);
            assertTrue(result);
            result = classUnderTest.limits().create(guardedElementMock2);
            assertTrue(result);
            result = classUnderTest.limits().create(guardedElementMock2);
            assertFalse(result);
            result = classUnderTest.limits().create(guardedElementMock1);
            assertFalse(result);
        }
        
        @Test
        public void testGuardCreateAccess() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
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
        public void testGuardDestroyAccess() throws Exception {
            guardedElementHasGuard1 = new GuardedElementHasGuardImpl(guardedElementMock1);
            expect(guardedElementMock1.guard()).andReturn(guardedElementHasGuard1).anyTimes();
            replay(guardedElementMock1);
            classUnderTest = new GuardImpl(guardedElementMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class CheckTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock2;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution1;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private Invariant invariantMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private Invariant invariantMock2;
        
        /** Allow the target mock to populate the reference */
        private InvariantHasCheck invariantHasCheck1;
        
        /** Allow the target mock to populate the reference */
        private InvariantHasCheck invariantHasCheck2;
        
        private Check classUnderTest;
        
        @Test
        public void testCheckInstantiation() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.check().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreCheckAttributes on begin
	    @Ignore
// protected region IgnoreCheckAttributes end	    
	    @Test
        public void testCheckAttributes() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
// protected region CheckAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region CheckAttributes end
        }

        
        @Test
        public void testCheckCreateInvariantConflict() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            invariantHasCheck2 = new InvariantHasCheckImpl(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantHasCheck2).anyTimes();
            replay(invariantMock2);
        
            boolean result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
        }
        
        @Test
        public void testCheckDestroyInvariant() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testCheckDestroyAndCreateInvariant() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            invariantHasCheck2 = new InvariantHasCheckImpl(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantHasCheck2).anyTimes();
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
        public void testCheckCreateAccess() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
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
        public void testCheckDestroyAccess() throws Exception {
            invariantHasCheck1 = new InvariantHasCheckImpl(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantHasCheck1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new CheckImpl(invariantMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class MessageTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private Access accessMock2;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution1;
        
        /** Allow the target mock to populate the reference */
        private AccessHasExecution accessHasExecution2;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private Invariant invariantMock1;
        
        /** Mock the target of the invariant reference. */
        @Mock
        private Invariant invariantMock2;
        
        /** Allow the target mock to populate the reference */
        private InvariantHasMessage invariantHasMessage1;
        
        /** Allow the target mock to populate the reference */
        private InvariantHasMessage invariantHasMessage2;
        
        private Message classUnderTest;
        
        @Test
        public void testMessageInstantiation() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.message().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreMessageAttributes on begin
	    @Ignore
// protected region IgnoreMessageAttributes end	    
	    @Test
        public void testMessageAttributes() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
// protected region MessageAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region MessageAttributes end
        }

        
        @Test
        public void testMessageCreateInvariantConflict() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            invariantHasMessage2 = new InvariantHasMessageImpl(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantHasMessage2).anyTimes();
            replay(invariantMock2);
        
            boolean result = classUnderTest.invariant().create(invariantMock2);
            assertFalse(result);
        }
        
        @Test
        public void testMessageDestroyInvariant() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testMessageDestroyAndCreateInvariant() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            invariantHasMessage2 = new InvariantHasMessageImpl(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantHasMessage2).anyTimes();
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
        public void testMessageCreateAccess() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
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
        public void testMessageDestroyAccess() throws Exception {
            invariantHasMessage1 = new InvariantHasMessageImpl(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantHasMessage1).anyTimes();
            replay(invariantMock1);
            classUnderTest = new MessageImpl(invariantMock1);
            accessHasExecution1 = new AccessHasExecutionImpl(accessMock1);
            expect(accessMock1.execution()).andReturn(accessHasExecution1).anyTimes();
            replay(accessMock1);
            classUnderTest.accesses().create(accessMock1);
            boolean result = classUnderTest.accesses().destroy(accessMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessMock1)));
            accessHasExecution2 = new AccessHasExecutionImpl(accessMock2);
            expect(accessMock2.execution()).andReturn(accessHasExecution2).anyTimes();
            replay(accessMock2);
            result = classUnderTest.accesses().destroy(accessMock2);
            assertFalse(result);
        }
    }
    
    public static class SatisfiesTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the invariants reference. */
        @Mock
        private Invariant invariantMock1;
        
        /** Mock the target of the invariants reference. */
        @Mock
        private Invariant invariantMock2;
        
        /** Mock the container. */
        @Mock
        private Invariant containerMock;
        
        /** Allow the container mock to populate the reference */
        private InvariantHasSatisfies invariantHasSatisfies1;

        private Satisfies classUnderTest;
        
        @Test
        public void testSatisfiesInstantiation() throws Exception {
            invariantHasSatisfies1 = new InvariantHasSatisfiesImpl(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantHasSatisfies1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesImpl(containerMock);
            assertThat(containerMock.satisfies().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreSatisfiesAttributes on begin
	    @Ignore
// protected region IgnoreSatisfiesAttributes end	    
	    @Test
        public void testSatisfiesAttributes() throws Exception {
            invariantHasSatisfies1 = new InvariantHasSatisfiesImpl(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantHasSatisfies1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesImpl(containerMock);
// protected region SatisfiesAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region SatisfiesAttributes end
        }

        @Test
        public void testSatisfiesCreateInvariant() throws Exception {
            invariantHasSatisfies1 = new InvariantHasSatisfiesImpl(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantHasSatisfies1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesImpl(containerMock);
            boolean result;
            result = classUnderTest.invariants().create(invariantMock1);
            assertTrue(result);
            result = classUnderTest.invariants().create(invariantMock2);
            assertTrue(result);
            result = classUnderTest.invariants().create(invariantMock1);
            assertFalse(result);
        }
        
        @Test
        public void testSatisfiesDestroyInvariant() throws Exception {
            invariantHasSatisfies1 = new InvariantHasSatisfiesImpl(containerMock);
            expect(containerMock.satisfies()).andReturn(invariantHasSatisfies1).anyTimes();
            replay(containerMock);
            classUnderTest = new SatisfiesImpl(containerMock);
            classUnderTest.invariants().create(invariantMock1);
            boolean result = classUnderTest.invariants().destroy(invariantMock1);
            assertTrue(result);
            assertThat(classUnderTest.invariants().get(), not(hasItem(invariantMock1)));
            result = classUnderTest.invariants().destroy(invariantMock2);
            assertFalse(result);
        }
    }

}