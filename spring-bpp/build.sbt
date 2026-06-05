name := "spring-bpp"
version := "0.1.0"
scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala3-library" % "3.8.4",

  "org.springframework" % "spring-context" % "7.0.7",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)