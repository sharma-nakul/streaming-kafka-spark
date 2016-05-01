package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

/**
 * Created by Naks on 27-Apr-16.
 * Streaming data from Kafka
 */

public class KafkaStreaming {

    private static JavaSparkContext sc;


    private String zookeeperURL;

    private String consumerGroup;

    public KafkaStreaming(String zookeeperURL, String sparkAppName, String consumerGroup, List<String> topicList, int numberThreads) {
        this.zookeeperURL = zookeeperURL;
        this.consumerGroup = consumerGroup;
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkConf sparkConf = new SparkConf().setAppName(sparkAppName).setMaster("local[4]");
        sparkConf.set("spark.cassandra.connection.host", "127.0.0.1");
        JavaStreamingContext jsc = new JavaStreamingContext(sparkConf, new Duration(3000));
        sparkStreaming(topicList, numberThreads, jsc);
    }

    private void sparkStreaming(List<String> topicList, int numberThreads, JavaStreamingContext jsc) {
        Map<String, Integer> topicMap = new HashMap<>();
        for (String topic : topicList) {
            topicMap.put(topic, numberThreads);
        }

        JavaPairReceiverInputDStream<String, String> messages =
                KafkaUtils.createStream(jsc, zookeeperURL, consumerGroup, topicMap);

        JavaDStream<String> line = messages.map(new StreamMessages());

        JavaDStream<String> words = line.flatMap(new WordSplitter());

        JavaPairDStream<String, Integer> wordCount = words.mapToPair(new WordMapper()).reduceByKey(new WordCountReducer());

        sc = jsc.sparkContext();
        wordCount.foreachRDD((v1, v2) -> {
            v1.foreach((x) -> {
                List<WordCount> counts = Arrays.asList(
                        new WordCount(x._1(), x._2()));
                JavaRDD rdd = sc.parallelize(counts);

                javaFunctions(rdd)
                        .writerBuilder("java_api", "word_count", mapToRow(WordCount.class))
                        .saveToCassandra();
            });

            return null;
        });
        jsc.start();
        jsc.awaitTermination();
    }
}
