package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;

// Builder class for creating Person objects
    public class PersonBuilder {
        private String idCode;
        private String name;
        private Integer age;
        private Boolean isMale;

    /**
     * PersonBuilder constructor.
     * @param idCode - id code as string.
     */
    public PersonBuilder(String idCode) {
        // mandatory parameter.
            this.idCode = idCode;
        }

    /**
     * PersonBuilder name setter.
     * @param name - Persons name.
     * @return - name.
     */
    public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

    /**
     * Age setter.
     * @param age - Persons age.
     * @return - person.
     */
    public PersonBuilder withAge(Integer age) throws IllegalArgumentException {
            if (age > 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            return this;
        }

    /**
     * Male setter.
     * @param isMale - male boolean.
     * @return - person.
     */
    public PersonBuilder isMale(boolean isMale) {
            this.isMale = isMale;
            return this;
        }


    /**
     * Person builder.
     * @return - new person same old mistakes.
     */
    public Person build() {
            return new Person(idCode, name, age, isMale);
        }
    }
