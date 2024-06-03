package ee.taltech.iti0202.generics.animalbox;

import ee.taltech.iti0202.generics.animal.Animal;
import ee.taltech.iti0202.generics.food.Food;

import java.util.Optional;

public class AnimalBox<T extends Animal> {
    private Animal animal;
    public AnimalBox() {this.animal = null;}

    public void put(Animal animal) {
        this.animal = animal;
    }

    public String sound() {
        if (animal != null) return animal.sound();
        else {
            return "";
        }
    }

    public <T extends Food> String  feed(T food) {
        if (animal != null) {
            return (animal.getName() + " was fed with " + food.getName() + ".");
        } else {
            return ("No animal to feed.");
        }
    }

    public Optional<T> getAnimal() {
        return Optional.ofNullable((T) animal);
    }
}
