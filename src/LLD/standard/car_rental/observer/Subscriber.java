package LLD.standard.car_rental.observer;

import LLD.standard.car_rental.entities.Payment;

public interface Subscriber {

    public void sendNotification(Payment payment);
}
