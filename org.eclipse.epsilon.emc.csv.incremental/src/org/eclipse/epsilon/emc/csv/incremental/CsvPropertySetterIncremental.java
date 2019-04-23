package org.eclipse.epsilon.emc.csv.incremental;


import java.util.Map;

import org.eclipse.epsilon.emc.csv.CsvPropertySetter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

public class CsvPropertySetterIncremental extends CsvPropertySetter {
	
	private CsvModelIncremental model;

	public CsvModelIncremental getModel() {
		return model;
	}

	public void setModel(CsvModelIncremental model) {
		this.model = model;
	}
	
	@Override
	public void invoke(Object value) throws EolRuntimeException {
		
		Map<String, String> currentObject = getMap();
		super.invoke(value);
		model.notifyChange(currentObject, property);
	}

}
