package LLD.standard.movie_booking.entities;

import LLD.standard.movie_booking.state.BookingState;
import LLD.standard.movie_booking.state.HeldState;

public class Booking {

    private String bookingId;
    private User user;
    private Show show;
    private Seat seat;
    public BookingState bookingState;

    public Booking(String bookingId, User user, Show show, Seat seat) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seat = seat;
        bookingState = new HeldState();
    }

    public User getUser() {
        return user;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void cancel() {
        this.bookingState.cancel(this);
    }

    public void confirm() {
        this.bookingState.confirm(this);
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }
}
