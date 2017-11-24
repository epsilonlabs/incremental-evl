package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.execute.IEolModuleExecutionRepository;
import org.eclipse.epsilon.eol.incremental.execute.IModelTraceRepository;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTraceHasElements;
import org.eclipse.epsilon.eol.incremental.trace.IModelTraceHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecutionHasModel;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	RecordingTests.PropertyAccessRecorderTests.class,
				RecordingTests.AllInstancesInvocationRecorderTests.class})
public class RecordingTests {
	
	private interface TestModuleExecution extends IModuleExecution { }
	
	public static class AccessRecorderTests<T> extends EasyMockSupport {
		
		protected IRecorder<T> recorder;
		
		@Test
        public void testStartRecording() {
			assertFalse(recorder.isRecording());
			recorder.startRecording();
			IRecordings<T> recordings = recorder.getRecordings();
			assertTrue(recorder.isRecording());
			recorder.startRecording();
			assertNotEquals(recordings, recorder.getRecordings());
		}
		
		@Test
        public void testStopRecording() {
			assertFalse(recorder.isRecording());
			recorder.startRecording();
			IRecordings<T> recordings = recorder.getRecordings();
			assertTrue(recorder.isRecording());
			recorder.stopRecording();
			assertFalse(recorder.isRecording());
			assertEquals(recordings, recorder.getRecordings());
		}
		
	}
	
	public static class PropertyAccessRecorderTests extends AccessRecorderTests<IPropertyAccess> {
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IEolExecutionTraceManager traceManager;
		
		@Mock
		private IModuleExecution evlExecution;
		
		@Mock
		private IExecutionTrace executionTrace;
		
		@Before
		public void setup() {
			recorder = new PropertyAccessRecorder(traceManager, evlExecution, executionTrace);
		}
		
		@Test
		public void testRecordNotRecording() {
			//IModel model = support.mock(IModel.class);
			IModel model = mock(IModel.class);
			String modelElement = "element";
			String propertyName = "address";
			((PropertyAccessRecorder)recorder).record(model, modelElement, propertyName);
			assertThat(recorder.getRecordings(), is(nullValue()));
		}
		
		@Test
		public void testRecord() throws Exception {
			String modelElement = "element";
			String propertyName = "address";
			String modelName = "modelA";
			String elementId = "some/path/element";
			
			// Setup mocks
			IModel model = mock(IModel.class);
			EasyMock.expect(model.getName()).andReturn(modelName).anyTimes();
			EasyMock.expect(model.getElementId(modelElement)).andReturn(elementId).anyTimes();
			
			// Model repo has no model by that name
			IModelTraceRepository modelRepoMock = createNiceMock(IModelTraceRepository.class);  // Nice so it allows add()
			EasyMock.expect(modelRepoMock.getModelTraceByName(modelName)).andReturn(null);
			// The trace manager returns this mock
			EasyMock.expect(traceManager.modelTraces()).andReturn(modelRepoMock).times(2);
			
			// Since not present, the evlExecution will need to create one
			IModelTrace modelTraceMock = mock(IModelTrace.class);
			EasyMock.expect(evlExecution.createModelTrace(modelName)).andReturn(modelTraceMock);
				// evlExecution has models
			IModuleExecutionHasModel hasModel = createMock(IModuleExecutionHasModel.class);
			EasyMock.expect(evlExecution.model()).andReturn(hasModel).anyTimes();
			//EasyMock.expect(hasModel.create(modelTraceMock)).andReturn(true);
						
			// Model has no elements
			IModelTraceHasElements modelHasElementsMock = mock(IModelTraceHasElements.class);
			EasyMock.expect(modelTraceMock.elements()).andReturn(modelHasElementsMock);
			EasyMock.expect(modelHasElementsMock.get()).andReturn(new ArrayDeque<>());
						
			// Since not present, the model trace will craete a modelElement
			IModelElementTrace elementTraceMock = mock(IModelElementTrace.class);
			EasyMock.expect(modelTraceMock.createModelElementTrace(elementId)).andReturn(elementTraceMock);
			
			// Element has no properties
			IModelElementTraceHasProperties elementHasPropMock = mock(IModelElementTraceHasProperties.class);
			EasyMock.expect(elementTraceMock.properties()).andReturn(elementHasPropMock);
			EasyMock.expect(elementHasPropMock.get()).andReturn(new ArrayDeque<>());
			
			// Since not present, the element will create a Property
			IPropertyTrace propertyTraceMock = mock(IPropertyTrace.class);
			EasyMock.expect(elementTraceMock.createPropertyTrace(propertyName)).andReturn(propertyTraceMock);
			
			// No traces for the property
			@SuppressWarnings("unchecked")
			IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock = mock(IEolModuleExecutionRepository.class);
			EasyMock.expect(traceManager.moduleExecutionTraces()).andReturn((IEolModuleExecutionRepository) executionRepoMock);
			EasyMock.expect(executionRepoMock.getPropertyAccessFor(executionTrace, propertyTraceMock)).andReturn(null);
			
			// We need to create one
			IPropertyAccess pa = mock(IPropertyAccess.class);
			EasyMock.expect(executionTrace.createPropertyAccess(elementTraceMock, propertyTraceMock)).andReturn(pa);
			replayAll();
			recorder.startRecording();
			((PropertyAccessRecorder)recorder).record(model, modelElement, propertyName);
			assertThat(recorder.getRecordings().all(), contains(pa));
			verifyAll();
		}
	}
	
