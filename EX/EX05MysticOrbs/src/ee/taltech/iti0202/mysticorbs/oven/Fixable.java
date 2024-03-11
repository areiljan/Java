package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * Fix the Oven.
     * @throws CannotFixException - If the Oven could not be fixed.
     */
    void fix() throws CannotFixException;

    /**
     * Getter for FixCount.
     * @return - times the Oven has been fixed.
     */
    int getTimesFixed();
}
