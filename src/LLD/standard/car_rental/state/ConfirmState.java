package LLD.standard.car_rental.state;

import LLD.standard.car_rental.entities.Booking;
import LLD.standard.car_rental.enums.BookingStatus;

public class ConfirmState implements BookingState{

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking is already in confirm state");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking is moved to cancel state for booking ID: " + booking.getBookingId());
        booking.setState(new CancelState());
    }

    @Override
    public String getState() {
        return BookingStatus.CONFIRMED.name();
    }
}
