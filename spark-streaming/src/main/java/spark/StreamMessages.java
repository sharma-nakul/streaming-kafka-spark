package spark;


import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

/**
 * Static class to read kafka-producer using Spark Streaming as a consumer
 */
public class StreamMessages implements Function<Tuple2<String, String>, String> {
    public String call(Tuple2<String, String> message) {
        return message._2();
    }
}
