package ee.taltech.iti0202.delivery;

public interface Strategy {
    /**
     * Get the next action to do.
     * @return - action.
     */
    Action getAction();
}
