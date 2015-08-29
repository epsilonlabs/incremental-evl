package org.eclipse.epsilon.evl.incremental.trace.orient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
import org.eclipse.epsilon.evl.incremental.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.trace.TContext;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TEvaluates;
import org.eclipse.epsilon.evl.incremental.trace.TIn;
import org.eclipse.epsilon.evl.incremental.trace.TOwns;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TRootOf;
import org.eclipse.epsilon.evl.incremental.trace.TScope;
import org.eclipse.epsilon.evl.incremental.trace.orient.OrientPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.orient.OrientPropertyAccessTraceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

public class OrientPropertyAccessTraceGraphTest {

	 private static final String URL = "memory:test";
//	private static final String URL = "remote:localhost/test";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";

	private OrientPropertyAccessTraceFactory factory = null;

	@Before
	public void setup() {
		factory = OrientPropertyAccessTraceFactory.getInstance();
	}

	@After
	public void tearDown() {
		IPropertyAccessTrace graph = factory.getTrace();
		ODatabaseDocumentTx db = ((OrientPropertyAccessTrace) graph).getBaseGraph()
				.getRawGraph();
		assertTrue(db.exists());
		db.drop();
		assertFalse(db.exists());
		assertFalse(graph.isOpen());
		factory = null;
	}
	
	@Test
	public void testVertexTypesCreated() {
		final OrientPropertyAccessTrace graph = (OrientPropertyAccessTrace) factory.getTrace();
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
		final OrientPropertyAccessTrace graph = (OrientPropertyAccessTrace) factory.getTrace();
		final OrientBaseGraph baseGraph = graph.getBaseGraph();

		assertNotNull(baseGraph.getEdgeType(TAccesses.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TEvaluates.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TIn.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TOwns.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(TRootOf.TRACE_TYPE));
	}

}
