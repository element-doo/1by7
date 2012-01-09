import sbt._
import Keys._

object BuildSettings {
  import Repositories._

  val bsCountries =
    Default.settings ++ Seq(
      name         := "Countries",
      organization := "hr.element.onebyseven.common",
      version      := "2011-11-08",
      
      publishTo <<= (version) { version => Some(
        if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
      )},
      
      javacOptions     := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"),
      compileOrder     := CompileOrder.JavaThenScala,
      autoScalaLibrary := false,
      crossPaths       := false       
    )
    
  val bsRipper =
    Default.settings ++ Seq(
      name         := "Countries-Ripper",
      organization := "hr.element.onebyseven",
      version      := "0.0.1"
    )
}

object ProjectDeps {
  import Dependencies._
  import Implicits._

  val depsCountries = libDeps(
  )
  
  val depsRipper = libDeps(
    dispatchTagsoup,
    etbUtil,
    scalaIo,
    templater,
    //test
    scalaTest
  )
}

//  ---------------------------------------------------------------------------

object Dependencies {
  import Implicits._

  val dispatchTagsoup = "net.databinder" %% "dispatch-tagsoup" % "0.8.7"

  val etbUtil = "hr.element.etb" %% "etb-util" % "0.2.3"
  val scalaIo = "com.github.scala-incubator.io" %% "scala-io-file" % "0.3.0"

  val templater = "hr.ngs.templater" %% "templater" % "1.3.2"
   
  //test
  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"
}

//  ---------------------------------------------------------------------------

object CountriesBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val countries = Project(
    "Countries",
    file("Countries"),
    settings = bsCountries :+ depsCountries
  )

  lazy val ripper = Project(
    "Ripper",
    file("Ripper"),
    settings = bsRipper :+ depsRipper
  )
}
