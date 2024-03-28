package ee.taltech.iti0202.zoo;

import java.util.Optional;

public class Turtle extends Animal {
    /**
     * Turtle constructor.
     * @param name - the name of the turtle.
     * @param animalType - the type of animal.
     * @param sound - the sound he makes.
     * @param endurance - how many days does he get hungrier.
     */
    public Turtle(String name, AnimalTypes animalType, String sound, Integer endurance) {
        super(name, animalType, sound, endurance);
        animalType = AnimalTypes.AMPHIBIAN;
    }

    @Override
    public Optional<String> makeSound() {
        return Optional.empty();
    }
}
