package ee.taltech.iti0202.transportation.booking;

import ee.taltech.iti0202.transportation.person.Person;
import ee.taltech.iti0202.transportation.strategy.BookingStrategy;

import java.time.LocalDate;

public class Booking {
    private final Person person;
    private final LocalDate date;
    private BookingStrategy bookingStrategy;

    /**
     * 
     * @param person
     * @param date
     */
    public Booking(Person person, LocalDate date) {
        this.person = person;
        this.date = date;
        this.bookingStrategy = null;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setStrategy(BookingStrategy strategy) {
        bookingStrategy = strategy;
    }

    public BookingStrategy getStrategy() {
        return bookingStrategy;
    }
}
