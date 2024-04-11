package ee.taltech.iti0202.transportation.strategy;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.ticket.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TrainStrategy implements BookingStrategy {
    @Override
    public Ticket bookTicket(Person person, LocalDate date, double price) {
        if (!date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return new Ticket(price * 0.75);
        } else {
            return new Ticket(price);
        }
    }
}
