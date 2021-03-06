val Scala212 = "2.12.10"
val Scala213 = "2.13.1"

inThisBuild(
  List(
    organization := "com.kubukoz",
    homepage := Some(url("https://github.com/kubukoz/cats-effect-utils")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "kubukoz",
        "Jakub Kozłowski",
        "kubukoz@gmail.com",
        url("https://kubukoz.com")
      )
    )
  )
)

def crossPlugin(x: sbt.librarymanagement.ModuleID) = compilerPlugin(x.cross(CrossVersion.full))

val compilerPlugins = List(
  crossPlugin("org.typelevel" % "kind-projector" % "0.11.3"),
  crossPlugin("com.github.cb372" % "scala-typed-holes" % "0.1.6"),
  compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
)

val commonSettings = Seq(
  scalaVersion := Scala212,
  crossScalaVersions := List(Scala212, Scala213),
  scalacOptions --= Seq("-Xfatal-warnings"),
  name := "catharsis",
  updateOptions := updateOptions.value.withGigahorse(false),
  testFrameworks += new TestFramework("munit.Framework"),
  libraryDependencies ++= List(
    "org.typelevel" %% "cats-effect" % "2.2.0",
    "org.scalameta" %% "munit" % "0.5.2" % Test
  ) ++ compilerPlugins
)

val catharsis =
  project.in(file(".")).settings(commonSettings)
