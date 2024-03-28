package ee.taltech.iti0202.zoo;

import java.util.Optional;

public class Animal {
    public Integer getEndurance() {
        return endurance;
    }

    protected final Integer endurance;
    protected AnimalTypes animalType;
    protected Integer hungerLevel;
    protected String sound;
    protected final String name;

    /**
     * Getter for type.
     * @return - animalType.
     */
    public AnimalTypes getAnimalType() {
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

    /**
     * Animal constructor.
     * @param name - name of the animal.
     * @param animalType - the type of the animal.
     * @param sound - the sound the animal makes.
     * @param endurance how many days does he get hungrier.
     */
    public Animal(String name, AnimalTypes animalType, String sound, Integer endurance) {
        this.name = name;
        this.sound = sound;
        this.endurance = endurance;
        this.hungerLevel = endurance;
        this.animalType = animalType;
    }

    public enum AnimalTypes {
        MAMMAL, BIRD, FISH, REPTILE, AMPHIBIAN
    }

    /**
     * Make a sound when not starving.
     * @return - Sound or Optional.empty().
     */
    public Optional<String> makeSound() {
        if (hungerLevel.equals(endurance)) {
            return Optional.ofNullable(sound);
        }
        return Optional.empty();
    }

    /**
     * Feed the animal.
     * The animals hunger level will be maxed.
     */
    public void feedAnimal() {
        this.hungerLevel = this.endurance;
    }

    /**
     * A day passes.
     * The animal will get hungrier.
     */
    public void getHungrier() {
        this.hungerLevel -= 1;
    }








}
