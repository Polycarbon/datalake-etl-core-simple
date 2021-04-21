package com.tn.etl

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkUtilities {
  val conf = new SparkConf()
  val deployMode: String = conf.get("spark.master", "local")

  def getSession: SparkSession = {
    SparkSession.builder().getOrCreate()
  }

  def startSession(appName: String): SparkSession = {
    val sparkBuilder = SparkSession.builder()
      .appName(appName)
    var spark: SparkSession = null
    if (deployMode == "local") {
      spark = sparkBuilder
        .config("spark.master", "local")
        .getOrCreate()
      spark.sparkContext.setLogLevel("INFO")
    } else {
      spark = SparkSession.builder()
        .appName(appName)
        .getOrCreate()
    }
    //    val conf = HBaseConfiguration.create()
    //    new HBaseContext(spark.sparkContext, conf)
    spark
  }

  def closeSession(): Unit = {
    val spark = this.getSession
    spark.close()
  }

}
