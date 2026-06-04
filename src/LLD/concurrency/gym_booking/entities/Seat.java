package LLD.concurrency.gym_booking.entities;

public class Seat {

    private final int seartId;
    private final int row;
    private final String seatCategory;

    public Seat(int seartId, int row, String seatCategory) {
        this.seartId = seartId;
        this.row = row;
        this.seatCategory = seatCategory;
    }

    public int getSeartId() {
        return seartId;
    }

    public int getRow() {
        return row;
    }

    public String getSeatCategory() {
        return seatCategory;
    }
}
