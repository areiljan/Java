package ee.taltech.iti0202.travelagency.client;

// Builder class for creating Person objects
public class ClientBuilder {
    private String idCode;
    private String name;
    private Integer age;
    private String email;
    private int budget;

    /**
     * ClientBuilder constructor.
     * @param idCode - Persons idCode.
     * @param name - Persons name.
     * @param budget - Persons budget.
     * @return - Client.
     */
    public ClientBuilder(String idCode, String name, int budget) {
            this.idCode = idCode;
            this.name = name;
            this.budget = budget;
    }

    /**
     * ClientBuilder email setter.
     * @param email - Persons email.
     * @return - person.
     */
    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Age setter.
     * @param age - Persons age.
     * @return - person.
     */
    public ClientBuilder withAge(int age) throws IllegalArgumentException {
            if (age > 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            return this;
    }

    /**
     * Person builder.
     * @return - new person same old mistakes.
     */
    public Client build() {
            return new Client(idCode, name, email, age, budget);
        }
}
