package LLD.concurrency.booking_system.service;

import LLD.concurrency.booking_system.entity.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieService {
    private final Map<Integer, Movie> movies;
    public final AtomicInteger movieCounter;

    public MovieService() {
        this.movies = new HashMap<>();
        this.movieCounter = new AtomicInteger(0);
    }

    public Movie getMovie(int movieId) throws Exception {
        if(!movies.containsKey(movieId)) {
            throw new Exception("Movie with id " + movieId + " does not exist");
        }
        return movies.get(movieId);
    }

    public Movie createMovie(String movieName, int movieDurationInMinutes) {
        int movieId = movieCounter.incrementAndGet();
        Movie movie = new Movie(movieId, movieName, movieDurationInMinutes);
        movies.put(movieId, movie);
        return movie;
    }
}
