package ee.taltech.iti0202.travelagency.client;

public class InsufficientFundsException extends Exception {
    /**
     * Thrown when the client does not have enough money.
     * @param message - which message to display.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
