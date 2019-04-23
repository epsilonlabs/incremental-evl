package org.eclipse.epsilon.base.incremental.execute;

/**
 * Different execution modes for incremental modules.
 * @author Horacio Hoyos
 *
 */
public enum ExecutionMode {
		offline,	// Execute all
		online;		// i.e. listening to model changes
	}