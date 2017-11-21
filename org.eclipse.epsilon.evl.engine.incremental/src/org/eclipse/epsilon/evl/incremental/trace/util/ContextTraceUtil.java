package org.eclipse.epsilon.evl.incremental.trace.util;

import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

public class ContextTraceUtil {

	
	public static IGuardTrace getGuardFor(IGuardedElementTrace guardedElement) {
		IGuardTrace result;
		result = guardedElement.guard().get();
		return result;
	}

	public static IInvariantTrace getInvariantIn(IContextTrace context, String invariantName) {
		IInvariantTrace result = null;
		result = context.constraints().get().stream()
					.filter(inv -> inv.getName().equals(invariantName))
					.findFirst()
					.orElse(null);
		return result;
	}

}
