package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.Wrapper;
import org.eclipse.epsilon.evl.execute.FixInstance;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;

public class TraceConstraint extends Constraint implements Wrapper<Constraint> {
	
	private final Constraint wrapped;
	
	public TraceConstraint(Constraint constraint) {
		super();
		this.wrapped = constraint;
		this.name = constraint.name;
		this.isCritique = constraint.isCritique;
		this.fixes = constraint.fixes;
		this.constraintContext = constraint.constraintContext;
		this.guardBlock = constraint.guardBlock;
		this.checkBlock = constraint.checkBlock;
		this.messageBlock = constraint.messageBlock;
	}
	
	@Override
	public boolean check(Object self, IEvlContext context)
			throws EolRuntimeException {
		// Current constraint does not apply to object, return false.
		if (!appliesTo(self, context))
			return false;
		
		// Build Framestack for evaluation		
		context.getFrameStack().enterLocal(FrameType.UNPROTECTED, checkBlock.getBody());
		context.getFrameStack().put(Variable.createReadOnlyVariable("self", self));

		// Execute checks, case it passes
		if (checkBlock.execute(context, false)) {
			context.getConstraintTrace().addChecked(this, self, true);
			context.getFrameStack().leaveLocal(checkBlock.getBody());
			return true;
		} 
		
		// Checks failed
		else {
			UnsatisfiedConstraint unsatisfiedConstraint = new UnsatisfiedConstraint();
			unsatisfiedConstraint.setInstance(self);
			unsatisfiedConstraint.setConstraint(this);

			for (Fix fix : fixes) {
				if (!fix.appliesTo(self, context))
					continue;

				FixInstance fixInstance = new FixInstance(context);
				fixInstance.setFix(fix);
				fixInstance.setSelf(self);
				unsatisfiedConstraint.getFixes().add(fixInstance);
			}

			String messageResult = null;

			if (messageBlock != null) {
				messageResult = messageBlock.execute(context, false);
			} else {
				messageResult = "Invariant " + this.getName() + " failed for "
						+ context.getPrettyPrinterManager().toString(self);
			}

			unsatisfiedConstraint.setMessage(messageResult);

			context.getUnsatisfiedConstraints().add(unsatisfiedConstraint);
			// We don't dispose the frame we leave because it may be needed for
			// fix parts
			context.getFrameStack().leaveLocal(checkBlock.getBody(), false);
			return false;
		}
	}

	@Override
	public Constraint getWrapped() {
		return this.wrapped;
	}
	
}
