package LLD.standard.elevator_system.strategy;

import LLD.standard.elevator_system.enums.Direction;

import java.util.TreeSet;

public class LookStrategy implements SchedulingStrategy {

    @Override
    public int getNextStop(int currentFloor, Direction dir, TreeSet<Integer> upStops, TreeSet<Integer> downStops) {
        if (dir == Direction.UP) {
            Integer next = upStops.ceiling(currentFloor + 1);
            if (next != null) return next;
            Integer down = downStops.floor(currentFloor);
            if (down != null) return down;
        } else if (dir == Direction.DOWN) {
            Integer next = downStops.floor(currentFloor - 1);
            if (next != null) return next;
            Integer up = upStops.ceiling(currentFloor);
            if (up != null) return up;
        }
        // idle - pick nearest
        Integer up = upStops.ceiling(currentFloor);
        Integer down = downStops.floor(currentFloor);
        if (up == null && down == null) return currentFloor;
        if (up == null) return down;
        if (down == null) return up;
        return (up - currentFloor <= currentFloor - down) ? up : down;
    }
}
