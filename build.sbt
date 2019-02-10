organization := "com.nossin"
scalaVersion := "2.12.4"
version      := "0.0.1"

name := "string-to-spark"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0" ,
  "org.apache.spark" %% "spark-sql" % "2.4.0" ,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"
