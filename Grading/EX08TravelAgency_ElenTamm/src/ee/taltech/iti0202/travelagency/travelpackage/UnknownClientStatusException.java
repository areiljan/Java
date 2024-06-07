package ee.taltech.iti0202.travelagency.travelpackage;

/**
 * The type Cannot sell travel package exception.
 */
public class UnknownClientStatusException extends Exception {
    /**
     * Instantiates a new TravelPackage exception.
     * @param message the message
     */
    public UnknownClientStatusException(String message) {
        super(message);
    }
}
