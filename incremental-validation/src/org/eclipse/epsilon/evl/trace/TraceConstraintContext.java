package org.eclipse.epsilon.evl.trace;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;
import org.eclipse.epsilon.common.module.Comment;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.common.parse.Region;
import org.eclipse.epsilon.eol.dom.AnnotationBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Constraints;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;

import com.tinkerpop.blueprints.Graph;

/**
 * Decorator class for {@link ConstraintContext} for use in incremental
 * validation. This class is used to trace property accesses during the
 * evaluation of a constraint, in order to build a scope set.
 * 
 * @author Jonathan Co
 *
 */
public class TraceConstraintContext extends ConstraintContext {

	private final ConstraintContext wrapped;
	private final TraceGraph<? extends Graph> traceGraph;

	public TraceConstraintContext(ConstraintContext constraintContext,
			TraceGraph<? extends Graph> traceGraph) {
		this.wrapped = constraintContext;
		this.traceGraph = traceGraph;
	}

	@Override
	public void checkAll(IEvlContext context) throws EolRuntimeException {
		// Get all the elements that are of the required kind

		Collection<?> allOfKind = getAllOfSourceKind(context);
		Iterator<?> it = allOfKind.iterator();

		// Iterator over each object of kind
		while (it.hasNext()) {
			Object object = it.next();

			// If constraint context applies to object then perform the
			// constraint check
			if (appliesTo(object, context)) {
				Iterator cit = getConstraints().values().iterator();
				while (cit.hasNext()) {
					Constraint constraint = (Constraint) cit.next();

					if (!constraint.isLazy(context) && constraint.appliesTo(object, context)) {
						// Set a listener to trace property accesses
						ConstraintCheckListener listener = new ConstraintCheckListener();
						context.getExecutorFactory().addExecutionListener(listener);

						// Perform actual checks
						constraint.check(object, context);
						
						addToTrace(context, constraint, object, listener.getScopes());

						// Clean-up the listener
						context.getExecutorFactory().removeExecutionListener(listener);
					}
				}

			}
		}
	}

	private void addToTrace(IEvlContext ctx, 
			Constraint constraint, 
			Object modelElement,
			Collection<Scope> scopes) {

		final TContext tContext = this.traceGraph.getContext(this.getName());
		
		final TConstraint tConstraint = this.traceGraph.getConstraint(constraint.getName(), tContext);
		final TElement tElement = this.traceGraph.getElement(ctx
				.getModelRepository().getOwningModel(modelElement)
				.getElementId(modelElement));
		final TScope tScope = this.traceGraph.getScope(tConstraint, tElement);
		
		for (Scope s : scopes) {
			final TElement element = 
					this.traceGraph.getElement(s.getElementId());
			final TProperty property = 
					this.traceGraph.getProperty(s.getPropertyName(), element);
			
			tScope.addProperty(property);
		}
		
	}

	public void addChild(Tree arg0) {
		wrapped.addChild(arg0);
	}

	public void addChildren(List arg0) {
		wrapped.addChildren(arg0);
	}

	public boolean getBooleanAnnotationValue(String name, IEolContext context,
			boolean ifNotExists, boolean ifNoValue) throws EolRuntimeException {
		return wrapped.getBooleanAnnotationValue(name, context, ifNotExists,
				ifNoValue);
	}

	public void build() {
		wrapped.build();
	}

	public List<Object> getAnnotationsValues(String name, IEolContext context)
			throws EolRuntimeException {
		return wrapped.getAnnotationsValues(name, context);
	}

	public boolean appliesTo(Object object, IEvlContext context)
			throws EolRuntimeException {
		return wrapped.appliesTo(object, context);
	}

	public Object deleteChild(int arg0) {
		return wrapped.deleteChild(arg0);
	}

	public Tree dupNode() {
		return wrapped.dupNode();
	}

	public boolean equals(Object obj) {
		return wrapped.equals(obj);
	}

	public void freshenParentAndChildIndexes() {
		wrapped.freshenParentAndChildIndexes();
	}

	public void freshenParentAndChildIndexes(int arg0) {
		wrapped.freshenParentAndChildIndexes(arg0);
	}

	public AnnotationBlock getAnnotationBlock() {
		return wrapped.getAnnotationBlock();
	}

	public boolean getBooleanAnnotationValue(String name, IEolContext context)
			throws EolRuntimeException {
		return wrapped.getBooleanAnnotationValue(name, context);
	}

	public AST getAnnotationsAst() {
		return wrapped.getAnnotationsAst();
	}

	public String getBasename() {
		return wrapped.getBasename();
	}

	public Collection getAllOfSourceType(IEvlContext context)
			throws EolModelElementTypeNotFoundException,
			EolModelNotFoundException {
		return wrapped.getAllOfSourceType(context);
	}

	public Collection getAllOfSourceKind(IEvlContext context)
			throws EolModelElementTypeNotFoundException,
			EolModelNotFoundException {
		return wrapped.getAllOfSourceKind(context);
	}

	public int getCharPositionInLine() {
		return wrapped.getCharPositionInLine();
	}

	public AST getChild(int i) {
		return wrapped.getChild(i);
	}

	public int getChildCount() {
		return wrapped.getChildCount();
	}

	public int getChildIndex() {
		return wrapped.getChildIndex();
	}

