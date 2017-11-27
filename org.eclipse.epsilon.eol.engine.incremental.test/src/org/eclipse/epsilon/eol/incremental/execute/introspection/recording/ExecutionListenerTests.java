package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.NameExpression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.dom.StringLiteral;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
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
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	ExecutionListenerTests.AllInstancesInvocationExetionListenerTest.class,
				ExecutionListenerTests.PropertyAccessExecutionListenerTest.class})
public class ExecutionListenerTests {
	
	private interface TestModuleExecution extends IModuleExecution { }
	
	public static class AllInstancesInvocationExetionListenerTest extends EasyMockSupport {
		
		private final String result = "someValue";
		private final String typeName = "modelA!typeB";
		private final StringLiteral[] params = new StringLiteral[0];
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IEolExecutionTraceManager<TestModuleExecution> traceManagerMock;
		@Mock
		private IModuleExecution evlExecutionMock;
		@Mock
		private IEolContext contextMock;
		

		
		private AllInstancesInvocationExecutionListener listener;
		
		private OperationCallExpression ast;
		
		private NameExpression targetExpression;
		
		@Before
		public void setup() {
			listener = new AllInstancesInvocationExecutionListener(traceManagerMock, evlExecutionMock);
			targetExpression = new NameExpression(typeName);
			EolCompilationContext contextMock = new EolCompilationContext();
			targetExpression.compile(contextMock);
		}
		
