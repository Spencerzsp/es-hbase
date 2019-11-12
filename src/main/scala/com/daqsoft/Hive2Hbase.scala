package com.daqsoft

import java.net.URI
import java.security.MessageDigest

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{HFileOutputFormat2, LoadIncrementalHFiles}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapreduce.Job
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import scala.util.Random

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

    import org.apache.spark.sql.functions._
    val dataFromHive = spark.sql("select * from default.corpus limit 10").withColumn("id", monotonically_increasing_id() + 1)
    dataFromHive.show(truncate = false)
//    dataFromHive.createOrReplaceTempView("test")
//    val data = spark.sql("select * ,id as rowkey from test")

    var fields = dataFromHive.columns
    fields = fields.dropWhile(_ == "id")

//    dataFromHive.rdd.map(row => {
//      row.getAs("id")
//    })
//    dataFromHive.rdd.filter()foreach(println)

//    val dataRDD = dataFromHive.rdd.map(row => {
//      val rowkey = row.getAs[String]("id")
//      fields.map(field => {
//        val fieldValue = row.getAs[String](field)
//        (Bytes.toBytes(rowkey), Array(Bytes.toBytes("cf"), Bytes.toBytes(fieldValue)))
//      })
//    }).flatMap(array => {
//      (array)
//    })

//    dataRDD.foreach(println)
//    println(fields)
//    data.show(truncate = false)
//
//    val dataRDD = data.rdd.flatMap(row => {
//      val rowkey = row.getAs[String]("rowkey")
//      println(rowkey)
//      Array(
//        (rowkey, ("cf", "id", nullHandle(row.getAs[String]("id")))),
//        (rowkey, ("cf", "label", nullHandle(row.getAs[String]("label")))),
//        (rowkey, ("cf", "content", nullHandle(row.getAs[String]("content"))))
//      )
//    })

//    dataRDD.foreach(println)

    //
    //    val rdds = dataRDD.filter(_._1 != null).sortBy(x => (x._1, x._2._1, x._2._2, x._2._3)).map(
    //      x => {
    //        val rowkey = Bytes.toBytes(x._1)
    //        val family = Bytes.toBytes(x._2._1)
    //        val column = Bytes.toBytes(x._2._2)
    //        val value = Bytes.toBytes(x._2._3)
    //
    //        (new ImmutableBytesWritable(rowkey), new KeyValue(family, column, value))
    //      }
    //    )
    //
    //    // 临时保存位置
    //    val tmpdir = "/user/ceshi/test"
    //
    //    val conf = new Configuration()
    //    conf.set("fs.defaultFS", "hdfs://daqsoft-bdsp-04:8020")
    //    val fs = FileSystem.get(new URI("hdfs://daqsoft-bdsp-04:8020"), conf, "hdfs")
    //    if (fs.exists(new Path(tmpdir))){
    //      println("删除临时文件夹")
    //      fs.delete(new Path(tmpdir), true)
    //    }
    //
    //    val hconf = HBaseConfiguration.create()
    //
    //    //为了预防hfile文件数过多无法进行导入，设置该参数值
    //    hconf.setInt("hbase.mapreduce.bulkload.max.hfiles.perRegion.perFamily", 5000)
    //
    //    rdds.saveAsNewAPIHadoopFile(tmpdir,
    //      classOf[ImmutableBytesWritable],
    //      classOf[KeyValue],
    //      classOf[HFileOutputFormat2],
    //      hconf
    //    )
    //    val load = new LoadIncrementalHFiles(hconf)
    //
    //    val tableName = "corpus"
    //    val conn = ConnectionFactory.createConnection(hconf)
    //    val table = conn.getTable(TableName.valueOf(tableName))
    //    val regionLocator = conn.getRegionLocator(TableName.valueOf(tableName))
    //    val job = Job.getInstance(conf)
    //    job.setJobName("test")
    //    job.setMapOutputKeyClass(classOf[ImmutableBytesWritable])
    //    job.setMapOutputValueClass(classOf[KeyValue])
    //    HFileOutputFormat2.configureIncrementalLoad(job, table, regionLocator)
    //
    //    // 开始导入
    //    load.doBulkLoad(new Path(tmpdir), conn.getAdmin, table, regionLocator)
    //  }
    //
    def nullHandle(str: String): String = {
      if (str == null || "".equals(str)) {
        return "NULL"
      } else
        return str
    }
  }
}
