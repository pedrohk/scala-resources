name := "scala-p04-length"

version := "0.1.0"

scalaVersion := "3.8.4"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test
)

Test / parallelExecution := false