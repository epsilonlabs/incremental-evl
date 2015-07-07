package org.eclipse.epsilon.evl;

import org.eclipse.epsilon.evl.trace.TraceGraph;
import org.junit.After;

public abstract class AbstractTraceGraphTest<T extends TraceGraph> {
	
	protected abstract T getGraph();
	
	protected abstract void dropGraph();
	
	public void tearDown() {
		
	}
	
	@After
	public void doTearDown() {
		dropGraph();
		tearDown();
	}

}
