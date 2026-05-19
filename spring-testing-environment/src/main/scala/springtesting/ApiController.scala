package springtesting

class ApiController(private val environment: Environment) {

  def handleRequest(path: String, method: String): Response = {
    if (path == "/info" && method == "GET") {
      val appName = environment.getProperty("app.name", "UnknownApp")
      val stage = environment.getProperty("app.stage", "production")
      new Response(200, s"App: $appName, Stage: $stage")
    } else if (path == "/admin" && method == "GET") {
      if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")) {
        new Response(200, "Admin access granted for non-prod profile")
      } else {
        new Response(403, "Access denied in production profile")
      }
    } else {
      new Response(404, "Not Found")
    }
  }
}
