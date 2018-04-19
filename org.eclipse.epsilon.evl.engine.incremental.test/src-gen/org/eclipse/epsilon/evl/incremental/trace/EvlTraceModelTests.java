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
import org.eclipse.epsilon.evl.incremental.trace.impl.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EvlTraceModelTests.EvlModuleTraceTests.class,
                     EvlTraceModelTests.ContextTraceTests.class,
                     EvlTraceModelTests.InvariantTraceTests.class,
                     EvlTraceModelTests.GuardTraceTests.class,
                     EvlTraceModelTests.CheckTraceTests.class,
                     EvlTraceModelTests.MessageTraceTests.class,
                     EvlTraceModelTests.SatisfiesTraceTests.class})
public class EvlTraceModelTests {

    
    public static class EvlModuleTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the ruleTraces reference. */
        @Mock
        private IRuleTrace ruleTracesMock1;
        
        /** Mock the target of the ruleTraces reference. */
        @Mock
        private IRuleTrace ruleTracesMock2;
        
        /** Mock the target of the executionContexts reference. */
        @Mock
        private IExecutionContext executionContextsMock1;
        
        /** Mock the target of the executionContexts reference. */
        @Mock
        private IExecutionContext executionContextsMock2;
        
        private EvlModuleTrace classUnderTest;

	    
// protected region IgnoreEvlModuleTraceAttributes on begin
	    @Ignore
// protected region IgnoreEvlModuleTraceAttributes end	    
	    @Test
        public void testEvlModuleTraceAttributes() throws Exception {
            // protected region EvlModuleTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new EvlModuleTrace("source1");                    
            // protected region EvlModuleTraceInit end     
// protected region EvlModuleTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region EvlModuleTraceAttributes end
        }

        @Test
        public void testEvlModuleTraceCreateRuleTracesReference() throws Exception {
            // protected region EvlModuleTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new EvlModuleTrace("source1");                    
            // protected region EvlModuleTraceInit end     
            boolean result;
            result = classUnderTest.ruleTraces().create(ruleTracesMock1);
            assertTrue(result);
            result = classUnderTest.ruleTraces().create(ruleTracesMock2);
            assertTrue(result);
            result = classUnderTest.ruleTraces().create(ruleTracesMock1);
            assertFalse(result);
            // Create a second one
            IEvlModuleTrace classUnderTest2 = new EvlModuleTrace("source2");
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testEvlModuleTraceDestroyRuleTracesReference() throws Exception {
            // protected region EvlModuleTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new EvlModuleTrace("source1");                    
            // protected region EvlModuleTraceInit end     
            classUnderTest.ruleTraces().create(ruleTracesMock1);
            boolean result = classUnderTest.ruleTraces().destroy(ruleTracesMock1);
            assertTrue(result);
            assertThat(classUnderTest.ruleTraces().get(), not(hasItem(ruleTracesMock1)));
            result = classUnderTest.ruleTraces().destroy(ruleTracesMock2);
            assertFalse(result);
        }
        @Test
        public void testEvlModuleTraceCreateExecutionContextsReference() throws Exception {
            // protected region EvlModuleTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new EvlModuleTrace("source1");                    
            // protected region EvlModuleTraceInit end     
            boolean result;
            result = classUnderTest.executionContexts().create(executionContextsMock1);
            assertTrue(result);
            result = classUnderTest.executionContexts().create(executionContextsMock2);
            assertTrue(result);
            result = classUnderTest.executionContexts().create(executionContextsMock1);
            assertFalse(result);
            // Create a second one
            IEvlModuleTrace classUnderTest2 = new EvlModuleTrace("source2");
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testEvlModuleTraceDestroyExecutionContextsReference() throws Exception {
            // protected region EvlModuleTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new EvlModuleTrace("source1");                    
            // protected region EvlModuleTraceInit end     
            classUnderTest.executionContexts().create(executionContextsMock1);
            boolean result = classUnderTest.executionContexts().destroy(executionContextsMock1);
            assertTrue(result);
            assertThat(classUnderTest.executionContexts().get(), not(hasItem(executionContextsMock1)));
            result = classUnderTest.executionContexts().destroy(executionContextsMock2);
            assertFalse(result);
        }
    }
    
    public static class ContextTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

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
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleMock1;
        
        /** Mock the target of the module reference. */
        @Mock
        private IModuleTrace moduleMock2;
        
        /** Mock the target of the executionContext reference. */
        @Mock
        private IExecutionContext executionContextMock1;
        
        /** Mock the target of the executionContext reference. */
        @Mock
        private IExecutionContext executionContextMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionContextHasRules executionContext1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionContextHasRules executionContext2;
        
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
        
        private ContextTrace classUnderTest;

	    
// protected region IgnoreContextTraceAttributes on begin
	    @Ignore
// protected region IgnoreContextTraceAttributes end	    
	    @Test
        public void testContextTraceAttributes() throws Exception {
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            Queue<IRuleTrace> values = containerMock.ruleTraces().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreContextTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreContextTraceAttributes end	    
	    @Test
        public void testContextTraceAttributes() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
// protected region ContextTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ContextTraceAttributes end
        }

        @Test
        public void testContextTraceCreateGuardReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
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
            moduleTrace2 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace2).anyTimes();
            replay(containerMock);
            IModuleTrace _module2 = mock(IModuleTrace.class);
             
