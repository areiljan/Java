package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven {
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return createdOrbs >= 25;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if(!this.isBroken() && resourceStorage.hasEnoughResource("meteorite stone", 1) && resourceStorage.hasEnoughResource("star fragment", 15)) {
            incrementOrbs();
            return Optional.of(new SpaceOrb(name));
        } else if(resourceStorage.hasEnoughResource("pearl", 1) && resourceStorage.hasEnoughResource("silver", 1)) {
            return Optional.of(new Orb(name));
        } else {
            return Optional.empty();
        }
    }

}
