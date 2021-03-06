# Incremental Epsilon Code Generation

Provides functionality for the Epsilon Incremental code generation. The generation
code consists of several EGL and EGX files. There are a set of reusable base templates
and new templates can be added for each additional Epsilon language.

## Purpose
In order to reduce the implementation effort, we will use the metamodel to generate Java code to
provide implementations that allow us to persist the execution trace model. Depending on the
modelling/persistence technology, the structural information of the metamodel can be sufficient
to generate the required code. For example, a basic POJO implementation based solely on structure
is generated by the EMF GenModel.

However, the structural information alone might not be enough for other modelling/persistence
technologies. The main reason is that the EMF structural constructs may not have a direct mapping to
the target technology. For example, the concept of containment does not have a 1-to-1 mapping to
database concepts, that is, containment could be modelled as a column, as a separate table, etc. 
As a result, we need additional information in order to correctly generate the code in such cases.

For this, we provide a set of predefined annotations that will allow us to guide the code
generation for other technologies. Some of this annotations can be shared, and others will be added
for specific technologies. The purpose is that these annotations can be used to generate
code that can bridge the gap between concepts in EMF and the target technology.

### Documenting the Metamodel itself
It is recommended to add documentation details to EClasses and EFeatures. This helps better
understand the metamodel and allows better javadoc generation. Focus on the semantic aspects of the
metamodel. For this use the `http://www.eclipse.org/emf/2002/GenModel`


## Language Specific
For each Epsilon language a new ExLExecutionTrace metamodel should be provided. The metamodel is 
expected to contain specialised classes to describe the CST components of the language. The
metamodel should be annotated with the following annotations to guide the code generation:

#### https://eclipse.org/epsilon/incremental/identity
  This annotation is used to define the EAttributes that can be used to give instances
  of a class an unique identity. This attributes are used in the implementation of the
  `equals` and `hashCode` methods. EAttributes defines as identity will be used as 
  parameters for the class constructor. Further, they could be used to provide fields that
  define primary keys (or similar) for DB code generation. Identity EAttributes are considered
  final and hence no setter will be generated

#### https://eclipse.org/epsilon/incremental/equivalency
  This annotation is used to provide EReferences that are used, in addition to identity
  EAttributes, to provide instance equivalence. They are provided separately as in some
  cases, references to other elements can not be used for indexing. The EReferences
  added to this annotation should not result in loops (otherwise the hash and equals
  functions would result in infinite loops). 
    
## Technology Specific

## DB Annotations
The main purpose of this annotations is to help the generation of DTO, DAO, EntityManager, etc.,
code for the trace model.

The EAnnotations sources supported are:
 
* http://eclipse.org/epsilon/incremental/Graph              for graph properties
* http://eclipse.org/epsilon/incremental/Index              for index/PK definitions
* http://eclipse.org/epsilon/incremental/Find               for find methods
* http://eclipse.org/epsilon/incremental/Property           for property definitions

### Graph
Not all structural information of the metamodel can be directly mapped to a graph. For example, 
edges might need to be directed, or inheritance flattened. The *Graph* annotation can be used to
determine what elements of the metamodel are used to define the structure (schema) of the graph.
For each particular DB the key--value pairs can hold different meaning.

#### OrientDB
Graph annotations should only be added to EReferences, and will be ignored in any other metamodel
elements.
    
- key: edge, value: true/false
    
  EMF models support bi--directional relationships between classes by defining one EReference
  as the opposite of the other. In OrientDB, a single edge is needed to define the relationship.
  In order to avoid double edges the OrientDbGraph annotation allow you to define which
  EReferences in the metamodel should be used as edges in the DB.
  To define an EReference as an edge, add the (edge, true) pair to the annotation values.
  If the EReference used for and edge has an opposite reference, the appropriate access methods
  will be added to the Vertex definitions.

## http://eclipse.org/epsilon/incremental/Index
The modelling technology can support the concept of index/PK. The index/PK is usually formed by one
or more features of the class and are intended to increase DB performance (looking elements by
index is faster). 

  The Index annotation allows information relevant to to Index definitions (see 
  http://orientdb.com/docs/last/Indexes.html). To define an index for an EClass, the annotation
  must reference the EAttribute that will be used as an index and must include a details entry
  where the key is "type" and the value is one of the supported index types.
  
  OrientDbIndex annotations should only be added to EClasses, and will be ignored in any other
  metamodel elements. 
 
## http://eclipse.org/epsilon/incremental/ValueObject
ValueOjects are created when needed, don't have ids and don't keep references to entities 


## https://eclipse.org/epsilon/incremental/Property

  By default the template assumes that EAttributes use EPrmitive types (EString, EInt, etc.) as
  types and uses the corresponding Java type to select the appropriate Type type when creating
  properties. For cases in which this is not the case, the Property can be used to have
  more control over the used type. THIS IS NOT IMPLEMENTED YET.


## http://eclipse.org/epsilon/incremental/UniqueIn