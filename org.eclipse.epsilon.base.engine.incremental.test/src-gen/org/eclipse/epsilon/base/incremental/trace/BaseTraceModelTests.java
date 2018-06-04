package org.eclipse.epsilon.base.incremental.trace;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BaseTraceModelTests.ExecutionContextTests.class,
                     BaseTraceModelTests.ModelElementVariableTests.class,
                     BaseTraceModelTests.ElementAccessTests.class,
                     BaseTraceModelTests.AllInstancesAccessTests.class,
                     BaseTraceModelTests.PropertyAccessTests.class,
                     BaseTraceModelTests.ModelTraceTests.class,
                     BaseTraceModelTests.ModelTypeTraceTests.class,
                     BaseTraceModelTests.ModelElementTraceTests.class,
                     BaseTraceModelTests.PropertyTraceTests.class})
public class BaseTraceModelTests {

    
    public static class ExecutionContextTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the contextVariables reference. */
        @Mock
        private IModelElementVariable contextVariablesMock1;
        
        /** Mock the target of the contextVariables reference. */
        @Mock
        private IModelElementVariable contextVariablesMock2;
        
        /** Mock the container. */
        @Mock
        private IContextModuleElementTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IContextModuleElementTraceHasExecutionContext contextModuleElementTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IContextModuleElementTraceHasExecutionContext contextModuleElementTrace2;

        private ExecutionContext classUnderTest;

        
        @Test
        public void testExecutionContextInstantiation() throws Exception {
            contextModuleElementTrace1 = new ContextModuleElementTraceHasExecutionContext(containerMock);
            expect(containerMock.executionContext()).andReturn(contextModuleElementTrace1).anyTimes();
            replay(containerMock);
            // protected region ExecutionContextInit on begin
            // Default init parameters can be modified
            classUnderTest = new ExecutionContext();                    
            // protected region ExecutionContextInit end     
// protected region ExecutionContextAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ExecutionContextAttributes end
        }

