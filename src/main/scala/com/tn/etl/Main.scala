package com.tn.etl
import org.apache.log4j.LogManager
import org.apache.spark.sql.SparkSession

import java.util.concurrent.TimeUnit

object Main {

  private val logger = LogManager.getLogger("com.tn.etl.jobs")
  def main(args: Array[String]): Unit = {
      val start = System.nanoTime()
      var spark:SparkSession =null
      try {
        spark = SparkUtilities.startSession("spark_simple")
        logger.info("Start Job")
        //TODO
        logger.info("Job Complete")
      } catch {
        case e:Exception =>
          throw e
        case e:RuntimeException =>
          logger.error(e)
          sys.exit(1)
      } finally {
        SparkUtilities.closeSession()
        val end = System.nanoTime()
        val difference = end - start
        logger.info("Total execution time: " +
          TimeUnit.NANOSECONDS.toHours(difference) + " hours " +
          ( TimeUnit.NANOSECONDS.toMinutes(difference) -  TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(difference)))   + " min " +
          ( TimeUnit.NANOSECONDS.toSeconds(difference) -  TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(difference))) + " sec " +
          " - " + difference + " nSec (Total)")
      }
  }

}