	public static class AllInstancesInvocationRecorderTests extends AccessRecorderTests<IAllInstancesAccess> {
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IEolExecutionTraceManager traceManager;
		
		@Mock
		private IModuleExecution evlExecution;
		
		@Mock
		private IExecutionTrace executionTrace;
		
		@Before
		public void setup() {
			recorder = new AllInstancesInvocationRecorder(traceManager, evlExecution, executionTrace);
		}
		
		@Test
		public void testRecordNotRecording() {
			//IModel model = support.mock(IModel.class);
			boolean isKind = true;
			String modelAndMetaClass = "modelA!typeB";
			((AllInstancesInvocationRecorder)recorder).record(isKind, modelAndMetaClass);
			assertThat(recorder.getRecordings(), is(nullValue()));
		}
		
		@Test
		public void testRecordEmptyTraceNotQualified() throws Exception {
			boolean isKind = true;
			String modelAndMetaClass = "typeB";
			String modelName = "";
			String modelTypeName = "typeB";
			
			// Setup mocks
			IModel model = mock(IModel.class);
			EasyMock.expect(model.getName()).andReturn(modelName).anyTimes();
			
			// No trace for the model
			IModelTraceRepository modelRepoMock = createNiceMock(IModelTraceRepository.class);  // Nice so it allows add()
			EasyMock.expect(modelRepoMock.first()).andReturn(null);
			// The trace manager returns this mock
			EasyMock.expect(traceManager.modelTraces()).andReturn(modelRepoMock).times(2);
			
			// Since not present, the evlExecution will need to create one
			IModelTrace modelTraceMock = mock(IModelTrace.class);
			EasyMock.expect(evlExecution.createModelTrace(modelName)).andReturn(modelTraceMock);
				// evlExecution has models
			IModuleExecutionHasModel hasModel = createMock(IModuleExecutionHasModel.class);
			EasyMock.expect(evlExecution.model()).andReturn(hasModel).anyTimes();
						
			// ModelTrace has no types
			IModelTraceHasTypes modelHasTypesMock = mock(IModelTraceHasTypes.class);
			EasyMock.expect(modelTraceMock.types()).andReturn(modelHasTypesMock);
			EasyMock.expect(modelHasTypesMock.get()).andReturn(new ArrayDeque<>());
						
			// Since not present, the model trace will create a modelType
			IModelTypeTrace typeTraceMock = mock(IModelTypeTrace.class);
			EasyMock.expect(modelTraceMock.createModelTypeTrace(modelTypeName)).andReturn(typeTraceMock);
			
			// No traces for the type
			@SuppressWarnings("unchecked")
			IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock = mock(IEolModuleExecutionRepository.class);
			EasyMock.expect(traceManager.moduleExecutionTraces()).andReturn((IEolModuleExecutionRepository) executionRepoMock);
			EasyMock.expect(executionRepoMock.getAllInstancesAccessFor(executionTrace, typeTraceMock)).andReturn(null);
			
			// We need to create one
			IAllInstancesAccess aa = createNiceMock(IAllInstancesAccess.class);
			EasyMock.expect(executionTrace.createAllInstancesAccess(typeTraceMock)).andReturn(aa);
			replayAll();
			
			recorder.startRecording();
			((AllInstancesInvocationRecorder)recorder).record(isKind, modelAndMetaClass);
			assertThat(recorder.getRecordings().all(), contains(aa));
			verifyAll();
		}
		
