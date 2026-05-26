name := "spring-data-jdbc"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter-web" % "4.0.6",
  "org.springframework.boot" % "spring-boot-starter-data-jdbc" % "4.0.6",

  "com.h2database" % "h2" % "2.4.240",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test,

  "org.mockito" % "mockito-core" % "5.23.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "5.23.0" % Test
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)