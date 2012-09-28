import sbt._
import Keys._

import Repositories._

object BuildSettings {
  val defaultSettings = Default.settings ++ Seq(
      organization := "hr.element.onebyseven"
    )

  val scalaSettings = defaultSettings ++ Seq(
      scalaVersion := "2.9.1"
    , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)
    )

  val javaSettings = scalaSettings ++ Seq(
      javacOptions     := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5")
    , compileOrder     := CompileOrder.JavaThenScala
    , crossPaths       := false
    , publishTo <<= (version) { version => Some(
        if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
      )}
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    )

  val bsCountries = javaSettings ++ Seq(
      name         := "Countries"
    , organization := "hr.element.onebyseven.common"
    , version      := "2011-11-08"
    )

  val bsMimeTypes = javaSettings ++ Seq(
      name         := "MimeTypes"
    , organization := "hr.element.onebyseven.common"
    , version      := "2012-02-12"
    )

  val bsRipper = scalaSettings ++ Seq(
      name         := "Ripper"
    , version      := "0.0.1"
    )

  val bsDumper = scalaSettings ++ Seq(
      name         := "Dumper"
    , version      := "0.0.1"
    )

  val bsCroZip = scalaSettings ++ Seq(
      name         := "CroZip"
    , organization := "hr.element.onebyseven.croatia"
    , version      := "2012-09-28"
    , publishTo <<= (version) { version => Some(
        if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
      )}
    )
}

object ProjectDeps {
  import Dependencies._
  import Implicits._

  val depsCountries = libDeps(
    itext
  , templater
  , liftJson
  , liftCommon
  , liftUtil)

  val depsMimeTypes = libDeps()

  val depsRipper = libDeps(
    doitCsv
  , dispatchTagsoup
  , dispatchLiftJson
  , cssSelectors
  , etbUtil
  , scalaIo
  , templater)

  val depsDumper = libDeps(
    doitCsv
  , templater
  , itext)

  val depsCroZip = libDeps(
    etbUtil
  , scalaIo)
}

//  ---------------------------------------------------------------------------

trait Publications {
  val countries = "hr.element.onebyseven.common" % "countries" % "2011-11-08"
}

object Dependencies extends Publications {
  import Implicits._

  val liftVersion = "2.5-M1"
  val liftJson   = "net.liftweb" %% "lift-json"   % liftVersion
  val liftCommon = "net.liftweb" %% "lift-common" % liftVersion
  val liftUtil   = "net.liftweb" %% "lift-util"   % liftVersion

  val dispatchVersion  = "0.8.8"
  val dispatchTagsoup  = "net.databinder" %% "dispatch-tagsoup" % dispatchVersion
  val dispatchLiftJson = "net.databinder" %% "dispatch-lift-json" % "0.8.5"

  val cssSelectors = "se.fishtank" %% "css-selectors-scala" % "0.1.0"

  val etbUtil = "hr.element.etb" %% "etb-util" % "0.2.16-P0"
  val scalaIo = "com.github.scala-incubator.io" % "scala-io-file_2.9.2" % "0.4.1"

  val templater = "hr.ngs.templater" %% "templater" % "1.7.0"

  val doitCsv   = "hr.element.doit" %% "doit-csv" % "0.1.6-T1"

  val itext     = "com.lowagie" % "itext" % "2.1.5"
}

//  ---------------------------------------------------------------------------

object RipperBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val countries = Project(
    "Countries"
  , file("Countries")
  , settings = bsCountries :+ depsCountries
  )

  lazy val mimeTypes = Project(
    "MimeTypes"
  , file("MimeTypes")
  , settings = bsMimeTypes :+ depsMimeTypes
  )

  lazy val ripper = Project(
    "Ripper"
  , file("Ripper")
  , settings = bsRipper :+ depsRipper
  )

  lazy val dumper = Project(
    "Dumper"
  , file("Dumper")
  , settings = bsDumper :+ depsDumper
  )

  lazy val croZip = Project(
    "CroZip"
  , file("CroZip")
  , settings = bsCroZip :+ depsCroZip
  )
}
