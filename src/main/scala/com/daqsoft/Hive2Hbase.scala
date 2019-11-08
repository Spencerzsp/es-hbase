package com.daqsoft

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Hive2Hbase {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Hive2Hbase")
      .master("local[2]")
      .enableHiveSupport()
      .config("spark.testing.memory", "512000000")
      .getOrCreate()

    Logger.getRootLogger.setLevel(Level.WARN)
    val sc = spark.sparkContext

    val databases = spark.sql("select * from default.corpus limit 10").show(truncate = false)
  }

}
