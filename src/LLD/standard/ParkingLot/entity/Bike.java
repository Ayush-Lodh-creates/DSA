package LLD.standard.ParkingLot.entity;

import LLD.standard.ParkingLot.strategy.ParkingFeeStrategy;

public class Bike extends Vehicle{

    public Bike(String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(licensePlate, "Bike", parkingFeeStrategy);
    }
}
