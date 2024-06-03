package ee.taltech.iti0202.generics.animal;

public class SphynxCat extends Animal {
    private String name;
    public SphynxCat(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String sound() {
        return "Meeeeeoooooww!";
    }
}
