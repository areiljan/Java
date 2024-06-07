package ee.taltech.iti0202.travelagency.client;

public class StandardClientBuilder {
    private int id;
    private String name;
    private String email;
    private int age;
    private double budget;

    /**
     * @param id Id
     * @return This
     */
    public StandardClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @param name Name
     * @return This
     */
    public StandardClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param email Email
     * @return This
     */
    public StandardClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @param age Age
     * @return This
     */
    public StandardClientBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * @param budget Budget
     * @return This
     */
    public StandardClientBuilder setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    /**
     * @return Client
     */
    public StandardClient createStandardClient() {
        return new StandardClient(id, name, email, age, budget);
    }
}
