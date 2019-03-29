package org.eclipse.epsilon.evl.incremental;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;

/**
 * A base AbstractModule for specific incremental EVL trace model implementations. Trace model
 * providers should implement the following interfaces:
 * <ul>
 * 	<li> {@link IEvlModuleTraceRepository}
 * 	<li> {@link IModelTraceRepository}
 *  <li> {@link IEvlRootElementsFactory}
 *  <li> {@link IEvlExecutionTraceManager}
 *  <li> {@link IEvlModuleIncremental}
 * </ul>
 * and provide the appropiate bindings in the {@link #configure()} method.
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class AbstractEvlTracelGuiceModule extends AbstractModule {
	
	
	public void validateBindings(Injector injector) {
		Set<Class<?>> reqInterfaces = new HashSet<>(Arrays.asList(
				IEvlModuleTraceRepository.class,
				IModelTraceRepository.class,
				IEvlRootElementsFactory.class,
				IEvlExecutionTraceManager.class,
				IEvlModuleIncremental.class
				));
        
		reqInterfaces.removeAll(injector.getAllBindings().keySet().stream()
			.map(k -> k.getTypeLiteral().getRawType())
			.collect(Collectors.toSet()));
        if (!reqInterfaces.isEmpty()) {
           throw new IllegalStateException("Missing bindings: " + reqInterfaces);
        }
   }

}
