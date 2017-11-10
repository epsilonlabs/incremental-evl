package org.eclipse.epsilon.evl.engine.incremental.trace;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.Check;
import org.eclipse.epsilon.evl.incremental.trace.Context;
import org.eclipse.epsilon.evl.incremental.trace.EvlExecutionTrace;
import org.eclipse.epsilon.evl.incremental.trace.EvlModule;
import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.Invariant;
import org.eclipse.epsilon.evl.incremental.trace.Message;
import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlExecutionTraceImpl;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EvlExecutionTraceLifecycleTest.EvlExecutionTraceFactoryTest.class,
					 EvlExecutionTraceLifecycleTest.EvlModuleFactoryTest.class,
					 EvlExecutionTraceLifecycleTest.ContextFactoryTest.class,
					 EvlExecutionTraceLifecycleTest.InvariantFactoryTest.class})
public class EvlExecutionTraceLifecycleTest {
	
	public static class EvlExecutionTraceFactoryTest {
		
		@Test
		public void createModel() throws Exception {
			EvlExecutionTrace evltrace = new EvlExecutionTraceImpl();
			Model modelA = evltrace.createModel("A");
			assertThat(modelA.getName(), is("A"));
			assertThat(modelA.elements().get(), is(empty()));
			Model modelB = evltrace.createModel("A");
			assertThat(modelB, is(modelA));
			modelB = evltrace.createModel("B");
			assertThat(modelB, is(not(modelA)));
			assertThat(modelB.elements().get(), is(empty()));			
			
		}
		
		@Test
		public void createModule() throws Exception {
			EvlExecutionTrace evltrace = new EvlExecutionTraceImpl();
			EvlModule moduleA = evltrace.createEvlModule("/some/path/script.evl");
			assertThat(moduleA.getSource(), is("/some/path/script.evl"));
			EvlModule moduleB = evltrace.createEvlModule("/some/path/script.evl");
			assertThat(moduleB, is(moduleA));
			moduleB = evltrace.createEvlModule("/some/other/path/script.evl");
			assertThat(moduleB, is(not(moduleA)));
			EvlModule moduleC = evltrace.createEvlModule("/some/random/path/script.evl");
			assertThat(evltrace.module().get(), contains(moduleA, moduleB, moduleC));
		}
		
	}
	
	public static class EvlModuleFactoryTest {
		
		private EvlExecutionTrace evltrace;
		private EvlModule moduleA;
		
		@Before
		public void setup() throws Exception {
			evltrace = new EvlExecutionTraceImpl();
			moduleA = evltrace.createEvlModule("/some/path/script.evl");
		}
		
		@Test
		public void createContext() throws Exception {
			Context ctxY = moduleA.createContext();
			assertThat(ctxY.module().get(), is(moduleA));
			Context ctxZ = moduleA.createContext();
			assertThat(ctxY, is(ctxZ));
		}
	}
	
	public static class ContextFactoryTest {
		
		private EvlExecutionTrace evltrace;
		private EvlModule module;
		private Context ctx;
		
		@Before
		public void setup() throws Exception {
			evltrace = new EvlExecutionTraceImpl();
			module = evltrace.createEvlModule("/some/path/script.evl");
			ctx = module.createContext();
		}
		
		@Test
		public void createGuard() throws Exception {			
			Guard gA = ctx.createGuard();
			assertThat(gA.limits().get(), is(ctx));
			Guard gB = ctx.createGuard();
			assertThat(gB, is(gA));
		}
		
		@Test
		public void createInvariant() throws Exception {
			Invariant invA = ctx.createInvariant("invA");
			assertThat(invA.getName(), is("invA"));
			Invariant invB = ctx.createInvariant("invA");
			assertThat(invB, is(invA));
			invB = ctx.createInvariant("invB");
			assertThat(invB, is(not(invA)));
			Invariant invC = ctx.createInvariant("invC");
			assertThat(ctx.constraints().get(), contains(invA, invB, invC));
		}
	}
	
	public static class InvariantFactoryTest {
		