		@Test
		public void testFinishedNotAnOperationCallExpression() {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute other expression
			ModuleElement me = mock(ModuleElement.class);
			listener.finishedExecuting(me, result, null);
			replayAll();
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testFinishedNotAnAllOperationCallExpression() {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute a non-all operation
			ast = ExecutionListenerTests.createOperationCallExpression(targetExpression, "getOne", params);
			listener.finishedExecuting(ast, result, null);
			replayAll();
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testFinishedAllInvocation() throws Exception {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace("modelA", "typeB", executionTraceMock);
			replayAll();
			// Test
			// 3. Execute all operation
			ast = ExecutionListenerTests.createOperationCallExpression(targetExpression, "all", params);
			listener.finishedExecuting(ast, "elements", null);
			
			// 4. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testFinishedAllInstancesInvocation() throws Exception {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace("modelA", "typeB", executionTraceMock);
			replayAll();
			
			// Test
			// 3. Execute all operation
			ast = ExecutionListenerTests.createOperationCallExpression(targetExpression, "allInstances", params);
			listener.finishedExecuting(ast, "elements", null);
//			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
//
//				@Override
//				public Object answer() throws Throwable {
//					// Verify arguments
//					assertThat(EasyMock.getCurrentArguments().length, is(2));
//					assertThat(EasyMock.getCurrentArguments()[0], is(true));
//					assertThat(EasyMock.getCurrentArguments()[1], is(typeName));
//					return null;
//				}
//			});
			// 4. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testFinishedAllOfKindInvocation() throws Exception {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace("modelA", "typeB", executionTraceMock);
			replayAll();
			
			// Test
			// 3. Execute all operation
			ast = ExecutionListenerTests.createOperationCallExpression(targetExpression, "allOfKind", params);
			listener.finishedExecuting(ast, "elements", null);
			
			// 4. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testFinishedAllOfTypeInvocation() throws Exception {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace("modelA", "typeB", executionTraceMock);
			replayAll();
			
			// Test
			// 3. Execute all operation
			ast = ExecutionListenerTests.createOperationCallExpression(targetExpression, "allOfType", params);
			listener.finishedExecuting(ast, "elements", null);
			
			// 4. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		private void recordExecutionTrace(String modelName, String modelTypeName, IExecutionTrace executionTraceMock) throws Exception {
			// Setup mocks
			IModel model = mock(IModel.class);
			IModelTraceRepository modelRepoMock = createNiceMock(IModelTraceRepository.class);  // Nice so it allows add()
			IModelTrace modelTraceMock = mock(IModelTrace.class);
			IModuleExecutionHasModel hasModel = createMock(IModuleExecutionHasModel.class);
			IModelTraceHasTypes modelHasTypesMock = mock(IModelTraceHasTypes.class);
			IAllInstancesAccess aa = createNiceMock(IAllInstancesAccess.class);
			
			EasyMock.expect(model.getName()).andReturn(modelName).anyTimes();
			
			// No trace for the model
			if (!modelName.isEmpty()) {
				EasyMock.expect(modelRepoMock.getModelTraceByName(modelName)).andReturn(null);
			}
			else {
				EasyMock.expect(modelRepoMock.first()).andReturn(null);
			}
			// The trace manager returns this mock
			EasyMock.expect(traceManagerMock.modelTraces()).andReturn(modelRepoMock).times(2);
			
			// Since not present, the evlExecution will need to create one
			
			EasyMock.expect(evlExecutionMock.createModelTrace(modelName)).andReturn(modelTraceMock);
				// evlExecution has models
			EasyMock.expect(evlExecutionMock.model()).andReturn(hasModel).anyTimes();
						
			// ModelTrace has no types
			
			EasyMock.expect(modelTraceMock.types()).andReturn(modelHasTypesMock);
			EasyMock.expect(modelHasTypesMock.get()).andReturn(new ArrayDeque<>());
						
			// Since not present, the model trace will create a modelType
			IModelTypeTrace typeTraceMock = mock(IModelTypeTrace.class);
			EasyMock.expect(modelTraceMock.createModelTypeTrace(modelTypeName)).andReturn(typeTraceMock);
			
			// No traces for the type
			@SuppressWarnings("unchecked")
			IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock = mock(IEolModuleExecutionRepository.class);
			EasyMock.expect(traceManagerMock.moduleExecutionTraces()).andReturn((IEolModuleExecutionRepository) executionRepoMock);
			EasyMock.expect(executionRepoMock.getAllInstancesAccessFor(executionTraceMock, typeTraceMock)).andReturn(null);
			
			// We need to create one
			EasyMock.expect(executionTraceMock.createAllInstancesAccess(typeTraceMock)).andReturn(aa);
		
		}
	}

	public static class PropertyAccessExecutionListenerTest extends EasyMockSupport {
		
		private String modelName = "modelA";
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IEolExecutionTraceManager<TestModuleExecution> traceManagerMock;
		@Mock
		private IModuleExecution evlExecutionMock;

		
		@Mock
		private IEolContext contextMock;	// = mock(IEolContext.class);
//		@Mock
//		private IModel modelMock;	// = mock(IModel.class);
//		@Mock
//		private IModelTrace modelTraceMock;	// = mock(IModelTrace.class);
//		@Mock
//		private IModuleExecutionHasModel hasModelMock;	// = mock(IModuleExecutionHasModel.class);
//		@Mock
//		private IModelTraceHasElements modelHasElementsMock;	// = mock(IModelTraceHasElements.class);
//		@Mock
//		private IPropertyTrace propertyTraceMock;	// = mock(IPropertyTrace.class);
//		@Mock
//		private IPropertyAccess pa;	// = mock(IPropertyAccess.class);
//		@Mock
//		private IModelElementTrace elementTraceMock;	// = mock(IModelElementTrace.class);
//		@Mock
//		private IModelElementTraceHasProperties elementHasPropMock;	// = mock(IModelElementTraceHasProperties.class);
//		@Mock
//		private IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock;	// = mock(IEolModuleExecutionRepository.class);
		
		
		private PropertyAccessExecutionListener listener;
		
		@Test
		public void testRecordSingleExecution() throws Exception {
			String elementId = "some/path/element";
			String result = "someValue";
			String instance = "someObject";
			String propertyName = "someProperty";
			String propertyvalue = "somePValue";
			
			// Init the listener
			listener = new PropertyAccessExecutionListener(traceManagerMock, evlExecutionMock);

			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Save the leftHand side result 1st.
			StringLiteral objectValue = createLeftSideExpression("ObjectRef");
			listener.finishedExecuting(objectValue, instance, null);
			
			// 3. Create the traced property call expression
			PropertyCallExpression ast = createPropertyAccessExpression(objectValue, propertyName );
			
			// 4. Record invocations
			recordExecutionTrace(elementId, instance, propertyName, executionTraceMock);
			// Test
			replayAll();
			
			// 5. Finish executing property access, should trigger ExecutionTrace access
			listener.finishedExecuting(ast, propertyvalue, contextMock);
			// 6. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		@Test
		public void testNoPropertyAccess() throws Exception {
			String elementId = "some/path/element";
			String result = "someValue";
			String instance = "someObject";
			String propertyName = "someProperty";
			
			// Init the listener
			listener = new PropertyAccessExecutionListener(traceManagerMock, evlExecutionMock);

			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedExecutableBlock<Boolean> blockMock = new TracedExecutableBlock<Boolean>(Boolean.class);
			IExecutionTrace executionTraceMock = mock(IExecutionTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Save the leftHand side result 1st.
			StringLiteral objectValue = createLeftSideExpression("ObjectRef");
			listener.finishedExecuting(objectValue, instance, null);
			
			//3. Create an operation call expression
			NameExpression targetExpression = new NameExpression("modelA!typeB");
			StringLiteral[] params = new StringLiteral[0];
			OperationCallExpression ast = createOperationCallExpression(objectValue, "someOp", params);
			
			// 4. No record as there should be no ExecutionTrace access
			
			// Test
			replayAll();
			// 5. Finish executing property access, should trigger ExecutionTrace access
			listener.finishedExecuting(ast, result, contextMock);
			// 6. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
			verifyAll();
		}
		
		// FIXME TO HARD TO TEST WITH EASYMOCK, MOCKITO?
		// OR move to EVL tests where we can fully instantiate the ExecutionTrace...
//		@Test
//		public void testRecordNestedExecutions() throws Exception {
//			String elementId = "some/path/element";
//			String result = "someValue";
//			String instance = "someObject";
//			String propertyOneName = "someProperty";
//			String propertyTwoName = "otherProperty";
//			
//			
//			// Init the listener
//			listener = new PropertyAccessExecutionListener(traceManagerMock, evlExecutionMock);
//
//			// 1. Trigger listener by executing a TracedExecutableBlock
//			TracedExecutableBlock<Boolean> blockMock1 = new TracedExecutableBlock<Boolean>(Boolean.class);
//			IExecutionTrace executionTrace1Mock = mock(IExecutionTrace.class);
//			blockMock1.setTrace(executionTrace1Mock);
//			listener.aboutToExecute(blockMock1, contextMock);
//			
//			// 2. Nested block
//			TracedExecutableBlock<Boolean> blockMock2 = new TracedExecutableBlock<Boolean>(Boolean.class);
//			IExecutionTrace executionTrace2Mock = mock(IExecutionTrace.class);
//			blockMock2.setTrace(executionTrace2Mock);
//			listener.aboutToExecute(blockMock2, contextMock);
//			
//			// 3a. Save the leftHand side result 1st.
//			StringLiteral object1Value = createLeftSideExpression("Object1Ref");
//			listener.finishedExecuting(object1Value, instance, null);
//			
//			// 3b. Create the traced property call expression
//			PropertyCallExpression ast1 = createAccessPropCall(object1Value, propertyOneName );
//			// Record invocations
//			recordExecutionTrace(elementId, instance, propertyOneName, executionTrace1Mock);
//			
//			// 4a. Save the leftHand side result 1st.
//			StringLiteral object2Value = createLeftSideExpression("Object2Ref");
//			listener.finishedExecuting(object2Value, instance, null);
//			
//			// 4b. Create the traced property call expression
//			PropertyCallExpression ast2 = createAccessPropCall(object2Value, propertyTwoName );
//			// Record invocations
//			recordExecutionTrace(elementId, instance, propertyTwoName, executionTrace2Mock);
//			
//			// Test
//			replayAll();
//			// 5. Finish executing property access
//			listener.finishedExecuting(ast2, result, contextMock);
//			
//			// 6. Finish executing block1
//			listener.finishedExecuting(blockMock1, false, contextMock);
//			assertFalse(listener.done());
//			
//			// 7. Finish executing property access
//			listener.finishedExecuting(ast2, result, contextMock);
//			
//			// 8. Finish executing block2
//			listener.finishedExecuting(blockMock2, true, contextMock);
//			assertFalse(listener.done());
//			
//			
//			verifyAll();
//		}

		/**
		 * @param elementId
		 * @param instance
		 * @param propertyName
		 * @param executionTraceMock 
		 * @param propertyvalue 
		 * @throws EolIncrementalExecutionException
		 */
		private void recordExecutionTrace(String elementId, String instance, String propertyName, IExecutionTrace executionTraceMock)
				throws EolIncrementalExecutionException {
			
			IModel modelMock = mock(IModel.class);
			IModelTrace modelTraceMock = mock(IModelTrace.class);
			IModuleExecutionHasModel hasModelMock = mock(IModuleExecutionHasModel.class);
			IModelTraceHasElements modelHasElementsMock = mock(IModelTraceHasElements.class);
			IPropertyTrace propertyTraceMock = mock(IPropertyTrace.class);
			IPropertyAccess pa = createNiceMock(IPropertyAccess.class);
			IModelElementTrace elementTraceMock = mock(IModelElementTrace.class);
			IModelElementTraceHasProperties elementHasPropMock = mock(IModelElementTraceHasProperties.class);
			IEolModuleExecutionRepository<TestModuleExecution> executionRepoMock = mock(IEolModuleExecutionRepository.class);
			
			ModelRepository modelRepo = new ModelRepository();
			modelRepo.addModel(modelMock);
			EasyMock.expect(contextMock.getModelRepository()).andReturn(modelRepo).times(1);
			EasyMock.expect(modelMock.getName()).andReturn(modelName).anyTimes();
			EasyMock.expect(modelMock.getElementId(instance)).andReturn(elementId).anyTimes();
			EasyMock.expect(modelMock.knowsAboutProperty(instance, propertyName)).andReturn(true).times(1);
			
			// Model repo has no model by that name
			IModelTraceRepository modelTraceRepoMock = createNiceMock(IModelTraceRepository.class);  // Nice so it allows add()
			EasyMock.expect(modelTraceRepoMock.getModelTraceByName(modelName)).andReturn(null);
			// The trace manager returns the model trace repo mock
			EasyMock.expect(traceManagerMock.modelTraces()).andReturn(modelTraceRepoMock).times(2);

			// Since not present, the evlExecution will need to create one
			EasyMock.expect(evlExecutionMock.createModelTrace(modelName)).andReturn(modelTraceMock);
				// evlExecution has models
			//EasyMock.expect(evlExecutionMock.model()).andReturn(hasModelMock).times(1);
						
			// Model has no elements
			EasyMock.expect(modelTraceMock.elements()).andReturn(modelHasElementsMock);
			EasyMock.expect(modelHasElementsMock.get()).andReturn(new ArrayDeque<>());
						
			// Since not present, the model trace will craete a modelElement
			
			EasyMock.expect(modelTraceMock.createModelElementTrace(elementId)).andReturn(elementTraceMock);
			
			// Element has no properties
			EasyMock.expect(elementTraceMock.properties()).andReturn(elementHasPropMock);
			EasyMock.expect(elementHasPropMock.get()).andReturn(new ArrayDeque<>());
			
			// Since not present, the element will create a Property
			EasyMock.expect(elementTraceMock.createPropertyTrace(propertyName)).andReturn(propertyTraceMock);
			
			// No traces for the property
			EasyMock.expect(traceManagerMock.moduleExecutionTraces()).andReturn((IEolModuleExecutionRepository) executionRepoMock);
			EasyMock.expect(executionRepoMock.getPropertyAccessFor(executionTraceMock, propertyTraceMock)).andReturn(null);
			
			// We need to create one
			EasyMock.expect(executionTraceMock.createPropertyAccess(elementTraceMock, propertyTraceMock)).andReturn(pa);
		}

		/**
		 * @return
		 */
		private StringLiteral createLeftSideExpression(String objectRef) {
			PropertyCallExpression leftParent = new PropertyCallExpression();
			StringLiteral objectValue = new StringLiteral(objectRef);
			leftParent.setTargetExpression(objectValue);
			objectValue.setParent(leftParent);
			return objectValue;
		}

		/**
		 * @param objectValue
		 * @return
		 */
		private PropertyCallExpression createPropertyAccessExpression(StringLiteral objectValue, String propertyName) {
			PropertyCallExpression ast;
			NameExpression propertyExp = new NameExpression(propertyName);
			ast = new PropertyCallExpression(objectValue, propertyExp);
			OperationCallExpression parent = new OperationCallExpression();
			parent.setTargetExpression(ast);
			ast.setParent(parent);
			ast.setTargetExpression(objectValue);
			return ast;
		}
		

	}

	public static OperationCallExpression createOperationCallExpression(Expression targetExpression,
			String operationName, StringLiteral[] params) {
		NameExpression nameExpression = new NameExpression(operationName);			
		OperationCallExpression oce = new OperationCallExpression(targetExpression, nameExpression, params);
		return oce;
	}
}
