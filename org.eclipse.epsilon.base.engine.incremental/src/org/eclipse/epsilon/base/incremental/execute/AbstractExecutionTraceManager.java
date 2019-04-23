package org.eclipse.epsilon.base.incremental.execute;

import java.time.LocalDate;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;

/**
 * A base implementation of the IEolExecutionTraceManager.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class AbstractExecutionTraceManager implements IExecutionTrace {

	/** A flag to signal parallel persistence */
	protected boolean inParallel;

	/**
	 * The maximum number of elements in the repositories before a
	 * {@link #persistTraceInformation()} is triggered.
	 */
	protected int flushSize;

	/**
	 * The periodic time (in milliseconds) for a {@link #persistTraceInformation()}
	 * to be triggered.
	 */
	protected float timeOut;

	/** Time of last flush */
	protected LocalDate lastFlush;

	protected final IModelTraceRepository modelTraceRepository;

	protected AbstractExecutionTraceManager(IModelTraceRepository mdlTrcRpstry) {
		this.lastFlush = LocalDate.now();
		this.modelTraceRepository = mdlTrcRpstry;
	}

	@Override
	public void setParallelPersist(boolean inParallel) {
		this.inParallel = inParallel;
	}

	@Override
	public void setFlushQueueSize(int size) {
		this.flushSize = size;
	}

	@Override
	public void setFlushTimeout(float period) {
		this.timeOut = period;
	}

	@Override
	public IModelTraceRepository getModelTraceRepository() {
		return modelTraceRepository;
	}

}
