package LLD.standard.movie_booking;

import LLD.standard.movie_booking.entities.*;
import LLD.standard.movie_booking.factory.UserFactory;
import LLD.standard.movie_booking.observer.EmailSubscriber;
import LLD.standard.movie_booking.observer.Subscriber;
import LLD.standard.movie_booking.services.BookingService;
import LLD.standard.movie_booking.services.ScreenService;
import LLD.standard.movie_booking.services.TheatreService;
import LLD.standard.movie_booking.strategy.CashPayment;
import LLD.standard.movie_booking.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Movie Booking System");
        User user1 = UserFactory.createUser("external", "Alice", "alice@gmail.com");
        User user3 = UserFactory.createUser("external", "Bob", "bob@gmail.com");
        User user2 = UserFactory.createUser("admin", "Ayush", "ayush@gmail.com");

        Theatre theatre1 = new Theatre("1", "PVR");
        Theatre theatre2 = new Theatre("2", "INOX");

        Movie movie1 = new Movie("1", "Avengers");
        Movie movie2 = new Movie("2", "Iron Man");
        Movie movie3 = new Movie("3", "Dark Knight");

        Screen screen1 = new Screen("1", 1, movie1);
        Screen screen2 = new Screen("2", 2, movie2);
        Screen screen3 = new Screen("3", 3, movie3);

        TheatreService theatreService = new TheatreService();
        theatreService.addTheatre(theatre1, new ArrayList<>(List.of(screen1, screen2)), user2);
        theatreService.addTheatre(theatre2, new ArrayList<>(List.of(screen3)), user2);

        Seat seat1 = new Seat("1", 1, 100, false);
        Seat seat2 = new Seat("2", 2, 100, false);
        Seat seat3 = new Seat("3", 3, 100, false);
        Seat seat4 = new Seat("4", 4, 100, false);
        Seat seat5 = new Seat("5", 5, 100, false);
        Seat seat6 = new Seat("6", 6, 100, false);

        ScreenService screenService = new ScreenService();
        screenService.addScreen(screen1, new ArrayList<>(List.of(seat1, seat2, seat3)), user2);
        screenService.addScreen(screen2, new ArrayList<>(List.of(seat4)), user2);
        screenService.addScreen(screen3, new ArrayList<>(List.of(seat5, seat6)), user2);

        BookingService bookingService = new BookingService();
        Thread userThread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Booking booking1 = bookingService.bookSeat(user1, seat1);
                    System.out.println("Booking successful for user: " + user1.getName() + ", Booking ID: " + booking1.getBookingId());
                } catch (IllegalArgumentException e) {
                    System.out.println("Booking failed for user: " + user1.getName() + ", Reason: " + e.getMessage());
                }
            }
        };

        Thread userThread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Booking booking1 = bookingService.bookSeat(user3, seat1);
                    System.out.println("Booking successful for user: " + user3.getName() + ", Booking ID: " + booking1.getBookingId());
                } catch (IllegalArgumentException e) {
                    System.out.println("Booking failed for user: " + user3.getName() + ", Reason: " + e.getMessage());
                }
            }
        };

        userThread1.start();
        userThread2.start();

        try {
            userThread1.join();
            userThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Something went wrong while executing threads!!!!");
        }

        Booking pending = bookingService.getPendingPaymentBooking();

        if(pending != null) {
            PaymentStrategy paymentStrategy = new CashPayment();
            try {
                paymentStrategy.pay(pending.getSeat().getPrice());
                Subscriber subscriber = new EmailSubscriber(pending.getUser().getEmail());
                subscriber.sendNotification("Your booking has been confirmed for bookingId : " + pending.getBookingId());
                System.out.println("Payment successful for booking ID: " + pending.getBookingId());
            } catch (Exception e) {
                pending.cancel();
                Seat seat = pending.getSeat();
                seat.setBooked(false);
                System.out.println("Payment failed for booking ID: " + pending.getBookingId());
            }
        } else {
            System.out.println("No pending bookings found.");
        }
    }
}
