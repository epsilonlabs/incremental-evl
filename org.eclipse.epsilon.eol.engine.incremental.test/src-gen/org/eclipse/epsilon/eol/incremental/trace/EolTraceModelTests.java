package org.eclipse.epsilon.eol.incremental.trace;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.eol.incremental.trace.impl.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EolTraceModelTests.AllInstancesAccessTests.class,
                     EolTraceModelTests.ElementAccessTests.class,
                     EolTraceModelTests.PropertyAccessTests.class,
                     EolTraceModelTests.ModelTraceTests.class,
                     EolTraceModelTests.ModelTypeTraceTests.class,
                     EolTraceModelTests.ModelElementTraceTests.class,
                     EolTraceModelTests.PropertyTraceTests.class})
public class EolTraceModelTests {

    
    public static class AllInstancesAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace modelTypeTraceMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace modelTypeTraceMock2;
        
        private AllInstancesAccess classUnderTest;
        
        @Test
        public void testAllInstancesAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            assertThat(classUnderTest.execution().get(), is(executionTraceMock1));
            Queue<IAccess> values = executionTraceMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreAllInstancesAccessAttributes on begin
	    @Ignore
// protected region IgnoreAllInstancesAccessAttributes end	    
	    @Test
        public void testAllInstancesAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
// protected region AllInstancesAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region AllInstancesAccessAttributes end
        }

        
        @Test
        public void testAllInstancesAccessCreateExecutionConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
        
            boolean result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyAndCreateExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
  
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessCreateModelTypeTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            boolean result;
            result = classUnderTest.type().create(modelTypeTraceMock2);
            assertFalse(result);
            result = classUnderTest.type().create(_type);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyModelTypeTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionTraceMock1);
            boolean result = classUnderTest.type().destroy(_type);
            assertTrue(result);
            assertThat(classUnderTest.type().get(), is(nullValue()));
            result = classUnderTest.type().destroy(modelTypeTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class ElementAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock1;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock2;
        
        private ElementAccess classUnderTest;
        
        @Test
        public void testElementAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            assertThat(classUnderTest.execution().get(), is(executionTraceMock1));
            Queue<IAccess> values = executionTraceMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreElementAccessAttributes on begin
	    @Ignore
// protected region IgnoreElementAccessAttributes end	    
	    @Test
        public void testElementAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
// protected region ElementAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ElementAccessAttributes end
        }

        
        @Test
        public void testElementAccessCreateExecutionConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
        
            boolean result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testElementAccessDestroyExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testElementAccessDestroyAndCreateExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
  
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testElementAccessCreateModelElementTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            boolean result;
            result = classUnderTest.modelElement().create(modelElementTraceMock2);
            assertFalse(result);
            result = classUnderTest.modelElement().create(_modelElement);
            assertFalse(result);
        }
        
        @Test
        public void testElementAccessDestroyModelElementTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionTraceMock1);
            boolean result = classUnderTest.modelElement().destroy(_modelElement);
            assertTrue(result);
            assertThat(classUnderTest.modelElement().get(), is(nullValue()));
            result = classUnderTest.modelElement().destroy(modelElementTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock1;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock2;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyTraceMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyTraceMock2;
        
        private PropertyAccess classUnderTest;
        
        @Test
        public void testPropertyAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            assertThat(classUnderTest.execution().get(), is(executionTraceMock1));
            Queue<IAccess> values = executionTraceMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyAccessAttributes on begin
	    @Ignore
// protected region IgnorePropertyAccessAttributes end	    
	    @Test
        public void testPropertyAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
// protected region PropertyAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyAccessAttributes end
        }

        
        @Test
        public void testPropertyAccessCreateExecutionConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
        
            boolean result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessDestroyExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyAccessDestroyAndCreateExecution() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionTraceMock2);
            expect(executionTraceMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionTraceMock2);
  
            boolean result = classUnderTest.execution().destroy(executionTraceMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionTraceMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessCreateModelElementTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            boolean result;
            result = classUnderTest.modelElement().create(modelElementTraceMock2);
            assertFalse(result);
            result = classUnderTest.modelElement().create(_modelElement);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessDestroyModelElementTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            boolean result = classUnderTest.modelElement().destroy(_modelElement);
            assertTrue(result);
            assertThat(classUnderTest.modelElement().get(), is(nullValue()));
            result = classUnderTest.modelElement().destroy(modelElementTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testPropertyAccessCreatePropertyTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            boolean result;
            result = classUnderTest.property().create(propertyTraceMock2);
            assertFalse(result);
            result = classUnderTest.property().create(_property);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessDestroyPropertyTrace() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionTraceMock1);
            expect(executionTraceMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionTraceMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_modelElement, _property, executionTraceMock1);
            boolean result = classUnderTest.property().destroy(_property);
            assertTrue(result);
            assertThat(classUnderTest.property().get(), is(nullValue()));
            result = classUnderTest.property().destroy(propertyTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the elements reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock1;
        
        /** Mock the target of the elements reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasModel modelElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasModel modelElementTrace2;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace modelTypeTraceMock1;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace modelTypeTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelTypeTraceHasModel modelTypeTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTypeTraceHasModel modelTypeTrace2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecution containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionHasModel moduleExecution1;

        private ModelTrace classUnderTest;
        
        @Test
        public void testModelTraceInstantiation() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            Queue<IModelTrace> values = containerMock.model().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTraceAttributes end	    
	    @Test
        public void testModelTraceAttributes() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
// protected region ModelTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTraceAttributes end
        }

        @Test
        public void testModelTraceCreateModelElementTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelElementTrace1 = new ModelElementTraceHasModel(modelElementTraceMock1);
            expect(modelElementTraceMock1.model()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            modelElementTrace2 = new ModelElementTraceHasModel(modelElementTraceMock2);
            expect(modelElementTraceMock2.model()).andReturn(modelElementTrace2).anyTimes();
            replay(modelElementTraceMock2);
            boolean result;
            result = classUnderTest.elements().create(modelElementTraceMock1);
            assertTrue(result);
            result = classUnderTest.elements().create(modelElementTraceMock2);
            assertTrue(result);
            result = classUnderTest.elements().create(modelElementTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelTraceDestroyModelElementTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelElementTrace1 = new ModelElementTraceHasModel(modelElementTraceMock1);
            expect(modelElementTraceMock1.model()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest.elements().create(modelElementTraceMock1);
            boolean result = classUnderTest.elements().destroy(modelElementTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.elements().get(), not(hasItem(modelElementTraceMock1)));
            modelElementTrace2 = new ModelElementTraceHasModel(modelElementTraceMock2);
            expect(modelElementTraceMock2.model()).andReturn(modelElementTrace2).anyTimes();
            replay(modelElementTraceMock2);
            result = classUnderTest.elements().destroy(modelElementTraceMock2);
            assertFalse(result);
        }
        @Test
        public void testModelTraceCreateModelTypeTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelTypeTrace1 = new ModelTypeTraceHasModel(modelTypeTraceMock1);
            expect(modelTypeTraceMock1.model()).andReturn(modelTypeTrace1).anyTimes();
            replay(modelTypeTraceMock1);
            modelTypeTrace2 = new ModelTypeTraceHasModel(modelTypeTraceMock2);
            expect(modelTypeTraceMock2.model()).andReturn(modelTypeTrace2).anyTimes();
            replay(modelTypeTraceMock2);
            boolean result;
            result = classUnderTest.types().create(modelTypeTraceMock1);
            assertTrue(result);
            result = classUnderTest.types().create(modelTypeTraceMock2);
            assertTrue(result);
            result = classUnderTest.types().create(modelTypeTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelTraceDestroyModelTypeTrace() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelTypeTrace1 = new ModelTypeTraceHasModel(modelTypeTraceMock1);
            expect(modelTypeTraceMock1.model()).andReturn(modelTypeTrace1).anyTimes();
            replay(modelTypeTraceMock1);
            classUnderTest.types().create(modelTypeTraceMock1);
            boolean result = classUnderTest.types().destroy(modelTypeTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.types().get(), not(hasItem(modelTypeTraceMock1)));
            modelTypeTrace2 = new ModelTypeTraceHasModel(modelTypeTraceMock2);
            expect(modelTypeTraceMock2.model()).andReturn(modelTypeTrace2).anyTimes();
            replay(modelTypeTraceMock2);
            result = classUnderTest.types().destroy(modelTypeTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelTypeTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasTypes modelTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasTypes modelTrace2;
        
        private ModelTypeTrace classUnderTest;
        
        @Test
        public void testModelTypeTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelTraceMock1);
            expect(modelTraceMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelTypeTrace("name", modelTraceMock1);
            assertThat(classUnderTest.model().get(), is(modelTraceMock1));
            Queue<IModelTypeTrace> values = modelTraceMock1.types().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelTypeTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTypeTraceAttributes end	    
	    @Test
        public void testModelTypeTraceAttributes() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelTraceMock1);
            expect(modelTraceMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelTypeTrace("name", modelTraceMock1);
// protected region ModelTypeTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTypeTraceAttributes end
        }

        
        @Test
        public void testModelTypeTraceCreateModelConflict() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelTraceMock1);
            expect(modelTraceMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelTypeTrace("name", modelTraceMock1);
            modelTrace2 = new ModelTraceHasTypes(modelTraceMock2);
            expect(modelTraceMock2.types()).andReturn(modelTrace2).anyTimes();
            replay(modelTraceMock2);
        
            boolean result = classUnderTest.model().create(modelTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testModelTypeTraceDestroyModel() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelTraceMock1);
            expect(modelTraceMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelTypeTrace("name", modelTraceMock1);
            boolean result = classUnderTest.model().destroy(modelTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelTypeTraceDestroyAndCreateModel() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelTraceMock1);
            expect(modelTraceMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelTypeTrace("name", modelTraceMock1);
            modelTrace2 = new ModelTraceHasTypes(modelTraceMock2);
            expect(modelTraceMock2.types()).andReturn(modelTrace2).anyTimes();
            replay(modelTraceMock2);
  
            boolean result = classUnderTest.model().destroy(modelTraceMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock2);
            assertFalse(result);
            result = classUnderTest.model().create(modelTraceMock1);
            assertFalse(result);
        }
        
    }
    
    public static class ModelElementTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private IModelTrace modelTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasElements modelTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasElements modelTrace2;
        
        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertyTraceMock1;
        
        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertyTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IPropertyTraceHasElement propertyTrace1;
        
        /** Allow the target mock to populate the reference */
        private IPropertyTraceHasElement propertyTrace2;
        
        private ModelElementTrace classUnderTest;
        
        @Test
        public void testModelElementTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            assertThat(classUnderTest.model().get(), is(modelTraceMock1));
            Queue<IModelElementTrace> values = modelTraceMock1.elements().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelElementTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelElementTraceAttributes end	    
	    @Test
        public void testModelElementTraceAttributes() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
// protected region ModelElementTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelElementTraceAttributes end
        }

        
        @Test
        public void testModelElementTraceCreateModelConflict() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            modelTrace2 = new ModelTraceHasElements(modelTraceMock2);
            expect(modelTraceMock2.elements()).andReturn(modelTrace2).anyTimes();
            replay(modelTraceMock2);
        
            boolean result = classUnderTest.model().create(modelTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementTraceDestroyModel() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            boolean result = classUnderTest.model().destroy(modelTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelElementTraceDestroyAndCreateModel() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            modelTrace2 = new ModelTraceHasElements(modelTraceMock2);
            expect(modelTraceMock2.elements()).andReturn(modelTrace2).anyTimes();
            replay(modelTraceMock2);
  
            boolean result = classUnderTest.model().destroy(modelTraceMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelTraceMock2);
            assertFalse(result);
            result = classUnderTest.model().create(modelTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementTraceCreatePropertyTrace() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            propertyTrace1 = new PropertyTraceHasElement(propertyTraceMock1);
            expect(propertyTraceMock1.element()).andReturn(propertyTrace1).anyTimes();
            replay(propertyTraceMock1);
            propertyTrace2 = new PropertyTraceHasElement(propertyTraceMock2);
            expect(propertyTraceMock2.element()).andReturn(propertyTrace2).anyTimes();
            replay(propertyTraceMock2);
            boolean result;
            result = classUnderTest.properties().create(propertyTraceMock1);
            assertTrue(result);
            result = classUnderTest.properties().create(propertyTraceMock2);
            assertTrue(result);
            result = classUnderTest.properties().create(propertyTraceMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementTraceDestroyPropertyTrace() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelTraceMock1);
            expect(modelTraceMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelTraceMock1);
            classUnderTest = new ModelElementTrace("uri", modelTraceMock1);
            propertyTrace1 = new PropertyTraceHasElement(propertyTraceMock1);
            expect(propertyTraceMock1.element()).andReturn(propertyTrace1).anyTimes();
            replay(propertyTraceMock1);
            classUnderTest.properties().create(propertyTraceMock1);
            boolean result = classUnderTest.properties().destroy(propertyTraceMock1);
            assertTrue(result);
            assertThat(classUnderTest.properties().get(), not(hasItem(propertyTraceMock1)));
            propertyTrace2 = new PropertyTraceHasElement(propertyTraceMock2);
            expect(propertyTraceMock2.element()).andReturn(propertyTrace2).anyTimes();
            replay(propertyTraceMock2);
            result = classUnderTest.properties().destroy(propertyTraceMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyTraceTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock1;
        
        /** Mock the target of the element reference. */
        @Mock
        private IModelElementTrace modelElementTraceMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasProperties modelElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasProperties modelElementTrace2;
        
        private PropertyTrace classUnderTest;
        
        @Test
        public void testPropertyTraceInstantiation() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(modelElementTraceMock1);
            expect(modelElementTraceMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest = new PropertyTrace("name", modelElementTraceMock1);
            assertThat(classUnderTest.element().get(), is(modelElementTraceMock1));
            Queue<IPropertyTrace> values = modelElementTraceMock1.properties().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyTraceAttributes on begin
	    @Ignore
// protected region IgnorePropertyTraceAttributes end	    
	    @Test
        public void testPropertyTraceAttributes() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(modelElementTraceMock1);
            expect(modelElementTraceMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest = new PropertyTrace("name", modelElementTraceMock1);
// protected region PropertyTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyTraceAttributes end
        }

        
        @Test
        public void testPropertyTraceCreateElementConflict() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(modelElementTraceMock1);
            expect(modelElementTraceMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest = new PropertyTrace("name", modelElementTraceMock1);
            modelElementTrace2 = new ModelElementTraceHasProperties(modelElementTraceMock2);
            expect(modelElementTraceMock2.properties()).andReturn(modelElementTrace2).anyTimes();
            replay(modelElementTraceMock2);
        
            boolean result = classUnderTest.element().create(modelElementTraceMock2);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyTraceDestroyElement() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(modelElementTraceMock1);
            expect(modelElementTraceMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest = new PropertyTrace("name", modelElementTraceMock1);
            boolean result = classUnderTest.element().destroy(modelElementTraceMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyTraceDestroyAndCreateElement() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(modelElementTraceMock1);
            expect(modelElementTraceMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(modelElementTraceMock1);
            classUnderTest = new PropertyTrace("name", modelElementTraceMock1);
            modelElementTrace2 = new ModelElementTraceHasProperties(modelElementTraceMock2);
            expect(modelElementTraceMock2.properties()).andReturn(modelElementTrace2).anyTimes();
            replay(modelElementTraceMock2);
  
            boolean result = classUnderTest.element().destroy(modelElementTraceMock1);
            assertTrue(result);
            result = classUnderTest.element().create(modelElementTraceMock2);
            assertTrue(result);
            result = classUnderTest.element().create(modelElementTraceMock2);
            assertFalse(result);
            result = classUnderTest.element().create(modelElementTraceMock1);
            assertFalse(result);
        }
        
    }

}