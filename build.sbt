name := """playProject"""
organization := "api"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,ProtobufPlugin)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "io.grpc" % "grpc-netty-shaded" % "1.60.0"

libraryDependencies += "io.grpc" % "grpc-netty" % "1.60.0"
libraryDependencies += "io.grpc" % "grpc-protobuf" % "1.60.0"
libraryDependencies += "io.grpc" % "grpc-stub" % "1.60.0"
libraryDependencies += "javax.annotation" % "javax.annotation-api" % "1.3.2"
libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.25.1"
libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.13"

libraryDependencies += "io.grpc" % "protoc-gen-grpc-java" % "1.59.1" pomOnly()

libraryDependencies += "com.lightbend.akka.grpc" %% "akka-grpc-runtime" % "2.3.4"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.3"
libraryDependencies += "org.mapstruct" % "mapstruct" % "1.5.5.Final"
libraryDependencies += "org.mapstruct" % "mapstruct-processor" % "1.5.5.Final"


//libraryDependencies += "org.apache.pekko" %% "pekko-actor" % "1.0.2"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.0"

//libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.0"

Compile / PB.targets := Seq(
  PB.gens.java -> (Compile / sourceManaged).value
)

