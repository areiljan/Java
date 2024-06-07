package ee.taltech.iti0202.travelagency.client;

import ee.taltech.iti0202.travelagency.ApplicationLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Client builder.
 */
public class ClientBuilder {
    private String idCode;
    private String name;
    private String email;
    private int age;
    /**
     * The Logger.
     */
    Logger logger = ApplicationLogger.getLogger(ClientBuilder.class.getName());

    /**
     * Sets id code.
     *
     * @param idCode the id code
     * @return the id code
     */
    public ClientBuilder setIdCode(String idCode) {
        logger.log(Level.INFO, "Setting client builder id code");
        this.idCode = idCode;
        return this;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public ClientBuilder setName(String name) {
        logger.log(Level.INFO, "Setting client builder name");
        this.name = name;
        return this;
    }

    /**
     * Sets email.
     *
     * @param email the email
     * @return the email
     */
    public ClientBuilder setEmail(String email) {
        logger.log(Level.INFO, "Setting client builder email");
        this.email = email;
        return this;
    }

    /**
     * Sets age.
     *
     * @param age the age
     * @return the age
     */
    public ClientBuilder setAge(int age) {
        logger.log(Level.INFO, "Setting client builder age");
        this.age = age;
        return this;
    }

    /**
     * Creates a client.
     *
     * @return the client
     */
    public Client createClient() {
        logger.log(Level.INFO, "Building client");
        return new Client(idCode, name, email, age);
    }
}
