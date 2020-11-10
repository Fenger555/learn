package com.allinabc.demo

import java.io.ByteArrayInputStream

import cn.hutool.core.io.FileUtil
import com.allinabc.ods.klarf.buf.WaferDetailOuterClass
import com.allinabc.ods.klarf.buf.WaferDetailOuterClass.WaferDetail
import com.amazonaws.services.s3.AmazonS3
import org.apache.spark.sql.{DataFrame, SparkSession}
import util.S3Utils

import scala.collection.JavaConverters._

object ReadData2Protobuf {

  def getValueForRow(row: org.apache.spark.sql.Row, fieldName: String): String = {
    row.getString(row.fieldIndex(fieldName))
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("demo").master("local[*]").getOrCreate()

    val df: DataFrame = spark.read.option("header", true).csv("/Users/gaoxing/Downloads/dms_wafer_defect_info.csv")

    df.show()

    df.rdd
      .groupBy(row =>
        getValueForRow(row, "ref_wafer_id")
      )
      .foreachPartition(iter => {
        val s3Client: AmazonS3 = S3Utils.getS3Client("70902QAAAVYA0XFX3AWL", "mMFQjvY6ohXYAeSkWNqg6DGTu6Ulncp0owlRmROe", "http://192.168.2.178:30020")
        while (iter.hasNext) {
          val wafer = iter.next()

          val defects: Iterable[WaferDetail.Defect] =
            wafer._2.map(row =>
            WaferDetailOuterClass.WaferDetail.Defect.newBuilder()
              .setDefectId(getValueForRow(row, "defect_id").toInt)
              .setCoord(WaferDetailOuterClass.WaferDetail.Coord.newBuilder().setX(0).setY(0).build())
              .setDieIndex(WaferDetailOuterClass.WaferDetail.Coord.newBuilder().setX(0).setY(0).build())
              .setManBin(getValueForRow(row, "man_bin").toInt)
              .setRoughBin(getValueForRow(row, "rough_bin").toInt)
              .setAdcBin(getValueForRow(row, "adc_bin").toInt)
              .setClusterId(getValueForRow(row, "cluster_id").toInt)
              .build()
          )
          val dies: Iterable[WaferDetail.Die] =
            wafer._2
              .map(row =>
                (getValueForRow(row, "defective_die_index"), getValueForRow(row, "die_size"))
              )
              .toMap.keys
//              .foldLeft(List.empty)((rs, row) => {
//                if (rs.contains(row))
//                  return rs
//                else
//                  return rs.+:(row)
//              })
              .map(c =>
                WaferDetailOuterClass.WaferDetail.Die.newBuilder()
                  .setDieIndex(WaferDetailOuterClass.WaferDetail.Coord.newBuilder().setX(0).setY(0).build())
                  .setDieCoord(WaferDetailOuterClass.WaferDetail.Coord.newBuilder().setX(0).setY(0).build())
                  .build()
              )

          val waferDetail: WaferDetailOuterClass.WaferDetail =
            WaferDetailOuterClass.WaferDetail
              .newBuilder()
              .setRefWaferId(wafer._1)
              .setLotId("")
              .setWaferNo("")
              .setProductId("")
              .setStepId("")
              .setScanTm("")
              .setDefectCount(100)
              .addAllDefects(defects.asJava)
              .addAllDies(dies.asJava)
              .build()

          println(defects.size + "\t" + dies.size + "\t" + waferDetail.toByteArray.length)

          FileUtil.writeBytes(waferDetail.toByteArray, "/Users/gaoxing/Downloads/"+wafer._1);
          S3Utils.uploadStream(s3Client, "test", "wafer/" + wafer._1, new ByteArrayInputStream(waferDetail.toByteArray))
        }
      })


  }

}
