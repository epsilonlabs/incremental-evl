package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link TraceGraphVertex} interface represents a single node/vertex in the
 * constraint trace graph. 
 * 
 * This interface also extends {@link VertexFrame} to allow sub-classes to be
 * cast into relevant Java object mappings later. See the Tinkerpop Frames API
 * for more information.
 * 
 * @author Jonathan Co
 *
 */
public interface TraceGraphVertex extends TraceGraphElement, VertexFrame {
}
