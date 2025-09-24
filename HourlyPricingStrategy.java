public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Ticket ticket) {
        long duration = ticket.getExitTime() - ticket.getEntryTime();
        return (duration / (1000 * 60 * 60)) * 10.0; // Example: $10 per hour
    }
}