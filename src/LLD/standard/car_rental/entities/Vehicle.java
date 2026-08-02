package LLD.standard.car_rental.entities;

import LLD.standard.car_rental.enums.VehicleType;

public abstract class Vehicle {

    private String id;
    private VehicleType vehicleType;
    private String licenseNumber;
    private String vehicleName;
    private int cost;
    private int seats;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Vehicle(String id, VehicleType vehicleType, String licenseNumber, String vehicleName, int cost, int seats) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.vehicleName = vehicleName;
        this.cost = cost;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
