package org.eclipse.epsilon.evl.incremental.trace;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.eclipse.epsilon.evl.incremental.AbstractOrientTraceGraphTest;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraph;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraphFactory;
import org.eclipse.epsilon.evl.incremental.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.trace.TContext;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;
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
		this.graph = (OrientTraceGraph) this.factory.getGraph();
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
	public void testCreateConstraintDuplicate() {
		final String contextName = "context-testCreateConstraintDuplicate";
		final String constraintName = "constraint-testCreateConstraintDuplicate";
		
		assertNull(this.graph.getConstraint(constraintName, contextName));
		
		final TConstraint first = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(first);
		assertEquals(contextName, first.getContext().getName());
		assertEquals(constraintName, first.getName());
		final Object contextRID = first.getContext().asVertex().getId();
		final Object constraintRID = first.asVertex().getId();
		
		final TConstraint second = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(second);
		assertEquals(contextName, second.getContext().getName());
		assertEquals(constraintName, second.getName());
		assertEquals(contextRID, second.getContext().asVertex().getId());
		assertEquals(constraintRID, second.asVertex().getId());
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
	public void testCreateElementDuplicate() {
		final String elementId = "element-testCreateElementDuplicate";
		
		assertNull(this.graph.getElement(elementId));
		
		final TElement first = this.graph.createElement(elementId);
		assertNotNull(first);
		assertEquals(elementId, first.getElementId());
		final Object elementRID = first.asVertex().getId();
		
		final TElement second = this.graph.createElement(elementId);
		assertNotNull(second);
		assertEquals(elementId, second.getElementId());
		assertEquals(elementRID, second.asVertex().getId());
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
	public void testCreateScopeDuplicate() {
		final String contextName = "context-testCreateScopeDuplicate";
		final String constraintName = "constraint-testCreateScopeDuplicate";
		final String elementId = "element-testCreateScopeDuplicate";
		
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
		
		final TScope first = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(first);
		assertEquals(elementId, first.getRootElement().getElementId());
		assertEquals(constraintName, first.getConstraint().getName());
		assertEquals(contextName, first.getConstraint().getContext().getName());
		final Object scopeRID = first.asVertex().getId();
		
		final TScope second = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(second);
		assertEquals(elementId, second.getRootElement().getElementId());
		assertEquals(constraintName, second.getConstraint().getName());
		assertEquals(contextName, second.getConstraint().getContext().getName());
		assertEquals(scopeRID, second.asVertex().getId());
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
	public void testCreatePropertyDuplicate() {
		final String propertyName = "property-testCreatePropertyDuplicate";
		final String elementId = "element-testCreatePropertyDuplicate";
		
		assertNull(this.graph.getElement(elementId));
		assertNull(this.graph.getProperty(propertyName, elementId));
		
		final TProperty first = this.graph.createProperty(propertyName, elementId);
		assertNotNull(first);
		assertEquals(elementId, first.getOwner().getElementId());
		assertEquals(propertyName, first.getName());
		final Object propertyRID = first.asVertex().getId();
		
		final TProperty second = this.graph.createProperty(propertyName, elementId);
		assertNotNull(second);
		assertEquals(elementId, second.getOwner().getElementId());
		assertEquals(propertyName, second.getName());
		assertEquals(propertyRID, second.asVertex().getId());
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
		final String contextName = "context-testGetContextDoesNotExist";
		assertNull(this.graph.getContext(contextName));
	}

	@Test
	public void testGetConstraintStringString() {
		final String contextName = "context-testGetConstraintStringString";
		final String constraintName = "constraint-testGetConstraintStringString";
		
		assertNull(this.graph.getConstraint(constraintName, contextName));
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(constraint);
		assertEquals(contextName, constraint.getContext().getName());
		assertEquals(constraintName, constraint.getName());
		final Object constraintRID = constraint.asVertex().getId();
		
		final TConstraint get = this.graph.getConstraint(constraintName, contextName);
		assertNotNull(get);
		assertEquals(constraintRID, get.asVertex().getId());
	}

	@Test
	public void testGetConstraintStringTContext() {
		final String contextName = "context-testGetConstraintStringTContext";
		final String constraintName = "constraint-testGetConstraintStringTContext";
		
		final TContext context = this.getGraph().createContext(contextName);
		assertNotNull(context);
		assertEquals(contextName, context.getName());
		assertNull(this.graph.getConstraint(constraintName, context));
		final Object contextRID = context.asVertex().getId();
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, context);
		assertNotNull(constraint);
		assertEquals(constraintName, constraint.getName());
		final Object constraintRID = constraint.asVertex().getId();
		
		final TConstraint get = this.graph.getConstraint(constraintName, context);
		assertNotNull(get);
		assertEquals(constraintName, constraint.getName());
		assertEquals(constraintRID, get.asVertex().getId());
		assertEquals(contextRID, get.getContext().asVertex().getId());
	}
	
	@Test
	public void getConstraintDoesNotExist() {
		final String contextName = "context-getConstraintDoesNotExist";
		final String constraintName = "constraint-getConstraintDoesNotExist";
		
		final TContext context = this.graph.createContext(contextName);
		assertNotNull(context);
		
		assertNull(this.graph.getConstraint(constraintName, context));
	}

	@Test
	public void testGetElement() {
		final String elementId = "element-testGetElement";
		
		assertNull(this.graph.getElement(elementId));
		
		final TElement create = this.graph.createElement(elementId);
		assertNotNull(create);
		assertEquals(elementId, create.getElementId());
		final Object rid = create.asVertex().getId();
		
		final TElement get = this.graph.getElement(elementId);
		assertNotNull(get);
		assertEquals(elementId, get.getElementId());
		assertEquals(rid, get.asVertex().getId());
	}
	
	@Test
	public void testGetElementDoesNotExist(){
		final String elementId = "element-testGetElementDoesNotExist";
		assertNull(this.graph.getElement(elementId));
	}

	@Test
	public void testGetScopeStringStringString() {
		final String contextName = "context-testGetScopeStringStringString";
		final String constraintName = "constraint-testGetScopeStringStringString";
		final String elementId = "element-testGetScopeStringStringString";
		
		assertNull(this.getGraph().getScope(elementId, constraintName, contextName));
		
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		final Object rid = scope.asVertex().getId();
		
		final TScope get = this.graph.getScope(elementId, constraintName, contextName);
		assertEquals(rid, get.asVertex().getId());
	}

	@Test
	public void testGetScopeStringTConstraint() {
		final String contextName = "context-testGetScopeStringTConstraint";
		final String constraintName = "constraint-testGetScopeStringTConstraint";
		final String elementId = "element-testGetScopeStringTConstraint";
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(constraint);
		assertNull(this.getGraph().getScope(elementId, constraint));
		
		final TScope scope = this.graph.createScope(elementId, constraint);
		assertNotNull(scope);
		final Object rid = scope.asVertex().getId();
		
		final TScope get = this.graph.getScope(elementId, constraint);
		assertEquals(rid, get.asVertex().getId());
	}

	@Test
	public void testGetScopeTElementStringString() {
		final String contextName = "context-testGetScopeTElementStringString";
		final String constraintName = "constraint-testGetScopeTElementStringString";
		final String elementId = "element-testGetScopeTElementStringString";
		
		final TElement element = this.graph.createElement(elementId);
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(element);
		assertNotNull(constraint);
		assertNull(this.getGraph().getScope(element, constraint));

		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		final Object rid = scope.asVertex().getId();
		
		final TScope get = this.graph.getScope(element, constraint);
		assertEquals(rid, get.asVertex().getId());
	}

	@Test
	public void testGetScopeTElementTConstraint() {
		final String contextName = "context-testGetScopeTElementTConstraint";
		final String constraintName = "constraint-testGetScopeTElementTConstraint";
		final String elementId = "element-testGetScopeTElementTConstraint";
		
		final TElement element = this.graph.createElement(elementId);
		assertNotNull(element);
		assertNull(this.getGraph().getScope(element, constraintName, contextName));
		
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		final Object rid = scope.asVertex().getId();
		
		final TScope get = this.graph.getScope(elementId, constraintName, contextName);
		assertEquals(rid, get.asVertex().getId());
	}
	
	@Test
	public void testGetScopeDoesNotExist() {
		final String contextName = "context-testGetScopeDoesNotExist";
		final String constraintName = "constraint-testGetScopeDoesNotExist";
		final String elementId = "element-testGetScopeDoesNotExist";
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		final TElement element = this.graph.createElement(elementId);
		
		assertNull(this.graph.getScope(element, constraint));
	}

	@Test
	public void testGetPropertyStringString() {
		final String propertyName = "property-testGetPropertyStringString";
		final String elementId = "element-testGetPropertyStringString";
		
		assertNull(this.graph.getProperty(propertyName, elementId));
		
		final TProperty property = this.graph.createProperty(propertyName, elementId);
		assertNotNull(property);
		assertEquals(propertyName, property.getName());
		final Object rid = property.asVertex().getId();
		
		final TProperty get = this.graph.getProperty(propertyName, elementId);
		assertNotNull(get);
		assertEquals(propertyName, get.getName());
		assertEquals(rid, get.asVertex().getId());
	}

	@Test
	public void testGetPropertyStringTElement() {
		final String propertyName = "property-testGetPropertyStringTElement";
		final String elementId = "element-testGetPropertyStringTElement";
		
		final TElement element = this.graph.createElement(elementId);
		assertNotNull(element);
		assertEquals(elementId, element.getElementId());
		assertNull(this.graph.getProperty(propertyName, element));
		
		final TProperty create = this.graph.createProperty(propertyName, element);
		assertNotNull(create);
		assertEquals(propertyName, create.getName());
		final Object rid = create.asVertex().getId();
		
		final TProperty get = this.graph.getProperty(propertyName, element);
		assertNotNull(get);
		assertEquals(propertyName, get.getName());
		assertEquals(rid, get.asVertex().getId());
	}
	
	@Test
	public void testGetPropertyDoesNotExist() {
		final String propertyName = "property-testGetPropertyDoesNotExist";
		final String elementId = "element-testGetPropertyDoesNotExist";
		
		final TElement element = this.graph.createElement(elementId);
		assertNotNull(element);
		
		assertNull(this.graph.getProperty(propertyName, element));
	}

	@Test
	public void testGetAllContexts() {
		final String[] contextNames = {
				"context-testGetAllContexts-1",
				"context-testGetAllContexts-2",
				"context-testGetAllContexts-3",
				"context-testGetAllContexts-4",
				"context-testGetAllContexts-5"
		};
		
		for (String s : contextNames) {
			this.graph.createContext(s);
		}
		
		int count = 0;
		for (TContext context : this.graph.getAllContexts()) {
			count++;
			assertThat(Arrays.asList(contextNames), hasItem(context.getName()));
		}
		assertEquals(contextNames.length, count);
	}

	@Test
	public void testGetAllConstraints() {
		final String contextName = "context-testGetAllConstraints";
		final String[] constraintNames = {
				"constraint-testGetAllConstraints-1",
				"constraint-testGetAllConstraints-2",
				"constraint-testGetAllConstraints-3",
				"constraint-testGetAllConstraints-4",
				"constraint-testGetAllConstraints-5"
		};
		
		final TContext context = this.graph.createContext(contextName);
		for (String s : constraintNames) {
			this.graph.createConstraint(s, context);
		}
		
		int count = 0;
		for (TConstraint constraint : this.graph.getAllConstraints()) {
			count++;
			assertThat(Arrays.asList(constraintNames), hasItem(constraint.getName()));
		}
		assertEquals(constraintNames.length, count);
	}

	@Test
	public void testGetAllElements() {
		final String[] elementIds = {
				"element-testGetAllElements-1",
				"element-testGetAllElements-2",
				"element-testGetAllElements-3",
				"element-testGetAllElements-4",
				"element-testGetAllElements-5"
		};
		
		for (String s : elementIds) {
			this.graph.createElement(s);
		}
		
		int count = 0;
		for (TElement element : this.graph.getAllElements()) {
			count++;
			assertThat(Arrays.asList(elementIds), hasItem(element.getElementId()));
		}
		assertEquals(elementIds.length, count);
	}

	@Test
	public void testGetAllScopes() {
		final String contextName = "context-testGetAllScopes";
		final String constraintName = "constraint-testGetAllScopes";
		final String[] elementIds = {
				"element-testGetAllScopes-1",
				"element-testGetAllScopes-2",
				"element-testGetAllScopes-3",
				"element-testGetAllScopes-4",
				"element-testGetAllScopes-5"
		};
		
		for (String s : elementIds) {
			this.graph.createScope(s, constraintName, contextName);
		}
		
		int count = 0;
		for (TScope scope : this.graph.getAllScopes()) {
			count++;
			assertEquals(contextName, scope.getConstraint().getContext().getName());
			assertEquals(constraintName, scope.getConstraint().getName());;
			assertThat(Arrays.asList(elementIds), hasItem(scope.getRootElement().getElementId()));
		}
		assertEquals(elementIds.length, count);
	}

	@Test
	public void testGetAllProperties() {
		final String elementId = "element-testGetAllProperties";
		final String[] propertyNames = {
				"property-testGetAllProperties-1",
				"property-testGetAllProperties-2",
				"property-testGetAllProperties-3",
				"property-testGetAllProperties-4",
				"property-testGetAllProperties-5"
		};
		
		for (String s : propertyNames) {
			this.graph.createProperty(s, elementId);
		}
		
		int count = 0;
		for (TProperty property : this.graph.getAllProperties()) {
			count++;
			assertEquals(elementId, property.getOwner().getElementId());
			assertThat(Arrays.asList(propertyNames), hasItem(property.getName()));
		}
		assertEquals(propertyNames.length, count);
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
		final String contextName = "context-testRemoveContextString";
		
		final TContext create = this.graph.createContext(contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getName());
		
		assertNotNull(this.graph.getContext(contextName));
		this.graph.removeContext(contextName);
		assertNull(this.graph.getContext(contextName));
	}

	@Test
	public void testRemoveContextTContext() {
		final String contextName = "context-testRemoveContextTContext";
		
		final TContext create = this.graph.createContext(contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getName());
		
		assertNotNull(this.graph.getContext(contextName));
		this.graph.removeContext(create);
		assertNull(this.graph.getContext(contextName));
	}

	@Test
	public void testRemoveConstraintTConstraint() {
		final String contextName = "context-testRemoveContextTContext";
		final String constraintName = "constraint-testRemoveContextTContext";
		
		final TConstraint create = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getContext().getName());
		assertEquals(constraintName, create.getName());
		
		assertNotNull(this.graph.getConstraint(constraintName, contextName));
		this.graph.removeConstraint(create);
		assertNull(this.graph.getConstraint(constraintName, contextName));
	}

	@Test
	public void testRemoveConstraintStringString() {
		final String contextName = "context-testRemoveConstraintStringString";
		final String constraintName = "constraint-testRemoveConstraintStringString";
		
		final TConstraint create = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getContext().getName());
		assertEquals(constraintName, create.getName());
		
		assertNotNull(this.graph.getConstraint(constraintName, contextName));
		this.graph.removeConstraint(constraintName, contextName);
		assertNull(this.graph.getConstraint(constraintName, contextName));
	}

	@Test
	public void testRemoveConstraintStringTContext() {
		final String contextName = "context-testRemoveConstraintStringTContext";
		final String constraintName = "constraint-testRemoveConstraintStringTContext";
		
		final TContext context = this.graph.createContext(contextName);
		assertNotNull(context);
		assertEquals(contextName, context.getName());
		
		final TConstraint create = this.graph.createConstraint(constraintName, contextName);
		assertNotNull(create);
		assertEquals(contextName, create.getContext().getName());
		assertEquals(constraintName, create.getName());
		
		assertNotNull(this.graph.getConstraint(constraintName, contextName));
		this.graph.removeConstraint(constraintName, context);
		assertNull(this.graph.getConstraint(constraintName, contextName));
	}

	@Test
	public void testRemoveElementTElement() {
		final String elementId = "element-testRemoveElementTElement";
		
		final TElement create = this.graph.createElement(elementId);
		assertNotNull(create);
		assertEquals(elementId, create.getElementId());
		
		assertNotNull(this.graph.getElement(elementId));
		this.graph.removeElement(create);
		assertNull(this.graph.getElement(elementId));
	}

	@Test
	public void testRemoveElementString() {
		final String elementId = "element-testRemoveElementString";
		
		final TElement create = this.graph.createElement(elementId);
		assertNotNull(create);
		assertEquals(elementId, create.getElementId());
		
		assertNotNull(this.graph.getElement(elementId));
		this.graph.removeElement(elementId);
		assertNull(this.graph.getElement(elementId));
	}

	@Test
	public void testRemoveScopeTScope() {
		final String contextName = "context-testRemoveScopeTScope";
		final String constraintName = "constraint-testRemoveScopeTScope";
		final String elementId = "element-testRemoveScopeTScope";
		
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		
		assertNotNull(this.graph.getScope(elementId, constraintName, contextName));
		this.graph.removeScope(scope);
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
	}

	@Test
	public void testRemoveScopeStringStringString() {
		final String contextName = "context-testRemoveScopeStringStringString";
		final String constraintName = "constraint-testRemoveScopeStringStringString";
		final String elementId = "element-testRemoveScopeStringStringString";
		
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(scope);
		
		assertNotNull(this.graph.getScope(elementId, constraintName, contextName));
		this.graph.removeScope(elementId, constraintName, contextName);
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
	}

	@Test
	public void testRemoveScopeStringTConstraint() {
		final String contextName = "context-testRemoveScopeStringTConstraint";
		final String constraintName = "constraint-testRemoveScopeStringTConstraint";
		final String elementId = "element-testRemoveScopeStringTConstraint";
		
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(constraint);
		assertNotNull(scope);
		
		assertNotNull(this.graph.getScope(elementId, constraintName, contextName));
		this.graph.removeScope(elementId, constraint);
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
	}

	@Test
	public void testRemoveScopeTElementStringString() {
		final String contextName = "context-testRemoveScopeTElementStringString";
		final String constraintName = "constraint-testRemoveScopeTElementStringString";
		final String elementId = "element-testRemoveScopeTElementStringString";
		
		final TElement element = this.graph.createElement(elementId);
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(element);
		assertNotNull(scope);
		
		assertNotNull(this.graph.getScope(elementId, constraintName, contextName));
		this.graph.removeScope(element, constraintName, contextName);
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
	}

	@Test
	public void testRemoveScopeTElementTConstraint() {
		final String contextName = "context-testRemoveScopeTElementTConstraint";
		final String constraintName = "constraint-testRemoveScopeTElementTConstraint";
		final String elementId = "element-testRemoveScopeTElementTConstraint";
		
		final TElement element = this.graph.createElement(elementId);
		final TConstraint constraint = this.graph.createConstraint(constraintName, contextName);
		final TScope scope = this.graph.createScope(elementId, constraintName, contextName);
		assertNotNull(element);
		assertNotNull(constraint);
		assertNotNull(scope);
		
		assertNotNull(this.graph.getScope(elementId, constraintName, contextName));
		this.graph.removeScope(element, constraint);
		assertNull(this.graph.getScope(elementId, constraintName, contextName));
	}

	@Test
	public void testRemovePropertyTProperty() {
		final String propertyName = "property-testRemovePropertyTProperty";
		final String elementId = "element-testRemovePropertyTProperty";
		
		final TProperty property = this.graph.createProperty(propertyName, elementId);
		assertNotNull(property);
		
		assertNotNull(this.graph.getProperty(propertyName, elementId));
		this.graph.removeProperty(property);
		assertNull(this.graph.getProperty(propertyName, elementId));
	}

	@Test
	public void testRemovePropertyStringString() {
		final String propertyName = "property-testRemovePropertyStringString";
		final String elementId = "element-testRemovePropertyStringString";
		
		final TProperty property = this.graph.createProperty(propertyName, elementId);
		assertNotNull(property);
		
		assertNotNull(this.graph.getProperty(propertyName, elementId));
		this.graph.removeProperty(propertyName, elementId);
		assertNull(this.graph.getProperty(propertyName, elementId));
	}

	@Test
	public void testRemovePropertyStringTElement() {
		final String propertyName = "property-testRemovePropertyStringTElement";
		final String elementId = "element-testRemovePropertyStringTElement";
		
		final TElement element = this.graph.createElement(elementId);
		final TProperty property = this.graph.createProperty(propertyName, elementId);
		assertNotNull(element);
		assertNotNull(property);
		
		assertNotNull(this.graph.getProperty(propertyName, elementId));
		this.graph.removeProperty(propertyName, element);
		assertNull(this.graph.getProperty(propertyName, elementId));
	}

}