        @Test
        public void testExecutionContextCreateContextVariablesReference() throws Exception {
            // protected region ExecutionContextInit on begin
            // Default init parameters can be modified
            classUnderTest = new ExecutionContext();                    
            // protected region ExecutionContextInit end     
            boolean result;
            result = classUnderTest.contextVariables().create(contextVariablesMock1);
            assertTrue(result);
            result = classUnderTest.contextVariables().create(contextVariablesMock2);
            assertTrue(result);
            result = classUnderTest.contextVariables().create(contextVariablesMock1);
            assertFalse(result);
            // Create a second one
            IExecutionContext classUnderTest2 = new ExecutionContext();
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testExecutionContextDestroyContextVariablesReference() throws Exception {
            // protected region ExecutionContextInit on begin
            // Default init parameters can be modified
            classUnderTest = new ExecutionContext();                    
            // protected region ExecutionContextInit end     
            classUnderTest.contextVariables().create(contextVariablesMock1);
            boolean result = classUnderTest.contextVariables().destroy(contextVariablesMock1);
            assertTrue(result);
            assertThat(classUnderTest.contextVariables().get(), not(hasItem(contextVariablesMock1)));
            result = classUnderTest.contextVariables().destroy(contextVariablesMock2);
            assertFalse(result);
        }
        @Test
        public void testExecutionContextCreateRulesReference() throws Exception {
            // protected region ExecutionContextInit on begin
            // Default init parameters can be modified
            classUnderTest = new ExecutionContext();                    
            // protected region ExecutionContextInit end     
            ruleTrace1 = new RuleTraceHasExecutionContext(rulesMock1);
            expect(rulesMock1.executionContext()).andReturn(ruleTrace1).anyTimes();
            replay(rulesMock1);
            ruleTrace2 = new RuleTraceHasExecutionContext(rulesMock2);
            expect(rulesMock2.executionContext()).andReturn(ruleTrace2).anyTimes();
            replay(rulesMock2);
            boolean result;
            result = classUnderTest.rules().create(rulesMock1);
            assertTrue(result);
            result = classUnderTest.rules().create(rulesMock2);
            assertTrue(result);
            result = classUnderTest.rules().create(rulesMock1);
            assertFalse(result);
            // Create a second one
            IExecutionContext classUnderTest2 = new ExecutionContext();
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testExecutionContextDestroyRulesReference() throws Exception {
            // protected region ExecutionContextInit on begin
            // Default init parameters can be modified
            classUnderTest = new ExecutionContext();                    
            // protected region ExecutionContextInit end     
            classUnderTest.contextVariables().create(contextVariablesMock1);
            boolean result = classUnderTest.contextVariables().destroy(contextVariablesMock1);
            assertTrue(result);
            assertThat(classUnderTest.contextVariables().get(), not(hasItem(contextVariablesMock1)));
            result = classUnderTest.contextVariables().destroy(contextVariablesMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelElementVariableTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the value reference. */
        @Mock
        private IModelElementTrace valueMock1;
        
        /** Mock the target of the value reference. */
        @Mock
        private IModelElementTrace valueMock2;
        
        /** Mock the container. */
        @Mock
        private IExecutionContext containerMock;
        
        /** Allow the container mock to populate the reference */
        private IExecutionContextHasContextVariables executionContext1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IExecutionContextHasContextVariables executionContext2;

        private ModelElementVariable classUnderTest;

        
        @Test
        public void testModelElementVariableInstantiation() throws Exception {
            executionContext1 = new ExecutionContextHasContextVariables(containerMock);
            expect(containerMock.contextVariables()).andReturn(executionContext1).anyTimes();
            replay(containerMock);
            IModelElementTrace _value = mock(IModelElementTrace.class);
        
            // protected region ModelElementVariableInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementVariable("name1", _value);                    
            // protected region ModelElementVariableInit end     
// protected region ModelElementVariableAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelElementVariableAttributes end
        }

        @Test
        public void testModelElementVariableCreateValueReference() throws Exception {
            IModelElementTrace _value = mock(IModelElementTrace.class);
        
            // protected region ModelElementVariableInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementVariable("name1", _value);                    
            // protected region ModelElementVariableInit end     
            boolean result;
            result = classUnderTest.value().create(valueMock2);
            assertFalse(result);
            result = classUnderTest.value().create(_value);
            assertFalse(result);
            // Create a second one
            IModelElementTrace _value2 = mock(IModelElementTrace.class);
             
            IModelElementVariable classUnderTest2 = new ModelElementVariable("name2", _value2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelElementVariableDestroyValueReference() throws Exception {
            IModelElementTrace _value = mock(IModelElementTrace.class);
        
            // protected region ModelElementVariableInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementVariable("name1", _value);                    
            // protected region ModelElementVariableInit end     
        
            boolean result = classUnderTest.value().destroy(_value);
            assertTrue(result);
            assertThat(classUnderTest.value().get(), is(nullValue()));
            result = classUnderTest.value().destroy(valueMock2);
            assertFalse(result);
        }
    }
    
    public static class ElementAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock1;
        
        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace2;
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock1;
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace2;

        private ElementAccess classUnderTest;

        
        @Test
        public void testElementAccessInstantiation() throws Exception {
            moduleExecutionTrace1 = new ModuleExecutionTraceHasAccesses(containerMock);
            expect(containerMock.accesses()).andReturn(moduleExecutionTrace1).anyTimes();
            replay(containerMock);
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
            IModuleElementTraceHasAccesses moduleElementTrace = niceMock(IModuleElementTraceHasAccesses.class);
            expect(_executionTrace.accesses()).andReturn(moduleElementTrace).anyTimes();
            replay(_executionTrace);
            expect(moduleElementTrace.get()).andReturn(null).anyTimes();
            replay(moduleElementTrace);
        
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region ElementAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new ElementAccess(_executionTrace, _element);                    
            // protected region ElementAccessInit end     
// protected region ElementAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ElementAccessAttributes end
        }

        @Test
        public void testElementAccessCreateExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region ElementAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new ElementAccess(_executionTrace, _element);                    
            // protected region ElementAccessInit end     
            boolean result;
            result = classUnderTest.executionTrace().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.executionTrace().create(_executionTrace);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IModelElementTrace _element2 = mock(IModelElementTrace.class);
             
            IElementAccess classUnderTest2 = new ElementAccess(_executionTrace2, _element2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testElementAccessDestroyExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region ElementAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new ElementAccess(_executionTrace, _element);                    
            // protected region ElementAccessInit end     
        
        
            boolean result = classUnderTest.executionTrace().destroy(_executionTrace);
            assertTrue(result);
            assertThat(classUnderTest.executionTrace().get(), is(nullValue()));
            result = classUnderTest.executionTrace().destroy(executionTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testElementAccessCreateElementReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region ElementAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new ElementAccess(_executionTrace, _element);                    
            // protected region ElementAccessInit end     
            boolean result;
            result = classUnderTest.element().create(elementMock2);
            assertFalse(result);
            result = classUnderTest.element().create(_element);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IModelElementTrace _element2 = mock(IModelElementTrace.class);
             
            IElementAccess classUnderTest2 = new ElementAccess(_executionTrace2, _element2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testElementAccessDestroyElementReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region ElementAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new ElementAccess(_executionTrace, _element);                    
            // protected region ElementAccessInit end     
            // Opposite for executionTrace
            //reset(moduleElementTrace);
            //expect(moduleElementTrace.get()).andReturn(classUnderTest).anyTimes();
            //replay(moduleElementTrace);
        
        
            boolean result = classUnderTest.element().destroy(_element);
            assertTrue(result);
            assertThat(classUnderTest.element().get(), is(nullValue()));
            result = classUnderTest.element().destroy(elementMock2);
            assertFalse(result);
        }
    }
    
    public static class AllInstancesAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock1;
        
        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace2;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace2;

        private AllInstancesAccess classUnderTest;

        
        @Test
        public void testAllInstancesAccessInstantiation() throws Exception {
            moduleExecutionTrace1 = new ModuleExecutionTraceHasAccesses(containerMock);
            expect(containerMock.accesses()).andReturn(moduleExecutionTrace1).anyTimes();
            replay(containerMock);
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
            IModuleElementTraceHasAccesses moduleElementTrace = niceMock(IModuleElementTraceHasAccesses.class);
            expect(_executionTrace.accesses()).andReturn(moduleElementTrace).anyTimes();
            replay(_executionTrace);
            expect(moduleElementTrace.get()).andReturn(null).anyTimes();
            replay(moduleElementTrace);
        
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            // protected region AllInstancesAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new AllInstancesAccess(false, _executionTrace, _type);                    
            // protected region AllInstancesAccessInit end     
// protected region AllInstancesAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region AllInstancesAccessAttributes end
        }

        @Test
        public void testAllInstancesAccessCreateExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            // protected region AllInstancesAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new AllInstancesAccess(false, _executionTrace, _type);                    
            // protected region AllInstancesAccessInit end     
            boolean result;
            result = classUnderTest.executionTrace().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.executionTrace().create(_executionTrace);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IModelTypeTrace _type2 = mock(IModelTypeTrace.class);
             
            IAllInstancesAccess classUnderTest2 = new AllInstancesAccess(false, _executionTrace2, _type2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testAllInstancesAccessDestroyExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            // protected region AllInstancesAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new AllInstancesAccess(false, _executionTrace, _type);                    
            // protected region AllInstancesAccessInit end     
        
        
            boolean result = classUnderTest.executionTrace().destroy(_executionTrace);
            assertTrue(result);
            assertThat(classUnderTest.executionTrace().get(), is(nullValue()));
            result = classUnderTest.executionTrace().destroy(executionTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testAllInstancesAccessCreateTypeReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            // protected region AllInstancesAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new AllInstancesAccess(false, _executionTrace, _type);                    
            // protected region AllInstancesAccessInit end     
            boolean result;
            result = classUnderTest.type().create(typeMock2);
            assertFalse(result);
            result = classUnderTest.type().create(_type);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IModelTypeTrace _type2 = mock(IModelTypeTrace.class);
             
            IAllInstancesAccess classUnderTest2 = new AllInstancesAccess(false, _executionTrace2, _type2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testAllInstancesAccessDestroyTypeReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            // protected region AllInstancesAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new AllInstancesAccess(false, _executionTrace, _type);                    
            // protected region AllInstancesAccessInit end     
            // Opposite for executionTrace
            //reset(moduleElementTrace);
            //expect(moduleElementTrace.get()).andReturn(classUnderTest).anyTimes();
            //replay(moduleElementTrace);
        
        
            boolean result = classUnderTest.type().destroy(_type);
            assertTrue(result);
            assertThat(classUnderTest.type().get(), is(nullValue()));
            result = classUnderTest.type().destroy(typeMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock1;
        
        /** Mock the target of the executionTrace reference. */
        @Mock
        private IModuleElementTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModuleElementTraceHasAccesses moduleElementTrace2;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionTraceHasAccesses moduleExecutionTrace2;

        private PropertyAccess classUnderTest;

        
        @Test
        public void testPropertyAccessInstantiation() throws Exception {
            moduleExecutionTrace1 = new ModuleExecutionTraceHasAccesses(containerMock);
            expect(containerMock.accesses()).andReturn(moduleExecutionTrace1).anyTimes();
            replay(containerMock);
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
            IModuleElementTraceHasAccesses moduleElementTrace = niceMock(IModuleElementTraceHasAccesses.class);
            expect(_executionTrace.accesses()).andReturn(moduleElementTrace).anyTimes();
            replay(_executionTrace);
            expect(moduleElementTrace.get()).andReturn(null).anyTimes();
            replay(moduleElementTrace);
        
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            // protected region PropertyAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyAccess(_executionTrace, _property);                    
            // protected region PropertyAccessInit end     
// protected region PropertyAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyAccessAttributes end
        }

        @Test
        public void testPropertyAccessCreateExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            // protected region PropertyAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyAccess(_executionTrace, _property);                    
            // protected region PropertyAccessInit end     
            boolean result;
            result = classUnderTest.executionTrace().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.executionTrace().create(_executionTrace);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IPropertyTrace _property2 = mock(IPropertyTrace.class);
             
            IPropertyAccess classUnderTest2 = new PropertyAccess(_executionTrace2, _property2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testPropertyAccessDestroyExecutionTraceReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            // protected region PropertyAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyAccess(_executionTrace, _property);                    
            // protected region PropertyAccessInit end     
        
        
            boolean result = classUnderTest.executionTrace().destroy(_executionTrace);
            assertTrue(result);
            assertThat(classUnderTest.executionTrace().get(), is(nullValue()));
            result = classUnderTest.executionTrace().destroy(executionTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testPropertyAccessCreatePropertyReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            // protected region PropertyAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyAccess(_executionTrace, _property);                    
            // protected region PropertyAccessInit end     
            boolean result;
            result = classUnderTest.property().create(propertyMock2);
            assertFalse(result);
            result = classUnderTest.property().create(_property);
            assertFalse(result);
            // Create a second one
            IModuleElementTrace _executionTrace2 = mock(IModuleElementTrace.class);
             
            IPropertyTrace _property2 = mock(IPropertyTrace.class);
             
            IPropertyAccess classUnderTest2 = new PropertyAccess(_executionTrace2, _property2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testPropertyAccessDestroyPropertyReference() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            // protected region PropertyAccessInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyAccess(_executionTrace, _property);                    
            // protected region PropertyAccessInit end     
            // Opposite for executionTrace
            //reset(moduleElementTrace);
            //expect(moduleElementTrace.get()).andReturn(classUnderTest).anyTimes();
            //replay(moduleElementTrace);
        
        
            boolean result = classUnderTest.property().destroy(_property);
            assertTrue(result);
            assertThat(classUnderTest.property().get(), is(nullValue()));
            result = classUnderTest.property().destroy(propertyMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the elements reference. */
        @Mock
        private IModelElementTrace elementsMock1;
        
        /** Mock the target of the elements reference. */
        @Mock
        private IModelElementTrace elementsMock2;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace typesMock1;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace typesMock2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionTraceHasModels moduleExecutionTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionTraceHasModels moduleExecutionTrace2;

        private ModelTrace classUnderTest;

        
        @Test
        public void testModelTraceInstantiation() throws Exception {
            moduleExecutionTrace1 = new ModuleExecutionTraceHasModels(containerMock);
            expect(containerMock.models()).andReturn(moduleExecutionTrace1).anyTimes();
            replay(containerMock);
            // protected region ModelTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelTrace("name1");                    
            // protected region ModelTraceInit end     
            classUnderTest.types().create(typesMock1);
            boolean result = classUnderTest.types().destroy(typesMock1);
            assertTrue(result);
            assertThat(classUnderTest.types().get(), not(hasItem(typesMock1)));
            result = classUnderTest.types().destroy(typesMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelTypeTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the container. */
        @Mock
        private IModelTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModelTraceHasTypes modelTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModelTraceHasTypes modelTrace2;

        private ModelTypeTrace classUnderTest;

        
        @Test
        public void testModelTypeTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(containerMock);
            expect(containerMock.types()).andReturn(modelTrace1).anyTimes();
            replay(containerMock);
            // protected region ModelTypeTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelTypeTrace("name1", _model);                    
            // protected region ModelTypeTraceInit end     
// protected region ModelTypeTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTypeTraceAttributes end
        }

        @Test
        public void testModelTypeTraceCreateModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            // protected region ModelTypeTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelTypeTrace("name1", _model);                    
            // protected region ModelTypeTraceInit end     
            boolean result;
            result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            result = classUnderTest.model().create(_model);
            assertFalse(result);
            // Create a second one
            IModelTrace _model2 = mock(IModelTrace.class);
             
            IModelTypeTrace classUnderTest2 = new ModelTypeTrace("name2", _model2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelTypeTraceDestroyModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            // protected region ModelTypeTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelTypeTrace("name1", _model);                    
            // protected region ModelTypeTraceInit end     
// protected region ModelTypeTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTypeTraceAttributes end
        }

    }
    
    public static class ModelElementTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertiesMock1;
        
        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertiesMock2;
        
        /** Mock the container. */
        @Mock
        private IModelTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModelTraceHasElements modelTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModelTraceHasElements modelTrace2;

        private ModelElementTrace classUnderTest;

        
        @Test
        public void testModelElementTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasElements(containerMock);
            expect(containerMock.elements()).andReturn(modelTrace1).anyTimes();
            replay(containerMock);
            // protected region ModelElementTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);                    
            // protected region ModelElementTraceInit end     
// protected region ModelElementTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelElementTraceAttributes end
        }

        @Test
        public void testModelElementTraceCreateModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            // protected region ModelElementTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);                    
            // protected region ModelElementTraceInit end     
            boolean result;
            result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            result = classUnderTest.model().create(_model);
            assertFalse(result);
            // Create a second one
            IModelTrace _model2 = mock(IModelTrace.class);
             
            IModelElementTrace classUnderTest2 = new ModelElementTrace("url://path/in/model/to/uri/2", _model2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelElementTraceDestroyModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            // protected region ModelElementTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);                    
            // protected region ModelElementTraceInit end     
            classUnderTest.properties().create(propertiesMock1);
            boolean result = classUnderTest.properties().destroy(propertiesMock1);
            assertTrue(result);
            assertThat(classUnderTest.properties().get(), not(hasItem(propertiesMock1)));
            result = classUnderTest.properties().destroy(propertiesMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the container. */
        @Mock
        private IModelElementTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModelElementTraceHasProperties modelElementTrace1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModelElementTraceHasProperties modelElementTrace2;

        private PropertyTrace classUnderTest;

        
        @Test
        public void testPropertyTraceInstantiation() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(containerMock);
            expect(containerMock.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(containerMock);
            // protected region PropertyTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyTrace("name1", _element);                    
            // protected region PropertyTraceInit end     
// protected region PropertyTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyTraceAttributes end
        }

        @Test
        public void testPropertyTraceCreateElementReference() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region PropertyTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyTrace("name1", _element);                    
            // protected region PropertyTraceInit end     
            boolean result;
            result = classUnderTest.element().create(elementMock2);
            assertFalse(result);
            result = classUnderTest.element().create(_element);
            assertFalse(result);
            // Create a second one
            IModelElementTrace _element2 = mock(IModelElementTrace.class);
             
            IPropertyTrace classUnderTest2 = new PropertyTrace("name2", _element2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testPropertyTraceDestroyElementReference() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            // protected region PropertyTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new PropertyTrace("name1", _element);                    
            // protected region PropertyTraceInit end     
// protected region PropertyTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyTraceAttributes end
        }

    }

}