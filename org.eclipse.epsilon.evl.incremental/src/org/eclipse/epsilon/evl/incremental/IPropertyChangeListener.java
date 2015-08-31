package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

public interface IPropertyChangeListener  {
	
	public Collection<TScope> onChange(EObject notifier, EStructuralFeature feature);
	public Collection<TScope> onDelete(EObject notifier, EStructuralFeature feature);
	public void validateScopes(Collection<TScope> scopes);
	public void onCreate(EObject notifier);
}
