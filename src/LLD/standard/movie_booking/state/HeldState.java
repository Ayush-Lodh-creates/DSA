package LLD.standard.movie_booking.state;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.enums.BookingStatus;

public class HeldState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking confirmed from held state.");
        booking.setBookingState(new ConfirmState());
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking cancelled from held state.");
        booking.setBookingState(new CancelState());
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.PENDING;
    }
}
