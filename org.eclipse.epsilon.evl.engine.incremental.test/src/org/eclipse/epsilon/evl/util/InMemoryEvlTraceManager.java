package org.eclipse.epsilon.evl.util;

import org.eclipse.epsilon.evl.execute.AbstractEvlExecutionTraceManager;

public class InMemoryEvlTraceManager extends AbstractEvlExecutionTraceManager  {

	@Override
	public boolean persistTraceInformation() {
		System.out.println("InMemoryEvlTraceManager does not persist information.");
		return false;
	}

		
}