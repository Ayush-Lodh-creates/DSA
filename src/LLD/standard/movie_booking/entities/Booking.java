package LLD.standard.movie_booking.entities;

import LLD.standard.movie_booking.state.BookingState;
import LLD.standard.movie_booking.state.CancelState;
import LLD.standard.movie_booking.state.ConfirmState;
import LLD.standard.movie_booking.state.HeldState;

public class Booking {

    private String bookingId;
    private User user;
    private Seat seat;
    public BookingState bookingState;

    public Booking(String bookingId, User user, Seat seat) {
        this.bookingId = bookingId;
        this.user = user;
        this.seat = seat;
        bookingState = new HeldState();
    }

    public User getUser() {
        return user;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void cancel() {
        this.bookingState = new CancelState();
    }

    public void confirm() {
        this.bookingState = new ConfirmState();
    }
}
