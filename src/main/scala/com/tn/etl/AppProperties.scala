package com.tn.etl

import java.io.FileInputStream
import java.util.Properties
import scala.io.Source

object AppProperties {
//  private val fs = new FileInputStream("app.properties")
  private val prop = new Properties()
  private lazy val properties: Properties ={
    val deployMode = SparkUtilities.deployMode
    if (deployMode == "local"){
          val url = getClass.getResource("/app.properties")
          if (url != null) {
            val source = Source.fromURL(url)
            prop.load(source.bufferedReader())
          }
    }else{
          val fs = new FileInputStream("app.properties")
          prop.load(fs)
    }

    prop
  }

  def getProperty(property_name:String): String ={
    properties.getProperty(property_name)
  }

}
