package LLD.standard.car_rental.state;

import LLD.standard.car_rental.entities.Booking;

public interface BookingState {

    void confirm(Booking booking);
    void cancel(Booking booking);
    String getState();
}
