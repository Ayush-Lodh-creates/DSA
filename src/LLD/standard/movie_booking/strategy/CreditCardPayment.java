package LLD.standard.movie_booking.strategy;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(int value) {
        System.out.println("Credit card payment of " + value + " processed.");
    }
}
