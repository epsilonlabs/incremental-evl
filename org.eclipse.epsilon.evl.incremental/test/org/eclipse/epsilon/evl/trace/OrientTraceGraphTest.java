package org.eclipse.epsilon.evl.trace;

import static org.junit.Assert.*;

import org.eclipse.epsilon.evl.AbstractOrientTraceGraphTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrientTraceGraphTest extends AbstractOrientTraceGraphTest {

	private OrientTraceGraphFactory factory = null;
	private OrientTraceGraph graph;

	@Before
	public void setup() {
		this.factory = new OrientTraceGraphFactory(
				AbstractOrientTraceGraphTest.URL,
				AbstractOrientTraceGraphTest.USER,
				AbstractOrientTraceGraphTest.PASSWORD);
		this.graph = this.factory.getGraph();
	}

	@Override
	public void tearDown() {
		this.factory = null;
		this.graph = null;
	}
	
	@Override
	protected OrientTraceGraph getGraph() {
		return this.graph;
	}

	@Test
	public void testOrientTraceGraph() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateContext() {
		final String contextName = "testCreateContext";
		assertNull(this.graph.getContext(contextName));
		
		final TContext create = this.graph.createContext(contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getName());
	}
	
	@Test
	public void testCreateContextDuplicate() {
		final String contextName = "testCreateContext";
		assertNull(this.graph.getContext(contextName));
		
		final TContext first = this.graph.createContext(contextName);
		final Object id = first.asVertex().getId();
		assertNotNull(first);
		assertEquals(contextName, first.getName());
		
		final TContext second = this.graph.createContext(contextName);
		assertNotNull(second);
		assertEquals(contextName, first.getName());
		assertEquals(id, second.asVertex().getId());
	}

	@Test
	public void testCreateConstraintStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateConstraintStringTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateScopeStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateScopeStringTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateScopeTElementStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateScopeTElementTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePropertyStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePropertyStringTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContext() {
		final String contextName = "testGetContext";
		assertNull(this.graph.getContext(contextName));
		
		this.graph.createContext(contextName);
		final TContext get = this.graph.getContext(contextName);
		
		assertNotNull(get);
		assertEquals(contextName, get.getName());
	}
	
	@Test
	public void testGetContextDoesNotExist() {
		final String contextName = "testGetContextDoesNotExist";
		assertNull(this.graph.getContext(contextName));
	}

	@Test
	public void testGetConstraintStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConstraintStringTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeStringTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeTElementStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeTElementTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPropertyStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPropertyStringTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllContexts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllConstraints() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllElements() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllScopes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopesInTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopesInString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveContextString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveContextTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintStringTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveElementTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveElementString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTScope() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeStringTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTElementStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTElementTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyTProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyStringTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBaseGraph() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOpen() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommit() {
		fail("Not yet implemented");
	}

	@Test
	public void testShutdown() {
		fail("Not yet implemented");
	}

}
