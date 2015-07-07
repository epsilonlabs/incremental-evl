package org.eclipse.epsilon.evl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.evl.trace.OrientTraceGraph;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

/**
 * Abstract base class for tests that use the OrientDB database.
 * 
 * @author Jonathan Co
 *
 */
public abstract class AbstractOrientTraceGraphTest extends
		AbstractTraceGraphTest<OrientTraceGraph> {

	public static final String URL = "memory:test";
	public static final String USER = "admin";
	public static final String PASSWORD = "admin";
//	private static final String URL = "remote:localhost/test";

	protected abstract OrientTraceGraph getGraph();

	@Override
	protected void dropGraph() {
		OrientTraceGraph graph = this.getGraph();
		ODatabaseDocumentTx db = graph.getBaseGraph().getRawGraph();
		assertTrue(db.exists());
		db.drop();
		assertFalse(db.exists());
		assertFalse(graph.isOpen());
	}

}
