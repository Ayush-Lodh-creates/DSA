package LLD.standard.movie_booking.entities;

import java.util.concurrent.locks.ReentrantLock;

public class Seat {

    private String seatId;
    private int seatNumber;
    private int price;
    private Boolean isBooked;
    public ReentrantLock lock;

    public Seat(String seatId, int seatNumber, int price, Boolean isBooked) {
        this.seatId = seatId;
        this.price = price;
        this.seatNumber = seatNumber;
        this.lock = new ReentrantLock();
        this.isBooked = isBooked;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        this.isBooked = booked;
    }
}
