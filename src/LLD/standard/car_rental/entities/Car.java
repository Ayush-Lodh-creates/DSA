package LLD.standard.car_rental.entities;

import LLD.standard.car_rental.enums.VehicleType;

public class Car extends Vehicle{

    public Car(String id, String licenseNumber, String vehicleName, int cost, int seats) {
        super(id, VehicleType.FOUR_WHEELER, licenseNumber, vehicleName, cost, seats);
    }
}
