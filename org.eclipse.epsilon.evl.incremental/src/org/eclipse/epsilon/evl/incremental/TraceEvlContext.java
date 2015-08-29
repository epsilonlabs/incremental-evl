package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.context.EvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.orient.OrientPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

/**
 * Subclass of {@link EvlContext} for use with incremental evaluation.
 * 
 * @author Jonathan Co
 *
 */
public class TraceEvlContext extends EvlContext {

	private IPropertyAccessTrace trace = null;
	
	private boolean hasTrace = false;
	
	public IPropertyAccessTrace getTrace() {
		if (trace == null) {
			this.trace = OrientPropertyAccessTraceFactory.getInstance().getTrace();
		}
		return this.trace;
	}
	
	public boolean hasTrace() {
		return this.hasTrace;
	}
	
	public void setHasTrace(boolean trace) {
		this.hasTrace = true;
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
		return (TraceEvlModule) super.getModule();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.trace.shutdown();
	}
}
