package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private final ResourceStorage resourceStorage;
    private List<Orb> orbList;
    private List<Oven> ovenList = new ArrayList<>();
    private List<Oven> unfixableOvens = new ArrayList<>();
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.orbList = new ArrayList<>();
    }

    /**
     * Add an Oven to the ovenList.
     * @param oven - which oven to add.
     */
    public void addOven(Oven oven) {
        ovenList.add(oven);
    }

    /**
     * OvenList getter.
     * @return the list of ovens.
     */
    public List<Oven> getOvens() {
        return ovenList;
    }

    /**
     * Return the list and clear it.
     *
     * @return produced orb List.
     */
    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> returnableOrbList = new ArrayList<>(orbList);
        orbList.clear();
        return returnableOrbList;
    }

    /**
     * Try to produce one orb from each oven.
     *
     * @return produced orb quantity
     */
    public int produceOrbs() {
        int producedOrbs = 0;
        for (Oven oven : ovenList) {
            if (!oven.isFixable() && !unfixableOvens.contains(oven)) {
                unfixableOvens.add(oven);
            }
            try {
                oven.fix(); // Attempt to fix the oven
            } catch (CannotFixException e) {
                // Do nothing.
            }

            // If oven is fixed or doesn't need fixing, proceed to crafting orbs
            Optional<Orb> orbOptional = oven.craftOrb();
            if (orbOptional.isPresent()) {
                orbList.add(orbOptional.get());
                producedOrbs++;
            }
        }
        return producedOrbs;
    }

    /**
     * Produce orbs from ovens, run each oven for the cycle amount.
     * @param cycles - how many run cycles.
     * @return produced orb quantity
     */
    public int produceOrbs(int cycles) {
        int producedOrbs = 0;
        for (int i = 0; i < cycles; i++) {
            for (Oven oven : ovenList) {
                if (!oven.isFixable() && !unfixableOvens.contains(oven)) {
                    unfixableOvens.add(oven);
                }
                try {
                    oven.fix(); // Attempt to fix the oven
                } catch (CannotFixException e) {
                    // Do nothing.
                }

                // If oven is fixed or doesn't need fixing, proceed to crafting orbs
                Optional<Orb> orbOptional = oven.craftOrb();
                if (orbOptional.isPresent()) {
                    orbList.add(orbOptional.get());
                    producedOrbs++;
                }
            }
        }
        return producedOrbs;
    }


    public void optimizeOvensOrder() {
        Collections.sort(ovenList);
    }

    /**
     * Getter for unfixable ovens.
     * @return - unfixable ovens.
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        return unfixableOvens;
    }

    /**
     * Remove the ovens that cannot be fixec.
     */
    public void getRidOfOvensThatCannotBeFixed() {
        ovenList.removeAll(unfixableOvens);
    }
}
