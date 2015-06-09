package org.eclipse.epsilon.evl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Wrapper for properties that make up a Rule Instance.
 * 
 * A {@link RuleInstance} consists of:
 * <ul>
 * <li>Rule - the name of the rule/constraint</li>
 * <li>Root Element ID - the ID of the root element that the rule applies to</li>
 * <li>Scopes - Collection of Scope objects</li>
 * </ul>
 * 
 * @author Jonathan Co
 *
 */
public class RuleInstance {

	private String rule;
	private String rootElementId;
	private Collection<Scope> scopes;
	
	public RuleInstance(String rule, String rootElementId, Collection<Scope> scopes) {
		this.rule = rule;
		this.rootElementId = rootElementId;
		
		// Create safe copy
		this.scopes = new HashSet<Scope>();
		this.scopes.addAll(scopes);
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRootElementId() {
		return rootElementId;
	}

	public void setRootElementId(String rootElement) {
		this.rootElementId = rootElement;
	}

	public Collection<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(Collection<Scope> scopes) {
		// Create safe copy
		this.scopes = new HashSet<Scope>();
		this.scopes.addAll(scopes);
	}

	public boolean isComplete() {
		return rule != null && rootElementId != null && !scopes.isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{ ");
		sb.append("\"rule\": \"").append(rule)
		.append("\", \"root\": \"").append(rootElementId)
		.append("\", \"scopes\": [ ");
		
		Iterator<Scope> it = scopes.iterator();
		Scope scope = null;
		while(it.hasNext()) {
			scope = it.next();
			sb.append("\"")
				.append(scope.getElementId())
				.append(".")
				.append(scope.getPropertyName())
				.append("\"");
			if (it.hasNext()) sb.append(", ");
		}
		sb.append(" ] }");
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rootElementId == null) ? 0 : rootElementId.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		result = prime * result + ((scopes == null) ? 0 : scopes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleInstance other = (RuleInstance) obj;
		if (rootElementId == null) {
			if (other.rootElementId != null)
				return false;
		} else if (!rootElementId.equals(other.rootElementId))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		if (scopes == null) {
			if (other.scopes != null)
				return false;
		} else if (!scopes.equals(other.scopes))
			return false;
		return true;
	}
	
}
