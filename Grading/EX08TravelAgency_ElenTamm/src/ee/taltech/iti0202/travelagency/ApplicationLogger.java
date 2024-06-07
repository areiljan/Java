package ee.taltech.iti0202.travelagency;

import java.util.logging.Logger;

/**
 * The type Application logger.
 */
public class ApplicationLogger {
    /**
     * Gets logger.
     *
     * @param name the name
     * @return the logger
     */
    public static Logger getLogger(String name) {
        return java.util.logging.Logger.getLogger(name);
    }
}
