package LLD.standard.ParkingLot.entity;

import LLD.standard.ParkingLot.strategy.PaymentStrategy;

public class Payment {
    private double amount;
    private PaymentStrategy paymentStrategy;

    public Payment(double amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        if(this.amount > 0) {
            this.paymentStrategy.processPayment(this.amount);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }
}
