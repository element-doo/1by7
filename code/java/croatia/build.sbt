organization := "org._1by7.croatia"

name         := "1by7-croatia-zupanije"

version      := "0.1.0"

// ### Build settings ###

unmanagedSourceDirectories in Compile <<= (javaSource in Compile)( _ :: Nil)

unmanagedSourceDirectories in Test := Nil

// ### Publishing ###

publishTo := Some("Element Releases" at "http://maven.element.hr/nexus/content/repositories/releases/")

credentials += Credentials(Path.userHome / ".publish" / "element.credentials")

publishArtifact in (Compile, packageDoc) := false
