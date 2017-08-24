package org.eclipse.epsilon.eol.engine.incremental;


public class EOLIncrementalExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474163903079549097L;

	
	public EOLIncrementalExecutionException(String string, Exception e) {
		super(string, e);
	}


	public EOLIncrementalExecutionException(String string) {
		super(string);
	}

	
}
