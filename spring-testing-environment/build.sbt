
name := "spring-testing-environment"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala3-library" % "3.8.4",

  "org.springframework.boot" % "spring-boot-starter" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter-web" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter-webflux" % "4.0.6",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)