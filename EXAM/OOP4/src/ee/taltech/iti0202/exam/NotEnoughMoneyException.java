package ee.taltech.iti0202.exam;

import java.time.LocalDate;

public class NotEnoughMoneyException extends Exception {
    private LocalDate dateToBook;

    /**
     * Exception for when the client does not have enough money.
     */
    public NotEnoughMoneyException() {
        super("The client does not have enough money");
    }
}