package springvalue

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers

  class ValueAnnotationTest extends AnyFunSuite with Matchers {

    test("Processor should inject properties and resolve defaults correctly") {
      val props = new java.util.HashMap[String, String]()
      props.put("app.version", "1.0.2")

      val resolver = new PropertyResolver(props)
      val processor = new ValueAnnotationProcessor(resolver)
      val config = new AppConfig()

      processor.process(config)

      config.getAppName shouldBe "DefaultApp"
      config.getAppVersion shouldBe "1.0.2"
      config.getTimeout shouldBe "5000"
      config.getStaticValue shouldBe "StaticLiteralValue"
    }

    test("Processor should overwrite default configuration values when properties exist") {
      val props = new java.util.HashMap[String, String]()
      props.put("app.name", "CustomScalaApp")
      props.put("app.version", "2.0.0")
      props.put("app.timeout", "9000")

      val resolver = new PropertyResolver(props)
      val processor = new ValueAnnotationProcessor(resolver)
      val config = new AppConfig()

      processor.process(config)

      config.getAppName shouldBe "CustomScalaApp"
      config.getAppVersion shouldBe "2.0.0"
      config.getTimeout shouldBe "9000"
    }

    test("Resolver should throw IllegalArgumentException when placeholder missing and no default given") {
      val emptyProps = new java.util.HashMap[String, String]()
      val resolver = new PropertyResolver(emptyProps)
      val processor = new ValueAnnotationProcessor(resolver)
      val config = new AppConfig()

      assertThrows[IllegalArgumentException] {
        processor.process(config)
      }
    }

  }

