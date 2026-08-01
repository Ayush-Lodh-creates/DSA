package LLD.standard.elevator_system.entities;

import LLD.standard.elevator_system.enums.Direction;
import LLD.standard.elevator_system.observer.ElevatorObserver;
import LLD.standard.elevator_system.state.ElevatorState;
import LLD.standard.elevator_system.state.IdleElevatorState;
import LLD.standard.elevator_system.state.MovingElevatorState;
import LLD.standard.elevator_system.strategy.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator {

    final int id;
    public int currentFloor;
    Direction direction = Direction.IDLE;
    ElevatorState state = new IdleElevatorState();
    TreeSet<Integer> upStops = new TreeSet<>();
    TreeSet<Integer> downSteps = new TreeSet<>();
    int targetFloor = -1;
    SchedulingStrategy schedulingStrategy;
    List<ElevatorObserver> observers = new ArrayList<>();

    public Elevator(int id, SchedulingStrategy schedulingStrategy) {
        this.id = id;
        this.schedulingStrategy = schedulingStrategy;
        this.currentFloor = 0;
    }

    public void addObservers(ElevatorObserver elevatorObserver) {
        observers.add(elevatorObserver);
    }

    private void notifyObservers() {
        for(ElevatorObserver elevatorObserver : observers) {
            elevatorObserver.onFloorChange(id, currentFloor, direction);
        }
    }

    public void addRequest(Request request) {
        if(request.floor == currentFloor) {
            return;
        }
        if(request.direction == null || Direction.IDLE.equals(this.direction)) {
            if(request.floor > currentFloor) {
                upStops.add(request.floor);
            } else {
                downSteps.add(request.floor);
            }
        } else {
            if(this.direction.equals(request.direction)) {
                if(Direction.UP.equals(this.direction)) {
                    upStops.add(request.floor);
                } else {
                    downSteps.add(request.floor);
                }
            } else {
                if(Direction.UP.equals(this.direction)) {
                    downSteps.add(request.floor);
                } else {
                    upStops.add(request.floor);
                }
            }
        }
        if(state instanceof IdleElevatorState) {
            state.step(this);
        }
    }

    public boolean hasPendingRequests() {
        return !upStops.isEmpty() || !downSteps.isEmpty();
    }

    public void pickNextStopAndMove() {
        targetFloor = schedulingStrategy.getNextStop(currentFloor, direction, upStops, downSteps);
        direction = targetFloor > currentFloor ? Direction.UP : targetFloor < currentFloor ? Direction.DOWN : Direction.IDLE;
        state = Direction.IDLE.equals(direction) ? new IdleElevatorState() : new MovingElevatorState();
    }

    public void moveOneStep() {
        currentFloor += (Direction.UP.equals(direction) ? 1 : -1);
        notifyObservers();
        if(currentFloor == targetFloor) {
            upStops.remove(currentFloor);
            downSteps.remove(currentFloor);
            state = new IdleElevatorState();
        }
    }

    public void setState(ElevatorState elevatorState) {
        this.state = elevatorState;
    }
}
