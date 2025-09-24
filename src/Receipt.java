import java.util.*;

public class Receipt {
    private String receiptId;
    private double amount;
    private Ticket ticket;

    public Receipt(Ticket ticket, double amount) {
        this.receiptId = UUID.randomUUID().toString();
        this.ticket = ticket;
        this.amount = amount;
    }
}