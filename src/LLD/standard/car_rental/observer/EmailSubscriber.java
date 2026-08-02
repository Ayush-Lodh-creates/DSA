package LLD.standard.car_rental.observer;

import LLD.standard.car_rental.entities.Payment;

public class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void sendNotification(Payment payment) {
        System.out.println("Sending email notification to " + email + " for payment: " + payment.getPaymentId());
    }
}
