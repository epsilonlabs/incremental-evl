package org.eclipse.epsilon.eol.engine.incremental.trace;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModel;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionTraceHasModule;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionTraceHasModelImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.ModelImpl;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.junit.Test;

public class ModelLifecycleTest {
	
	private class TestExecutionTrace implements ExecutionTrace {
		
		private ExecutionTraceHasModel model;
		
		public TestExecutionTrace() {
			this.model = new ExecutionTraceHasModelImpl(this);
		}
		
		@Override
		public void setId(Object value) {
			// TODO Implement Type1510241616118.setId
			throw new UnsupportedOperationException("Unimplemented Method    Type1510241616118.setId invoked.");
		}
		
		@Override
		public Object getId() {
			// TODO Implement Type1510241616118.getId
			throw new UnsupportedOperationException("Unimplemented Method    Type1510241616118.getId invoked.");
		}
		
		@Override
		public ExecutionTraceHasModule module() {
			// TODO Implement Type1510241616118.module
			throw new UnsupportedOperationException("Unimplemented Method    Type1510241616118.module invoked.");
		}
		
		@Override
		public ExecutionTraceHasModel model() {
			return model;
		}
		
		@Override
		public Model createModel(String name) {
			try {
				return new ModelImpl(name, this);
			} catch (TraceModelDuplicateRelation e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
	};
	
	@Test
	public void modelHasTypes() throws Exception {
		
		ExecutionTrace extrace = new TestExecutionTrace();
		Model m = extrace.createModel("uml");
		ModelType mtA = m.createModelType("A");
		ModelType mtB = m.createModelType("A");
		assertThat(mtB, is(mtA));
		ModelType mtA2 = m.createModelType("A");
		assertThat(mtA2, is(mtA));
		mtB = m.createModelType("B");
		assertThat(mtB, is(not(mtA)));
	}
	
	@Test
	public void modelHasElements() throws Exception {
		
		ExecutionTrace extrace = new TestExecutionTrace();
		Model m = extrace.createModel("uml");
		ModelElement mtA = m.createModelElement("A");
		ModelElement mtB = m.createModelElement("A");
		assertThat(mtB, is(mtA));
		ModelElement mtA2 = m.createModelElement("A");
		assertThat(mtA2, is(mtA));
		mtB = m.createModelElement("B");
		assertThat(mtB, is(not(mtA)));
	}
	
	@Test
	public void elementHasProperties() throws Exception {
		
		ExecutionTrace extrace = new TestExecutionTrace();
		Model m = extrace.createModel("uml");
		ModelElement mtA = m.createModelElement("A");
		
		Property pA = mtA.createProperty("a");
		Property pB = mtA.createProperty("a");
		assertThat(pB, is(pA));
		Property pA2 = mtA.createProperty("a");
		assertThat(pA2, is(pA));
		pB = mtA.createProperty("b");
		assertThat(pB, is(not(pA)));
	}


}
