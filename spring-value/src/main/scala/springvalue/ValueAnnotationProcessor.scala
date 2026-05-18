package springvalue

class ValueAnnotationProcessor(private val resolver: PropertyResolver) {

  def process(bean: AnyRef): Unit = {
    val clazz = bean.getClass
    val fields = clazz.getDeclaredFields

    var i = 0
    while (i < fields.length) {
      val field = fields(i)
      val annotationClass = classOf[Value].asInstanceOf[Class[? <: java.lang.annotation.Annotation]]

      if (field.isAnnotationPresent(annotationClass)) {
        val annotation = field.getAnnotation(classOf[Value])
        val expression = annotation.value()
        val resolvedValue = resolver.resolve(expression)

        field.setAccessible(true)
        field.set(bean, resolvedValue)
      }
      i += 1
    }
  }

}