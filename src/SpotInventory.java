import java.util.*;

public class SpotInventory {
    private Map<SpotType, List<ParkingSpot>> availableSpots;

    public SpotInventory() {
        this.availableSpots = new HashMap<>();
    }

    public ParkingSpot getAvailableSpot(SpotType type) {
        List<ParkingSpot> spots = availableSpots.get(type);
        if (spots != null && !spots.isEmpty()) {
            return spots.get(0);
        }
        return null;
    }

    public void markOccupied(ParkingSpot spot) {
        List<ParkingSpot> spots = availableSpots.get(spot.getType());
        if (spots != null) {
            spots.remove(spot);
        }
    }

    public void markAvailable(ParkingSpot spot) {
        availableSpots.computeIfAbsent(spot.getType(), k -> new ArrayList<>()).add(spot);
    }
}