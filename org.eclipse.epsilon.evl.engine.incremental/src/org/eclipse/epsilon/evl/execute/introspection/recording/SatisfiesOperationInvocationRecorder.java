package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.util.ContextTraceUtil;

public class SatisfiesOperationInvocationRecorder extends AbstractRecorder<ISatisfiesTrace> implements ISatisfiesInvocationRecorder {
	
	private final IInvariantTrace invariant;

	public SatisfiesOperationInvocationRecorder(IInvariantTrace invariant) {
		super();
		this.invariant = invariant;
	}

	@Override
	public void record(boolean all, Collection<String> parameterValues) {
		if (recording) {
			currentRecording.add(createSatisfies(all, parameterValues));
		}
	}

	private ISatisfiesTrace createSatisfies(boolean all, Collection<String> parameterValues) {
		// Each parameter should be an Invariant name
		IContextTrace context = invariant.invariantContext().get();
		List<IInvariantTrace> invariants = new ArrayList<>();
		for (Object p : parameterValues) {
			assert p instanceof String;
			String invariantName = (String) p;
			IInvariantTrace  invariant = ContextTraceUtil.getInvariantIn(context, invariantName);
			if (invariant == null) {
				try {
					invariant = context.createInvariantTrace((String) p);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException(String.format("Uknown invariant for %s: %s", invariantName, p), e);
				}
			}
		}
		ISatisfiesTrace result = null;
		try {
			result = invariant.createSatisfiesTrace();
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			for (IInvariantTrace i : invariants) {
				result.invariants().create(i);
			}
			result.setAll(all);
		}
		return result;
	}
	

}
