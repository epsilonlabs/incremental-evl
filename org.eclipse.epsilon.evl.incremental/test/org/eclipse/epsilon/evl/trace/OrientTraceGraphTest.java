package org.eclipse.epsilon.evl.trace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.epsilon.evl.AbstractOrientTraceGraphTest;
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

	@Test
	public void testGetContextDoesNotExist() {
		final String contextName = "testGetContextDoesNotExist";
		assertNull(this.graph.getContext(contextName));
	}
	
	@Test
	public void testGetContext() {
		final String contextName = "testGetContext";
		assertNull(this.graph.getContext(contextName));
		this.graph.createContext(contextName);
		final TContext get = this.graph.getContext(contextName);
		assertNotNull(get);
		assertEquals(get.getName(), contextName);
	}

	@Test
	public void testCreateContext() {
		final String contextName = "testCreateContext";
		assertNull(this.graph.getContext(contextName));
		final TContext create = this.graph.createContext(contextName);
		assertNotNull(create);
		assertEquals(create.getName(), contextName);
	}

	@Override
	protected OrientTraceGraph getGraph() {
		return this.graph;
	}

}
