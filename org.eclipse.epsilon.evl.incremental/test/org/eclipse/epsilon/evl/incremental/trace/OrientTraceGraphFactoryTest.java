package org.eclipse.epsilon.evl.incremental.trace;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

public class OrientTraceGraphFactoryTest {

	 private static final String URL = "memory:test";
//	private static final String URL = "remote:localhost/test";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";

	private OrientTraceGraphFactory factory = null;

	@Before
	public void setup() {
		factory = OrientTraceGraphFactory.getInstance();
	}

	@After
	public void tearDown() {
		TraceGraph graph = factory.getGraph();
		ODatabaseDocumentTx db = ((OrientTraceGraph) graph).getBaseGraph()
				.getRawGraph();
		assertTrue(db.exists());
		db.drop();
		assertFalse(db.exists());
		assertFalse(graph.isOpen());
		factory = null;
	}
	
	@Test
	public void testVertexTypesCreated() {
		final OrientTraceGraph graph = (OrientTraceGraph) factory.getGraph();
		final OrientBaseGraph baseGraph = graph.getBaseGraph();

		// Context
		OrientVertexType context = baseGraph.getVertexType(TContext.TRACE_TYPE);
		assertNotNull(context);
		assertNotNull(context.getProperty(TContext.NAME));

		// Constraint
		OrientVertexType constraint = baseGraph
				.getVertexType(TConstraint.TRACE_TYPE);
		assertNotNull(constraint);
		assertNotNull(constraint.getProperty(TConstraint.NAME));

		// Element
		OrientVertexType element = baseGraph.getVertexType(TElement.TRACE_TYPE);
		assertNotNull(element);
		assertNotNull(element.getProperty(TElement.ELEMENT_ID));

		// Property
		OrientVertexType property = baseGraph
				.getVertexType(TProperty.TRACE_TYPE);
		assertNotNull(property);
		assertNotNull(property.getProperty(TProperty.NAME));

		// Scope
		OrientVertexType scope = baseGraph.getVertexType(TScope.TRACE_TYPE);
		assertNotNull(scope);
	}

	@Test
	public void testEdgeTypesCreated() {
		final OrientTraceGraph graph = (OrientTraceGraph) factory.getGraph();
		final OrientBaseGraph baseGraph = graph.getBaseGraph();

		assertNotNull(baseGraph.getEdgeType(TAccesses.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TEvaluates.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TIn.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TOwns.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TRootOf.TRACE_TYPE));
	}

}
