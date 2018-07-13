package org.eclipse.epsilon.evl.engine.incremental.test.util;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.execute.BasicEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

public class InMemoryEvlTraceManager extends BasicEvlExecutionTraceManager  {

	public InMemoryEvlTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository) {
		super(executionTraceRepository, modelTraceRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean persistTraceInformation() {
		System.out.println("InMemoryEvlTraceManager does not persist information.");
		return false;
	}

		
}