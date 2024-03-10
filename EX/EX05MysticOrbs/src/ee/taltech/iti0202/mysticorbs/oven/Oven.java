package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Oven {
    protected final String name;
    protected final ResourceStorage resourceStorage;
    protected Integer createdOrbs;

    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbs;
    }

    public void incrementOrbs() {
        createdOrbs++;
    }

    public boolean isBroken() {
        return createdOrbs >= 15;
    }

    public Optional<Orb> craftOrb() {
        if(!this.isBroken() && resourceStorage.hasEnoughResource("pearl", 1) && resourceStorage.hasEnoughResource("silver", 1)) {
            incrementOrbs();
            Orb createdOrb = new Orb(name);
            return Optional.of(createdOrb);
        } else {
            return Optional.empty();
        }
    }
}
