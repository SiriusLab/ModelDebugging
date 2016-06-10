# ModelDebugging
Runtime and tools to bring execution, debug and simulation in your domain specific tooling built with Sirius and/or Eclipse Modeling Framework.



The repository includes several components:
- ___simulationmodelanimation___ The plugins required to animate models with Sirius
- ___framework___ contains the components used to implement execution engines with model debugging support. The framework is two folds: 
 - *xdsml_framework* The plugins for the language workbench (ie. plugins that provides support for designing xdml languages using a given engine). 
 - *execution_framework* The plugins for the modeling workbench (ie. the engine runtime for executing models).
- ___trace___ The plugins dedicated to execution traces. It offers two traces implementations that cans be used by the framework:
 - a simple generic trace metamodel 
 - an efficient trace metamodel powered by a generative approach. This trace enables all omniscient debugging capabilities of the framework and offers a multidimentional timeline.
- ___java_execution___ contains an implementation of the framework using a sequential java engine (based on xtend+k3). 

This project is already used by [GEMOC](http://gemoc.org/ "GEMOC Homepage") and [Arduino Designer](https://github.com/mbats/arduino "Arduino Designer project page").



This project is still in incubation and need more work to reach industrial grade. If you need some help to get started with this project or have any questions about it feel free to contact [me (Yvan Lussaud)](mailto:yvan.lussaud@obeo.fr).
