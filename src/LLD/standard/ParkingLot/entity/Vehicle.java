package LLD.standard.ParkingLot.entity;

import LLD.standard.ParkingLot.enums.DurationType;
import LLD.standard.ParkingLot.strategy.ParkingFeeStrategy;

public abstract class Vehicle {

    private String licensePlate;
    private String vehicleType;
    private ParkingFeeStrategy parkingFeeStrategy;

    public Vehicle(String licensePlate, String vehicleType, ParkingFeeStrategy parkingFeeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double calculateFee(int duration, DurationType durationType) {
        return parkingFeeStrategy.calculateFee(this.vehicleType, duration, durationType);
    }
}
