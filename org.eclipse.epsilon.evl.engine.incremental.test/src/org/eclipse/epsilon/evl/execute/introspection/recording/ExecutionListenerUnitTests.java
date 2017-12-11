package org.eclipse.epsilon.evl.execute.introspection.recording;

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
import org.eclipse.epsilon.eol.dom.StringLiteral;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.evl.dom.TracedConstraint;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTraceHasConstraints;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasInvariant;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTraceHasSatisfiedInvariants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	ExecutionListenerUnitTests.SatisfiesInvocationExecutionListenerTest.class})
public class ExecutionListenerUnitTests {
	
	private interface TestModuleExecution extends IModuleExecution { }
	
	public static class SatisfiesInvocationExecutionListenerTest extends EasyMockSupport {
		
		private final boolean result = true;
		private final String typeName = "modelA!typeB";

		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IEolExecutionTraceManager<TestModuleExecution> traceManagerMock;
		@Mock
		private IEvlModuleExecution evlExecutionMock;
		@Mock
		private IEolContext contextMock;
		
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
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute other expression
			ModuleElement me = mock(ModuleElement.class);
			listener.finishedExecuting(me, result, null);
			replayAll();
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			verifyAll();
		}
		
		@Test
		public void testFinishedNotSatisfiesOperationCallExpression() {
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			// 2. Execute a non-satisfies operation
			ast = ExecutionListenerUnitTests.createOperationCallExpression(targetExpression, "getOne", new StringLiteral[0]);
			listener.finishedExecuting(ast, result, null);
			replayAll();
			// 3. Finish executing block
			listener.finishedExecuting(blockMock, result, contextMock);
			verifyAll();
		}
		
		@Test
		public void testSatisfiesInvocation() throws Exception {
			StringLiteral[] params = new StringLiteral[1];
			String targetInvariant = "IsNamed";
			params[0] = new StringLiteral(targetInvariant);
			
			// 1. Trigger listener by executing a TracedExecutableBlock
			TracedConstraint blockMock = new TracedConstraint();
			IInvariantTrace executionTraceMock = mock(IInvariantTrace.class);
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);
			replayAll();
			
			// 3. Execute a Satisfies operation
			ast = ExecutionListenerUnitTests.createOperationCallExpression(targetExpression, EvlOperationFactory.SATISFIES_OPERATION, params);
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
			verifyAll();
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
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);
			replayAll();
			
			// 3. Execute a Satisfies operation
			ast = ExecutionListenerUnitTests.createOperationCallExpression(targetExpression, EvlOperationFactory.SATISFIES_ONE_OPERATION, params);
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
			verifyAll();
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
			blockMock.setTrace(executionTraceMock);
			listener.aboutToExecute(blockMock, contextMock);
			
			// 2. Record invocations
			recordExecutionTrace(params, executionTraceMock);
			replayAll();
			
			// 3. Execute a Satisfies operation
			ast = ExecutionListenerUnitTests.createOperationCallExpression(targetExpression, EvlOperationFactory.SATISFIES_ALL_OPERATION, params);
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
			verifyAll();
		}
		
		private void recordExecutionTrace(StringLiteral[] params, IInvariantTrace currentInvariantMock) throws Exception {
			IContextTrace contextMock = mock(IContextTrace.class);
			IInvariantTraceHasInvariantContext invariantHasContextMock = mock(IInvariantTraceHasInvariantContext.class);
			IContextTraceHasConstraints contextHasInvariants = mock(IContextTraceHasConstraints.class);
			IInvariantTrace targetInvariantMock = mock(IInvariantTrace.class);
			ISatisfiesTrace satisfiesMock = niceMock(ISatisfiesTrace.class);
			ISatisfiesTraceHasSatisfiedInvariants satisfiesHasSatisfiedInvariantMock = niceMock(ISatisfiesTraceHasSatisfiedInvariants.class);
			
			EasyMock.expect(invariantHasContextMock.get()).andReturn(contextMock);
			EasyMock.expect(currentInvariantMock.invariantContext()).andReturn(invariantHasContextMock);
			EasyMock.expect(contextMock.constraints()).andReturn(contextHasInvariants).times(params.length);
			EasyMock.expect(contextHasInvariants.get()).andReturn(new ArrayDeque<>()).times(params.length);
			for (StringLiteral p : params) {
				EasyMock.expect(evlExecutionMock.createInvariantTrace(p.getValue())).andReturn(targetInvariantMock);
				EasyMock.expect(satisfiesMock.satisfiedInvariants()).andReturn(satisfiesHasSatisfiedInvariantMock);
			}
			EasyMock.expect(evlExecutionMock.createSatisfiesTrace(currentInvariantMock)).andReturn(satisfiesMock);
		}

	}
	
	public static OperationCallExpression createOperationCallExpression(Expression targetExpression,
			String operationName, StringLiteral[] params) {
		NameExpression nameExpression = new NameExpression(operationName);			
		OperationCallExpression oce = new OperationCallExpression(targetExpression, nameExpression, params);
		return oce;
	}
	
}
