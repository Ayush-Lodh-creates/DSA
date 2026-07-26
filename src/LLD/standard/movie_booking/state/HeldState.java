package LLD.standard.movie_booking.state;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.enums.BookingStatus;

public class HeldState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking confirmed from held state.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking canceled from held state.");
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.PENDING;
    }
}
