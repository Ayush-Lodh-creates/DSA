package LLD.concurrency.gym_booking.entities;

import java.time.Instant;
import java.util.Date;

public class SeatLock {

    private Seat seat;
    private TimeSlot timeSlot;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private User lockedBy;

    public SeatLock(Seat seat, TimeSlot timeSlot, Integer timeoutInSeconds, Date lockTime, User lockedBy) {
        this.seat = seat;
        this.timeSlot = timeSlot;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = lockedBy;
    }

    public boolean isLockExpired() {
        Long now = Instant.now().toEpochMilli();
        Long locktime = lockTime.toInstant().toEpochMilli();
        return (locktime + timeoutInSeconds * 1000) < now;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(User lockedBy) {
        this.lockedBy = lockedBy;
    }
}
