package LLD.standard.car_rental.strategy;

import LLD.standard.car_rental.entities.Payment;

public interface PaymentStrategy {

    void pay(Payment payment);
}
