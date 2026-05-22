package LLD.concurrency.booking_system.entity;

import LLD.concurrency.booking_system.enums.SeatCategory;

public class Seat {
    private final int seartId;
    private final int row;
    private final SeatCategory seatCategory;

    public Seat(int seartId, int row, SeatCategory seatCategory) {
        this.seartId = seartId;
        this.row = row;
        this.seatCategory = seatCategory;
    }

    public int getSeatId() {
        return seartId;
    }

    public int getRow() {
        return row;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
}
