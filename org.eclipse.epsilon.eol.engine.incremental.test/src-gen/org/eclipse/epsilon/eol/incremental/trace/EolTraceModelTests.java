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
        private IExecutionTrace executionMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock2;
        
        private AllInstancesAccess classUnderTest;
        
        @Test
        public void testAllInstancesAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            assertThat(classUnderTest.execution().get(), is(executionMock1));
            Queue<IAccess> values = executionMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreAllInstancesAccessAttributes on begin
	    //@Ignore
// protected region IgnoreAllInstancesAccessAttributes end	    
	    @Test
        public void testAllInstancesAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
// protected region AllInstancesAccessAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            boolean kind = false;
            classUnderTest.setOfKind(kind);
            assertThat(classUnderTest.getOfKind(), is(kind));
            kind = true;
            classUnderTest.setOfKind(kind);
            assertThat(classUnderTest.getOfKind(), is(kind));
// protected region AllInstancesAccessAttributes end
        }

        
        @Test
        public void testAllInstancesAccessCreateExecutionContainerReferenceConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
        
            boolean result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testAllInstancesAccessDestroyExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyAndCreateExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
  
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionMock1);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessCreateTypeReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            boolean result;
            result = classUnderTest.type().create(typeMock2);
            assertFalse(result);
            result = classUnderTest.type().create(_type);
            assertFalse(result);
            // Create a second one
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
            IModelTypeTrace _type2 = mock(IModelTypeTrace.class);
            IAllInstancesAccess classUnderTest2 = new AllInstancesAccess(_type2, executionMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testAllInstancesAccessDestroyTypeReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
            classUnderTest = new AllInstancesAccess(_type, executionMock1);
            boolean result = classUnderTest.type().destroy(_type);
            assertTrue(result);
            assertThat(classUnderTest.type().get(), is(nullValue()));
            result = classUnderTest.type().destroy(typeMock2);
            assertFalse(result);
        }
    }
    
    public static class ElementAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementMock1;
        
        /** Mock the target of the modelElement reference. */
        @Mock
        private IModelElementTrace modelElementMock2;
        
        private ElementAccess classUnderTest;
        
        @Test
        public void testElementAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            assertThat(classUnderTest.execution().get(), is(executionMock1));
            Queue<IAccess> values = executionMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreElementAccessAttributes on begin
	    //@Ignore
// protected region IgnoreElementAccessAttributes end	    
	    @Test
        public void testElementAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
