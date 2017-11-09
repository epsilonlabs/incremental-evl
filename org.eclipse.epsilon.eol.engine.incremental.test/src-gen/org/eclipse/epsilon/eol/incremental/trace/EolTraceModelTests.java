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
                     EolTraceModelTests.PropertyAccessTests.class,
                     EolTraceModelTests.PropertyTests.class,
                     EolTraceModelTests.ModelElementTests.class,
                     EolTraceModelTests.ModelTypeTests.class,
                     EolTraceModelTests.ModelTests.class})
public class EolTraceModelTests {

    
    public static class AllInstancesAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private Execution executionMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private Execution executionMock2;
        
        /** Allow the target mock to populate the reference */
        private ExecutionHasAccesses executionHasAccesses1;
        
        /** Allow the target mock to populate the reference */
        private ExecutionHasAccesses executionHasAccesses2;
        
        /** Mock the target of the type reference. */
        @Mock
        private ModelType modelTypeMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private ModelType modelTypeMock2;
        
        private AllInstancesAccess classUnderTest;
        
        @Test
        public void testAllInstancesAccessInstantiation() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            assertThat(classUnderTest.execution().get(), is(executionMock1));
            Queue<Access> values = executionMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreAllInstancesAccessAttributes on begin
	    @Ignore
// protected region IgnoreAllInstancesAccessAttributes end	    
	    @Test
        public void testAllInstancesAccessAttributes() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
// protected region AllInstancesAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region AllInstancesAccessAttributes end
        }

        
        @Test
        public void testAllInstancesAccessCreateExecutionConflict() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            executionHasAccesses2 = new ExecutionHasAccessesImpl(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionHasAccesses2).anyTimes();
            replay(executionMock2);
        
            boolean result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyExecution() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyAndCreateExecution() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            executionHasAccesses2 = new ExecutionHasAccessesImpl(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionHasAccesses2).anyTimes();
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
        public void testAllInstancesAccessCreateModelType() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            boolean result;
            result = classUnderTest.type().create(modelTypeMock2);
            assertFalse(result);
            result = classUnderTest.type().create(_type);
            assertFalse(result);
        }
        
        @Test
        public void testAllInstancesAccessDestroyModelType() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            ModelType _type = mock(ModelType.class);
            classUnderTest = new AllInstancesAccessImpl(_type, executionMock1);
            boolean result = classUnderTest.type().destroy(_type);
            assertTrue(result);
            assertThat(classUnderTest.type().get(), is(nullValue()));
            result = classUnderTest.type().destroy(modelTypeMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the execution reference. */
        @Mock
        private Execution executionMock1;
        
        /** Mock the target of the execution reference. */
        @Mock
        private Execution executionMock2;
        
        /** Allow the target mock to populate the reference */
        private ExecutionHasAccesses executionHasAccesses1;
        
        /** Allow the target mock to populate the reference */
        private ExecutionHasAccesses executionHasAccesses2;
        
        /** Mock the target of the property reference. */
        @Mock
        private Property propertyMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private Property propertyMock2;
        
        private PropertyAccess classUnderTest;
        
        @Test
        public void testPropertyAccessInstantiation() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            assertThat(classUnderTest.execution().get(), is(executionMock1));
            Queue<Access> values = executionMock1.accesses().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyAccessAttributes on begin
	    @Ignore
// protected region IgnorePropertyAccessAttributes end	    
	    @Test
        public void testPropertyAccessAttributes() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
// protected region PropertyAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyAccessAttributes end
        }

        
        @Test
        public void testPropertyAccessCreateExecutionConflict() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            executionHasAccesses2 = new ExecutionHasAccessesImpl(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionHasAccesses2).anyTimes();
            replay(executionMock2);
        
            boolean result = classUnderTest.execution().create(executionMock2);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessDestroyExecution() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            boolean result = classUnderTest.execution().destroy(executionMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyAccessDestroyAndCreateExecution() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            executionHasAccesses2 = new ExecutionHasAccessesImpl(executionMock2);
            expect(executionMock2.accesses()).andReturn(executionHasAccesses2).anyTimes();
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
        public void testPropertyAccessCreateProperty() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            boolean result;
            result = classUnderTest.property().create(propertyMock2);
            assertFalse(result);
            result = classUnderTest.property().create(_property);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyAccessDestroyProperty() throws Exception {
            executionHasAccesses1 = new ExecutionHasAccessesImpl(executionMock1);
            expect(executionMock1.accesses()).andReturn(executionHasAccesses1).anyTimes();
            replay(executionMock1);
            Property _property = mock(Property.class);
            classUnderTest = new PropertyAccessImpl(_property, executionMock1);
            boolean result = classUnderTest.property().destroy(_property);
            assertTrue(result);
            assertThat(classUnderTest.property().get(), is(nullValue()));
            result = classUnderTest.property().destroy(propertyMock2);
            assertFalse(result);
        }
    }
    
    public static class PropertyTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the element reference. */
        @Mock
        private ModelElement modelElementMock1;
        
        /** Mock the target of the element reference. */
        @Mock
        private ModelElement modelElementMock2;
        
        /** Allow the target mock to populate the reference */
        private ModelElementHasProperties modelElementHasProperties1;
        
        /** Allow the target mock to populate the reference */
        private ModelElementHasProperties modelElementHasProperties2;
        
        private Property classUnderTest;
        
        @Test
        public void testPropertyInstantiation() throws Exception {
            modelElementHasProperties1 = new ModelElementHasPropertiesImpl(modelElementMock1);
            expect(modelElementMock1.properties()).andReturn(modelElementHasProperties1).anyTimes();
            replay(modelElementMock1);
            classUnderTest = new PropertyImpl("name", modelElementMock1);
            assertThat(classUnderTest.element().get(), is(modelElementMock1));
            Queue<Property> values = modelElementMock1.properties().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnorePropertyAttributes on begin
	    @Ignore
// protected region IgnorePropertyAttributes end	    
	    @Test
        public void testPropertyAttributes() throws Exception {
            modelElementHasProperties1 = new ModelElementHasPropertiesImpl(modelElementMock1);
            expect(modelElementMock1.properties()).andReturn(modelElementHasProperties1).anyTimes();
            replay(modelElementMock1);
            classUnderTest = new PropertyImpl("name", modelElementMock1);
// protected region PropertyAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyAttributes end
        }

        
        @Test
        public void testPropertyCreateElementConflict() throws Exception {
            modelElementHasProperties1 = new ModelElementHasPropertiesImpl(modelElementMock1);
            expect(modelElementMock1.properties()).andReturn(modelElementHasProperties1).anyTimes();
            replay(modelElementMock1);
            classUnderTest = new PropertyImpl("name", modelElementMock1);
            modelElementHasProperties2 = new ModelElementHasPropertiesImpl(modelElementMock2);
            expect(modelElementMock2.properties()).andReturn(modelElementHasProperties2).anyTimes();
            replay(modelElementMock2);
        
            boolean result = classUnderTest.element().create(modelElementMock2);
            assertFalse(result);
        }
        
        @Test
        public void testPropertyDestroyElement() throws Exception {
            modelElementHasProperties1 = new ModelElementHasPropertiesImpl(modelElementMock1);
            expect(modelElementMock1.properties()).andReturn(modelElementHasProperties1).anyTimes();
            replay(modelElementMock1);
            classUnderTest = new PropertyImpl("name", modelElementMock1);
            boolean result = classUnderTest.element().destroy(modelElementMock1);
            assertTrue(result);
        }
        
        @Test
        public void testPropertyDestroyAndCreateElement() throws Exception {
            modelElementHasProperties1 = new ModelElementHasPropertiesImpl(modelElementMock1);
            expect(modelElementMock1.properties()).andReturn(modelElementHasProperties1).anyTimes();
            replay(modelElementMock1);
            classUnderTest = new PropertyImpl("name", modelElementMock1);
            modelElementHasProperties2 = new ModelElementHasPropertiesImpl(modelElementMock2);
            expect(modelElementMock2.properties()).andReturn(modelElementHasProperties2).anyTimes();
            replay(modelElementMock2);
  
            boolean result = classUnderTest.element().destroy(modelElementMock1);
            assertTrue(result);
            result = classUnderTest.element().create(modelElementMock2);
            assertTrue(result);
            result = classUnderTest.element().create(modelElementMock2);
            assertFalse(result);
            result = classUnderTest.element().create(modelElementMock1);
            assertFalse(result);
        }
        
    }
    
    public static class ModelElementTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock2;
        
        /** Allow the target mock to populate the reference */
        private ModelHasElements modelHasElements1;
        
        /** Allow the target mock to populate the reference */
        private ModelHasElements modelHasElements2;
        
        /** Mock the target of the properties reference. */
        @Mock
        private Property propertyMock1;
        
        /** Mock the target of the properties reference. */
        @Mock
        private Property propertyMock2;
        
        /** Allow the target mock to populate the reference */
        private PropertyHasElement propertyHasElement1;
        
        /** Allow the target mock to populate the reference */
        private PropertyHasElement propertyHasElement2;
        
        private ModelElement classUnderTest;
        
        @Test
        public void testModelElementInstantiation() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            assertThat(classUnderTest.model().get(), is(modelMock1));
            Queue<ModelElement> values = modelMock1.elements().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelElementAttributes on begin
	    @Ignore
