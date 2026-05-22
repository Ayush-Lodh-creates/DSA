package LLD.concurrency.booking_system.entity;

public class Movie {

    private final int movieId;
    private final String movieName;
    private final int movieDurationInMinutes;

    public Movie(int movieId, String movieName, int movieDurationInMinutes) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieDurationInMinutes() {
        return movieDurationInMinutes;
    }
}
