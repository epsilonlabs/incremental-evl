package org.eclipse.epsilon.eol.engine.incremental.trace;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.eclipse.epsilon.eol.incremental.trace.Access;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.AllInstancesAccessHasType;
import org.eclipse.epsilon.eol.incremental.trace.Execution;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccessHasTypeImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.AllInstancesAccessImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TraceModelTests.AllInstancesAccessTests.class})
public class TraceModelTests {
	
	public static class AllInstancesAccessTests extends EasyMockSupport {
		
		@Rule
	    public EasyMockRule rule = new EasyMockRule(this);

	    @Mock
	    private Execution executionMock1;
	    
	    @Mock
	    private Execution executionMock2;
	    
	    @Mock
	    private ModelType modelTypeMock1;
	    
	    @Mock
	    private ModelType modelTypeMock2;
	    
	    
	    private ExecutionHasAccesses executionHasAccessesMock;
	    private ExecutionHasAccesses executionHasAccessesMock2;
	    private AllInstancesAccess classUnderTest;
	   
		@Test
		public void testAllInstancesAccessInstantiation() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(5);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			assertThat(classUnderTest.execution().get(), is(executionMock1));
			Queue<Access> values = classUnderTest.execution().get().accesses().get();
			assertThat(values, hasItem(classUnderTest));
		}
		
		@Test
		public void testAllInstancesAccessAttributes() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(4);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			boolean isKindOf = true;
			classUnderTest.setOfKind(isKindOf);
			assertTrue(classUnderTest.getOfKind());
			isKindOf = false;
			classUnderTest.setOfKind(isKindOf);
			assertFalse(classUnderTest.getOfKind());
			Object id = "id";
			classUnderTest.setId(id);
			assertThat(classUnderTest.getId(), is(id));
		}
		
		@Test
		public void testAllInstancesAccessCreateExecutionConflict() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(4);
			replay(executionMock1);
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			executionHasAccessesMock2 = new ExecutionHasAccessesImpl(executionMock2);
			expect(executionMock2.accesses()).andReturn(executionHasAccessesMock2).times(3);
			replay(executionMock2);
			boolean result = classUnderTest.execution().create(executionMock2);
			assertFalse(result);

		}
		
		@Test
		public void testAllInstancesAccessDestroyExecution() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(6);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			boolean result = classUnderTest.execution().destroy(executionMock1);
			assertTrue(result);

		}
		
		@Test
		public void testAllInstancesAccessDestroyAndCreateExecution() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(6);
			executionHasAccessesMock2 = new ExecutionHasAccessesImpl(executionMock2);
			expect(executionMock2.accesses()).andReturn(executionHasAccessesMock2).times(5);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			boolean result = classUnderTest.execution().destroy(executionMock1);
			assertTrue(result);
			result = classUnderTest.execution().create(executionMock2);
			assertTrue(result);
			result = classUnderTest.execution().create(executionMock2);
			assertTrue(result);
		}
		
		@Test
		public void testAllInstancesAccessCreateModelType() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(4);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			boolean result = classUnderTest.type().create(modelTypeMock1);
			assertTrue(result);
			result = classUnderTest.type().create(modelTypeMock2);
			assertTrue(result);
			result = classUnderTest.type().create(modelTypeMock1);
			assertTrue(result);

		}
		
		@Test
		public void testAllInstancesAccessDestroyModelType() {
			executionHasAccessesMock = new ExecutionHasAccessesImpl(executionMock1);
			expect(executionMock1.accesses()).andReturn(executionHasAccessesMock).times(4);
			replayAll();
			classUnderTest = new AllInstancesAccessImpl(executionMock1);
			classUnderTest.type().create(modelTypeMock1);
			boolean result = classUnderTest.type().destroy(modelTypeMock1);
			assertTrue(result);
			assertThat(classUnderTest.type().get(), not(hasItem(modelTypeMock1)));
			result = classUnderTest.type().destroy(modelTypeMock2);
			assertFalse(result);
		}

	}

}
