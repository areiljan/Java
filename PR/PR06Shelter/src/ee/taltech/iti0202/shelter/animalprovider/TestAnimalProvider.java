package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Wombat;

import java.util.ArrayList;
import java.util.List;

public class TestAnimalProvider implements AnimalProvider {
    @Override
    public List<Animal> provide(Animal.Type type) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Wombat("red"));

        return animalList;
    }
}