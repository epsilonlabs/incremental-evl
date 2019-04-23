package org.eclipse.epsilon.evl.incremental;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.types.IToolNativeTypeDelegate;
import org.eclipse.epsilon.erl.execute.RuleProfiler;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.executors.evl.EvlExecutor;
import org.eclipse.epsilon.executors.evl.SimpleEvlExecutor;


public class IncrementalEvlExecutor implements EvlExecutor {

	private final SimpleEvlExecutor delegate;
	private final IncrementalEvlModule module;
	
	
	public IncrementalEvlExecutor(IncrementalEvlModule mdl) {
		module = mdl;
		delegate = new SimpleEvlExecutor(module);
	}
	
	// FIXME Getting the module is not the right thing
	public IncrementalEvlModule module() {
		return module;
	}
	

	public Collection<UnsatisfiedConstraint> execute() throws EolRuntimeException {
		return delegate.execute();
	}

	public List<Constraint> getConstraints() {
		return delegate.getConstraints();
	}

	public List<ConstraintContext> getConstraintContexts() {
		return delegate.getConstraintContexts();
	}

	public ConstraintContext getConstraintContext(String name) {
		return delegate.getConstraintContext(name);
	}

	public Set<UnsatisfiedConstraint> getUnsatisfiedConstraints() {
		return delegate.getUnsatisfiedConstraints();
	}

	public boolean parse(File file) throws Exception {
		return delegate.parse(file);
	}

	public boolean parse(String code) throws Exception {
		return delegate.parse(code);
	}

	public List<ParseProblem> getParseProblems() {
		return delegate.getParseProblems();
	}

	public void addModels(Collection<IModel> models) {
		delegate.addModels(models);
	}

	public void addParamters(Map<String, ?> parameters) {
		delegate.addParamters(parameters);
	}

	public void addNativeTypeDelegates(Collection<IToolNativeTypeDelegate> nativeDelegates) {
		delegate.addNativeTypeDelegates(nativeDelegates);
	}

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public Optional<RuleProfiler> getRuleProfiler() {
		return delegate.getRuleProfiler();
	}

	public void disposeModelRepository() {
		delegate.disposeModelRepository();
	}

	public void clearModelRepository() {
		delegate.clearModelRepository();
	}

	public void dispose() {
		delegate.dispose();
	}

	public void preProcess() {
		delegate.preProcess();
	}

	public void postProcess() {
		delegate.postProcess();
	}

	public void logUnsatisfied(Collection<UnsatisfiedConstraint> unsatisfiedConstraints) {
		delegate.logUnsatisfied(unsatisfiedConstraints);
	}

	public void printUnsatisfied(Collection<UnsatisfiedConstraint> unsatisfiedConstraints) {
		delegate.printUnsatisfied(unsatisfiedConstraints);
	}

}
