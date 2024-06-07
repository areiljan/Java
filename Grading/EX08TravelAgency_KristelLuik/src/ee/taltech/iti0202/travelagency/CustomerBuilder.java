package ee.taltech.iti0202.travelagency;

public class CustomerBuilder {
    private String id;
    private String name;
    private String email;
    private int age;
    private double budget;

    /**
     * Set id
     * @param id for id
     */
    public CustomerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Set name
     * @param name for name
     */
    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set email
     * @param email for email
     */
    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Set age
     * @param age for age
     */
    public CustomerBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Set budget
     * @param budget for budget
     */
    public CustomerBuilder setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    /**
     * Build customer
     * @return customer
     */
    public Customer build() {
        return new Customer(id, name, email, age, budget);
    }
}
