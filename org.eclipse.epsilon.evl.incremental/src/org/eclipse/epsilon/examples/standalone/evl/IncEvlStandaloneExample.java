package org.eclipse.epsilon.examples.standalone.evl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.TraceEvlModule;
import org.eclipse.epsilon.evl.trace.TProperty;
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
		
		System.out.println("METAMODEL :: " + metamodel);
		System.out.println("MODEL :: " + model);
		System.out.println("EVL :: " + evl);

		new IncEvlStandaloneExample(metamodel, model, evl).execute();
		
		System.out.println("FINISHED");
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
	}

	@Override
	public void postProcess() {

		final TraceEvlModule mod = (TraceEvlModule) this.module;
		
		// get the property to change
		Iterable<TProperty> allProps = mod.getTraceGraph().getAllProperties();
		TProperty property = allProps.iterator().next();
		String elementId = property.getOwner().getElementId();
		String propName = property.getName();
		
		EObject eobj = null;
		
		for (IModel model : mod.getContext().getModelRepository().getModels()) {
			Object o = model.getElementById(elementId);
			if (o instanceof EObject) {
				eobj = (EObject) o;
				break;
			}
		}
	
		System.out.println("Changing " + propName + "." + elementId);
		for (EAttribute eAttribute : eobj.eClass().getEAttributes()) {
			if (eAttribute.getName().equals(propName)) {
				eobj.eSet(eAttribute, Long.toString(System.currentTimeMillis()));
			}
		}
		
		// Change a feature
		
		super.postProcess();
	}

	@Override
	public IEolExecutableModule createModule() {
		return new TraceEvlModule();
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
