package org.eclipse.epsilon.base.incremental.exceptions;

/**
 * An exception to signal that a searched element was not found
 * 
 * @author Horacio Hoyos
 *
 */
public class ElementNotFound extends Exception {

	public ElementNotFound() {
		super();
	}

	public ElementNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public ElementNotFound(String message) {
		super(message);
	}
}
