package springrest

class MockHttpHandler {

  private val getRoutes: java.util.concurrent.ConcurrentHashMap[String, String] =
    new java.util.concurrent.ConcurrentHashMap()

  private val postRoutes: java.util.concurrent.ConcurrentHashMap[String, String] =
    new java.util.concurrent.ConcurrentHashMap()

  def registerGetRoute(url: String, jsonResponse: String): Unit = {
    getRoutes.put(url, jsonResponse)
  }

  def registerPostRoute(url: String, expectedPayload: String): Unit = {
    postRoutes.put(url, expectedPayload)
  }

  def executeGet(url: String): ResponseEntity[String] = {
    if (getRoutes.containsKey(url)) {
      new ResponseEntity[String](getRoutes.get(url), 200)
    } else {
      new ResponseEntity[String]("Not Found", 404)
    }
  }

  def executePost(url: String, requestBody: String): ResponseEntity[String] = {
    if (postRoutes.containsKey(url) && postRoutes.get(url) == requestBody) {
      new ResponseEntity[String](requestBody, 201)
    } else {
      new ResponseEntity[String]("Bad Request", 400)
    }
  }

}