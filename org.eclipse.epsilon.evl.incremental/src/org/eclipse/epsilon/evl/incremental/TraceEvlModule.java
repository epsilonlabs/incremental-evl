package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.dom.TraceConstraint;
import org.eclipse.epsilon.evl.incremental.trace.OrientTraceGraphFactory;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;
import org.eclipse.epsilon.evl.parse.EvlParser;

import com.tinkerpop.blueprints.Graph;


public class TraceEvlModule extends EvlModule {
	
	private static final String PERSIST_URL_FORMAT = "plocal:%s";
	private static final String MEMORY_URL_FORMAT = "memory:%s";
	private static final String USER = "admin";
	private static final String PASS = "admin";
	
	private final boolean persist;
	
	private TraceGraph<? extends Graph> traceGraph = null;
	
	public TraceEvlModule() {
		this(true);
	}
	
	public TraceEvlModule(boolean persist) {
		super();
		this.persist = persist;
		prepareContext(getContext());
	}
	
	public Object execute(Resource resource, Constraint constraint, EObject obj) throws EolRuntimeException {
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		
		Set<String> keySet = resource.getResourceSet().getPackageRegistry().keySet();
		Collection<String> keyset2 = EPackage.Registry.INSTANCE.keySet();

		
//		Collection<Object> values = resource.getResourceSet().getPackageRegistry().values();
		Collection<Object> values = EPackage.Registry.INSTANCE.values();
		List<EPackage> packages = new LinkedList<EPackage>((Collection<? extends EPackage>) values);
		
		context.getModelRepository().addModel(new InMemoryEmfModel("", resource, packages));
		
		boolean check = constraint.check(obj, context);
		
		return check;
	}
	
	@Override
	public Object execute() throws EolRuntimeException {
		// FIXME: Detect if a tracegraph is already present or do we want this to batch exec all the time?
		// Initialize the context
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));

		// Perform evaluation
		execute(getPre(), context);
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}		
		if (fixer != null) fixer.fix(this);
		execute(getPost(), context);
		
		// Attach the listeners
		this.attachChangeListeners();
		
		return null;
	}
	
	public String getTraceLocation() {
		final StringBuilder sb = new StringBuilder();
//		if (persist) {
//			sb.append(System.getProperty("user.dir")).append("/");
//		}
		sb.append("file");
//		sb.append(sourceFile.getName().split("\\.")[0]).append("-trace");
//		if (persist) {
//			File file = new File(sb.toString());
//			file.mkdirs();
//			return String.format(PERSIST_URL_FORMAT, file.toString());
//		}
		return String.format(MEMORY_URL_FORMAT, sb.toString());
	}
	
	public TraceGraph<? extends Graph> getTraceGraph() {
		return ((TraceEvlModule) this.context).getTraceGraph();
	}
	
	/**
	 * Utility method - attach notification listeners to all models loaded in
	 * current repository.
	 */
	private void attachChangeListeners() {
		// Attach change listeners to models
		for (IModel m : context.getModelRepository().getModels()) {
			if (m instanceof AbstractEmfModel) {
				((EmfModel) m).getResource().eAdapters().add(new TraceEvlChangeListener(m, this));
			}
		}
	}
	
	@Override
	protected void prepareContext(IEolContext context) {
		super.prepareContext(context);
	}
	
	@Override
	public AST adapt(AST cst, AST parentAst) {
		switch (cst.getType()) {
			case EvlParser.FIX: return new Fix();
			case EvlParser.DO: return new ExecutableBlock<Void>(Void.class);
			case EvlParser.TITLE: return new ExecutableBlock<String>(String.class);
			case EvlParser.MESSAGE: return new ExecutableBlock<String>(String.class);
			case EvlParser.CHECK: return new ExecutableBlock<Boolean>(Boolean.class);
			case EvlParser.GUARD: return new ExecutableBlock<Boolean>(Boolean.class);
			
			// Modified to return the appropriate subclasses of Constraint
			case EvlParser.CONSTRAINT: return new TraceConstraint();
			case EvlParser.CRITIQUE: return new TraceConstraint();
			case EvlParser.CONTEXT: return new ConstraintContext();
		}
		return super.adapt(cst, parentAst);
	}
	
	@Override
	public void reset() {
		super.reset();	
		if (this.getTraceGraph() != null) {
			this.getTraceGraph().shutdown();
		}
		context = new TraceEvlContext();
	}
	
}
