package org.eclipse.epsilon.evl.execute.introspection.recording;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.dom.NameExpression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.dom.StringLiteral;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	ExecutionListenerTests.SatisfiesInvocationExecutionListenerTest.class})
public class ExecutionListenerTests {

	
	public static class SatisfiesInvocationExecutionListenerTest extends EasyMockSupport {
		
		private final boolean result = true;
		private final String typeName = "modelA!typeB";

		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private ISatisfiesInvocationRecorder recorder;
		
		private SatisfiesInvocationExecutionListener listener;
		
		private OperationCallExpression ast;
		
		private NameExpression targetExpression;
		
		@Before
		public void setup() {
			listener = new SatisfiesInvocationExecutionListener();
			listener.getRecorders().add(recorder);
			targetExpression = new NameExpression(typeName);
			EolCompilationContext contextMock = new EolCompilationContext();
			targetExpression.compile(contextMock);
		}
		
		@Test
		public void testFinishedNotAnOperationCallExpression() {
			ModuleElement me = mock(ModuleElement.class);
			listener.finishedExecuting(me, result, null);
			replayAll();
			verifyAll();
		}
		
		@Test
		public void testFinishedNotAnALlOperationCallExpression() {
			NameExpression nameExpression = new NameExpression("some");
			StringLiteral[] params = new StringLiteral[0];
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.aboutToExecute(ast, null);
			listener.finishedExecuting(ast, result, null);
			replayAll();
			verifyAll();
		}
		
		@Test
		public void testSatisfiesInvocation() {
			NameExpression nameExpression = new NameExpression(EvlOperationFactory.SATISFIES_OPERATION);
			StringLiteral[] params = new StringLiteral[1];
			String targetInvariant = "IsNamed";
			params[0] = new StringLiteral(targetInvariant);
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.aboutToExecute(ast, null);
			listener.finishedExecuting(params[0], targetInvariant, null);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(false));
					List<String> targets = (List<String>) EasyMock.getCurrentArguments()[1];
					assertThat(targets, contains(targetInvariant));
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
			verifyAll();
		}
		
		@Test
		public void testSatisfiesOneInvocation() {
			NameExpression nameExpression = new NameExpression(EvlOperationFactory.SATISFIES_ONE_OPERATION);
			StringLiteral[] params = new StringLiteral[2];
			String isNamed = "IsNamed";
			String isTyped = "IsTyped";
			params[0] = new StringLiteral(isNamed);
			params[1] = new StringLiteral(isTyped);
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.aboutToExecute(ast, null);
			listener.finishedExecuting(params[0], isNamed, null);
			listener.finishedExecuting(params[1], isTyped, null);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@SuppressWarnings("unchecked")
				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(false));
					List<String> targets = (List<String>) EasyMock.getCurrentArguments()[1];
					assertThat(targets, contains(isNamed, isTyped));
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}
		
		@Test
		public void testSatisfiesAllInvocation() {
			NameExpression nameExpression = new NameExpression(EvlOperationFactory.SATISFIES_ALL_OPERATION);
			StringLiteral[] params = new StringLiteral[2];
			String isNamed = "IsNamed";
			String isTyped = "IsTyped";
			params[0] = new StringLiteral(isNamed);
			params[1] = new StringLiteral(isTyped);
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.aboutToExecute(ast, null);
			listener.finishedExecuting(params[0], isNamed, null);
			listener.finishedExecuting(params[1], isTyped, null);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@SuppressWarnings("unchecked")
				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(true));
					List<String> targets = (List<String>) EasyMock.getCurrentArguments()[1];
					assertThat(targets, contains(isNamed, isTyped));
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}

	}

	
}
