import java.util.*;

public abstract class ParkingSpot {
    private String spotId;
    private SpotType type;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, SpotType type) {
        this.spotId = spotId;
        this.type = type;
        this.isAvailable = true;
    }

    public String getSpotId() {
        return spotId;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }
}