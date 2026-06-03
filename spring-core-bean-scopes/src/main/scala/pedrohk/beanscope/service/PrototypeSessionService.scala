package pedrohk.beanscope.service

import pedrohk.beanscope.model.ScopeSnapshot

class PrototypeSessionService(
                               private val snapshot: ScopeSnapshot
                             ) {

  def singletonReference(): String = {
    snapshot.singletonId
  }

  def prototypeReference(): String = {
    snapshot.prototypeId
  }

  def description(): String = {
    s"${snapshot.singletonId}:${snapshot.prototypeId}"
  }

}