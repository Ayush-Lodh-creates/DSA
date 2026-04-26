package LLD.standard.ParkingLot.entity;

import java.util.List;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;

    public ParkingLot(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(String vehicleType) {
        for(ParkingSpot spot : this.parkingSpots) {
            if(!spot.isOccupied() && spot.getSpotType().equalsIgnoreCase(vehicleType)) {
                return spot;
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle.getVehicleType());
        if(spot != null) {
            spot.parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully in " + spot.getSpotNumber());
            return spot;
        }
        System.out.println("No parking spot available for " + vehicle.getVehicleType());
        return null;
    }

    public void vacateSpot(ParkingSpot spot, Vehicle vehicle) {
        if(spot != null && spot.isOccupied() && spot.getVehicle().equals(vehicle)) {
            spot.vacate();
            System.out.println(vehicle.getVehicleType() + " vacated the spot: " + spot.getSpotNumber());
        } else {
            throw new IllegalArgumentException("Invalid operation ! Either the spot is already vacant or the vehicle does not match.");
        }
    }

    public ParkingSpot getSpotByNumber(int spotNumber) {
        for(ParkingSpot spot : this.parkingSpots) {
            if(spot.getSpotNumber() == spotNumber) {
                return spot;
            }
        }
        return null;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}
