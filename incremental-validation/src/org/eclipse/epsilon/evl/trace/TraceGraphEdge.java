package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.EdgeFrame;

/**
 * The {@link TraceGraphEdge} interface represents a single node/vertex in the
 * constraint trace graph. 
 * 
 * This interface also extends {@link EdgeFrame} to allow sub-classes to be
 * cast into relevant Java object mappings later. See the Tinkerpop Frames API
 * for more information.
 * 
 * @author Jonathan Co
 *
 */
public interface TraceGraphEdge extends TraceElement, EdgeFrame {
}
