package LLD.standard.movie_booking.strategy;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(int value) {
        System.out.println("Cash payment of " + value + " processed.");
    }
}
