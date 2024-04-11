package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.LocalDate;

import static java.lang.Math.round;

public class BusStrategy implements BookingStrategy {
    @Override
    public Ticket bookTicket(Person person, LocalDate date, double price) {
        double adjustedPrice = 0;
        if (date.getDayOfMonth() % 2 == 0) {
            adjustedPrice = price * 0.5;
        } else {
            int discountPercentage = Math.max(0, 100 - person.getName().length()); // Ensure discount is not negative
            adjustedPrice = price * (discountPercentage / 100.0);
        }
        return new Ticket(round(adjustedPrice));
    }
}
