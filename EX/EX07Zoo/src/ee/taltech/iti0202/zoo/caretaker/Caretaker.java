package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.Animal.AnimalTypes;

import java.util.ArrayList;

public class Caretaker {
    public ArrayList<AnimalTypes> getSpecializations() {
        return specialisation;
    }
    private ArrayList<AnimalTypes> specialisation;
    private final String name;

    /**
     * Caretaker constructor.
     * @param name - name of the constructor.
     * @param specialisation - the specialisation he/she has.
     */
    public Caretaker(String name, ArrayList specialisation) {
        this.specialisation = specialisation;
        this.name = name;
    }

    /**
     * Feed the animals specified.
     * @param animalsToFeed - which animals to feed.
     */
    public void feedAnimals(ArrayList<Animal> animalsToFeed) {
        for (Animal animal : animalsToFeed) {
            if (specialisation.contains(animal.getAnimalType())) {
                animal.feedAnimal();
            }
        }
    }
}
