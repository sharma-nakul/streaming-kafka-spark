package spark;

import org.apache.spark.api.java.function.Function2;

/**
 * Created by Naks on 30-Apr-16.
 * Reduce words with its counts
 */

public class WordCountReducer implements Function2<Integer, Integer, Integer> {
    public Integer call(Integer x, Integer y) {
        return x + y;
    }
}
