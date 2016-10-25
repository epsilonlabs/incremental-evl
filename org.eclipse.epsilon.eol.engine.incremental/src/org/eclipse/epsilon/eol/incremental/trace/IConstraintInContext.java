package org.eclipse.epsilon.eol.incremental.trace;


// FIXME This might be only specific to OrientDB
public interface IConstraintInContext extends TraceComponent {
	
//	String TRACE_TYPE = "in";

//	@InVertex
	IContextTrace getContext();
	
//	@OutVertex
	IConstraintTrace getConstraint();
}
