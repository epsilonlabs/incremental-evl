package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.execute.FixInstance;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;


public class TraceConstraint extends Constraint {

	@Override
	public boolean check(Object self, IEvlContext context) throws EolRuntimeException {
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context)) return false;
		
		// Build Frame
		context.getFrameStack().enterLocal(FrameType.UNPROTECTED, checkBlock.getBody());
		context.getFrameStack().put(Variable.createReadOnlyVariable("self", self));
		
		if (!checkBlock.execute(context, false)){			
			UnsatisfiedConstraint unsatisfiedConstraint = new UnsatisfiedConstraint();
			unsatisfiedConstraint.setInstance(self);
			unsatisfiedConstraint.setConstraint(this);

			for (Fix fix : fixes) {
				if (!fix.appliesTo(self, context)) continue;

				FixInstance fixInstance = new FixInstance(context);
				fixInstance.setFix(fix);
				fixInstance.setSelf(self); 
				unsatisfiedConstraint.getFixes().add(fixInstance);
			}
			
			String messageResult = null;
			
			if (messageBlock != null) {
				messageResult = messageBlock.execute(context, false);
			}
			else {
				messageResult = "Invariant " + this.getName() + " failed for " + 
					context.getPrettyPrinterManager().toString(self);
			}
			
			unsatisfiedConstraint.setMessage(messageResult);
			
			context.getUnsatisfiedConstraints().add(unsatisfiedConstraint);
			// We don't dispose the frame we leave because it may be needed for fix parts
			context.getFrameStack().leaveLocal(checkBlock.getBody(), false);
			return false;
		}
		else {
			context.getFrameStack().leaveLocal(checkBlock.getBody());
			return true;
		}
	}

}
