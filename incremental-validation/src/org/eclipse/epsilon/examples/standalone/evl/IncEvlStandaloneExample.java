package org.eclipse.epsilon.examples.standalone.evl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.IncEvlModule;
import org.eclipse.epsilon.evl.notifications.ReEvaluateListener;
import org.eclipse.epsilon.examples.standalone.EpsilonStandaloneExample;

/**
 * Example standalone runner for IncEvlModule using Tree
 * 
 * @author Jonathan Co
 *
 */
public class IncEvlStandaloneExample extends EpsilonStandaloneExample {
	
	private static final String METAMODEL = "-metamodel";
	private static final String MODEL = "-model";
	private static final String EVL = "-evl";
	
	private final String metamodel;
	private final String model;
	private final String evl;

	/**
	 * Main run method for this example
	 * 
	 * @param args
	 *            Not used
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		for (String s : args) {
			System.out.println(s);
		}
		 
		String metamodel = null;
		String model = null;
		String evl = null;

		for (int i = 0; i < args.length; i++) {
			final String s = args[i];

			if (s.equals(METAMODEL)) {
				i++;
				metamodel = args[i];
			} else if (s.equals(MODEL)) {
				i++;
				model = args[i];
			} else if (s.equals(EVL)) {
				i++;
				evl = args[i];
			}
		}
		
		if (metamodel == null || model == null || evl == null) {
			throw new IllegalArgumentException("All arguments must have a value");
		}

		new IncEvlStandaloneExample(metamodel, model, evl).execute();
	}
	
	/**
	 * Default constructor
	 * 
	 * @param metamodel
	 * @param model
	 * @param evl
	 */
	public IncEvlStandaloneExample(String metamodel, String model, String evl) {
		this.metamodel = metamodel;
		this.model = model;
		this.evl = evl;
	}
	
	@Override
	public void preProcess() {
		super.preProcess();
		for (IModel model : module.getContext().getModelRepository().getModels()) {
			if (model instanceof EmfModel) {
				((EmfModel) model)
				.getResource()
				.eAdapters()
				.add(new ReEvaluateListener(((IncEvlModule) module).getRuleInstances(), model));
			}
		}
	}

	@Override
	public void postProcess() {
		// FIXME: for testing
		super.postProcess();
	}

	@Override
	public IEolExecutableModule createModule() {
		return new IncEvlModule();
	}

	@Override
	public String getSource() throws Exception {
		return this.evl;
	}

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		
		EmfModel emfModel = createEmfModel("Model", 
				this.model, 
				this.metamodel, 
				true, 
				true);
		
		models.add(emfModel);
		return models;
	}
	
}
