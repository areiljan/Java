package ee.taltech.iti0202.transportation.travelagency;

import ee.taltech.iti0202.transportation.booking.Booking;
import ee.taltech.iti0202.transportation.ticket.Ticket;

public class TravelAgency {
    private double defaultPrice;
    private double price;

    /**
     * TravelAgency constructor.
     * @param defaultPrice - defaultPrice of the tickets.
     */
    public TravelAgency(double defaultPrice) {
        this.price = defaultPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Book a ticket.
     * @param booking - the booking that has the strategy.
     * @return - a ticket.
     */
    public Ticket bookTicket(Booking booking) {
        return booking.getStrategy().bookTicket(booking.getPerson(), booking.getDate(), price);
    }

}
