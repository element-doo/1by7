resolvers := Seq(
  "Instantor Nexus" at "http://www.instantor.com/nexus/content/groups/public/"
, Resolver.url("Instantor Nexus (Ivy)", new URL("http://www.instantor.com/nexus/content/groups/public/"))(Resolver.ivyStylePatterns)
)

externalResolvers <<= resolvers map { rS =>
  Resolver.withDefaultResolvers(rS, mavenCentral = false)
}

// =======================================================================================

// +-------------------------------------------------------------------------------------+
// | SBT Eclipse (https://github.com/typesafehub/sbteclipse)                             |
// | Creates .project and .classpath files for easy Eclipse project imports              |
// |                                                                                     |
// | See also: Eclipse downloads (http://www.eclipse.org/downloads/)                     |
// | See also: Scala IDE downloads (http://download.scala-ide.org/)                      |
// +-------------------------------------------------------------------------------------+

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0")
