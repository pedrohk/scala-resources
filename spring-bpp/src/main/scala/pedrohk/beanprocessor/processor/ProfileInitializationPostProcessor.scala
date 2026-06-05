package pedrohk.beanprocessor.processor

import org.springframework.beans.factory.config.BeanPostProcessor
import pedrohk.beanprocessor.service.DeveloperProfileService

class ProfileInitializationPostProcessor
  extends BeanPostProcessor {

  override def postProcessBeforeInitialization(
                                                bean: Any,
                                                beanName: String
                                              ): AnyRef = {

    bean match {

      case profile:
        DeveloperProfileService =>

        profile.setOwner(
          "Pedro Henrique"
        )

      case _ =>
    }

    bean.asInstanceOf[AnyRef]
  }

  override def postProcessAfterInitialization(
                                               bean: Any,
                                               beanName: String
                                             ): AnyRef = {

    bean match {

      case profile:
        DeveloperProfileService =>

        profile.setInitialized(
          true
        )

      case _ =>
    }

    bean.asInstanceOf[AnyRef]
  }

}