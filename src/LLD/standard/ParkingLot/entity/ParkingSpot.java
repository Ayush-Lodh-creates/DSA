package LLD.standard.ParkingLot.entity;

public abstract class ParkingSpot {

    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private String spotType;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if(isOccupied) {
            throw  new IllegalArgumentException("Spot already occupied");
        }
        if(!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException("This spot is not suitable for " +vehicle.getVehicleType());
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        if(!isOccupied) {
            throw new IllegalArgumentException("Spot is already vacant");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getSpotType() {
        return spotType;
    }
}
