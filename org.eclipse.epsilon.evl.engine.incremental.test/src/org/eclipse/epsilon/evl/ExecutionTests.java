package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.epsilon.base.incremental.trace.IAccess;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.eol.dom.XorOperatorExpression;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Module;

/**
 * Test that the correct access traces are created
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */

public abstract class ExecutionTests<M extends Module> {

	protected IncrementalEvlModule module;
	private File evlFile;

	public abstract M getEvlGuiceModule();

	@Before
	public void setup() throws Exception {
		module = new IncrementalEvlModule();
		module.injectTraceManager(getEvlGuiceModule());
		evlFile = new File(ExecutionTests.class.getResource("testExecution.evl").toURI());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAccessCreation() throws Exception {
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "bank");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "iban");
		String path = ExecutionTests.class.getResource("bankSmall.csv").getPath();
		properties.put(CsvModel.PROPERTY_FILE, path);
		CsvModelIncremental model = new CsvModelIncremental();
		model.load(properties, new IRelativePathResolver() {
			@Override
			public String resolve(String relativePath) {
				return relativePath;
			}
		});

		module.parse(evlFile);
		module.context.getModelRepository().addModel(model);
		module.execute();

		Set<IEvlModuleTrace> moduleTraces = module.getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces();
		assertThat("One single EvlModuleTrace", moduleTraces.size(), is(1));
		IEvlModuleTrace moduleTrace = moduleTraces.iterator().next();
		List<IContextTrace> contextExecutionTraces = IncrementalUtils.asStream(moduleTrace.moduleElements().get())
				.filter(t -> t instanceof IContextTrace)
				.map(IContextTrace.class::cast).collect(Collectors.toList());
		// We have two contexts, traces per Row
		Collection<Map<String, Object>> modelRows = model.getAllOfType("Row");
		assertThat("One ContextTrace per Evl ContraintContext", contextExecutionTraces.size(),
				is(2));
		// Each context should have as many ExecutionContexts as Rows in the model
		for (IContextTrace ct : contextExecutionTraces) {
			List<IExecutionContext> excts = IncrementalUtils.asList(ct.executionContext().get());
			assertThat("One ExecutionContext per Row", excts.size(), is(modelRows.size()));
		}
		// Find the model element access of the ContextTraces
		Stream<IElementAccess> elementAccesses = contextExecutionTraces.stream()
				.flatMap(ct -> IncrementalUtils.asStream(ct.executionContext().get()))
				.flatMap(ec -> IncrementalUtils.asStream(ec.accesses().get())).filter(a -> a instanceof IElementAccess)
				.map(IElementAccess.class::cast);
		assertThat("One ElementAccess per Context-ModelElement pair", elementAccesses.count(),
				is(2L * modelRows.size()));

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date changeDate = sdf.parse("6/4/2017");
		for (IContextTrace ct : contextExecutionTraces) {
			// Get a random element access
			IExecutionContext context = IncrementalUtils.asStream(ct.executionContext().get()).findAny().get();
			IElementAccess elementAccess = IncrementalUtils.asStream(context.accesses().get())
					.filter(a -> a instanceof IElementAccess)
					.map(IElementAccess.class::cast)
					.findFirst()
					.get();
			Map<String, Object> currentRow = (Map<String, Object>) model
					.getElementById(elementAccess.element().get().getUri());
			if (ct.getIndex() == 1) {
				assertThat("ContextTrace should have a guard", ct.guard().get(), is(notNullValue()));
				if (changeDate.before(sdf.parse((String) currentRow.get("startDate")))) {
					Iterator<IInvariantTrace> invariants = ct.constraints().get();
					IInvariantTrace overDraftIt = invariants.next();
					ICheckTrace checkTrace = overDraftIt.check().get();

					IPropertyAccess pa = IncrementalUtils.asStream(context.accesses().get())
							.filter(IPropertyAccess.class::isInstance)
							.map(IPropertyAccess.class::cast)
							.findFirst()
							.get();
					assertThat("Check should access 'balance' property", pa.property().get().getName(), is("balance"));

					IInvariantTrace chargesIt = invariants.next();
					ISatisfiesTrace satisfiesTrace = chargesIt.satisfies().get();
					assertThat("Satisfies trace should not be for 'all'", satisfiesTrace.getAll(), is(false));
					Set<IInvariantTrace> satInvariants = IncrementalUtils
							.asSet(satisfiesTrace.satisfiedInvariants().get());
					assertThat("Satisfies trace should not be for 'all'", satInvariants.size(), is(1));
					assertThat("Satisfies trace should not be for 'isInOverdraft'",
							satInvariants.iterator().next().getName(), is("isInOverdraft"));
					ICheckResult overDraftResult = IncrementalUtils.asStream(overDraftIt.check().get().result().get())
							.filter(r -> r.context().get().equals(context))
							.findFirst()
							.get();
					if (!overDraftResult.getValue()) {
						ICheckTrace checkTrace2 = chargesIt.check().get();
						IncrementalUtils.asStream(context.accesses().get())
							.filter(a -> a.from().get().equals(checkTrace2) && (a instanceof IPropertyAccess))
							.map(IPropertyAccess.class::cast)
							.collect(Collectors.toSet());
						// FIMXE Move this complex queries to the repository so we can exploit gremlin!
						Set<IPropertyAccess> checkAccessesSet = IncrementalUtils.asStream(context.accesses().get())
								.filter(a -> a.from().get().equals(checkTrace) && (a instanceof IPropertyAccess))
								.map(IPropertyAccess.class::cast)
								.collect(Collectors.toSet());
						// Boolean operations can fail fast, so property access must be at least 1 and
						// at most 2.
						assertThat("Two properties accesses in check", checkAccessesSet.size(), greaterThan(0));
						assertThat("Two properties accesses in check", checkAccessesSet.size(), lessThan(3));

						Set<String> propNames = checkAccessesSet.stream()
								.map(e -> e.property().get().getName())
								.collect(Collectors.toSet());
						assertThat("Check should access ['hasOverdraft','branch'] properties", propNames,
								anyOf(containsInAnyOrder("hasOverdraft", "branch"), contains("hasOverdraft")));
					}
				}
			} else {
				assertThat("ContextTrace should not have a guard", ct.guard().get(), is(nullValue()));
			}

		}

	}

}