		private EvlExecutionTrace evltrace;
		private EvlModule module;
		private Context ctx;
		private Invariant invariant;
		
		@Before
		public void setup() throws Exception {
			evltrace = new EvlExecutionTraceImpl();
			module = evltrace.createEvlModule("/some/path/script.evl");
			ctx = module.createContext();
			invariant = ctx.createInvariant("invariant");
		}
		
		@Test
		public void createGuard() throws Exception {
			Guard gA = invariant.createGuard();
			assertThat(gA.limits().get(), is(invariant));
			Guard gB = invariant.createGuard();
			assertThat(gB, is(gA));
		}
		
		@Test
		public void createCheck() throws Exception {
			Check cA = invariant.createCheck();
			assertThat(cA.invariant().get(), is(invariant));
			Check cB = invariant.createCheck();
			assertThat(cB, is(cA));
		}
		
		@Test
		public void createMessage() throws Exception {
			Message mA = invariant.createMessage();
			assertThat(mA.invariant().get(), is(invariant));
			Message mB = invariant.createMessage();
			assertThat(mB, is(mA));
		}
		
		@Test
		public void createSatisfies() throws Exception {
			Satisfies sA = invariant.createSatisfies();
			Satisfies sB = invariant.createSatisfies();
			assertThat(sB, is(sA));
		}
	}
	
	public static class CreateTracesTest {
		
		private static final String PATH_TO_ELEMENT_B = "path/to/elementB";
		private static final String PATH_TO_ELEMENT_A = "path/to/elementA";
		private EvlExecutionTrace evltrace;
		private EvlModule module;
		private Context ctxA;
		private Context ctxB;
		private Invariant invariantA;
		private Invariant invariantB;
		private Model model;
		private ModelType typeA;
		private ModelType typeB;
		private ModelElement elementA;
		private ModelElement elementB;
		private Property aName;
		private Property aAge;
		private Property bPartner;
		private Property bName;
		private Invariant invariantC;
		private Invariant invariantD;
		
		@Before
		public void setup() throws Exception {
			evltrace = new EvlExecutionTraceImpl();
			module = evltrace.createEvlModule("/some/path/script.evl");
			ctxA = module.createContext();
			invariantA = ctxA.createInvariant("isOld");
			invariantB = ctxA.createInvariant("isNamed");
			ctxB = module.createContext();
			invariantC = ctxB.createInvariant("isNamed");
			invariantD = ctxB.createInvariant("isSingle");
			model = evltrace.createModel("modelA");
			typeA = model.createModelType("typeA");
			typeB = model.createModelType("typeB");
			elementA = model.createModelElement(PATH_TO_ELEMENT_A);
			elementB = model.createModelElement(PATH_TO_ELEMENT_B);
			aName = elementA.createProperty("name");
			aAge = elementA.createProperty("age");
			bPartner = elementB.createProperty("partner");
			bName = elementB.createProperty("name");
		}
		
		@Test
		public void contextContextElement() {
			ctxA.context().set(elementA);
			// If elementA is deleted, we need to find ctxA
			Context targetCtx = evltrace.module().get().stream()
						.flatMap(mu -> mu.moduleElements().get().stream())
						.filter(mue -> (mue instanceof Context))
						.map(Context.class::cast)
						.filter(ctx -> ctx.context().get().contains(elementA))
						.findFirst()
						.get();		// FIXME This can be part of a Util Class, as I might use them a lot
			assertThat(ctxA, is(targetCtx));
			ctxB.context().set(elementA);
			List<Context> result = evltrace.module().get().stream()
					.flatMap(mu -> mu.moduleElements().get().stream())
					.filter(mue -> (mue instanceof Context))
					.map(Context.class::cast)
					.filter(ctx -> ctx.context().get().contains(elementA))
					.collect(Collectors.toList());
			assertThat(result, contains(ctxA, ctxB));
			
		}
		
		@Test
		public void contextGuardTracesProperty() throws Exception {
			Guard guardA = ctxA.createGuard();
			guardA.createPropertyAccess(aName);
			// Property changes, re-execute guarded element
			
		}
		
		
	}

}
