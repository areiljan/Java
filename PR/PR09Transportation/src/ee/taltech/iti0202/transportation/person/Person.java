package ee.taltech.iti0202.transportation.person;

public class Person {
    private final int age;
    private final String name;

    /**
     * Person constructor
     * @param name - name of the person.
     * @param age - age of the person.
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Age getter.
     * @return - age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Name getter.
     * @return - name.
     */
    public String getName() {
        return name;
    }
}
