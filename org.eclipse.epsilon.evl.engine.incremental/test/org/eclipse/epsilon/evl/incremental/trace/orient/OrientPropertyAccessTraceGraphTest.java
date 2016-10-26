package org.eclipse.epsilon.evl.incremental.trace.orient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintInContext;
import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;
import org.eclipse.epsilon.eol.incremental.trace.IElementOwnsProperty;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IElementRootOfScope;
import org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.IScopeConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.evl.incremental.orientdb.OrientPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.orientdb.OrientPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.TAccesses;
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
		IIncrementalTraceManager graph = factory.getTrace();
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
		OrientVertexType context = baseGraph.getVertexType(IContextTrace.TRACE_TYPE);
		assertNotNull(context);
		assertNotNull(context.getProperty(IContextTrace.NAME));

		// Constraint
		OrientVertexType constraint = baseGraph
				.getVertexType(IConstraintTrace.TRACE_TYPE);
		assertNotNull(constraint);
		assertNotNull(constraint.getProperty(IConstraintTrace.NAME));

		// Element
		OrientVertexType element = baseGraph.getVertexType(IModelElement.TRACE_TYPE);
		assertNotNull(element);
		assertNotNull(element.getProperty(IModelElement.ELEMENT_ID));

		// Property
		OrientVertexType property = baseGraph
				.getVertexType(IElementProperty.TRACE_TYPE);
		assertNotNull(property);
		assertNotNull(property.getProperty(IElementProperty.NAME));

		// Scope
		OrientVertexType scope = baseGraph.getVertexType(ITraceScope.TRACE_TYPE);
		assertNotNull(scope);
	}

	@Test
	public void testEdgeTypesCreated() {
		final OrientPropertyAccessTrace graph = (OrientPropertyAccessTrace) factory.getTrace();
		final OrientBaseGraph baseGraph = graph.getBaseGraph();

		assertNotNull(baseGraph.getEdgeType(TAccesses.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(IScopeConstraintTrace.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(IConstraintInContext.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(IElementOwnsProperty.TRACE_TYPE));
		assertNotNull(baseGraph.getEdgeType(IElementRootOfScope.TRACE_TYPE));
	}

}
