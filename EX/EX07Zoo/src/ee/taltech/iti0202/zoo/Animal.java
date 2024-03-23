package ee.taltech.iti0202.zoo;

import java.util.Optional;

public class Animal {
    private final Integer endurance;
    private final AnimalType animalType;
    private Integer hungerLevel;
    private final String sound;
    private final String name;

    /**
     * Getter for type.
     * @return - animalType.
     */
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Getter for hungerLevel.
     * @return - hungerlevel.
     */
    public Integer getHungerLevel() {
        return hungerLevel;
    }

    /**
     * Getter for the animals name.
     * @return - animals name.
     */
    public String getName() {
        return name;
    }

    public Animal(String name, AnimalType animalType , String sound, Integer endurance) {
        this.name = name;
        this.sound = sound;
        this.endurance = endurance;
        this.hungerLevel = endurance;
        this.animalType = animalType;
    }

    public enum AnimalType {
        MAMMAL, BIRD, FISH, REPTILE, AMPHIBIAN
    }

    /**
     * Make a sound when not starving.
     * @return - Sound or Optional.empty().
     */
    public Optional<String> makeSound() {
        if (hungerLevel > 0) {
            return Optional.ofNullable(sound);
        }
        return Optional.empty();
    }

    /**
     * Feed the animal.
     * The animals hunger level will be maxed.
     */
    public void feedAnimal () {
        this.hungerLevel = this.endurance;
    }

    /**
     * A day passes.
     * The animal will get hungrier.
     */
    public void getHungrier () {
        this.hungerLevel -= 1;
    }








}
