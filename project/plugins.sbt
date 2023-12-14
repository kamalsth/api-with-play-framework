// The Play plugin
addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.0")

// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.16.2")
//addSbtPlugin("com.github.gseitz" % "sbt-protobuf" % "0.6.2")

addSbtPlugin("com.github.sbt" % "sbt-protobuf" % "0.7.1")
addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.6")

