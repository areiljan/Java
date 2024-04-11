package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

public class BusStrategy implements BookingStrategy {
    @Override
    public Ticket bookTicket(Person person, LocalDate date, double price) {
        double adjustedPrice = 0;
        if (date.getDayOfMonth() % 2 == 0) {
            adjustedPrice = price * 0.5;
        } else {
            adjustedPrice = price * (1 - ((100 - person.getName().length()) / 100));
        }
        return new Ticket(adjustedPrice);
    }
}
