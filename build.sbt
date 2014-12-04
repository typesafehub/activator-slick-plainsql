name := "slick-plainsql"

version := "1.0"

scalaVersion := "2.11.4"

mainClass in Compile := Some("PlainSQL")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.174"
)
