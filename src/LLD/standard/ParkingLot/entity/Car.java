package LLD.standard.ParkingLot.entity;

import LLD.standard.ParkingLot.strategy.ParkingFeeStrategy;

public class Car extends Vehicle {

    public Car(String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(licensePlate, "Car", parkingFeeStrategy);
    }
}
