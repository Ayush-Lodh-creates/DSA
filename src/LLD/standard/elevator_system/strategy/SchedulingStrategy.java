package LLD.standard.elevator_system.strategy;

import LLD.standard.elevator_system.enums.Direction;

import java.util.TreeSet;

public interface SchedulingStrategy {
    int getNextStop(int currentFloor, Direction currentDirection, TreeSet<Integer> upStops, TreeSet<Integer> downStops);
}
