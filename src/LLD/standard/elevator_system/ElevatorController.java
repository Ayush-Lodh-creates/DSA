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

    // simplest dispatch: pick nearest idle-ish elevator
    Elevator assignElevator(int floor, Direction dir) {
        return elevators.stream()
                .min(Comparator.comparingInt(e -> Math.abs(e.currentFloor - floor)))
                .orElseThrow();
    }
}