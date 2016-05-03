package movie.recommendation;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import movie.model.Movie;
import movie.model.StaticValues;
import movie.model.UserMovieRating;
import movie.rdd.MapMovieDataStructure;
import movie.rdd.MapRatingDataStructure;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;
import static org.apache.spark.api.java.JavaSparkContext.fromSparkContext;


public class MovieRatingRecommendation {

    private static final String moviesFilePath="spark-streaming\\src\\main\\resources\\movies.csv";
    private static final String ratingsFilePath="spark-streaming\\src\\main\\resources\\ratings.csv";

    private static final int userId=1;
    private static final String keySpace="movies";
    private static final String movieListTable="movies_list";
    private static final String userRatingTable="user_rating";

    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setAppName("MovieRecommendation")
                .setMaster("local[4]")
                .set("spark.driver.allowMultipleContexts", "true")
                .set("spark.cassandra.connection.host", "127.0.0.1");

        SparkContext sc=new SparkContext(sparkConf);
        JavaSparkContext ctx = fromSparkContext(sc);
        SQLContext sqlContext = new org.apache.spark.sql.SQLContext(sc);

        //Map the movie CSV with JavaRDD -> Movies.class
        JavaRDD<Movie> moviesRDD = ctx.textFile(moviesFilePath).map(new MapMovieDataStructure());
        CassandraJavaUtil.javaFunctions(moviesRDD)
                .writerBuilder(StaticValues.getKeySpace(), StaticValues.getMovieListTable(), mapToRow(Movie.class))
                .saveToCassandra();

        //Fetch ratings of all movies
        JavaRDD<UserMovieRating> allMovieRating =ctx.textFile(ratingsFilePath)
                .map(new MapRatingDataStructure(sc));

        DataFrame schemaPeople = sqlContext.createDataFrame(allMovieRating, UserMovieRating.class);
        schemaPeople.registerTempTable("allMovieRating");

        /*DataFrame movieRatingMap = sqlContext.sql("SELECT movieId, ratingGivenByUser FROM allMovieRating");
        DataFrame averageMovieRating=movieRatingMap.groupBy("movieId").avg("ratingGivenByUser");
        System.out.println(averageMovieRating.take(10).toString());*/


        //JavaPairDStream<String, Integer> wordCount = words.mapToPair(new WordMapper()).reduceByKey(new WordCountReducer());


        //Map the user rating CSV with JavaRDD -> UserMovieRating.class
        Function<UserMovieRating,Boolean> filterUser = userMovieRating -> userMovieRating.getUserId()==userId;
        JavaRDD<UserMovieRating> userMovieRatingRDD = allMovieRating.filter(filterUser);

        CassandraJavaUtil.javaFunctions(userMovieRatingRDD)
                .writerBuilder(StaticValues.getKeySpace(),StaticValues.getUserRatingTable(),mapToRow(UserMovieRating.class))
                .saveToCassandra();






    }
}