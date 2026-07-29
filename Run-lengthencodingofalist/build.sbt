scalaVersion := "3.8.4"

lazy val root = (project in file("."))
  .settings(
    name := "Run-lengthencodingofalist",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % Test,
    Test / parallelExecution := false,
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )
