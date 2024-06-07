package ee.taltech.iti0202.travelagency.client;

public class GoldClientBuilder {
    private int id;
    private String name;
    private String email;
    private int age;
    private double budget;

    /**
     * @param id Id
     * @return This
     */
    public GoldClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @param name Name
     * @return This
     */
    public GoldClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param email Email
     * @return This
     */
    public GoldClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @param age Age
     * @return This
     */
    public GoldClientBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * @param budget Budget
     * @return This
     */
    public GoldClientBuilder setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    /**
     * @return Client
     */
    public GoldClient createGoldClient() {
        return new GoldClient(id, name, email, age, budget);
    }
}
