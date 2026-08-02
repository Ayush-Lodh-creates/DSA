package LLD.standard.car_rental.state;

import LLD.standard.car_rental.entities.Booking;
import LLD.standard.car_rental.enums.BookingStatus;

public class CancelState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking is already canceled and cannot be confirmed for booking ID: " + booking.getBookingId());
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking is already canceled for booking ID: " + booking.getBookingId());
    }

    @Override
    public String getState() {
        return BookingStatus.CANCELLED.name();
    }
}
