package LLD.standard.elevator_system.state;

import LLD.standard.elevator_system.entities.Elevator;

public class IdleElevatorState implements ElevatorState {

    @Override
    public void step(Elevator elevator) {
        // If the elevator is idle, it can either stay idle or start moving if there are requests.
        if (elevator.hasPendingRequests()) {
            elevator.setState(new MovingElevatorState());
        }
    }
}
