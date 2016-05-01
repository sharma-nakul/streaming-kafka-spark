package spark;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

/**
 * Created by Naks on 30-Apr-16.
 * Finding Word Count
 */

public class WordMapper implements PairFunction<String,String,Integer>{
    public Tuple2<String, Integer> call(String x) {
                   return new Tuple2(x, 1);
               }
}
