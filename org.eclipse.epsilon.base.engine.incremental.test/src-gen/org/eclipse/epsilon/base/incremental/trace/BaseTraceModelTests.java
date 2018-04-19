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
        
        /** Mock the target of the rules reference. */
        @Mock
        private IRuleTrace rulesMock1;
        
        /** Mock the target of the rules reference. */
        @Mock
        private IRuleTrace rulesMock2;
        
        /** Allow the target mock to populate the reference */
        private IRuleTraceHasExecutionContext ruleTrace1;
        
        /** Allow the target mock to populate the reference */
        private IRuleTraceHasExecutionContext ruleTrace2;
        
        private ExecutionContext classUnderTest;

	    
// protected region IgnoreExecutionContextAttributes on begin
	    @Ignore
// protected region IgnoreExecutionContextAttributes end	    
	    @Test
        public void testExecutionContextAttributes() throws Exception {
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
            ruleTrace1 = new RuleTraceHasExecutionContext(rulesMock1);
            expect(rulesMock1.executionContext()).andReturn(ruleTrace1).anyTimes();
            replay(rulesMock1);
            classUnderTest.rules().create(rulesMock1);
            boolean result = classUnderTest.rules().destroy(rulesMock1);
            assertTrue(result);
            assertThat(classUnderTest.rules().get(), not(hasItem(rulesMock1)));
            ruleTrace2 = new RuleTraceHasExecutionContext(rulesMock2);
            expect(rulesMock2.executionContext()).andReturn(ruleTrace2).anyTimes();
            replay(rulesMock2);
            result = classUnderTest.rules().destroy(rulesMock2);
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
        
        private ModelElementVariable classUnderTest;

	    
// protected region IgnoreModelElementVariableAttributes on begin
	    @Ignore
// protected region IgnoreModelElementVariableAttributes end	    
	    @Test
        public void testModelElementVariableAttributes() throws Exception {
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
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock1;
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock2;
        
        private ElementAccess classUnderTest;

	    
// protected region IgnoreElementAccessAttributes on begin
	    @Ignore
// protected region IgnoreElementAccessAttributes end	    
	    @Test
        public void testElementAccessAttributes() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
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
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock2;
        
        private AllInstancesAccess classUnderTest;

	    
// protected region IgnoreAllInstancesAccessAttributes on begin
	    @Ignore
// protected region IgnoreAllInstancesAccessAttributes end	    
	    @Test
        public void testAllInstancesAccessAttributes() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
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
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock2;
        
        private PropertyAccess classUnderTest;

	    
// protected region IgnorePropertyAccessAttributes on begin
	    @Ignore
// protected region IgnorePropertyAccessAttributes end	    
	    @Test
        public void testPropertyAccessAttributes() throws Exception {
            IModuleElementTrace _executionTrace = mock(IModuleElementTrace.class);
        
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

        private ModelTrace classUnderTest;

	    
// protected region IgnoreModelTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTraceAttributes end	    
	    @Test
        public void testModelTraceAttributes() throws Exception {
            // protected region ModelTraceInit on begin
            // Default init parameters can be modified
            classUnderTest = new ModelTrace("name1");                    
            // protected region ModelTraceInit end     
// protected region ModelTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTraceAttributes end
        }

    }
    
    public static class ModelTypeTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock2;
        
        private ModelTypeTrace classUnderTest;

	    
// protected region IgnoreModelTypeTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTypeTraceAttributes end	    
	    @Test
        public void testModelTypeTraceAttributes() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
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
        
            boolean result = classUnderTest.model().destroy(_model);
            assertTrue(result);
            assertThat(classUnderTest.model().get(), is(nullValue()));
            result = classUnderTest.model().destroy(modelMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelElementTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelMock2;
        
        private ModelElementTrace classUnderTest;

	    
// protected region IgnoreModelElementTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelElementTraceAttributes end	    
	    @Test
        public void testModelElementTraceAttributes() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
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
        
            boolean result = classUnderTest.model().destroy(_model);
            assertTrue(result);
            assertThat(classUnderTest.model().get(), is(nullValue()));
            result = classUnderTest.model().destroy(modelMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock1;
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace elementMock2;
        
        private PropertyTrace classUnderTest;

	    
// protected region IgnorePropertyTraceAttributes on begin
	    @Ignore
// protected region IgnorePropertyTraceAttributes end	    
	    @Test
        public void testPropertyTraceAttributes() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
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
        
            boolean result = classUnderTest.element().destroy(_element);
            assertTrue(result);
            assertThat(classUnderTest.element().get(), is(nullValue()));
            result = classUnderTest.element().destroy(elementMock2);
            assertFalse(result);
        }
    }

}