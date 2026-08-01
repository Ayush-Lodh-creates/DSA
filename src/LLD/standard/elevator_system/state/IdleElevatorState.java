package LLD.standard.elevator_system.state;

import LLD.standard.elevator_system.entities.Elevator;

public class IdleElevatorState implements ElevatorState {

    @Override
    public void step(Elevator elevator) {
        if (elevator.hasPendingRequests()) {
            elevator.pickNextStopAndMove();
        }
    }
}
