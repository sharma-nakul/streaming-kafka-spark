package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naks on 27-Apr-16.
 * Streaming data from Kafka
 */

public class KafkaStreaming {


    /**
     * Zookeeper URL or quorum
     */
    private String zookeeperURL;

    /**
     * Name of app displayed on console/ url when cluster runs
     */
    private String sparkAppName;

    /**
     * Name of consumer group, this can be any name.
     */
    private String consumerGroup;

    /**
     * master node value, default is local[*]
     */
    private String masterNode = "local[*]"; // default value

    public KafkaStreaming(String zookeeperURL, String sparkAppName, String consumerGroup) {
        this.zookeeperURL = zookeeperURL;
        this.sparkAppName = sparkAppName;
        this.consumerGroup = consumerGroup;
    }


    /**
     * Method to stream the data from kafka
     *
     * @param topicList     List of topics from which spark should stream the data
     * @param numberThreads Number of threads assigned to each topic. It should not be more than number of partitions
     *                      assigned to each topic, otherwise extra threads will be ideal process (i.e. it'll not be
     *                      utilized for parallalism
     */
    public void sparkStreaming(List<String> topicList, int numberThreads) {

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        //The appName parameter is a name for your application to show on the cluster UI. master is a Spark, Mesos or
        // YARN cluster URL, or a special “local[*]” string to run in local mode.
        SparkConf sparkConf = new SparkConf().setAppName(sparkAppName).setMaster(getMasterNode());

        //Show log only when error


        // Create the context with 2 seconds batch size
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, new Duration(3000));

        Map<String, Integer> topicMap = new HashMap<>();
        for (String topic : topicList) {
            topicMap.put(topic, numberThreads);
        }

        JavaPairReceiverInputDStream<String, String> messages =
                KafkaUtils.createStream(javaStreamingContext, zookeeperURL, consumerGroup, topicMap);

        JavaDStream<String> line = messages.map(new StreamMessages());

        JavaDStream<String> words=line.flatMap(new WordSplitter());

        JavaPairDStream<String,Integer> wordCount=words.mapToPair(new WordMapper()).reduceByKey(new WordCountReducer());

        wordCount.print();
        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }

    /**
     * Setter to define number of master nodes (processors)
     *
     * @param masterNode the default is "local[*]"
     */
    public void setMasterNode(String masterNode) {
        this.masterNode = masterNode;
    }

    /**
     * To get master node configuration
     *
     * @return
     */
    public String getMasterNode() {
        return masterNode;
    }
}
