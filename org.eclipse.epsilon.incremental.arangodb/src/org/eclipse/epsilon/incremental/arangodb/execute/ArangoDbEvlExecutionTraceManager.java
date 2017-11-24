package org.eclipse.epsilon.incremental.arangodb.execute;

import org.eclipse.epsilon.evl.execute.AbstractEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;

public class ArangoDbEvlExecutionTraceManager extends AbstractEvlExecutionTraceManager  implements IEvlExecutionTraceManager {


	@Override
	public boolean persistTraceInformation() {
//		for (Model model : modelRepo.extent) {
//			ExecutionContextArangoDbImpl vertex = new ExecutionContextArangoDbImpl(scriptId, modelsIds);
//	        VertexEntity info = null;
//	        try {
//	            info = arangoDB.db(dbName).graph(graphName)
//	                .vertexCollection(VERTEX_COLLECTION_EXECUTION_CONTEXT).insertVertex(vertex, null);
//	            vertex.put("_id", info.getId());
//	            vertex.put("_key", info.getKey());
//	            vertex.put("_rev", info.getRev());
//	        } catch (ArangoDBException ex) {
//	            // FIXME log?
//	            vertex = null;
//	        }
	        // FIXME created edges to store types, and model elements
	        
	        /* for (me in modelElements) {
	         * 	create properties and add edges!
	         * }
	         */
	        
	        // FIXME Conflict management?
//		}
		return false;
	}

	public SatisfiesInvocationExecutionListener getSatisfiesListener() {
		return null;
	}

	public AllInstancesInvocationExetionListener getAllInstancesListener() {
		return null;
	}

	public PropertyAccessExecutionListener getPropertyAccessListener() {
		return null;
	}

	public void setSatisfiesListener(SatisfiesInvocationExecutionListener satisfiesListener) {
	}

	

}
