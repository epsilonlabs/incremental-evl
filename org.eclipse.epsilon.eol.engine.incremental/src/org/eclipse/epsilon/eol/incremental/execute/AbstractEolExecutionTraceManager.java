package org.eclipse.epsilon.eol.incremental.execute;

import java.time.LocalDate;

/**
 * A base implementation of the IEolExecutionTraceManager.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class AbstractEolExecutionTraceManager implements IEolExecutionTraceManager {

	/** A flag to signal parallel persistance */
	protected boolean inParallel;
	
	/** The model repository */
	protected IModelRepository modelRepository;

	/** The maximum number of elements in the repositories before a {@link #persistTraceInformation()} is triggered. */
	protected int flushSize;

	/** The periodic time (in miliseconds) for a {@link #persistTraceInformation()} to be triggered. */
	protected float timeOut;

	/** Time of last flush */
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
		if (this.modelRepository == null) {
			this.modelRepository = new EolModelRepository(inParallel);
		}
		return modelRepository;
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
