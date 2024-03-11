package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

public class CannotFixException extends Throwable {
    private final Oven oven;
    private final Reason reason;

    /**
     * Exception constructor.
     * @param oven - the Oven that the exception happened with.
     * @param reason - Reason of the exception.
     */
    public CannotFixException(Oven oven, Reason reason) {
        this.oven = oven;
        this.reason = reason;
    }
    public enum Reason {
        IS_NOT_BROKEN, FIXED_MAXIMUM_TIMES, NOT_ENOUGH_RESOURCES
    }

    public Oven getOven() {
        return oven;
    }

    public Reason getReason() {
        return reason;
    }
}
