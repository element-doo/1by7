import sbt._
import Keys._

import Repositories._

object BuildSettings {
  val defaultSettings = Default.settings ++ Seq(
      organization := "hr.element.onebyseven"
    )

  val scalaSettings = defaultSettings ++ Seq(
      scalaVersion := "2.10.4"
    , unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil
    )

  val javaSettings = scalaSettings ++ Seq(
      javaHome := sys.env.get("JDK16_HOME").map(file(_))
    , javacOptions := Seq(
        "-deprecation"
      , "-encoding", "UTF-8"
      , "-Xlint:unchecked"
      , "-source", "1.6"
      , "-target", "1.6"
      )
    , compileOrder     := CompileOrder.JavaThenScala
    , autoScalaLibrary := false
    , crossPaths       := false
    , publishTo := Some(
        if (version.value endsWith "SNAPSHOT") ElementSnapshots else ElementReleases
      )
    , unmanagedSourceDirectories in Compile := (javaSource in Compile).value :: Nil
    )

  val bsCountries = javaSettings ++ Seq(
      name         := "Countries"
    , organization := "hr.element.onebyseven.common"
    , version      := "2011-11-08"
    )

  val bsMimeTypes = javaSettings ++ Seq(
      name         := "MimeTypes"
    , organization := "hr.element.onebyseven.common"
    , version      := "2013-10-21"
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
    , version      := "2012-09-26"
    , publishTo := Some(
        if (version.value endsWith "SNAPSHOT") ElementSnapshots else ElementReleases
      )
    )
}

//  ---------------------------------------------------------------------------

trait Publications {
  val countries = "hr.element.onebyseven.common" % "countries" % "2011-11-08"
}

object Dependencies extends Publications {
  import Implicits._

  val liftVersion = "2.5.1"
  val liftCommon = "net.liftweb" %% "lift-common" % liftVersion
  val liftUtil   = "net.liftweb" %% "lift-util"   % liftVersion

  val dispatch = "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"

  val cssSelectors = "se.fishtank" %% "css-selectors-scala" % "0.1.2"

  val etbUtil = "hr.element.etb" %% "etb-util" % "0.2.20"
  lazy val scalaIo = Def.setting {
    scalaVersion.value match {
      case x if x startsWith "2.9.0" => "com.github.scala-incubator.io" % "scala-io-file_2.9.1" % "0.4.1-seq"
      case x if x startsWith "2.9.1" => "com.github.scala-incubator.io" % "scala-io-file_2.9.1" % "0.4.1-seq"
      case x if x startsWith "2.9.3" => "com.github.scala-incubator.io" % "scala-io-file_2.9.2" % "0.4.1-seq"
      case x if x startsWith "2.9."  => "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1-seq"
      case x                         => "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"
    }
  }

  val templater = "hr.ngs.templater" %% "templater" % "1.8.2-1"

  val doitCsv   = "hr.element.doit" %% "doit-csv" % "0.1.7"

  val itext     = "com.lowagie" % "itext" % "2.1.7"
}

//  ---------------------------------------------------------------------------

object RipperBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val countries = Project(
    "Countries"
  , file("Countries")
  , settings = bsCountries :+ (
      libraryDependencies ++= Seq(
          itext
        , templater
        , liftCommon
        , liftUtil
        )
      )
    )

  lazy val mimeTypes = Project(
    "MimeTypes"
  , file("MimeTypes")
  , settings = bsMimeTypes :+ (
      libraryDependencies ++= Seq(
      )
    )
  )

  lazy val ripper = Project(
    "Ripper"
  , file("Ripper")
  , settings = bsRipper :+ (
      libraryDependencies ++= Seq(
        doitCsv
      , dispatch
//      , cssSelectors
      , etbUtil
      , scalaIo.value
      , templater
      )
    )
  )

  lazy val dumper = Project(
    "Dumper"
  , file("Dumper")
  , settings = bsDumper :+ (
      libraryDependencies ++= Seq(
        doitCsv
      , templater
      , itext
      )
    )
  )

  lazy val croZip = Project(
    "CroZip"
  , file("CroZip")
  , settings = bsCroZip :+ (
      libraryDependencies ++= Seq(
        etbUtil
      , scalaIo.value
      )
    )
  )
}
