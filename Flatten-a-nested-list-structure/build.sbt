scalaVersion := "3.8.4"

lazy val root = rootProject
  .settings(
    name := "Flatten-a-nested-list-structure",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % Test,
    Test / parallelExecution := false,
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )
