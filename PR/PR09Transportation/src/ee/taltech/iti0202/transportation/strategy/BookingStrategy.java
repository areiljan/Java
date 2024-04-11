package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

public interface BookingStrategy {
    /**
     * Book a ticket.
     * @param person - the person who books.
     * @param date - the date to book.
     * @param price - the price of the ticket.
     * @return - Ticket that was booked.
     */
    Ticket bookTicket(Person person, LocalDate date, double price);
}
