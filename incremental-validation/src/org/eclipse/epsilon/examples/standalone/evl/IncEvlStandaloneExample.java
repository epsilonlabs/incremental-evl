package org.eclipse.epsilon.examples.standalone.evl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.IncEvlModule;
import org.eclipse.epsilon.examples.standalone.EpsilonStandaloneExample;

/**
 * Example standalone runner for IncEvlModule using Tree
 * 
 * @author Jonathan Co
 *
 */
public class IncEvlStandaloneExample extends EpsilonStandaloneExample {
	
	private static final String META = "models/Tree.ecore";
	private static final String MODEL = "models/Tree.model";
	private static final String EVL = "models/Tree.evl";

	/**
	 * Main run method for this example
	 * 
	 * @param args
	 *            Not used
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new IncEvlStandaloneExample().execute();
	}

	@Override
	public IEolExecutableModule createModule() {
		return new IncEvlModule();
	}

	@Override
	public String getSource() throws Exception {
		return EVL;
	}

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		models.add(createEmfModel("Model", 
				MODEL, 
				META, 
				true, 
				true));
		return models;
	}
	
}
