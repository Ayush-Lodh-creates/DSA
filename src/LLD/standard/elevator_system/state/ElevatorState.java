package LLD.standard.elevator_system.state;

import LLD.standard.elevator_system.entities.Elevator;

public interface ElevatorState {

    void step(Elevator elevator);
}
