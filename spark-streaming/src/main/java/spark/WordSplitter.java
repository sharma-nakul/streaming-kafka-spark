package spark;

import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.Arrays;

/**
 * Created by Naks on 30-Apr-16.
 * Finding Word from streaming messages
 */

public class WordSplitter implements FlatMapFunction<String, String> {

    public Iterable<String> call(String x) {
        return Arrays.asList(x.split(","));
    }
}
