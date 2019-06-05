package org.eclipse.epsilon.evl.incremental;

import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangedElementsStrategy implements IncrementalEvlExecutionStrategy {
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(ChangedElementsStrategy.class);
	
	private final Object changedElement;
	private final IIncrementalModel model;
	private final boolean live;
	private final String propertyName;
	

	public ChangedElementsStrategy(Object changedElement, IIncrementalModel model, String propertyName) {
		this(changedElement, model, propertyName, false);
	}


	public ChangedElementsStrategy(Object changedElement, IIncrementalModel model, String propertyName, boolean live) {
		super();
		this.changedElement = changedElement;
		this.model = model;
		this.live = live;
		this.propertyName = propertyName;
	}

	@Override
	public void execute(
		IIncrementalEvlContext context,
		IncrementalEvlModule evlModule) throws EolRuntimeException {
		IEvlModuleTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		String moduleUri = context.getModule().getUri().toString();
		context.getTraceManager().getModelTraceRepository()
			.getPropertyTraceFor(
				model.getModelUri(),
				model.getElementId(changedElement),
				propertyName)
			.ifPresent(pt -> {
				logger.info(String.format("Propertry trace information found for property %s of %s", propertyName, model.getElementId(changedElement)));
				Set<TraceReexecution> traces = repo.findPropertyAccessExecutionTraces(moduleUri,
						pt);
				traces.addAll(repo.findAllInstancesExecutionTraces(moduleUri, model.getModelUri(), model.getTypeNameOf(changedElement)));
				logger.debug("Found {} traces for the element", traces.size());
				for (TraceReexecution t : traces) {
					try {
						t.reexecute(context, evlModule);
					} catch (EolRuntimeException e) {
						logger.error("Error reexecuting trace", e);
					}
				}
			});
	}
}
