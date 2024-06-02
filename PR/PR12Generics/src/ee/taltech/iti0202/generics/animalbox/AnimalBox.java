package ee.taltech.iti0202.generics.animalbox;

public class AnimalBox<Animal> {
    private Animal animal;
    public AnimalBox() {this.animal = null;}

    public void put(Animal animal) {
        this.animal = animal;
    }

    public String sound() {
        if (animal != null) {
            return animal.getSound();
        } else {
            return "";
        }
    }


}
