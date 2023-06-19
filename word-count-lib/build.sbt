lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "techtest",
      scalaVersion := "2.12.14"
    )),
    name := "WordCounter"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test
