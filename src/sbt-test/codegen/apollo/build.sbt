name := "test"
enablePlugins(GraphQLCodegenPlugin)
scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria" % "1.3.0"
)

graphqlCodegenStyle := Apollo

TaskKey[Unit]("check") := {
  val generatedFiles = (graphqlCodegen in Compile).value
  val queryFile = generatedFiles.find(_.getName == "HeroNameQuery.scala")

  assert(queryFile.isDefined, s"Could not find generated scala class. Available files\n  ${generatedFiles.mkString("\n  ")}")
}
