package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ExecutionContextGremlin;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;

// FIXME Need an EGX rule for this so we generate it?
public class EvlExecutionContextGremlin extends ExecutionContextGremlin {

	public EvlExecutionContextGremlin(IContextModuleElementTrace container, Vertex vertex, GraphTraversalSource gts)
			throws TraceModelDuplicateElement, TraceModelConflictRelation {
		super(container, vertex, gts);
	}

	public EvlExecutionContextGremlin() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <A extends IAccess> A getOrCreateAccess(Class<A> accessClass, Object... args) throws EolIncrementalExecutionException {
        GraphTraversalSource g = startTraversal();
        A result = null;
        switch(accessClass.getSimpleName()) {
        case "ISatisfiesAccess":
        	assert args[0] instanceof IModelElementTrace;
        	ISatisfiesAccess satisfiesAccess = null;
            try {
                Vertex v = null;
                try {
                    v = g.addV("SatisfiesAccess").next();
                    satisfiesAccess = new SatisfiesAccessGremlin((IModelElementTrace) args[0], this, v, graphTraversalSource());
                } catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
                    g.V(v).as("v").properties().drop().select("v").drop();
                    throw new EolIncrementalExecutionException("Error creating requested ElementAccess", e);
                }
        	} finally {
                finishTraversal(g);
            }
            result = (A) satisfiesAccess;
        	break;
        default:
        	result = super.getOrCreateAccess(accessClass, args);
        }
        return result;
    }
}
