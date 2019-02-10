package com.nossin.spark

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.SparkSession

object StringToSpark {
    implicit class DataFrameInterpolator(val sc: StringContext)(implicit spark: SparkSession)  {
        def df(parameters: Any*) : org.apache.spark.sql.DataFrame = {

            val stringInput = sc.parts(0).toString

            import spark.implicits._
 
            val df : org.apache.spark.sql.DataFrame = 
                try {
                    val dataset = spark.read.json(Seq(stringInput).toDS)
                    val corruptExists = dataset.columns
                                               .toList
                                               .exists(columnName => columnName == "_corrupt_record")

                    if (corruptExists) {
                        throw new IllegalArgumentException("Json not correct")

                    }
                    return dataset
                }
                catch{
                    case e : Exception => {
                        val unparsedDataset = stringInput.stripMargin
                                                         .lines
                                                         .toList
                                                         .toDS()
                        val parsedDataframe= spark.read
                                            .option("inferSchema",true)
                        val dataset = unparsedDataset.count match {
                            case 1 => parsedDataframe.option("header", false)
                            case _ => parsedDataframe.option("header", true)
                        }
                        return dataset.csv(unparsedDataset)

                    }
                    throw new IllegalArgumentException("Input String is not a valid Json string and can't be parsed as CSV")

                }
            df
        }
    }
}
