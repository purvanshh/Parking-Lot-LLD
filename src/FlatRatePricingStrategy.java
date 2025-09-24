public class FlatRatePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Ticket ticket) {
        return 50.0; // Example: Flat rate of $50
    }
}