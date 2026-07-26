package LLD.standard.movie_booking.services;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.entities.Seat;
import LLD.standard.movie_booking.entities.User;
import LLD.standard.movie_booking.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookingService {

    List<Booking> bookings = new CopyOnWriteArrayList<>();

    public Booking bookSeat(User userId, Seat seat) {
        seat.lock.lock();
        try {
            if(seat.getBooked()) {
                throw new IllegalArgumentException("Seat is already booked");
            }
            seat.setBooked(true);
            Booking booking = new Booking(
                    UUID.randomUUID().toString(),
                    userId, seat
            );
            bookings.add(booking);
            return booking;
        } finally {
            seat.lock.unlock();
        }
    }

    public List<Booking> getAllBookingsForUser(String userId) {
        List<Booking> userBookings = new ArrayList<>();
        for(Booking booking : bookings) {
            if(booking.getUser().getId().equals(userId)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public Booking getPendingPaymentBooking() {
        for(Booking booking : bookings) {
            if(booking.bookingState.getStatus().equals(BookingStatus.PENDING)) {
                return booking;
            }
        }
        return null;
    }
}
