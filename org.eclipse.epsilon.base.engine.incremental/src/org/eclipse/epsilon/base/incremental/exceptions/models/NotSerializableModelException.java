package org.eclipse.epsilon.base.incremental.exceptions.models;

/**
 * An exception to indicate that the model or its elements are not serializable and hence the
 * values of properties can not be stored in the trace model
 * @author Horacio Hoyos Rodriguez
 *
 */
public class NotSerializableModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3234644670548582148L;

	public NotSerializableModelException() {
		super();
	}

	public NotSerializableModelException(String message) {
		super(message);
	}
	
	

}
