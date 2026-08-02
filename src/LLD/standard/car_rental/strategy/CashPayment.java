package LLD.standard.car_rental.strategy;

import LLD.standard.car_rental.entities.Payment;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Payment of " + payment.getPrice() + " made using Cash for user: " + payment.getUserId());
    }
}