// protected region IgnoreModelElementAttributes end	    
	    @Test
        public void testModelElementAttributes() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
// protected region ModelElementAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelElementAttributes end
        }

        
        @Test
        public void testModelElementCreateModelConflict() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            modelHasElements2 = new ModelHasElementsImpl(modelMock2);
            expect(modelMock2.elements()).andReturn(modelHasElements2).anyTimes();
            replay(modelMock2);
        
            boolean result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementDestroyModel() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelElementDestroyAndCreateModel() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            modelHasElements2 = new ModelHasElementsImpl(modelMock2);
            expect(modelMock2.elements()).andReturn(modelHasElements2).anyTimes();
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
        public void testModelElementCreateProperty() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            propertyHasElement1 = new PropertyHasElementImpl(propertyMock1);
            expect(propertyMock1.element()).andReturn(propertyHasElement1).anyTimes();
            replay(propertyMock1);
            propertyHasElement2 = new PropertyHasElementImpl(propertyMock2);
            expect(propertyMock2.element()).andReturn(propertyHasElement2).anyTimes();
            replay(propertyMock2);
            boolean result;
            result = classUnderTest.properties().create(propertyMock1);
            assertTrue(result);
            result = classUnderTest.properties().create(propertyMock2);
            assertTrue(result);
            result = classUnderTest.properties().create(propertyMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelElementDestroyProperty() throws Exception {
            modelHasElements1 = new ModelHasElementsImpl(modelMock1);
            expect(modelMock1.elements()).andReturn(modelHasElements1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelElementImpl("uri", modelMock1);
            propertyHasElement1 = new PropertyHasElementImpl(propertyMock1);
            expect(propertyMock1.element()).andReturn(propertyHasElement1).anyTimes();
            replay(propertyMock1);
            classUnderTest.properties().create(propertyMock1);
            boolean result = classUnderTest.properties().destroy(propertyMock1);
            assertTrue(result);
            assertThat(classUnderTest.properties().get(), not(hasItem(propertyMock1)));
            propertyHasElement2 = new PropertyHasElementImpl(propertyMock2);
            expect(propertyMock2.element()).andReturn(propertyHasElement2).anyTimes();
            replay(propertyMock2);
            result = classUnderTest.properties().destroy(propertyMock2);
            assertFalse(result);
        }
    }
    
    public static class ModelTypeTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock1;
        
        /** Mock the target of the model reference. */
        @Mock
        private Model modelMock2;
        
        /** Allow the target mock to populate the reference */
        private ModelHasTypes modelHasTypes1;
        
        /** Allow the target mock to populate the reference */
        private ModelHasTypes modelHasTypes2;
        
        private ModelType classUnderTest;
        
        @Test
        public void testModelTypeInstantiation() throws Exception {
            modelHasTypes1 = new ModelHasTypesImpl(modelMock1);
            expect(modelMock1.types()).andReturn(modelHasTypes1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeImpl("name", modelMock1);
            assertThat(classUnderTest.model().get(), is(modelMock1));
            Queue<ModelType> values = modelMock1.types().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelTypeAttributes on begin
	    @Ignore
// protected region IgnoreModelTypeAttributes end	    
	    @Test
        public void testModelTypeAttributes() throws Exception {
            modelHasTypes1 = new ModelHasTypesImpl(modelMock1);
            expect(modelMock1.types()).andReturn(modelHasTypes1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeImpl("name", modelMock1);
// protected region ModelTypeAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTypeAttributes end
        }

        
        @Test
        public void testModelTypeCreateModelConflict() throws Exception {
            modelHasTypes1 = new ModelHasTypesImpl(modelMock1);
            expect(modelMock1.types()).andReturn(modelHasTypes1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeImpl("name", modelMock1);
            modelHasTypes2 = new ModelHasTypesImpl(modelMock2);
            expect(modelMock2.types()).andReturn(modelHasTypes2).anyTimes();
            replay(modelMock2);
        
            boolean result = classUnderTest.model().create(modelMock2);
            assertFalse(result);
        }
        
        @Test
        public void testModelTypeDestroyModel() throws Exception {
            modelHasTypes1 = new ModelHasTypesImpl(modelMock1);
            expect(modelMock1.types()).andReturn(modelHasTypes1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeImpl("name", modelMock1);
            boolean result = classUnderTest.model().destroy(modelMock1);
            assertTrue(result);
        }
        
        @Test
        public void testModelTypeDestroyAndCreateModel() throws Exception {
            modelHasTypes1 = new ModelHasTypesImpl(modelMock1);
            expect(modelMock1.types()).andReturn(modelHasTypes1).anyTimes();
            replay(modelMock1);
            classUnderTest = new ModelTypeImpl("name", modelMock1);
            modelHasTypes2 = new ModelHasTypesImpl(modelMock2);
            expect(modelMock2.types()).andReturn(modelHasTypes2).anyTimes();
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
    
    public static class ModelTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the elements reference. */
        @Mock
        private ModelElement modelElementMock1;
        
        /** Mock the target of the elements reference. */
        @Mock
        private ModelElement modelElementMock2;
        
        /** Allow the target mock to populate the reference */
        private ModelElementHasModel modelElementHasModel1;
        
        /** Allow the target mock to populate the reference */
        private ModelElementHasModel modelElementHasModel2;
        
        /** Mock the target of the types reference. */
        @Mock
        private ModelType modelTypeMock1;
        
        /** Mock the target of the types reference. */
        @Mock
        private ModelType modelTypeMock2;
        
        /** Allow the target mock to populate the reference */
        private ModelTypeHasModel modelTypeHasModel1;
        
        /** Allow the target mock to populate the reference */
        private ModelTypeHasModel modelTypeHasModel2;
        
        /** Mock the container. */
        @Mock
        private ExecutionTrace containerMock;
        
        /** Allow the container mock to populate the reference */
        private ExecutionTraceHasModel executionTraceHasModel1;

        private Model classUnderTest;
        
        @Test
        public void testModelInstantiation() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
            Queue<Model> values = containerMock.model().get();
            assertThat(values, hasItem(classUnderTest));
	    }
	    
// protected region IgnoreModelAttributes on begin
	    @Ignore
// protected region IgnoreModelAttributes end	    
	    @Test
        public void testModelAttributes() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
// protected region ModelAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelAttributes end
        }

        @Test
        public void testModelCreateModelElement() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
            modelElementHasModel1 = new ModelElementHasModelImpl(modelElementMock1);
            expect(modelElementMock1.model()).andReturn(modelElementHasModel1).anyTimes();
            replay(modelElementMock1);
            modelElementHasModel2 = new ModelElementHasModelImpl(modelElementMock2);
            expect(modelElementMock2.model()).andReturn(modelElementHasModel2).anyTimes();
            replay(modelElementMock2);
            boolean result;
            result = classUnderTest.elements().create(modelElementMock1);
            assertTrue(result);
            result = classUnderTest.elements().create(modelElementMock2);
            assertTrue(result);
            result = classUnderTest.elements().create(modelElementMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelDestroyModelElement() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
            modelElementHasModel1 = new ModelElementHasModelImpl(modelElementMock1);
            expect(modelElementMock1.model()).andReturn(modelElementHasModel1).anyTimes();
            replay(modelElementMock1);
            classUnderTest.elements().create(modelElementMock1);
            boolean result = classUnderTest.elements().destroy(modelElementMock1);
            assertTrue(result);
            assertThat(classUnderTest.elements().get(), not(hasItem(modelElementMock1)));
            modelElementHasModel2 = new ModelElementHasModelImpl(modelElementMock2);
            expect(modelElementMock2.model()).andReturn(modelElementHasModel2).anyTimes();
            replay(modelElementMock2);
            result = classUnderTest.elements().destroy(modelElementMock2);
            assertFalse(result);
        }
        @Test
        public void testModelCreateModelType() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
            modelTypeHasModel1 = new ModelTypeHasModelImpl(modelTypeMock1);
            expect(modelTypeMock1.model()).andReturn(modelTypeHasModel1).anyTimes();
            replay(modelTypeMock1);
            modelTypeHasModel2 = new ModelTypeHasModelImpl(modelTypeMock2);
            expect(modelTypeMock2.model()).andReturn(modelTypeHasModel2).anyTimes();
            replay(modelTypeMock2);
            boolean result;
            result = classUnderTest.types().create(modelTypeMock1);
            assertTrue(result);
            result = classUnderTest.types().create(modelTypeMock2);
            assertTrue(result);
            result = classUnderTest.types().create(modelTypeMock1);
            assertFalse(result);
        }
        
        @Test
        public void testModelDestroyModelType() throws Exception {
            executionTraceHasModel1 = new ExecutionTraceHasModelImpl(containerMock);
            expect(containerMock.model()).andReturn(executionTraceHasModel1).anyTimes();
            replay(containerMock);
            classUnderTest = new ModelImpl("name", containerMock);
            modelTypeHasModel1 = new ModelTypeHasModelImpl(modelTypeMock1);
            expect(modelTypeMock1.model()).andReturn(modelTypeHasModel1).anyTimes();
            replay(modelTypeMock1);
            classUnderTest.types().create(modelTypeMock1);
            boolean result = classUnderTest.types().destroy(modelTypeMock1);
            assertTrue(result);
            assertThat(classUnderTest.types().get(), not(hasItem(modelTypeMock1)));
            modelTypeHasModel2 = new ModelTypeHasModelImpl(modelTypeMock2);
            expect(modelTypeMock2.model()).andReturn(modelTypeHasModel2).anyTimes();
            replay(modelTypeMock2);
            result = classUnderTest.types().destroy(modelTypeMock2);
            assertFalse(result);
        }
    }

}