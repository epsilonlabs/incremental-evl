package org.eclipse.epsilon.base.incremental.exceptions.models;


public class NotInstantiableModelElementValueException extends Exception {
	
	private Object value;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7796688629472126390L;

	public NotInstantiableModelElementValueException() {
		super();
	}

	public NotInstantiableModelElementValueException(String message) {
		super(message);
	}

	public NotInstantiableModelElementValueException(String message, Object value) {
		super(message);
		this.value = value;
	}

	public NotInstantiableModelElementValueException(String message, Exception e) {
		super(message, e);
	}

	public Object getValue() {
		return value;
	}
}
