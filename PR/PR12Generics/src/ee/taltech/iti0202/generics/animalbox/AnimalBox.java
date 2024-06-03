package ee.taltech.iti0202.generics.animalbox;

import ee.taltech.iti0202.generics.animal.Animal;
import ee.taltech.iti0202.generics.food.Food;

import java.util.Optional;

public class AnimalBox<T extends Animal> {
    private T animal;

    /**
     * AnimalBox constructor.
     */
    public AnimalBox() {
        this.animal = null;
    }

    /**
     * Put animal in the box.
     * @param animal - animal to cage.
     */
    public void put(T animal) {
        this.animal = animal;
    }

    /**
     * Make animal in cage make sound.
     * @return - sound of the animal in the cage.
     */
    public String sound() {
        if (animal != null) return animal.sound();
        else {
            return "";
        }
    }

    /**
     * Feed the caged animal.
     * @param food - food to give to the animal.
     * @return - string describing the feeding.
     * @param <F> - which kind of food to feed.
     */
    public <F extends Food> String  feed(F food) {
        if (animal != null) {
            return (animal.getName() + " was fed with " + food.getName() + ".");
        } else {
            return ("No animal to feed.");
        }
    }

    /**
     * Get an optional of the animal.
     * @return - optional of animal.
     */
    public Optional<T> getAnimal() {
        return Optional.ofNullable(animal);
    }
}
