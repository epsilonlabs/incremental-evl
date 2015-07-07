package org.eclipse.epsilon.evl.trace;

import static org.junit.Assert.*;

import org.eclipse.epsilon.evl.AbstractOrientTraceGraphTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrientTraceGraphTest extends AbstractOrientTraceGraphTest {

	private OrientTraceGraphFactory factory = null;
	private OrientTraceGraph graph;

	@Before
	public void setup() {
		this.factory = new OrientTraceGraphFactory(
				AbstractOrientTraceGraphTest.URL,
				AbstractOrientTraceGraphTest.USER,
				AbstractOrientTraceGraphTest.PASSWORD);
		this.graph = this.factory.getGraph();
	}

	@Override
	public void tearDown() {
		this.factory = null;
		this.graph = null;
	}
	
	@Override
	protected OrientTraceGraph getGraph() {
		return this.graph;
	}

	@Test
	public void testCreateContext() {
		final String contextName = "context-testCreateContext";
		
		assertNull(this.graph.getContext(contextName));
		
		final TContext create = this.graph.createContext(contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getName());
	}
	
	@Test
	public void testCreateContextDuplicate() {
		final String contextName = "context-testCreateContextDuplicate";
		assertNull(this.graph.getContext(contextName));
		
		final TContext first = this.graph.createContext(contextName);
		final Object id = first.asVertex().getId();
		assertNotNull(first);
		assertEquals(contextName, first.getName());
		
		final TContext second = this.graph.createContext(contextName);
		assertNotNull(second);
		assertEquals(contextName, first.getName());
		assertEquals(id, second.asVertex().getId());
	}
	
	@Test
	public void testCreateConstraint() {
		final String contextName = "context-testCreateConstraint";
		final String constraintName = "constraint-testCreateConstraint";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		
		final TContext context = this.graph.createContext(contextName);
		assertNotNull(context);
		assertEquals(contextName, context.getName());
		final Object contextId = context.asVertex().getId();

		final TConstraint constraint = this.graph.createConstraint(constraintName, context);
		assertNotNull(constraint);
		assertEquals(constraintName, constraint.getName());
		assertEquals(context, constraint.getContext());
		assertEquals(contextName, constraint.getContext().getName());
		assertEquals(contextId, constraint.getContext().asVertex().getId());
	}

	@Test
	public void testCreateConstraintStringString() {
		final String contextName = "context-testCreateConstraintStringString";
		final String constraintName = "constraint-testCreateConstraintStringString";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(constraint);
		assertEquals(constraintName, constraint.getName());
		assertEquals(contextName, constraint.getContext().getName());
	}

	@Test
	public void testCreateElement() {
		final String elementId = "element-testCreateElement";
		
		assertNull(this.graph.getElement(elementId));
		
		final TElement element = this.graph.createElement(elementId);
		assertNotNull(element);
		assertEquals(elementId, element.getElementId());
	}

	@Test
	public void testCreateScope() {
		final String contextName = "context-testCreateScope";
		final String constraintName = "constraint-testCreateScope";
		final String elementId = "element-testCreateScope";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
		
		final TContext context = this.graph.createContext(contextName);
		final TConstraint constraint = this.graph.createConstraint(constraintName, context);
		final TElement element = this.graph.createElement(elementId);
		final Object constraintRID = constraint.asVertex().getId();
		final Object elementRID = element.asVertex().getId();

		final TScope scope = this.graph.createScope(element, constraint);
		assertNotNull(scope);
		assertEquals(constraint, scope.getConstraint());
		assertEquals(element, scope.getRootElement());
		assertEquals(constraintRID, scope.getConstraint().asVertex().getId());
		assertEquals(elementRID, scope.getRootElement().asVertex().getId());
	}
	
	@Test
	public void testCreateScopeStringStringString() {
		final String contextName = "context-testCreateScopeStringStringString";
		final String constraintName = "constraint-testCreateScopeStringStringString";
		final String elementId = "element-testCreateScopeStringStringString";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
		
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		assertEquals(contextName, scope.getConstraint().getContext().getName());
		assertEquals(constraintName, scope.getConstraint().getName());
		assertEquals(elementId, scope.getRootElement().getElementId());
	}

	@Test
	public void testCreateScopeStringTConstraint() {
		final String contextName = "context-testCreateScopeStringTConstraint";
		final String constraintName = "constraint-testCreateScopeStringTConstraint";
		final String elementId = "element-testCreateScopeStringTConstraint";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getScope(elementId, constraintName, contextName));

		final TContext context = this.graph.createContext(contextName);
		final TConstraint constraint = this.graph.createConstraint(constraintName, context);
		final Object constraintRID = constraint.asVertex().getId();
		
		final TScope scope = this.graph.createScope(elementId, constraint);
		assertNotNull(scope);
		assertEquals(constraint, scope.getConstraint());
		assertEquals(constraintRID, scope.getConstraint().asVertex().getId());
		assertEquals(elementId, scope.getRootElement().getElementId());
	}

	@Test
	public void testCreateScopeTElementStringString() {
		final String contextName = "context-testCreateScopeTElementStringString";
		final String constraintName = "constraint-testCreateScopeTElementStringString";
		final String elementId = "element-testCreateScopeTElementStringString";
		
		assertNull(this.graph.getContext(contextName));
		assertNull(this.graph.getConstraint(constraintName, contextName));
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
		
		final TElement element = this.graph.createElement(elementId);
		final Object elementRID = element.asVertex().getId();
		
		final TScope scope = this.graph.createScope(element, constraintName, contextName);
		assertNotNull(scope);
		assertEquals(constraintName, scope.getConstraint().getName());
		assertEquals(contextName, scope.getConstraint().getContext().getName());
		assertEquals(element, scope.getRootElement());
		assertEquals(elementRID, scope.getRootElement().asVertex().getId());
	}

	@Test
	public void testCreatePropertyStringString() {
		final String propertyName = "property-testCreatePropertyStringString";
		final String elementId = "element-testCreatePropertyStringString";
		
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getProperty(propertyName, elementId));
		
		final TProperty property = this.graph.createProperty(propertyName, elementId);
		assertNotNull(property);
		assertEquals(elementId, property.getOwner().getElementId());
		assertEquals(propertyName, property.getName());
	}

	@Test
	public void testCreatePropertyStringTElement() {
		final String propertyName = "property-testCreatePropertyStringString";
		final String elementId = "element-testCreatePropertyStringString";
		
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getProperty(propertyName, elementId));
		
		final TElement element = this.graph.createElement(elementId);
		final Object elementRID = element.asVertex().getId();
		
		final TProperty property = this.graph.createProperty(propertyName, element);
		assertNotNull(property);
		assertEquals(element, property.getOwner());
		assertEquals(elementRID, property.getOwner().asVertex().getId());
		assertEquals(propertyName, property.getName());
	}

	@Test
	public void testGetContext() {
		final String contextName = "testGetContext";
		assertNull(this.graph.getContext(contextName));
		
		this.graph.createContext(contextName);
		final TContext get = this.graph.getContext(contextName);
		
		assertNotNull(get);
		assertEquals(contextName, get.getName());
	}
	
	@Test
	public void testGetContextDoesNotExist() {
		final String contextName = "testGetContextDoesNotExist";
		assertNull(this.graph.getContext(contextName));
	}

	@Test
	public void testGetConstraintStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConstraintStringTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeStringTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeTElementStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopeTElementTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPropertyStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPropertyStringTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllContexts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllConstraints() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllElements() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllScopes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopesInTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScopesInString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveContextString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveContextTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveConstraintStringTContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveElementTElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveElementString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTScope() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeStringTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTElementStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveScopeTElementTConstraint() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyTProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyStringTElement() {
		fail("Not yet implemented");
	}

}
