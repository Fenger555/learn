package com.allinabc.udf

import org.apache.spark.sql.SparkSession

/**
 * spark不支持自定义UDTF
 *
 */
object MyUDTF {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("demo").master("local[*]").getOrCreate()
    val context = spark.sqlContext

    context.sql("select v from lateral view explode(split('a,b,c', ',')) as v").show()
  }

}
