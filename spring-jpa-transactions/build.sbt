name := "spring-jpa-transactions"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala3-library" % "3.8.3",

  "org.springframework.boot" % "spring-boot-starter" % "3.5.0",
  "org.springframework.boot" % "spring-boot-starter-web" % "3.5.0",
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % "3.5.0",

  "com.h2database" % "h2" % "2.3.232",

  "org.scalatest" %% "scalatest" % "3.2.20" % Test,

  "org.mockito" % "mockito-core" % "5.23.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "5.23.0" % Test
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)