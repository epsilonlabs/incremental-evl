package org.eclipse.epsilon.evl.incremental.execute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;
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
		for (IModuleElementTrace t : extent ) {
			if (t instanceof IContextTrace) {
				IContextTrace ct = (IContextTrace) t;
				if (ct.getKind().equals(typeName)) {
					if (ct.getIndex().equals(index)) {
						if (ct.module().get().equals(moduleTrace)) {
							if (ct.executionContext().get().equals(exContext)) {
								
							}
						}
					}
				}
			}
		}
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
							.anyMatch(pa -> pa.property().get().getName().equals(propertyName) &&
									pa.property().get().element().get().getUri().equals(objectId))
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

	@Override
	public List<IModuleElementTrace> findIndirectExecutionTraces(String objectId, Object object, IModel model) {
		List<IContextTrace> indirectContextTraces = extent.stream()
				.filter(IContextTrace.class::isInstance)
				.map(IContextTrace.class::cast)
				.filter(ct -> !ct.executionContext().get().contextVariables().get().stream()
						.anyMatch(v -> v.value().get().getId().equals(objectId))
						)
				.collect(Collectors.toList());
		List<IModuleElementTrace> result = new ArrayList<>();
		result.addAll(indirectContextTraces.stream()
				.filter(ct -> accessesElement(ct.guard().get(), objectId, object, model)
						)
				.collect(Collectors.toList()));
		List<IInvariantTrace> allInvaraints = new ArrayList<>();
		indirectContextTraces.forEach(ct -> allInvaraints.addAll(ct.constraints().get()));
		result.addAll(allInvaraints.stream()
						.filter(inv -> accessesElement(inv.guard().get(), objectId, object, model) ||
									   accessesElement(inv.check().get(), objectId, object, model) ||
									   accessesElement(inv.message().get(), objectId, object, model)
					            )
						.collect(Collectors.toList()));
		return result;
	}
	
	@Override
	public void removeTraceInformation(String objectId) {
		// Property Accesses
		List<IPropertyAccess> pas = new ArrayList<>();
		extent.stream()
				.forEach(t -> pas.addAll(t.accesses().get().stream()
						.filter(IPropertyAccess.class::isInstance)
						.map(IPropertyAccess.class::cast)
						.filter(pa -> pa.property().get().element().get().getUri().equals(objectId))
						.collect(Collectors.toList())
						));
		for (IPropertyAccess pa : pas) {
			IModuleElementTrace executionTrace = pa.executionTrace().get();
			pa.executionTrace().destroy(executionTrace);
			if (executionTrace.accesses().get().isEmpty()) {
				extent.remove(executionTrace);
			}
		}
		// ElementAccess
		List<IElementAccess> eas = new ArrayList<>();
		extent.stream()
				.forEach(t -> eas.addAll(t.accesses().get().stream()
						.filter(IElementAccess.class::isInstance)
						.map(IElementAccess.class::cast)
						.filter(ea -> ea.element().get().getUri().equals(objectId))
						.collect(Collectors.toList())
						));
		for (IElementAccess ea : eas) {
			IModuleElementTrace executionTrace = ea.executionTrace().get();
			ea.executionTrace().destroy(executionTrace);
			if (executionTrace.accesses().get().isEmpty()) {
				extent.remove(executionTrace);
			}
		}
		// TODO We could delete allInstances access is no more elements of the type exist in the model?
	}
	
	private boolean accessesElement(IModuleElementTrace trace, String objectId, Object object, IModel model) {
		String elementType = model.getTypeNameOf(object);
		return trace.accesses().get().stream()
				.filter(IPropertyAccess.class::isInstance)
				.map(IPropertyAccess.class::cast)
				.anyMatch(pa -> pa.property().get().element().get().getUri().equals(objectId)) ||
			trace.accesses().get().stream()
					.filter(IAllInstancesAccess.class::isInstance)
					.map(IAllInstancesAccess.class::cast)
					.anyMatch(aia -> {
						try {
							return aia.getOfKind()?model.isOfKind(object, elementType):model.isOfType(object, elementType);
						} catch (EolModelElementTypeNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					});
		
	}

}
