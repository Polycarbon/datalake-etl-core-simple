name := "datalake-etl-core-simple"

version := "0.1"

scalaVersion := "2.11.12"

idePackagePrefix := Some("com.tn.etl")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
//libraryDependencies += "be.olsson" % "slack-appender" % "1.3.0"
//libraryDependencies += "org.apache.hbase" % "hbase-client" % "2.2.2"
//libraryDependencies += "org.apache.hbase" % "hbase-common" % "2.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

mainClass in (Compile, packageBin) := Some("com.tn.etl.Main")
mainClass in (Compile, run) := Some("com.tn.etl.Main")
mainClass := Some("com.tn.etl.Main")