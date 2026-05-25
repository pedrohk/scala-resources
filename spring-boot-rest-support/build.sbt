name := "spring-boot-rest-support"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala3-library" % "3.8.3",

  "org.springframework.boot" % "spring-boot-starter-web" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter" % "4.0.6",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test,

  "org.mockito" % "mockito-core" % "5.23.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "5.23.0" % Test,

  "org.mockito.scala" %% "mockito-scala-scalatest" % "1.17.37" % Test,

  "net.bytebuddy" % "byte-buddy" % "1.18.8" % Test,
  "net.bytebuddy" % "byte-buddy-agent" % "1.18.8" % Test
)

Test / javaOptions ++= Seq(
  "-Dnet.bytebuddy.experimental=true"
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)