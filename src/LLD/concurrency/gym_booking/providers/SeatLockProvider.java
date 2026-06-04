package LLD.concurrency.gym_booking.providers;

import LLD.concurrency.gym_booking.entities.Seat;
import LLD.concurrency.gym_booking.entities.SeatLock;
import LLD.concurrency.gym_booking.entities.TimeSlot;
import LLD.concurrency.gym_booking.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockProvider {

    private final Integer lockTime;
    private Map<TimeSlot, Map<Seat, SeatLock>> locks;

    public SeatLockProvider(Integer lockTime) {
        this.lockTime = lockTime;
        this.locks = new ConcurrentHashMap<>();
    }

    public void lockSeats(TimeSlot timeSlot, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLockMap = locks.computeIfAbsent(timeSlot, k -> new ConcurrentHashMap<>());
        synchronized (seatLockMap) {
            for(Seat seat : seats) {
                if(seatLockMap.containsKey(seat)) {
                    SeatLock seatLock = seatLockMap.get(seat);
                    if(seatLock.isLockExpired()) {
                        throw new RuntimeException("Seat " + seat.getSeartId() + " is already locked for time slot " + timeSlot.getId());
                    }
                }
            }
            Date date = new Date();
            for(Seat seat : seats) {
                SeatLock seatLock = new SeatLock(seat, timeSlot, lockTime, date, user);
                seatLockMap.put(seat, seatLock);
            }
        }
    }

//    public void unlockSeats(TimeSlot timeSlot, )
}
