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
    public void addCaretaker (Caretaker caretaker) {
        caretakerList.add(caretaker);
    }

    /**
     * Add an Animal.
     */
    public void addAnimal (Animal animal) {
        this.animalList.add(animal);
    }

    /**
     * Let the fed animals make a sound.
     */
    public Map<String, String> makeSound () {
        return animalList.stream()
                .collect(Collectors.toMap(
                        animal -> String.format("%s: %s", animal.getAnimalType(), animal.getName()),
                        animal -> animal.makeSound().orElse("")
                ));
    }

    /**
     * Return all unfed animals.
    public List<Animal> unfedAnimals () {
        return animalList.stream()
                .collect(Collectors.toMap(
                        
                )
    }
     **/
}
