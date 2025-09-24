import java.util.*;

public class ParkingLot {
    private List<ParkingFloor> floors;
    private SpotInventory inventory;

    public ParkingLot(List<ParkingFloor> floors, SpotInventory inventory) {
        this.floors = floors;
        this.inventory = inventory;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public SpotInventory getInventory() {
        return inventory;
    }
}