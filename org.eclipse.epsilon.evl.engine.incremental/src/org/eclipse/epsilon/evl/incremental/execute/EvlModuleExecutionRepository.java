package org.eclipse.epsilon.evl.incremental.execute;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

public class EvlModuleExecutionRepository implements IEvlExecutionTraceRepository {
	
	private final Set<IExecutionTrace> extent;
	
	public EvlModuleExecutionRepository() {
		this(false);
	}
	
	public EvlModuleExecutionRepository(boolean inParallel) {
//		if (inParallel) {
//			this.extent = ConcurrentHashMap.newKeySet();
//		}
//		else {
			this.extent = new LinkedHashSet<>();
//		}
	}
	

	@Override
	public IExecutionTrace get(Object id) {
		IExecutionTrace result = null;
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
	public void add(IExecutionTrace item) {
		extent.add(item);
	}

	@Override
	public void remove(IExecutionTrace item) {
		extent.remove(item);
	}

	@Override
	public IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace,
			IModelElementTrace modelElement) {
		
		IContextTrace result = null;
		// FIXME We need the model to create the model, element and elementAccess value objects,
		// Probably this should be factory methods in the IncrementalModel!
		result = extent.stream()
				.filter(IContextTrace.class::isInstance)
				.map(IContextTrace.class::cast)
				.filter(ct -> ct.getKind().equals(typeName) && ct.getIndex().equals(index) && ct.module().get().equals(moduleTrace))
				.findFirst()
				.orElseGet(() -> null);
		return result;
	}

	@Override
	public IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace) {
		IContextTrace result = null;
		result = extent.stream()
				.filter(IContextTrace.class::isInstance)
				.map(IContextTrace.class::cast)
				.filter(ct -> ct.getKind().equals(typeName) &&
							  ct.getIndex().equals(index) &&
							  ct.module().get().equals(moduleTrace))
				.findFirst()
				.orElseGet(() -> null);
		return result;
	}
	
	@Override
	public List<IExecutionTrace> findExecutionTraces(String objectId, String propertyName) {
		// TODO Implement IEvlExecutionTraceRepository.findExecutionTraces
		throw new UnsupportedOperationException("Unimplemented Method    IEvlExecutionTraceRepository.findExecutionTraces invoked.");
	}


	
//	@Override
//	public IContextTrace getContextTraceFor(String typeName, int index, IModelElementTrace modelElement) {
//		IContextTrace result = null;
//		result = extent.stream()
//				.filter(ct -> ct.getKind().equals(typeName) && ct.getIndex().equals(index) && ct.context().get().equals(modelElement))
//				.findFirst()
//				.orElseGet(() -> null);
//		return result;
//	}
	
//	
//
//	@Override
//	public IEvlModuleExecution getEvlModuleExecutionForSource(String string) {
//		IEvlModuleExecution result = null;
//		try {
//			result = extent.stream()
//						.filter(me -> (me.module().get().getSource() != null) &&
//									(me.module().get().getSource().equals(string)))
//						.findFirst()
//						.get();
//		} catch (NoSuchElementException e) {
//			// No info
//			// FIXME Should we go to the DB here?
//		}
//		return result;
//	}
//
//	@Override
//	public IPropertyAccess getPropertyAccessFor(IExecutionTrace executionTrace, IPropertyTrace property) {
//		IPropertyAccess result = null;
//		try {
//			result = extent.stream()
//				.flatMap(me -> me.executions().get().stream())
//				.filter(et -> et.equals(executionTrace))
//				.flatMap(gt -> gt.accesses().get().stream())
//				.filter(a -> a instanceof IPropertyAccess)
//				.map(IPropertyAccess.class::cast)
//				.filter(pa -> pa.property().get().equals(property))
//				.findFirst()
//				.get();
//		} catch (NoSuchElementException e) {
//			// No info
//			// FIXME Should we go to the DB here?
//		}
//		return result;
//	}
//
//	@Override
//	public IAllInstancesAccess getAllInstancesAccessFor(IExecutionTrace executionTrace, IModelTypeTrace modelType) {
//		IAllInstancesAccess result = null;
//		try {
//			result = extent.stream()
//				.flatMap(me -> me.executions().get().stream())
//				.filter(et -> et.equals(executionTrace))
//				.flatMap(gt -> gt.accesses().get().stream())
//				.filter(a -> a instanceof IAllInstancesAccess)
//				.map(IAllInstancesAccess.class::cast)
//				.filter(aa -> aa.type().get().equals(modelType))
//				.findFirst()
//				.get();
//		} catch (NoSuchElementException e) {
//			// No info
//			// FIXME Should we go to the DB here?
//		}
//		return result;
//	}

}
