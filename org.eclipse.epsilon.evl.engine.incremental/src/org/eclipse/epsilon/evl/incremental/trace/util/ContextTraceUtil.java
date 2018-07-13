package org.eclipse.epsilon.evl.incremental.trace.util;

import java.util.Set;
import java.util.stream.StreamSupport;

import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
 * Helper class to retrieve ContexTraces with different filters
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ContextTraceUtil {

	
	public static IGuardTrace getGuardFor(IGuardedElementTrace guardedElement) {
		IGuardTrace result;
		result = guardedElement.guard().get();
		return result;
	}

	public static IInvariantTrace getInvariantIn(IContextTrace context, String invariantName) {
		IInvariantTrace result = null;
		Iterable<IInvariantTrace> iterable = () -> context.constraints().get();
		result = StreamSupport.stream(iterable.spliterator(), false)
					.filter(inv -> inv.getName().equals(invariantName))
					.findFirst()
					.orElse(null);
		return result;
	}

}
