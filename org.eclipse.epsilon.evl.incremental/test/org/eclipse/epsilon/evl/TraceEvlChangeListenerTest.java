package org.eclipse.epsilon.evl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.evl.trace.OrientTraceGraph;
import org.eclipse.epsilon.evl.trace.TElement;
import org.eclipse.epsilon.evl.trace.TProperty;
import org.eclipse.epsilon.evl.trace.TScope;
import org.eclipse.epsilon.evl.trace.TraceGraph;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.tinkerpop.blueprints.Graph;

public class TraceEvlChangeListenerTest extends AbstractOrientTraceGraphTest {

	private static File modelFile = null;
	private static File metaFile = null;
	private static File evlFile = null;

	private TraceEvlModule module = null;
	private EmfModel model = null;
	private TraceGraph<? extends Graph> graph = null;

	@BeforeClass
	public static void setupClass() throws Exception {
		modelFile = getFile("models/Foo.model");
		metaFile = getFile("models/Foo.ecore");
		evlFile = getFile("models/Foo.evl");
	}

	@Before
	public void setup() throws Exception {
		// Setup module
		this.module = new TraceEvlModule(false);
		this.module.parse(evlFile);
		assertTrue(this.module.getParseProblems().isEmpty());

		// Setup model
		this.model = EmfModelFactory.getInstance().createEmfModel("Model",
				modelFile, metaFile);
		this.model.setStoredOnDisposal(false);
		this.model.load();
		
		this.module.getContext().getModelRepository().addModel(this.model);
		this.module.execute();
		
		this.graph = this.module.getTraceGraph();
	}

	@Override
	public void tearDown() {
		this.module.getContext().getModelRepository().dispose();
		this.module = null;
		this.model = null;
		this.graph = null;
	}

	@Override
	protected OrientTraceGraph getGraph() {
		return (OrientTraceGraph) this.graph;
	}
	
	@Test
	public void testDeleteElement() throws Exception {		
		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";

		// Check trace element is in graph
		TElement beforeElement = this.graph.getElement(id);
		assertNotNull(beforeElement);

		TProperty tp = beforeElement.getProperties().iterator().next();

		for (TScope tScope : graph.getAllScopes()) {
			tScope.addProperty(tp);
			graph.commit();
		}

		// Delete element
		final EObject eobj = (EObject) model.getElementById(id);
		assertEquals(eobj.eResource(), model.getResource());
		model.deleteElement(eobj);
		assertNull(eobj.eResource());
		assertNull(model.getElementById(id));

		// FIXME: Check the correct constraints reran
		// FIXME: Check all the relevant scopes and properties removed
		// Check if element has been deleted from trace graph
		TElement afterElement = this.graph.getElement(id);
		assertNull(afterElement);

	}

	@Test
	public void testDeleteAttrSetNull() throws Exception {
		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
		final String attr = "singleAttr";
		
		final TProperty before = this.graph.getProperty(attr, id);
		assertNotNull(before);
		final Object ridBefore = before.asVertex().getId();
		
		final EObject eobj = (EObject) model.getElementById(id);
		assertNotNull(eobj);
		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
		assertNotNull(feature);
		assertEquals(eobj.eGet(feature), 1);
		eobj.eSet(feature, null);
		assertFalse(eobj.eIsSet(feature));
		
		final TProperty after = this.graph.getProperty(attr, id);
		final Object ridAfter = after.asVertex().getId();
		assertNotEquals(ridBefore, ridAfter);
	}
	
	@Test
	public void testDeleteAttrUnset() throws Exception {
		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
		final String attr = "singleAttr";
		
		final TProperty before = this.graph.getProperty(attr, id);
		assertNotNull(before);
		Object ridBefore = before.asVertex().getId();
		
		final EObject eobj = (EObject) model.getElementById(id);
		assertNotNull(eobj);
		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
		assertNotNull(feature);
		assertEquals(eobj.eGet(feature), 1);
		eobj.eSet(feature, null);
		assertFalse(eobj.eIsSet(feature));
		
		final TProperty after = this.graph.getProperty(attr, id);
		Object ridAfter = after.asVertex().getId();
		assertNotEquals(ridBefore, ridAfter);
	}


	@Test
	@Ignore
	public void testDeleteAttrList() {
	}

	@Test
	@Ignore
	public void testDeleteAttrFromList() {
	}

}