            IExecutionContext _executionContext2 = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext2 = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext2.rules()).andReturn(executionContext2).anyTimes();
            replay(_executionContext2);
            expect(executionContext2.get()).andReturn(null).anyTimes();
            replay(executionContext2);
             
            IContextTrace classUnderTest2 = new ContextTrace("kind2", 2, _module2, _executionContext2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyGuardReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            guardTrace1 = new GuardTraceHasLimits(guardMock1);
            expect(guardMock1.limits()).andReturn(guardTrace1).anyTimes();
            replay(guardMock1);
            classUnderTest.guard().create(guardMock1);
        
            reset(executionContext);
            expect(executionContext.get()).andReturn(classUnderTest).anyTimes();
            replay(executionContext);
        
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
        public void testContextTraceCreateAccessesReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            boolean result;
            result = classUnderTest.accesses().create(accessesMock1);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock2);
            assertTrue(result);
            result = classUnderTest.accesses().create(accessesMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleTrace2 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace2).anyTimes();
            replay(containerMock);
            IModuleTrace _module2 = mock(IModuleTrace.class);
             
            IExecutionContext _executionContext2 = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext2 = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext2.rules()).andReturn(executionContext2).anyTimes();
            replay(_executionContext2);
            expect(executionContext2.get()).andReturn(null).anyTimes();
            replay(executionContext2);
             
            IContextTrace classUnderTest2 = new ContextTrace("kind2", 2, _module2, _executionContext2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyAccessesReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
        
            reset(executionContext);
            expect(executionContext.get()).andReturn(classUnderTest).anyTimes();
            replay(executionContext);
        
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateModuleReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            boolean result;
            result = classUnderTest.module().create(moduleMock2);
            assertFalse(result);
            result = classUnderTest.module().create(_module);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleTrace2 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace2).anyTimes();
            replay(containerMock);
            IModuleTrace _module2 = mock(IModuleTrace.class);
             
            IExecutionContext _executionContext2 = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext2 = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext2.rules()).andReturn(executionContext2).anyTimes();
            replay(_executionContext2);
            expect(executionContext2.get()).andReturn(null).anyTimes();
            replay(executionContext2);
             
            IContextTrace classUnderTest2 = new ContextTrace("kind2", 2, _module2, _executionContext2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyModuleReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
        
            reset(executionContext);
            expect(executionContext.get()).andReturn(classUnderTest).anyTimes();
            replay(executionContext);
        
            boolean result = classUnderTest.module().destroy(_module);
            assertTrue(result);
            assertThat(classUnderTest.module().get(), is(nullValue()));
            result = classUnderTest.module().destroy(moduleMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateExecutionContextReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            executionContext1 = new ExecutionContextHasRules(executionContextMock1);
            expect(executionContextMock1.rules()).andReturn(executionContext1).anyTimes();
            replay(executionContextMock1);
            executionContext2 = new ExecutionContextHasRules(executionContextMock2);
            expect(executionContextMock2.rules()).andReturn(executionContext2).anyTimes();
            replay(executionContextMock2);
            boolean result;
            result = classUnderTest.executionContext().create(executionContextMock2);
            assertFalse(result);
            result = classUnderTest.executionContext().create(_executionContext);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleTrace2 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace2).anyTimes();
            replay(containerMock);
            IModuleTrace _module2 = mock(IModuleTrace.class);
             
            IExecutionContext _executionContext2 = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext2 = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext2.rules()).andReturn(executionContext2).anyTimes();
            replay(_executionContext2);
            expect(executionContext2.get()).andReturn(null).anyTimes();
            replay(executionContext2);
             
            IContextTrace classUnderTest2 = new ContextTrace("kind2", 2, _module2, _executionContext2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyExecutionContextReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            executionContext1 = new ExecutionContextHasRules(executionContextMock1);
            expect(executionContextMock1.rules()).andReturn(executionContext1).anyTimes();
            replay(executionContextMock1);
        
            reset(executionContext);
            expect(executionContext.get()).andReturn(classUnderTest).anyTimes();
            replay(executionContext);
        
            boolean result = classUnderTest.executionContext().destroy(_executionContext);
            assertTrue(result);
            assertThat(classUnderTest.executionContext().get(), is(nullValue()));
            executionContext2 = new ExecutionContextHasRules(executionContextMock2);
            expect(executionContextMock2.rules()).andReturn(executionContext2).anyTimes();
            replay(executionContextMock2);
            result = classUnderTest.executionContext().destroy(executionContextMock2);
            assertFalse(result);
        }
        @Test
        public void testContextTraceCreateConstraintsReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
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
            moduleTrace2 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace2).anyTimes();
            replay(containerMock);
            IModuleTrace _module2 = mock(IModuleTrace.class);
             
            IExecutionContext _executionContext2 = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext2 = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext2.rules()).andReturn(executionContext2).anyTimes();
            replay(_executionContext2);
            expect(executionContext2.get()).andReturn(null).anyTimes();
            replay(executionContext2);
             
            IContextTrace classUnderTest2 = new ContextTrace("kind2", 2, _module2, _executionContext2, containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testContextTraceDestroyConstraintsReference() throws Exception {
            moduleTrace1 = new ModuleTraceHasRuleTraces(containerMock);
            expect(containerMock.ruleTraces()).andReturn(moduleTrace1).anyTimes();
            replay(containerMock);
            IModuleTrace _module = mock(IModuleTrace.class);
        
            IExecutionContext _executionContext = mock(IExecutionContext.class);
            IExecutionContextHasRules executionContext = niceMock(IExecutionContextHasRules.class);
            expect(_executionContext.rules()).andReturn(executionContext).anyTimes();
            replay(_executionContext);
            expect(executionContext.get()).andReturn(null).anyTimes();
            replay(executionContext);
        
            // protected region ContextTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ContextTrace("kind1", 1, _module, _executionContext, containerMock);                    
            // protected region ContextTraceInit end     
            invariantTrace1 = new InvariantTraceHasInvariantContext(constraintsMock1);
            expect(constraintsMock1.invariantContext()).andReturn(invariantTrace1).anyTimes();
            replay(constraintsMock1);
            classUnderTest.constraints().create(constraintsMock1);
        
            reset(executionContext);
            expect(executionContext.get()).andReturn(classUnderTest).anyTimes();
            replay(executionContext);
        
            boolean result = classUnderTest.constraints().destroy(constraintsMock1);
            assertTrue(result);
            assertThat(classUnderTest.constraints().get(), not(hasItem(constraintsMock1)));
            invariantTrace2 = new InvariantTraceHasInvariantContext(constraintsMock2);
            expect(constraintsMock2.invariantContext()).andReturn(invariantTrace2).anyTimes();
            replay(constraintsMock2);
            result = classUnderTest.constraints().destroy(constraintsMock2);
            assertFalse(result);
        }
    }
    
    public static class InvariantTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

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
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock1;
        
        /** Mock the target of the accesses reference. */
        @Mock
        private IAccess accessesMock2;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock1;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock2;
        
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
            assertThat(classUnderTest.invariantContext().get(), is(invariantContextMock1));
            Queue<IInvariantTrace> values = invariantContextMock1.constraints().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreInvariantTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreInvariantTraceAttributes end	    
	    @Test
        public void testInvariantTraceAttributes() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
