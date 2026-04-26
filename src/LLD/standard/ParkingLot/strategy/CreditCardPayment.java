package LLD.standard.ParkingLot.strategy;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit payment of $" +amount);
    }
}
