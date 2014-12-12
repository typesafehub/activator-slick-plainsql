name := "slick-plainsql"

mainClass in Compile := Some("PlainSQL")

libraryDependencies ++= List(
  TypesafeLibrary.slick.value,
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.175"
)

fork in run := true
