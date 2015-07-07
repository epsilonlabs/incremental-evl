package org.eclipse.epsilon.evl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.evl.trace.TElement;
import org.eclipse.epsilon.evl.trace.TProperty;
import org.eclipse.epsilon.evl.trace.TScope;
import org.eclipse.epsilon.evl.trace.TraceGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class OldTraceEvlChangeListenerTest {

	private static TraceEvlModule module = null;

	private InMemoryEmfModel model = null;
	private TraceGraph<?> graph = null;

	@BeforeClass
	public static void setupClass() throws Exception {
		// Setup model


		// Setup module
		module = new TraceEvlModule(false);
		module.parse(getFile("models/Foo.evl"));
		if (module.getParseProblems().size() > 0) {
			throw new Exception("Parse problems in EVL file");
		}
	}

	@Before
	public void setUp() throws Exception {
		File modelFile = getFile("models/Foo.model");
		File metaFile = getFile("models/Foo.ecore");
		EmfModel emf = EmfModelFactory.getInstance().createEmfModel("Model",
				modelFile, metaFile);
		emf.setStoredOnDisposal(false);
		emf.load();
		this.model = new InMemoryEmfModel(emf.getResource());
		module.getContext().getModelRepository().addModel(model);
		module.execute();
		this.graph = module.getTraceGraph();
		System.out.println("=== FINISHED SETUP ===");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=== REMOVING ALL ADAPTERS ===");
		this.model.getResource().eAdapters().clear();
		this.model.dispose();
		this.model = null;
		this.graph = null;
		module.context.getModelRepository().dispose();
		module.reset();
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
		
		final EObject eobj = (EObject) model.getElementById(id);
		assertNotNull(eobj);
		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
		assertNotNull(feature);
		assertEquals(eobj.eGet(feature), 1);
		eobj.eSet(feature, null);
		assertFalse(eobj.eIsSet(feature));
		
		final TProperty after = this.graph.getProperty(attr, id);
		assertNull(after);
		
		for (TScope tScope : graph.getElement(id).getScopes()) {
			for (TProperty p : tScope.getProperties()) {
				if (p.getName().equals(attr) && p.getOwner().getElementId().equals(id)) {
					fail("Not deleted from scopes");
				}
			}
		}
	}
	
	@Test
	public void testDeleteAttrUnset() throws Exception {
		final String id = "_Q7PJoB9XEeWZ5uBIxHRxCw";
		final String attr = "singleAttr";
		
		final TProperty before = this.graph.getProperty(attr, id);
		assertNotNull(before);
		
		final EObject eobj = (EObject) model.getElementById(id);
		assertNotNull(eobj);
		final EStructuralFeature feature = eobj.eClass().getEStructuralFeature("singleAttr");
		assertNotNull(feature);
		assertEquals(eobj.eGet(feature), 1);
		eobj.eSet(feature, null);
		assertFalse(eobj.eIsSet(feature));
		
		final TProperty after = this.graph.getProperty(attr, id);
		assertNull(after);
		
		for (TScope tScope : graph.getElement(id).getScopes()) {
			for (TProperty p : tScope.getProperties()) {
				if (p.getName().equals(attr) && p.getOwner().getElementId().equals(id)) {
					fail("Not deleted from scopes");
				}
			}
		}
	}


	@Test
	@Ignore
	public void testDeleteAttrList() {
	}

	@Test
	@Ignore
	public void testDeleteAttrFromList() {
	}

	protected static File getFile(String filename) throws URISyntaxException {
		URI binUri = OldTraceEvlChangeListenerTest.class.getResource(filename)
				.toURI();
		URI uri = null;

		if (binUri.toString().indexOf("bin") > -1) {
			uri = new URI(binUri.toString().replaceAll("bin", "test"));
		} else {
			uri = binUri;
		}

		return new File(uri);
	}

}
