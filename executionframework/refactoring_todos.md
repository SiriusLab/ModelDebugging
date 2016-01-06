## actions to do on executionframework before distribution

* org.gemoc.gemoc_language_workbench.api
  * move out concurrent related concepts such as :
    * api.moc.ISolver
    * INonDeterministicExecutionEngine
    * api.extension.deciders
    * ILogicalStepDecider
  * move out engine specific concepts suc as
    * IDeterministicExecutionEngine
  * clean IEngineAddon from concurrent concepts, move them to concurrent execution engine
* org.gemoc.execution.engine
  * rename into ??
