public class ElectricCarSpot extends ParkingSpot {
    private boolean hasCharger;

    public ElectricCarSpot(String spotId, boolean hasCharger) {
        super(spotId, SpotType.ELECTRIC_CAR);
        this.hasCharger = hasCharger;
    }

    public boolean hasCharger() {
        return hasCharger;
    }
}