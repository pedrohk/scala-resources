package springintegration

class UserController(private val userService: UserService) {

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

}
