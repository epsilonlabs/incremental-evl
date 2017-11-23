package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.dom.AssignmentStatement;
import org.eclipse.epsilon.eol.dom.NameExpression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.dom.StringLiteral;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
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

	
	public static class AllInstancesInvocationExetionListenerTest extends EasyMockSupport {
		
		private final String result = "someValue";
		private final String typeName = "modelA!typeB";
		private final StringLiteral[] params = new StringLiteral[0];
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IAllInstancesInvocationRecorder recorder;
		
		private IExecutionListener listener;
		
		private OperationCallExpression ast;
		
		private NameExpression targetExpression;
		
		@Before
		public void setup() {
			IAllInstancesInvocationRecorder[] params = {recorder};
			listener = new AllInstancesInvocationExetionListener(params);
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
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.finishedExecuting(ast, result, null);
			replayAll();
			verifyAll();
		}
		
		@Test
		public void testFinishedAllInvocation() {
			NameExpression nameExpression = new NameExpression("all");
			StringLiteral[] params = new StringLiteral[0];
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(true));
					assertThat(EasyMock.getCurrentArguments()[1], is(typeName));
					System.out.println();
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}
		
		@Test
		public void testFinishedAllInstancesInvocation() {
			NameExpression nameExpression = new NameExpression("allInstances");
			StringLiteral[] params = new StringLiteral[0];
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(true));
					assertThat(EasyMock.getCurrentArguments()[1], is(typeName));
					System.out.println();
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}
		
		@Test
		public void testFinishedAllOfKindInvocation() {
			NameExpression nameExpression = new NameExpression("allOfKind");
			StringLiteral[] params = new StringLiteral[0];
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(true));
					assertThat(EasyMock.getCurrentArguments()[1], is(typeName));
					System.out.println();
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}
		
		@Test
		public void testFinishedAllOfTypeInvocation() {
			NameExpression nameExpression = new NameExpression("allOfType");
			StringLiteral[] params = new StringLiteral[0];
			ast = new OperationCallExpression(targetExpression, nameExpression, params);
			listener.finishedExecuting(ast, result, null);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(2));
					assertThat(EasyMock.getCurrentArguments()[0], is(false));
					assertThat(EasyMock.getCurrentArguments()[1], is(typeName));
					System.out.println();
					return null;
				}
			});
			replayAll();
			listener.finishedExecuting(ast, result, null);
		}
	}

	public static class PropertyAccessExecutionListenerTest extends EasyMockSupport {
		
		private final String result = "someValue";
		private final String instance = "someObject";
		private final String propertyName = "someProperty";
		
		@Rule
        public EasyMockRule rule = new EasyMockRule(this);
		
		@Mock
		private IPropertyAccessRecorder recorder;
		
		private PropertyAccessExecutionListener listener;
		
		private PropertyCallExpression ast;

		
		@Before
		public void setup() {
			IPropertyAccessRecorder[] params = {recorder};
			listener = new PropertyAccessExecutionListener(params);
			// 1. Save the leftHand side result 1st.
			PropertyCallExpression leftParent = new PropertyCallExpression();
			StringLiteral objectValue = new StringLiteral("ObjectRef");
			leftParent.setTargetExpression(objectValue);
			objectValue.setParent(leftParent);
			listener.finishedExecuting(objectValue, instance, null);
			NameExpression propertyExp = new NameExpression(propertyName);
			ast = new PropertyCallExpression(objectValue, propertyExp);
			OperationCallExpression parent = new OperationCallExpression();
			parent.setTargetExpression(ast);
			ast.setParent(parent);
			ast.setTargetExpression(objectValue);
		}
		
		@Test
		public void testFinishedExecuting() {
			ModelRepository modelRepo = new ModelRepository();
			IModel modelMock = mock(IModel.class);
			EasyMock.expect(modelMock.knowsAboutProperty(instance, propertyName)).andReturn(true).times(3);
			modelRepo.addModel(modelMock);
			IEolContext contextMock = mock(IEolContext.class);
			EasyMock.expect(contextMock.getModelRepository()).andReturn(modelRepo).times(3);
			EasyMock.replay(modelMock);
			EasyMock.replay(contextMock);
			listener.finishedExecuting(ast, result, contextMock);
			EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {

				@Override
				public Object answer() throws Throwable {
					// Verify arguments
					assertThat(EasyMock.getCurrentArguments().length, is(3));
					assertThat(EasyMock.getCurrentArguments()[0], is(EasyMock.same(modelMock)));
					assertThat(EasyMock.getCurrentArguments()[1], is(instance));
					assertThat(EasyMock.getCurrentArguments()[2], is(propertyName));
					System.out.println();
					return null;
				}
			});
			EasyMock.replay(recorder);
			listener.finishedExecuting(ast, result, contextMock);
		}
	}
}
