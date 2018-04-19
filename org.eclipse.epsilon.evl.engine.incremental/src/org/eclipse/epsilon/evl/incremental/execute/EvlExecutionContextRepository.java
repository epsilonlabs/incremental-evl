package org.eclipse.epsilon.evl.incremental.execute;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvlExecutionContextRepository implements IEvlExecutionContextRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(EvlModuleExecutionRepository.class);
	
	private final Set<IExecutionContext> extent;
	
	public EvlExecutionContextRepository() {
		this(false);
	}
	
	public EvlExecutionContextRepository(boolean inParallel) {
//		if (inParallel) {
//			this.extent = ConcurrentHashMap.newKeySet();
//		}
//		else {
			this.extent = new LinkedHashSet<>();
//		}
	}

	@Override
	public IExecutionContext get(Object id) {
		
		logger.debug("Get ExecutionContext with id:{}", id);
		IExecutionContext result = null;
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
	public boolean add(IExecutionContext item) {
		return extent.add(item);
	}

	@Override
	public boolean remove(IExecutionContext item) {
		return extent.remove(item);
	}

	@Override
	public IExecutionContext getExecutionContextFor(IModelElementVariable... selfVariable) {
		
		@SuppressWarnings("unchecked")
		HashSet<IModelElementVariable> expectedVariables = new HashSet(Arrays.asList(selfVariable));
		IExecutionContext result;
		result = extent.stream()
						.filter(ec -> expectedVariables.equals(ec.contextVariables().get()))
						.findFirst()
						.orElseGet(() -> null);
		return result;
	}

}
