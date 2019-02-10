package com.nossin.spark

import org.apache.spark.sql.SparkSession

trait Fixtures {
  protected def createSparkSession: SparkSession = {
    val session = SparkSession
      .builder()
      .appName("spark_test")
      .master("local[2]")
      .getOrCreate()
    session
  }
}
