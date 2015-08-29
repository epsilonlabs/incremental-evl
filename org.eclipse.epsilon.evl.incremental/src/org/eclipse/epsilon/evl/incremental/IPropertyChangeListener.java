package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

public interface IPropertyChangeListener  {
	
	public Collection<TScope> onChange(Notification notice);
	public Collection<TScope> onDelete(Notification notice);
	public void validateScopes(Collection<TScope> scopes);

}
