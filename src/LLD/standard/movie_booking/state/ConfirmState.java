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
        System.out.println("Cancelling confirmed booking.");
        booking.setBookingState(new CancelState());
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.CONFIRMED;
    }
}
