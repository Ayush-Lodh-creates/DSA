package LLD.concurrency.gym_booking.entities;

import java.util.Date;
import java.util.List;

public class TimeSlot {

    private final int id;
    private final String name;
    private final Workout workout;
    private final Date startTime;
    private final Integer durationInMinutes;

    public TimeSlot(int id, String name, Workout workout, Date startTime, Integer durationInMinutes) {
        this.id = id;
        this.name = name;
        this.workout = workout;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }
}
