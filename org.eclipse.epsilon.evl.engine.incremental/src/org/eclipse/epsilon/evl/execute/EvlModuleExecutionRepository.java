package org.eclipse.epsilon.evl.execute;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

public class EvlModuleExecutionRepository implements IEvlModuleExecutionRepository {
	
	private final Set<IEvlModuleExecution> extent;

	public EvlModuleExecutionRepository(boolean inParallel) {
		if (inParallel) {
			this.extent = ConcurrentHashMap.newKeySet();
		}
		else {
			this.extent = new LinkedHashSet<>();
		}
	}

	@Override
	public IEvlModuleExecution get(Object id) {
		IEvlModuleExecution result = null;
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
	public void add(IEvlModuleExecution item) {
		extent.add(item);
	}

	@Override
	public void remove(IEvlModuleExecution item) {
		extent.remove(item);
	}

	@Override
	public IEvlModuleExecution getEvlModuleExecutionForSource(String string) {
		IEvlModuleExecution result = null;
		try {
			result = extent.stream()
						.filter(me -> (me.module().get().getSource() != null) &&
									(me.module().get().getSource().equals(string)))
						.findFirst()
						.get();
		} catch (NoSuchElementException e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public IPropertyAccess getPropertyAccessFor(IExecutionTrace executionTrace, IPropertyTrace property) {
		IPropertyAccess result = null;
		try {
			result = extent.stream()
				.flatMap(me -> me.executions().get().stream())
				.filter(et -> et.equals(executionTrace))
				.flatMap(gt -> gt.accesses().get().stream())
				.filter(a -> a instanceof IPropertyAccess)
				.map(IPropertyAccess.class::cast)
				.filter(pa -> pa.property().get().equals(property))
				.findFirst()
				.get();
		} catch (NoSuchElementException e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}

	@Override
	public IAllInstancesAccess getAllInstancesAccessFor(IExecutionTrace executionTrace, IModelTypeTrace modelType) {
		IAllInstancesAccess result = null;
		try {
			result = extent.stream()
				.flatMap(me -> me.executions().get().stream())
				.filter(et -> et.equals(executionTrace))
				.flatMap(gt -> gt.accesses().get().stream())
				.filter(a -> a instanceof IAllInstancesAccess)
				.map(IAllInstancesAccess.class::cast)
				.filter(aa -> aa.type().get().equals(modelType))
				.findFirst()
				.get();
		} catch (NoSuchElementException e) {
			// No info
			// FIXME Should we go to the DB here?
		}
		return result;
	}

}
