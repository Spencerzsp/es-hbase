package com.daqsoft

import com.mongodb.spark.MongoSpark
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object ReadMongdbData {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("ReadMongdbData")
      .master("local[2]")
      .config("spark.mongodb.input.uri", "mongodb://daqsoft-bdsp-06/db_wechat.col_article_info")
      .config("spark.testing.memory", "512000000")
      .getOrCreate()

    Logger.getRootLogger.setLevel(Level.WARN)

    val frame = MongoSpark.load(spark)
    frame.createTempView("col_article_info")

    val result = spark.sql("select * from col_article_info")
    result.show(truncate = false)
  }

}
