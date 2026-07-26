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

        User alice = UserFactory.createUser("external", "Alice", "alice@gmail.com");
        User bob = UserFactory.createUser("external", "Bob", "bob@gmail.com");
        User admin = UserFactory.createUser("admin", "Ayush", "ayush@gmail.com");

        Theatre theatre1 = new Theatre("1", "PVR");
        Theatre theatre2 = new Theatre("2", "INOX");

        Movie movie1 = new Movie("1", "Avengers");
        Movie movie2 = new Movie("2", "Iron Man");

        Screen screen1 = new Screen("1", 1);
        Screen screen2 = new Screen("2", 2);
        Screen screen3 = new Screen("3", 3);

        TheatreService theatreService = new TheatreService();
        theatreService.addTheatre(theatre1, new ArrayList<>(List.of(screen1, screen2)), admin);
        theatreService.addTheatre(theatre2, new ArrayList<>(List.of(screen3)), admin);

        Seat seat1 = new Seat("1", 1, 100);
        Seat seat2 = new Seat("2", 2, 100);
        Seat seat3 = new Seat("3", 3, 150);
        Seat seat4 = new Seat("4", 4, 150);

        ScreenService screenService = new ScreenService();
        screenService.addScreen(screen1, new ArrayList<>(List.of(seat1, seat2, seat3)), admin);
        screenService.addScreen(screen2, new ArrayList<>(List.of(seat4)), admin);

        Show show1 = new Show("1", movie1, screen1, "7:00 PM");
        Show show2 = new Show("2", movie1, screen1, "10:00 PM");
        Show show3 = new Show("3", movie2, screen2, "8:00 PM");

        BookingService bookingService = new BookingService();

        // Two users racing for the same seat in the same show
        Thread userThread1 = new Thread(() -> {
            try {
                Booking booking = bookingService.bookSeat(alice, show1, seat1);
                System.out.println("Booking successful for " + alice.getName() + " | Show: " + show1.getStartTime() + " | Booking ID: " + booking.getBookingId());
            } catch (IllegalArgumentException e) {
                System.out.println("Booking failed for " + alice.getName() + " | Reason: " + e.getMessage());
            }
        });

        Thread userThread2 = new Thread(() -> {
            try {
                Booking booking = bookingService.bookSeat(bob, show1, seat1);
                System.out.println("Booking successful for " + bob.getName() + " | Show: " + show1.getStartTime() + " | Booking ID: " + booking.getBookingId());
            } catch (IllegalArgumentException e) {
                System.out.println("Booking failed for " + bob.getName() + " | Reason: " + e.getMessage());
            }
        });

        userThread1.start();
        userThread2.start();

        try {
            userThread1.join();
            userThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Same seat, different show — should succeed (seat1 for 10PM show)
        Booking bobBooking = bookingService.bookSeat(bob, show2, seat1);
        System.out.println("Bob booked seat1 for 10PM show | Booking ID: " + bobBooking.getBookingId());

        // Payment flow
        Booking pending = bookingService.getPendingPaymentBooking();
        if (pending != null) {
            PaymentStrategy paymentStrategy = new CashPayment();
            try {
                paymentStrategy.pay(pending.getSeat().getPrice());
                pending.confirm();
                Subscriber subscriber = new EmailSubscriber(pending.getUser().getEmail());
                subscriber.sendNotification("Booking confirmed for ID: " + pending.getBookingId());
            } catch (Exception e) {
                bookingService.cancelBooking(pending);
                System.out.println("Payment failed, booking cancelled: " + pending.getBookingId());
            }
        }
    }
}
