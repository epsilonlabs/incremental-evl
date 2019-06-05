package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of IncrementalEvlExecutionStrategy that reexecutes by visiting the modes
 * via TreeIterator and executes each trace as found.
 * 
 * @author Horacio Hoyos Rodriguez
 */
public class SimpleStrategy implements IncrementalEvlExecutionStrategy {

	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(SimpleStrategy.class);
	

	/**
	 * Instantiates a new tree iterator strategy.
	 *
	 * @param incrementalEvlModule the incremental evl module
	 */
	public SimpleStrategy() { }

	/**
	 * Execute.
	 * @param modelTraceRepo the model trace repo
	 * @param context the context
	 * @param sourceChksum the source checksum
	 * @param moduleRepo the execution trace repo
	 * @throws EolRuntimeException the eol runtime exception
	 */
	@Override
	public void execute(
		IIncrementalEvlContext context,
		IncrementalEvlModule evlModule) throws EolRuntimeException {
		IEvlModuleTraceRepository  moduleTraceRepo = context.getTraceManager().getExecutionTraceRepository();
		IModelTraceRepository  modelTraceRepo = context.getTraceManager().getModelTraceRepository();
		for (IModel m : context.getModelRepository().getModels()) {
			if (m instanceof IIncrementalModel) {
				IIncrementalModel im = (IIncrementalModel) m;
				IModelTrace modelTrace = null;
				Iterator<? extends Object> it = im.getAllElements();
				Set<String> visited = new HashSet<>();
				while (it.hasNext()) {
					Object element = it.next();
					Set<TraceReexecution> traces = new HashSet<>();
					// Get all Accesses for the element
					// For property access compare values, use property getter?
					Collection<IPropertyAccess> pas = moduleTraceRepo
							.getPropertyAccessesForElement(im.getElementId(element), im.getModelUri(), evlModule.getChksum());
					if (pas.isEmpty()) {
						// If there are not access maybe new element.
						if (modelTrace == null) {
							modelTrace = modelTraceRepo.getModelTraceForModule(im.getModelUri(), evlModule.getChksum())
									.orElseThrow(() -> new IllegalStateException("Unable to retreive model trace for model " + im.getModelUri()));
						}
						Optional<IModelElementTrace> et = modelTraceRepo.getModelElementTrace(im.getElementId(element), modelTrace);
						if (!et.isPresent()) {
							//Do we have any type/kind access?
							Collection<String> alltypes = im.getAllTypeNamesOf(element);
							for (String type : alltypes) {		// FIXME We can group all missing types, then execute?
								// Check for type access and if so, if there is no element trace its a new element
								traces.addAll(moduleTraceRepo
										.findAllInstancesExecutionTraces(
											evlModule.getChksum(),
											im.getModelUri(),
											type));
							}
						}
					}		
					else {
						for (IPropertyAccess pa : pas) {
							// The PropertyAccessExecutionListener asks the model how to store the properties
							// so we must use the same "wrapping" in order to get comparable results
							if (! im.comparePropertyValue(element, pa.property().get().getName(), pa.getValue())) {
								// Change
								traces.addAll(moduleTraceRepo
										.findPropertyAccessExecutionTraces(pa));
								logger.debug("Found {} traces for the element", traces.size());
							}
						}
						// If any property changed also get all Instance accesses by elements type/kind
						// and then execute. FIXME Can we tell if the property access was after an AllInstances?
						if (!traces.isEmpty()) {
							traces.addAll(moduleTraceRepo
									.findAllInstancesExecutionTraces(
										evlModule.getChksum(),
										im.getModelUri(),
										im.getTypeNameOf(element)));
							// this.incrementalEvlModule.executeTraces(sourceChksum, im, traces, element);			
						}
					}
					for (TraceReexecution t : traces) {
						try {
							t.reexecute(context, evlModule);
						} catch (EolRuntimeException e) {
							logger.error("Error reexecuting trace", e);
						}
					}
					// We need to keep all ElementTraces so we can then know which has been deleted...
					visited.add(im.getElementId(element));
				}
				Collection<IModelElementTrace> deleted = modelTraceRepo.getModelElementTraces(evlModule.getChksum(), im.getModelUri(), visited);
				DeletedElementsStrategy dStrategy = new DeletedElementsStrategy(deleted, im);
				dStrategy.execute(context, evlModule);
			}
		}
		context.getConstraintTrace().clear();
	}
	
}