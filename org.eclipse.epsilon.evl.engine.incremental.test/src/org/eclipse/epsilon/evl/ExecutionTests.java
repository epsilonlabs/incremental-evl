package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.CsvModelIncremental;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.junit.Test;

/**
 * Test that the correct access traces are created
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */

public abstract class ExecutionTests {

	public abstract File evlFile();
	public abstract IncrementalEvlModule module();


	@SuppressWarnings("unchecked")
	@Test
	final public void testAccessCreation() throws Exception {
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

		module().parse(evlFile());
		module().getContext().getModelRepository().addModel(model);
		module().execute();

		Iterator<IEvlModuleTrace> moduleTraces = module().getContext().getTraceManager().getExecutionTraceRepository()
				.getAllModuleTraces();
		IEvlModuleTrace moduleTrace = moduleTraces.next();
		assertThat("One single EvlModuleTrace", !moduleTraces.hasNext());
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
		Stream<IElementAccess> elementAccesses = IncrementalUtils.asStream(moduleTrace.accesses().get())
				.filter(IElementAccess.class::isInstance)
				.map(IElementAccess.class::cast);
		assertThat("One ElementAccess per Context-ModelElement pair", elementAccesses.count(),
				is(2L * modelRows.size()));

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date changeDate = sdf.parse("6/4/2017");
		for (IContextTrace ct : contextExecutionTraces) {
			// Get a random element access
			IElementAccess elementAccess = IncrementalUtils.asStream(moduleTrace.accesses().get())
					.filter(a -> a instanceof IElementAccess)
					.map(IElementAccess.class::cast)
					.filter(ea -> ea.from().get().equals(ct))
					.findAny()
					.get();
			Map<String, Object> currentRow = (Map<String, Object>) model
					.getElementById(elementAccess.element().get().getUri());		
			if (ct.getIndex() == 1) {
				assertThat("ContextTrace should have a guard", ct.guard().get(), is(notNullValue()));
				if (changeDate.before(sdf.parse((String) currentRow.get("startDate")))) {
					Iterator<IInvariantTrace> invariants = ct.constraints().get();
					IInvariantTrace overDraftIt = invariants.next();
					ICheckTrace checkTrace = overDraftIt.check().get();
					// One property access for the check
					IPropertyAccess pa = IncrementalUtils.asStream(moduleTrace.accesses().get())
							.filter(IPropertyAccess.class::isInstance)
							.map(IPropertyAccess.class::cast)
							.filter(a -> a.property().get().elementTrace().get().equals(elementAccesses)
									&& a.from().get().equals(checkTrace))
							.findFirst()
							.get();
					assertThat("Check should access 'balance' property", pa.property().get().getName(), is("balance"));
					// All childs
					//IInvariantTrace chargesIt = invariants.next();
					ICheckResult overDraftResult = IncrementalUtils.asStream(overDraftIt.check().get().result().get())
							.filter(r -> r.context().get().equals(ct.executionContext().get()))
							.findFirst()
							.get();
					if (!overDraftResult.getValue()) {
						Set<IPropertyAccess> checkAccessesSet = IncrementalUtils.asStream(moduleTrace.accesses().get())
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