// protected region ElementAccessAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
// protected region ElementAccessAttributes end
        }

        
        @Test
        public void testElementAccessCreateExecutionContainerReferenceConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
        
            boolean result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testElementAccessDestroyExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
        }
        
        @Test
        public void testElementAccessDestroyAndCreateExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
  
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionMock1);
            assertFalse(result);
        }
        
        @Test
        public void testElementAccessCreateModelElementReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            boolean result;
            result = classUnderTest.modelElement().create(modelElementMock2);
            assertFalse(result);
            result = classUnderTest.modelElement().create(_modelElement);
            assertFalse(result);
            // Create a second one
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
            IModelElementTrace _modelElement2 = mock(IModelElementTrace.class);
            IElementAccess classUnderTest2 = new ElementAccess(_modelElement2, executionMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testElementAccessDestroyModelElementReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IModelElementTrace _modelElement = mock(IModelElementTrace.class);
            classUnderTest = new ElementAccess(_modelElement, executionMock1);
            boolean result = classUnderTest.modelElement().destroy(_modelElement);
            assertTrue(result);
            assertThat(classUnderTest.modelElement().get(), is(nullValue()));
            result = classUnderTest.modelElement().destroy(modelElementMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private IExecutionTrace executionMock2;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace1;
        
        /** Allow the target mock to populate the reference */
        private IExecutionTraceHasAccesses executionTrace2;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock2;
        
        private PropertyAccess classUnderTest;
        
        @Test
        public void testPropertyAccessInstantiation() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
            assertThat(classUnderTest.execution().get(), is(executionMock1));
            Queue<IAccess> values = executionMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyAccessAttributes on begin
	    //@Ignore
// protected region IgnorePropertyAccessAttributes end	    
	    @Test
        public void testPropertyAccessAttributes() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
// protected region PropertyAccessAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            String name = "ObjectName";
            classUnderTest.setValue(name);
            assertThat(classUnderTest.getValue(), is(name));                    
// protected region PropertyAccessAttributes end
        }

        
        @Test
        public void testPropertyAccessCreateExecutionContainerReferenceConflict() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
        
            boolean result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testPropertyAccessDestroyExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyAccessDestroyAndCreateExecutionContainerReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
  
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertTrue(result);
            result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
            result = classUnderTest.execution().create(executionMock1);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessCreatePropertyReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
            boolean result;
            result = classUnderTest.property().create(propertyMock2);
            assertFalse(result);
            result = classUnderTest.property().create(_property);
            assertFalse(result);
            // Create a second one
            executionTrace2 = new ExecutionTraceHasAccesses(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionTrace2).anyTimes();
            replay(executionMock2);
            IPropertyTrace _property2 = mock(IPropertyTrace.class);
            IPropertyAccess classUnderTest2 = new PropertyAccess(_property2, executionMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testPropertyAccessDestroyPropertyReference() throws Exception {
            executionTrace1 = new ExecutionTraceHasAccesses(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionTrace1).anyTimes();
            replay(executionMock1);
            IPropertyTrace _property = mock(IPropertyTrace.class);
            classUnderTest = new PropertyAccess(_property, executionMock1);
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
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasModel modelElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasModel modelElementTrace2;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace typesMock1;
        
        /** Mock the target of the types reference. */
        @Mock
        private IModelTypeTrace typesMock2;
        
        /** Allow the target mock to populate the reference */
        private IModelTypeTraceHasModel modelTypeTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTypeTraceHasModel modelTypeTrace2;
        
        /** Mock the container. */
        @Mock
        private IModuleExecution containerMock;
        
        /** Allow the container mock to populate the reference */
        private IModuleExecutionHasModel moduleExecution1;
        
        /** Allow the container mock to populate the reference of second instance*/
        private IModuleExecutionHasModel moduleExecution2;

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
	    //@Ignore
// protected region IgnoreModelTraceAttributes end	    
	    @Test
        public void testModelTraceAttributes() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
// protected region ModelTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            assertThat(classUnderTest.getName(), is("name"));
// protected region ModelTraceAttributes end
        }

        @Test
        public void testModelTraceCreateElementsReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelElementTrace1 = new ModelElementTraceHasModel(elementsMock1);
            expect(elementsMock1.model()).andReturn(modelElementTrace1).anyTimes();
            replay(elementsMock1);
            modelElementTrace2 = new ModelElementTraceHasModel(elementsMock2);
            expect(elementsMock2.model()).andReturn(modelElementTrace2).anyTimes();
            replay(elementsMock2);
            boolean result;
            result = classUnderTest.elements().create(elementsMock1);
            assertTrue(result);
            result = classUnderTest.elements().create(elementsMock2);
            assertTrue(result);
            result = classUnderTest.elements().create(elementsMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelTrace classUnderTest2 = new ModelTrace("name2", containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelTraceDestroyElementsReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelElementTrace1 = new ModelElementTraceHasModel(elementsMock1);
            expect(elementsMock1.model()).andReturn(modelElementTrace1).anyTimes();
            replay(elementsMock1);
            classUnderTest.elements().create(elementsMock1);
            boolean result = classUnderTest.elements().destroy(elementsMock1);
            assertTrue(result);
            assertThat(classUnderTest.elements().get(), not(hasItem(elementsMock1)));
            modelElementTrace2 = new ModelElementTraceHasModel(elementsMock2);
            expect(elementsMock2.model()).andReturn(modelElementTrace2).anyTimes();
            replay(elementsMock2);
            result = classUnderTest.elements().destroy(elementsMock2);
            assertFalse(result);
        }
        @Test
        public void testModelTraceCreateTypesReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelTypeTrace1 = new ModelTypeTraceHasModel(typesMock1);
            expect(typesMock1.model()).andReturn(modelTypeTrace1).anyTimes();
            replay(typesMock1);
            modelTypeTrace2 = new ModelTypeTraceHasModel(typesMock2);
            expect(typesMock2.model()).andReturn(modelTypeTrace2).anyTimes();
            replay(typesMock2);
            boolean result;
            result = classUnderTest.types().create(typesMock1);
            assertTrue(result);
            result = classUnderTest.types().create(typesMock2);
            assertTrue(result);
            result = classUnderTest.types().create(typesMock1);
            assertFalse(result);
            // Create a second one
            reset(containerMock);
            moduleExecution2 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution2).anyTimes();
            replay(containerMock);
            IModelTrace classUnderTest2 = new ModelTrace("name2", containerMock);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelTraceDestroyTypesReference() throws Exception {
            moduleExecution1 = new ModuleExecutionHasModel(containerMock);
            expect(containerMock.model()).andReturn(moduleExecution1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelTrace("name", containerMock);
            modelTypeTrace1 = new ModelTypeTraceHasModel(typesMock1);
            expect(typesMock1.model()).andReturn(modelTypeTrace1).anyTimes();
            replay(typesMock1);
            classUnderTest.types().create(typesMock1);
            boolean result = classUnderTest.types().destroy(typesMock1);
            assertTrue(result);
            assertThat(classUnderTest.types().get(), not(hasItem(typesMock1)));
            modelTypeTrace2 = new ModelTypeTraceHasModel(typesMock2);
            expect(typesMock2.model()).andReturn(modelTypeTrace2).anyTimes();
            replay(typesMock2);
            result = classUnderTest.types().destroy(typesMock2);
            assertFalse(result);
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
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasTypes modelTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasTypes modelTrace2;
        
        private ModelTypeTrace classUnderTest;
        
        @Test
        public void testModelTypeTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelMock1);
            expect(modelMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeTrace("name", modelMock1);
            assertThat(classUnderTest.model().get(), is(modelMock1));
            Queue<IModelTypeTrace> values = modelMock1.types().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelTypeTraceAttributes on begin
	    //@Ignore
// protected region IgnoreModelTypeTraceAttributes end	    
	    @Test
        public void testModelTypeTraceAttributes() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelMock1);
            expect(modelMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeTrace("name", modelMock1);
// protected region ModelTypeTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            assertThat(classUnderTest.getName(), is("name"));
// protected region ModelTypeTraceAttributes end
        }

        
        @Test
        public void testModelTypeTraceCreateModelContainerReferenceConflict() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelMock1);
            expect(modelMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeTrace("name", modelMock1);
            modelTrace2 = new ModelTraceHasTypes(modelMock2);
            expect(modelMock2.types()).andReturn(modelTrace2).anyTimes();
            replay(modelMock2);
        
            boolean result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testModelTypeTraceDestroyModelContainerReference() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelMock1);
            expect(modelMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeTrace("name", modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelTypeTraceDestroyAndCreateModelContainerReference() throws Exception {
            modelTrace1 = new ModelTraceHasTypes(modelMock1);
            expect(modelMock1.types()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeTrace("name", modelMock1);
            modelTrace2 = new ModelTraceHasTypes(modelMock2);
            expect(modelMock2.types()).andReturn(modelTrace2).anyTimes();
            replay(modelMock2);
  
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            result = classUnderTest.model().create(modelMock1);
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
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasElements modelTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelTraceHasElements modelTrace2;
        
        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertiesMock1;
        
        /** Mock the target of the properties reference. */
        @Mock
        private IPropertyTrace propertiesMock2;
        
        /** Allow the target mock to populate the reference */
        private IPropertyTraceHasElement propertyTrace1;
        
        /** Allow the target mock to populate the reference */
        private IPropertyTraceHasElement propertyTrace2;
        
        private ModelElementTrace classUnderTest;
        
        @Test
        public void testModelElementTraceInstantiation() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            assertThat(classUnderTest.model().get(), is(modelMock1));
            Queue<IModelElementTrace> values = modelMock1.elements().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelElementTraceAttributes on begin
	    //@Ignore
// protected region IgnoreModelElementTraceAttributes end	    
	    @Test
        public void testModelElementTraceAttributes() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
// protected region ModelElementTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            assertThat(classUnderTest.getUri(), is("uri"));                    
// protected region ModelElementTraceAttributes end
        }

        
        @Test
        public void testModelElementTraceCreateModelContainerReferenceConflict() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            modelTrace2 = new ModelTraceHasElements(modelMock2);
            expect(modelMock2.elements()).andReturn(modelTrace2).anyTimes();
            replay(modelMock2);
        
            boolean result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testModelElementTraceDestroyModelContainerReference() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelElementTraceDestroyAndCreateModelContainerReference() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            modelTrace2 = new ModelTraceHasElements(modelMock2);
            expect(modelMock2.elements()).andReturn(modelTrace2).anyTimes();
            replay(modelMock2);
  
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertTrue(result);
            result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
            result = classUnderTest.model().create(modelMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementTraceCreatePropertiesReference() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            propertyTrace1 = new PropertyTraceHasElement(propertiesMock1);
            expect(propertiesMock1.element()).andReturn(propertyTrace1).anyTimes();
            replay(propertiesMock1);
            propertyTrace2 = new PropertyTraceHasElement(propertiesMock2);
            expect(propertiesMock2.element()).andReturn(propertyTrace2).anyTimes();
            replay(propertiesMock2);
            boolean result;
            result = classUnderTest.properties().create(propertiesMock1);
            assertTrue(result);
            result = classUnderTest.properties().create(propertiesMock2);
            assertTrue(result);
            result = classUnderTest.properties().create(propertiesMock1);
            assertFalse(result);
            // Create a second one
            modelTrace2 = new ModelTraceHasElements(modelMock2);
            expect(modelMock2.elements()).andReturn(modelTrace2).anyTimes();
            replay(modelMock2);
            IModelElementTrace classUnderTest2 = new ModelElementTrace("uri2", modelMock2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testModelElementTraceDestroyPropertiesReference() throws Exception {
            modelTrace1 = new ModelTraceHasElements(modelMock1);
            expect(modelMock1.elements()).andReturn(modelTrace1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementTrace("uri", modelMock1);
            propertyTrace1 = new PropertyTraceHasElement(propertiesMock1);
            expect(propertiesMock1.element()).andReturn(propertyTrace1).anyTimes();
            replay(propertiesMock1);
            classUnderTest.properties().create(propertiesMock1);
            boolean result = classUnderTest.properties().destroy(propertiesMock1);
            assertTrue(result);
            assertThat(classUnderTest.properties().get(), not(hasItem(propertiesMock1)));
            propertyTrace2 = new PropertyTraceHasElement(propertiesMock2);
            expect(propertiesMock2.element()).andReturn(propertyTrace2).anyTimes();
            replay(propertiesMock2);
            result = classUnderTest.properties().destroy(propertiesMock2);
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
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasProperties modelElementTrace1;
        
        /** Allow the target mock to populate the reference */
        private IModelElementTraceHasProperties modelElementTrace2;
        
        private PropertyTrace classUnderTest;
        
        @Test
        public void testPropertyTraceInstantiation() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(elementMock1);
            expect(elementMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(elementMock1);
            classUnderTest = new PropertyTrace("name", elementMock1);
            assertThat(classUnderTest.element().get(), is(elementMock1));
            Queue<IPropertyTrace> values = elementMock1.properties().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyTraceAttributes on begin
	    //@Ignore
// protected region IgnorePropertyTraceAttributes end	    
	    @Test
        public void testPropertyTraceAttributes() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(elementMock1);
            expect(elementMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(elementMock1);
            classUnderTest = new PropertyTrace("name", elementMock1);
// protected region PropertyTraceAttributes on begin
            String id = "ObjectId";
            classUnderTest.setId(id);
            assertThat(classUnderTest.getId(), is(id));
            assertThat(classUnderTest.getName(), is("name"));                    
// protected region PropertyTraceAttributes end
        }

        
        @Test
        public void testPropertyTraceCreateElementContainerReferenceConflict() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(elementMock1);
            expect(elementMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(elementMock1);
            classUnderTest = new PropertyTrace("name", elementMock1);
            modelElementTrace2 = new ModelElementTraceHasProperties(elementMock2);
            expect(elementMock2.properties()).andReturn(modelElementTrace2).anyTimes();
            replay(elementMock2);
        
            boolean result = classUnderTest.element().create(elementMock2);
            assertFalse(result);
            
        }
        
        @Test
        public void testPropertyTraceDestroyElementContainerReference() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(elementMock1);
            expect(elementMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(elementMock1);
            classUnderTest = new PropertyTrace("name", elementMock1);
            boolean result = classUnderTest.element().destroy(elementMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyTraceDestroyAndCreateElementContainerReference() throws Exception {
            modelElementTrace1 = new ModelElementTraceHasProperties(elementMock1);
            expect(elementMock1.properties()).andReturn(modelElementTrace1).anyTimes();
            replay(elementMock1);
            classUnderTest = new PropertyTrace("name", elementMock1);
            modelElementTrace2 = new ModelElementTraceHasProperties(elementMock2);
            expect(elementMock2.properties()).andReturn(modelElementTrace2).anyTimes();
            replay(elementMock2);
  
            boolean result = classUnderTest.element().destroy(elementMock1);
            assertTrue(result);
            result = classUnderTest.element().create(elementMock2);
            assertTrue(result);
            result = classUnderTest.element().create(elementMock2);
            assertFalse(result);
            result = classUnderTest.element().create(elementMock1);
            assertFalse(result);
        }
        
    }

}