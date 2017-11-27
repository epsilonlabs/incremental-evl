package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import java.util.Collection;
import java.util.Set;

/**
 * Represents a collection of recordings made by a {@link IRecorder}
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <E>
 */
@Deprecated
public interface IRecordings<E> extends Collection<E> {
	
	public Collection<? extends E> all();

	public Set<? extends E> unique();

}
