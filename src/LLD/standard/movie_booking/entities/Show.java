package LLD.standard.movie_booking.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Show {

    private String showId;
    private Movie movie;
    private Screen screen;
    private String startTime;
    private Set<String> bookedSeatIds;
    private ReentrantLock lock;

    public Show(String showId, Movie movie, Screen screen, String startTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.bookedSeatIds = new HashSet<>();
        this.lock = new ReentrantLock();
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public String getStartTime() {
        return startTime;
    }

    public boolean isSeatBooked(String seatId) {
        return bookedSeatIds.contains(seatId);
    }

    public void bookSeat(String seatId) {
        bookedSeatIds.add(seatId);
    }

    public void releaseSeat(String seatId) {
        bookedSeatIds.remove(seatId);
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
