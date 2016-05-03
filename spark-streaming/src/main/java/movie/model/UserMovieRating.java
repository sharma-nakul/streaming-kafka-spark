package movie.model;

import java.io.Serializable;

/**
 * Created by Naks on 02-May-16.
 * Movies that user rated.
 */
public class UserMovieRating implements Serializable{

    private int userId;
    private int movieId;
    //private String movieName;
    private double ratingGivenByUser;

    public UserMovieRating() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRatingGivenByUser() {
        return ratingGivenByUser;
    }

    public void setRatingGivenByUser(double ratingGivenByUser) {
        this.ratingGivenByUser = ratingGivenByUser;
    }

  /*  public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }*/
}
