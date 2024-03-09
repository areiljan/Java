package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class InfinityMagicOven extends Oven {
    private int orbCounter;

    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.orbCounter = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if(!this.isBroken() && resourceStorage.hasEnoughResource("gold", 1) && resourceStorage.hasEnoughResource("dust", 3)) {
            incrementOrbs();
            orbCounter++;
            if(orbCounter / 2 == 0) {
                return Optional.of(new MagicOrb(name));
            } else {
                return Optional.of(new Orb(name));
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
