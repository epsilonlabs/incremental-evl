package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraphFactory;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

/**
 * Subclass of {@link EvlContext} for use with incremental evaluation.
 * 
 * @author Jonathan Co
 *
 */
public class TraceEvlContext extends EvlContext {

	private TraceGraph trace = null;
	
	public TraceGraph getTrace() {
		if (trace == null) {
			this.trace = OrientTraceGraphFactory.getInstance().getGraph();
		}
		return this.trace;
	}

	/**
	 * Throws {@link UnsupportedOperationException}. This is not used in 
	 * incremental evaluation.
	 */
	@Override
	public ConstraintTrace getConstraintTrace() {
		throw new UnsupportedOperationException();
	}

	public void attachChangeListeners() {
		// Attach change listeners to models
		for (IModel model : this.getModelRepository().getModels()) {
			if (model instanceof EmfModel) {
				((EmfModel) model).getResource().eAdapters().add(
						new EmfPropertyChangeListener((EmfModel) model, this));
			}
		} 
	}
	
	@Override
	public TraceEvlModule getModule() {
		// TODO Auto-generated method stub
		return (TraceEvlModule) super.getModule();
	}
}
