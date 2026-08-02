package LLD.standard.car_rental;

import LLD.standard.car_rental.entities.*;
import LLD.standard.car_rental.factory.VehicleFactory;
import LLD.standard.car_rental.observer.EmailSubscriber;
import LLD.standard.car_rental.service.BookingService;
import LLD.standard.car_rental.service.RentalService;
import LLD.standard.car_rental.strategy.CardPayment;
import LLD.standard.car_rental.strategy.CashPayment;
import LLD.standard.car_rental.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        // Setup vehicles via factory
        Vehicle sedan = VehicleFactory.getVehicle("v1", "SEDAN", "MH-01-1234", "Honda City", 1500, 4);
        Vehicle suv = VehicleFactory.getVehicle("v2", "SUV", "MH-01-5678", "Toyota Fortuner", 3000, 6);
        Vehicle bike = VehicleFactory.getVehicle("v3", "BIKE", "MH-01-9999", "Royal Enfield", 500, 2);

        // Setup slots
        Slot slot1 = new Slot(1, sedan, true);
        Slot slot2 = new Slot(2, suv, true);
        Slot slot3 = new Slot(3, bike, true);

        // Setup rental store and register with singleton service
        RentalStore store = new RentalStore(1, "Mumbai Central Rentals", "Mumbai", 9876543210L);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);

        RentalService rentalService = RentalService.getInstance();
        rentalService.addRentalStore(store, slots);

        System.out.println("Available slots: " + rentalService.getAvailableSlotsCount(store));

        // Setup users with observers
        User user1 = new User(1, "Ayush", "ayush@email.com");
        user1.addSubscriber(new EmailSubscriber("ayush@email.com"));

        User user2 = new User(2, "Rahul", "rahul@email.com");
        user2.addSubscriber(new EmailSubscriber("rahul@email.com"));

        // Book a slot
        BookingService bookingService = new BookingService();
        Booking booking1 = bookingService.bookSlot(user1, store, slot1);
        System.out.println("Booking created: " + booking1.getBookingId() + " | State: " + booking1.getState().getState());

        // Make payment and confirm
        Payment payment1 = new Payment(UUID.randomUUID().toString(), sedan.getCost(), String.valueOf(user1.getId()));
        PaymentStrategy cardPayment = new CardPayment();
        cardPayment.pay(payment1);
        user1.notifySubscribers(payment1);
        booking1.confirm();
        System.out.println("Booking state after payment: " + booking1.getState().getState());

        System.out.println("Available slots after booking: " + rentalService.getAvailableSlotsCount(store));

        // Book another slot with cash payment
        Booking booking2 = bookingService.bookSlot(user2, store, slot2);
        Payment payment2 = new Payment(UUID.randomUUID().toString(), suv.getCost(), String.valueOf(user2.getId()));
        PaymentStrategy cashPayment = new CashPayment();
        cashPayment.pay(payment2);
        user2.notifySubscribers(payment2);
        booking2.confirm();
        System.out.println("Booking2 state: " + booking2.getState().getState());

        // Cancel booking1
        bookingService.cancelBooking(booking1);
        System.out.println("Booking1 state after cancel: " + booking1.getState().getState());
        System.out.println("Available slots after cancel: " + rentalService.getAvailableSlotsCount(store));

        // Try to cancel again — should be no-op
        bookingService.cancelBooking(booking1);

        // Try booking a slot that's already taken
        try {
            bookingService.bookSlot(user1, store, slot2);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}
