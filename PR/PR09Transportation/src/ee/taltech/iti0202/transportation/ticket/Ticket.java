package ee.taltech.iti0202.transportation.ticket;

public class Ticket {
    private final double price;

    /**
     * Ticket constructor.
     * @param price - price that the ticket is worth.
     */
    public Ticket(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
