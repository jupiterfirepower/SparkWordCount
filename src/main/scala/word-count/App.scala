package org.apache.streaming.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

//import org.apache.spark.SparkContext
//import org.apache.spark.SparkContext._
//import org.apache.spark._
import org.apache.spark.sql.SaveMode
import scala.reflect.io.File
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

object WordCountApp {
    def main(args:Array[String]): Unit = {

    println("Start Spark Word Count App.")

    val spark:SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("WordCountApp")
      .getOrCreate()

    val sc = spark.sparkContext

    val rdd:RDD[String] = sc.textFile("/tmp/input.txt")
    println("initial partition count: " + rdd.getNumPartitions)

    val reparRdd = rdd.repartition(4)
    println("re-partition count: " + reparRdd.getNumPartitions)

    // rdd flatMap transformation
    val words = rdd.flatMap(line => line.toLowerCase.split("\\s+"))
    //words.foreach(f => println(f))

    //Create a Tuple by adding 1 to each word
    val wordPairs= words.map(word => (word,1))
    //ReduceBy transformation
    val wordCounts = wordPairs.reduceByKey(_ + _)
    //val wordCounts = wordPairs.reduceByKey((x, y) => x + y)

    wordCounts.foreach(f => println(f))

    //Action - saveAsTextFile
    //val timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date())
    //wordCounts.saveAsTextFile(s"/tmp/wordcount-${timestamp}")

    val df = spark.createDataFrame(wordCounts)
    df.write.mode("overwrite").csv("/tmp/wordcount/")

    println("OK.")
    }
}