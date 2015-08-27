package org.eclipse.epsilon.evl.incremental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraph;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TraceEvlChangeListenerTest extends AbstractOrientTraceGraphTest {

	private static File modelFile = null;
	private static File metaFile = null;
	private static File evlFile = null;

	private TraceEvlModule module = null;
	private EmfModel model = null;
	private TraceGraph trace = null;

	@BeforeClass
	public static void setupClass() throws Exception {
		modelFile = getFile("models/car-1.model");
		metaFile = getFile("models/car.ecore");
		evlFile = getFile("models/car.evl");
	}

	@Before
	public void setup() throws Exception {
		// Setup module
		this.module = new TraceEvlModule();
		this.module.parse(evlFile);
		assertTrue(this.module.getParseProblems().isEmpty());

		// Setup model
		this.model = EmfModelFactory.getInstance().createEmfModel("Model",
				modelFile, metaFile);
		this.model.setStoredOnDisposal(false);
		this.model.load();
		
		this.module.getContext().getModelRepository().addModel(this.model);
		this.module.execute();
		
		this.module.getContext().attachChangeListeners();
		
		this.trace =  this.module.getContext().getTrace();
	}

	@Override
	public void tearDown() {
		this.module.getContext().getModelRepository().dispose();
		this.module = null;
		this.model = null;
		this.trace = null;
	}

	@Override
	protected OrientTraceGraph getGraph() {
		return (OrientTraceGraph) this.trace;
	}
	
	@Test
	public void testRevalidateNotSatisfied() throws Exception {
		final String id = "_0oxZsEwxEeWCTquqlmo4Bw";
		
		// Assert is in the trace and is not satisifed
		TElement beforeElement = this.trace.getElement(id);
		assertNotNull(beforeElement);
		
		for (UnsatisfiedConstraint unsatisfiedConstraint : module.getContext().getUnsatisfiedConstraints()) {
			assertEquals(id, model.getElementId(unsatisfiedConstraint.getInstance()));
		}

		EObject eobj = (EObject) model.getElementById(id);
		EStructuralFeature wheels = eobj.eClass().getEStructuralFeature("wheels");
		List<?> eGet = (List<?>) eobj.eGet(wheels);
		
		eGet.remove(eGet.size() -1);
		
		for (UnsatisfiedConstraint unsatisfiedConstraint : module.getContext().getUnsatisfiedConstraints()) {
			assertNotEquals(id, model.getElementId(unsatisfiedConstraint.getInstance()));
		}
	}
	
//	
//	@Test
//	public void testDeleteElement() throws Exception {		
//		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
//
//		// Check trace element is in graph
//		TElement beforeElement = this.trace.getElement(id);
//		assertNotNull(beforeElement);
//		
//		// Delete element
//		final EObject eobj = (EObject) model.getElementById(id);
//		assertEquals(eobj.eResource(), model.getResource());
//		model.deleteElement(eobj);
//		assertNull(eobj.eResource());
//		assertNull(model.getElementById(id));
//
//		// FIXME: Check the correct constraints reran
//		// FIXME: Check all the relevant scopes and properties removed
//		// Check if element has been deleted from trace graph
//		TElement afterElement = this.trace.getElement(id);
//		assertNull(afterElement);
//
//	}
//
//	@Test
//	public void testDeleteAttrSetNull() throws Exception {
//		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
//		final String attr = "singleAttr";
//		
//		final TProperty before = this.trace.getProperty(attr, id);
//		assertNotNull(before);
//		final Object ridBefore = before.asVertex().getId();
//		
//		final EObject eobj = (EObject) model.getElementById(id);
//		assertNotNull(eobj);
//		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
//		assertNotNull(feature);
//		assertEquals(eobj.eGet(feature), 1);
//		eobj.eSet(feature, null);
//		assertFalse(eobj.eIsSet(feature));
//		
//		final TProperty after = this.trace.getProperty(attr, id);
//		final Object ridAfter = after.asVertex().getId();
//		assertNotEquals(ridBefore, ridAfter);
//	}
//	
//	@Test
//	public void testDeleteAttrUnset() throws Exception {
//		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
//		final String attr = "singleAttr";
//		
//		final TProperty before = this.trace.getProperty(attr, id);
//		assertNotNull(before);
//		Object ridBefore = before.asVertex().getId();
//		
//		final EObject eobj = (EObject) model.getElementById(id);
//		assertNotNull(eobj);
//		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
//		assertNotNull(feature);
//		assertEquals(eobj.eGet(feature), 1);
//		eobj.eSet(feature, null);
//		assertFalse(eobj.eIsSet(feature));
//		
//		final TProperty after = this.trace.getProperty(attr, id);
//		Object ridAfter = after.asVertex().getId();
//		assertNotEquals(ridBefore, ridAfter);
//	}
}
