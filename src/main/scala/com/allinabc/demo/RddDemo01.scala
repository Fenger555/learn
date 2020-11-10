package com.allinabc.demo

import org.apache.spark.sql.SparkSession

import scala.reflect.ClassTag

object RddDemo01 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("demo").master("local[*]").getOrCreate()
    val sc = spark.sparkContext

    val rdd = sc.makeRDD(Seq(("a", 1), ("b", 2), ("c", 3), ("c", 44)), 3)
    rdd.collect()

    val res = rdd.combineByKey(
      (_, 1),
      (c: (Int, Int), v) => (c._1 + v, c._2 + 1),
      (c1: (Int, Int), c2: (Int, Int)) => (c1._1 + c2._1, c1._2 + c2._2)
    ).mapValues(v => v._1 / v._2).collect()


    val rs = sc.makeRDD(List(("a", 95), ("b", 88), ("a", 84), ("b", 66), ("c", 55), ("b", 45)))
      .combineByKey(v => (v, 1),
        (c:(Int, Int), v) => (c._1+v, c._2+1),
        (c1:(Int, Int), c2:(Int, Int)) => (c1._1+c2._1, c2._2+c2._2))
      .map(v => (v._1, v._2._1/v._2._2))
      .collect()

    rs.foreach(v => println(v._1 + ":" + v._2))

    val rdd1 = sc.parallelize(List("a", "c", "q", "t", "v", "h", "s"), 3)

    val rs1 = rdd1.sortBy(v => v).collect()
    show(rs1)

  }

  def show[T: ClassTag](rs: Seq[T]): Unit = {
    rs.foreach(v => println(v))
  }

}
