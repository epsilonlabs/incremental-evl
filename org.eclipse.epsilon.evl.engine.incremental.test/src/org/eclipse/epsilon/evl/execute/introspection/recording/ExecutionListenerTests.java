package org.eclipse.epsilon.evl.execute.introspection.recording;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayDeque;
import java.util.ArrayList;

import org.eclipse.epsilon.base.incremental.IBaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.NameExpression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.dom.StringLiteral;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccessHasSatisfiedInvariants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(Suite.class)
@SuiteClasses({ ExecutionListenerTests.SatisfiesInvocationExecutionListenerTest.class })
public class ExecutionListenerTests {

	public static class SatisfiesInvocationExecutionListenerTest {

		private final boolean result = true;
		private final String typeName = "modelA!typeB";

		@Rule
		public MockitoRule mockitoRule = MockitoJUnit.rule();

		@Mock
		private IIncrementalBaseContext contextMock;

		private SatisfiesInvocationExecutionListener listener;

		private OperationCallExpression ast;

		private NameExpression targetExpression;

		@Before
		public void setup() {
			listener = new SatisfiesInvocationExecutionListener();
			targetExpression = new NameExpression(typeName);
			EolCompilationContext contextMock = new EolCompilationContext();
			targetExpression.compile(contextMock);
		}

		@Test
		public void testFinishedNotAnOperationCallExpression() {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setModuleElementTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute other expression
			ModuleElement me = mock(ModuleElement.class);
			listener.finishedExecuting(me, result, null);
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
		}

		@Test
		public void testFinishedNotSatisfiesOperationCallExpression() {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setModuleElementTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute a non-satisfies operation
			ast = ExecutionListenerTests.getOrCreateOperationCallExpression(targetExpression, "getOne",
					new StringLiteral[0]);
			listener.finishedExecuting(ast, result, null);
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
		}

		@Test
		public void testSatisfiesInvocation() throws Exception {
			StringLiteral[] params = new StringLiteral[1];
			String targetInvariant = "IsNamed";
			params[0] = new StringLiteral(targetInvariant);

			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setModuleElementTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);

			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);

			// 3. Execute a Satisfies operation
			ast = ExecutionListenerTests.getOrCreateOperationCallExpression(targetExpression,
					EvlOperationFactory.SATISFIES_OPERATION, params);
			// 4a. About to execute operation to register for argument resolution
			listener.aboutToExecute(ast, contextMock);
			// Test
			// 4b. Execute parameters
			listener.finishedExecuting(params[0], targetInvariant, contextMock);
			// 5. Execute operation
			listener.finishedExecuting(ast, result, contextMock);

			// 6. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
		}

		@Test
		public void testSatisfiesOneInvocation() throws Exception {
			StringLiteral[] params = new StringLiteral[2];
			String isNamed = "IsNamed";
			String isTyped = "IsTyped";
			params[0] = new StringLiteral(isNamed);
			params[1] = new StringLiteral(isTyped);

			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setModuleElementTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);

			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);

			// 3. Execute a Satisfies operation
			ast = ExecutionListenerTests.getOrCreateOperationCallExpression(targetExpression,
					EvlOperationFactory.SATISFIES_ONE_OPERATION, params);
			// 4a. About to execute operation to register for argument resolution
			listener.aboutToExecute(ast, contextMock);
			// Test
			// 4b. Execute parameters
			listener.finishedExecuting(params[0], isNamed, contextMock);
			listener.finishedExecuting(params[1], isTyped, contextMock);

			// 5. Execute operation
			listener.finishedExecuting(ast, result, contextMock);

			// 6. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
		}

		@Test
		public void testSatisfiesAllInvocation() throws Exception {
			StringLiteral[] params = new StringLiteral[2];
			String isNamed = "IsNamed";
			String isTyped = "IsTyped";
			params[0] = new StringLiteral(isNamed);
			params[1] = new StringLiteral(isTyped);

			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setModuleElementTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);

			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);

			// 3. Execute a Satisfies operation
			ast = ExecutionListenerTests.getOrCreateOperationCallExpression(targetExpression,
					EvlOperationFactory.SATISFIES_ALL_OPERATION, params);
			// 4a. About to execute operation to register for argument resolution
			listener.aboutToExecute(ast, contextMock);
			// Test
			// 4b. Execute parameters
			listener.finishedExecuting(params[0], isNamed, contextMock);
			listener.finishedExecuting(params[1], isTyped, contextMock);

			// 5. Execute operation
			listener.finishedExecuting(ast, result, contextMock);

			// 6. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			assertTrue(listener.done());
		}

		private void recordExecutionTrace(StringLiteral[] params, IInvariantTrace currentInvariantMock)
				throws Exception {
			IContextTrace contextMock = mock(IContextTrace.class);
			IInvariantTraceHasInvariantContext invariantHasContextMock = mock(IInvariantTraceHasInvariantContext.class);
			IContextTraceHasConstraints contextHasInvariants = mock(IContextTraceHasConstraints.class);
			IInvariantTrace targetInvariantMock = mock(IInvariantTrace.class);
			ISatisfiesAccess satisfiesMock = mock(ISatisfiesAccess.class);
			ISatisfiesAccessHasSatisfiedInvariants satisfiesHasSatisfiedInvariantMock = mock(
					ISatisfiesAccessHasSatisfiedInvariants.class);

			when(invariantHasContextMock.get()).thenReturn(contextMock);
			when(currentInvariantMock.invariantContext()).thenReturn(invariantHasContextMock);
			when(contextMock.constraints()).thenReturn(contextHasInvariants); // .times(params.length);
			when(contextHasInvariants.get()).thenReturn(new ArrayList<IInvariantTrace>().iterator()); // .times(params.length);
			for (StringLiteral p : params) {
				// when(evlExecutionMock.getOrCreateInvariantTrace(p.getValue())).andReturn(targetInvariantMock);
				when(satisfiesMock.satisfiedInvariants()).thenReturn(satisfiesHasSatisfiedInvariantMock);
			}
			// when(evlExecutionMock.getOrCreateSatisfiesTrace(currentInvariantMock)).andReturn(satisfiesMock);
		}

	}

	public static OperationCallExpression getOrCreateOperationCallExpression(Expression targetExpression,
			String operationName, StringLiteral[] params) {
		NameExpression nameExpression = new NameExpression(operationName);
		OperationCallExpression oce = new OperationCallExpression(targetExpression, nameExpression, params);
		return oce;
	}

}
