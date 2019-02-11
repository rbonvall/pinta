scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalatest"              %% "scalatest"     % "3.0.5"  % "test"
, "org.scalacheck"             %% "scalacheck"    % "1.14.0" % "test"
, "org.typelevel"              %% "squants"       % "1.4.0"
, "eu.timepit"                 %% "refined"       % "0.9.4"
, "com.beachape"               %% "enumeratum"    % "1.5.13"
, "com.github.mpilquist"       %% "simulacrum"    % "0.15.0"
, "com.github.julien-truffaut" %% "monocle-core"  % "1.5.0"
, "com.github.julien-truffaut" %% "monocle-macro" % "1.5.0"
, "org.scalaz"                 %% "scalaz-core"   % "7.2.27"
)

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.8")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

//wartremoverErrors ++= Warts.unsafe
Compile / console / scalacOptions :=
  (console / scalacOptions).value.filterNot(_ contains("wartremover"))
