package LLD.standard.car_rental.entities;

import LLD.standard.car_rental.state.BookingState;
import LLD.standard.car_rental.state.PendingState;

public class Booking {

    private String bookingId;
    private User user;
    private Slot slot;
    private RentalStore rentalStore;
    private BookingState state = new PendingState();

    public Booking(String bookingId, User user, Slot slot, RentalStore rentalStore) {
        this.bookingId = bookingId;
        this.user = user;
        this.slot = slot;
        this.rentalStore = rentalStore;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public void cancel() {
        state.cancel(this);
    }

    public void confirm() {
        state.confirm(this);
    }

    public RentalStore getRentalStore() {
        return rentalStore;
    }

    public void setRentalStore(RentalStore rentalStore) {
        this.rentalStore = rentalStore;
    }
}
