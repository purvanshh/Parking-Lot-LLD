import java.util.*;

public class ParkingService {
    private ParkingLot lot;
    private SpotAllocationStrategy allocationStrategy;
    private PricingStrategy pricingStrategy;

    public ParkingService(ParkingLot lot, SpotAllocationStrategy allocationStrategy, PricingStrategy pricingStrategy) {
        this.lot = lot;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket allocateSpot(Vehicle vehicle, EntryGate entryGate) {
        ParkingSpot spot = allocationStrategy.findSpot(lot, vehicle);
        if (spot != null) {
            spot.assignVehicle(vehicle);
            return new Ticket(vehicle, spot, entryGate);
        }
        return null;
    }

    public Receipt processExit(Ticket ticket, ExitGate exitGate) {
        ticket.setExitGate(exitGate);
        ticket.setExitTime(System.currentTimeMillis());
        double amount = pricingStrategy.calculatePrice(ticket);
        ticket.getSpot().removeVehicle();
        return new Receipt(ticket, amount);
    }
}