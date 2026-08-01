package LLD.standard.elevator_system;

import LLD.standard.elevator_system.entities.Elevator;
import LLD.standard.elevator_system.enums.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ElevatorController {
    private static ElevatorController instance;
    List<Elevator> elevators = new ArrayList<>();

    private ElevatorController() {}

    static ElevatorController getInstance() {
        if (instance == null) instance = new ElevatorController();
        return instance;
    }

    void addElevator(Elevator e) { elevators.add(e); }

    Elevator assignElevator(int floor, Direction dir) {
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

    void stepAll() {
        for (Elevator e : elevators) {
            e.step();
        }
    }
}