package LLD.standard.ParkingLot.factory;

import LLD.standard.ParkingLot.entity.Bike;
import LLD.standard.ParkingLot.entity.Car;
import LLD.standard.ParkingLot.entity.OtherVehicle;
import LLD.standard.ParkingLot.entity.Vehicle;
import LLD.standard.ParkingLot.strategy.ParkingFeeStrategy;

public class VehicleFactory {

    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy) {
        if(vehicleType.equalsIgnoreCase("Car")) {
            return new Car(licensePlate, feeStrategy);
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            return new Bike(licensePlate, feeStrategy);
        }
        return new OtherVehicle(licensePlate, feeStrategy);
    }
}
