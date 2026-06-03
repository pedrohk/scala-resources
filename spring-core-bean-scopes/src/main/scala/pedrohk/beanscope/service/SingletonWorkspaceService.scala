package pedrohk.beanscope.service

import pedrohk.beanscope.model.DeveloperWorkspace

class SingletonWorkspaceService(
                                 private val workspace: DeveloperWorkspace
                               ) {

  def workspaceId(): String = {
    workspace.identity
  }

  def owner(): String = {
    workspace.owner
  }

}