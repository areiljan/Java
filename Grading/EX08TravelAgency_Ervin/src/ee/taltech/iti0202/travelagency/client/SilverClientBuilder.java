package ee.taltech.iti0202.travelagency.client;

public class SilverClientBuilder {
    private int id;
    private String name;
    private String email;
    private int age;
    private double budget;

    /**
     * @param id Id
     * @return This
     */
    public SilverClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @param name Name
     * @return This
     */
    public SilverClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param email Email
     * @return This
     */
    public SilverClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @param age Age
     * @return This
     */
    public SilverClientBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * @param budget Budget
     * @return This
     */
    public SilverClientBuilder setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    /**
     * @return Client
     */
    public SilverClient createSilverClient() {
        return new SilverClient(id, name, email, age, budget);
    }
}
