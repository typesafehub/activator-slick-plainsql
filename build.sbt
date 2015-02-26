name := "slick-plainsql"

mainClass in Compile := Some("PlainSQL")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.0.0-RC1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.175"
)
