package org.eclipse.epsilon.evl.incremental;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.evl.incremental.orientdb.OrientPropertyAccessTrace;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

/**
 * Abstract base class for tests that use the OrientDB database.
 * 
 * @author Jonathan Co
 *
 */
public abstract class AbstractOrientPropertyAccessTraceTest extends
		AbstractPropertyAccessTraceTest<OrientPropertyAccessTrace> {

	public static final String URL = "memory:test";
	public static final String USER = "admin";
	public static final String PASSWORD = "admin";
//	private static final String URL = "remote:localhost/test";

	protected abstract OrientPropertyAccessTrace getGraph();

	@Override
	protected void dropGraph() {
		OrientPropertyAccessTrace graph = this.getGraph();
		ODatabaseDocumentTx db = graph.getBaseGraph().getRawGraph();
		assertTrue(db.exists());
		db.drop();
		assertFalse(db.exists());
		assertFalse(graph.isOpen());
	}

}
