package com.nossin.spark

import org.apache.spark.sql.SparkSession
import org.scalatest.{FunSuite, Matchers}
import StringToSpark._
import org.apache.spark.sql.DataFrame

class TestStringToSpark extends FunSuite with Matchers with Fixtures {

  implicit val spark: SparkSession = createSparkSession

  test("Should create spark dataframe from string") {
    val jsonDf = df"""{ "michel" : "nossin", "metadata": { "key": 84896, "value": 54 }}"""

    val singleCsvDf = df"jan,michel,kees jan"

    val multiCsvDf = df"""
                     |id,date,timedump
                     |1, "2014/01/01 23:00:01",1499959917383
                     |2, "2014/11/31 12:40:32",1198138008843
                     """

    assert(jsonDf.columns
                 .exists(columnName => columnName == "michel"))
    assert(singleCsvDf.columns
                      .length == 3)
    assert(multiCsvDf.count == 2)
    assert(multiCsvDf.columns
                     .exists(columnName => columnName == "timedump"))
  }
}
