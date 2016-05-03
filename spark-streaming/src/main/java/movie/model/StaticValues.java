package movie.model;

/**
 * Created by Naks on 02-May-16.
 * To serve static values
 */
public class StaticValues {
    private static final String keySpace="movies";
    private static final String movieListTable="movies_list";
    private static final String userRatingTable="user_rating";

    public static String getKeySpace() {
        return keySpace;
    }

    public static String getMovieListTable() {
        return movieListTable;
    }

    public static String getUserRatingTable() {
        return userRatingTable;
    }
}
