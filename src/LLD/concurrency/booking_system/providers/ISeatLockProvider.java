package LLD.concurrency.booking_system.providers;

import LLD.concurrency.booking_system.entity.Seat;
import LLD.concurrency.booking_system.entity.Show;
import LLD.concurrency.booking_system.entity.User;

import java.util.List;

public interface ISeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, User user) throws Exception;
    void unlockSeats(Show show, List<Seat> seats, User user);
    boolean validateLock(Show show, Seat seat, User user);
    List<Seat> getLockedSeats(Show show);
}
