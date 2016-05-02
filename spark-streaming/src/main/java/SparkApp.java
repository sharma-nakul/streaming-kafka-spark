import spark.KafkaStreaming;

import java.util.ArrayList;


public class SparkApp {
    public static void main(String[] args) throws Exception {


        ArrayList<String> topicList = new ArrayList<>();
        topicList.add("weather_north_california");
        KafkaStreaming kafkaStream = new KafkaStreaming("localhost:2181", "KafkaSparkStreaming", "test-group", topicList,2);

        // WordCountBasicSpark basicSpark=new WordCountBasicSpark(inputFile,outputFolder);
        // basicSpark.findWordCount();
    }
}
