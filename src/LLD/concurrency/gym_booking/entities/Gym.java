package LLD.concurrency.gym_booking.entities;

import java.util.List;

public class Gym {

    private final int id;
    private final String name;
    private final List<Workout> workouts;

    public Gym(int id, String name, List<Workout> workouts) {
        this.id = id;
        this.name = name;
        this.workouts = workouts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
