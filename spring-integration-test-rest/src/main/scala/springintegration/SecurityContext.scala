package springintegration {

  import java.util.Base64
  import java.nio.charset.StandardCharsets

  class SecurityContext {

    def authenticate(authHeader: String): Option[String] = {
      if (authHeader != null && authHeader.startsWith("Basic ")) {
        try {
          val base64Credentials = authHeader.substring(6)
          val credBytes = Base64.getDecoder.decode(base64Credentials)
          val credentials = new String(credBytes, StandardCharsets.UTF_8)
          val values = credentials.split(":", 2)
          if (values.length == 2 && values(1) == "secret123") {
            Some(values(0))
          } else {
            None
          }
        } catch {
          case _: Exception => {
            None
          }
        }
      } else {
        None
      }
    }

  }

}
