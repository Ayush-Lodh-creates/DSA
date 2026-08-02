package LLD.standard.car_rental.factory;

import LLD.standard.car_rental.entities.Bike;
import LLD.standard.car_rental.entities.Car;
import LLD.standard.car_rental.entities.Vehicle;

public class VehicleFactory {

    public static Vehicle getVehicle(String id, String type, String licenseNumber, String vehicleName, int cost, int seats) {
        if(type.equalsIgnoreCase("SEDAN")) {
            return new Car(id, licenseNumber, vehicleName, cost, seats);
        } else if(type.equalsIgnoreCase("SUV")) {
            return new Car(id, licenseNumber, vehicleName, cost, seats);
        } else if(type.equalsIgnoreCase("BIKE")) {
            return new Bike(id, licenseNumber, vehicleName, cost);
        } else {
            return new Car(id, licenseNumber, vehicleName, cost, seats);
        }
    }
}
