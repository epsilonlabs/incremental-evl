package org.eclipse.epsilon.evl.incremental.orientdb.test.trace.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import org.eclipse.epsilon.evl.incremental.orientdb.execute.EvlOrientDbDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
 
/**
  * Test schema definition and CRUD operations on Orient DB Trace Model implementations
  * @author Horacio Hoyos
  *
  */
public class TraceModel {
	
	private static OrientGraphFactory factory;
	
	@BeforeClass
	public static void createFactory() {
		factory = new OrientGraphFactory("memory:evlTest");
	}
	
	@Before
	public void setupSchema() {
		EvlOrientDbDAO.setupSchema(factory);
		// Create some simple traces
		OrientGraph graph = factory.getTx();
		OrientVertex context = graph.addVertex("class:EvlContext",
				"uri", "file::/some/path/libary.evl/IncBook");
		OrientVertex const1 = graph.addVertex("class:EvlConstraint",
				"uri", "file::/some/path/libary.evl/IncBook.NumberOfCopiesValid");
		OrientVertex const2 = graph.addVertex("class:EvlConstraint",
				"uri", "file::/some/path/libary.evl/IncBook.NumberOfBorrowersValid");
		OrientVertex check1 = graph.addVertex("class:Check",
				"uri", "file::/some/path/libary.evl/IncBook.NumberOfCopiesValid.check",
				"result", "true");
		OrientVertex check2 = graph.addVertex("class:Check",
				"uri", "file::/some/path/libary.evl/IncBook.NumberOfBorrowersValid.check",
				"result", "false");
		OrientVertex message2 = graph.addVertex("class:Message",
				"uri", "file::/some/path/libary.evl/IncBook.NumberOfBorrowersValid.message",
				"result", "null");
		OrientVertex book = graph.addVertex("class:ModelElement",
				"uri", "file::/some/path/libary.model/Library/stock/0");
		OrientVertex borr1 = graph.addVertex("class:ModelElement",
				"uri", "file::/some/path/libary.model/Library/borrowers/0");
		OrientVertex borr2 = graph.addVertex("class:ModelElement",
				"uri", "file::/some/path/libary.model/Library/borrowers/1");
		OrientVertex prop1 = graph.addVertex("class:Property",
				"uri", "file::/some/path/libary.model/Library/stock/0.title");
		OrientVertex prop2 = graph.addVertex("class:Property",
				"uri", "file::/some/path/libary.model/Library/stock/0.copies");
		OrientVertex prop3 = graph.addVertex("class:Property",
				"uri", "file::/some/path/libary.model/Library/stock/0.borrowers");
		graph.addEdge("class:Owner", check1, const1, null);
		graph.addEdge("class:Owner", check2, const2, null);
		graph.addEdge("class:Owner", message2, const2, null);
		OrientVertex trace1 = graph.addVertex("class:ElementTrace");
		graph.addEdge("class:Blocks", trace1, check1, null);
		graph.addEdge("class:Elements", trace1, book, null);
		graph.addEdge("class:Accesses", trace1, prop2, null);
		
		OrientVertex trace2 = graph.addVertex("class:ElementTrace");
		graph.addEdge("class:Blocks", trace2, check2, null);
		graph.addEdge("class:Blocks", trace2, book, null);
		graph.addEdge("class:Blocks", trace2, prop2, null);
		graph.addEdge("class:Blocks", trace2, prop3, null);
		
		OrientVertex trace3 = graph.addVertex("class:ElementTrace");
		graph.addEdge("class:Blocks", trace3, check2, null);
		graph.addEdge("class:Blocks", trace3, book, null);
		graph.addEdge("class:Blocks", trace3, prop1, null);
		graph.addEdge("class:Blocks", trace3, prop2, null);
		graph.addEdge("class:Blocks", trace3, prop3, null);
	
		
		graph.commit();
		graph.shutdown();
		
	}
	
	@After
	public void dropDB() {
		factory.drop();
	}
	
	@AfterClass
	public static void setupDB() {
		factory.close();
	}
	
	
	//Only valid if we can generate the test automatically... which is true for all the classes too....
	@Test
	public void testSchemaIsComplete() {
		OrientGraph g = factory.getTx();
		OrientVertexType t = g.getVertexType("ElementTrace");
		assertThat(t, is(notNullValue()));
		
		OrientEdgeType et = g.getEdgeType("Accesses");
	}
	

}
