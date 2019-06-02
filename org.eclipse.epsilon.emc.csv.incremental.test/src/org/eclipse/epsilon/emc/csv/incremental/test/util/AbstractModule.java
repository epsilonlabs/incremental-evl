package org.eclipse.epsilon.emc.csv.incremental.test.util;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.module.ModuleMarker;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.common.parse.Region;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;

public abstract class AbstractModule implements IModuleIncremental {

//	@Override
//	public IModuleTrace getModuleTrace() {
//		// TODO Implement AbstractModule.getModuleTrace
//		throw new UnsupportedOperationException("Unimplemented Method    AbstractModule.getModuleTrace invoked.");
//	}

	@Override
	public Set<IIncrementalModel> getTargets() {
		// TODO Implement AbstractModule.getTargets
		throw new UnsupportedOperationException("Unimplemented Method    AbstractModule.getTargets invoked.");
	}

	@Override
	public void listenToModelChanges(boolean listen) {
		// TODO Implement AbstractModule.listenToModelChanges
		throw new UnsupportedOperationException("Unimplemented Method    AbstractModule.listenToModelChanges invoked.");
	}

	@Override
	public boolean parse(File file) throws Exception {
		// TODO Implement IModule.parse
		throw new UnsupportedOperationException("Unimplemented Method    IModule.parse invoked.");
	}

	@Override
	public boolean parse(URI uri) throws Exception {
		// TODO Implement IModule.parse
		throw new UnsupportedOperationException("Unimplemented Method    IModule.parse invoked.");
	}

	@Override
	public boolean parse(String code) throws Exception {
		// TODO Implement IModule.parse
		throw new UnsupportedOperationException("Unimplemented Method    IModule.parse invoked.");
	}

	@Override
	public boolean parse(String code, File file) throws Exception {
		// TODO Implement IModule.parse
		throw new UnsupportedOperationException("Unimplemented Method    IModule.parse invoked.");
	}

	@Override
	public List<ModuleMarker> compile() {
		// TODO Implement IModule.compile
		throw new UnsupportedOperationException("Unimplemented Method    IModule.compile invoked.");
	}

	@Override
	public URI getSourceUri() {
		// TODO Implement IModule.getSourceUri
		throw new UnsupportedOperationException("Unimplemented Method    IModule.getSourceUri invoked.");
	}

	@Override
	public List<ParseProblem> getParseProblems() {
		// TODO Implement IModule.getParseProblems
		throw new UnsupportedOperationException("Unimplemented Method    IModule.getParseProblems invoked.");
	}

	@Override
	public ModuleElement createAst(AST cst, ModuleElement parentAst) {
		// TODO Implement IModule.createAst
		throw new UnsupportedOperationException("Unimplemented Method    IModule.createAst invoked.");
	}

	@Override
	public File getFile() {
		// TODO Implement ModuleElement.getFile
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getFile invoked.");
	}

	@Override
	public URI getUri() {
		// TODO Implement ModuleElement.getUri
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getUri invoked.");
	}

	@Override
	public void setUri(URI uri) {
		// TODO Implement ModuleElement.setUri
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.setUri invoked.");
	}

	@Override
	public void setModule(IModule module) {
		// TODO Implement ModuleElement.setModule
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.setModule invoked.");
	}

	@Override
	public void build(AST cst, IModule module) {
		// TODO Implement ModuleElement.build
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.build invoked.");
	}

	@Override
	public Region getRegion() {
		// TODO Implement ModuleElement.getRegion
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getRegion invoked.");
	}

	@Override
	public void setRegion(Region region) {
		// TODO Implement ModuleElement.setRegion
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.setRegion invoked.");
	}

	@Override
	public ModuleElement getParent() {
		// TODO Implement ModuleElement.getParent
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getParent invoked.");
	}

	@Override
	public void setParent(ModuleElement moduleElement) {
		// TODO Implement ModuleElement.setParent
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.setParent invoked.");
	}

	@Override
	public List<ModuleElement> getChildren() {
		// TODO Implement ModuleElement.getChildren
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getChildren invoked.");
	}

	@Override
	public IModule getModule() {
		// TODO Implement ModuleElement.getModule
		throw new UnsupportedOperationException("Unimplemented Method    ModuleElement.getModule invoked.");
	}

}
