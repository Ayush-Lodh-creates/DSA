package LLD.concurrency.booking_system.providers;

import LLD.concurrency.booking_system.entity.Seat;
import LLD.concurrency.booking_system.entity.SeatLock;
import LLD.concurrency.booking_system.entity.Show;
import LLD.concurrency.booking_system.entity.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockProvider implements ISeatLockProvider {

    private final Integer lockTimeOut;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public SeatLockProvider(Integer lockTimeOut) {
        this.lockTimeOut = lockTimeOut;
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, User user) throws Exception {
        Map<Seat, SeatLock> seatLocks = locks.computeIfAbsent(show, k -> new ConcurrentHashMap<>());
        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)) {
                    SeatLock existingLock = seatLocks.get(seat);
                    if (!existingLock.isLockExpired()) {
                        throw new Exception("Seat " + seat.getSeatId() + " is already locked for show " + show.getId());
                    }
                }
            }
            Date now = new Date();
            for(Seat seat : seats) {
                SeatLock lock = new SeatLock(seat, show, lockTimeOut, now, user);
                seatLocks.put(seat, lock);
            }
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) {
            return;
        }
        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)) {
                    SeatLock existingLock = seatLocks.get(seat);
                    if (existingLock != null && existingLock.getLockedBy().equals(user)) {
                        seatLocks.remove(seat);
                    }
                }
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if(seatLocks == null) {
            return false;
        }
        synchronized (seatLocks) {
            SeatLock lock = seatLocks.get(seat);
            return lock != null && !lock.isLockExpired() && lock.getLockedBy().equals(user);
        }
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if(seatLocks == null) {
            return Collections.emptyList();
        }
        synchronized (seatLocks) {
            return seatLocks.entrySet().stream()
                    .filter(entry -> !entry.getValue().isLockExpired())
                    .map(Map.Entry::getKey)
                    .toList();
        }
    }
}
