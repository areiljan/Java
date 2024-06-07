package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.packages.TravelPackage;

public class ClientBuilder {
    private int id;
    private String name;
    private String email;
    private int age;
    private double budget;

    /**
     * @param id Id
     * @return Id
     */
    public ClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @param name Name
     * @return Name
     */
    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param email Email
     * @return Email
     */
    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @param age Age
     * @return Age
     */
    public ClientBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * @param budget Budget
     * @return Budget
     */
    public ClientBuilder setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    /**
     * @return Client
     */
    public Client createClient() {
        return new Client(id, name, email, age, budget) {
            @Override
            public boolean purchasePackage(TravelPackage travelPackage) {
                return false;
            }

            @Override
            public double calculateDiscount(TravelPackage travelPackage) {
                return 0;
            }
        };
    }
}
