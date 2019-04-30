package org.eclipse.epsilon.base.incremental.trace.util;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex.Builder;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty;

/**
 * A factory class to created detached Tinkerpop vertices
 * @author Horacio Hoyos Rodriguez
 *
 */
public class DetachableTinkerpopDelegateFactory {

	public final Vertex createDetachedVertex(String label, Object... keyValues) {
        Builder vertexBuilder = DetachedVertex.build();
        vertexBuilder.setLabel(label);
        vertexBuilder.setId("Detached");
        // Properties
        org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty.Builder pBuilder = DetachedVertexProperty.build();
        for (int i=0;i<keyValues.length;i+=2) {
        	pBuilder = DetachedVertexProperty.build();
            pBuilder.setLabel((String) keyValues[i])
            	.setValue(keyValues[i+1]);
            vertexBuilder.addProperty(pBuilder.create());	
        }
        return vertexBuilder.create();
    }

}
