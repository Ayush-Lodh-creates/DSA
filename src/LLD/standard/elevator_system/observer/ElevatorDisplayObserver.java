package LLD.standard.elevator_system.observer;

import LLD.standard.elevator_system.enums.Direction;

public class ElevatorDisplayObserver implements ElevatorObserver {

    @Override
    public void onFloorChange(int elevatorId, int floor, Direction direction) {
        System.out.println("Elevator " + elevatorId + " is now on floor " + floor + " moving " + direction);
    }
}
