package LLD.standard.car_rental.entities;

import LLD.standard.car_rental.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private final List<Subscriber> subscriberList;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriberList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscriberList.add(subscriber);
    }

    public void notifySubscribers(Payment payment) {
        for(Subscriber subscriber : subscriberList) {
            subscriber.sendNotification(payment);
        }
    }
}
