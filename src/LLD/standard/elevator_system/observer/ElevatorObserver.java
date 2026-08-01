package LLD.standard.elevator_system.observer;

import LLD.standard.elevator_system.enums.Direction;

public interface ElevatorObserver {

    public void onFloorChange(int elevatorId, int floor, Direction direction);
}
