name := "scalamu-not-response"
version := "0.0.1"
scalaVersion := "2.12.4"

scalacOptions += "-unchecked"
scalacOptions += "-deprecation"
scalacOptions += "-feature"

lazy val akkaVersion = "2.4.20" //version akka for akka http

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
)
