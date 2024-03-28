package ee.taltech.iti0202.zoo;

import java.util.Optional;

public class Turtle extends Animal{
    public Turtle(String name, AnimalTypes animalType , String sound, Integer endurance) {
        super(name, animalType = AnimalTypes.AMPHIBIAN, sound, endurance);
    }

    @Override
    public Optional<String> makeSound() {
        return Optional.empty();
    }
}
