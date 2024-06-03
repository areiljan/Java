package ee.taltech.iti0202.generics.animal;

public class SphynxCat extends Animal {
    private String name;

    /**
     * SphynxCat.
     * @param name - name of the cat.
     */
    public SphynxCat(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String sound() {
        return "Meeeeeoooooww!";
    }
}
