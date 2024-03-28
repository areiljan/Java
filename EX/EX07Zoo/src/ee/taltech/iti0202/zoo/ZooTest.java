package ee.taltech.iti0202.zoo;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ZooTest {
    @org.junit.jupiter.api.Test
    void TurtleMakesRightSound() {
        Zoo zoo = new Zoo();
        Animal oceanTurtle = new Turtle("Billy", Animal.AnimalTypes.AMPHIBIAN, "Ni Hao Ma", 15);
        zoo.addAnimal(oceanTurtle);
        zoo.aDayPasses();


        Map<String, String> ExpectedSounds = new HashMap<>();
        ExpectedSounds.put(String.format("%s: %s", oceanTurtle.getAnimalType(), oceanTurtle.getName()), ""); // A turtle makes no sound
        Assertions.assertEquals(ExpectedSounds, zoo.makeSound());
    }

    @org.junit.jupiter.api.Test
    void SheepMakesRightSoundFed() {
        Zoo zoo = new Zoo();
        Animal blackSheep = new Lamb("Fluffy", Animal.AnimalTypes.MAMMAL, "Bing Chi Ling", 15);
        zoo.addAnimal(blackSheep);


        Map<String, String> ExpectedSounds = new HashMap<>();
        ExpectedSounds.put(String.format("%s: %s", blackSheep.getAnimalType(), blackSheep.getName()), "Mää");
        Assertions.assertEquals(ExpectedSounds, zoo.makeSound());
    }

    @org.junit.jupiter.api.Test
    void SheepMakesRightSoundUnfed() {
        Zoo zoo = new Zoo();
        Animal blackSheep = new Lamb("Fluffy", Animal.AnimalTypes.MAMMAL, "Bing Chi Ling", 15);
        zoo.addAnimal(blackSheep);
        zoo.aDayPasses();


        Map<String, String> ExpectedSounds = new HashMap<>();
        ExpectedSounds.put(String.format("%s: %s", blackSheep.getAnimalType(), blackSheep.getName()), "Mää");
        Assertions.assertEquals(ExpectedSounds, zoo.makeSound());
    }

    @org.junit.jupiter.api.Test
    void RegularAnimalsMakeRightSoundUnfed() {
        Zoo zoo = new Zoo();
        Animal crocodileGena = new Animal("Gena", Animal.AnimalTypes.REPTILE, "Bing Chi Ling", 2);
        zoo.addAnimal(crocodileGena);
        zoo.aDayPasses();
        zoo.aDayPasses();

        Map<String, String> ExpectedSounds = new HashMap<>();
        ExpectedSounds.put(String.format("%s: %s", crocodileGena.getAnimalType(), crocodileGena.getName()), "");
        Assertions.assertEquals(ExpectedSounds, zoo.makeSound());
    }

    @org.junit.jupiter.api.Test
    void UnfedMonkeySoundRandomness() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 2);
        zoo.addAnimal(orangutan);
        zoo.aDayPasses();

        ArrayList<String> expectedSounds = new ArrayList<>();
        expectedSounds.add("uuh");
        expectedSounds.add("ääh");
        Map<String, String> soundsMap = zoo.makeSound();
        String rightSound = soundsMap.get("MAMMAL: Oogabooga");
        if (expectedSounds.contains(rightSound)) {
            System.out.println("Test passed: Sound matches one of the expected sounds");
        } else {
            System.out.println("Test failed: Sound does not match any of the expected sounds");
        }
    }

    @org.junit.jupiter.api.Test
    void MonkeyMakesRightSoundFed() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 2);
        zoo.addAnimal(orangutan);
        
        Map<String, String> ExpectedSounds = new HashMap<>();
        ExpectedSounds.put(String.format("%s: %s", orangutan.getAnimalType(), orangutan.getName()), "BANANA");
        Assertions.assertEquals(ExpectedSounds, zoo.makeSound());
    }

    @org.junit.jupiter.api.Test
    void unfedAnimals() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 15);
        Animal oceanTurtle = new Turtle("Billy", Animal.AnimalTypes.AMPHIBIAN, "Ni Hao Ma", 15);
        Animal blackSheep = new Lamb("Fluffy", Animal.AnimalTypes.MAMMAL, "Bing Chi Ling", 15);
        zoo.addAnimal(orangutan);
        zoo.addAnimal(oceanTurtle);
        zoo.addAnimal(blackSheep);

        zoo.aDayPasses();
        zoo.aDayPasses();
        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(orangutan);
        expectedAnimals.add(oceanTurtle);
        Assertions.assertEquals(expectedAnimals, zoo.unfedAnimals());
    }

    @org.junit.jupiter.api.Test
    void caretakerWhoFeedsMostAnimals() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 2);
        Animal oceanTurtle = new Turtle("Billy", Animal.AnimalTypes.AMPHIBIAN, "Ni Hao Ma", 2);
        Animal crocodileGena = new Animal("Gena", Animal.AnimalTypes.REPTILE, "Bing Chi Ling", 2);
        zoo.addAnimal(orangutan);
        zoo.addAnimal(oceanTurtle);
        zoo.addAnimal(crocodileGena);
        ArrayList<Animal.AnimalTypes> caretaker1Specializations = new ArrayList<>();
        caretaker1Specializations.add(Animal.AnimalTypes.MAMMAL);
        caretaker1Specializations.add(Animal.AnimalTypes.BIRD);
        caretaker1Specializations.add(Animal.AnimalTypes.AMPHIBIAN);
        Caretaker caretaker1 = new Caretaker("Sveta", caretaker1Specializations);
        zoo.addCaretaker(caretaker1);
        ArrayList<Animal.AnimalTypes> caretaker2Specializations = new ArrayList<>();
        caretaker2Specializations.add(Animal.AnimalTypes.FISH);
        caretaker2Specializations.add(Animal.AnimalTypes.BIRD);
        caretaker1Specializations.add(Animal.AnimalTypes.REPTILE);
        Caretaker caretaker2 = new Caretaker("Bruno", caretaker2Specializations);
        zoo.addCaretaker(caretaker2);

        zoo.aDayPasses();

        Assertions.assertEquals(caretaker1, zoo.caretakerWhoFeedsMostAnimals());
    }

    @org.junit.jupiter.api.Test
    void CaretakerFeedsNobodyBecauseNoSpecialization() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 15);
        Animal oceanTurtle = new Turtle("Billy", Animal.AnimalTypes.AMPHIBIAN, "Ni Hao Ma", 15);
        ArrayList<Animal.AnimalTypes> caretaker1Specializations = new ArrayList<>();
        caretaker1Specializations.add(Animal.AnimalTypes.REPTILE);
        caretaker1Specializations.add(Animal.AnimalTypes.BIRD);
        Caretaker caretaker1 = new Caretaker("Sveta", caretaker1Specializations);

        zoo.addCaretaker(caretaker1);
        zoo.addAnimal(orangutan);
        zoo.addAnimal(oceanTurtle);
        zoo.aDayPasses();
        zoo.aDayPasses();
        ArrayList<Animal> animalsToFeed = new ArrayList<>();
        animalsToFeed.add(orangutan);
        animalsToFeed.add(oceanTurtle);
        caretaker1.feedAnimals(animalsToFeed);

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(orangutan);
        expectedAnimals.add(oceanTurtle);
        Assertions.assertEquals(expectedAnimals, zoo.unfedAnimals());
    }

    @org.junit.jupiter.api.Test
    void CaretakerFeedsAnimalsBecauseHasSpecialization() {
        Zoo zoo = new Zoo();
        Animal orangutan = new Monkey("Oogabooga", Animal.AnimalTypes.MAMMAL, "Ching", 15);
        Animal oceanTurtle = new Turtle("Billy", Animal.AnimalTypes.AMPHIBIAN, "Ni Hao Ma", 15);
        ArrayList<Animal.AnimalTypes> caretaker1Specializations = new ArrayList<>();
        caretaker1Specializations.add(Animal.AnimalTypes.MAMMAL);
        caretaker1Specializations.add(Animal.AnimalTypes.AMPHIBIAN);
        Caretaker caretaker1 = new Caretaker("Sveta", caretaker1Specializations);

        zoo.addCaretaker(caretaker1);
        zoo.addAnimal(orangutan);
        zoo.addAnimal(oceanTurtle);
        zoo.aDayPasses();
        zoo.aDayPasses();
        ArrayList<Animal> animalsToFeed = new ArrayList<>();
        animalsToFeed.add(orangutan);
        animalsToFeed.add(oceanTurtle);
        caretaker1.feedAnimals(animalsToFeed);

        List<Animal> expectedAnimals = new ArrayList<>();
        Assertions.assertEquals(expectedAnimals, zoo.unfedAnimals());
    }
}
