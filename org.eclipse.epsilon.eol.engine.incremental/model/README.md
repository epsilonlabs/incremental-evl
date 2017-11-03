# Purpose
In order to reduce the implementation effort, we will use the metamodel to generate Java code to
provide implementations that allow us to persist the execution trace model. Depending on the
modelling technology, the structural information of the metamodel can be sufficient to generate the
required code. For example, generating the EMF code would allow us to easily persist the trace model
as an EMF model. Further, the code will allow us to do all the required data manipulation.

For other modelling technologies additional information is required. The main reason is that the
EMF structural constructs may not have a direct mapping to the selected technology. For example, 
the concept of containment can not be directly mapped to database concepts and hence we need
additional information in order to correctly generate the code in such cases.

For this, we provide a set of predefined annotations that will allow us to guide the code
generation for other technologies. The purpose is that these annotations can be used to generate
code that can bridge the gap between concepts in EMF and the target technology.

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
  EReferences in the metamodel should be used as edges in the DB. If the EReference used for
  and edge has an opposite reference, the appropriate access methods will be added to the
  Vertex definitions.

## http://eclipse.org/epsilon/incremental/Index
The modelling technology can support the concept of index/PK. The index/PK is usually formed by one
or more features of the class and are intended to increase DB performance (looking elements by
index is faster). 

  The OrientDbIndex annotation allows information relevant to to Index definitions (see 
  http://orientdb.com/docs/last/Indexes.html). To define an index for an EClass, the annotation
  must reference the EAttribute that will be used as an index and must include a details entry
  where the key is "type" and the value is one of the supported index types.
  
  OrientDbIndex annotaions should only be added to EClasses, and will be ignored in any other
  metamodel elements. 
 
## http://eclipse.org/epsilon/incremental/UniqueIn


  ## https://eclipse.org/epsilon/incremental/OrientDbProperty ##

  By default the temaplte assumes that EAttributes use EPrmitive types (EString, EInt, etc.) as
  types and uses the correspoinding Java type to select the appropiate OType type when creating
  properties. For cases in which this is not the case, the OrientDbProperty can be used to have
  more control over the used type. THIS IS NOT IMPLEMENTED YET.
