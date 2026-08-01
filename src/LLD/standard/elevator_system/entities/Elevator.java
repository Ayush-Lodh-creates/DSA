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
    public Direction direction = Direction.IDLE;
    ElevatorState state = new IdleElevatorState();
    TreeSet<Integer> upStops = new TreeSet<>();
    TreeSet<Integer> downStops = new TreeSet<>();
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
                downStops.add(request.floor);
            }
        } else {
            if(this.direction.equals(request.direction)) {
                if(Direction.UP.equals(this.direction)) {
                    upStops.add(request.floor);
                } else {
                    downStops.add(request.floor);
                }
            } else {
                if(Direction.UP.equals(this.direction)) {
                    downStops.add(request.floor);
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
        return !upStops.isEmpty() || !downStops.isEmpty();
    }

    public void pickNextStopAndMove() {
        if (!hasPendingRequests()) {
            direction = Direction.IDLE;
            state = new IdleElevatorState();
            return;
        }
        targetFloor = schedulingStrategy.getNextStop(currentFloor, direction, upStops, downStops);
        direction = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;
        state = new MovingElevatorState();
    }

    public void moveOneStep() {
        if (Direction.IDLE.equals(direction)) return;
        currentFloor += (Direction.UP.equals(direction) ? 1 : -1);
        notifyObservers();
        if (currentFloor == targetFloor) {
            upStops.remove(currentFloor);
            downStops.remove(currentFloor);
            pickNextStopAndMove();
        }
    }

    public void step() {
        state.step(this);
    }

    public void setState(ElevatorState elevatorState) {
        this.state = elevatorState;
    }
}
