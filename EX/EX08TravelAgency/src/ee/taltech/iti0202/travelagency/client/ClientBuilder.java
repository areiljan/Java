package ee.taltech.iti0202.travelagency.client;

// Builder class for creating Person objects
public class ClientBuilder {
    private String idCode;
    private String name;
    private Integer age;
    private String email;
    private int budget;
    private String phoneNumber;
    private String city;

    /**
     * ClientBuilder constructor.
     * @param idCode - Persons idCode.
     * @param name - Persons name.
     * @param age - Persons budget.
     * @return - Client.
     */
    public ClientBuilder(String idCode, String name, String email, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.idCode = idCode;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    /**
     * ClientBuilder phoneNumber setter.
     * @param phoneNumber - Persons phoneNumber.
     * @return - ClientBuilder.
     */
    public ClientBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * ClientBuilder city setter.
     * @param city - Persons city.
     * @return - ClientBuilder.
     */
    public ClientBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * ClientBuilder budget setter.
     * If the budget is not set, then the client can buy everything.
     * @param budget - Persons budget.
     * @return - ClientBuilder.
     */
    public ClientBuilder withBudget(Integer budget) {
        this.budget = budget;
        return this;
    }

    /**
     * Client builder.
     * @return - Client.
     */
    public Client build() {
            return new Client(idCode, name, email, age, budget, phoneNumber, city);
        }
}
