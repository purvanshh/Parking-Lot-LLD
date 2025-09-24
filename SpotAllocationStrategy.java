public interface SpotAllocationStrategy {
    ParkingSpot findSpot(ParkingLot lot, Vehicle vehicle);
}