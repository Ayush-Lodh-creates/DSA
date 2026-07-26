package LLD.standard.movie_booking.state;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.enums.BookingStatus;

public interface BookingState {

    void confirm(Booking booking);
    void cancel(Booking booking);
    BookingStatus getStatus();
}
