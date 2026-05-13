package ioc

final case class BeanDefinition(
                                 name: String,
                                 beanClass: Class[?]
                               ) {
}