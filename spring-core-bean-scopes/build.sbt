ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "3.8.3"

lazy val root = (project in file("."))
  .settings(
    name := "spring-core-bean-scopes",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ),
    testFrameworks += new TestFramework("org.scalatest.tools.Framework")
  )