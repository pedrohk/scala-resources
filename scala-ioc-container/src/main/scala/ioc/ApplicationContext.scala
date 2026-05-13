package ioc

import scala.collection.mutable

class ApplicationContext(classes: Seq[Class[?]]) {

  private val beans = mutable.LinkedHashMap[String, Any]()

  initialize()

  private def initialize(): Unit = {
    classes.foreach(createBean)
  }

  private def createBean(clazz: Class[?]): Any = {

    val beanName = clazz.getSimpleName

    beans.get(beanName) match {
      case Some(existing) => {
        existing
      }

      case None => {

        val constructor = clazz.getConstructors.headOption.getOrElse {
          throw new IllegalArgumentException(
            s"No public constructor found for ${clazz.getName}"
          )
        }

        val dependencies =
          constructor.getParameterTypes.toSeq.map(createBean)

        val instance =
          constructor.newInstance(
            dependencies.map(_.asInstanceOf[Object])*
          )

        beans.put(beanName, instance)

        instance
      }
    }
  }

  def getBean[T](clazz: Class[T]): T = {

    beans
      .get(clazz.getSimpleName)
      .map(_.asInstanceOf[T])
      .getOrElse {
        throw new IllegalArgumentException(
          s"Bean not found: ${clazz.getName}"
        )
      }
  }

  def containsBean(name: String): Boolean = {
    beans.contains(name)
  }

  def getBeanDefinitionNames(): List[String] = {
    beans.keys.toList.sorted
  }

  def registeredBeansCount(): Int = {
    beans.size
  }
}