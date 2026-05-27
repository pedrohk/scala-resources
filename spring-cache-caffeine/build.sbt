name := "spring-cache-caffeine"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter-cache" % "4.0.6",

  "com.github.ben-manes.caffeine" % "caffeine" % "3.2.4",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test,

  "org.mockito" % "mockito-core" % "5.23.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "5.23.0" % Test
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)