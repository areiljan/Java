package ee.taltech.iti0202.generics.animal;

public class DobermanDog extends Animal {
    public DobermanDog(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Woof!";
    }
}
