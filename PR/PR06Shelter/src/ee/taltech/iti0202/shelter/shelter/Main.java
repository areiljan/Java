package ee.taltech.iti0202.shelter.shelter;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animalprovider.TestAnimalProvider;

public class Main {
    public static void main(String[] args) {
        TestAnimalProvider testAnimalProvider = new TestAnimalProvider();
        AnimalShelter shelter = new AnimalShelter(testAnimalProvider);
        System.out.println(shelter.getAnimals(Animal.Type.WOMBAT, "red", 2));
    }
}
