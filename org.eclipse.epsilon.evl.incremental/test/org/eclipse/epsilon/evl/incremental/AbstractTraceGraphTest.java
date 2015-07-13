package org.eclipse.epsilon.evl.incremental;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;
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
	
	protected static File getFile(String filename) throws URISyntaxException {
		URI binUri = AbstractTraceGraphTest.class.getResource(filename)
				.toURI();
		URI uri = null;

		if (binUri.toString().indexOf("bin") > -1) {
			uri = new URI(binUri.toString().replaceAll("bin", "test"));
		} else {
			uri = binUri;
		}

		return new File(uri);
	}

}
