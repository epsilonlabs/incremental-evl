package org.eclipse.epsilon.evl;

import java.io.PrintStream;
import java.util.List;

import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.eol.execute.ExecutorFactory;
import org.eclipse.epsilon.eol.execute.context.AsyncStatementInstance;
import org.eclipse.epsilon.eol.execute.context.ExtendedProperties;
import org.eclipse.epsilon.eol.execute.context.FrameStack;
import org.eclipse.epsilon.eol.execute.introspection.IntrospectionManager;
import org.eclipse.epsilon.eol.execute.operations.EolOperationFactory;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributorRegistry;
import org.eclipse.epsilon.eol.execute.prettyprinting.PrettyPrinterManager;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.eol.types.IToolNativeTypeDelegate;
import org.eclipse.epsilon.eol.userinput.IUserInput;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

public class TraceEvlContext implements IEvlContext {

	private IEvlContext wrapped;

	public TraceEvlContext(IEvlContext context) {
		super();
		this.wrapped = context;
	}
	
	@Override
	public ConstraintTrace getConstraintTrace() {
		return wrapped.getConstraintTrace();
//		throw new UnsupportedOperationException();
	}
	
	
	// DELEGATES

	public IEvlModule getModule() {
		return wrapped.getModule();
	}

	public List<UnsatisfiedConstraint> getUnsatisfiedConstraints() {
		return wrapped.getUnsatisfiedConstraints();
	}

	public boolean hasFixes() {
		return wrapped.hasFixes();
	}

	public void setUserInput(IUserInput userInput) {
		wrapped.setUserInput(userInput);
	}

	public IUserInput getUserInput() {
		return wrapped.getUserInput();
	}

	public PrettyPrinterManager getPrettyPrinterManager() {
		return wrapped.getPrettyPrinterManager();
	}

	public void setPrettyPrinterManager(
			PrettyPrinterManager prettyPrinterManager) {
		wrapped.setPrettyPrinterManager(prettyPrinterManager);
	}

	public PrintStream getOutputStream() {
		return wrapped.getOutputStream();
	}

	public void setOutputStream(PrintStream outputStream) {
		wrapped.setOutputStream(outputStream);
	}

	public PrintStream getWarningStream() {
		return wrapped.getWarningStream();
	}

	public void setWarningStream(PrintStream warningStream) {
		wrapped.setWarningStream(warningStream);
	}

	public EolOperationFactory getOperationFactory() {
		return wrapped.getOperationFactory();
	}

	public void setOperationFactory(EolOperationFactory operationFactory) {
		wrapped.setOperationFactory(operationFactory);
	}

	public ExecutorFactory getExecutorFactory() {
		return wrapped.getExecutorFactory();
	}

	public void setExecutorFactory(ExecutorFactory executorFactory) {
		wrapped.setExecutorFactory(executorFactory);
	}

	public ModelRepository getModelRepository() {
		return wrapped.getModelRepository();
	}

	public void setModelRepository(ModelRepository modelRepository) {
		wrapped.setModelRepository(modelRepository);
	}

	public FrameStack getFrameStack() {
		return wrapped.getFrameStack();
	}

	public void setFrameStack(FrameStack scope) {
		wrapped.setFrameStack(scope);
	}

	public IntrospectionManager getIntrospectionManager() {
		return wrapped.getIntrospectionManager();
	}

	public void setIntrospectionManager(
			IntrospectionManager introspectionManager) {
		wrapped.setIntrospectionManager(introspectionManager);
	}

	public PrintStream getErrorStream() {
		return wrapped.getErrorStream();
	}

	public void setErrorStream(PrintStream defaultErrorStream) {
		wrapped.setErrorStream(defaultErrorStream);
	}

	public void setModule(IModule module) {
		wrapped.setModule(module);
	}

	public void setNativeTypeDelegates(
			List<IToolNativeTypeDelegate> nativeTypeDelegates) {
		wrapped.setNativeTypeDelegates(nativeTypeDelegates);
	}

	public List<IToolNativeTypeDelegate> getNativeTypeDelegates() {
		return wrapped.getNativeTypeDelegates();
	}

	public boolean isProfilingEnabled() {
		return wrapped.isProfilingEnabled();
	}

	public void setProfilingEnabled(boolean profilingEnabled) {
		wrapped.setProfilingEnabled(profilingEnabled);
	}

	public boolean isAssertionsEnabled() {
		return wrapped.isAssertionsEnabled();
	}

	public void setAssertionsEnabled(boolean assertionsEnabled) {
		wrapped.setAssertionsEnabled(assertionsEnabled);
	}

	public ExtendedProperties getExtendedProperties() {
		return wrapped.getExtendedProperties();
	}

	public void setExtendedProperties(ExtendedProperties properties) {
		wrapped.setExtendedProperties(properties);
	}

	public void dispose() {
		wrapped.dispose();
	}

	public List<AsyncStatementInstance> getAsyncStatementsQueque() {
		return wrapped.getAsyncStatementsQueque();
	}

	public OperationContributorRegistry getOperationContributorRegistry() {
		return wrapped.getOperationContributorRegistry();
	}

}
