import sbt._
import Keys._

object Repositories {
  val ElementNexus            = "Element Nexus"             at "http://repo.element.hr/nexus/content/groups/public/"
  val ElementReleases         = "Element Releases"          at "http://repo.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots        = "Element Snapshots"         at "http://repo.element.hr/nexus/content/repositories/snapshots/"
  val ElementPrivateReleases  = "Element Private Releases"  at "http://repo.element.hr/nexus/content/repositories/releases-private/"
  val ElementPrivateSnapshots = "Element Private Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots-private/"
}

object Resolvers {
  import Repositories._

  val settings = Seq(
    resolvers := Seq(ElementNexus) // , ElementPrivateReleases, ElementPrivateSnapshots)
  , externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)
  )
}

object Publishing {
  import Repositories._

  val settings = Seq(
    publishTo := Some(
      if (version.value endsWith "SNAPSHOT") ElementPrivateSnapshots else ElementPrivateReleases
    )
  , credentials += Credentials(Path.userHome / ".config" / "1by7" / "nexus.config")
  , publishArtifact in (Compile, packageDoc) := false
  )
}

object Default {
  //Dependency graph plugin
  import net.virtualvoid.sbt.graph.Plugin._
  
  //Eclipse plugin
  import com.typesafe.sbteclipse.plugin.EclipsePlugin.{settings => eclipseSettings, _}
  
  val settings =
    Defaults.defaultSettings ++
    Resolvers.settings ++
    Publishing.settings ++
    graphSettings ++ 
    eclipseSettings ++ Seq(
      crossScalaVersions := Seq("2.10.3", "2.9.3", "2.9.2", "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0")
    , scalaVersion := crossScalaVersions.value.head
    , scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise")
    , unmanagedSourceDirectories in Test := Nil
    , EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)
    )
}

object Implicits {
  implicit def depToFunSeq(m: ModuleID) = Seq((_: String) => m)
  implicit def depFunToSeq(fm: String => ModuleID) = Seq(fm)
  implicit def depSeqToFun(mA: Seq[ModuleID]) = mA.map(m => ((_: String) => m))

  def libDeps(deps: (Seq[String => ModuleID])*) = {
    libraryDependencies <++= scalaVersion( sV =>
      for (depSeq <- deps; dep <- depSeq) yield dep(sV)
    )
  }
}
