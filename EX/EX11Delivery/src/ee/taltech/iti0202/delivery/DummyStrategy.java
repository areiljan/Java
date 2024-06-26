package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class DummyStrategy implements Strategy {
    private List<Action> actions;

    /**
     * DummyStrategy constructor.
     * @param actions - list of actions.
     */
    public DummyStrategy(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    @Override
    public Action getAction() {
        if (!actions.isEmpty()) {
            return actions.remove(0); // Remove and return the first action
        } else {
            // Handle the case when there are no actions left
            return null; // or throw an exception, depending on your requirements
        }
    }
}
