package ee.taltech.iti0202.zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Zoo {
    private List<Caretaker> caretakerList;
    private List<Animal> animalList;
    public Zoo() {
        this.caretakerList = new ArrayList<>();
        this.animalList = new ArrayList<>();
    }

    /**
     * Add a Caretaker.
     */
    public void addCaretaker(Caretaker caretaker) {
        caretakerList.add(caretaker);
    }

    /**
     * Add an Animal.
     */
    public void addAnimal(Animal animal) {
        this.animalList.add(animal);
    }

    /**
     * Let the fed animals make a sound.
     */
    public Map<String, String> makeSound() {
        return animalList.stream()
                .collect(Collectors.toMap(
                        animal -> String.format("%s: %s", animal.getAnimalType(), animal.getName()),
                        animal -> animal.makeSound().orElse("")
                ));
    }

    /**
     * Return all unfed animals.
     */
     public List<Animal> unfedAnimals() {
        return animalList.stream()
                .filter(animal -> animal.getHungerLevel() != animal.getEndurance())
                .collect(Collectors.toList());
    }

    /**
     * Return the most productive CareTaker as of now.
     * The caretaker who could feed the most amount of Animals.
     */
    public Caretaker caretakerWhoFeedsMostAnimals() {
        if (caretakerList == null || caretakerList.isEmpty()) {
            return null;
        }

        List<Animal> unfedAnimals = unfedAnimals();
        if (unfedAnimals == null || unfedAnimals.isEmpty()) {
            return null;
        }

        Map<Caretaker, Long> counts = caretakerList.stream()
                .collect(Collectors.toMap(
                        caretaker -> caretaker,
                        caretaker -> unfedAnimals.stream()
                                .filter(animal -> caretaker.getSpecializations().contains(animal.getAnimalType()))
                                .count()
                ));

        Map.Entry<Caretaker, Long> maxEntry = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        return maxEntry != null ? maxEntry.getKey() : null;
    }


    /**
     * The animals will get hungrier.
     */
    public void aDayPasses() {
        for (Animal animal : animalList) {
            animal.getHungrier();
        }
    }
}
