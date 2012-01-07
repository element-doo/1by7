import sbt._
import Keys._

object BuildSettings {
  val bsCountryRipper = 
    Default.settings ++ Seq(
      name         := "CountryRipper",
      organization := "hr.element.onebyseven",
      version      := "0.0.1"
    )
}

object ProjectDeps {
  import Dependencies._
  import Implicits._
  
  val depsCountryRipper = libDeps(
    //test
    scalaTest
  )
}

//  ---------------------------------------------------------------------------

object Dependencies {
  import Implicits._

  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"
}

//  ---------------------------------------------------------------------------

object CountryRipperBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val countryRipper = Project(
    "CountryRipper",
    file("."),
    settings = bsCountryRipper :+ depsCountryRipper
  )
}
