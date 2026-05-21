package springintegration

class MockMvcResponse(private val status: Int, private val body: String) {

  def getStatus: Int = {
    this.status
  }

  def getBody: String = {
    this.body
  }

}