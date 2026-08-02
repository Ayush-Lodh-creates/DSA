package LLD.standard.car_rental.entities;

import LLD.standard.car_rental.enums.VehicleType;

public class Bike extends Vehicle {

    public Bike(String id, String licenseNumber, String vehicleName, int cost) {
        super(id, VehicleType.TWO_WHEELER, licenseNumber, vehicleName, cost, 2);
    }
}
