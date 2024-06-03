package ee.taltech.iti0202.generics.animal;

public class PersianCat extends Animal {

    /**
     * PersianCat.
     * @param name - name of the cat.
     */
    public PersianCat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Prrr-prrr";
    }
}
