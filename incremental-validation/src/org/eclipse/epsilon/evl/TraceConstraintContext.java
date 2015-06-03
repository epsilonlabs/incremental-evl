package org.eclipse.epsilon.evl;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
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

/**
 * Decorator class for {@link ConstraintContext} for use in incremental
 * validation. This class is used to trace property accesses during the
 * evaluation of a constraint, in order to build a scope set.
 * 
 * @author Jonathan Co
 *
 */
public class TraceConstraintContext extends ConstraintContext {

	private final ConstraintContext ctx;
	private final Collection<RuleInstance> ruleInstances = new ArrayList<RuleInstance>();

	public TraceConstraintContext(ConstraintContext ctx) {
		this.ctx = ctx;
	}
	
	public Collection<RuleInstance> getRuleInstances() {
		return this.ruleInstances;
	}
	
	@Override
	public void checkAll(IEvlContext context) throws EolRuntimeException {
		// Get all the elements that are of the required kind

		Collection allOfKind = getAllOfSourceKind(context);
		Iterator it = allOfKind.iterator();

		// Iterator over each object of kind
		while (it.hasNext()) {
			Object object = it.next();

			// If constraint context applies to object then perform the
			// constraint checkE
			if (appliesTo(object, context)) {
				Iterator cit = getConstraints().values().iterator();
				while (cit.hasNext()) {
					Constraint constraint = (Constraint) cit.next();
					if (!constraint.isLazy(context)
							&& constraint.appliesTo(object, context)) {
											
						// Set a listener to trace property accesses
						ScopeBuilderListener listener = new ScopeBuilderListener();
						context.getExecutorFactory().addExecutionListener(listener);
						// Perform actual checks
						constraint.check(object, context);
						
						// FIXME: Move to own method
						// Build RuleInstance
						this.ruleInstances.add(
								new RuleInstance(constraint.getName(), 
										context.getModelRepository().getOwningModel(object).getElementId(object), 
										listener.getScopes()));
						
						// Clean-up the listener
						context.getExecutorFactory().removeExecutionListener(listener);
					}
				}

			}
		}
	}

	public void addChild(Tree arg0) {
		ctx.addChild(arg0);
	}

	public void addChildren(List arg0) {
		ctx.addChildren(arg0);
	}

	public boolean getBooleanAnnotationValue(String name, IEolContext context,
			boolean ifNotExists, boolean ifNoValue) throws EolRuntimeException {
		return ctx.getBooleanAnnotationValue(name, context, ifNotExists,
				ifNoValue);
	}

	public void build() {
		ctx.build();
	}

	public List<Object> getAnnotationsValues(String name, IEolContext context)
			throws EolRuntimeException {
		return ctx.getAnnotationsValues(name, context);
	}

	public boolean appliesTo(Object object, IEvlContext context)
			throws EolRuntimeException {
		return ctx.appliesTo(object, context);
	}

	public Object deleteChild(int arg0) {
		return ctx.deleteChild(arg0);
	}

	public Tree dupNode() {
		return ctx.dupNode();
	}

	public boolean equals(Object obj) {
		return ctx.equals(obj);
	}

	public void freshenParentAndChildIndexes() {
		ctx.freshenParentAndChildIndexes();
	}

	public void freshenParentAndChildIndexes(int arg0) {
		ctx.freshenParentAndChildIndexes(arg0);
	}

	public AnnotationBlock getAnnotationBlock() {
		return ctx.getAnnotationBlock();
	}

	public boolean getBooleanAnnotationValue(String name, IEolContext context)
			throws EolRuntimeException {
		return ctx.getBooleanAnnotationValue(name, context);
	}

	public AST getAnnotationsAst() {
		return ctx.getAnnotationsAst();
	}

	public String getBasename() {
		return ctx.getBasename();
	}

	public Collection getAllOfSourceType(IEvlContext context)
			throws EolModelElementTypeNotFoundException,
			EolModelNotFoundException {
		return ctx.getAllOfSourceType(context);
	}

	public Collection getAllOfSourceKind(IEvlContext context)
			throws EolModelElementTypeNotFoundException,
			EolModelNotFoundException {
		return ctx.getAllOfSourceKind(context);
	}

	public int getCharPositionInLine() {
		return ctx.getCharPositionInLine();
	}

	public AST getChild(int i) {
		return ctx.getChild(i);
	}

	public int getChildCount() {
		return ctx.getChildCount();
	}

	public int getChildIndex() {
		return ctx.getChildIndex();
	}

	public List<Comment> getComments() {
		return ctx.getComments();
	}

	public String getDebugInfo() {
		return ctx.getDebugInfo();
	}

	public List<ModuleElement> getModuleElements() {
		return ctx.getModuleElements();
	}

	public IModule getModule() {
		return ctx.getModule();
	}

