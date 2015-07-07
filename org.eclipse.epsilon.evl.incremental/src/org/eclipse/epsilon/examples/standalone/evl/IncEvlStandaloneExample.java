package org.eclipse.epsilon.examples.standalone.evl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.TraceEvlModule;
import org.eclipse.epsilon.examples.standalone.EpsilonStandaloneExample;

/**
 * Example standalone runner for IncEvlModule using Tree
 * 
 * @author Jonathan Co
 *
 */
@Deprecated
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
//		IModel m = module.getContext().getModelRepository().getModels().iterator().next();
//		EObject child = (EObject) m.getElementById("_NF27MB3xEeWLVubRAeSCOA");
//		EObject meta = (EObject) m.getElementById("_RRFzcB4bEeWLVubRAeSCOA");
//		child.eSet(child.eClass().getEStructuralFeature("meta"), meta);
		deleteElement();
//		unsetAttr();
//		setAttr();
//		unsetListElement();
//		unsetListAttr();
//		unsetAttr();
		System.out.println("=== FINISHED EXEC ===");
		removeListeners();
		super.postProcess();
	}
	
	public void deleteElement() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		
		while (it.hasNext()) {
			IModel model = it.next();
			Object object = model.getElementById("_RRFzcB4bEeWLVubRAeSCOA");
			if (object != null) {
				try {
					model.deleteElement(object);
				} catch (EolRuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void unsetList() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		while (it.hasNext()) {
			IModel model = it.next();
			EObject object = (EObject) model.getElementById("_ObBXUB3xEeWLVubRAeSCOA");
			if (object != null) {
				object.eUnset(object.eClass().getEStructuralFeature("numbers"));
			}
		}
	}
	
	public void setAttr() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		while (it.hasNext()) {
			IModel model = it.next();
			EObject object = (EObject) model.getElementById("_ObBXUB3xEeWLVubRAeSCOA");
			if (object != null) {
				object.eSet(object.eClass().getEStructuralFeature("label"), Long.toString(System.currentTimeMillis()));
			}
		}
	}
	
	public void unsetAttr() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		while (it.hasNext()) {
			IModel model = it.next();
			EObject object = (EObject) model.getElementById("_ObBXUB3xEeWLVubRAeSCOA");
			if (object != null) {
				object.eUnset(object.eClass().getEStructuralFeature("label"));
			}
		}
	}
	
	public void unsetListElement() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		while (it.hasNext()) {
			IModel model = it.next();
			EObject object = (EObject) model.getElementById("_NF27MB3xEeWLVubRAeSCOA");
			if (object != null) {
				EList<?> list = (EList<?>) object.eGet(object.eClass().getEStructuralFeature("children"));
				list.remove(0);
			}
		}
	}
	
	public void unsetListAttr() {
		Iterator<IModel> it = module.getContext().getModelRepository().getModels().iterator();
		while (it.hasNext()) {
			IModel model = it.next();
			EObject object = (EObject) model.getElementById("_OpwW4B3xEeWLVubRAeSCOA");
			if (object != null) {
				EList<?> list = (EList<?>) object.eGet(object.eClass().getEStructuralFeature("numbers"));
				list.remove(0);
			}
		}
	}

	public void removeListeners() {
		System.out.println("=== REMOVING ALL ADAPTERS ===");
		for (IModel m : module.getContext().getModelRepository().getModels()) {
			if (m instanceof AbstractEmfModel) {
				EList<Adapter> adapters = ((AbstractEmfModel) m).getResource()
						.eAdapters();
				adapters.removeAll(adapters);
			}
		}

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