		@Test
		public void testRecordEmptyTraceQualified() throws Exception {
			boolean isKind = true;
			String modelAndMetaClass = "modelA!typeB";
			String modelName = "modelA";
			String modelTypeName = "typeB";
			
			// Setup mocks
			IModel model = mock(IModel.class);
			EasyMock.expect(model.getName()).andReturn(modelName).anyTimes();
			
			// No trace for the model
			IModelTraceRepository modelRepoMock = createNiceMock(IModelTraceRepository.class);  // Nice so it allows add()
			EasyMock.expect(modelRepoMock.getModelTraceByName(modelName)).andReturn(null);
			// The trace manager returns this mock
			EasyMock.expect(traceManager.modelTraces()).andReturn(modelRepoMock).times(2);
			
			// Since not present, the evlExecution will need to create one
			IModelTrace modelTraceMock = mock(IModelTrace.class);
			EasyMock.expect(evlExecution.createModelTrace(modelName)).andReturn(modelTraceMock);
				// evlExecution has models
			IModuleExecutionHasModel hasModel = createMock(IModuleExecutionHasModel.class);
			EasyMock.expect(evlExecution.model()).andReturn(hasModel).anyTimes();
						
			// ModelTrace has no types
			IModelTraceHasTypes modelHasTypesMock = mock(IModelTraceHasTypes.class);
			EasyMock.expect(modelTraceMock.types()).andReturn(modelHasTypesMock);
			EasyMock.expect(modelHasTypesMock.get()).andReturn(new ArrayDeque<>());
						
			// Since not present, the model trace will create a modelType
			IModelTypeTrace typeTraceMock = mock(IModelTypeTrace.class);
			EasyMock.expect(modelTraceMock.createModelTypeTrace(modelTypeName)).andReturn(typeTraceMock);
			
			// No traces for the type
			@SuppressWarnings("unchecked")
			IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock = mock(IEolModuleExecutionRepository.class);
			EasyMock.expect(traceManager.moduleExecutionTraces()).andReturn((IEolModuleExecutionRepository) executionRepoMock);
			EasyMock.expect(executionRepoMock.getAllInstancesAccessFor(executionTrace, typeTraceMock)).andReturn(null);
			
			// We need to create one
			IAllInstancesAccess aa = createNiceMock(IAllInstancesAccess.class);
			EasyMock.expect(executionTrace.createAllInstancesAccess(typeTraceMock)).andReturn(aa);
			replayAll();
			
			recorder.startRecording();
			((AllInstancesInvocationRecorder)recorder).record(isKind, modelAndMetaClass);
			assertThat(recorder.getRecordings().all(), contains(aa));
			verifyAll();
		}
		
		@Test
		public void testRecordKindWithFullyQualified() throws Exception {
			
		}
	}
}
