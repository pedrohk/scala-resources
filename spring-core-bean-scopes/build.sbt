name := "spring-core-bean-scopes"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala3-library" % "3.8.3",

  "org.springframework" % "spring-context" % "6.2.8",
  "org.springframework" % "spring-beans" % "6.2.8",
  "org.springframework" % "spring-core" % "6.2.8",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test
)

Test / fork := true

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)