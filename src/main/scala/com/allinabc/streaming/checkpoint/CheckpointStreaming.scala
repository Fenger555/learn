package com.allinabc.streaming.checkpoint

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Duration, StreamingContext}

object CheckpointStreaming {
  def main(args: Array[String]): Unit = {
    val ssc = StreamingContext.getOrCreate("/Volumes/UFDRecovery/checkpoint", () => start())

    ssc.start()
    ssc.awaitTermination()
  }

  def start(): StreamingContext = {
    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(sparkConf, Duration(2))

    val topics = Seq("")
    val kafkaParams = Map[String, Object]()

//    val dStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
//      ssc,
//      LocationStrategies.PreferConsistent,
//      ConsumerStrategies.Subscribe(topics, kafkaParams)
//    )
//
//    dStream.foreachRDD(
//      rdd => {
//      }
//    )


    ssc
  }
}
