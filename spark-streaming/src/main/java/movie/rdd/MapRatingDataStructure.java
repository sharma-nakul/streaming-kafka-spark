package movie.rdd;

import movie.model.UserMovieRating;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * Created by Naks on 02-May-16.
 * Map the CSV file data with JavaRDD structure
 */
public class MapRatingDataStructure implements Function<String,UserMovieRating> {

   // private JavaRDD<String> movieNameRDD;
    public MapRatingDataStructure(SparkContext sc)
    {
        /*this.movieNameRDD= CassandraJavaUtil.javaFunctions(sc)
                .cassandraTable(StaticValues.getKeySpace(),StaticValues.getMovieListTable(),mapColumnTo(String.class))
                .select("movie_name");*/
    }
    @Override
    public UserMovieRating call(String line) throws Exception{
        String[] r=line.split(",");
        UserMovieRating userMovieRating= new UserMovieRating();
        userMovieRating.setUserId(Integer.parseInt(r[0]));
        userMovieRating.setMovieId(Integer.parseInt(r[1]));
        userMovieRating.setRatingGivenByUser(Double.parseDouble(r[2]));
      //  userMovieRating.setMovieName(movieNameRDD.toString());// need to change
        return userMovieRating;
    }
}
