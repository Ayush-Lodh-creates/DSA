package LLD.standard.movie_booking.state;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.enums.BookingStatus;

public class CancelState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        throw new IllegalArgumentException("Cannot confirm a cancelled booking.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking is already cancelled.");
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.CANCELLED;
    }
}
