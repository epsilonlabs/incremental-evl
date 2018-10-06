package org.eclipse.epsilon.evl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.common.parse.EpsilonTreeAdaptor;
import org.eclipse.epsilon.common.util.AstUtil;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.parse.EvlParser;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that the correct EVL model elements are created in the trace model
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class PreexecutionTests {

	private IncrementalEvlModule module;
	private AST moduleAST;
	private File evlFile;

	@Before
	public void setup() throws URISyntaxException {
		module = new IncrementalEvlModule();
		evlFile = new File(PreexecutionTests.class.getResource("testPreExecution.evl").toURI());
	}

	@Test
	public void testAdaptCst() throws Exception {
		URI evlUri = evlFile.toURI();
		InputStream noTabsStream = evlUri.toURL().openStream();
		final Lexer lexer = module.createLexer(new ANTLRInputStream(noTabsStream));
		final CommonTokenStream stream = new CommonTokenStream(lexer);
		final EpsilonTreeAdaptor adaptor = new EpsilonTreeAdaptor(evlUri, module);

		EvlParser parser = new EvlParser(stream);
		parser.setDeepTreeAdaptor(adaptor);
		moduleAST = (AST) parser.evlModule().getTree();
		module.adapt(moduleAST, null);
		// One ConstraintContext
		List<AST> contexts = AstUtil.getChildren(moduleAST, EvlParser.CONTEXT);
		assertThat(contexts.size(), is(1));
		AST singleContextAST = contexts.get(0);
		ModuleElement constraintContextME = module.adapt(singleContextAST, module);
		assertThat(constraintContextME, instanceOf(TracedConstraintContext.class));
		List<AST> invariants = AstUtil.getChildren(singleContextAST, EvlParser.CONSTRAINT);
		assertThat(invariants.size(), is(4));

		// UnGuardedSimple
		AST unGuardedSimpleAST = invariants.get(0);
		ModuleElement unGuardedSimpleME = module.adapt(unGuardedSimpleAST, constraintContextME);
		assertThat(unGuardedSimpleME, instanceOf(TracedConstraint.class));
		unGuardedSimpleME.setParent(constraintContextME);
		constraintContextME.getChildren().add(unGuardedSimpleME);
		ModuleElement checkBlock0ME = module.adapt(AstUtil.getChild(unGuardedSimpleAST, EvlParser.CHECK),
				unGuardedSimpleME);
		assertThat(checkBlock0ME, instanceOf(TracedExecutableBlock.class));
		checkBlock0ME.setParent(unGuardedSimpleME);
		unGuardedSimpleME.getChildren().add(checkBlock0ME);

		// GuardedSimple
		AST guardedSimpleAST = invariants.get(1);
		ModuleElement guardedSimpleME = module.adapt(guardedSimpleAST, constraintContextME);
		assertThat(guardedSimpleME, instanceOf(TracedConstraint.class));
		guardedSimpleME.setParent(constraintContextME);
		constraintContextME.getChildren().add(guardedSimpleME);
		ModuleElement guardBlock1Me = module.adapt(AstUtil.getChild(guardedSimpleAST, EvlParser.GUARD),
				guardedSimpleME);
		assertThat(guardBlock1Me, instanceOf(TracedExecutableBlock.class));
		guardBlock1Me.setParent(guardedSimpleME);
		guardedSimpleME.getChildren().add(guardBlock1Me);
		ModuleElement checkBlock1ME = module.adapt(AstUtil.getChild(guardedSimpleAST, EvlParser.CHECK),
				guardedSimpleME);
		assertThat(checkBlock1ME, instanceOf(TracedExecutableBlock.class));
		checkBlock1ME.setParent(guardedSimpleME);
		guardedSimpleME.getChildren().add(checkBlock1ME);

		// GuardedWithMessage
		AST guardedWithMessageAST = invariants.get(2);
		ModuleElement guardedWithMessageME = module.adapt(guardedWithMessageAST, constraintContextME);
		assertThat(guardedWithMessageME, instanceOf(TracedConstraint.class));
		guardedWithMessageME.setParent(constraintContextME);
		constraintContextME.getChildren().add(guardedWithMessageME);
		ModuleElement guardBlock2Me = module.adapt(AstUtil.getChild(guardedWithMessageAST, EvlParser.GUARD),
				guardedWithMessageME);
		assertThat(guardBlock2Me, instanceOf(TracedExecutableBlock.class));
		guardBlock2Me.setParent(guardedWithMessageME);
		guardedWithMessageME.getChildren().add(guardBlock2Me);
		ModuleElement checkBlock2ME = module.adapt(AstUtil.getChild(guardedWithMessageAST, EvlParser.CHECK),
				guardedWithMessageME);
		assertThat(checkBlock2ME, instanceOf(TracedExecutableBlock.class));
		checkBlock2ME.setParent(guardedWithMessageME);
		guardedWithMessageME.getChildren().add(checkBlock2ME);
		ModuleElement messageBlock2Me = module.adapt(AstUtil.getChild(guardedWithMessageAST, EvlParser.MESSAGE),
				guardedWithMessageME);
		assertThat(messageBlock2Me, instanceOf(TracedExecutableBlock.class));
		messageBlock2Me.setParent(guardedWithMessageME);
		guardedWithMessageME.getChildren().add(messageBlock2Me);

		// UnguardedWithFix
		AST unguardedWithFixAST = invariants.get(3);
		ModuleElement unguardedWithFixME = module.adapt(unguardedWithFixAST, constraintContextME);
		assertThat(unguardedWithFixME, instanceOf(TracedConstraint.class));
		unguardedWithFixME.setParent(constraintContextME);
		constraintContextME.getChildren().add(unguardedWithFixME);
		ModuleElement checkBlock3ME = module.adapt(AstUtil.getChild(unguardedWithFixAST, EvlParser.CHECK),
				unguardedWithFixME);
		assertThat(checkBlock3ME, instanceOf(TracedExecutableBlock.class));
		checkBlock3ME.setParent(unguardedWithFixME);
		unguardedWithFixME.getChildren().add(checkBlock3ME);
		AST fixAST = AstUtil.getChild(unguardedWithFixAST, EvlParser.FIX);
		ModuleElement fixBlock3Me = module.adapt(fixAST, unguardedWithFixME);
		assertThat(fixBlock3Me, instanceOf(Fix.class));
		fixBlock3Me.setParent(unguardedWithFixME);
		unguardedWithFixME.getChildren().add(fixBlock3Me);
		// Fix's guard should not be traced
		ModuleElement fixGuardBlock3Me = module.adapt(AstUtil.getChild(fixAST, EvlParser.GUARD), fixBlock3Me);
		assertThat(fixGuardBlock3Me, instanceOf(ExecutableBlock.class));
		assertThat(fixGuardBlock3Me, not(instanceOf(TracedExecutableBlock.class)));
		fixGuardBlock3Me.setParent(fixBlock3Me);
		fixBlock3Me.getChildren().add(fixGuardBlock3Me);

		// CritiqueSimple
		AST critiqueSimpleAST = AstUtil.getChild(singleContextAST, EvlParser.CRITIQUE);
		ModuleElement critiqueSimpleME = module.adapt(critiqueSimpleAST, constraintContextME);
		assertThat(critiqueSimpleME, instanceOf(TracedConstraint.class));
		critiqueSimpleME.setParent(constraintContextME);
		constraintContextME.getChildren().add(critiqueSimpleME);
		ModuleElement checkBlock5ME = module.adapt(AstUtil.getChild(critiqueSimpleAST, EvlParser.CHECK),
				critiqueSimpleME);
		assertThat(checkBlock5ME, instanceOf(TracedExecutableBlock.class));
		checkBlock5ME.setParent(critiqueSimpleME);
		critiqueSimpleME.getChildren().add(checkBlock5ME);

	}

}