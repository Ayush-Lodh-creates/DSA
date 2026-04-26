package LLD.standard.ParkingLot.entity;

import LLD.standard.ParkingLot.strategy.ParkingFeeStrategy;

public class OtherVehicle extends Vehicle {

    public OtherVehicle(String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(licensePlate, "Other", parkingFeeStrategy);
    }
}
