package LLD.concurrency.booking_system.controllers;

import LLD.concurrency.booking_system.service.MovieService;

public class MovieController {

    private final MovieService movieService;

    // Constructor to initialize the MovieService dependency
    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    public int createMovie(final String movieName, final int durationInMinutes) {
        return movieService.createMovie(movieName, durationInMinutes).getMovieId();
    }
}
