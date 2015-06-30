package org.eclipse.epsilon.evl;

import java.io.File;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.dom.TraceConstraint;
import org.eclipse.epsilon.evl.dom.TraceConstraintContext;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.parse.EvlParser;
import org.eclipse.epsilon.evl.trace.OrientTraceGraphFactory;
import org.eclipse.epsilon.evl.trace.TraceGraph;
import org.eclipse.epsilon.evl.trace.TraceGraphFactory;

import com.tinkerpop.blueprints.Graph;


public class TraceEvlModule extends EvlModule {
	
//	private static final String URL_FORMAT = "remote:localhost/%s";
	private static final String URL_FORMAT = "plocal:%s";
	private static final String USER = "admin";
	private static final String PASS = "admin";
	
	private TraceGraph<? extends Graph> traceGraph = null;

	@Override
	protected void prepareContext(IEolContext context) {
		super.prepareContext(context);
		initTraceGraph();
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
		StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
		sb.append("/")
		.append(sourceFile.getName().split("\\.")[0])
		.append("-trace");
		File file = new File(sb.toString());
		file.mkdirs();
		return String.format(URL_FORMAT, file.toString());
	}
	
	public void initTraceGraph() {
		if (this.traceGraph == null || !this.traceGraph.isOpen()) {
			TraceGraphFactory<? extends Graph> tgf = new OrientTraceGraphFactory(getTraceLocation(), USER, PASS);
			this.traceGraph = tgf.getGraph();
		}
	}
	
	public TraceGraph<? extends Graph> getTraceGraph() {
		if (this.traceGraph == null || !this.traceGraph.isOpen()) {
			this.initTraceGraph();
		}
		return this.traceGraph;
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
			case EvlParser.CONTEXT: return new TraceConstraintContext();
		}
		return super.adapt(cst, parentAst);
	}
	
	@Override
	public void reset() {
		super.reset();
		// Overwrite context with custom context
		context = new TraceEvlContext();
	}
	
}
