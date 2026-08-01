package LLD.standard.elevator_system;

import LLD.standard.elevator_system.entities.Elevator;
import LLD.standard.elevator_system.entities.Request;
import LLD.standard.elevator_system.enums.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElevatorController {
    private static ElevatorController instance;
    private final List<Elevator> elevators = new ArrayList<>();

    private ElevatorController() {}

    public static synchronized ElevatorController getInstance() {
        if (instance == null) instance = new ElevatorController();
        return instance;
    }

    public synchronized void addElevator(Elevator e) { elevators.add(e); }

    public synchronized void handleRequest(int floor, Direction dir) {
        Elevator e = assignElevator(floor, dir);
        e.addRequest(new Request(floor, dir));
    }

    private Elevator assignElevator(int floor, Direction dir) {
        return elevators.stream()
                .min(Comparator.comparingInt(e -> {
                    int dist = Math.abs(e.currentFloor - floor);
                    if (Direction.IDLE.equals(e.direction)) return dist;
                    boolean sameDir = e.direction == dir;
                    boolean notPassed = (dir == Direction.UP && e.currentFloor <= floor)
                            || (dir == Direction.DOWN && e.currentFloor >= floor);
                    return (sameDir && notPassed) ? dist : dist + 1000;
                }))
                .orElseThrow();
    }

    public synchronized void stepAll() {
        for (Elevator e : elevators) {
            e.step();
        }
    }
}