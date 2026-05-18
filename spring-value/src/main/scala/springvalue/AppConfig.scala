package springvalue

class AppConfig {

  @Value("${app.name:DefaultApp}")
  private var appName: String = ""

  @Value("${app.version}")
  private var appVersion: String = ""

  @Value("${app.timeout:5000}")
  private var timeout: String = ""

  @Value("StaticLiteralValue")
  private var staticValue: String = ""

  def getAppName: String = {
    this.appName
  }

  def getAppVersion: String = {
    this.appVersion
  }

  def getTimeout: String = {
    this.timeout
  }

  def getStaticValue: String = {
    this.staticValue
  }

}
