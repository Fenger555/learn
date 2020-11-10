package com.allinabc.demo

import org.apache.spark.TaskContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object ExecutorReturnDriver {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("demo").master("local[*]").getOrCreate()
    val sc = spark.sparkContext

    val rdd: RDD[(String, Int)] = sc.makeRDD(Seq(("a", 1), ("b", 2), ("c", 3), ("c", 44)), 3)

    sc.runJob(rdd, (taskContext, iter:Iterator[(String, Int)]) => {

      iter.toMap

      (taskContext.partitionId(), 0)
    })


  }

}
