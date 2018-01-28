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
@Suite.SuiteClasses({BaseTraceModelTests.AllInstancesAccessTests.class,
                     BaseTraceModelTests.PropertyAccessTests.class,
                     BaseTraceModelTests.ModelTraceTests.class,
                     BaseTraceModelTests.ModelTypeTraceTests.class,
                     BaseTraceModelTests.ModelElementTraceTests.class,
                     BaseTraceModelTests.PropertyTraceTests.class})
public class BaseTraceModelTests {

    
    public static class AllInstancesAccessTests extends EasyMockSupport {
    
        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock1;
        
        /** Mock the target of the type reference. */
        @Mock
        private IModelTypeTrace typeMock2;
        
        private AllInstancesAccess classUnderTest;
        
        @Test
        public void testAllInstancesAccessInstantiation() throws Exception {
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            classUnderTest = new AllInstancesAccess(_type);
	    }
	    
// protected region IgnoreAllInstancesAccessAttributes on begin
	    @Ignore
// protected region IgnoreAllInstancesAccessAttributes end	    
	    @Test
        public void testAllInstancesAccessAttributes() throws Exception {
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            classUnderTest = new AllInstancesAccess(_type);
// protected region AllInstancesAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region AllInstancesAccessAttributes end
        }

        @Test
        public void testAllInstancesAccessCreateTypeReference() throws Exception {
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            classUnderTest = new AllInstancesAccess(_type);
            boolean result;
            result = classUnderTest.type().create(typeMock2);
            assertFalse(result);
            result = classUnderTest.type().create(_type);
            assertFalse(result);
            // Create a second one
            IModelTypeTrace _type2 = mock(IModelTypeTrace.class);
             
            IAllInstancesAccess classUnderTest2 = new AllInstancesAccess(_type2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testAllInstancesAccessDestroyTypeReference() throws Exception {
            IModelTypeTrace _type = mock(IModelTypeTrace.class);
        
            classUnderTest = new AllInstancesAccess(_type);
        
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

        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock1;
        
        /** Mock the target of the property reference. */
        @Mock
        private IPropertyTrace propertyMock2;
        
        private PropertyAccess classUnderTest;
        
        @Test
        public void testPropertyAccessInstantiation() throws Exception {
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            classUnderTest = new PropertyAccess(_property);
	    }
	    
// protected region IgnorePropertyAccessAttributes on begin
	    @Ignore
// protected region IgnorePropertyAccessAttributes end	    
	    @Test
        public void testPropertyAccessAttributes() throws Exception {
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            classUnderTest = new PropertyAccess(_property);
// protected region PropertyAccessAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyAccessAttributes end
        }

        @Test
        public void testPropertyAccessCreatePropertyReference() throws Exception {
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            classUnderTest = new PropertyAccess(_property);
            boolean result;
            result = classUnderTest.property().create(propertyMock2);
            assertFalse(result);
            result = classUnderTest.property().create(_property);
            assertFalse(result);
            // Create a second one
            IPropertyTrace _property2 = mock(IPropertyTrace.class);
             
            IPropertyAccess classUnderTest2 = new PropertyAccess(_property2);
            assertThat(classUnderTest2, is(notNullValue()));
        }
        
        @Test
        public void testPropertyAccessDestroyPropertyReference() throws Exception {
            IPropertyTrace _property = mock(IPropertyTrace.class);
        
            classUnderTest = new PropertyAccess(_property);
        
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
        
        @Test
        public void testModelTraceInstantiation() throws Exception {
            classUnderTest = new ModelTrace("name1");
	    }
	    
// protected region IgnoreModelTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTraceAttributes end	    
	    @Test
        public void testModelTraceAttributes() throws Exception {
            classUnderTest = new ModelTrace("name1");
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
        
        @Test
        public void testModelTypeTraceInstantiation() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelTypeTrace("name1", _model);
	    }
	    
// protected region IgnoreModelTypeTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelTypeTraceAttributes end	    
	    @Test
        public void testModelTypeTraceAttributes() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelTypeTrace("name1", _model);
// protected region ModelTypeTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelTypeTraceAttributes end
        }

        @Test
        public void testModelTypeTraceCreateModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelTypeTrace("name1", _model);
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
        
            classUnderTest = new ModelTypeTrace("name1", _model);
        
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
        
        @Test
        public void testModelElementTraceInstantiation() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);
	    }
	    
// protected region IgnoreModelElementTraceAttributes on begin
	    @Ignore
// protected region IgnoreModelElementTraceAttributes end	    
	    @Test
        public void testModelElementTraceAttributes() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);
// protected region ModelElementTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region ModelElementTraceAttributes end
        }

        @Test
        public void testModelElementTraceCreateModelReference() throws Exception {
            IModelTrace _model = mock(IModelTrace.class);
        
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);
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
        
            classUnderTest = new ModelElementTrace("url://path/in/model/to/uri/1", _model);
        
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
        
        @Test
        public void testPropertyTraceInstantiation() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            classUnderTest = new PropertyTrace("name1", _element);
	    }
	    
// protected region IgnorePropertyTraceAttributes on begin
	    @Ignore
// protected region IgnorePropertyTraceAttributes end	    
	    @Test
        public void testPropertyTraceAttributes() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            classUnderTest = new PropertyTrace("name1", _element);
// protected region PropertyTraceAttributes on begin
            // TODO Add test code for parameters (to hard to generate correct code for any type).                    
// protected region PropertyTraceAttributes end
        }

        @Test
        public void testPropertyTraceCreateElementReference() throws Exception {
            IModelElementTrace _element = mock(IModelElementTrace.class);
        
            classUnderTest = new PropertyTrace("name1", _element);
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
        
            classUnderTest = new PropertyTrace("name1", _element);
        
            boolean result = classUnderTest.element().destroy(_element);
            assertTrue(result);
            assertThat(classUnderTest.element().get(), is(nullValue()));
            result = classUnderTest.element().destroy(elementMock2);
            assertFalse(result);
        }
    }

}