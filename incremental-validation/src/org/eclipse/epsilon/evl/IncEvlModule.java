package org.eclipse.epsilon.evl;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;

public class IncEvlModule extends EvlModule {
	
	private Collection<RuleInstance> ruleInstances = new HashSet<RuleInstance>();
	
	/**
	 * Main execution method
	 */
	@Override
	public Object execute() throws EolRuntimeException {

		// Initialize the context
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("constraintTrace", context.getConstraintTrace()));
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));

		// Execute pre-conditions
		execute(getPre(), context);

		// Check all constraints and log the trace
		for (ConstraintContext conCtx : getConstraintContexts()) {
			TraceConstraintContext incConCtx = new TraceConstraintContext(conCtx);
			incConCtx.checkAll(context);
			ruleInstances.addAll(incConCtx.getRuleInstances());
		}
	
		// Execute fixers
		if (fixer != null)
			fixer.fix(this);

		// Execute post-conditions
		execute(getPost(), context);

		return null;
	}

	public Collection<RuleInstance> getRuleInstances() {
		return ruleInstances;
	}

}
