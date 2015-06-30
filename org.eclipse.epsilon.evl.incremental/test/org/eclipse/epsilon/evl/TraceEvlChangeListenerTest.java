package org.eclipse.epsilon.evl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TraceEvlChangeListenerTest {

	private static EmfModel masterModel = null;
	private static TraceEvlModule module = null;
	
	private InMemoryEmfModel model = null;

	@BeforeClass
	public static void setupClass() throws Exception {
		// Setup model
		File modelFile = getFile("models/Tree.model");
		File metaFile = getFile("models/Tree.ecore");
		masterModel = EmfModelFactory.getInstance().createEmfModel("Model",
				modelFile, metaFile);
		masterModel.load();
		
		// Setup module
		module = new TraceEvlModule();
		module.parse(getFile("models/Tree.evl"));
		if (module.getParseProblems().size() > 0) {
			throw new Exception("Parse problems in EVL file");
		}
	}

	@Before
	public void setUp() throws Exception {
		model = new InMemoryEmfModel(masterModel.getResource());
		module.getContext().getModelRepository().addModel(model);
		module.execute();
	}

	@After
	public void tearDown() throws Exception {
		model.dispose();
		module.reset();
	}

	@Test
	public void testDeleteElement() throws Exception {
		final EObject eobj = (EObject) model.getElementById("_RRFzcB4bEeWLVubRAeSCOA");
		assertEquals(eobj.eResource(), model.getResource());
		model.deleteElement(eobj);
		assertNull(eobj.eResource());
	}

	protected static File getFile(String filename) throws URISyntaxException {
		URI binUri = TraceEvlChangeListenerTest.class.getResource(filename)
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