	public HashMap<String, Object> getProperties() {
		return ctx.getProperties();
	}

	public File getFile() {
		return ctx.getFile();
	}

	public Constraints getConstraints() {
		return ctx.getConstraints();
	}

	public List<AST> getChildren() {
		return ctx.getChildren();
	}

	public AST getParent() {
		return ctx.getParent();
	}

	public int getLine() {
		return ctx.getLine();
	}

	public String getName() {
		return ctx.getName();
	}

	public int getColumn() {
		return ctx.getColumn();
	}

	public AST getNextSibling() {
		return ctx.getNextSibling();
	}

	public int getNumberOfChildren() {
		return ctx.getNumberOfChildren();
	}

	public AST getFirstChild() {
		return ctx.getFirstChild();
	}

	public AST getFourthChild() {
		return ctx.getFourthChild();
	}

	public List<AST> getDescendants() {
		return ctx.getDescendants();
	}

	public List<Token> getExtraTokens() {
		return ctx.getExtraTokens();
	}

	public List<Token> getCommentTokens() {
		return ctx.getCommentTokens();
	}

	public Tree getFirstChildWithType(int arg0) {
		return ctx.getFirstChildWithType(arg0);
	}

	public AST getSecondChild() {
		return ctx.getSecondChild();
	}

	public AST getThirdChild() {
		return ctx.getThirdChild();
	}

	public Region getRegion() {
		return ctx.getRegion();
	}

	public String getText() {
		return ctx.getText();
	}

	public Token getToken() {
		return ctx.getToken();
	}

	public int getTokenStartIndex() {
		return ctx.getTokenStartIndex();
	}

	public int getTokenStopIndex() {
		return ctx.getTokenStopIndex();
	}

	public int getType() {
		return ctx.getType();
	}

	public void setAnnotationBlock(AnnotationBlock annotationBlock) {
		ctx.setAnnotationBlock(annotationBlock);
	}

	public boolean hasAnnotation(String name) {
		return ctx.hasAnnotation(name);
	}

	public int hashCode() {
		return ctx.hashCode();
	}

	public String getTypeName() {
		return ctx.getTypeName();
	}

	public AST setAnnotationsAst(AST annotations) {
		return ctx.setAnnotationsAst(annotations);
	}

	public URI getUri() {
		return ctx.getUri();
	}

	public boolean hasChildren() {
		return ctx.hasChildren();
	}

	public boolean isImaginary() {
		return ctx.isImaginary();
	}

	public boolean isNil() {
		return ctx.isNil();
	}

	public void replaceChildren(int arg0, int arg1, Object arg2) {
		ctx.replaceChildren(arg0, arg1, arg2);
	}

	public void sanityCheckParentAndChildIndexes() {
		ctx.sanityCheckParentAndChildIndexes();
	}

	public void sanityCheckParentAndChildIndexes(Tree arg0, int arg1) {
		ctx.sanityCheckParentAndChildIndexes(arg0, arg1);
	}

	public void setChild(int arg0, Tree arg1) {
		ctx.setChild(arg0, arg1);
	}

	public void setChildIndex(int arg0) {
		ctx.setChildIndex(arg0);
	}

	public AST setModule(IModule module) {
		return ctx.setModule(module);
	}

	public void setUri(URI uri) {
		ctx.setUri(uri);
	}

	public AST setLine(int line) {
		return ctx.setLine(line);
	}

	public AST setColumn(int column) {
		return ctx.setColumn(column);
	}

	public void setName(String name) {
		ctx.setName(name);
	}

	public String toString() {
		return ctx.toString();
	}

	public AST setNextSibling(AST sibling) {
		return ctx.setNextSibling(sibling);
	}

	public AST setFirstChild(AST child) {
		return ctx.setFirstChild(child);
	}

	public void setToken(Token token) {
		ctx.setToken(token);
	}

	public void setRegion(Region region) {
		ctx.setRegion(region);
	}

	public AST setImaginary(boolean imaginary) {
		return ctx.setImaginary(imaginary);
	}

	public void setExtraTokens(List<Token> extraTokens) {
		ctx.setExtraTokens(extraTokens);
	}

	public void setCommentTokens(List<Token> comments) {
		ctx.setCommentTokens(comments);
	}

	public void setParent(Tree arg0) {
		ctx.setParent(arg0);
	}

	public void setTokenStartIndex(int arg0) {
		ctx.setTokenStartIndex(arg0);
	}

	public void setTokenStopIndex(int arg0) {
		ctx.setTokenStopIndex(arg0);
	}

	public String toExtendedStringTreeItem() {
		return ctx.toExtendedStringTreeItem();
	}

	public String toExtendedStringTree() {
		return ctx.toExtendedStringTree();
	}

	public String toStringTree() {
		return ctx.toStringTree();
	}

}
