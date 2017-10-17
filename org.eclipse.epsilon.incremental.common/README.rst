Epsilon Incremental Commons
===========================

Provides a set of common functionality for the Epsilon Incremental execution. This common
functionality includes EGl templates to generate the base code for each of the Epsilon languages.
The base code provides:
    - An Interface definition for each EClass
    - An abstract implementation that uses Gremlin pipes for EReference navigation
    - An implementation of the ITraceModel interface with tailored implementations of the methods
        that require type (EClass) and attribute (EStrucutralFeature) meta information.
    