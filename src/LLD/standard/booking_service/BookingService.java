package LLD.standard.booking_service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    private final Map<String, Boolean> seatStore = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();
    private final List<Booking> bookings = new CopyOnWriteArrayList<>();

    public BookingService(List<Seat> seats) {
        for(Seat seat : seats) {
            seatStore.put(seat.getSeatId(), false);
            lockMap.put(seat.getSeatId(), new ReentrantLock());
        }
    }

    public Booking bookSeat(User user, String seatId) {
        ReentrantLock lock = lockMap.get(seatId);
        lock.lock();
        try {
            boolean alreadyBooked = seatStore.get(seatId);
            if(alreadyBooked) {
                return new Booking(
                        UUID.randomUUID().toString(),
                        user.getId(),
                        seatId,
                        BookingStatus.FAILED
                );
            }
            seatStore.put(seatId, true);
            Booking booking = new Booking(UUID.randomUUID().toString(),
                    user.getId(), seatId, BookingStatus.CONFIRMED);
            bookings.add(booking);
            return booking;
        } finally {
            lock.unlock();
        }
    }
}
