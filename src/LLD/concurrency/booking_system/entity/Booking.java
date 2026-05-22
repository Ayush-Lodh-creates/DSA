package LLD.concurrency.booking_system.entity;

import LLD.concurrency.booking_system.enums.BookingStatus;

import java.util.List;

public class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final User user;
    private BookingStatus bookingStatus;

    public Booking(String id, Show show, List<Seat> seatsBooked, User user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception {
        if(this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot confirm a booking that is not in the CREATED state.");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws Exception {
        if(this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot expire a booking that is not in the CREATED state.");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

    public Show getShow() {
        return show;
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public User getUser() {
        return user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
