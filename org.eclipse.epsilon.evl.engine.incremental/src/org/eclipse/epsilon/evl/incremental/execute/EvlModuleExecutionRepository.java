package org.eclipse.epsilon.evl.incremental.execute;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//FIXME We need to instrument this class to measure times/memory of all the query operations
// Probably provided an abstract class that does the instrumentation and then subclasses that 
// provide the specific implementations. 
public class EvlModuleExecutionRepository implements IEvlExecutionTraceRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(EvlModuleExecutionRepository.class);
	
	private final Set<IModuleElementTrace> extent;
	
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
	public IModuleElementTrace get(Object id) {
		logger.debug("Get ModuleElementTrace with id:{}", id);
		IModuleElementTrace result = null;
		try {
			result = extent.stream()
					.filter(mt -> mt.getId().equals(id))
					.findFirst()
					.get();
		} catch (NoSuchElementException  e) {
			// No info about the ModelTrace
			// FIXME Should we go to the DB here?
			logger.info("ModuleElementTrace not found in extent, delegating to DB?");
		}
		return result;
	}

	@Override
	public boolean add(IModuleElementTrace item) {
		return extent.add(item);
	}

	@Override
	public boolean remove(IModuleElementTrace item) {
		return extent.remove(item);
	}

	@Override
	public IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace,
			IExecutionContext exContext) {
		
		logger.debug("Get ContextTraceFor type:{}, index:{} with ", typeName, index);
		IContextTrace result = null;
		// FIXME We need the model to create the model, element and elementAccess value objects,
		// Probably this should be factory methods in the IncrementalModel!
		result = extent.stream()
				.filter(IContextTrace.class::isInstance)
				.map(IContextTrace.class::cast)
				.filter(ct -> ct.getKind().equals(typeName) &&
							ct.getIndex().equals(index) &&
							ct.module().get().equals(moduleTrace) &&
							ct.executionContext().get().equals(exContext))
				.findFirst()
				.orElseGet(() -> null);
		return result;
	}

	@Override
	public IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace) {
		
		logger.debug("Get ContextTraceFor type:{}, index:{} with ", typeName, index);
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
	public List<IModuleElementTrace> findPropertyAccessExecutionTraces(String objectId, String propertyName) {
		List<IModuleElementTrace> result = extent.stream()
				.filter(t -> t.accesses().get().stream()
							.filter(IPropertyAccess.class::isInstance)
							.map(IPropertyAccess.class::cast)
							.anyMatch(pa -> pa.property().get().getName().equals(propertyName))
							)
				.collect(Collectors.toList());
		return result;
	}		

	@Override
	public List<IModuleElementTrace> findAllInstancesExecutionTraces(String typeName) {
		List<IModuleElementTrace> result = extent.stream()
				.filter(t -> t.accesses().get().stream()
							.filter(IAllInstancesAccess.class::isInstance)
							.map(IAllInstancesAccess.class::cast)
							.anyMatch(aia -> aia.type().get().getName().equals(typeName))
							)
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace) {
		// TODO Implement IEvlExecutionTraceRepository.findSatisfiesExecutionTraces
		throw new UnsupportedOperationException("Unimplemented Method    IEvlExecutionTraceRepository.findSatisfiesExecutionTraces invoked.");
	}

	@Override
	public Set<IModuleElementTrace> getAllExecutionTraces() {
		return Collections.unmodifiableSet(extent);
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
