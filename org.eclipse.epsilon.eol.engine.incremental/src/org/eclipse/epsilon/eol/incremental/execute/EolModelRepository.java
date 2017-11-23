package org.eclipse.epsilon.eol.incremental.execute;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;

/**
 * Implementation of the {@link IModelRepository} that only accesses elements in memory.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class EolModelRepository implements IModelRepository {
	
	private final Set<IModelTrace> extent;
	
	public EolModelRepository(boolean inParallel) {
		if (inParallel) {
			this.extent = ConcurrentHashMap.newKeySet();
		}
		else {
			this.extent = new LinkedHashSet<>();
		}
	}

	@Override
	public IModelTrace get(Object id) {
		IModelTrace result = null;
		try {
			result = extent.stream()
					.filter(mt -> mt.getId().equals(id))
					.findFirst()
					.get();
		} catch (NoSuchElementException  e) {
			// No info about the ModelTrace
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public void add(IModelTrace item) {
		extent.add(item);
	}

	@Override
	public void remove(IModelTrace item) {
		extent.remove(item);
	}

	@Override
	public IModelTrace getModelTraceByName(String name) {
		IModelTrace result = null;
		try {
			result = extent.stream()
					.filter(mt -> mt.getName().equals(name))
					.findFirst()
					.get();
		} catch (NoSuchElementException  e) {
			// No info about the ModelTrace
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public IModelTrace first() {
		IModelTrace result = null;
		try {
			result = extent.stream()
					.findFirst()
					.get();
		} catch (NoSuchElementException  e) {
			// No info about the ModelTrace
			// FIXME Should we go to the DB here?
		}
		return result;
	}

}
