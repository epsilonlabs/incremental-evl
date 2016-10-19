package org.eclipse.epsilon.evl.incremental;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
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

	private Map<IModel, IPropertyChangeListener> listenerMap = new HashMap<IModel, IPropertyChangeListener>();

	public IPropertyAccessTrace getTrace() {
		if (trace == null) {
			this.trace = OrientPropertyAccessTraceFactory.getInstance()
					.getTrace();
		}
		return this.trace;
	}

	public boolean hasTrace() {
		return this.hasTrace;
	}

	public void setHasTrace(boolean trace) {
		this.hasTrace = trace;
	}

	/**
	 * Throws {@link UnsupportedOperationException}. This is not used in
	 * incremental evaluation.
	 */
	@Override
	public ConstraintTrace getConstraintTrace() {
		return super.getConstraintTrace();
	}

	public void attachChangeListeners() {
		// Attach change listeners to models
		for (IModel model : this.getModelRepository().getModels()) {
			if (this.listenerMap.containsKey(model)) {
				continue;
			}

			if (model instanceof EmfModel) {
				EmfModel emfModel = (EmfModel) model;
				EmfPropertyChangeListener listener = new EmfPropertyChangeListener(
						emfModel, this);
				listenerMap.put(emfModel, listener);
				((EmfModel) model).getResource().eAdapters().add(listener);
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
		for (Entry<IModel, IPropertyChangeListener> entry : this.listenerMap
				.entrySet()) {
			IModel model = entry.getKey();
			if (model instanceof EmfModel) {
				EList<Adapter> adapters = ((EmfModel) model).getResource()
						.eAdapters();
				int indexOf = adapters.indexOf(entry.getValue());
				adapters.remove(indexOf);
			}
		}
		this.trace.shutdown();
	}
}
