package spark;


import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

/**
 * Static class to read kafka-producer using Spark Streaming as a consumer
 */
public class StreamMessages implements Function<Tuple2<String, String>, String> {
    public String call(Tuple2<String, String> message) {

        /*String jsonl = "";
        jsonl = message._2;

        char startCharacter = jsonl.charAt(0);
        System.out.println("++++++++++++++++++++++++");
        System.out.println(message._2);
        System.out.println("++++++++++++++++++++++++");

        if(startCharacter == '{') {
            JsonParser parser = new JsonParser();
            JsonObject rootObj = parser.parse(jsonl).getAsJsonObject();
            System.out.println("********************************");
            String result = rootObj.get("county").toString();
            System.out.println(result);
        }*/
        return message._2();
    }
}
