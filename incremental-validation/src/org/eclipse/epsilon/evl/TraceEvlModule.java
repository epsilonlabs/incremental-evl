package org.eclipse.epsilon.evl;

import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.trace.OrientTraceGraphFactory;
import org.eclipse.epsilon.evl.trace.ChangeListener;
import org.eclipse.epsilon.evl.trace.TraceConstraintContext;
import org.eclipse.epsilon.evl.trace.TraceGraph;
import org.eclipse.epsilon.evl.trace.TraceGraphFactory;

import com.tinkerpop.blueprints.Graph;

public class TraceEvlModule extends EvlModule {
	
	private static final String URL_FORMAT = "remote:localhost/%s";
	private static final String USER = "admin";
	private static final String PASS = "admin";
	
	private TraceGraph<? extends Graph> traceGraph = null;
	
	/**
	 * Main execution method
	 */
	@Override
	public Object execute() throws EolRuntimeException {
		
		// Initialize the context
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("constraintTrace", context.getConstraintTrace()));
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		
		this.doChecks();
		this.attachChangeListeners();
		this.getConstraints();
		
		return null;
	}
	
	public void initTraceGraph() {
		if (this.traceGraph == null || !this.traceGraph.isOpen()) {
//			System.out.println(System.getProperty("java.io.tmpdir"));
//			File file = new File(System.getProperty("java.io.tmpdir")+ "/trace");
//			file.mkdirs();
//			String url = String.format(URL_FORMAT, file.toString());
			String url = String.format(URL_FORMAT, "trace");
			TraceGraphFactory<? extends Graph> tgf = new OrientTraceGraphFactory(url, USER, PASS);
			this.traceGraph = tgf.getGraph();
		}
	}
	
	public TraceGraph<? extends Graph> getTraceGraph() {
		if (this.traceGraph == null || !this.traceGraph.isOpen()) {
			this.initTraceGraph();
		}
		return this.traceGraph;
	}
	
	private void doChecks() throws EolRuntimeException {
		execute(getPre(), context);
		
		for (ConstraintContext conCtx : getConstraintContexts()) {
			TraceConstraintContext incConCtx = 
					new TraceConstraintContext(conCtx, this.getTraceGraph());
			incConCtx.checkAll(context);
		}
		
		if (fixer != null) fixer.fix(this);
		execute(getPost(), context);
	}
	
	private void attachChangeListeners() {
		// Attach change listeners to models
		for (IModel m : context.getModelRepository().getModels()) {
			if (m instanceof AbstractEmfModel) {
				((EmfModel) m).getResource().eAdapters().add(new ChangeListener(m, this));
			}
		}
	}
	
}
