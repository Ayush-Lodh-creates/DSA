package LLD.concurrency.gym_booking.entities;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private final int id;
    private final String name;
    private final Gym gym;
    private List<Seat> seats;

    public Workout(int id, String name, Gym gym) {
        this.id = id;
        this.name = name;
        this.gym = gym;
        this.seats = new ArrayList<>();
    }

    public void addSeats(Seat seat) {
        this.seats.add(seat);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gym getGym() {
        return gym;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
