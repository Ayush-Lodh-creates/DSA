package LLD.concurrency.booking_system.controllers;

import LLD.concurrency.booking_system.entity.Movie;
import LLD.concurrency.booking_system.entity.Screen;
import LLD.concurrency.booking_system.entity.Seat;
import LLD.concurrency.booking_system.entity.Show;
import LLD.concurrency.booking_system.service.MovieService;
import LLD.concurrency.booking_system.service.SeatAvailabilityService;
import LLD.concurrency.booking_system.service.ShowService;
import LLD.concurrency.booking_system.service.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowController {

    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    // Constructor to inject all required services
    public ShowController(SeatAvailabilityService seatAvailabilityService, ShowService showService,
                          TheatreService theatreService, MovieService movieService) {
        this.seatAvailabilityService = seatAvailabilityService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.movieService = movieService;
    }

    public int createShow(final int movieId, final int screenId, final Date startTime,
                          final Integer durationInSeconds) throws Exception{
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();

    }

    public List<Integer> getAvailableSeats(final int showId) throws Exception{
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getSeatId).collect(Collectors.toList());
    }
}