// protected region InvariantTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region InvariantTraceAttributes end
        }

        
        @Test
        public void testInvariantTraceCreateInvariantContextContainerReferenceConflict() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
            boolean result = classUnderTest.invariantContext().destroy(invariantContextMock1);
            assertTrue(result);
        }
        
        @Test
        public void testInvariantTraceDestroyAndCreateInvariantContextContainerReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
        public void testInvariantTraceCreateGuardReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
        public void testInvariantTraceCreateAccessesReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateParentTraceReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
            boolean result;
            result = classUnderTest.parentTrace().create(parentTraceMock2);
            assertFalse(result);
            result = classUnderTest.parentTrace().create(_parentTrace);
            assertFalse(result);
            // Create a second one
            contextTrace2 = new ContextTraceHasConstraints(invariantContextMock2);
            expect(invariantContextMock2.constraints()).andReturn(contextTrace2).anyTimes();
            replay(invariantContextMock2);
            IInvariantTrace classUnderTest2 = new InvariantTrace("name2", invariantContextMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testInvariantTraceDestroyParentTraceReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
            boolean result = classUnderTest.parentTrace().destroy(_parentTrace);
            assertTrue(result);
            assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
            result = classUnderTest.parentTrace().destroy(parentTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testInvariantTraceCreateCheckReference() throws Exception {
            contextTrace1 = new ContextTraceHasConstraints(invariantContextMock1);
            expect(invariantContextMock1.constraints()).andReturn(contextTrace1).anyTimes();
            replay(invariantContextMock1);
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
            // protected region InvariantTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new InvariantTrace("name1", invariantContextMock1);                    
            // protected region InvariantTraceInit end     
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
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock1;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock2;
        
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
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
            assertThat(classUnderTest.limits().get(), is(limitsMock1));
            assertThat(limitsMock1.guard().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreGuardTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreGuardTraceAttributes end	    
	    @Test
        public void testGuardTraceAttributes() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
// protected region GuardTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region GuardTraceAttributes end
        }

        
        @Test
        public void testGuardTraceCreateLimitsContainerReferenceConflict() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
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
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
            boolean result = classUnderTest.limits().destroy(limitsMock1);
            assertTrue(result);
        }
        
        @Test
        public void testGuardTraceDestroyAndCreateLimitsContainerReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
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
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
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
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testGuardTraceCreateParentTraceReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
            boolean result;
            result = classUnderTest.parentTrace().create(parentTraceMock2);
            assertFalse(result);
            result = classUnderTest.parentTrace().create(_parentTrace);
            assertFalse(result);
            // Create a second one
            guardedElementTrace2 = new GuardedElementTraceHasGuard(limitsMock2);
            expect(limitsMock2.guard()).andReturn(guardedElementTrace2).anyTimes();
            replay(limitsMock2);
            IGuardTrace classUnderTest2 = new GuardTrace(limitsMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testGuardTraceDestroyParentTraceReference() throws Exception {
            guardedElementTrace1 = new GuardedElementTraceHasGuard(limitsMock1);
            expect(limitsMock1.guard()).andReturn(guardedElementTrace1).anyTimes();
            replay(limitsMock1);
            // protected region GuardTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new GuardTrace(limitsMock1);                    
            // protected region GuardTraceInit end     
            boolean result = classUnderTest.parentTrace().destroy(_parentTrace);
            assertTrue(result);
            assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
            result = classUnderTest.parentTrace().destroy(parentTraceMock2);
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
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock1;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock2;
        
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
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.check().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreCheckTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreCheckTraceAttributes end	    
	    @Test
        public void testCheckTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
// protected region CheckTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region CheckTraceAttributes end
        }

        
        @Test
        public void testCheckTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
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
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testCheckTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
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
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
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
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testCheckTraceCreateParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
            boolean result;
            result = classUnderTest.parentTrace().create(parentTraceMock2);
            assertFalse(result);
            result = classUnderTest.parentTrace().create(_parentTrace);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasCheck(invariantMock2);
            expect(invariantMock2.check()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            ICheckTrace classUnderTest2 = new CheckTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testCheckTraceDestroyParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasCheck(invariantMock1);
            expect(invariantMock1.check()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region CheckTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new CheckTrace(invariantMock1);                    
            // protected region CheckTraceInit end     
            boolean result = classUnderTest.parentTrace().destroy(_parentTrace);
            assertTrue(result);
            assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
            result = classUnderTest.parentTrace().destroy(parentTraceMock2);
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
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock1;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock2;
        
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
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.message().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreMessageTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreMessageTraceAttributes end	    
	    @Test
        public void testMessageTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
// protected region MessageTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region MessageTraceAttributes end
        }

        
        @Test
        public void testMessageTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
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
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testMessageTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
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
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
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
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testMessageTraceCreateParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
            boolean result;
            result = classUnderTest.parentTrace().create(parentTraceMock2);
            assertFalse(result);
            result = classUnderTest.parentTrace().create(_parentTrace);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasMessage(invariantMock2);
            expect(invariantMock2.message()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            IMessageTrace classUnderTest2 = new MessageTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testMessageTraceDestroyParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasMessage(invariantMock1);
            expect(invariantMock1.message()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region MessageTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new MessageTrace(invariantMock1);                    
            // protected region MessageTraceInit end     
            boolean result = classUnderTest.parentTrace().destroy(_parentTrace);
            assertTrue(result);
            assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
            result = classUnderTest.parentTrace().destroy(parentTraceMock2);
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
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock1;
        
        /** Mock the target of the parentTrace reference. */
        @Mock
        private IRuleTrace parentTraceMock2;
        
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
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            assertThat(classUnderTest.invariant().get(), is(invariantMock1));
            assertThat(invariantMock1.satisfies().get(), is(classUnderTest));
	    }
	    
// protected region IgnoreSatisfiesTraceAttributes on begin	    
	    @Ignore
// protected region IgnoreSatisfiesTraceAttributes end	    
	    @Test
        public void testSatisfiesTraceAttributes() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
// protected region SatisfiesTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region SatisfiesTraceAttributes end
        }

        
        @Test
        public void testSatisfiesTraceCreateInvariantContainerReferenceConflict() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
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
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            boolean result = classUnderTest.invariant().destroy(invariantMock1);
            assertTrue(result);
        }
        
        @Test
        public void testSatisfiesTraceDestroyAndCreateInvariantContainerReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
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
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
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
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            classUnderTest.accesses().create(accessesMock1);
            boolean result = classUnderTest.accesses().destroy(accessesMock1);
            assertTrue(result);
            assertThat(classUnderTest.accesses().get(), not(hasItem(accessesMock1)));
            result = classUnderTest.accesses().destroy(accessesMock2);
            assertFalse(result);
        }
        @Test
        public void testSatisfiesTraceCreateParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            boolean result;
            result = classUnderTest.parentTrace().create(parentTraceMock2);
            assertFalse(result);
            result = classUnderTest.parentTrace().create(_parentTrace);
            assertFalse(result);
            // Create a second one
            invariantTrace2 = new InvariantTraceHasSatisfies(invariantMock2);
            expect(invariantMock2.satisfies()).andReturn(invariantTrace2).anyTimes();
            replay(invariantMock2);
            ISatisfiesTrace classUnderTest2 = new SatisfiesTrace(invariantMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testSatisfiesTraceDestroyParentTraceReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            boolean result = classUnderTest.parentTrace().destroy(_parentTrace);
            assertTrue(result);
            assertThat(classUnderTest.parentTrace().get(), is(nullValue()));
            result = classUnderTest.parentTrace().destroy(parentTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testSatisfiesTraceCreateSatisfiedInvariantsReference() throws Exception {
            invariantTrace1 = new InvariantTraceHasSatisfies(invariantMock1);
            expect(invariantMock1.satisfies()).andReturn(invariantTrace1).anyTimes();
            replay(invariantMock1);
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
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
            // protected region SatisfiesTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new SatisfiesTrace(invariantMock1);                    
            // protected region SatisfiesTraceInit end     
            classUnderTest.satisfiedInvariants().create(satisfiedInvariantsMock1);
            boolean result = classUnderTest.satisfiedInvariants().destroy(satisfiedInvariantsMock1);
            assertTrue(result);
            assertThat(classUnderTest.satisfiedInvariants().get(), not(hasItem(satisfiedInvariantsMock1)));
            result = classUnderTest.satisfiedInvariants().destroy(satisfiedInvariantsMock2);
            assertFalse(result);
        }
    }

}