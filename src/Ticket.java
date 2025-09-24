import java.util.*;

public class Ticket {
    private String ticketId;
    private long entryTime;
    private long exitTime;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private EntryGate entryGate;
    private ExitGate exitGate;
    private TicketStatus status;

    public Ticket(Vehicle vehicle, ParkingSpot spot, EntryGate entryGate) {
        this.ticketId = UUID.randomUUID().toString();
        this.entryTime = System.currentTimeMillis();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryGate = entryGate;
        this.status = TicketStatus.ACTIVE;
    }

    public void setExitGate(ExitGate exitGate) {
        this.exitGate = exitGate;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}