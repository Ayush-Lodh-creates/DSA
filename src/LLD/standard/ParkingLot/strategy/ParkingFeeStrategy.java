package LLD.standard.ParkingLot.strategy;

import LLD.standard.ParkingLot.enums.DurationType;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duration, DurationType durationType);
}
