package pedrohk.beanscope.config

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import pedrohk.beanscope.model.DeveloperWorkspace
import pedrohk.beanscope.model.ScopeSnapshot
import pedrohk.beanscope.service.PrototypeSessionService
import pedrohk.beanscope.service.SingletonWorkspaceService

@Configuration
class BeanScopeConfiguration {

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  def developerWorkspace(): DeveloperWorkspace = {
    new DeveloperWorkspace(
      "Pedro Henrique",
      System.nanoTime()
    )
  }

  @Bean
  def singletonWorkspaceService(
                                 developerWorkspace: DeveloperWorkspace
                               ): SingletonWorkspaceService = {
    new SingletonWorkspaceService(
      developerWorkspace
    )
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  def scopeSnapshot(
                     developerWorkspace: DeveloperWorkspace
                   ): ScopeSnapshot = {
    new ScopeSnapshot(
      developerWorkspace.identity,
      java.util.UUID.randomUUID().toString
    )
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  def prototypeSessionService(
                               scopeSnapshot: ScopeSnapshot
                             ): PrototypeSessionService = {
    new PrototypeSessionService(
      scopeSnapshot
    )
  }

}