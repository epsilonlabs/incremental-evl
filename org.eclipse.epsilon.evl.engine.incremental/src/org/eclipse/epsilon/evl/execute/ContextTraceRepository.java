package org.eclipse.epsilon.evl.execute;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;

public class ContextTraceRepository implements IContextTraceRepository {
	
	private final Set<IContextTrace> extent;
	
	public ContextTraceRepository(boolean inParallel) {
		if (inParallel) {
			this.extent = ConcurrentHashMap.newKeySet();
		}
		else {
			this.extent = new LinkedHashSet<>();
		}
	}

	@Override
	public IContextTrace get(Object id) {
		IContextTrace result = null;
		try {
			result = extent.stream()
					.filter(mt -> mt.getId().equals(id))
					.findFirst()
					.get();
		} catch (NoSuchElementException  e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public void add(IContextTrace item) {
		extent.add(item);
	}

	@Override
	public void remove(IContextTrace item) {
		extent.remove(item);
	}

	@Override
	public IContextTrace getContextTraceFor(String typeName, int index, IModelElementTrace modelElement) {
		IContextTrace result = null;
		result = extent.stream()
				.filter(ct -> ct.getKind().equals(typeName) && ct.getIndex().equals(index) && ct.context().get().equals(modelElement))
				.findFirst()
				.orElseGet(() -> null);
		return result;
	}
}
