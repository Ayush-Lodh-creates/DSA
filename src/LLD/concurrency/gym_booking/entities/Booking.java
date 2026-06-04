package LLD.concurrency.gym_booking.entities;

import java.util.List;

public class Booking {

    private final String id;
    private final TimeSlot timeSlot;
    private final List<Seat> seatsBooked;
    private final User user;
    private String bookingStatus;

    public Booking(String id, TimeSlot timeSlot, List<Seat> seatsBooked, User user) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = "CONFIRMED";
    }

    public String getId() {
        return id;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public User getUser() {
        return user;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}
