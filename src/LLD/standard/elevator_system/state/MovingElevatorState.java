package LLD.standard.elevator_system.state;

import LLD.standard.elevator_system.entities.Elevator;

public class MovingElevatorState implements ElevatorState{

    @Override
    public void step(Elevator elevator) {
        elevator.moveOneStep();
    }
}
