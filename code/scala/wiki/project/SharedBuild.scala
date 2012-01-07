import sbt._
import Keys._

object Repositories {
  val ElementNexus            = "Element Nexus"             at "http://maven.element.hr/nexus/content/groups/public/"
  val ElementReleases         = "Element Releases"          at "http://maven.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots        = "Element Snapshots"         at "http://maven.element.hr/nexus/content/repositories/snapshots/"
  val ElementPrivateReleases  = "Element Private Releases"  at "http://maven.element.hr/nexus/content/repositories/releases-private/"
  val ElementPrivateSnapshots = "Element Private Snapshots" at "http://maven.element.hr/nexus/content/repositories/snapshots-private/"
}

object Resolvers {
  import Repositories._

  val settings = Seq(
    resolvers := Seq(ElementNexus, ElementPrivateReleases, ElementPrivateSnapshots),
    externalResolvers <<= resolvers map { rs =>
      Resolver.withDefaultResolvers(rs, mavenCentral = false, scalaTools = false)
    }
  )
}

object Publishing {
  import Repositories._

  val settings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementPrivateSnapshots else ElementPrivateReleases
    )},
    credentials += Credentials(Path.userHome / ".publish" / "element.credentials"),
    publishArtifact in (Compile, packageDoc) := false
  )
}

object Default {
  val settings =
    Defaults.defaultSettings ++
    Resolvers.settings ++
    Publishing.settings ++ Seq(
      crossScalaVersions := Seq("2.9.1"),
      scalaVersion <<= (crossScalaVersions) { versions => versions.head },
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise", "-Yrepl-sync")
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
