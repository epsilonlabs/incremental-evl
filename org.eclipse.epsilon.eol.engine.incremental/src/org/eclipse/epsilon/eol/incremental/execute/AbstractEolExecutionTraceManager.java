package org.eclipse.epsilon.eol.incremental.execute;

import java.time.LocalDate;

public abstract class AbstractEolExecutionTraceManager implements IEolExecutionTraceManager {

	/** A flag to signal parallel persistance */
	protected boolean inParallel;
	
	protected IModelRepository models;

	protected int flushSize;

	protected float timeOut;

	protected LocalDate lastFlush;
	
	protected AbstractEolExecutionTraceManager() {
		this.lastFlush = LocalDate.now();
	}

	@Override
	public void setParallelPersist(boolean inParallel) {
		this.inParallel = inParallel;
	}

	@Override
	public IModelRepository modelTraces() {
		if (this.models == null) {
			this.models = new EolModelRepository(inParallel);
		}
		return models;
	}

	@Override
	public void setFlushQueueSize(int size) {
		this.flushSize = size;
	}

	@Override
	public void setFlushTimeout(float period) {
		this.timeOut = period;
	}

}
