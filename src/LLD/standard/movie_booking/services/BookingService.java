package LLD.standard.movie_booking.services;

import LLD.standard.movie_booking.entities.Booking;
import LLD.standard.movie_booking.entities.Seat;
import LLD.standard.movie_booking.entities.Show;
import LLD.standard.movie_booking.entities.User;
import LLD.standard.movie_booking.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    List<Booking> bookings = new CopyOnWriteArrayList<>();

    //private ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public Booking bookSeat(User user, Show show, Seat seat) {
        show.getLock().lock();
        try {
            if(show.isSeatBooked(seat.getSeatId())) {
                throw new IllegalArgumentException("Seat is already booked for this show");
            }
            show.bookSeat(seat.getSeatId());
            Booking booking = new Booking(
                    UUID.randomUUID().toString(),
                    user, show, seat
            );
            bookings.add(booking);
            return booking;
        } finally {
            show.getLock().unlock();
        }
    }

//    public Booking bookSeat(User user, Show show, Seat seat) {
//        String lockKey = show.getShowId() + "_" + seat.getSeatId();
//        ReentrantLock lock = lockMap.computeIfAbsent(lockKey, k -> new ReentrantLock());
//
//        lock.lock();
//        try {
//            if (show.isSeatBooked(seat.getSeatId())) {
//                throw new IllegalArgumentException("Seat already booked for this show");
//            }
//            show.bookSeat(seat.getSeatId());
//            // create and return booking...
//        } finally {
//            lock.unlock();
//        }
//    }

    public void cancelBooking(Booking booking) {
        Show show = booking.getShow();
        show.getLock().lock();
        try {
            booking.cancel();
            show.releaseSeat(booking.getSeat().getSeatId());
        } finally {
            show.getLock().unlock();
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
