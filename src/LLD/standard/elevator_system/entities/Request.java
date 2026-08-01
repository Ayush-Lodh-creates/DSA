package LLD.standard.elevator_system.entities;

import LLD.standard.elevator_system.enums.Direction;

public class Request {

    // direction is null then the request is from inside the lift
    // if direction is present then the lift needs to go to that floor
    public int floor;
    public Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }
}
