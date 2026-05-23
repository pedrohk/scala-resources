package springintegration {

  class UserController(private val userRepository: UserRepository, private val securityContext: SecurityContext) {

    private var userService: UserService = new UserService(userRepository)

    def this(userService: UserService) = {
      this(null, null)
      this.userService = userService
    }

    def performGet(path: String): MockMvcResponse = {
      if (path.startsWith("/api/users/")) {
        val name = path.substring("/api/users/".length)
        userService.getUserProfile(name) match {
          case Some(user) => {
            new MockMvcResponse(200, s"""{"name":"${user.name}","role":"${user.role}"}""")
          }
          case None => {
            new MockMvcResponse(404, s"""{"error":"User not found"}""")
          }
        }
      } else {
        new MockMvcResponse(404, s"""{"error":"Not Found"}""")
      }
    }

    def performPost(path: String, body: String): MockMvcResponse = {
      if (path == "/api/users") {
        try {
          val nameToken = """"name":""""
          val roleToken = """"role":""""

          val nameIdx = body.indexOf(nameToken)
          val roleIdx = body.indexOf(roleToken)

          if (nameIdx == -1 || roleIdx == -1) {
            new MockMvcResponse(400, s"""{"error":"Invalid JSON payload"}""")
          } else {
            val nameStart = nameIdx + nameToken.length
            val nameEnd = body.indexOf('"', nameStart)
            val name = body.substring(nameStart, nameEnd)

            val roleStart = roleIdx + roleToken.length
            val roleEnd = body.indexOf('"', roleStart)
            val role = body.substring(roleStart, roleEnd)

            val user = userService.registerUser(name, role)
            new MockMvcResponse(201, s"""{"name":"${user.name}","role":"${user.role}"}""")
          }
        } catch {
          case _: Exception => {
            new MockMvcResponse(400, s"""{"error":"Bad Request"}""")
          }
        }
      } else {
        new MockMvcResponse(404, s"""{"error":"Not Found"}""")
      }
    }

    def handleRequest(method: String, path: String, authHeader: String, body: String): ResponseEntity[String] = {
      if (securityContext == null) {
        new ResponseEntity[String]("""{"error":"Security not configured"}""", 500)
      } else {
        val userOpt = securityContext.authenticate(authHeader)

        if (userOpt.isEmpty) {
          new ResponseEntity[String]("""{"error":"Unauthorized"}""", 401)
        } else {
          val username = userOpt.get

          if (path == "/api/admin" && username != "Pedro Henrique") {
            new ResponseEntity[String]("""{"error":"Forbidden"}""", 403)
          } else if (method == "POST" && path == "/api/users") {
            val res = performPost(path, body)
            new ResponseEntity[String](res.getBody, res.getStatus)
          } else if (method == "GET" && path.startsWith("/api/users/")) {
            val res = performGet(path)
            new ResponseEntity[String](res.getBody, res.getStatus)
          } else if (path == "/api/admin") {
            new ResponseEntity[String]("""{"status":"Welcome Admin Pedro Henrique"}""", 200)
          } else {
            new ResponseEntity[String]("""{"error":"Not Found"}""", 404)
          }
        }
      }
    }

  }

}
