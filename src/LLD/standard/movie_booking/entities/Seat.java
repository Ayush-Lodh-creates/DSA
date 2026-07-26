package LLD.standard.movie_booking.entities;

public class Seat {

    private String seatId;
    private int seatNumber;
    private int price;

    public Seat(String seatId, int seatNumber, int price) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
