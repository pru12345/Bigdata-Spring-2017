/**
  * Created by pru on 2/15/2017.
  */



import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
object new_ {

  val PATH = "data/"
  val INPUT_DIR = PATH + "train"

 def main {

   System.setProperty("hadoop.home.dir","C:\\Users\\Manikanta\\Documents\\UMKC Subjects\\PB\\hadoopforspark");
   val conf = new SparkConf()
     .setAppName(s"IPApp")
     .setMaster("local[*]")
     .set("spark.executor.memory", "6g")
     .set("spark.driver.memory", "6g")
   val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

   val sc=new SparkContext(sparkConf)
   val images = sc.wholeTextFiles(s"${IPSettings.INPUT_DIR}/*/*.jpg")




  }

}
