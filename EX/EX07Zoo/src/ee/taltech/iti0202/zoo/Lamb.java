package ee.taltech.iti0202.zoo;

public class Lamb extends Animal {
    public Lamb(String name, AnimalTypes animalType, String sound, Integer endurance) {
        super(name, animalType = AnimalTypes.MAMMAL, sound = "Mää", endurance);
    }

    /**
     * A day passes.
     * This animal will never get hungrier.
     */
    public void getHungrier () {
        // Do nothing.
    }
}
