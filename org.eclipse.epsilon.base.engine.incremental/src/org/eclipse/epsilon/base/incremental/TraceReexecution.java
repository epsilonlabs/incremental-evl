package org.eclipse.epsilon.base.incremental;

import java.util.Optional;

import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

/**
 * A Trace Execution class knows how to execute particular types of Module element traces.
 * Represents a ModuleElementTrace-ExecutionContext pair that should be re-executed.
 * 
 * Additionally, the reexecution provides a tree-like structure to allow dependant traces to be
 * nested below their supporter traces. Only supporter traces should be re-executed.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface TraceReexecution {
	
	/**
	 * A ModuleElement trace is reexecuted in a particular context.
	 */
	 void reexecute(IIncrementalBaseContext context, IModuleIncremental module) throws EolRuntimeException;
			
	/**
	 * Assign the IReexecutionTrace as parent of this reexecution trace
	 * @param parent				the parent
	 * @return
	 */
	TraceReexecution makeChildOf(TraceReexecution parent);
	
	Optional<TraceReexecution> parent();
	
}