	public List<Comment> getComments() {
		return wrapped.getComments();
	}

	public String getDebugInfo() {
		return wrapped.getDebugInfo();
	}

	public List<ModuleElement> getModuleElements() {
		return wrapped.getModuleElements();
	}

	public IModule getModule() {
		return wrapped.getModule();
	}

	public HashMap<String, Object> getProperties() {
		return wrapped.getProperties();
	}

	public File getFile() {
		return wrapped.getFile();
	}

	public Constraints getConstraints() {
		return wrapped.getConstraints();
	}

	public List<AST> getChildren() {
		return wrapped.getChildren();
	}

	public AST getParent() {
		return wrapped.getParent();
	}

	public int getLine() {
		return wrapped.getLine();
	}

	public String getName() {
		return wrapped.getName();
	}

	public int getColumn() {
		return wrapped.getColumn();
	}

	public AST getNextSibling() {
		return wrapped.getNextSibling();
	}

	public int getNumberOfChildren() {
		return wrapped.getNumberOfChildren();
	}

	public AST getFirstChild() {
		return wrapped.getFirstChild();
	}

	public AST getFourthChild() {
		return wrapped.getFourthChild();
	}

	public List<AST> getDescendants() {
		return wrapped.getDescendants();
	}

	public List<Token> getExtraTokens() {
		return wrapped.getExtraTokens();
	}

	public List<Token> getCommentTokens() {
		return wrapped.getCommentTokens();
	}

	public Tree getFirstChildWithType(int arg0) {
		return wrapped.getFirstChildWithType(arg0);
	}

	public AST getSecondChild() {
		return wrapped.getSecondChild();
	}

	public AST getThirdChild() {
		return wrapped.getThirdChild();
	}

	public Region getRegion() {
		return wrapped.getRegion();
	}

	public String getText() {
		return wrapped.getText();
	}

	public Token getToken() {
		return wrapped.getToken();
	}

	public int getTokenStartIndex() {
		return wrapped.getTokenStartIndex();
	}

	public int getTokenStopIndex() {
		return wrapped.getTokenStopIndex();
	}

	public int getType() {
		return wrapped.getType();
	}

	public void setAnnotationBlock(AnnotationBlock annotationBlock) {
		wrapped.setAnnotationBlock(annotationBlock);
	}

	public boolean hasAnnotation(String name) {
		return wrapped.hasAnnotation(name);
	}

	public int hashCode() {
		return wrapped.hashCode();
	}

	public String getTypeName() {
		return wrapped.getTypeName();
	}

	public AST setAnnotationsAst(AST annotations) {
		return wrapped.setAnnotationsAst(annotations);
	}

	public URI getUri() {
		return wrapped.getUri();
	}

	public boolean hasChildren() {
		return wrapped.hasChildren();
	}

	public boolean isImaginary() {
		return wrapped.isImaginary();
	}

	public boolean isNil() {
		return wrapped.isNil();
	}

	public void replaceChildren(int arg0, int arg1, Object arg2) {
		wrapped.replaceChildren(arg0, arg1, arg2);
	}

	public void sanityCheckParentAndChildIndexes() {
		wrapped.sanityCheckParentAndChildIndexes();
	}

	public void sanityCheckParentAndChildIndexes(Tree arg0, int arg1) {
		wrapped.sanityCheckParentAndChildIndexes(arg0, arg1);
	}

	public void setChild(int arg0, Tree arg1) {
		wrapped.setChild(arg0, arg1);
	}

	public void setChildIndex(int arg0) {
		wrapped.setChildIndex(arg0);
	}

	public AST setModule(IModule module) {
		return wrapped.setModule(module);
	}

	public void setUri(URI uri) {
		wrapped.setUri(uri);
	}

	public AST setLine(int line) {
		return wrapped.setLine(line);
	}

	public AST setColumn(int column) {
		return wrapped.setColumn(column);
	}

	public void setName(String name) {
		wrapped.setName(name);
	}

	public String toString() {
		return wrapped.toString();
	}

	public AST setNextSibling(AST sibling) {
		return wrapped.setNextSibling(sibling);
	}

	public AST setFirstChild(AST child) {
		return wrapped.setFirstChild(child);
	}

	public void setToken(Token token) {
		wrapped.setToken(token);
	}

	public void setRegion(Region region) {
		wrapped.setRegion(region);
	}

	public AST setImaginary(boolean imaginary) {
		return wrapped.setImaginary(imaginary);
	}

	public void setExtraTokens(List<Token> extraTokens) {
		wrapped.setExtraTokens(extraTokens);
	}

	public void setCommentTokens(List<Token> comments) {
		wrapped.setCommentTokens(comments);
	}

	public void setParent(Tree arg0) {
		wrapped.setParent(arg0);
	}

	public void setTokenStartIndex(int arg0) {
		wrapped.setTokenStartIndex(arg0);
	}

	public void setTokenStopIndex(int arg0) {
		wrapped.setTokenStopIndex(arg0);
	}

	public String toExtendedStringTreeItem() {
		return wrapped.toExtendedStringTreeItem();
	}

	public String toExtendedStringTree() {
		return wrapped.toExtendedStringTree();
	}

	public String toStringTree() {
		return wrapped.toStringTree();
	}

}
