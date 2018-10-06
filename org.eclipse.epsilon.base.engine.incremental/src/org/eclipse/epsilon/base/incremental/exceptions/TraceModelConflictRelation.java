package org.eclipse.epsilon.base.incremental.exceptions;

/**
 * An exception to indicate that creating a relation between two elements of the trace model would result in duplicate
 * information.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TraceModelConflictRelation extends Exception {

	public TraceModelConflictRelation(String msg) {
		super(msg);
	}

}
