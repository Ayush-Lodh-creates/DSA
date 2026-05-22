package LLD.concurrency.booking_system.entity;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    private final int id;
    private final String name;
    private final Theatre theatre;
    private List<Seat> seats;

    public Screen(int id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        seats = new ArrayList<>();
    }

    public void addSeats(Seat seat) {
        seats.add(seat);
    }

    public int getScreenId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
