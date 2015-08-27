package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.trace.TScope;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;

public interface PropertyChangeListener  {
	
	public Collection<TScope> onChange(Notification notice);
	public Collection<TScope> onDelete(Notification notice);
	public void validateScopes(Collection<TScope> scopes);

}
