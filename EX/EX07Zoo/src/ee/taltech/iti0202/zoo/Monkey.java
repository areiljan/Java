package ee.taltech.iti0202.zoo;

import java.util.Optional;
import java.util.Random;

public class Monkey extends Animal {
    /**
     * Monkey constructor.
     * @param name - the name of the monkey.
     * @param animalType - the type of animal.
     * @param sound - the sound he makes.
     * @param endurance - how many days does he get hungrier.
     */
    public Monkey(String name, AnimalTypes animalType, String sound, Integer endurance) {
        super(name, animalType, sound, endurance);
        this.animalType = AnimalTypes.MAMMAL;
        this.sound = "BANANA";
    }

    @Override
    public Optional<String> makeSound() {
        if (hungerLevel == endurance) {
            return Optional.ofNullable(sound);
        }
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        String sound1 = "uuh";
        String sound2 = "ääh";
        String chosenSound = (randomNumber == 0) ? sound1 : sound2;
        return Optional.ofNullable(chosenSound);
    }
}
