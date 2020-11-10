package com.allinabc.shuffle

import org.apache.spark.sql.SparkSession

import scala.reflect.ClassTag

object GroupShuffle {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("demo").master("local[*]").getOrCreate()
    val sc = spark.sparkContext

    val joinRdd = sc.makeRDD(Seq(("the", "t"), ("and", "a"), ("of", "o"), ("i", "i"), ("if", "f"), ("cccc", ""), ("come", "com")))

    val value = sc.textFile("file:///Users/gaoxing/Downloads/tmp")
      .flatMap(line => line.trim.split(" "))
      .repartition(4)
      .map(word => new Tuple2(word.toLowerCase, 1))

    val sample = value.sample(false, 0.2)
      .reduceByKey((a, b) => a + b)
      .filter(_._2 > 200)

    val tuples = sample.collect()

    var sub = value.subtractByKey(sample).reduceByKey((a, b) => a + b).sortBy(_._2, false).collect()
//      .collect()

    val joinResult = value.join(joinRdd).map(rs => rs._2.swap).collect()


    val rdd = sc.makeRDD(Seq(
      ("a", 1), ("b", 2), ("c", 3), ("d", 5), ("b", 2), ("c", 1), ("a", 1), ("f", 2), ("c", 3),
      ("r", 1), ("b", 8), ("c", 5), ("d", 5), ("b", 9), ("g", 1), ("a", 1), ("x", 1), ("g", 4)
    ), 3)

    val c = rdd.sample(false, 0.2).reduceByKey((a, b) => a+b)


    c.foreach(k => println(k._1+": "+k._2))


}


}
