package LLD.standard.movie_booking.state;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.enums.BookingStatus;

public class ConfirmState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking is already confirmed.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Cancelling the booking.");
        booking.cancel();
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.CONFIRMED;
    }
}
