package org.eclipse.epsilon.evl.incremental.util;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import com.arangodb.tinkerpop.gremlin.structure.ArangoDBGraph;

public class ArangoDBEvlUtil {

    private static final String GRAPH_NAME = "IncrementalEvl";

    /**
     * Create a base configuration that provides the required ArangoDB schema. This can the be
     * augmented with the user specific information: uri, user:passwrd, etc. 
     * @return The base Apache Configuration
     */
    public static Configuration getBaseConfiguration() {
        final Configuration conf = new BaseConfiguration();
        // Respect the commas in relations
        ((AbstractConfiguration) conf).setListDelimiter('/');
        
        conf.addProperty(Graph.GRAPH, ArangoDBGraph.class.getName());
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + "." + ArangoDBGraph.CONFIG_GRAPH_NAME, GRAPH_NAME);
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "EvlModuleTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ContextTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "InvariantTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "GuardResult");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "GuardTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "CheckResult");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "CheckTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "MessageTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "SatisfiesTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ModelAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ExecutionContext");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ModelElementVariable");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ElementAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "AllInstancesAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "PropertyAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ModelTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ModelTypeTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "ModelElementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.vertex", "PropertyTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "modelTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "modelTrace:ModelTypeTrace,ModelElementTrace,ModelAccess->ModelTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "guard");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "guard:InvariantTrace,ContextTrace->GuardTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "type");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "type:AllInstancesAccess,ModelElementTrace->ModelTypeTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "constraints");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "constraints:ContextTrace->InvariantTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "result");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "result:GuardTrace,CheckTrace->GuardResult,CheckResult");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "elementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "elementTrace:PropertyTrace->ModelElementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "satisfies");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "satisfies:InvariantTrace->SatisfiesTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "satisfiedInvariants");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "satisfiedInvariants:SatisfiesTrace->InvariantTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "invariantContext");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "invariantContext:InvariantTrace->ContextTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "executionContext");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "executionContext:ContextTrace->ExecutionContext");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "context");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "context:GuardResult,CheckResult->ExecutionContext");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "contextVariables");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "contextVariables:ExecutionContext->ModelElementVariable");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "property");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "property:PropertyAccess->PropertyTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "from");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "from:ElementAccess,AllInstancesAccess,PropertyAccess->InvariantTrace,ContextTrace,GuardTrace,MessageTrace,SatisfiesTrace,CheckTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "accesses");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "accesses:ExecutionContext->ElementAccess,AllInstancesAccess,PropertyAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "value");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "value:ModelElementVariable->ModelElementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "limits");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "limits:GuardTrace->InvariantTrace,ContextTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "element");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "element:ElementAccess->ModelElementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "models");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "models:EvlModuleTrace->ModelAccess");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "types");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "types:ModelTrace->ModelTypeTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "kind");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "kind:ModelElementTrace->ModelTypeTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "contextModuleElement");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "contextModuleElement:InvariantTrace,GuardTrace,MessageTrace,SatisfiesTrace,CheckTrace->ContextTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "check");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "check:InvariantTrace->CheckTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "message");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "message:InvariantTrace->MessageTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "invariant");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "invariant:MessageTrace,SatisfiesTrace,CheckTrace->InvariantTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "elements");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "elements:ModelTrace->ModelElementTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "moduleElements");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "moduleElements:EvlModuleTrace->InvariantTrace,ContextTrace,GuardTrace,MessageTrace,SatisfiesTrace,CheckTrace");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.edge", "properties");
        conf.addProperty(ArangoDBGraph.ARANGODB_CONFIG_PREFIX + ".graph.relation", "properties:ModelElementTrace->PropertyTrace");
        return conf;
    }
}
