package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private final ResourceStorage resourceStorage;
    private List<Oven> ovenList = new ArrayList<>();
    private List<Orb> orbList = new ArrayList<>();
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public void addOven(Oven oven) {
        ovenList.add(oven);
    }

    public List<Oven> getOvens() {
        return ovenList;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> returnableOrbList = orbList;
        orbList.clear();
        return returnableOrbList;
    }

    public int produceOrbs() {
        int producedOrbs = 0;
        for (Oven oven : ovenList) {
            Optional<Orb> orbOptional = oven.craftOrb();
            if(orbOptional.isPresent()) {
                orbList.add(orbOptional.get());
                producedOrbs++;
            }
        }
        return producedOrbs;
    }

    public int produceOrbs(int cycles) {
        int producedOrbs = 0;
        for (int i = 0; i < cycles; i++) {
            for (Oven oven : ovenList) {
                Optional<Orb> orbOptional = oven.craftOrb();
                if(orbOptional.isPresent()) {
                    orbList.add(orbOptional.get());
                    producedOrbs++;
                }
            }
        }
        return producedOrbs;
    }
}
