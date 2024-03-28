package ee.taltech.iti0202.zoo;

public class Lamb extends Animal {
    /**
     * Lamb constructor.
     * @param name - the name of the lamb.
     * @param animalType - the type of animal.
     * @param sound - the sound he makes.
     * @param endurance - how many days does he get hungrier.
     */
    public Lamb(String name, AnimalTypes animalType, String sound, Integer endurance) {
        super(name, animalType, sound, endurance);
        this.animalType = AnimalTypes.MAMMAL;
        this.sound = "Mää";
    }

    /**
     * A day passes.
     * This animal will never get hungrier.
     */
    public void getHungrier() {
        // Do nothing.
    }
}
