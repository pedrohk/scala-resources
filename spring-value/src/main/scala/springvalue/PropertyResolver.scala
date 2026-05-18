package springvalue

class PropertyResolver(private val properties: java.util.Map[String, String]) {

  def resolve(expression: String): String = {
    if (expression.startsWith("${") && expression.endsWith("}")) {
      val parts = expression.substring(2, expression.length - 1).split(":", 2)
      val key = parts(0)

      if (properties.containsKey(key)) {
        properties.get(key)
      } else if (parts.length == 2) {
        parts(1)
      } else {
        throw new IllegalArgumentException(s"Could not resolve placeholder '$key' and no default value was provided")
      }
    } else {
      expression
    }
  }

}


