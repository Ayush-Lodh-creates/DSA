package LLD.standard.car_rental.service;

import LLD.standard.car_rental.entities.Booking;
import LLD.standard.car_rental.entities.RentalStore;
import LLD.standard.car_rental.entities.Slot;
import LLD.standard.car_rental.entities.User;
import LLD.standard.car_rental.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private List<Booking> bookings = new ArrayList<>();

    public Booking bookSlot(User user, RentalStore rentalStore, Slot slot) {
        try {
            rentalStore.lock.lock();
            if (!slot.isAvailable()) {
                throw new IllegalArgumentException("Slot is already booked");
            }
            Booking booking = new Booking(UUID.randomUUID().toString(), user, slot, rentalStore);
            slot.setAvailable(false);
            bookings.add(booking);
            return booking;
        } finally {
            rentalStore.lock.unlock();
        }
    }

    public void cancelBooking(Booking booking) {
        RentalStore rentalStore = booking.getRentalStore();
        try {
            rentalStore.lock.lock();
            if(booking.getState().getState().equals(BookingStatus.CANCELLED.name())) {
                return;
            }
            Slot slot = booking.getSlot();
            booking.cancel();
            slot.setAvailable(true);
        } finally {
            rentalStore.lock.unlock();
        }
    }
}
