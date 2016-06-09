# ModelDebugging
Runtime and tools to bring execution, debug and simulation in your domain specific tooling built with Sirius and/or Eclipse Modeling Framework.

This project is already used by [GEMOC](http://gemoc.org/ "GEMOC Homepage") and [Arduino Designer](https://github.com/mbats/arduino "Arduino Designer project page").

This project is still in incubation and need more work to reach industrial grade. If you need some help to get started with this project or have any questions about it feel free to contact [me (Yvan Lussaud)](mailto:yvan.lussaud@obeo.fr).

The repository includes several components:
- ___simulationmodelanimation___ The core plugins required to animate models with Sirius
- ___framework___ contains the components used to build execution engines with model debugging support. One part (xdsml_framework) is dedicated to build the components for the language workbench (ie. provides support for designing xdml languages). Another part (execution_framework) contains the components used to build the engine runtime for executing models.
- ___trace___ contains the part of the framework dedicated to capture execution traces and thus enable omniscient debugging capabilities of the framework.
- ___java_execution___ contains an implementation of the framework using a sequential java engine. It features a multidimentaional timeline and omniscient debugging facilities.
