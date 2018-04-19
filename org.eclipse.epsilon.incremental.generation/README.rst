Epsilon Incremental Generation
==============================

Provides functionality for the Epsilon Incremental code generation. The generation
code consists of several EGL and EGX files. There are a set of reusable base templates
and new templates can be added for each additional Epsilon language.

For each language a new ExLExecutionTrace metamodel is needed. The metamodel should be
annotated with the following annotations to guide the code generation:

https://eclipse.org/epsilon/incremental/identity
  This annotation is used to define the EAttributes that can be used to give instances
  of a class an unique identity. This attributes are used in the implementation of the
  ``equals`` and hash ``methods``. EAttributes defines as identity will be used as 
  parameters for the class constructor. Further, they could be used to provide fields that
  define primary keys (or similar) for DB code generation.

https://eclipse.org/epsilon/incremental/equivalency
  This annotation is used to provide EReferences that are used, in addition to identity
  EAttributes, to provide instance equivalency. They are provided separately as in some
  cases, references to other elements can not be used for indexing. The EReferences
  added to this annotation should not result in loops (otherwise the hash and equals
  functions would result in infinite loops).  
    
