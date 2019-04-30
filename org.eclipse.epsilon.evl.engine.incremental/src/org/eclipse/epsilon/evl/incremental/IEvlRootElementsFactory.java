package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.IBaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * A factory for creating the domain root elements. Root elements are created with a detached
 * vertex which can then be attached to a graph for persisting the element.
 * Implementations must ensure that the returned instances implement {@link GremlinWrapper} and that
 * the returned {@linkplain GremlinWarpper#delegate()} is a {@link DetachedVertex}.
 * @author horacio
 *
 */
public interface IEvlRootElementsFactory extends IBaseRootElementsFactory {
	
	/**
	 * Create the root element for the EVL domain
	 */
	IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation;

}
