package movie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naks on 02-May-16.
 * Movies list and their corresponding list of genre
 */
public class Movie implements Serializable {
    private String movieId;
    private String movieName;
    private List<String> genreList;

    public Movie() {
        genreList = new ArrayList<>();
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }
}