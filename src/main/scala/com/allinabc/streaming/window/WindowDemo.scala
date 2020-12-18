package com.allinabc.streaming.window

import com.allinabc.streaming.checkpoint.CheckpointStreaming.start
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Duration, StreamingContext}

object WindowDemo {

  def main(args: Array[String]): Unit = {
    val ssc = StreamingContext.getOrCreate("./checkpoint", () => start())

    ssc.start()
    ssc.awaitTermination()
  }

  def start(): StreamingContext = {
    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(sparkConf, Duration(3000))

    val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 22222)

    dstream.reduceByWindow((a, b) => a+b, Duration.apply(30000), Duration.apply(15000))

    dstream.print()

    ssc
  }

}
