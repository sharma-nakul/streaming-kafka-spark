import spark.KafkaStreaming;

import java.util.ArrayList;


public class SparkApp {
    public static void main(String[] args) throws Exception {

        KafkaStreaming kafkaStream = new KafkaStreaming("localhost:2181", "KafkaSparkStreaming", "test-group");
        ArrayList topicList = new ArrayList();
        topicList.add("weather_north_california");
        kafkaStream.sparkStreaming(topicList, 2);
        // WordCountBasicSpark basicSpark=new WordCountBasicSpark(inputFile,outputFolder);
        // basicSpark.findWordCount();
    }
}
