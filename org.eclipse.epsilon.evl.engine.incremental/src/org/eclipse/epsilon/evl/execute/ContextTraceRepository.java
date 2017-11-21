package org.eclipse.epsilon.evl.execute;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

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
	public IContextTrace getContextFor(String typeName, IEvlModuleTrace evlModule) {
		IContextTrace result = null;
		try {
			result = extent.stream()
					.filter(ctx -> ctx.getKind().equals(typeName) && ctx.module().get().equals(evlModule))
					.findFirst()
					.get();
		} catch (NoSuchElementException e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public IElementAccess getElementAccessFor(IContextTrace context, IModelElementTrace modelElement) {
		IElementAccess result = null;
		try {
			result = extent.stream()
						.filter(ct -> ct.equals(context))
						.flatMap(ct -> ct.accesses().get().stream())
						.filter(a -> a instanceof IElementAccess)
						.map(IElementAccess.class::cast)
						.filter(et -> et.modelElement().get().equals(modelElement))
						.findFirst()
						.get();
		} catch (NoSuchElementException e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}



}
