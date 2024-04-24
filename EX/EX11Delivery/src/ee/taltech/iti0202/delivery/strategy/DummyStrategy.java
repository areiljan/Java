package ee.taltech.iti0202.delivery.strategy;

import ee.taltech.iti0202.delivery.action.Action;

import java.util.ArrayList;
import java.util.List;

public class DummyStrategy implements Strategy {
    private List<Action> actions = new ArrayList<>();
    public DummyStrategy(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public Action getAction() {
        return actions.removeFirst();
    }
}
